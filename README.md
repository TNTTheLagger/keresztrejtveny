# Keresztrejtvény Feladat

**Azonosító jel:**  

**40 pont**  

## Keresztrejtvény  

A keresztrejtvény a nyomtatott sajtóban megjelenő egyik legnépszerűbb játéktípus. Hagyományos változatában a rácsba írandó szavak meghatározásait különválasztva vízszintesen és függőlegesen, beszámozva sorolja fel a készítő. A szavakat egymástól fekete négyzetek választják el. A számok a megfejtendő szavak első betűjét jelölik, ahonnan akár vízszintesen, akár függőlegesen indul a megfejtés.

A következő feladatban egy szöveges állományban keresztrejtvényt kódoltunk karakterekkel mátrixszerűen.  

- Egy-egy fekete mezőt a `#` (hashmark) karakter,  
- Az üres mezőket a `-` (kötőjel) karakter jelöli.  

A keresztrejtvény méretét nem ismerjük, de feltételezhető, hogy **maximum 15x15-ös méretű**, és a megfejtendő szavak **legalább 2 karakter hosszúak**.  

## Megoldási követelmények  

- Megoldását elkészítheti saját osztály definiálása nélkül is, de úgy az nem lesz teljes értékű.
- A képernyőre írást igénylő feladatok előtt írja ki a feladat sorszámát (pl.: `5. feladat`).
- A kiírásokat a minta szerint készítse el.
- Az ékezetmentes kiírások is elfogadhatók.
- Az azonosítókat kis- és nagybetűkkel is kezdheti.
- Nem kell ellenőriznie az állományok szerkezetét, feltételezheti a helyességet.
- A megoldás működjön más, hasonló szerkezetű bemeneti adatokkal is.

## Konzolos alkalmazás  

**Projekt neve:** `Keresztrejtveny`  

### KeresztrejtvenyRacs osztály  

Hozzon létre egy `KeresztrejtvenyRacs` nevű osztályt!  

**Konstruktor feladatai:**  

1. **Beolvasás:**  
   - A konstruktor kapja paraméterként a forrásállomány nevét.  
   - Hívja meg a `BeolvasAdatsorok()` metódust, amely betölti az `Adatsorok` listába a fájl tartalmát.  
   - A fájl neve `kr1.txt` vagy `kr2.txt` lehet.  

2. **Mátrixok inicializálása:**  
   - Határozza meg a rács méretét `SorokDb` és `OszlopokDb` segítségével.  
   - Inicializálja a `Racs` és `Sorszamok` mátrixokat.  
   - Célszerű a mátrixokat **két sorral és két oszloppal nagyobbra méretezni**.  

3. **Rács feltöltése:**  
   - Hívja meg a `FeltoltRacs()` metódust.  
   - A `Racs` mátrixba töltse be a `#` és `-` karaktereket az `Adatsorok` alapján.  

**Főprogram feladatai:**  

- Hozzon létre egy `KeresztrejtvenyRacs` példányt (`kr1.txt` vagy `kr2.txt` fájlból).  
- Írja ki a keresztrejtvény méreteit.  
- Jelenítse meg a beolvasott rácsot:  
  - Üres mezőket: `[]`  
  - Fekete mezőket: `##`  
- Határozza meg és írassa ki a legtöbb karakterből álló **függőleges szó hosszát**.  
- Készítsen statisztikát a **vízszintes szavak hosszáról** (növekvő sorrendben).  
- Számozza meg azokat a mezőket, ahol szó kezdődik.  
  - Ha egy mezőben vízszintes és függőleges szó is kezdődik, akkor is **csak egy számot kap**.  
- Kódolja a számozásokat a `Sorszamok` mátrixban és jelenítse meg.  

---

## Grafikus alkalmazás  

**Projekt neve:** `KeresztrejtvenyGUI`  

### Felhasználói felület  

- Az ablak címe: **„Keresztrejtvény készítő”**  
- A mátrix mérete legördülő listával választható (6-15 között).  
- Az alapértelmezett érték **15** legyen.  
- A „Keresztrejtvény mentése” melletti legördülő listában az állománynév indexe **1-10 között** állítható (alapértelmezett: **3**).  

### Funkcionalitás  

1. **„Keresztrejtvény létrehozása” gomb**  
   - Beviteli mezők létrehozása mátrixszerűen.  
   - Alapértelmezetten minden mező `-` karaktert tartalmaz.  
   - Ha már volt korábbi mátrix, az új létrehozása előtt törlődjön.  

2. **Dupla kattintás egy mezőre**  
   - `-` → `#`  
   - `#` → `-`  
   - Csak **egy karakter hosszú adat** kerülhessen a mezőbe.  

3. **„Keresztrejtvény mentése” gomb**  
   - A program létrehoz egy szöveges fájlt.  
   - A fájl neve: `kr{index}.txt`, ahol az indexet a legördülő listából lehet választani.  
   - Ha a mentés sikeres, megjelenik egy üzenet.  
   - Hiba esetén felugró ablakban jelenjen meg a hiba szövege.  

---

## Vizsgafeladat információk  

**Informatikai ismeretek E2321**  
- **Emelt szintű gyakorlati vizsga**  
- **Dátum:** 2024. október 18.  
- **Pontszám:** 40 pont  
