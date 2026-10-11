package com.brahma.bioquaderno.data

/**
 * Materie del TRIENNIO (parte B): Elettronica ed Elettrotecnica,
 * Meccanica/Meccatronica/Energia, Chimica Materiali e Biotecnologie,
 * Agraria, Costruzioni Ambiente e Territorio, Moda, Grafica e Comunicazione.
 */
fun triennioBSubjects(): List<Subject> = listOf(
    // =========================================================================
    // ELETTRONICA ED ELETTROTECNICA — ELETTROTECNICA ED ELETTRONICA
    // =========================================================================
    Subject(
        id = "elettrotecnica", name = "Elettrotecnica ed Elettronica", emoji = "⚡",
        indirizzo = "Elettronica ed Elettrotecnica", colorHex = 0xFFF9A825,
        chapters = listOf(
            Chapter(0, "I circuiti elettrici", "🔌",
                "Corrente, tensione, resistenza e la legge di Ohm.",
                listOf(
                    Section("Grandezze fondamentali", listOf(
                        Block.Text("La tensione (V, volt) è la «pressione» che spinge gli elettroni; la corrente (I, ampere) è il flusso di cariche; la resistenza (R, ohm) ostacola il flusso."),
                        Block.Text("La potenza (P, watt) è l'energia consumata in un secondo: P = V × I."),
                        Block.Key("P = V × I (potenza = tensione per corrente)."),
                    )),
                    Section("La legge di Ohm", listOf(
                        Block.Text("La legge di Ohm lega le tre grandezze: V = R × I. A parità di tensione, più resistenza = meno corrente."),
                        Block.Bullet("Resistenze in serie: R totale = somma delle R."),
                        Block.Bullet("Resistenze in parallelo: la corrente si divide, la R totale diminuisce."),
                        Block.Key("V = R × I; da essa si ricavano R = V/I e I = V/R."),
                    )),
                )),
            Chapter(0, "Componenti e sicurezza", "🧰",
                "Resistenze, condensatori, sensori e le regole di sicurezza.",
                listOf(
                    Section("Componenti passivi", listOf(
                        Block.Text("Il condensatore immagazzina carica e si scarica liberandola (es. nei circuiti temporizzati)."),
                        Block.Text("Il multimetro misura tensione, corrente e resistenza: va collegato in serie per la corrente, in parallelo per la tensione."),
                        Block.Key("Multimetro: in serie per gli ampere, in parallelo per i volt."),
                    )),
                    Section("Sicurezza elettrica", listOf(
                        Block.Bullet("Non toccare mai conduttori sotto tensione."),
                        Block.Bullet("Usare calzature e attrezzi isolanti."),
                        Block.Bullet("L'interruttore differenziale (salvavita) protegge dalle dispersioni."),
                        Block.Key("Il salvavita interrompe il circuito in caso di dispersione di corrente."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Tensione", "Differenza di potenziale elettrico, misurata in volt (V)."),
            GlossaryTerm("Corrente", "Flusso di cariche elettriche, misurato in ampere (A)."),
            GlossaryTerm("Resistenza", "Opposizione al passaggio della corrente, misurata in ohm (Ω)."),
            GlossaryTerm("Potenza", "Energia usata nell'unità di tempo: P = V × I, in watt (W)."),
            GlossaryTerm("Condensatore", "Componente che immagazzina carica elettrica."),
            GlossaryTerm("Legge di Ohm", "Relazione V = R × I tra tensione, resistenza e corrente."),
        ),
        flashcards = listOf(
            Flashcard("Grandezze", "Quali unità hanno tensione, corrente e resistenza?", "Volt (V), ampere (A), ohm (Ω)."),
            Flashcard("Grandezze", "Formula della legge di Ohm?", "V = R × I."),
            Flashcard("Grandezze", "Come si calcola la potenza?", "P = V × I."),
            Flashcard("Componenti", "A cosa serve il condensatore?", "A immagazzinare e rilasciare carica elettrica."),
            Flashcard("Sicurezza", "Come si collega il multimetro per misurare la corrente?", "In serie al circuito."),
        ),
        questions = listOf(
            Question("Grandezze", "La resistenza si misura in:", listOf("Volt", "Ampere", "Ohm", "Watt"), 2, "In ohm (Ω)."),
            Question("Grandezze", "Con V = 12 V e R = 4 Ω, la corrente è:", listOf("48 A", "3 A", "8 A", "0,3 A"), 1, "I = V/R = 12/4 = 3 A."),
            Question("Grandezze", "Se aumenti la resistenza a parità di tensione, la corrente:", listOf("Aumenta", "Diminuisce", "Resta uguale", "Si annulla sempre"), 1, "Più resistenza = meno corrente (Ohm)."),
            Question("Sicurezza", "Il dispositivo che protegge dalle dispersioni di corrente è:", listOf("Il fusibile lento", "L'interruttore differenziale", "Il condensatore", "Il trasformatore"), 1, "Il salvavita interrompe il circuito in caso di dispersione."),
            Question("Componenti", "Il multimetro per misurare la tensione si collega:", listOf("In serie", "In parallelo", "Senza fili", "Alla terra"), 1, "La tensione si misura in parallelo."),
        ),
    ),

    // =========================================================================
    // ELETTRONICA — SISTEMI AUTOMATICI
    // =========================================================================
    Subject(
        id = "sistemi-automatici", name = "Sistemi automatici", emoji = "🤖",
        indirizzo = "Elettronica ed Elettrotecnica", colorHex = 0xFF6D4C41,
        chapters = listOf(
            Chapter(0, "Il controllo automatico", "🎛️",
                "Anello di controllo, sensori e attuatori.",
                listOf(
                    Section("Sistemi di controllo", listOf(
                        Block.Text("Un sistema di controllo automatico regola una grandezza senza intervento umano: impianto di riscaldamento, cruise control, termostato."),
                        Block.Text("A ciclo aperto: agisce senza controllare il risultato. A ciclo chiuso (retroazione): misura l'uscita e corregge l'azione."),
                        Block.Key("La retroazione (feedback) è il cuore del controllo a ciclo chiuso."),
                    )),
                    Section("Sensori e attuatori", listOf(
                        Block.Text("Il sensore (trasduttore) trasforma una grandezza fisica in un segnale elettrico: temperatura, luce, distanza, pressione."),
                        Block.Text("L'attuatore esegue l'azione comandata: motore, servomotore, valvola, relè."),
                        Block.Key("Sensore = occhio del sistema; attuatore = mano del sistema."),
                    )),
                )),
            Chapter(0, "PLC e automazione industriale", "🏭",
                "Dal controllo a relè al PLC programmabile.",
                listOf(
                    Section("Il PLC", listOf(
                        Block.Text("Il PLC (Programmable Logic Controller) è un computer industriale che esegue un programma di controllo leggendo ingressi e comandando uscite."),
                        Block.Text("Si programma con diagrammi a contatti (ladder) o testi strutturati."),
                        Block.Key("Il PLC sostituisce i quadri a relè: è programmabile, robusto e affidabile."),
                    )),
                    Section("Applicazioni", listOf(
                        Block.Bullet("Nastri trasportatori e linee di montaggio."),
                        Block.Bullet("Bracci robotici con celle di lavorazione."),
                        Block.Bullet("Impianti di climatizzazione e illuminazione (building automation)."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Retroazione", "Meccanismo che misura l'uscita e corregge l'ingresso del sistema."),
            GlossaryTerm("Sensore", "Dispositivo che trasforma una grandezza fisica in un segnale elettrico."),
            GlossaryTerm("Attuatore", "Dispositivo che esegue l'azione di comando (motore, valvola, relè)."),
            GlossaryTerm("PLC", "Computer industriale programmabile per il controllo automatico."),
            GlossaryTerm("Trasduttore", "Dispositivo che converte una grandezza in un'altra (es. fisica → elettrica)."),
            GlossaryTerm("A ciclo chiuso", "Sistema di controllo che usa la retroazione per correggersi."),
        ),
        flashcards = listOf(
            Flashcard("Controllo", "Che cos'è la retroazione?", "La misura dell'uscita usata per correggere il comando."),
            Flashcard("Controllo", "Differenza tra sensore e attuatore?", "Il sensore legge; l'attuatore agisce."),
            Flashcard("Controllo", "Il termostato di casa regola in…", "Ciclo chiuso (misura e corregge)."),
            Flashcard("PLC", "Che cos'è un PLC?", "Un computer industriale programmabile per il controllo."),
            Flashcard("PLC", "Come si programma di solito un PLC?", "Con diagrammi a contatti (ladder)."),
        ),
        questions = listOf(
            Question("Controllo", "Un sistema che non verifica il risultato dell'azione lavora:", listOf("A ciclo chiuso", "A ciclo aperto", "In retroazione", "In parallelo"), 1, "A ciclo aperto: comanda senza controllare."),
            Question("Controllo", "Il dispositivo che trasforma la temperatura in segnale elettrico è un:", listOf("Attuatore", "Sensore", "Relè", "Motore"), 1, "Il sensore (trasduttore)."),
            Question("PLC", "Il PLC sostituisce:", listOf("I motori", "I quadri a relè", "I sensori", "I trasformatori"), 1, "Il controllo a relè, con un sistema programmabile."),
            Question("Controllo", "Nel cruise control dell'auto la velocità misurata serve a…", listOf("Niente", "Correggere l'accelerazione (retroazione)", "Spegnere il motore", "Aumentare il consumo"), 1, "È un classico esempio di ciclo chiuso."),
            Question("PLC", "I bracci robotici nelle fabbriche sono un esempio di:", listOf("Automazione industriale", "Telefonia", "Energia", "Grafica"), 0, "Automazione industriale."),
        ),
    ),

    // =========================================================================
    // ELETTRONICA — TECN. E PROGETTAZIONE SISTEMI ELETTRICI (COMPATTA)
    // =========================================================================
    Subject(
        id = "tecn-prog-elettrici", name = "Tecn. e progettazione di sistemi elettrici", emoji = "🔌",
        indirizzo = "Elettronica ed Elettrotecnica", colorHex = 0xFFE65100,
        chapters = listOf(
            Chapter(0, "Progettare un impianto elettrico", "🛠️",
                "Dallo schema al quadro elettrico, tra norme e sicurezza.",
                listOf(
                    Section("Schemi e documenti", listOf(
                        Block.Text("Si parte dai requisiti del cliente: punti luce, prese, potenze. Poi si disegna lo schema unifilare e si scelgono i componenti."),
                        Block.Text("Il progetto contiene: planimetria con posizioni, schema elettrico, calcolo delle sezioni dei cavi e della protezione."),
                        Block.Key("Lo schema unifilare sintetizza l'intero impianto in un'unica linea."),
                    )),
                    Section("Norme e sicurezza", listOf(
                        Block.Text("Gli impianti devono rispettare le norme CEI: sezioni minime dei cavi, protezioni (interruttori magnetotermici e differenziali) e messa a terra."),
                        Block.Bullet("Magnetotermico: protegge da sovraccarichi e cortocircuiti."),
                        Block.Bullet("Differenziale (salvavita): protegge le persone dalle dispersioni."),
                        Block.Key("Norme CEI → impianto sicuro, verificato e documentato."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Schema unifilare", "Schema che rappresenta l'intero impianto con una sola linea.")
        , GlossaryTerm("Magnetotermico", "Interruttore che protegge da sovraccarichi e cortocircuiti."),
            GlossaryTerm("Differenziale", "Interruttore che protegge le persone dalle dispersioni di corrente."),
            GlossaryTerm("CEI", "Norme italiane per gli impianti elettrici."),
            GlossaryTerm("Messa a terra", "Impianto che scarica a terra le correnti di guasto."),
        ),
        flashcards = listOf(
            Flashcard("Progetto", "Che cos'è lo schema unifilare?", "La rappresentazione dell'intero impianto in un'unica linea."),
            Flashcard("Sicurezza", "A cosa serve il magnetotermico?", "A proteggere da sovraccarichi e cortocircuiti."),
            Flashcard("Sicurezza", "Il salvavita protegge da:", "Le dispersioni di corrente."),
            Flashcard("Progetto", "Da cosa si parte per progettare un impianto?", "Dai requisiti del cliente (punti luce, prese, potenze)."),
        ),
        questions = listOf(
            Question("Sicurezza", "L'interruttore differenziale protegge:", listOf("Dai sovraccarichi", "Le persone dalle dispersioni", "Dal fulmine", "Dal caldo"), 1, "Protegge le persone dalle dispersioni verso terra."),
            Question("Progetto", "Cosa contiene lo schema unifilare?", listOf("Solo le prese", "L'intero impianto in una linea", "I prezzi", "Le marche"), 1, "Sintetizza tutto l'impianto."),
            Question("Norme", "Le norme degli impianti elettrici italiani si chiamano:", listOf("ISO", "CEI", "OSI", "HTML"), 1, "Norme CEI."),
            Question("Sicurezza", "La messa a terra serve a:", listOf("Risparmiare", "Scaricare a terra le correnti di guasto", "Accendere le luci", "Caricare il telefono"), 1, "Protegge scaricando le correnti di guasto."),
        ),
    ),

    // =========================================================================
    // MECCANICA — MECCANICA, MACCHINE ED ENERGIA
    // =========================================================================
    Subject(
        id = "meccanica", name = "Meccanica, Macchine ed Energia", emoji = "⚙️",
        indirizzo = "Meccanica, Meccatronica ed Energia", colorHex = 0xFF546E7A,
        chapters = listOf(
            Chapter(0, "Statica e resistenza dei materiali", "🏗️",
                "Forze, sforzi e deformazioni nei corpi.",
                listOf(
                    Section("Le forze e le leve", listOf(
                        Block.Text("Le forze sono grandezze vettoriali: hanno intensità, direzione e verso. Due forze si sommano con il parallelogramma."),
                        Block.Text("La leva è una macchina semplice: potenza, resistenza e fulcro. Il momento è l'effetto rotante di una forza."),
                        Block.Key("Momento = forza × braccio (M = F × b)."),
                    )),
                    Section("Sforzo e deformazione", listOf(
                        Block.Text("Lo sforzo è la forza per unità di area: σ = F/A (misurato in pascal o N/mm²)."),
                        Block.Text("La deformazione relativa ε è l'allungamento rispetto alla lunghezza iniziale; entro certi limiti vale la legge di Hooke."),
                        Block.Key("σ = F/A: più sezione → meno sforzo a parità di forza."),
                    )),
                )),
            Chapter(0, "Trasmissione del moto", "🔧",
                "Ingranaggi, pulegge e rapporti di trasmissione.",
                listOf(
                    Section("Ingranaggi e rapporto", listOf(
                        Block.Text("Gli ingranaggi trasmettono il moto tra alberi: due ruote dentate ingranate ruotano in versi opposti."),
                        Block.Text("Il rapporto di trasmissione è i = n1/n2 = z2/z1 (n = giri, z = denti). Se i > 1 il moto è riduttore (più coppia)."),
                        Block.Key("Rapporto di trasmissione: con ruote più piccole si va più veloci, con ruote più grandi si ha più coppia."),
                    )),
                    Section("Pulegge e cinghie", listOf(
                        Block.Text("Le cinghie trasmettono il moto tra pulegge distanti, sfruttando l'attrito; le catene evitano lo slittamento."),
                        Block.Bullet("Riduttore: diminuisce i giri e aumenta la coppia."),
                        Block.Bullet("Moltiplicatore: aumenta i giri e riduce la coppia."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Sforzo", "Forza per unità di area: σ = F/A."),
            GlossaryTerm("Deformazione", "Variazione di forma o di dimensioni sotto carico."),
            GlossaryTerm("Momento", "Effetto rotante di una forza: M = F × b."),
            GlossaryTerm("Rapporto di trasmissione", "Rapporto tra i giri di due ruote collegate."),
            GlossaryTerm("Ingranaggio", "Ruota dentata che trasmette il moto per contatto."),
            GlossaryTerm("Legge di Hooke", "Nella zona elastica lo sforzo è proporzionale alla deformazione."),
        ),
        flashcards = listOf(
            Flashcard("Statica", "Come si calcola lo sforzo?", "σ = F/A (forza su area)."),
            Flashcard("Statica", "Che cos'è il momento di una forza?", "L'effetto rotante: M = F × b."),
            Flashcard("Trasmissione", "A che serve un riduttore?", "A diminuire i giri aumentando la coppia."),
            Flashcard("Trasmissione", "Cosa trasmette il moto tra alberi distanti?", "Cinghie o catene."),
            Flashcard("Trasmissione", "Due ruote dentate ingranate ruotano…", "In versi opposti."),
        ),
        questions = listOf(
            Question("Statica", "Lo sforzo si misura in:", listOf("Watt", "N/mm² (pascal)", "Ampere", "Metri"), 1, "Unità di forza su area."),
            Question("Statica", "A parità di forza, raddoppiando la sezione lo sforzo:", listOf("Raddoppia", "Si dimezza", "Resta uguale", "Si azzera"), 1, "σ = F/A: area doppia, sforzo dimezzato."),
            Question("Trasmissione", "Un riduttore di giri:", listOf("Aumenta la velocità", "Aumenta la coppia", "Risparmia energia", "Frena il moto"), 1, "Meno giri, più coppia."),
            Question("Trasmissione", "M = F × b è la formula del:", listOf("Sforzo", "Momento", "Rapporto di trasmissione", "Rendimento"), 1, "Il momento di una forza."),
            Question("Trasmissione", "La cinghia trasmette il moto sfruttando:", listOf("I denti", "L'attrito", "Il magnetismo", "La pressione"), 1, "L'attrito tra cinghia e puleggia."),
        ),
    ),

    // =========================================================================
    // MECCANICA — SISTEMI E AUTOMAZIONE
    // =========================================================================
    Subject(
        id = "sistemi-automazione", name = "Sistemi e Automazione", emoji = "🚗",
        indirizzo = "Meccanica, Meccatronica ed Energia", colorHex = 0xFF455A64,
        chapters = listOf(
            Chapter(0, "Automazione industriale", "🏭",
                "Cicli produttivi, robotica e controllo dei processi.",
                listOf(
                    Section("L'automazione", listOf(
                        Block.Text("L'automazione sostituisce il lavoro umano con macchine e sistemi di controllo, aumentando produttività e qualità."),
                        Block.Text("Una linea automatica è composta da stazioni: alimentazione, lavorazione, controllo qualità, imballaggio."),
                        Block.Key("Obiettivo dell'automazione: produrre di più, meglio e con meno errori."),
                    )),
                    Section("Robotica", listOf(
                        Block.Text("Un robot industriale ha un braccio con giunti e un controllore che ne guida i movimenti."),
                        Block.Text("I robot lavorano in celle: operazioni di saldatura, verniciatura, montaggio, pallettizzazione."),
                        Block.Key("Il robot ripete con precisione movimenti programmati, senza affaticarsi."),
                    )),
                )),
            Chapter(0, "Pneumatica e idraulica", "💨",
                "I circuiti a fluido usati nell'automazione.",
                listOf(
                    Section("Pneumatica", listOf(
                        Block.Text("I circuiti pneumatici usano aria compressa: cilindri, valvole e compressori muovono organi di lavoro."),
                        Block.Text("Il cilindro pneumatico trasforma la pressione dell'aria in movimento lineare."),
                        Block.Key("Pneumatica = aria compressa; idraulica = olio in pressione."),
                    )),
                    Section("Idraulica", listOf(
                        Block.Text("L'idraulica usa olio in pressione: forze molto elevate (freni, sollevatori, presse)."),
                        Block.Bullet("Pro: forze grandi e controllo preciso."),
                        Block.Bullet("Contro: impianti più costosi e rischi di perdite."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Automazione", "Sostituzione del lavoro umano con macchine e controlli automatici."),
            GlossaryTerm("Robot", "Macchina programmabile con braccio e movimenti guidati dal controllore."),
            GlossaryTerm("Cilindro pneumatico", "Attuatore che trasforma l'aria compressa in movimento lineare."),
            GlossaryTerm("Pneumatica", "Tecnica che usa aria compressa per muovere organi meccanici."),
            GlossaryTerm("Idraulica", "Tecnica che usa olio in pressione, adatta a forze elevate."),
            GlossaryTerm("Cella robotizzata", "Area protetta in cui il robot lavora in sicurezza."),
        ),
        flashcards = listOf(
            Flashcard("Automazione", "Perché si automatizza un processo?", "Per produrre di più, meglio e con meno errori."),
            Flashcard("Automazione", "Che cos'è una cella robotizzata?", "L'area protetta in cui lavora il robot."),
            Flashcard("Pneumatica", "Cosa trasforma il cilindro pneumatico?", "La pressione dell'aria in movimento lineare."),
            Flashcard("Idraulica", "Quale fluido usa l'idraulica?", "Olio in pressione."),
            Flashcard("Pneumatica", "Differenza tra pneumatica e idraulica?", "Aria compressa contro olio in pressione."),
        ),
        questions = listOf(
            Question("Automazione", "I vantaggi dell'automazione sono:", listOf("Più errori", "Produttività e qualità maggiori", "Più stanchezza", "Costi fissi"), 1, "Maggiore produttività, qualità e costanza."),
            Question("Robotica", "Chi guida i movimenti del robot?", listOf("Il controllore", "Un operatore con il joystick sempre", "La gravità", "Il motore termico"), 0, "Il controllore esegue il programma."),
            Question("Pneumatica", "Il compressore serve a:", listOf("Raffreddare", "Comprimere l'aria", "Filtrare l'olio", "Caricare le batterie"), 1, "Produce aria compressa per il circuito."),
            Question("Idraulica", "L'idraulica si sceglie quando servono:", listOf("Forze piccole", "Forze molto elevate", "Solo aria", "Movimenti lenti"), 1, "L'olio in pressione sviluppa forze elevate."),
            Question("Automazione", "La saldatura robotizzata è un esempio di:", listOf("Processo manuale", "Automazione industriale", "Idraulica", "Grafica"), 1, "Automazione industriale."),
        ),
    ),

    // =========================================================================
    // MECCANICA — TECNOLOGIE MECCANICHE
    // =========================================================================
    Subject(
        id = "tecn-meccaniche", name = "Tecnologie meccaniche di processo e prodotto", emoji = "🔩",
        indirizzo = "Meccanica, Meccatronica ed Energia", colorHex = 0xFF37474F,
        chapters = listOf(
            Chapter(0, "I materiali metallici", "🧱",
                "Acciai, ghise e leghe: proprietà e applicazioni.",
                listOf(
                    Section("Metalli ferrosi", listOf(
                        Block.Text("Il ferro in lega con il carbonio dà acciai (meno carbonio: duttili, saldabili) e ghise (più carbonio: fusibili, rigide)."),
                        Block.Text("Gli acciai legati aggiungono nichel, cromo o molibdeno per migliorare resistenza e corrosione."),
                        Block.Key("Acciaio = resistente e lavorabile; ghisa = economica, rigida e di facile fusione."),
                    )),
                    Section("Metalli non ferrosi", listOf(
                        Block.Bullet("Alluminio: leggero e resistente alla corrosione (aerei, scatolame)."),
                        Block.Bullet("Rame: ottimo conduttore (fili e tubi)."),
                        Block.Bullet("Leghe: ottone (rame+zinco) e bronzo (rame+stagno)."),
                        Block.Key("Le leghe uniscono le proprietà dei metalli di base."),
                    )),
                )),
            Chapter(0, "Le lavorazioni meccaniche", "🛠️",
                "Dall'asportazione di truciolo alle misure di controllo.",
                listOf(
                    Section("Lavorazioni per asportazione", listOf(
                        Block.Text("Tornitura: il pezzo ruota e l'utensile asporta materiale per fare superfici cilindriche."),
                        Block.Text("Fresatura: l'utensile rotante lavora il pezzo fermo (piani, scanalature, fori)."),
                        Block.Key("Tornio = pezzo che gira; fresatrice = utensile che gira."),
                    )),
                    Section("Misura e controllo", listOf(
                        Block.Text("Le tolleranze indicano l'errore ammesso sulla dimensione: senza tolleranza i pezzi non si accoppiano."),
                        Block.Text("Strumenti: calibro, micrometro, comparatore; durezza e trazione testano le proprietà del materiale."),
                        Block.Key("Tolleranza = campo di scostamento ammesso sulla quota."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Acciaio", "Lega ferro-carbonio con poco carbonio: duttile e saldabile."),
            GlossaryTerm("Ghisa", "Lega ferro-carbonio con più carbonio: rigida e di facile fusione."),
            GlossaryTerm("Leghe non ferrose", "Lega di metalli senza ferro (ottone, bronzo)."),
            GlossaryTerm("Tornitura", "Lavorazione in cui ruota il pezzo e taglia l'utensile."),
            GlossaryTerm("Fresatura", "Lavorazione in cui ruota l'utensile e lavora il pezzo."),
            GlossaryTerm("Tolleranza", "Scostamento ammesso sulla dimensione di un pezzo."),
        ),
        flashcards = listOf(
            Flashcard("Materiali", "Differenza tra acciaio e ghisa?", "Acciaio: poco carbonio, duttile; ghisa: più carbonio, rigida."),
            Flashcard("Materiali", "Quale metallo è leggero e resistente alla corrosione?", "L'alluminio."),
            Flashcard("Lavorazioni", "Nella tornitura cosa ruota?", "Il pezzo."),
            Flashcard("Lavorazioni", "Lo strumento che misura con precisione al centesimo è il…", "Micrometro."),
            Flashcard("Lavorazioni", "Che cos'è la tolleranza?", "L'errore ammesso sulla dimensione."),
        ),
        questions = listOf(
            Question("Materiali", "Il bronzo è una lega di:", listOf("Rame e zinco", "Rame e stagno", "Ferro e carbonio", "Alluminio e magnesio"), 1, "Rame + stagno = bronzo; rame + zinco = ottone."),
            Question("Materiali", "Le ghise hanno rispetto agli acciai:", listOf("Menù carbonio", "Più carbonio", "Più nichel", "Meno ferro"), 1, "Maggior tenore di carbonio."),
            Question("Lavorazioni", "Per fare un albero cilindrico si usa:", listOf("La fresatura", "La tornitura", "La saldatura", "La piegatura"), 1, "Il tornio crea superfici cilindriche."),
            Question("Lavorazioni", "Il calibro è uno strumento di:", listOf("Misura", "Taglio", "Riscaldamento", "Pressione"), 0, "Misura delle dimensioni."),
            Question("Materiali", "Il rame è usato nei fili elettrici perché:", listOf("È leggero", "È un ottimo conduttore", "È economico", "È magnetico"), 1, "Ottima conducibilità elettrica."),
        ),
    ),

    // =========================================================================
    // CHIMICA MATERIALI BIOTECNOLOGIE — CHIMICA ANALITICA
    // =========================================================================
    Subject(
        id = "chimica-analitica", name = "Chimica analitica e strumentale", emoji = "🔬",
        indirizzo = "Chimica, Materiali e Biotecnologie", colorHex = 0xFF00897B,
        chapters = listOf(
            Chapter(0, "L'analisi chimica", "🧪",
                "Campionamento, concentrazioni e analisi volumetriche.",
                listOf(
                    Section("Campionamento e preparazione", listOf(
                        Block.Text("L'analisi inizia dal campione: rappresentativo, omogeneo e conservato correttamente."),
                        Block.Text("Le concentrazioni si esprimono in molarità (mol/L) o percentuali; i calcoli stechiometrici collegano quantità e reazioni."),
                        Block.Key("Un campione non rappresentativo rende inutile qualsiasi analisi."),
                    )),
                    Section("Analisi volumetriche", listOf(
                        Block.Text("La titolazione misura la concentrazione di un analita facendolo reagire con una soluzione a concentrazione nota."),
                        Block.Text("Si usa la buretta per aggiungere il titolante; il punto finale si vede con un indicatore che cambia colore."),
                        Block.Key("Titolazione = concentrazione ignota trovata con una soluzione nota."),
                    )),
                )),
            Chapter(0, "Strumentazione analitica", "📊",
                "Spettrofotometria, pH-metria e cromatografia.",
                listOf(
                    Section("Strumenti di laboratorio", listOf(
                        Block.Text("Il pH-metro misura il pH di una soluzione con un elettrodo."),
                        Block.Text("Lo spettrofotometro misura quanto una soluzione assorbe la luce: da lì si risale alla concentrazione."),
                        Block.Key("Assorbanza ∝ concentrazione: legge di Lambert-Beer."),
                    )),
                    Section("Cromatografia", listOf(
                        Block.Text("La cromatografia separa i componenti di una miscela: quelli che avanzano più veloci escono prima."),
                        Block.Bullet("GC: gas cromatografia per sostanze volatili."),
                        Block.Bullet("HPLC: cromatografia liquida ad alta pressione."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Analita", "La sostanza che si vuole quantificare nell'analisi."),
            GlossaryTerm("Molarità", "Moli di soluto per litro di soluzione (mol/L)."),
            GlossaryTerm("Titolazione", "Analisi volumetrica che usa una soluzione a concentrazione nota."),
            GlossaryTerm("pH-metro", "Strumento che misura l'acidità di una soluzione."),
            GlossaryTerm("Spettrofotometria", "Misura dell'assorbimento della luce per risalire alla concentrazione."),
            GlossaryTerm("Cromatografia", "Tecnica che separa i componenti di una miscela."),
        ),
        flashcards = listOf(
            Flashcard("Analisi", "Che cos'è l'analita?", "La sostanza da quantificare."),
            Flashcard("Analisi", "Cosa misura la titolazione?", "La concentrazione di un analita con una soluzione nota."),
            Flashcard("Strumenti", "A cosa serve il pH-metro?", "A misurare l'acidità."),
            Flashcard("Strumenti", "La cromatografia serve a…", "Separare i componenti di una miscela."),
            Flashcard("Strumenti", "La legge di Lambert-Beer lega…", "Assorbanza e concentrazione."),
        ),
        questions = listOf(
            Question("Analisi", "La molarità si esprime in:", listOf("mol/L", "g/L", "kg/m³", "mol"), 0, "Moli per litro di soluzione."),
            Question("Analisi", "Nella titolazione la soluzione a concentrazione nota si chiama…", listOf("Analita", "Titolante", "Solvente", "Indicatore"), 1, "Il titolante."),
            Question("Strumenti", "L'indicatore di pH serve a:", listOf("Misurare la massa", "Vedere il punto finale con il colore", "Scaldare", "Filtrare"), 1, "Cambia colore al punto finale."),
            Question("Strumenti", "La cromatografia separa:", listOf("I componenti di una miscela", "Gli isotopi", "Le cariche", "I denti"), 0, "Separa i componenti mescolati."),
            Question("Strumenti", "lo strumento che misura l'assorbimento della luce è…", listOf("Il pH-metro", "Lo spettrofotometro", "La buretta", "La bilancia"), 1, "Lo spettrofotometro."),
        ),
    ),

    // =========================================================================
    // CHIMICA — CHIMICA ORGANICA E BIOCHIMICA
    // =========================================================================
    Subject(
        id = "chimica-organica", name = "Chimica organica e biochimica", emoji = "🧫",
        indirizzo = "Chimica, Materiali e Biotecnologie", colorHex = 0xFF2E7D32,
        chapters = listOf(
            Chapter(0, "Gli idrocarburi", "⛽",
                "Alcani, alcheni e composti aromatici.",
                listOf(
                    Section("Alcani e alcheni", listOf(
                        Block.Text("Gli idrocarburi sono composti di carbonio e idrogeno. Gli alcani hanno solo legami singoli (metano CH₄, propano); gli alcheni hanno un doppio legame (etene)."),
                        Block.Text("Gli alcani sono saturi e poco reattivi; gli alcheni, con il doppio legame, reagiscono più facilmente."),
                        Block.Key("Alcani: legami singoli (saturi); alcheni: un doppio legame (insaturi)."),
                    )),
                    Section("Aromatici", listOf(
                        Block.Text("Il benzene è il capostipite degli aromatici: un anello di 6 atomi di carbonio molto stabile."),
                        Block.Text("Benzene e derivati (toluene, stirene) sono la base di plastiche, medicinali e coloranti."),
                        Block.Key("L'anello benzenico è stabile e presente in molti prodotti industriali."),
                    )),
                )),
            Chapter(0, "I gruppi funzionali", "⚗️",
                "Alcoli, acidi e polimeri: la chimica delle sostanze di ogni giorno.",
                listOf(
                    Section("Alcoli e acidi carbossilici", listOf(
                        Block.Text("Il gruppo -OH (-olo) caratterizza gli alcoli: metanolo, etanolo (l'alcool delle bevande)."),
                        Block.Text("Il gruppo -COOH (-oico) caratterizza gli acidi carbossilici: acido acetico (aceto), acido citrico (agrumi)."),
                        Block.Key("Alcol = gruppo -OH; acido carbossilico = gruppo -COOH."),
                    )),
                    Section("I polimeri", listOf(
                        Block.Text("I polimeri sono grandi molecole formate da unità ripetute (monomeri)."),
                        Block.Bullet("Plastiche: polietilene, PVC, PET."),
                        Block.Bullet("Naturali: amido, cellulosa, proteine, DNA."),
                        Block.Key("Monomero + monomero + … = polimero."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Idrocarburo", "Composto formato solo da carbonio e idrogeno."),
            GlossaryTerm("Alcano", "Idrocarburo saturo con legami singoli (metano, propano)."),
            GlossaryTerm("Alchene", "Idrocarburo insaturo con un doppio legame (etene)."),
            GlossaryTerm("Benzene", "Idrocarburo aromatico a forma di anello, molto stabile."),
            GlossaryTerm("Gruppo funzionale", "Gruppo di atomi che dà proprietà caratteristiche (es. -OH, -COOH)."),
            GlossaryTerm("Polimero", "Grande molecola formata da tante unità ripetute."),
        ),
        flashcards = listOf(
            Flashcard("Idrocarburi", "Gli alcani hanno legami…", "Singoli (saturi)."),
            Flashcard("Idrocarburi", "Il composto aromatico più semplice è…", "Il benzene."),
            Flashcard("Funzionali", "Quale gruppo caratterizza gli alcoli?", "-OH."),
            Flashcard("Funzionali", "Da cosa è formato un polimero?", "Da tante unità ripetute (monomeri)."),
            Flashcard("Funzionali", "L'aceto contiene…", "Acido acetico."),
        ),
        questions = listOf(
            Question("Idrocarburi", "La formula del metano è…", listOf("CH₄", "CO₂", "C₂H₆", "C₆H₆"), 0, "CH₄."),
            Question("Idrocarburi", "Un alchene è caratterizzato da…", listOf("Un doppio legame", "Un legame singolo", "Un ossigeno", "Una carica"), 0, "Il doppio legame lo rende insaturo."),
            Question("Funzionali", "L'etanolo è un…", listOf("Acido", "Alcol", "Alchene", "Polimero"), 1, "Il gruppo -OH lo rende un alcol."),
            Question("Funzionali", "DNA, amido e proteine sono…", listOf("Monomeri", "Polimeri naturali", "Alcani", "Minerali"), 1, "Polimeri naturali."),
            Question("Idrocarburi", "Il benzene è un…", listOf("Alcano", "Alchene", "Aromatico", "Alcol"), 2, "Il capostipite degli aromatici."),
        ),
    ),

    // =========================================================================
    // CHIMICA — BIOTECNOLOGIE
    // =========================================================================
    Subject(
        id = "biotecnologie", name = "Biotecnologie e tecnologie chimiche", emoji = "🧬",
        indirizzo = "Chimica, Materiali e Biotecnologie", colorHex = 0xFF4527A0,
        chapters = listOf(
            Chapter(0, "Dal DNA all'ingegneria genetica", "🧬",
                "Struttura del DNA, enzimi e DNA ricombinante.",
                listOf(
                    Section("Il DNA", listOf(
                        Block.Text("Il DNA è una doppia elica formata da nucleotidi (base, zucchero, fosfato); le basi sono A-T e C-G."),
                        Block.Text("Il gene è un tratto di DNA che contiene l'istruzione per una proteina."),
                        Block.Key("Le basi si appaiano sempre: A con T, C con G."),
                    )),
                    Section("DNA ricombinante", listOf(
                        Block.Text("Gli enzimi di restrizione tagliano il DNA in punti precisi; con i plasmidi (anelli di DNA) si possono inserire geni in organismi."),
                        Block.Text("La PCR amplifica un tratto di DNA: copie in quantità enorme a partire da pochissimo materiale."),
                        Block.Key("Enzimi di restrizione + plasmidi + ligasi = DNA ricombinante."),
                    )),
                )),
            Chapter(0, "Le applicazioni", "💊",
                "Dai farmaci all'agricoltura: dove si usano le biotecnologie.",
                listOf(
                    Section("Salute e farmaci", listOf(
                        Block.Text("L'insulina umana è prodotta da batteri modificati: applicazione storica delle biotecnologie."),
                        Block.Text("Le biotecnologie aiutano a produrre vaccini, anticorpi e farmaci più sicuri e mirati."),
                    )),
                    Section("Agricoltura e industria", listOf(
                        Block.Bullet("Organismi transgenici (OGM): piante resistenti ai parassiti."),
                        Block.Bullet("Biotecnologie ambientali: batteri che degradano il petrolio."),
                        Block.Bullet("Lieviti e microrganismi in industrie alimentari e chimiche."),
                        Block.Key("Le biotecnologie toccano salute, agricoltura, ambiente e industria."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Gene", "Tratto di DNA con l'istruzione per una proteina."),
            GlossaryTerm("Nucleotide", "Unità del DNA: base + zucchero + fosfato."),
            GlossaryTerm("Enzima di restrizione", "Enzima che taglia il DNA in punti specifici."),
            GlossaryTerm("Plasmide", "Piccolo anello di DNA usato per trasportare geni."),
            GlossaryTerm("PCR", "Tecnica che amplifica rapidamente un tratto di DNA."),
            GlossaryTerm("Organismo transgenico", "Organismo che contiene geni di un'altra specie."),
        ),
        flashcards = listOf(
            Flashcard("DNA", "Come si appaiano le basi del DNA?", "A-T e C-G."),
            Flashcard("DNA", "Che cos'è un gene?", "Un tratto di DNA che istruisce una proteina."),
            Flashcard("DNA", "A che serve la PCR?", "A fare milioni di copie di un tratto di DNA."),
            Flashcard("Applicazioni", "L'insulina umana si produce con…", "Batteri modificati geneticamente."),
            Flashcard("DNA", "Gli enzimi di restrizione…", "Tagliano il DNA in punti precisi."),
        ),
        questions = listOf(
            Question("DNA", "La base complementare della citosina (C) è…", listOf("Adenina (A)", "Guanina (G)", "Timina (T)", "Uracile (U)"), 1, "C-G."),
            Question("DNA", "Il plasmide è…", listOf("Un enzima", "Un anello di DNA", "Una proteina", "Uno zucchero"), 1, "Piccolo anello di DNA vettore."),
            Question("Applicazioni", "Gli OGM sono organismi…", listOf("Con geni di altre specie", "Senza DNA", "Estinti", "Solo marini"), 0, "Transgenici."),
            Question("DNA", "Già 3 miliardi di basi: l'analisi del DNA per l'identificazione usa…", listOf("La PCR", "Il termometro", "Il calibro", "La centrifuga per il DNA"), 0, "La PCR amplifica il DNA da analizzare."),
            Question("Applicazioni", "I batteri che degradano il petrolio servono…", listOf("All'agricoltura", "Alle biotecnologie ambientali", "Alla moda", "Alla grafica"), 1, "Biorisanamento ambientale."),
        ),
    ),

    // =========================================================================
    // AGRARIA — PRODUZIONI VEGETALI E ANIMALI
    // =========================================================================
    Subject(
        id = "produzioni-agrarie", name = "Produzioni vegetali e animali", emoji = "🌾",
        indirizzo = "Agraria, Agroalimentare e Agroindustria", colorHex = 0xFF558B2F,
        chapters = listOf(
            Chapter(0, "Il suolo e le coltivazioni", "🌱",
                "Dal suolo alla pianta: agronomia di base.",
                listOf(
                    Section("Il suolo", listOf(
                        Block.Text("Il suolo è formato da minerali, sostanza organica, acqua e aria; la sua tessitura (argilla, sabbia, limo) ne determina fertilità e lavorabilità."),
                        Block.Text("La concimazione restituisce gli elementi tolti dalle colture: azoto (foglie), fosforo (radici e fiori), potassio (frutti)."),
                        Block.Key("NPK = azoto, fosforo, potassio: i tre macroelementi delle piante."),
                    )),
                    Section("Tecniche colturali", listOf(
                        Block.Bullet("Rotazione: alternare colture per non impoverire il suolo."),
                        Block.Bullet("Irrigazione: apportare acqua nei momenti giusti (goccia a goccia, aspersione)."),
                        Block.Bullet("Lavorazioni: aratura ed erpicatura preparano il terreno alla semina."),
                        Block.Key("La rotazione delle colture riduce parassiti e impoverimento del suolo."),
                    )),
                )),
            Chapter(0, "Zootecnia", "🐄",
                "L'allevamento e la produzione animale.",
                listOf(
                    Section("Allevamenti", listOf(
                        Block.Text("La zootecnia alleva animali per latte, carne, uova e lana: bovini, suini, ovini, avicoli."),
                        Block.Text("Il benessere animale (spazi, luce, alimentazione corretta) migliora salute e qualità dei prodotti."),
                    )),
                    Section("Alimentazione", listOf(
                        Block.Text("La razione deve bilanciare energia, proteine, minerali e vitamine in base alla produzione richiesta."),
                        Block.Bullet("Foraggi: erba e fieno."),
                        Block.Bullet("Concentrati: cereali e mangimi ricchi di energia."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Suolo", "Strato superficiale formato da minerali, sostanza organica, acqua e aria."),
            GlossaryTerm("Rotazione", "Alternare colture diverse sullo stesso terreno per preservarlo."),
            GlossaryTerm("NPK", "Azoto, fosforo e potassio: macroelementi nutritivi delle piante."),
            GlossaryTerm("Zootecnia", "Allevamento degli animali per prodotti alimentari."),
            GlossaryTerm("Foraggio", "Alimento verde o secco per gli animali (erba, fieno)."),
            GlossaryTerm("Benessere animale", "Condizioni di vita che tutelano la salute degli animali allevati."),
        ),
        flashcards = listOf(
            Flashcard("Suolo", "Cosa indica la tessitura del suolo?", "La proporzione di argilla, sabbia e limo."),
            Flashcard("Suolo", "A quale elemento serve l'azoto?", "Allo sviluppo delle foglie."),
            Flashcard("Colture", "Perché si fa la rotazione?", "Per non impoverire il suolo e ridurre i parassiti."),
            Flashcard("Zootecnia", "Cosa sono i foraggi?", "Alimenti verdi o secchi come erba e fieno."),
            Flashcard("Zootecnia", "Cosa produce l'allevamento avicolo?", "Uova e carne di pollame."),
        ),
        questions = listOf(
            Question("Suolo", "La concimazione azotata favorisce…", listOf("Le radici", "Le foglie", "I frutti", "I fiori"), 1, "L'azoto è importante per le parti verdi."),
            Question("Colture", "L'irrigazione a goccia…", listOf("Spreca acqua", "Risparmia acqua", "Non serve", "È solo per gli orti"), 1, "Distribuisce acqua solo alle radici, con risparmio."),
            Question("Zootecnia", "La razione alimentare deve contenere…", listOf("Solo acqua", "Energia, proteine e minerali", "Solo foraggio", "Solo cereali"), 1, "Bilanciata in energia, proteine e minerali."),
            Question("Suolo", "La parte viva e fertile del suolo più superficiale è…", listOf("La roccia", "L'orizzonte organico (humus)", "L'acqua", "L'argilla"), 1, "Lo strato ricco di sostanza organica."),
            Question("Zootecnia", "Il benessere animale serve a…", listOf("Aumentare lo stress", "Migliorare salute e qualità", "Ridurre i costi sempre", "Niente"), 1, "Animali sani = prodotti migliori."),
        ),
    ),

    // =========================================================================
    // AGRARIA — TRASFORMAZIONE DEI PRODOTTI (COMPATTA)
    // =========================================================================
    Subject(
        id = "trasformazione-prodotti", name = "Trasformazione dei prodotti agroalimentari", emoji = "🥫",
        indirizzo = "Agraria, Agroalimentare e Agroindustria", colorHex = 0xFFEF6C00,
        chapters = listOf(
            Chapter(0, "Dalla materia prima al prodotto", "🏭",
                "Conservazione, trasformazione e sicurezza alimentare.",
                listOf(
                    Section("Conservazione", listOf(
                        Block.Text("La trasformazione conserva e valorizza i prodotti: pastorizzazione del latte, sterilizzazione delle conserve, essiccazione, surgelazione."),
                        Block.Text("I metodi bloccano i microrganismi: il calore li uccide, il freddo rallenta la loro crescita."),
                        Block.Key("Calore = uccide; freddo = rallenta; zucchero e sale = sottraggono acqua."),
                    )),
                    Section("Qualità e sicurezza", listOf(
                        Block.Text("Il HACCP è il sistema di autocontrollo che garantisce la sicurezza alimentare in tutta la filiera."),
                        Block.Text("Etichetta e tracciabilità informano il consumatore su provenienza e contenuti."),
                        Block.Key("HACCP = piano di controllo dei rischi alimentari, obbligatorio per gli operatori."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Pastorizzazione", "Riscaldamento breve a ~72°C che riduce i microrganismi."),
            GlossaryTerm("Sterilizzazione", "Trattamento ad alta temperatura che elimina quasi tutti i microrganismi."),
            GlossaryTerm("Surgelazione", "Congelamento rapido che conserva qualità e nutrienti."),
            GlossaryTerm("HACCP", "Sistema di autocontrollo della sicurezza alimentare."),
            GlossaryTerm("Tracciabilità", "Possibilità di seguire il prodotto lungo tutta la filiera."),
        ),
        flashcards = listOf(
            Flashcard("Conservazione", "A quale temperatura avviene la pastorizzazione?", "~72°C per poco tempo."),
            Flashcard("Conservazione", "Perché il freddo conserva gli alimenti?", "Rallenta la crescita dei microrganismi."),
            Flashcard("Sicurezza", "Che cos'è l'HACCP?", "Il sistema di autocontrollo della sicurezza alimentare."),
            Flashcard("Sicurezza", "La tracciabilità permette di…", "Seguire il prodotto lungo tutta la filiera."),
        ),
        questions = listOf(
            Question("Conservazione", "La sterilizzazione differisce dalla pastorizzazione perché…", listOf("Usa temperature più alte", "Usa il freddo", "Non cambia nulla", "Usa il sale"), 0, "Temperatura e tempi maggiori, elimina quasi tutti i microrganismi."),
            Question("Conservazione", "Lo zucchero e il sale conservano perché…", listOf("Uccidono i batteri col calore", "Sottraggono acqua ai microrganismi", "Aggiungono vitamine", "Creano il vuoto"), 1, "Riducono l'acqua disponibile."),
            Question("Sicurezza", "L'HACCP è…", listOf("Un alimento", "Un sistema di autocontrollo", "Una macchina", "Un'etichetta"), 1, "Il piano di controllo dei rischi."),
            Question("Conservazione", "Un alimento «a lunga scadenza» in latta è stato…", listOf("Surgelato", "Sterilizzato", "Salato", "Essiccato"), 1, "La sterilizzazione permette lunga conservazione."),
        ),
    ),

    // =========================================================================
    // AGRARIA — ECONOMIA E ESTIMO AGRARIO (COMPATTA)
    // =========================================================================
    Subject(
        id = "economia-agraria", name = "Economia, estimo e marketing agroalimentare", emoji = "🚜",
        indirizzo = "Agraria, Agroalimentare e Agroindustria", colorHex = 0xFF33691E,
        chapters = listOf(
            Chapter(0, "L'impresa agricola", "🌿",
                "Costi, ricavi e la valutazione dei fondi.",
                listOf(
                    Section("L'economia della fattoria", listOf(
                        Block.Text("L'azienda agricola combina terra, lavoro e capitale: i costi sono fissi (macchine, impianti) e variabili (sementi, mangimi)."),
                        Block.Text("Il reddito dell'impresa = ricavi - costi; contano anche i contributi della Politica Agricola Comune (PAC)."),
                    )),
                    Section("L'estimo", listOf(
                        Block.Text("L'estimo stima il valore di beni immobili e fondi: si usa il valore di mercato, il valore di capitalizzazione e il costo di produzione."),
                        Block.Text("La capitalizzazione: valore = reddito annuo / saggio di interesse."),
                        Block.Key("Valore di capitalizzazione = reddito / saggio (V = R/r)."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Estimo", "Disciplina che stima il valore dei beni economici (fondi, immobili)."),
            GlossaryTerm("Capitalizzazione", "Valore = reddito annuo diviso il saggio di interesse."),
            GlossaryTerm("PAC", "Politica agricola comune: sostegni europei all'agricoltura."),
            GlossaryTerm("Costo fisso", "Costo che non cambia con la produzione (macchine, impianti)."),
            GlossaryTerm("Marketing agroalimentare", "Strategie per valorizzare e vendere i prodotti agricoli."),
        ),
        flashcards = listOf(
            Flashcard("Economia", "Cosa sono i costi fissi?", "Costi indipendenti dalla produzione (macchine, impianti)."),
            Flashcard("Estimo", "Come si capitalizza un reddito?", "V = R/r (reddito su saggio)."),
            Flashcard("Estimo", "Che cos'è l'estimo?", "La stima del valore dei beni economici."),
            Flashcard("Economia", "La PAC sostiene…", "L'agricoltura europea."),
        ),
        questions = listOf(
            Question("Estimo", "La formula della capitalizzazione è…", listOf("V = R/r", "V = R×r", "V = r/R", "V = R+r"), 0, "Valore = reddito / saggio."),
            Question("Economia", "I mangimi per gli animali sono un costo…", listOf("Fisso", "Variabile", "Nullo", "Fiscale"), 1, "Cambia con la produzione."),
            Question("Estimo", "L'estimo valuta soprattutto…", listOf("Le azioni", "Fondi e immobili", "Le monete", "Le macchine senza valore"), 1, "Beni immobili e fondi."),
            Question("Economia", "Il reddito dell'impresa agricola è…", listOf("Ricavi - costi", "Costi - ricavi", "Terra + lavoro", "Solo contributi"), 0, "Ricavi meno costi."),
        ),
    ),

    // =========================================================================
    // CAT — PROGETTAZIONE, COSTRUZIONI E IMPIANTI
    // =========================================================================
    Subject(
        id = "progettazione-cat", name = "Progettazione, costruzioni e impianti", emoji = "🏗️",
        indirizzo = "Costruzioni, Ambiente e Territorio", colorHex = 0xFFAD1457,
        chapters = listOf(
            Chapter(0, "I materiali da costruzione", "🧱",
                "Calcestruzzo, acciaio e legno nelle strutture.",
                listOf(
                    Section("Il calcestruzzo", listOf(
                        Block.Text("Il calcestruzzo = cemento + acqua + aggregati (sabbia e ghiaia): resiste bene a compressione, poco a trazione."),
                        Block.Text("Il calcestruzzo armato aggiunge barre di acciaio: l'acciaio assorbe la trazione."),
                        Block.Key("Solaio a travi: il cemento porta la compressione, l'acciaio la trazione."),
                    )),
                    Section("Elementi strutturali", listOf(
                        Block.Bullet("Fondazione: trasmette i carichi al terreno."),
                        Block.Bullet("Pilastro: elemento verticale che porta i carichi."),
                        Block.Bullet("Trave: elemento orizzontale che scarica sui pilastri."),
                        Block.Key("Carichi → travi → pilastri → fondazioni → terreno."),
                    )),
                )),
            Chapter(0, "Il progetto e gli impianti", "📐",
                "Dallo schizzo al progetto esecutivo, tra norme ed efficienza.",
                listOf(
                    Section("Le fasi del progetto", listOf(
                        Block.Text("Il progetto passa dalle fasi: fattibilità, progetto di fattibilità tecnico-economica, esecutivo."),
                        Block.Text("I disegni: piante, prospetti, sezioni e particolari costruttivi, oggi realizzati con il CAD e il BIM."),
                        Block.Key("BIM = modellazione 3D condivisa dell'edificio e dei suoi dati."),
                    )),
                    Section("Impianti ed efficienza", listOf(
                        Block.Text("Un edificio ha impianti idrico-sanitari, elettrici, termici e di ventilazione: vanno progettati in modo integrato."),
                        Block.Text("L'efficienza energetica (isolamento, infissi, pompe di calore) riduce consumi e inquinamento."),
                        Block.Key("Casa efficiente = meno consumi, più comfort e valore."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Calcestruzzo", "Impasto di cemento, acqua e aggregati: resiste a compressione."),
            GlossaryTerm("Calcestruzzo armato", "Cemento + barre d'acciaio: resiste anche a trazione."),
            GlossaryTerm("Fondazione", "Elemento che trasmette i carichi al terreno."),
            GlossaryTerm("Trave", "Elemento orizzontale portante."),
            GlossaryTerm("BIM", "Modellazione informativa 3D dell'edificio."),
            GlossaryTerm("Capitolato", "Documento che elenca lavori, materiali e regole dell'appalto."),
        ),
        flashcards = listOf(
            Flashcard("Materiali", "Perché armare il calcestruzzo?", "Per resistere anche alla trazione."),
            Flashcard("Materiali", "Qual è il percorso dei carichi in un edificio?", "Travi → pilastri → fondazioni → terreno."),
            Flashcard("Progetto", "Che cos'è il BIM?", "La modellazione 3D informativa dell'edificio."),
            Flashcard("Progetto", "Cosa migliora l'efficienza energetica?", "Isolamento, infissi efficienti, pompe di calore."),
            Flashcard("Materiali", "Il calcestruzzo resiste bene a…", "Compressione."),
        ),
        questions = listOf(
            Question("Materiali", "Il calcestruzzo non armato resiste male a…", listOf("Compressione", "Trazione", "Peso", "Caldo"), 1, "Poca resistenza a trazione: serve l'acciaio."),
            Question("Materiali", "L'elemento verticale che porta i carichi è…", listOf("La trave", "Il pilastro", "La finestra", "Il tetto"), 1, "Il pilastro."),
            Question("Progetto", "I documenti che elencano lavori e materiali si chiamano…", listOf("Capitolato", "Fattura", "Gantt", "Relazione di stima"), 0, "Il capitolato d'appalto."),
            Question("Materiali", "La fondazione serve a…", listOf("Decorare", "Trasmettere i carichi al terreno", "Isolare", "Illuminare"), 1, "Porta i carichi al terreno."),
            Question("Progetto", "Il BIM è…", listOf("Un materiale", "Un modello 3D informativo", "Un impianto", "Un attrezzo"), 1, "Modellazione informativa dell'edificio."),
        ),
    ),

    // =========================================================================
    // CAT — TOPOGRAFIA
    // =========================================================================
    Subject(
        id = "topografia", name = "Topografia", emoji = "🗺️",
        indirizzo = "Costruzioni, Ambiente e Territorio", colorHex = 0xFF00695C,
        chapters = listOf(
            Chapter(0, "Il rilievo topografico", "📡",
                "Strumenti e metodi per misurare il territorio.",
                listOf(
                    Section("Strumenti", listOf(
                        Block.Text("La stazione totale misura angoli e distanze; il GPS/gnss dà coordinate precise; il teodolite misura gli angoli."),
                        Block.Text("Il livello (livella) misura i dislivelli per la livellazione."),
                        Block.Key("Stazione totale = angoli + distanze; livello = dislivelli."),
                    )),
                    Section("Metodi di misura", listOf(
                        Block.Text("Si misurano angoli (azimut, zenit) e distanze: la triangolazione collega punti tramite triangoli."),
                        Block.Bullet("Rilievo planimetrico: coordinate X e Y."),
                        Block.Bullet("Rilievo altimetrico: quote Z."),
                        Block.Key("Un punto è localizzato da coordinate planimetriche e quota."),
                    )),
                )),
            Chapter(0, "Rappresentare il territorio", "🗻",
                "Piani quotati, curve di livello e profili.",
                listOf(
                    Section("Curve di livello", listOf(
                        Block.Text("Le curve di livello uniscono i punti con la stessa quota: curve vicine = pendenza forte."),
                        Block.Text("Il piano quotato riporta le quote dei punti; da esso si disegnano curve e profili."),
                        Block.Key("Curve ravvicinate = pendio ripido; distanti = dolce."),
                    )),
                    Section("Il profilo altimetrico", listOf(
                        Block.Text("Il profilo è il taglio verticale del terreno lungo una direzione: mostra le pendenze del percorso."),
                        Block.Text("Serve per strade, canali e terrapieni, calcolando pendenza e volumi di scavo."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Teodolite", "Strumento che misura gli angoli orizzontali e verticali."),
            GlossaryTerm("Stazione totale", "Strumento che misura angoli e distanze in un unico apparecchio."),
            GlossaryTerm("Livellazione", "Misura dei dislivelli con il livello."),
            GlossaryTerm("Azimut", "Angolo orizzontale misurato dal nord."),
            GlossaryTerm("Curva di livello", "Linea che unisce i punti con la stessa quota."),
            GlossaryTerm("Piano quotato", "Rappresentazione con le quote dei punti del terreno."),
        ),
        flashcards = listOf(
            Flashcard("Rilievo", "Cosa misura la stazione totale?", "Angoli e distanze."),
            Flashcard("Rilievo", "Per cosa serve la livellazione?", "Per misurare i dislivelli."),
            Flashcard("Rappresentazione", "Curve di livello ravvicinate indicano…", "Una pendenza ripida."),
            Flashcard("Rappresentazione", "Che cos'è il profilo altimetrico?", "Il taglio verticale del terreno lungo una direzione."),
            Flashcard("Rilievo", "Differenza tra le coordinate X/Y e la quota?", "XY sono planimetriche, la quota Z è altimetrica."),
        ),
        questions = listOf(
            Question("Rilievo", "Lo strumento che misura solo gli angoli è…", listOf("Il GPS", "Il teodolite", "Il livello", "Il metro"), 1, "Il teodolite."),
            Question("Rilievo", "L'azimut si misura a partire da…", listOf("Il sud", "Il nord", "L'equatore", "Il mare"), 1, "Dal nord."),
            Question("Rappresentazione", "Due curve di livello distanti tra loro indicano…", listOf("Un pendio ripido", "Un pendio dolce", "Un lago", "Una città"), 1, "Poca pendenza."),
            Question("Rilievo", "La quota altimetrica è la coordinata…", listOf("X", "Y", "Z", "W"), 2, "La quota è la Z."),
            Question("Rappresentazione", "Il piano quotato serve a…", listOf("Vendere", "Rappresentare le quote del terreno", "Misurare la pioggia", "Calcolare i prezzi"), 1, "Rappresenta le quote dei punti."),
        ),
    ),

    // =========================================================================
    // CAT — ESTIMO, SICUREZZA E CANTIERE (COMPATTA)
    // =========================================================================
    Subject(
        id = "estimo-cantiere", name = "Estimo, sicurezza e gestione del cantiere", emoji = "🦺",
        indirizzo = "Costruzioni, Ambiente e Territorio", colorHex = 0xFFE65100,
        chapters = listOf(
            Chapter(0, "Estimo e valutazioni", "📊",
                "Valore di mercato e valutazioni immobiliari.",
                listOf(
                    Section("I valori immobiliari", listOf(
                        Block.Text("L'estimo valuta i beni con il valore di mercato, di costo e di capitalizzazione."),
                        Block.Text("L'agenzia delle entrate usa i valori OMI come riferimento per le compravendite e le imposte."),
                        Block.Key("Valore di mercato = prezzo a cui un bene si scambia realmente."),
                    )),
                    Section("Le imposte", listOf(
                        Block.Bullet("IMU: imposta sugli immobili."),
                        Block.Bullet("TARI: tassa sui rifiuti."),
                        Block.Bullet("Imposta di registro: sulla compravendita."),
                    )),
                )),
            Chapter(0, "Il cantiere sicuro", "🦺",
                "Piano di sicurezza, figure e prevenzione.",
                listOf(
                    Section("La sicurezza in cantiere", listOf(
                        Block.Text("Il cantiere richiede il PSC (piano di sicurezza e coordinamento) quando ci sono più imprese."),
                        Block.Text("Il coordinatore per la sicurezza redige il piano; il responsabile dei lavori vigila sull'esecuzione."),
                        Block.Key("Prevenzione: DPI, ponteggi certificati, formazione dei lavoratori."),
                    )),
                    Section("Il cantiere, la direzione", listOf(
                        Block.Text("Il direttore dei lavori controlla che l'opera segua progetto e capitolato."),
                        Block.Text("Il computo metrico stima le quantità e i costi dei lavori."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Valore di mercato", "Prezzo a cui un bene si scambia sul mercato."),
            GlossaryTerm("IMU", "Imposta municipale sugli immobili."),
            GlossaryTerm("PSC", "Piano di sicurezza e coordinamento del cantiere."),
            GlossaryTerm("Direttore dei lavori", "Chi verifica che l'opera segua il progetto."),
            GlossaryTerm("Computo metrico", "Documento che quantifica lavori e costi."),
        ),
        flashcards = listOf(
            Flashcard("Estimo", "Il valore di mercato è…", "Il prezzo reale di scambio del bene."),
            Flashcard("Estimo", "A cosa serve il computo metrico?", "A stimare quantità e costi dei lavori."),
            Flashcard("Cantiere", "Quando serve il PSC?", "Quando in cantiere operano più imprese."),
            Flashcard("Cantiere", "Chi controlla l'opera rispetto al progetto?", "Il direttore dei lavori."),
        ),
        questions = listOf(
            Question("Estimo", "L'IMU è…", listOf("Un'imposta sugli immobili", "Una tassa sui rifiuti", "Un contributo", "Un affitto"), 0, "Imposta municipale sugli immobili."),
            Question("Cantiere", "Il PSC è obbligatorio quando…", listOf("C'è un solo operaio", "Operano più imprese", "Non ci sono rischi", "Il cantiere è piccolissimo"), 1, "Con più imprese serve il coordinamento."),
            Question("Estimo", "Il valore di capitalizzazione si usa per…", listOf("I beni che producono reddito", "Le auto", "I vestiti", "I cibi"), 0, "Per i beni produttivi di reddito."),
            Question("Cantiere", "I DPI sono…", listOf("Dispositivi di protezione individuale", "Documenti", "Macchine", "Muri"), 0, "Casco, guanti, scarpe di sicurezza."),
        ),
    ),

    // =========================================================================
    // SISTEMA MODA — IDEAZIONE E PROGETTAZIONE MODA
    // =========================================================================
    Subject(
        id = "moda", name = "Ideazione, progettazione e industrializzazione moda", emoji = "👗",
        indirizzo = "Sistema Moda", colorHex = 0xFFC2185B,
        chapters = listOf(
            Chapter(0, "Dall'idea al capo", "✂️",
                "Figurino, tessuti e cartamodello.",
                listOf(
                    Section("Il processo creativo", listOf(
                        Block.Text("La collezione nasce dall'idea: moodboard, figurino (disegno del capo) e ricerca di tessuti e colori."),
                        Block.Text("Il capo viene sviluppato con il cartamodello, la prova (prototipo) e la scheda tecnica per la produzione."),
                        Block.Key("Figurino → cartamodello → prototipo → scheda tecnica → produzione."),
                    )),
                    Section("I tessuti", listOf(
                        Block.Bullet("Naturali: cotone, lino, lana, seta."),
                        Block.Bullet("Sintetici: poliestere, nylon (derivati dal petrolio)."),
                        Block.Bullet("Artificiali: viscosa, rayon (da cellulosa)."),
                        Block.Key("La scelta del tessuto determina vestibilità, costo e destinazione del capo."),
                    )),
                )),
            Chapter(0, "Dalla sartoria all'industria", "🏭",
                "Industrializzazione e filiera della moda.",
                listOf(
                    Section("La filiera produttiva", listOf(
                        Block.Text("La filiera: progettazione → taglio → confezione → controllo → distribuzione nei negozi."),
                        Block.Text("L'industrializzazione standardizza misure e processi per produrre in grandi quantità."),
                        Block.Key("Fast fashion = produzione rapida e a basso costo; la moda sostenibile punta a ridurre gli sprechi."),
                    )),
                    Section("Le professioni", listOf(
                        Block.Bullet("Stylist e designer: creano la collezione."),
                        Block.Bullet("Modellista: costruisce i cartamodelli."),
                        Block.Bullet("Buyer: seleziona i prodotti da vendere."),
                        Block.Bullet("Visual merchandiser: allestisce i negozi."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Figurino", "Disegno stilizzato del capo d'abbigliamento."),
            GlossaryTerm("Cartamodello", "Modello in carta da cui si taglia il tessuto."),
            GlossaryTerm("Prototipo", "Primo esemplare del capo per la prova."),
            GlossaryTerm("Scheda tecnica", "Documento con misure e istruzioni per la produzione."),
            GlossaryTerm("Moodboard", "Tavola di ispirazione con colori, tessuti e immagini."),
            GlossaryTerm("Fast fashion", "Moda prodotta in fretta e a basso costo."),
        ),
        flashcards = listOf(
            Flashcard("Processo", "Qual è il percorso dalla creazione alla produzione?", "Figurino → cartamodello → prototipo → scheda tecnica."),
            Flashcard("Processo", "Che cos'è il figurino?", "Il disegno stilizzato del capo."),
            Flashcard("Tessuti", "Il poliestere è una fibra…", "Sintetica (dal petrolio)."),
            Flashcard("Filiera", "A cosa serve la scheda tecnica?", "A dare misure e istruzioni per produrre."),
            Flashcard("Professioni", "Chi costruisce i cartamodelli?", "Il modellista."),
        ),
        questions = listOf(
            Question("Processo", "Il primo passo per una collezione è…", listOf("Il taglio", "L'idea e il figurino", "La vendita", "Il controllo"), 1, "Prima si progetta e si disegna il capo."),
            Question("Tessuti", "La lana è una fibra…", listOf("Sintetica", "Naturale", "Artificiale", "Metallica"), 1, "Naturale di origine animale."),
            Question("Filiera", "La produzione in grandi quantità standardizzate è…", listOf("L'industrializzazione", "L'artigianato", "Il figurino", "La vendita"), 0, "L'industrializzazione del processo."),
            Question("Professioni", "Chi seleziona i prodotti da vendere nei negozi?", listOf("Il modellista", "Il buyer", "Il figurinista", "Il sarto"), 1, "Il buyer sceglie i prodotti da comprare per la vendita."),
            Question("Filiera", "La moda sostenibile punta a…", listOf("Produrre di più", "Ridurre gli sprechi", "Velocizzare", "Costare meno sempre"), 1, "Minore impatto ambientale e sociale."),
        ),
    ),

    // =========================================================================
    // GRAFICA E COMUNICAZIONE — PROGETTAZIONE GRAFICA
    // =========================================================================
    Subject(
        id = "progettazione-grafica", name = "Progettazione grafica", emoji = "🎨",
        indirizzo = "Grafica e Comunicazione", colorHex = 0xFF8E24AA,
        chapters = listOf(
            Chapter(0, "La comunicazione visiva", "🖌️",
                "Colore, tipografia e composizione.",
                listOf(
                    Section("Colore e tipografia", listOf(
                        Block.Text("I colori principali sono i primari (rosso, giallo, blu); la ruota dei colori aiuta a creare armonie (complementari, analoghe)."),
                        Block.Text("La tipografia studia le font: con grazie (serif), senza grazie (sans-serif), display e script; la leggibilità è prioritaria."),
                        Block.Key("Colori caldi avvicinano e attivano; colori freddi allontanano e calmano."),
                    )),
                    Section("Il layout", listOf(
                        Block.Text("Il layout organizza testo e immagini nella pagina: griglia, margini, gerarchia visiva."),
                        Block.Text("La gerarchia guida l'occhio: titolo, sottotitolo, corpo del testo. Il brand è l'insieme di nome, logo, colori e stile."),
                        Block.Key("Una buona gerarchia visiva fa capire subito cosa è importante."),
                    )),
                )),
            Chapter(0, "Strumenti e formati", "💻",
                "Software, immagini bitmap e vettoriali.",
                listOf(
                    Section("Bitmap e vettoriale", listOf(
                        Block.Text("L'immagine bitmap è fatta di pixel: si sfoca ingrandendola (foto, es. JPEG, PNG)."),
                        Block.Text("Il vettoriale usa curve matematiche: si ingrandisce senza perdere qualità (logo, es. SVG)."),
                        Block.Key("Bitmap = pixel; vettoriale = curve ridimensionabili."),
                    )),
                    Section("I software", listOf(
                        Block.Bullet("Photoshop: fotoritocco bitmap."),
                        Block.Bullet("Illustrator: grafica vettoriale."),
                        Block.Bullet("InDesign: impaginazione di libri e riviste."),
                        Block.Key("Il software giusto dipende dal tipo di lavoro: foto, vettoriale o impaginazione."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Tipografia", "Studio delle scritture e delle font."),
            GlossaryTerm("Layout", "Organizzazione di testi e immagini nella pagina."),
            GlossaryTerm("Brand", "Identità di un'azienda: nome, logo, colori e stile."),
            GlossaryTerm("Bitmap", "Immagine composta di pixel."),
            GlossaryTerm("Vettoriale", "Immagine basata su curve matematiche, ingrandibile senza perdita."),
            GlossaryTerm("Gerarchia visiva", "Ordine di importanza con cui l'occhio legge gli elementi."),
        ),
        flashcards = listOf(
            Flashcard("Comunicazione", "Quando si ingrandisce, l'immagine bitmap…", "Si sfoca (perde qualità)."),
            Flashcard("Comunicazione", "Quali sono i colori primari?", "Rosso, giallo, blu."),
            Flashcard("Comunicazione", "Che cos'è il brand?", "L'identità dell'azienda (nome, logo, colori)."),
            Flashcard("Strumenti", "A cosa serve InDesign?", "Alla impaginazione di libri e riviste."),
            Flashcard("Strumenti", "Il logo di solito si crea in…", "Vettoriale (Illustrator)."),
        ),
        questions = listOf(
            Question("Comunicazione", "Un'immagine JPEG è…", listOf("Vettoriale", "Bitmap", "Testuale", "3D"), 1, "Le foto sono bitmap."),
            Question("Comunicazione", "Le font senza grazie si chiamano…", listOf("Serif", "Sans-serif", "Script", "Decorative"), 1, "Sans-serif: senza grazie."),
            Question("Comunicazione", "La griglia serve a…", listOf("Disegnare", "Organizzare il layout", "Scrivere", "Stampare"), 1, "Struttura la pagina."),
            Question("Strumenti", "Photoshop è usato per…", listOf("Il fotoritocco", "Il 3D in moda", "L'impaginazione di libri", "Il montaggio video"), 0, "Fotoritocco bitmap."),
            Question("Comunicazione", "Il colore complementare del rosso è…", listOf("Il giallo", "Il verde", "Il blu", "Il nero"), 1, "Sulla ruota dei colori il complementare del rosso è il verde."),
        ),
    ),

    // =========================================================================
    // GRAFICA — TECNOLOGIE DEI PROCESSI DI PRODUZIONE (COMPATTA)
    // =========================================================================
    Subject(
        id = "tecnologie-grafiche", name = "Tecnologie dei processi di produzione", emoji = "🖨️",
        indirizzo = "Grafica e Comunicazione", colorHex = 0xFF4527A0,
        chapters = listOf(
            Chapter(0, "Dalla pagina al prodotto stampato", "🖨️",
                "Stampa, preparazione e controllo del colore.",
                listOf(
                    Section("I processi di stampa", listOf(
                        Block.Text("I principali processi: offset (grandi tirature), digitale (tirature brevi), flessografia (imballaggi), serigrafia (stampa su tessuti e oggetti)."),
                        Block.Text("La prestampa prepara i file: PDF/X, trappole, sovrastampe e imposizione delle pagine."),
                        Block.Key("Da sempre: prestampa → stampa → finitura (taglio, piega, rilegatura)."),
                    )),
                    Section("Il colore in stampa", listOf(
                        Block.Text("La stampa usa la quadricromia CMYK (ciano, magenta, giallo, nero); lo schermo usa RGB."),
                        Block.Text("La prova colore (soft proof e hard proof) verifica la corrispondenza tra file e stampa."),
                        Block.Key("RGB per lo schermo, CMYK per la stampa: serve la gestione del colore."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Offset", "Processo di stampa per grandi tirature."),
            GlossaryTerm("Quadricromia", "Stampa con i 4 colori CMYK."),
            GlossaryTerm("Prestampa", "Preparazione dei file per la stampa."),
            GlossaryTerm("CMYK", "Ciano, magenta, giallo, nero: colori della stampa."),
            GlossaryTerm("Imposizione", "Disposizione delle pagine sulla lastra o sul foglio."),
        ),
        flashcards = listOf(
            Flashcard("Stampa", "Per tirature brevi quale processo è più adatto?", "La stampa digitale."),
            Flashcard("Stampa", "Quali sono i colori del CMYK?", "Ciano, magenta, giallo, nero."),
            Flashcard("Stampa", "Il monitor usa…", "RGB."),
            Flashcard("Stampa", "La preparazione dei file per la stampa si chiama…", "Prestampa."),
        ),
        questions = listOf(
            Question("Stampa", "L'offset è adatto a…", listOf("Grandi tirature", "Tirature brevi", "Un solo foglio", "Stampe 3D"), 0, "Conviene su grandi quantità."),
            Question("Colore", "Se il file sta per lo schermo usa…", listOf("CMYK", "RGB", "PNG", "PDF"), 1, "Schermo = RGB."),
            Question("Colore", "Nella quadricromia il nero si indica con…", listOf("N", "K", "B", "D"), 1, "K: key (nero)."),
            Question("Stampa", "La finitura del prodotto stampato comprende…", listOf("Taglio, piega, rilegatura", "Solo il colore", "Lo scatto fotografico", "La progettazione"), 0, "Il completamento fisico del prodotto."),
        ),
    ),
)