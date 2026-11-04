# Reflexion zum Portfolio 1: MyGrep
**Name:** Christopher Engert  
**Modul:** Programmieren 2 (BSED2)

## 1. Einleitung und Zielsetzung
Das Ziel des ersten Portfolios war die Entwicklung eines Kommandozeilenwerkzeugs
(Mygrep) in Kotlin. Dabei standen die Handhabung von Kommandozeilenargumenten,
 die Dateiverarbeitung (I/O) sowie die Implementierung einer flexiblen
Suchlogik im Fokus.

## 2. Methodik und Hilfsmittel
Da das Thema Dateiverarbeitung (Input/Output) zum Zeitpunkt der Bearbeitung noch 
nicht Teil der Vorlesung war, habe ich mir bei der Umsetzung dieser spezifischen 
Programmteile Unterstützung durch eine KI (Gemini) geholt.

Besonders bei der Auswahl der Bibliotheken und der Dateiverarbeitung war die 
Unterstützung hilfreich, da ich mich in diesem Bereich noch nicht sicher 
gefühlt habe. So konnte ich eine funktionierende Lösung umsetzen, obwohl 
das Thema zum Teil in der Vorlesung noch nicht behandelt wurde. 
Die Logik für das CLI-Parsing und die Programmstruktur 
wurden eigenständig mit der Bibliothek `kotlinx-cli` umgesetzt.

## 3. Design-Entscheidungen
### Modularisierung und Struktur
Wie in der Aufgabenstellung gefordert, wurden die Bereiche Argumentverarbeitung, 
Dateizugriff und Suchlogik in separate Funktionen unterteilt. Auf eine Trennung 
in mehrere unterschiedliche Kotlin-Dateien habe ich jedoch bewusst verzichtet. 
Bei einem Programmumfang von etwa 75 Zeilen Code hielt ich eine Aufteilung auf 
mehrere Dateien für nicht zielführend, da dies die Komplexität unnötig erhöht hätte, 
ohne einen Mehrwert an Übersichtlichkeit zu bieten.
### Wahl der Bibliotheken (java.io vs. kotlin.io)
Für die Dateioperationen habe ich die Klasse `java.io.File` verwendet. Da Kotlin auf 
der Java Virtual Machine (JVM) läuft, ist der Zugriff auf die bewährten 
Java-Standardbibliotheken sehr stabil und bietet eine hohe Kompatibilität. 
Dies schien für die Anforderungen des Portfolios der sicherste Weg zu sein, 
um Pfadvalidierungen und Dateiprüfungen (z. B. `isDirectory` oder `exists()`) umzusetzen.

### Zeilenweise Verarbeitung mit `useLines`
Ein wichtiger Aspekt war für mich die Handhabung größerer Dateien, da ich zunächst 
nicht sicher war, wie diese effizient verarbeitet werden können. 
Anstatt die gesamte Datei auf einmal in den Arbeitsspeicher 
zu laden (z. B. via `readLines()`), habe ich 
mich auf Empfehlung der KI für `useLines` entschieden.
* **Speicherschonend:** Die Datei wird Zeile für Zeile gelesen. Das verhindert 
Abstürze bei sehr großen Textdokumenten.
* **Ressourcensicherheit:** `useLines` stellt sicher, dass die 
Datei nach der Verarbeitung automatisch wieder geschlossen wird, was Systemfehler minimiert.

### Nutzung der Standardbibliothek
Für die Suchfunktionalität wurde die Funktion `contains(..., ignoreCase = true)` aus 
der Kotlin-Standardbibliothek genutzt. Dies ist wesentlich lesbarer als eine manuelle Umwandlung 
aller Strings in Kleinschreibung und deckt die Anforderungen der Stufe 2 (Parameter `-i`) optimal ab.

## 4. Herausforderungen und Lösungen
Die größte Schwierigkeit lag für mich in der korrekten Übergabe der Argumente über das Terminal.
Da das Programm über Gradle gestartet wird, müssen die Parameter in einfachen 
Anführungszeichen an `--args` übergeben werden.
Dabei hatte ich anfangs Probleme mit der richtigen Verwendung von Anführungszeichen, insbesondere bei 
Suchmustern mit Leerzeichen. Erst durch mehrere Tests konnte ich nachvollziehen, wie die 
Argumente korrekt übergeben werden. 

Auch die Logik der invertierten Suche (`-v`) in Kombination mit der Verzeichnissuche 
hat mich dazu gebracht, die Logik klarer zu strukturieren, damit der Code übersichtlich bleibt.
## 5. Fazit
Die Arbeit am Portfolio 1 hat mir gezeigt, wie man sich neue Themengebiete (wie I/O) 
mithilfe von KI-Tools erschließen kann, und dabei trotzdem eine nachvollziehbare und 
saubere Struktur im Code beizubehalten. Die Aufteilung 
in spezialisierte Funktionen für die Dateisuche und die 
Datenverarbeitung macht den Code gut nachvollziehbar und übersichtlich.

---
*Erstellt am 24. April 2026, Hilfe durch KI Gemini bei Erstellung der Grundstruktur des Textes und Formulierungshilfen*