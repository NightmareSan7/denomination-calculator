Testaufgabe für Stückelungsberechnung:

# 1. Funktionale Anforderungen

* Frontend
    * Visuals
        * Button zur Umschaltung der Berechnungslogik
        * Eingabemöglichkeit für den zu stückelnden Betrag
        * Anzeige in Tabellenform für Anzahl der Stückelungen
        * Anzeige in Tabellenform für Anzahl der Stückelungen als Differenz zum vorher verarbeiteten Betrag
* Backend
    * REST Controller für POST Request sowie dazugehöriges Datenmodell
        * Feste Liste für verfügbare Scheine & Münzen in €
            * 200
            * 100
            * 50
            * 20
            * 10
            * 5
            * 2
            * 1
            * 0.5
            * 0.2
            * 0.1
            * 0.05
            * 0.02
            * 0.01
        * DTO für Request
        * DTO für Response
* Berechnungslogik
    * Soll die größtmöglichen Stückelungen zuerst verwenden, damit möglichst wenige Scheine und Münzen benötigt werden
    * Muss wahlweise im Frontend oder im Backend durchgeführt werden
    * Berechnung für die benötigte Anzahl der Stückelungen um einen Betrag abzubilden
    * Berechnung für die Differenz der benötigten Stückelungen im Vergleich zum zuvor verarbeiteten Betrag

# 2. Annahmen

* Keine zusätzlichen Sicherheitsanforderungen für Authentifizierung und API Anfragen notwendig
* Frontend dient nur zur einfachen Anzeige und zur Logikverarbeitung, keine Besonderheiten wie z.B. UI resizing
  notwendig
* Keine Swagger/OpenAPI Dokumentation notwendig
* Keine zusätzliche technische Dokumentation notwendig
* Keine persistente Datenspeicherung notwendig
* Keine fachliche Obergrenze für den Betrag notwendig
* Keine CORS-Definition notwendig (Vite-Proxy verwendet)
* Keine Trennung von Logs notwendig (logback.xml)
* Keine i18n Übersetzung notwendig

# 3. Verwendete Versionen:

* Java SDK 21.0.11
* Language Level 21
* Spring Boot 4.1.0 + Maven
* react 19.2.7
* react-dom 19.2.7
* node 24.16.0
* npm 11.13.0

# 4. API Beispiel:

`POST /api/tooling/denomination/v1`

Request format:

```json
{
  "currentAmount": 4.5,
  "previousAmount": 2
}
```

Response format:

```json
{
  "denominations": [
    {
      "denomination": 2,
      "count": 2
    },
    {
      "denomination": 0.50,
      "count": 1
    }
  ],
  "differences": [
    {
      "denomination": 2,
      "differenceCount": 1
    },
    {
      "denomination": 0.50,
      "differenceCount": 1
    }
  ]
}
```