package de.thws.fiw.kotlin.portfolio1

import kotlinx.cli.*
import java.io.File

fun main(args: Array<String>) {
    val parser = ArgParser("mygrep")

    val pattern by parser.argument(ArgType.String, description = "Hiernach wird gesucht")
    val path by parser.argument(ArgType.String, description = "Pfad zu gesuchter Datei/Verzeichnis")

    val ignore by parser.option(
        ArgType.Boolean,
        shortName = "i",
        description = "Groß- und Kleinschreibung bei der Suche ignorieren"
    ).default(false)

    val numbers by parser.option(
        ArgType.Boolean,
        shortName = "n",
        description = "Zeilennummern in der Ausgabe mit ausgeben"
    ).default(false)

    val invert by parser.option(
        ArgType.Boolean,
        shortName = "v",
        description = "invertiere Suche, d.h. es werden alle Zeilen ausgegeben, die NICHT auf das Suchmuster passen"
    ).default(false)

    parser.parse(args)

    println("Suche nach $pattern in $path")

    dateisuche(path, pattern, ignore, invert, numbers)
}

fun dateisuche(path: String, pattern: String, ignore: Boolean, invert: Boolean, numbers: Boolean) {
    val ziel = File(path)

    if (!ziel.exists()) {
        println("Datei/Verzeichnis $path nicht gefunden.")
        return
    } else if (ziel.isDirectory) {
        val dateien = ziel.listFiles()?.filter { it.isFile }
        dateien?.forEach { datei ->
            datenverarbeitung(datei, pattern, ignore, invert, numbers, true)
        }
    } else {
        datenverarbeitung(ziel, pattern, ignore, invert, numbers, false)
    }
}

fun datenverarbeitung (datei: File, pattern: String, ignore: Boolean, invert: Boolean, numbers: Boolean, dateinameanzeigen: Boolean) {
    var aktuelleZeile = 1

    datei.useLines(Charsets.UTF_8) { zeilen ->
        zeilen.forEach { zeile ->
            val enthaeltMuster = zeile.contains(pattern, ignoreCase = ignore)
            val istTreffer = if (invert) !enthaeltMuster else enthaeltMuster
            if (istTreffer) {
                var ausgabe = ""
                if (dateinameanzeigen) {
                    ausgabe += "${datei.name}: "
                }
                if (numbers) {
                    ausgabe += "$aktuelleZeile: "
                }
                ausgabe += zeile
                println(ausgabe)
            }
            aktuelleZeile++
        }
    }
}