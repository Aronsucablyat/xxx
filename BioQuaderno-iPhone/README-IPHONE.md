# BioQuaderno per iPhone — guida

Questa è la versione **iPhone** di BioQuaderno, identica a quella Android: stessa
interfaccia, stesse materie, Prof offline, appunti dalle foto (OCR), libri digitali
con citazione delle pagine. Il codice dell'interfaccia è **condiviso** tra le due
versioni (Kotlin Multiplatform + Compose Multiplatform), così le due app restano
sempre uguali.

> ⚠️ **Importante:** iOS non si può compilare su Windows. Il PC Windows non ha gli
> strumenti Apple (Xcode). Perciò l'app iPhone si costruisce **sul cloud (GitHub)**
> oppure su un Mac. Qui sotto ci sono tutti i passaggi.

---

## 1. Novità di questa versione (v1.5)

- ✅ **Estrazione testo molto migliorata**: OCR (foto) e PDF ora ricostruiscono il
  testo nell'**ordine di lettura vero** (alto→basso, sinistra→destra) usando le
  posizioni delle righe. Prima le parole uscivano "incasinate" su pagine a più
  colonne, titoli o riquadri.
- ✅ **Niente più sovrapposizioni**: la sezione "+ Foto" e la sezione "Libri" non si
  sovrappongono più (se ne apre una sola alla volta, su sfondo solido).
- ✅ **Tantissime impostazioni di aspetto** (oltre 20 opzioni, sezione
  "Personalizza l'app"): 16 colori, chiaro/scuro/auto, colori vivaci, contrasto
  alto, sfondo (aurora/gradiente/tinta/nessuno) con intensità, angoli (4 stili),
  ombra e bordo schede, dimensione testo (5 livelli), carattere, peso, spaziatura
  lettere e interlinea, spazi nelle schede, barra in basso (icona+testo/solo
  icone/solo testo), tocchi grandi, animazioni e velocità, passaggio tra schede,
  Liquid Glass, riduci trasparenza e scheda iniziale.
- ✅ **Mandare le foto al Prof**: nel tab "Prof" puoi allegare fino a 4 foto dei tuoi
  appunti; il testo viene letto **in locale** (offline) e inviato come domanda.

---

## 2. Come avere l'app sull'iPhone (metodo consigliato, dal PC Windows)

Serve solo un **Apple ID** (anche gratuito) e il programma **Sideloadly**.

1. **Carica il progetto su GitHub** (anche un repository privato):
   - crea un account su github.com se non ce l'hai;
   - crea un nuovo repository (es. `bioquaderno`);
   - carica **il contenuto della cartella `BioQuaderno-iOS`** (non la cartella stessa,
     ma ciò che c'è dentro: `gradle/`, `composeApp/`, `iosApp/`, `.github/`, ecc.).

2. **Fai costruire l'app dal cloud:**
   - nel repository vai su **Actions** → **"Build app iPhone (IPA non firmato)"**;
   - premi **Run workflow**;
   - attendi ~10-15 minuti;
   - quando è verde, apri la build e scarica l'artefatto **`BioQuaderno-ipa`**
     (contiene `BioQuaderno-unsigned.ipa`).

3. **Installa sull'iPhone con Sideloadly (dal PC):**
   - scarica **Sideloadly** da <https://sideloadly.io> e installalo sul PC;
   - collega l'iPhone al PC con il cavo e sbloccalo (fidati del computer);
   - apri Sideloadly, trascina dentro il file `BioQuaderno-unsigned.ipa`;
   - scrivi il tuo **Apple ID** e premi **Start**;
   - al primo avvio l'iPhone chiede di **fidarsi** dello sviluppatore:
     *Impostazioni → Generali → VPN e gestione dispositivo → fidati del tuo Apple ID*.

Fatto: BioQuaderno è sul tuo iPhone. 🎉

> **Nota sui tempi:** con un **Apple ID gratuito** l'app resta installata **7 giorni**,
> poi va reinstallata con Sideloadly (basta rifare il punto 3, l'IPA è già pronto).
> Con un **Apple Developer Program** (a pagamento, ~99 €/anno) dura **1 anno** e non
> serve ripetere. Non è possibile bypassare questa regola di Apple.

---

## 3. Come compilare su un Mac (se ne hai uno)

Serve **Xcode** e, se non è già presente, **XcodeGen**.

```bash
# nella cartella BioQuaderno-iOS
brew install xcodegen           # una volta sola
cd iosApp && xcodegen generate  # crea BioQuaderno.xcodeproj
open BioQuaderno.xcodeproj
```

Poi in Xcode: scegli il team di sviluppo (il tuo Apple ID), collega l'iPhone e premi
Run. Il progetto richiama automaticamente Gradle per costruire il framework Kotlin.

---

## 4. Struttura del progetto

```
BioQuaderno-iOS/
├── composeApp/
│   ├── src/commonMain/   ← interfaccia e logica condivise (Android + iPhone)
│   ├── src/androidMain/  ← versione Android (SharedPreferences, ML Kit, WebView)
│   └── src/iosMain/      ← versione iPhone (NSUserDefaults, Vision, WKWebView, PDFKit)
├── iosApp/               ← "guscio" Xcode (SwiftUI) che mostra l'interfaccia Compose
│   ├── BioQuaderno/      ← iOSApp.swift, ContentView.swift, Assets
│   └── project.yml       ← descrizione del progetto per XcodeGen
├── .github/workflows/    ← la build automatica su GitHub
└── gradlew / gradlew.bat ← Gradle wrapper
```

---

## 5. Domande frequenti

**Le parole del testo estratto sono ancora sbagliate?**
La lettura ora segue l'ordine di lettura. Per risultati migliori: fotografa dritto,
con buona luce, pagina intera e non di sbieco.

**Un PDF non si legge ("protetto da DRM")?**
I PDF dei libri di testo con protezione DRM **non** possono essere letti da nessuna
app esterna (è una protezione voluta dall'editore). Usa il PDF senza protezione o la
foto degli appunti.

**Serve internet?**
No, per studiare, OCR e Prof offline non serve internet. Internet serve solo se
attivi il Prof con l'API Gemini e per la finestra web dei libri online.

---

## 6. Versione Android

La stessa identica versione per Android è nella cartella `BioStudio`
(file APK: `BioQuaderno-v1.6-debug.apk`). Le due versioni condividono lo stesso
codice dell'interfaccia, quindi funzionano allo stesso modo.
