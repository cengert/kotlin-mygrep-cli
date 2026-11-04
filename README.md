# Portfolio 1: MyGrep CLI-Tool

Dieses Projekt ist eine Kotlin-basierte Implementierung eines Kommandozeilenwerkzeugs zur Textsuche, inspiriert durch 
das GNU-Tool `grep`. Es wurde im Rahmen des Moduls Programmieren 2 entwickelt.

## Funktionsumfang

Das Programm durchsucht Dateien oder Verzeichnisse nach einem bestimmten Suchmuster. Folgende Stufen wurden umgesetzt:

* **Stufe 1:** Suche in einer einzelnen Datei.
* **Stufe 2:** Unterstützung von Suchparametern:
    * `-i`: Groß- und Kleinschreibung bei der Suche ignorieren.
    * `-n`: Zeilennummern in der Ausgabe mit ausgeben.
    * `-v`: Invertierte Suche, d.h. es werden alle Zeilen ausgegeben, die nicht auf das Suchmuster passen.
    * Die Optionen dürfen auch kombiniert werden
* **Stufe 3:** Suche in Verzeichnissen (nicht rekursiv).

## Voraussetzungen

* **Java JDK 21**
* **Gradle** (über den enthaltenen Wrapper `./gradlew`)

## Anleitung zum Ausführen

Die Ausführung erfolgt über den Gradle-Wrapper. Die Argumente für das Programm müssen innerhalb von einfachen 
Anführungszeichen an den `--args` Parameter übergeben werden.

### Grundsyntax
`./gradlew run --args='[Optionen] Suchmuster Pfad'`

### Beispiele
1. **Einfache Suche:**
   `./gradlew run --args='Christopher ./tests/test1.txt'`
2. **Suche im Verzeichnis (Case-Insensitive):**
   `./gradlew run --args='-i Kotlin ./tests'`
3. **Suche mit allen Flags im Verzeichnis:**
   `./gradlew run --args='-i -n -v "ich programmiere" ./tests'`

*(Hinweis: Anführungszeichen um das Suchmuster sind nur bei Leerzeichen im Muster zwingend erforderlich.)*

## Projektstruktur und Design-Entscheidungen

* **Modularisierung:** Der Code ist logisch in Funktionen unterteilt:
    * `main`: CLI-Parsing und Argument-Verarbeitung.
    * `dateisuche`: Logik zur Unterscheidung zwischen Datei und Verzeichnis sowie Validierung der Pfade.
    * `datenverarbeitung`: Kernlogik der Textanalyse und formatierte Ausgabe der Treffer.
* **Effizienz:** Die Datei wird mittels `useLines` gestreamt. Dadurch werden Dateien zeilenweise verarbeitet, wodurch auch größere 
Dateien verarbeitet werden können, ohne komplett in den Speicher geladen zu werden.
* **Standardbibliothek:** Für die Suche wird die Kotlin-Standard-Funktion `contains(..., ignoreCase = true)` verwendet, was den Code besser lesbar macht.
* **Zeichenkodierung:** Die Verarbeitung erfolgt explizit im UTF-8-Format (`Charsets.UTF_8`), um Plattformunabhängigkeit und Korrektheit bei Sonderzeichen zu gewährleisten.
* **Fehlerbehandlung:** Das Programm validiert die Existenz von Pfaden vor dem Zugriff und gibt bei ungültigen Argumenten oder fehlenden Dateien benutzerfreundliche Fehlermeldungen aus, anstatt unkontrolliert abzustürzen.

## Test-Dokumentation

Das Programm wurde unter Ubuntu 24.04 erfolgreich mit folgenden Szenarien getestet, die zugehörigen Dateien sind im Ordner "tests" zu finden:

| Testfall               | Befehl-Argumente                         | Erwartetes Ergebnis                                          |
|:-----------------------|:-----------------------------------------|:-------------------------------------------------------------|
| Existenz-Check         | `... --args='Muster ./tests/test27.txt'` | Fehlermeldung: "Datei/Verzeichnis nicht gefunden."           |
| Verzeichnis-Suche      | `... --args='test ./tests'`              | Alle Treffer werden mit vorangestelltem Dateinamen gelistet. |
| Case-Insensitive       | `... --args='-i test ./tests/test3.txt'` | Findet "Test", "TEST" und "test", also alle Zeilen.          |
| Invertierte Suche      | `... --args='-v test ./tests/test2.txt'` | Alle Zeilen ohne "test" werden ausgegeben.                   |
| Zeilennummern anzeigen | `... --args='-n test ./tests/test2.txt'` | Zeile 1 und 5 werden mit Zeilennummer ausgegeben             |

---
*Struktur der Readme erstellt mit Google Gemini. Angepasst von Christopher Engert für das Portfolio 1 im Modul Programmieren 2.*