# PM
*Natalie Tuomi 2 juni 2023*

---

## Inledning
Mitt projekt går ut på att skapa något nytt, nämligen ett schackspel. Schack har redan definierade regler för spelet och är dessutom perfekt för objektorienterad programmering. Det finns olika typer av schackpjäser, men alla är pjäser. För att knyta in databaser så kan spelet även ha en highscore lista med hjälp av en databas med spelstatistik.

Sammanfattningsvis ska projektet:

- [X] Ha ett "schackbräde" med 8x8 rutor och 32 schackpjäser av olika slag.
- [X] Ha ett MVC upplägg.
- [X] Ha ett pixelbaserat GUI styrt med tangentbordet.
- [ ] Kunna spelas som ett vanligt schackspel med två spelare med reservation för att spelet inte är över förrän en kung är tagen.
- [ ] Ska föra in varje omgångs statistik i en databas.

---

## Bakgrund

Arbetet började med [planering.](/Dokumentation/planering.md) Provisoriska klassdiagram, skisser och databasritning skapades för att få en så bra överblick som möjligt. 

Själva programmeringen började med den objektorienterade biten. Klasserna som skulle användas för schackbrädet och pjäserna skapades först. Sedan när det fanns en grund för dem så gick jag över till MVC upplägget och själva spelet. Det inkluderade att metoden kan kolla om spelet är över, ge möjliga drag och liknande samtidigt som view behövde kunna rita upp spelplanen och alla pjäser samt reagera på tangenttryck. Controllern var sista steget för att försöka få ihop spelet.

---

## Bra
Det ser ut som ett schack. Du kan inte sätta igång programmet och missa vad poängen är. Dessutom har det gått förvånande bra med det interaktiva. Färgade symboler visar var spelaren är, pjäsen som redan tryckts och även möjliga drag. 
Även klasserna har blivit uppbyggda på ett sätt som jag är nöjd med. De här sakerna kunde jag lägga mer tid på eftersom det var där jag började och en genomtänkt planering var givetvis en stor hjälp. Jag har fått bli bättre på att felsöka eftersom när jag har fått fel så har jag ungefär 10 filer och en hel massa kod att genomsöka.

---

## Mindre bra
Tid- och energibrist får konsekvenser. Jag glömde bort databasinslaget framtills det var för sent för att hinna få till det. Jag har delar av dess uppbyggnad i koden, men den blir inte en del av programmet eftersom den inte är fullständig eller slutförd. 
Jag har dessutom fått ett problem som gör det omöjligt att göra ett drag. I viewn finns det en variabel (chosen) som ska vara koordinaterna för pjäsen du vill flytta. Problemet är att någonstans mellan view.setOptions() och view.getChosen() ändras koordinaterna till dem pjäsen ska flytta till istället för ifrån. Det gör det omöjligt att fullfölja ett drag. 
Om det problemet löstes borde spelet vara fullt spelbart, men jag kan för allt i världen inte hitta vad som har blivit fel. Jag har tittat på varenda ställe i viewn där chosen nämns, men inget av det borde förändra variabeln. Jag har stirrat mig blind på koden i ungefär 8 h under loppet av 2 dagar.


---

## Sammanfattning
De delarna jag hann med är jag väldigt nöjd med. Det är surt att inte kunnat fullfölja planen, men man lever och man lär. Det går alltid att fortsätta på det som hobbyprojekt om man får tid över i sommar. Jag hade gillat att få spelet spelbart och databasinsättningen i ordning. Det hade även varit kul att arbeta med schack/schackmatt och att en bonde blir en drottning om den når änden av brädet.