package com.brahma.bioquaderno.data

/**
 * Materie del BIENNIO COMUNE (tutti gli indirizzi degli istituti tecnici).
 * Contenuto compatto ma reale: teoria, glossario, flashcard e quiz.
 */
fun biennioSubjects(): List<Subject> = listOf(
    // =========================================================================
    // ITALIANO
    // =========================================================================
    Subject(
        id = "italiano", name = "Italiano", emoji = "📖", indirizzo = "Biennio comune", colorHex = 0xFF455A64,
        chapters = listOf(
            Chapter(0, "Il testo: leggere e comprendere", "📖",
                "Come si analizza un testo: scopo, tipologie, parafrasi e struttura.",
                listOf(
                    Section("Tipi di testo e scopo comunicativo", listOf(
                        Block.Text("Ogni testo ha uno scopo: informare (espositivo), convincere (persuasivo), raccontare (narrativo), descrivere (descrittivo)."),
                        Block.Text("Prima di analizzare un testo bisogna chiedersi: di cosa parla? Chi lo scrive? A chi è rivolto? Con quale scopo?"),
                        Block.Key("Lo scopo del testo guida la scelta di parole, registro e struttura."),
                    )),
                    Section("La parafrasi", listOf(
                        Block.Text("La parafrasi è la riscrittura di un testo con parole più semplici, senza cambiare il significato."),
                        Block.Text("Per parafrasare: lessico difficile → sinonimi, ordine delle frasi → più naturale, figure retoriche → spiegate con parole proprie."),
                        Block.Bullet("Mantieni il contenuto: non aggiungere idee tue né informazioni nuove."),
                        Block.Bullet("Mantieni la lunghezza proporzionata al testo di partenza."),
                        Block.Key("Parafrasi ≠ riassunto: la prima riscrive quasi parola per parola, il secondo riduce il testo."),
                    )),
                )),
            Chapter(0, "Elementi di poesia", "🖋️",
                "Metrica, rima e le principali figure retoriche per leggere una poesia.",
                listOf(
                    Section("Verso, strofa e metro", listOf(
                        Block.Text("La poesia si scrive in versi, raggruppati in strofe. Il verso è l'unità di base, definita dal numero di sillabe."),
                        Block.Text("La metrica studia il ritmo del verso; i versi più comuni nella poesia italiana sono l'endecasillabo (11 sillabe) e il settenario (7)."),
                        Block.Key("Il nome del verso dipende dal numero di sillabe: endecasillabo = 11."),
                    )),
                    Section("Le figure retoriche", listOf(
                        Block.Text("Le figure retoriche sono artifici del linguaggio che rendono il testo espressivo."),
                        Block.Bullet("Similitudine: paragone esplicito («come un leone»)."),
                        Block.Bullet("Metafora: paragone senza «come» («Sei un leone»)."),
                        Block.Bullet("Personificazione: si danno qualità umane a cose o animali."),
                        Block.Bullet("Anastrofe, iperbole (esagerazione), ossimoro (accostamento di contrari)."),
                        Block.Key("Metafora e similitudine esprimono lo stesso paragone: con o senza il «come»."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Parafrasi", "Riscrittura di un testo con parole proprie e più semplici, senza modificarne il significato."),
            GlossaryTerm("Riassunto", "Riduzione di un testo alle sole informazioni essenziali."),
            GlossaryTerm("Endecasillabo", "Verso di undici sillabe, il più usato nella poesia italiana."),
            GlossaryTerm("Strofa", "Gruppo di versi che formano un'unità ritmica separata dalle altre da uno spazio."),
            GlossaryTerm("Metafora", "Figura retorica che sostituisce un termine con un altro per somiglianza, senza «come»."),
            GlossaryTerm("Favola", "Racconto breve con animali parlanti che porta un insegnamento (morale)."),
        ),
        flashcards = listOf(
            Flashcard("Il testo", "Che cos'è la parafrasi?", "La riscrittura del testo con parole più semplici, senza cambiare il significato."),
            Flashcard("Il testo", "Differenza tra parafrasi e riassunto?", "La parafrasi riscrive senza ridurre; il riassunto riduce alle informazioni essenziali."),
            Flashcard("Poesia", "Come si chiama il verso di 11 sillabe?", "Endecasillabo."),
            Flashcard("Poesia", "Che cos'è una strofa?", "Un gruppo di versi uniti dal ritmo, separati dagli altri da uno spazio."),
            Flashcard("Figure retoriche", "«Sei un leone» è una metafora o una similitudine?", "Metafora: manca il «come»."),
            Flashcard("Testi", "Quali sono i quattro scopi principali di un testo?", "Informare, convincere, raccontare, descrivere."),
        ),
        questions = listOf(
            Question("Il testo", "La parafrasi di un testo deve:",
                listOf("Ridurre il numero di parole", "Riscrivere con parole più semplici senza cambiare il senso", "Aggiungere le tue opinioni", "Tradurre in un'altra lingua"), 1,
                "La parafrasi riscrive mantenendo il significato originale."),
            Question("Il testo", "Che cos'è il riassunto?",
                listOf("Riscrivere tutto il testo", "Ridurre il testo alle informazioni essenziali", "Spiegare le figure retoriche", "Contare le parole"), 1,
                "Il riassunto seleziona solo ciò che è indispensabile."),
            Question("Poesia", "Quante sillabe ha un endecasillabo?",
                listOf("7", "10", "11", "13"), 2,
                "Endeca = undici: undici sillabe."),
            Question("Figure retoriche", "«Le onde danzavano» è un esempio di:",
                listOf("Metafora", "Personificazione", "Iperbole", "Similitudine"), 1,
                "Si danno qualità umane (danzare) alle onde."),
            Question("Figure retoriche", "La similitudine rispetto alla metafora:",
                listOf("Usa il «come»", "Non usa il «come»", "Esagera sempre", "È un tipo di rima"), 0,
                "La similitudine introduce il paragone con «come»."),
            Question("Testi", "Un articolo che vuole farti comprare un prodotto ha scopo:",
                listOf("Informativo", "Narrativo", "Persuasivo", "Descrittivo"), 2,
                "Vuole convincerti: scopo persuasivo."),
        ),
    ),

    // =========================================================================
    // STORIA
    // =========================================================================
    Subject(
        id = "storia", name = "Storia", emoji = "🏛️", indirizzo = "Biennio comune", colorHex = 0xFF8D6E63,
        chapters = listOf(
            Chapter(0, "Il Medioevo", "🏰",
                "Dal feudalesimo alle autonomie comunali: come cambia il potere in Europa.",
                listOf(
                    Section("La società feudale", listOf(
                        Block.Text("Dopo il crollo dell'Impero romano, in Europa si diffonde il feudalesimo: il re concede terre (feudi) ai signori in cambio di fedeltà e aiuto militare."),
                        Block.Text("La società è rigida e divisa in tre ordini: chi prega (clero), chi combatte (nobili), chi lavora (contadini e artigiani)."),
                        Block.Key("Il feudo nasce da un rapporto di vassallaggio: terra in cambio di fedeltà e servizio militare."),
                    )),
                    Section("Comuni e Signorie", listOf(
                        Block.Text("Dal XI secolo, nelle città italiane crescono i Comuni: liberi governi cittadini che amministrano giustizia, commerci e difesa."),
                        Block.Text("I contrasti interni portano alla Signoria: un signore potente prende il controllo del Comune (es. i Medici a Firenze)."),
                        Block.Bullet("Comune: governo autonomo della città retto dai cittadini."),
                        Block.Bullet("Signoria: governo di un solo signore che eredita il potere."),
                    )),
                )),
            Chapter(0, "L'età moderna", "🗺️",
                "Rinascimento, scoperte geografiche e la nascita del mondo moderno.",
                listOf(
                    Section("Umanesimo e Rinascimento", listOf(
                        Block.Text("L'Umanesimo rimette al centro l'uomo e i valori classici; il Rinascimento è la sua manifestazione artistica e culturale (Leonardo, Michelangelo, Raffaello)."),
                        Block.Text("Grazie alla stampa (Gutenberg, metà 400) la cultura si diffonde molto più in fretta."),
                        Block.Key("L'Umanesimo segna il passaggio dalla mentalità medievale (Dio al centro) a quella moderna (l'uomo al centro)."),
                    )),
                    Section("Scoperte geografiche", listOf(
                        Block.Text("Nel 1492 Cristoforo Colombo raggiunge l'America; altri esploratori (Vasco da Gama, Magellano) aprono nuove rotte."),
                        Block.Text("Le scoperte trasformano l'economia: nuovi prodotti (mais, cacao, patata), oro e argento, e la nascita delle grandi potenze coloniali."),
                        Block.Bullet("1492: Colombo approda in America."),
                        Block.Bullet("1498: Vasco da Gama raggiunge le Indie via mare."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Feudalesimo", "Sistema politico-economico basato sul feudo: terra concessa in cambio di fedeltà e servizio."),
            GlossaryTerm("Vassallaggio", "Rapporto di fedeltà tra un signore e un vassallo, sancito con il giuramento."),
            GlossaryTerm("Comune", "Città medievale autogovernata dai suoi cittadini."),
            GlossaryTerm("Signoria", "Forma di governo in cui una sola famiglia prende il controllo di una città."),
            GlossaryTerm("Umanesimo", "Movimento culturale che mette al centro l'uomo e la cultura classica."),
            GlossaryTerm("1492", "Anno in cui Colombo raggiunge il continente americano."),
        ),
        flashcards = listOf(
            Flashcard("Medioevo", "Su che cosa si basa il feudalesimo?", "Sul feudo: terra concessa in cambio di fedeltà e aiuto militare."),
            Flashcard("Medioevo", "Quali sono i tre ordini della società feudale?", "Chi prega (clero), chi combatte (nobili), chi lavora (contadini)."),
            Flashcard("Medioevo", "Differenza tra Comune e Signoria?", "Il Comune è un governo cittadino autonomo; la Signoria concentra il potere in un solo signore."),
            Flashcard("Età moderna", "Che cos'è l'Umanesimo?", "Movimento culturale che mette al centro l'uomo e i valori classici."),
            Flashcard("Età moderna", "Cosa permette la stampa di Gutenberg?", "La rapida diffusione dei libri e della cultura."),
            Flashcard("Età moderna", "In che anno Colombo raggiunge l'America?", "Nel 1492."),
        ),
        questions = listOf(
            Question("Medioevo", "Nel feudalesimo il vassallo riceve il feudo in cambio di:",
                listOf("Denaro", "Fedeltà e servizio militare", "Terreni da vendere", "Titoli nobiliari"), 1,
                "Il feudo è la ricompensa della fedeltà e del servizio al signore."),
            Question("Medioevo", "I tre ordini della società feudale sono:",
                listOf("Re, principi, cavalieri", "Clero, nobili, contadini e artigiani", "Cittadini, mercanti, schiavi", "Re, chiesa, esercito"), 1,
                "Chi prega, chi combatte e chi lavora."),
            Question("Medioevo", "La Signoria si distingue dal Comune perché:",
                listOf("Il Comune ha un re", "Il potere è di una sola famiglia o signore", "Non ci sono città", "La chiesa governa"), 1,
                "Il signore concentra su di sé il governo della città."),
            Question("Età moderna", "La stampa a caratteri mobili è inventata da:",
                listOf("Leonardo", "Colombo", "Gutenberg", "Magellano"), 2,
                "Johannes Gutenberg, a metà del 1400."),
            Question("Età moderna", "Colombo nel 1492 approda:",
                listOf("In India", "In Giappone", "In America", "In Australia"), 2,
                "Raggiunge il continente americano, scambiandolo per le Indie."),
            Question("Età moderna", "«Dio al centro» era la mentalità del Medioevo; l'Umanesimo mette al centro:",
                listOf("L'uomo", "L'economia", "La guerra", "La natura"), 0,
                "L'uomo e la sua capacità di conoscere e creare."),
        ),
    ),

    // =========================================================================
    // MATEMATICA
    // =========================================================================
    Subject(
        id = "matematica", name = "Matematica", emoji = "➗", indirizzo = "Biennio comune", colorHex = 0xFF1565C0,
        chapters = listOf(
            Chapter(0, "Algebra: numeri ed equazioni", "🔢",
                "Dai monomi e polinomi alle equazioni di primo grado.",
                listOf(
                    Section("Monomi e polinomi", listOf(
                        Block.Text("Un monomio è il prodotto di un numero (coefficiente) e di una parte letterale (es. 3x²y)."),
                        Block.Text("Un polinomio è una somma di monomi. Per ridurre un polinomio si sommano i termini simili (stessa parte letterale)."),
                        Block.Key("Termini simili = stessa parte letterale: si sommano i coefficienti."),
                    )),
                    Section("Equazioni di primo grado", listOf(
                        Block.Text("Un'equazione di primo grado ha forma ax + b = 0. L'obiettivo è isolare l'incognita x."),
                        Block.Bullet("Si applicano le operazioni inverse: aggiungi o togli da entrambi i lati, moltiplica o dividi entrambi i lati."),
                        Block.Bullet("Esempio: 2x + 3 = 7 → 2x = 4 → x = 2."),
                        Block.Key("Principio di equivalenza: ogni operazione va fatta su entrambi i membri dell'equazione."),
                    )),
                )),
            Chapter(0, "Geometria: triangoli e aree", "📐",
                "Il teorema di Pitagora, i triangoli e il calcolo delle aree.",
                listOf(
                    Section("Il triangolo", listOf(
                        Block.Text("Il triangolo ha 3 lati e 3 angoli; la somma degli angoli interni è sempre 180°."),
                        Block.Text("Per tipo: equilatero (3 lati uguali), isoscele (2 uguali), scaleno (tutti diversi); per angoli: acutangolo, rettangolo, ottusangolo."),
                        Block.Key("Somma degli angoli interni di un triangolo = 180°."),
                    )),
                    Section("Pitagora e le aree", listOf(
                        Block.Text("In un triangolo rettangolo, il quadrato costruito sull'ipotenusa è uguale alla somma dei quadrati sui cateti: i² = c₁² + c₂²."),
                        Block.Bullet("Area rettangolo = base × altezza."),
                        Block.Bullet("Area triangolo = (base × altezza) / 2."),
                        Block.Bullet("Area cerchio = π × r²."),
                        Block.Key("Il teorema di Pitagora vale solo per i triangoli rettangoli."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Monomio", "Prodotto tra un numero (coefficiente) e una parte letterale, es. 3x²y."),
            GlossaryTerm("Polinomio", "Somma algebrica di monomi."),
            GlossaryTerm("Equazione", "Uguaglianza con una o più incognite; risolverla significa trovare i valori che la rendono vera."),
            GlossaryTerm("Incognita", "L'incognita in un'equazione è il valore sconosciuto, di solito indicato con x."),
            GlossaryTerm("Ipotenusa", "Lato opposto all'angolo retto, il più lungo del triangolo rettangolo."),
            GlossaryTerm("Teorema di Pitagora", "In un triangolo rettangolo, i² = c₁² + c₂²."),
        ),
        flashcards = listOf(
            Flashcard("Algebra", "Che cos'è un monomio?", "Il prodotto di un numero e di una parte letterale (es. 3x²y)."),
            Flashcard("Algebra", "Quali termini si possono sommare in un polinomio?", "Solo quelli simili, cioè con la stessa parte letterale."),
            Flashcard("Algebra", "Risolvi 2x + 5 = 11.", "2x = 6 → x = 3."),
            Flashcard("Geometria", "Quanti gradi hanno in totale gli angoli interni di un triangolo?", "180°."),
            Flashcard("Geometria", "Formula dell'area di un triangolo?", "(base × altezza) / 2."),
            Flashcard("Geometria", "A cosa serve il teorema di Pitagora?", "A trovare un lato del triangolo rettangolo conoscendo gli altri due."),
        ),
        questions = listOf(
            Question("Algebra", "L'equazione 3x = 12 ha soluzione:",
                listOf("x = 4", "x = 15", "x = 9", "x = 36"), 0,
                "Dividendo entrambi i lati per 3 si ottiene x = 4."),
            Question("Algebra", "Un monomio è:",
                listOf("Una somma di numeri", "Il prodotto di coefficiente e parte letterale", "Un numero primo", "Una disuguaglianza"), 1,
                "Il monomio unisce coefficiente e parte letterale con la moltiplicazione."),
            Question("Geometria", "Un triangolo con i tre lati uguali si chiama:",
                listOf("Isoscele", "Scaleno", "Equilatero", "Rettangolo"), 2,
                "Equilatero: tre lati uguali (e tre angoli di 60°)."),
            Question("Geometria", "L'area del rettangolo si calcola:",
                listOf("base × altezza", "lato × lato", "(base × altezza) / 2", "π × r²"), 0,
                "Area rettangolo = base per altezza."),
            Question("Geometria", "Il teorema di Pitagora vale per:",
                listOf("Ogni triangolo", "Solo i triangoli rettangoli", "Solo i quadrati", "I triangoli equilateri"), 1,
                "Vale solo nei triangoli rettangoli."),
            Question("Algebra", "Qual è la somma di 3x e 5x?",
                listOf("8x", "15x", "8x²", "Non si possono sommare"), 0,
                "Hanno la stessa parte letterale: si sommano i coefficienti → 8x."),
        ),
    ),

    // =========================================================================
    // INGLESE
    // =========================================================================
    Subject(
        id = "inglese", name = "Inglese", emoji = "🇬🇧", indirizzo = "Biennio comune", colorHex = 0xFF3949AB,
        chapters = listOf(
            Chapter(0, "Grammar: the present and the past", "📝",
                "Present simple, present continuous e past simple per descrivere azioni.",
                listOf(
                    Section("Present simple vs present continuous", listOf(
                        Block.Text("Il present simple descrive abitudini e verità generali: «I study every day». Si usa la -s alla terza persona singolare."),
                        Block.Text("Il present continuous descrive azioni in corso: «I am studying now». Si forma con to be + verbo in -ing."),
                        Block.Key("Abitudine → present simple; azione in corso → present continuous."),
                    )),
                    Section("Past simple", listOf(
                        Block.Text("Il past simple descrive azioni concluse nel passato: «Yesterday I studied». Verbi regolari + -ed; i verbi irregolari cambiano forma (go → went)."),
                        Block.Bullet("Indicatori di tempo: yesterday, last week, two days ago, in 2020."),
                        Block.Bullet("Forma negativa: didn't + forma base (I didn't go)."),
                        Block.Key("Past simple + espressioni di tempo passato: azione finita e datata."),
                    )),
                )),
            Chapter(0, "Everyday English", "💬",
                "Presentarsi, parlare di scuola e orientarsi in città.",
                listOf(
                    Section("Introductions and school", listOf(
                        Block.Text("Per presentarsi: «My name is…, I'm … years old, I live in…, I study at a technical school»."),
                        Block.Text("Per parlare della scuola si usano: subject (materia), timetable (orario), homework (compiti), classmates (compagni)."),
                    )),
                    Section("Asking for directions", listOf(
                        Block.Text("Per chiedere indicazioni: «Excuse me, where is the station?» Risposte: «Go straight on», «Turn left/right», «It's next to…»."),
                        Block.Key("Preposizioni utili: next to (accanto a), opposite (di fronte), between (tra)."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Noun", "Sostantivo: parola che indica una persona, un animale, una cosa o un luogo."),
            GlossaryTerm("Verb", "Verbo: parola che esprime un'azione o uno stato."),
            GlossaryTerm("Adjective", "Aggettivo: descrive un nome (es. big, red)."),
            GlossaryTerm("Present simple", "Tempo verbale per abitudini e verità generali."),
            GlossaryTerm("Present continuous", "Tempo per azioni in corso: to be + verbo in -ing."),
            GlossaryTerm("Irregular verbs", "Verbi che non formano il passato con -ed (es. go → went)."),
        ),
        flashcards = listOf(
            Flashcard("Grammar", "Quando si usa il present simple?", "Per abitudini e verità generali (I study every day)."),
            Flashcard("Grammar", "Come si forma il present continuous?", "Con to be + verbo in -ing (I am studying)."),
            Flashcard("Grammar", "Come si forma il past simple dei verbi regolari?", "Aggiungendo -ed (study → studied)."),
            Flashcard("Grammar", "Qual è il passato di «go»?", "went."),
            Flashcard("Vocabulary", "Che cosa significa «homework»?", "Compiti a casa."),
            Flashcard("Vocabulary", "«Next to» vuol dire:", "Accanto a."),
        ),
        questions = listOf(
            Question("Grammar", "«She usually … to school by bus» → forma corretta:",
                listOf("go", "goes", "going", "gone"), 1,
                "Terza persona singolare del present simple: goes."),
            Question("Grammar", "«I am studying now» indica:",
                listOf("Un'abitudine", "Un'azione in corso", "Un fatto passato", "Un piano futuro"), 1,
                "Il present continuous descrive azioni in corso."),
            Question("Grammar", "Il past simple di «study» è:",
                listOf("studyed", "studied", "studid", "studing"), 1,
                "Verbi regolari: -ed → studied."),
            Question("Grammar", "«Yesterday we … to the museum» → forma corretta:",
                listOf("go", "goes", "went", "going"), 2,
                "Azioni concluse nel passato: went (irregular)."),
            Question("Vocabulary", "«Timetable» significa:",
                listOf("Orario", "Compiti", "Materia", "Banco"), 0,
                "Timetable = orario delle lezioni."),
            Question("Vocabulary", "«Opposite» significa:",
                listOf("Accanto", "Di fronte", "Lontano", "Dentro"), 1,
                "Opposite = di fronte a."),
        ),
    ),

    // =========================================================================
    // FISICA
    // =========================================================================
    Subject(
        id = "fisica", name = "Scienze integrate: Fisica", emoji = "⚛️", indirizzo = "Biennio comune", colorHex = 0xFF00838F,
        chapters = listOf(
            Chapter(0, "La misura e il moto", "📏",
                "Grandezze fisiche, unità di misura e i primi tipi di moto.",
                listOf(
                    Section("Grandezze e unità di misura", listOf(
                        Block.Text("Una grandezza fisica è una proprietà misurabile (lunghezza, tempo, massa). Il Sistema Internazionale (SI) usa il metro, il secondo, il chilogrammo."),
                        Block.Text("Le grandezze si dividono in fondamentali (misurabili direttamente) e derivate (es. velocità = lunghezza/tempo)."),
                        Block.Key("Senza unità di misura un numero non dice nulla: 5 m ≠ 5 s."),
                    )),
                    Section("Moto rettilineo uniforme", listOf(
                        Block.Text("Un corpo si muove di moto rettilineo uniforme quando percorre spazi uguali in tempi uguali: la velocità è costante."),
                        Block.Text("Velocità media = spazio / tempo. Esempio: 100 km in 2 h → v = 50 km/h."),
                        Block.Key("Dalla formula v = s/t derivano s = v·t e t = s/v."),
                    )),
                )),
            Chapter(0, "Forze e moto", "💪",
                "Le forze, le tre leggi di Newton e la forza di gravità.",
                listOf(
                    Section("Le forze", listOf(
                        Block.Text("Una forza può deformare un corpo o cambiarne il moto; si misura in newton (N) con il dinamometro."),
                        Block.Text("La forza peso è F = m·g, dove g = 9,8 N/kg sulla Terra."),
                        Block.Key("Peso e massa non sono la stessa cosa: la massa non cambia, il peso dipende da g."),
                    )),
                    Section("Le leggi di Newton", listOf(
                        Block.Bullet("1ª legge (inerzia): un corpo mantiene il suo stato di quiete o moto rettilineo uniforme se non agiscono forze."),
                        Block.Bullet("2ª legge: F = m·a, la forza è uguale a massa per accelerazione."),
                        Block.Bullet("3ª legge (azione-reazione): a ogni azione corrisponde una reazione uguale e contraria."),
                        Block.Key("F = m·a è la legge fondamentale della dinamica."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Grandezza fisica", "Proprietà misurabile di un corpo o di un fenomeno (es. lunghezza, tempo)."),
            GlossaryTerm("Velocità media", "Rapporto tra spazio percorso e tempo impiegato: v = s/t."),
            GlossaryTerm("Accelerazione", "Variazione della velocità nell'unità di tempo."),
            GlossaryTerm("Forza", "Azione capace di deformare un corpo o di modificarne il moto; si misura in newton."),
            GlossaryTerm("Inerzia", "Tendenza di un corpo a mantenere il proprio stato di quiete o di moto."),
            GlossaryTerm("Peso", "Forza con cui la Terra attrae un corpo: P = m·g."),
        ),
        flashcards = listOf(
            Flashcard("Misura", "Quali sono le unità SI di lunghezza, tempo e massa?", "Metro, secondo, chilogrammo."),
            Flashcard("Misura", "Come si calcola la velocità media?", "v = spazio / tempo."),
            Flashcard("Moto", "Cos'è il moto rettilineo uniforme?", "Un moto a velocità costante su una retta."),
            Flashcard("Forze", "Con quale formula si calcola il peso?", "P = m·g (con g = 9,8 N/kg)."),
            Flashcard("Forze", "Enuncia la seconda legge di Newton.", "F = m·a: la forza è uguale a massa per accelerazione."),
            Flashcard("Forze", "Massa e peso sono la stessa cosa?", "No: la massa è quantità di materia, il peso è una forza che dipende da g."),
        ),
        questions = listOf(
            Question("Misura", "La velocità di 100 km percorsi in 2 ore è:",
                listOf("20 km/h", "50 km/h", "100 km/h", "200 km/h"), 1,
                "v = 100/2 = 50 km/h."),
            Question("Misura", "Nel SI la massa si misura in:",
                listOf("Newton", "Chilogrammi", "Metri", "Secondi"), 1,
                "Il chilogrammo è l'unità SI della massa."),
            Question("Forze", "La forza si misura in:",
                listOf("Joule", "Newton", "Watt", "Pascal"), 1,
                "Il newton (N) è l'unità della forza."),
            Question("Forze", "Secondo la prima legge di Newton, senza forze un corpo:",
                listOf("Si ferma sempre", "Mantiene quiete o moto uniforme", "Accelera", "Cade"), 1,
                "Il principio di inerzia: il corpo mantiene il suo stato."),
            Question("Forze", "La formula F = m·a esprime:",
                listOf("La legge di gravitazione", "La 2ª legge di Newton", "La legge di Ohm", "Il principio di Archimede"), 1,
                "F = m·a è la seconda legge della dinamica."),
            Question("Forze", "Un astronauta sulla Luna ha:",
                listOf("Massa minore", "Peso minore", "Peso uguale", "Nessuna massa"), 1,
                "La massa non cambia; il peso diminuisce perché g è più piccolo."),
        ),
    ),

    // =========================================================================
    // CHIMICA
    // =========================================================================
    Subject(
        id = "chimica", name = "Scienze integrate: Chimica", emoji = "🧪", indirizzo = "Biennio comune", colorHex = 0xFF00695C,
        chapters = listOf(
            Chapter(0, "La materia e le trasformazioni", "⚗️",
                "Stati di aggregazione, passaggi di stato, miscugli e sostanze pure.",
                listOf(
                    Section("Stati e passaggi di stato", listOf(
                        Block.Text("La materia si presenta in tre stati: solido (forma e volume propri), liquido (volume proprio), aeriforme (si espande)."),
                        Block.Bullet("Fusione: solido → liquido."),
                        Block.Bullet("Evaporazione: liquido → aeriforme."),
                        Block.Bullet("Condensazione: aeriforme → liquido."),
                        Block.Bullet("Solidificazione: liquido → solido."),
                        Block.Key("I passaggi di stato avvengono a temperatura costante e non cambiano la sostanza."),
                    )),
                    Section("Miscugli e sostanze pure", listOf(
                        Block.Text("Una sostanza pura ha composizione fissa (es. acqua distillata); un miscuglio è l'unione fisica di più sostanze."),
                        Block.Text("I miscugli possono essere omogenei (una sola fase, es. acqua e sale) o eterogenei (più fasi visibili, es. sabbia e acqua)."),
                        Block.Key("La distillazione separa due liquidi miscibili; la filtrazione separa un solido da un liquido."),
                    )),
                )),
            Chapter(0, "Atomi, molecole e tavola periodica", "⚛️",
                "Dai modelli atomici ai legami chimici e agli elementi.",
                listOf(
                    Section("L'atomo", listOf(
                        Block.Text("L'atomo ha un nucleo (protoni + e neutroni) e una nube di elettroni intorno."),
                        Block.Text("Il numero atomico Z è il numero di protoni; il numero di massa A è protoni + neutroni."),
                        Block.Key("Z identifica l'elemento; A determina l'isotopo."),
                    )),
                    Section("Elementi e legami", listOf(
                        Block.Text("La tavola periodica ordina gli elementi per numero atomico crescente: righe = periodi, colonne = gruppi."),
                        Block.Bullet("Legame ionico: trasferimento di elettroni (es. NaCl)."),
                        Block.Bullet("Legame covalente: condivisione di elettroni (es. H₂O)."),
                        Block.Bullet("Legame metallico: elettroni condivisi tra molti atomi."),
                        Block.Key("Elementi dello stesso gruppo hanno proprietà chimiche simili."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Atomo", "La più piccola parte di un elemento che ne conserva le proprietà chimiche."),
            GlossaryTerm("Molecola", "Aggregato di atomi uniti da legami chimici."),
            GlossaryTerm("Elemento", "Sostanza pura formata da atomi dello stesso tipo."),
            GlossaryTerm("Numero atomico", "Numero di protoni nel nucleo; identifica l'elemento."),
            GlossaryTerm("Legame ionico", "Legame dovuto al trasferimento di elettroni tra atomi."),
            GlossaryTerm("Tavola periodica", "Tabella che ordina gli elementi per numero atomico crescente."),
        ),
        flashcards = listOf(
            Flashcard("Materia", "Come si chiama il passaggio solido → liquido?", "Fusione."),
            Flashcard("Materia", "Un miscuglio omogeneo è…", "Un miscuglio con una sola fase (es. acqua e sale)."),
            Flashcard("Atomi", "Che cosa indica il numero atomico?", "Il numero di protoni, cioè quale elemento è."),
            Flashcard("Legami", "Differenza tra legame ionico e covalente?", "Ionico: trasferimento di elettroni; covalente: condivisione."),
            Flashcard("Atomi", "L'atomo è formato da…", "Nucleo (protoni e neutroni) ed elettroni."),
            Flashcard("Tavola periodica", "Le righe della tavola periodica si chiamano…", "Periodi; le colonne si chiamano gruppi."),
        ),
        questions = listOf(
            Question("Materia", "Il passaggio da liquido a solido è:",
                listOf("Fusione", "Solidificazione", "Condensazione", "Evaporazione"), 1,
                "Solidificazione: liquido → solido."),
            Question("Materia", "Acqua e sale sciolti formano un miscuglio:",
                listOf("Eterogeneo", "Omogeneo", "Puro", "Covalente"), 1,
                "Una sola fase: omogeneo."),
            Question("Atomi", "Il numero atomico è il numero di:",
                listOf("Neutroni", "Elettroni nel guscio esterno", "Protoni", "Atomi"), 2,
                "Z = numero di protoni."),
            Question("Legami", "L'acqua (H₂O) è tenuta insieme da legami:",
                listOf("Ionici", "Covalenti", "Metallici", "Deboli"), 1,
                "Idrogeno e ossigeno condividono elettroni: legame covalente."),
            Question("Tavola periodica", "Nella tavola periodica le colonne si chiamano:",
                listOf("Periodi", "Gruppi", "Serie", "Livelli"), 1,
                "Le colonne sono i gruppi."),
            Question("Materia", "Quale metodo separa un solido da un liquido?",
                listOf("Distillazione", "Filtrazione", "Decantazione del sangue", "Cromatografia"), 1,
                "La filtrazione trattiene il solido e lascia passare il liquido."),
        ),
    ),

    // =========================================================================
    // BIOLOGIA E SCIENZE DELLA TERRA
    // =========================================================================
    Subject(
        id = "biologia", name = "Biologia e Scienze della Terra", emoji = "🌍",
        indirizzo = "Biennio comune", colorHex = 0xFF2E7D32,
        chapters = Content.chapters,
        glossary = Content.glossary,
        flashcards = Content.flashcards,
        questions = Content.allQuestions,
    ),

    // =========================================================================
    // TECNOLOGIE E TECNICHE DI RAPPRESENTAZIONE GRAFICA
    // =========================================================================
    Subject(
        id = "disegno", name = "Tecn. e tecniche di rappresentazione grafica", emoji = "📐",
        indirizzo = "Biennio comune", colorHex = 0xFF5D4037,
        chapters = listOf(
            Chapter(0, "Il disegno tecnico", "📐",
                "Strumenti, norme, scale e proiezioni ortogonali.",
                listOf(
                    Section("Strumenti e norme", listOf(
                        Block.Text("Nel disegno tecnico si usano squadre, compasso, matite a punta fine e cartoncino; le linee hanno spessori e significati diversi."),
                        Block.Text("I disegni sono in scala: 1:1 (dimensioni reali), 1:2 (metà), 1:100 (centesimo). La quotatura indica le misure reali del pezzo."),
                        Block.Key("La scala riduce o ingrandisce il disegno, ma le quote restano le misure reali."),
                    )),
                    Section("Proiezioni ortogonali", listOf(
                        Block.Text("Le proiezioni ortogonali rappresentano un oggetto su tre piani perpendicolari: pianta (visto dall'alto), fronte e profilo."),
                        Block.Text("Ogni proiezione mostra due dimensioni dell'oggetto; serve più di una vista per capire la forma completa."),
                        Block.Key("Tre viste fondamentali: pianta, fronte, profilo."),
                    )),
                )),
            Chapter(0, "Assonometrie e sezioni", "✏️",
                "Rappresentazioni tridimensionali e tagli per vedere l'interno dei solidi.",
                listOf(
                    Section("Le assonometrie", listOf(
                        Block.Text("L'assonometria rappresenta un oggetto in 3D su un foglio: le più usate sono l'assonometria isometrica, cavaliera e monometrica."),
                        Block.Text("Nell'isometrica gli assi formano angoli di 120°; le misure sugli assi restano in scala reale."),
                    )),
                    Section("Le sezioni", listOf(
                        Block.Text("La sezione è il taglio ideale di un oggetto che ne mostra l'interno; la zona tagliata si tratteggia."),
                        Block.Text("Serve per capire fori interni, spessori e parti non visibili dall'esterno."),
                        Block.Key("Il tratteggio distingue la parte «tagliata» dal resto del disegno."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Scala", "Rapporto tra la misura nel disegno e la misura reale (es. 1:100)."),
            GlossaryTerm("Quotatura", "Insieme di linee e numeri che indicano le misure reali del pezzo."),
            GlossaryTerm("Proiezione ortogonale", "Rappresentazione dell'oggetto su piani perpendicolari tra loro."),
            GlossaryTerm("Assonometria", "Rappresentazione tridimensionale dell'oggetto su un foglio."),
            GlossaryTerm("Sezione", "Taglio ideale che mostra l'interno dell'oggetto; la parte tagliata si tratteggia."),
            GlossaryTerm("Pianta", "Vista dell'oggetto dall'alto nelle proiezioni ortogonali."),
        ),
        flashcards = listOf(
            Flashcard("Norme", "Che cosa indica la scala 1:2?", "Il disegno è la metà della dimensione reale."),
            Flashcard("Norme", "A cosa serve la quotatura?", "A indicare le misure reali del pezzo."),
            Flashcard("Proiezioni", "Quali sono le tre viste ortogonali principali?", "Pianta (alto), fronte e profilo."),
            Flashcard("Assonometrie", "Qual è la più usata delle assonometrie?", "L'isometrica (assi a 120°)."),
            Flashcard("Sezioni", "Come si indica la parte tagliata in una sezione?", "Con il tratteggio."),
            Flashcard("Strumenti", "In un disegno in scala 1:1 le dimensioni sono…", "Uguali a quelle reali."),
        ),
        questions = listOf(
            Question("Norme", "Nella scala 1:100, 1 cm nel disegno corrisponde a:",
                listOf("1 mm", "10 cm", "1 m", "100 m"), 2,
                "1 cm × 100 = 100 cm = 1 m."),
            Question("Proiezioni", "La vista dell'oggetto dall'alto è la:",
                listOf("Pianta", "Fronte", "Profilo", "Sezione"), 0,
                "La pianta corrisponde alla vista dall'alto."),
            Question("Sezioni", "La parte tagliata in una sezione si rappresenta con:",
                listOf("Un punto", "Il tratteggio", "Un colore pieno", "Una linea tratteggiata"), 1,
                "Il tratteggio segnala la zona del taglio."),
            Question("Assonometrie", "Nell'assonometria isometrica gli assi formano angoli di:",
                listOf("90°", "120°", "45°", "180°"), 1,
                "Tre assi a 120° l'uno dall'altro."),
            Question("Norme", "Le quote indicano:",
                listOf("Le misure del disegno", "Le misure reali", "Le scale", "Gli spessori delle linee"), 1,
                "Le quote danno sempre le misure reali del pezzo."),
            Question("Norme", "Ridurre un disegno alla metà significa usare la scala:",
                listOf("2:1", "1:2", "1:1", "1:20"), 1,
                "1:2 = il disegno è metà delle dimensioni reali."),
        ),
    ),

    // =========================================================================
    // DIRITTO ED ECONOMIA
    // =========================================================================
    Subject(
        id = "diritto-economia", name = "Diritto ed Economia", emoji = "⚖️", indirizzo = "Biennio comune", colorHex = 0xFFAD1457,
        chapters = listOf(
            Chapter(0, "Diritto: norme e Stato", "⚖️",
                "Le norme giuridiche, lo Stato e la Costituzione.",
                listOf(
                    Section("Le norme giuridiche", listOf(
                        Block.Text("La norma giuridica è una regola di condotta obbligatoria, generale e astratta; chi non la rispetta subisce una sanzione."),
                        Block.Bullet("Generale: si applica a tutti."),
                        Block.Bullet("Astratta: vale per situazioni ripetibili, non per un caso singolo."),
                        Block.Bullet("Coercitiva: garantita dallo Stato con la sanzione."),
                        Block.Key("Norma giuridica = regola obbligatoria, generale, astratta e sanzionata."),
                    )),
                    Section("Lo Stato e la Costituzione", listOf(
                        Block.Text("Lo Stato ha tre elementi: popolo, territorio, sovranità. I poteri sono legislativo (Parlamento), esecutivo (Governo), giudiziario (Magistratura)."),
                        Block.Text("La Costituzione italiana (1948) è la legge fondamentale: i suoi principi chiave sono democrazia, uguaglianza (art. 3), lavoro (art. 1) e solidarietà."),
                        Block.Key("La Costituzione è rigida: per cambiarla serve un procedimento speciale."),
                    )),
                )),
            Chapter(0, "Economia: bisogni e beni", "💰",
                "Bisogni, beni economici, reddito e consumo.",
                listOf(
                    Section("Bisogni e beni", listOf(
                        Block.Text("Il bisogno è una necessità o desiderio da soddisfare (mangiare, vestirsi, studiare); i beni e i servizi servono a soddisfarlo."),
                        Block.Text("Un bene è economico se è scarso rispetto ai bisogni (non è gratis); i beni liberi (aria, sole) non sono economici."),
                        Block.Key("Bene economico = bene scarso, prodotto con risorse limitate."),
                    )),
                    Section("Reddito e consumo", listOf(
                        Block.Text("Il reddito è ciò che si guadagna col lavoro o con il capitale; viene destinato al consumo (acquisti) o al risparmio."),
                        Block.Text("Il consumo dipende dal reddito e dai prezzi: a parità di reddito, prezzi più alti riducono la quantità acquistabile."),
                        Block.Key("Reddito = consumo + risparmio."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Norma giuridica", "Regola obbligatoria, generale, astratta, garantita da una sanzione."),
            GlossaryTerm("Sanzione", "Conseguenza prevista dalla legge per chi viola una norma."),
            GlossaryTerm("Costituzione", "Legge fondamentale dello Stato italiano, entrata in vigore nel 1948."),
            GlossaryTerm("Bisogno", "Necessità o desiderio che l'uomo cerca di soddisfare."),
            GlossaryTerm("Bene economico", "Bene scarso rispetto ai bisogni, prodotto con risorse limitate."),
            GlossaryTerm("Reddito", "Insieme dei guadagni di una persona (da lavoro o capitale)."),
        ),
        flashcards = listOf(
            Flashcard("Diritto", "Quali sono le caratteristiche della norma giuridica?", "Generale, astratta, obbligatoria, sanzionata."),
            Flashcard("Diritto", "Quali sono i tre elementi dello Stato?", "Popolo, territorio, sovranità."),
            Flashcard("Diritto", "Quando è entrata in vigore la Costituzione italiana?", "Nel 1948."),
            Flashcard("Economia", "Che cos'è un bene economico?", "Un bene scarso rispetto ai bisogni."),
            Flashcard("Economia", "Reddito = consumo + …", "Risparmio."),
            Flashcard("Diritto", "Quali sono i tre poteri dello Stato?", "Legislativo, esecutivo, giudiziario."),
        ),
        questions = listOf(
            Question("Diritto", "La sanzione è la conseguenza prevista per:",
                listOf("Chi paga le tasse", "Chi viola una norma giuridica", "Chi studia", "I minorenni"), 1,
                "La sanzione garantisce il rispetto delle norme."),
            Question("Diritto", "Il potere legislativo spetta a:",
                listOf("Governo", "Parlamento", "Magistratura", "Presidente"), 1,
                "Al Parlamento."),
            Question("Economia", "Un bene è economico se:",
                listOf("È gratuito", "È scarso rispetto ai bisogni", "È naturale", "È sempre disponibile"), 1,
                "La scarsità rispetto ai bisogni rende il bene economico."),
            Question("Economia", "Il denaro risparmiato anziché speso è parte del:", 
                listOf("Consumo", "Risparmio", "Reddito lordo", "Debito"), 1,
                "Reddito = consumo + risparmio."),
            Question("Diritto", "La Costituzione italiana è del:",
                listOf("1922", "1948", "1960", "1918"), 1,
                "Entrata in vigore il 1° gennaio 1948."),
            Question("Economia", "L'aria e la luce del sole sono beni:",
                listOf("Economici", "Liberi", "Rari", "Consumabili"), 1,
                "Beni liberi: disponibili senza costo."),
        ),
    ),

    // =========================================================================
    // GEOGRAFIA
    // =========================================================================
    Subject(
        id = "geografia", name = "Geografia", emoji = "🗺️", indirizzo = "Biennio comune", colorHex = 0xFF558B2F,
        chapters = listOf(
            Chapter(0, "La Terra e i suoi ambienti", "🌋",
                "Rappresentazioni della Terra, climi e ambienti naturali.",
                listOf(
                    Section("Il pianeta Terra", listOf(
                        Block.Text("La Terra ha forma di geoide; per localizzare i punti si usano latitudine (distanza dall'Equatore) e longitudine (distanza dal meridiano di Greenwich)."),
                        Block.Text("I movimenti principali sono la rotazione (giorno/notte) e la rivoluzione (stagioni)."),
                        Block.Key("Latitudine e longitudine sono coordinate misurate in gradi."),
                    )),
                    Section("Climi e ambienti", listOf(
                        Block.Text("Il clima dipende da latitudine, altitudine, distanza dal mare e correnti: da esso derivano gli ambienti naturali."),
                        Block.Bullet("Clima equatoriale → foresta pluviale."),
                        Block.Bullet("Clima arido → deserto."),
                        Block.Bullet("Clima temperato → boschi di latifoglie."),
                        Block.Bullet("Clima polare → tundra e ghiacci."),
                    )),
                )),
            Chapter(0, "Geografia umana", "👥",
                "Popolazione, città ed economia nel mondo.",
                listOf(
                    Section("La popolazione", listOf(
                        Block.Text("La popolazione mondiale supera gli 8 miliardi; il tasso di natalità è in calo nei Paesi ricchi, la crescita è forte in Africa e Asia."),
                        Block.Text("L'urbanizzazione è la crescita delle città; oggi più della metà dell'umanità vive in aree urbane."),
                        Block.Key("Più della metà della popolazione mondiale vive in città."),
                    )),
                    Section("Settori economici", listOf(
                        Block.Bullet("Primario: agricoltura, allevamento, pesca."),
                        Block.Bullet("Secondario: industria (fabbriche)."),
                        Block.Bullet("Terziario: servizi (banche, trasporti, turismo, commercio)."),
                        Block.Key("L'economia dei Paesi ricchi è dominata dal terziario."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Latitudine", "Distanza angolare di un punto dall'Equatore (da 0° a 90° N/S)."),
            GlossaryTerm("Longitudine", "Distanza angolare dal meridiano di Greenwich (0°)."),
            GlossaryTerm("Clima", "Insieme delle condizioni atmosferiche medie di una regione."),
            GlossaryTerm("Urbanizzazione", "Crescita delle città e della popolazione urbana."),
            GlossaryTerm("Terziario", "Settore economico dei servizi."),
            GlossaryTerm("Tundra", "Ambiente freddo del circolo polare con vegetazione bassa e muschi."),
        ),
        flashcards = listOf(
            Flashcard("Terra", "Quali movimenti della Terra provocano giorno/notte e stagioni?", "Rotazione (giorno/notte) e rivoluzione (stagioni)."),
            Flashcard("Terra", "Che cosa misura la latitudine?", "La distanza dall'Equatore in gradi."),
            Flashcard("Climi", "Quale ambiente corrisponde al clima arido?", "Il deserto."),
            Flashcard("Umanità", "Quale settore comprende le banche e il turismo?", "Il terziario."),
            Flashcard("Umanità", "Che cos'è l'urbanizzazione?", "La crescita delle città e della popolazione urbana."),
            Flashcard("Terra", "Il meridiano di riferimento (0°) è quello di…", "Greenwich."),
        ),
        questions = listOf(
            Question("Terra", "Le stagioni sono causate da:",
                listOf("La rotazione", "La rivoluzione attorno al Sole", "Le maree", "I venti"), 1,
                "La rivoluzione e l'inclinazione dell'asse terrestre."),
            Question("Terra", "La distanza dall'Equatore espressa in gradi è la…",
                listOf("Longitudine", "Latitudine", "Altitudine", "Distanza"), 1,
                "La latitudine misura la distanza dall'Equatore."),
            Question("Climi", "La foresta pluviale si trova in clima:",
                listOf("Polare", "Equatoriale", "Arido", "Temperato"), 1,
                "Clima equatoriale, caldo e umido."),
            Question("Umanità", "L'industria fa parte del settore:",
                listOf("Primario", "Secondario", "Terziario", "Quaternario"), 1,
                "Il secondario comprende l'industria."),
            Question("Umanità", "Più della metà della popolazione mondiale vive…",
                listOf("In aree rurali", "In città", "In montagna", "Sulle coste"), 1,
                "Oggi più del 50% della popolazione è urbana."),
            Question("Umanità", "La pesca appartiene al settore:",
                listOf("Primario", "Secondario", "Terziario", "Finanziario"), 0,
                "Il primario comprende agricoltura, allevamento e pesca."),
        ),
    ),

    // =========================================================================
    // SCIENZE MOTORIE E SPORTIVE
    // =========================================================================
    Subject(
        id = "scienze-motorie", name = "Scienze motorie e sportive", emoji = "🏃",
        indirizzo = "Biennio comune", colorHex = 0xFFF4511E,
        chapters = listOf(
            Chapter(0, "Il corpo in movimento", "🦵",
                "Apparato locomotore, qualità motorie e sicurezza prima dell'attività.",
                listOf(
                    Section("Apparato locomotore", listOf(
                        Block.Text("Il movimento dipende da scheletro (sostegno), articolazioni (mobilità) e muscoli (forza motrice)."),
                        Block.Text("I muscoli lavorano in coppia: il flessore piega, l'estensore stende (es. bicipite e tricipite)."),
                        Block.Key("Un muscolo può solo tirare (contrazione), mai spingere: servono muscoli antagonisti."),
                    )),
                    Section("Qualità motorie", listOf(
                        Block.Bullet("Resistenza: capacità di proseguire a lungo un'attività."),
                        Block.Bullet("Forza: capacità di vincere resistenze."),
                        Block.Bullet("Flessibilità: ampiezza dei movimenti articolari."),
                        Block.Bullet("Coordinazione: controllo preciso dei movimenti."),
                        Block.Key("Il riscaldamento (5-10 min) prepara muscoli e cuore e previene gli infortuni."),
                    )),
                )),
            Chapter(0, "Sport e salute", "❤️",
                "Regole di base, fair play e stile di vita sano.",
                listOf(
                    Section("Sport di squadra", listOf(
                        Block.Text("Calcio, basket e volley sono sport di squadra: l'obiettivo è cooperare per vincere, rispettando regole e avversari."),
                        Block.Text("Il fair play è il rispetto di regole, arbitri, avversari e compagni: vince anche chi perde con sportività."),
                    )),
                    Section("Stile di vita sano", listOf(
                        Block.Text("Per stare bene: attività fisica regolare (almeno 30 min al giorno), idratazione, sonno sufficiente e alimentazione equilibrata."),
                        Block.Text("Il movimento riduce stress, obesità e rischio di malattie cardiovascolari."),
                        Block.Key("Salute = equilibrio tra movimento, alimentazione, riposo e benessere mentale."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Resistenza", "Capacità di protrarre a lungo un'attività fisica."),
            GlossaryTerm("Flessibilità", "Ampiezza dei movimenti delle articolazioni."),
            GlossaryTerm("Coordinazione", "Controllo armonico dei movimenti del corpo."),
            GlossaryTerm("Fair play", "Comportamento leale e rispettoso nello sport."),
            GlossaryTerm("Riscaldamento", "Attività preparatoria che riduce il rischio di infortuni."),
            GlossaryTerm("Adattamento", "Modifica dell'organismo in risposta all'allenamento."),
        ),
        flashcards = listOf(
            Flashcard("Corpo", "Perché il riscaldamento è importante?", "Prepara muscoli e cuore e previene gli infortuni."),
            Flashcard("Corpo", "Che cos'è la resistenza?", "La capacità di protrarre a lungo un'attività."),
            Flashcard("Corpo", "Bicipite e tricipite sono…", "Muscoli antagonisti: uno flette, l'altro estende."),
            Flashcard("Sport", "Che cos'è il fair play?", "Il rispetto di regole, avversari e arbitri."),
            Flashcard("Salute", "Quanto movimento al giorno è consigliato?", "Almeno 30 minuti di attività moderata."),
            Flashcard("Corpo", "La flessibilità dipende da…", "L'ampiezza dei movimenti articolari."),
        ),
        questions = listOf(
            Question("Corpo", "I muscoli che svolgono funzioni opposte si chiamano:",
                listOf("Sinergici", "Antagonisti", "Combinati", "Riflessi"), 1,
                "Antagonisti: uno flette, l'altro estende."),
            Question("Corpo", "Il riscaldamento serve a:",
                listOf("Stancarsi subito", "Prevenire infortuni", "Aumentare l'adrenalina", "Saltare l'allenamento"), 1,
                "Prepara il corpo allo sforzo e previene gli infortuni."),
            Question("Salute", "L'attività fisica consigliata al giorno è di almeno:",
                listOf("10 minuti", "30 minuti", "2 ore", "5 ore"), 1,
                "Almeno 30 minuti di attività moderata."),
            Question("Sport", "Il fair play significa:",
                listOf("Vincere a ogni costo", "Rispettare regole e avversari", "Giocare da soli", "Fare il tifo"), 1,
                "Lealtà e rispetto dentro e fuori dal campo."),
            Question("Corpo", "La capacità di protrarre a lungo uno sforzo è la:",
                listOf("Forza", "Resistenza", "Velocità", "Coordinazione"), 1,
                "La resistenza."),
            Question("Salute", "L'allenamento regolare aiuta a:",
                listOf("Aumentare lo stress", "Ridurre il rischio cardiovascolare", "Ridurre il sonno", "Aumentare il peso"), 1,
                "Il movimento riduce stress e rischio cardiovascolare."),
        ),
    ),
)