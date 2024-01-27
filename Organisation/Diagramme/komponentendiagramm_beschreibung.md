# Beschreibung des Komponentendiagramm


Die folgende Beschreibung bezieht sich auf  [das Komponentendiagramm](komponentendiagramm.png) der Wetter-WebApp  welche im Rahmen des PS Softwarearchitektur erstellt wurde. Mehr Infos und die Dokumentation findest du im [README](/README.md).

## Level 1: Systemübersicht
Diese Übersicht der Komponenten dient einer groben Aufteilung des Systems und orientiert sich stark an der verwendeten 3-Schichten-Architektur. Zudem wird auf externe Schnittstellen hingewiesen.

### Presentation 
- Umfasst UI-relevante Logik (Controller und UI-Beans)
- Konkrete Umsetzung mittels dieser UI Frameworks: Java Server Faces, Primefaces
- Diese Blackbox wird mangels Relevanz im folgenden Level nicht genauer erläutert

### Model
- Beinhaltet das Domainmodell
- Entitäten dieses Modells werden mittels einer externen Schnittstelle zur Datenbank persistiert

### Service
- Umfasst Kernfunktionalitäten des Systems
- Da dies der relevanteste Teil für die Logik des Systems ist, wird die Komponente im Level 2 genauer modelliert

### Weather API (externe Schnittstelle)
- Schnittstelle zu verschiedenen API von openweathermap.org
- Alle Wetterdaten werden über diese Schnittstelle abgerufen

### Database (externe Schnittstelle)
- Verbindung zu Datenbankserver zum Speichern und Laden von Daten
- Verwendet wird eine H2 In-Memory Datenbank

### E-Mail Server (externe Schnittstelle)
- Verbindung zum E-Mail Server zum Senden von Nachrichten

## Level 2: Modellierung der Service Komponente
Im zweiten Level dieses Komponentendiagramm fokussieren wir uns auf die Service Komponente, da diese den Großteil der relevanten Business-Logik enthält.

Die Service Komponente wird zudem in zwei Unterkomponenten External und Internal unterteilt. Ersteres umfasst alle Komponenten, welche logisch gesehen mit externen Datenquellen (Wetter API) zusammenhängen. Die Unterkomponente Internal umfasst den Rest, wobei man die E-Mail Komponente sicherlich auch dem External Bereich zuordnen könnte.
<br>
<br>

### External

### GeocodingService
- Aufruf der Geocoding API, welche die jeweiligen Koordinaten eines Ortes anführt

### WeatherService
- Aufruf der Wetter APIs (Aktuelles Wetter, Vorschau, Langzeitprognose)

### AutoCompletion
- Umfasst die Logik für die Autovervollständigung bei Sucheingaben des Users.
- Ebenfalls mittels einer API realisiert

### VacationService
- Logik für die Auswahl eines Urlaubsdatums und Anzeige der Daten
- Benützt WeatherService um an die erforderlichen WetterDaten zu kommen
<br>
<br>

### Internal


### UserManagement
- umfasst Userverwaltung (Erstellen, Löschen, ...)
- verwendet für diese Funktionalitäten einige anderen Komponenten im Bereich Internal

### PaymentService
- verwaltet das Premium-System und die Abwicklung der dazugehörigen Zahlungen mittels Kreditkarten
- dafür werden teils noch weitere Komponenten genutzt (z.B. Logging für Änderungen des Premium-Status) auf welche aber mangels Relevanz nicht näher eingegangen wird

### CreditCardService
- Verwaltung der Entität CreditCard
- Verwaltet zudem fiktive Geldbeträge der jeweiligen Karten

### LocationService
- Verwaltet Orte welche von einem User als Favoriten gespeichert wurden
- Umfasst Sortierung und Konfiguration der Anzeige

### VerificationService
- Verifizierung vie E-Mail nach einer User-Registrierung
- Abwicklung des Passwort-Zurücksetzen Prozess
- um die Sicherheit zu gewährleisten wird der Token Service genutzt und folglich mittels Email Service mit dem User kommuniziert

### TokenService
- Verwaltet Tokens welche zu Verifizerungen diverser Arten verwendet werden können

### EmailService
- Verbinden zur externen Schnittstelle zum E-Mail Server
- bietet nach innen Schnittstelle zum versenden von einfachen E-Mails an
