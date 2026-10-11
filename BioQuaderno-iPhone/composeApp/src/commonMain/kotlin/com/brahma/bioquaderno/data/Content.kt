package com.brahma.bioquaderno.data

/**
 * L'intero database dei contenuti dell'app: teoria, glossario, flashcard e quiz.
 * Tutto offline, in italiano.
 */
object Content {

    // =========================================================================
    // CAPITOLI / TEORIA
    // =========================================================================
    val chapters = listOf(
        Chapter(
            1, "La cellula", "🦠",
            "Le unità fondamentali di tutti gli esseri viventi: struttura, organuli e differenze tra cellule animali e vegetali.",
            listOf(
                Section(
                    "La teoria cellulare", listOf(
                        Block.Text("La cellula è l'unità fondamentale della vita. Tutti gli organismi (batteri, piante, funghi, animali, uomo) sono fatti di una o più cellule."),
                        Block.Text("La teoria cellulare è il principio di base della biologia e si riassume in tre affermazioni."),
                        Block.Bullet("Tutti gli esseri viventi sono formati da una o più cellule."),
                        Block.Bullet("La cellula è l'unità strutturale e funzionale degli organismi."),
                        Block.Bullet("Ogni cellula deriva da un'altra cellula preesistente."),
                        Block.Key("La cellula è la più piccola unità in grado di svolgere tutte le funzioni vitali."),
                    )
                ),
                Section(
                    "Cellule procariote ed eucariote", listOf(
                        Block.Text("In natura esistono due grandi tipi di cellule, che si distinguono soprattutto per la presenza o l'assenza di un nucleo definito."),
                        Block.Text("Le cellule procariote (batteri e archei) sono le più semplici e antiche: il materiale genetico è libero nel citoplasma, senza membrana nucleare, e mancano gli organuli delimitati da membrane."),
                        Block.Text("Le cellule eucariote (animali, vegetali, funghi, protisti) hanno un nucleo ben definito e molti organuli specializzati, ognuno con una funzione precisa."),
                        Block.Bullet("Procariote: senza nucleo, DNA circolare libero, piccola (1-10 µm), parete spesso presente."),
                        Block.Bullet("Eucariote: con nucleo, DNA nel nucleo, organuli membranosi, più grande (10-100 µm)."),
                        Block.Key("La differenza chiave è il nucleo: assente nei procarioti, presente negli eucarioti."),
                    )
                ),
                Section(
                    "La cellula eucariote animale", listOf(
                        Block.Text("La cellula animale è circondata dalla membrana plasmatica e contiene il citoplasma, dove fluttuano gli organuli."),
                        Block.Text("Il nucleo contiene la cromatina (DNA + proteine) ed è il centro di controllo della cellula."),
                        Block.Bullet("Membrana plasmatica: delimita la cellula e regola gli scambi."),
                        Block.Bullet("Ribosomi: sintetizzano le proteine (liberi o su RER)."),
                        Block.Bullet("Reticolo endoplasmatico rugoso (RER): sintesi e trasporto di proteine."),
                        Block.Bullet("Reticolo endoplasmatico liscio (REL): sintesi dei lipidi e detossificazione."),
                        Block.Bullet("Apparato di Golgi: modifica, impacchetta e spedisce le proteine."),
                        Block.Bullet("Mitocondri: sede della respirazione cellulare, producono ATP."),
                        Block.Bullet("Lisosomi: digeriscono sostanze e parti esaurite della cellula."),
                        Block.Bullet("Citoscheletro: dà forma e permette i movimenti interni."),
                        Block.Key("Ogni organulo è una 'fabbrica' specializzata: la cellula è un sistema organizzato e cooperativo."),
                    )
                ),
                Section(
                    "La cellula vegetale", listOf(
                        Block.Text("La cellula vegetale ha tutto quello che ha quella animale, più tre caratteristiche esclusive."),
                        Block.Bullet("Parete cellulare di cellulosa: protegge, sostiene e dà rigidità."),
                        Block.Bullet("Cloroplasti: contengono la clorofilla e svolgono la fotosintesi."),
                        Block.Bullet("Vacuolo centrale: grande, pieno di acqua e sostanze; mantiene il turgore."),
                        Block.Text("Ecco le principali differenze con la cellula animale."),
                        Block.Bullet("Vegetale: parete cellulare, cloroplasti, vacuolo grande, centrioli assenti, riserva di amido."),
                        Block.Bullet("Animale: nessuna parete, lisosomi e centrioli presenti, riserva di glicogeno."),
                    )
                )
            )
        ),

        Chapter(
            2, "Biomolecole", "🧪",
            "Le molecole della vita: acqua, carboidrati, lipidi, proteine, acidi nucleici ed enzimi.",
            listOf(
                Section(
                    "L'acqua e la vita", listOf(
                        Block.Text("L'acqua è la molecola più abbondante negli organismi (60-70% del corpo umano) ed è indispensabile per tutte le reazioni cellulari."),
                        Block.Text("È un solvente eccellente perché è polare: la parte dell'ossigeno è leggermente negativa e quelle dell'idrogeno leggermente positive."),
                        Block.Bullet("Solvente universale: le sostanze si sciolgono sotto forma di ioni o molecole."),
                        Block.Bullet("Coesione e adesione: le molecole si attraggono tra loro e con altre superfici."),
                        Block.Bullet("Elevato calore specifico: assorbe e cede calore lentamente, stabilizzando la temperatura."),
                        Block.Bullet("Il ghiaccio galleggia perché è meno denso dell'acqua liquida."),
                        Block.Key("Le proprietà dell'acqua derivano dai legami a idrogeno tra le sue molecole."),
                    )
                ),
                Section(
                    "Carboidrati (glucidi)", listOf(
                        Block.Text("I carboidrati sono composti di carbonio, idrogeno e ossigeno. Sono la principale fonte di energia immediata."),
                        Block.Text("Sono suddivisi in base alla complessità della molecola."),
                        Block.Bullet("Monosaccaridi: zuccheri semplici (glucosio, fruttosio, galattosio)."),
                        Block.Bullet("Disaccaridi: formati da due monosaccaridi (saccarosio, lattosio, maltosio)."),
                        Block.Bullet("Polisaccaridi: lunghe catene di monosaccaridi (amido, glicogeno, cellulosa)."),
                        Block.Text("Amido (piante) e glicogeno (animali) sono riserve di energia; la cellulosa è un polisaccaride strutturale delle pareti vegetali."),
                        Block.Key("Il glucosio è 'il combustibile' universale delle cellule; il suo metabolismo produce energia (ATP)."),
                    )
                ),
                Section(
                    "Lipidi", listOf(
                        Block.Text("I lipidi sono molecole idrofobe (non si sciolgono in acqua) con funzioni di riserva, isolamento e struttura."),
                        Block.Bullet("Trigliceridi (grassi e oli): principale riserva di energia, isolamento termico."),
                        Block.Bullet("Fosfolipidi: formano il doppio strato delle membrane cellulari."),
                        Block.Bullet("Steroidi: colesterolo (stabilizza le membrane), ormoni sessuali, vitamina D."),
                        Block.Text("I grassi saturi hanno legami singoli e sono solidi a temperatura ambiente; quelli insaturi hanno doppi legami e sono liquidi (oli)."),
                        Block.Key("I fosfolipidi sono la 'mattonella' di cui è fatta la membrana di ogni cellula."),
                    )
                ),
                Section(
                    "Proteine", listOf(
                        Block.Text("Le proteine sono i 'lavoratori' della cellula: molecole grandi formate da catene di amminoacidi legati da legami peptidici."),
                        Block.Text("Esistono 20 tipi di amminoacidi; la sequenza determina la struttura e la funzione della proteina."),
                        Block.Bullet("Struttura primaria: la sequenza di amminoacidi."),
                        Block.Bullet("Struttura secondaria: alfa-elica o foglietto beta (legami a idrogeno)."),
                        Block.Bullet("Struttura terziaria: ripiegamento 3D della catena."),
                        Block.Bullet("Struttura quaternaria: unione di più catene (es. emoglobina)."),
                        Block.Text("Funzioni principali: enzimi (catalizzatori), trasporto (emoglobina), difesa (anticorpi), struttura (cheratina, collagene), movimento (actina, miosina), regolazione (ormoni)."),
                        Block.Key("La funzione di una proteina dipende dalla sua forma tridimensionale."),
                    )
                ),
                Section(
                    "Acidi nucleici e nucleotide", listOf(
                        Block.Text("Gli acidi nucleici (DNA e RNA) immagazzinano e trasmettono l'informazione genetica."),
                        Block.Text("Il nucleotide è l'unità di base: una base azotata + uno zucchero (desossiribosio nel DNA, ribosio nell'RNA) + un gruppo fosfato."),
                        Block.Bullet("DNA: doppia elica, basi A-T e G-C, conserva il patrimonio genetico."),
                        Block.Bullet("RNA: filamento singolo, tre tipi (messaggero, transfer, ribosomiale), coinvolto nella sintesi delle proteine."),
                        Block.Key("DNA conserva l'informazione; RNA la porta ai ribosomi per costruire le proteine."),
                    )
                ),
                Section(
                    "Enzimi", listOf(
                        Block.Text("Gli enzimi sono proteine che funzionano da catalizzatori biologici: accelerano le reazioni senza venire consumati."),
                        Block.Text("Ogni enzima è specifico: riconosce il suo substrato in un punto particolare, il sito attivo, e forma il complesso enzima-substrato."),
                        Block.Text("L'attività enzimatica dipende da temperatura, pH e concentrazione. Se la temperatura è troppo alta l'enzima si denatura e perde la funzione."),
                        Block.Key("Gli enzimi accelerano le reazioni abbassando l'energia di attivazione."),
                    )
                )
            )
        ),

        Chapter(
            3, "Membrana e trasporto", "🚪",
            "Come la cellula delimita il suo spazio e scambia sostanze con l'ambiente: diffusione, osmosi e trasporto attivo.",
            listOf(
                Section(
                    "La struttura della membrana", listOf(
                        Block.Text("La membrana plasmatica avvolge la cellula e funziona da barriera selettiva: lascia entrare e uscire solo certe sostanze."),
                        Block.Text("Secondo il modello a mosaico fluido, la membrana è formata da un doppio strato di fosfolipidi in cui 'galleggiano' proteine e altre molecole."),
                        Block.Bullet("Fosfolipidi: doppio strato, 'code' idrofobe all'interno, 'teste' idrofile all'esterno."),
                        Block.Bullet("Proteine di trasporto: canali e carrier che fanno passare le sostanze."),
                        Block.Bullet("Proteine recettore: ricevono segnali dall'esterno."),
                        Block.Bullet("Colesterolo: rende la membrana più stabile."),
                        Block.Bullet("Glicolipidi e glicoproteine: riconoscimento tra cellule."),
                        Block.Key("La membrana è fluida e semipermeabile: lascia passare selettivamente acqua e piccole molecole."),
                    )
                ),
                Section(
                    "Il trasporto passivo", listOf(
                        Block.Text("Nel trasporto passivo le sostanze si muovono secondo il gradiente di concentrazione (da dove ce ne sono di più a dove ce ne sono di meno), senza consumo di energia."),
                        Block.Bullet("Diffusione semplice: molecole piccole e non polari (ossigeno, CO2) attraversano direttamente il doppio strato."),
                        Block.Bullet("Diffusione facilitata: molecole polari o ioni passano attraverso proteine canale o carrier."),
                        Block.Bullet("Osmosi: diffusione dell'acqua attraverso la membrana semipermeabile."),
                        Block.Text("In un ambiente ipotonico la cellula assorbe acqua; in uno ipertonico la perde; in uno isotonico resta in equilibrio."),
                        Block.Key("Il trasporto passivo non richiede ATP: si muove sempre secondo gradiente."),
                    )
                ),
                Section(
                    "Il trasporto attivo", listOf(
                        Block.Text("Il trasporto attivo sposta le sostanze contro gradiente di concentrazione, quindi richiede energia (ATP)."),
                        Block.Text("Esempio classico: la pompa sodio-potassio espelle ioni Na+ (fuori) e introduce ioni K+ (dentro) contro gradiente, mantenendo il potenziale della cellula."),
                        Block.Text("Le sostanze grandi o gli interi materiali entrano ed escono con la vescicolazione."),
                        Block.Bullet("Endocitosi: la cellula ingloba materiale con una invaginazione della membrana (fagocitosi per i solidi, pinocitosi per i liquidi)."),
                        Block.Bullet("Esocitosi: le vescicole si fondono con la membrana e liberano il contenuto all'esterno."),
                        Block.Key("Trasporto passivo: secondo gradiente, senza energia. Trasporto attivo: contro gradiente, con ATP."),
                    )
                )
            )
        ),

        Chapter(
            4, "Metabolismo", "⚡",
            "Come le cellule ricavano e immagazzinano energia: ATP, respirazione cellulare, fermentazione e fotosintesi.",
            listOf(
                Section(
                    "L'ATP: la moneta energetica", listOf(
                        Block.Text("L'ATP (adenosina trifosfato) è la molecola che trasporta energia all'interno della cellula."),
                        Block.Text("Quando si rompe un legame tra i gruppi fosfato, l'ATP diventa ADP + fosfato e libera energia usata per le reazioni cellulari."),
                        Block.Key("L'ATP è la 'moneta energetica' della cellula: immagazzina e cede energia in modo rapido e controllato."),
                    )
                ),
                Section(
                    "La respirazione cellulare", listOf(
                        Block.Text("La respirazione cellulare ricava energia dal glucosio usando l'ossigeno: è il processo più produttivo (fino a 36-38 ATP per glucosio)."),
                        Block.Text("Equazione complessiva: glucosio + ossigeno → acqua + anidride carbonica + energia (ATP)."),
                        Block.Text("Avviene in tre tappe principali."),
                        Block.Bullet("Glicolisi (citoplasma): il glucosio è scisso in due molecole; si ricavano 2 ATP."),
                        Block.Bullet("Ciclo di Krebs (mitocondrio): completa la demolizione; si libera CO2."),
                        Block.Bullet("Fosforilazione ossidativa (catena di trasporto elettronico): produce la maggior parte dell'ATP con l'ossigeno."),
                        Block.Key("Con l'ossigeno ogni molecola di glucosio rende molto più ATP che senza."),
                    )
                ),
                Section(
                    "La fermentazione", listOf(
                        Block.Text("Senza ossigeno le cellule ricorrono alla fermentazione, che produce solo 2 ATP per glucosio."),
                        Block.Bullet("Fermentazione lattica: nei muscoli durante sforzi intensi; il lattato causa l'affaticamento."),
                        Block.Bullet("Fermentazione alcolica: nei lieviti; produce etanolo e CO2 (pane, birra, vino)."),
                        Block.Key("La fermentazione è la via anaerobica di riserva quando l'ossigeno manca."),
                    )
                ),
                Section(
                    "La fotosintesi", listOf(
                        Block.Text("La fotosintesi è il processo con cui le piante (e altri organismi) trasformano l'energia della luce in energia chimica. È la fonte di energia di quasi tutta la biosfera."),
                        Block.Text("Equazione complessiva: anidride carbonica + acqua + luce → glucosio + ossigeno."),
                        Block.Text("Si svolge nei cloroplasti in due fasi."),
                        Block.Bullet("Fase luce (tilacoidi): la clorofilla cattura la luce e produce ATP e NADPH, liberando ossigeno."),
                        Block.Bullet("Ciclo di Calvin (stroma): usa ATP e NADPH per fissare la CO2 e costruire il glucosio."),
                        Block.Key("La fotosintesi converte la luce in energia chimica e produce l'ossigeno che respiriamo."),
                    )
                ),
                Section(
                    "Autotrofi ed eterotrofi", listOf(
                        Block.Text("Gli autotrofi (piante, alghe, alcuni batteri) producono da soli le proprie molecole organiche grazie alla fotosintesi."),
                        Block.Text("Gli eterotrofi (animali, funghi, molti protisti) devono nutrirsi di altri organismi."),
                        Block.Text("L'energia passa da un livello all'altro della catena alimentare con grosse perdite (solo circa il 10% passa al livello successivo)."),
                        Block.Key("Autotrofi producono, eterotrofi consumano: da qui parte il flusso di energia degli ecosistemi."),
                    )
                )
            )
        ),

        Chapter(
            5, "Genetica", "🧬",
            "Le leggi dell'ereditarietà, la struttura del DNA, la sintesi delle proteine e le mutazioni.",
            listOf(
                Section(
                    "Le leggi di Mendel", listOf(
                        Block.Text("Gregor Mendel (XIX secolo) studiò l'ereditarietà incrociando piante di pisello e formulò le prime leggi dell'eredità."),
                        Block.Bullet("Legge della dominanza: incrociando due linee pure si ottiene la forma dominante; l'altra (recessiva) resta nascosta."),
                        Block.Bullet("Legge della segregazione: ogni carattere è controllato da due alleli che si separano durante la formazione dei gameti."),
                        Block.Bullet("Legge dell'assortimento indipendente: geni diversi si trasmettono indipendentemente."),
                        Block.Text("Termini chiave: fenotipo (carattere osservato), genotipo (corredo genetico), omozigote (due alleli uguali), eterozigote (due alleli diversi)."),
                        Block.Key("Da due genitori eterozigoti per un carattere dominante/recessivo esce il 25% di individui recessivi."),
                    )
                ),
                Section(
                    "La struttura del DNA", listOf(
                        Block.Text("Il DNA è una doppia elica formata da due filamenti di nucleotidi tenuti insieme da legami a idrogeno."),
                        Block.Text("Le basi azotate si appaiano in modo complementare: Adenina con Timina, Guanina con Citosina."),
                        Block.Text("La replicazione è semiconservativa: ogni nuovo filamento usa come stampo uno dei due filamenti originali."),
                        Block.Key("A-T e G-C: la complementarità delle basi è la chiave per copiare e trasmettere l'informazione."),
                    )
                ),
                Section(
                    "Dai geni alle proteine", listOf(
                        Block.Text("L'informazione del DNA viene 'tradotta' in proteine in due tappe: trascrizione e traduzione."),
                        Block.Bullet("Trascrizione: nel nucleo, un tratto di DNA (un gene) viene copiato in un filamento di mRNA."),
                        Block.Bullet("Traduzione: nel citoplasma, i ribosomi leggono l'mRNA a triplette (codoni) e, con l'aiuto dei tRNA, montano gli amminoacidi."),
                        Block.Text("Ogni codone di tre basi corrisponde a un amminoacido: questo codice genetico è universale per quasi tutti gli organismi."),
                        Block.Key("DNA → (trascrizione) → mRNA → (traduzione) → proteina."),
                    )
                ),
                Section(
                    "Mutazioni", listOf(
                        Block.Text("Le mutazioni sono cambiamenti permanenti nella sequenza del DNA. Sono la materia prima dell'evoluzione."),
                        Block.Bullet("Geniche: cambiamento di una o più basi (puntiforme, come la mutazione che provoca l'anemia falciforme)."),
                        Block.Bullet("Cromosomiche: alterazioni nella struttura del numero dei cromosomi (come la trisomia 21)."),
                        Block.Bullet("Genomiche: variazione del numero di cromosomi."),
                        Block.Text("Gli agenti mutageni (radiazioni, sostanze chimiche, virus) aumentano la frequenza delle mutazioni. Gli effetti possono essere neutri, dannosi o raramente benefici."),
                        Block.Key("Le mutazioni casuali forniscono la variabilità su cui agisce la selezione naturale."),
                    )
                ),
                Section(
                    "Ingegneria genetica", listOf(
                        Block.Text("Oggi è possibile leggere e modificare il DNA grazie a tecniche di biologia molecolare."),
                        Block.Bullet("DNA ricombinante: inserire un gene di un organismo in un altro batterio o microrganismo (es. insulina umana prodotta da batteri)."),
                        Block.Bullet("OGM: organismi il cui genoma è stato modificato (es. mais resistente, pomodoro a maturazione lenta)."),
                        Block.Bullet("CRISPR-Cas9: 'forbici molecolari' che tagliano il DNA in punti precisi, rivoluzione della terapia genica."),
                        Block.Bullet("Terapia genica: correggere geni difettosi per curare malattie ereditarie."),
                    )
                )
            )
        ),

        Chapter(
            6, "Riproduzione e ciclo cellulare", "🔁",
            "Come le cellule si dividono: il ciclo cellulare, la mitosi, la meiosi e i tipi di riproduzione.",
            listOf(
                Section(
                    "Il ciclo cellulare", listOf(
                        Block.Text("Il ciclo cellulare è la sequenza di eventi che porta una cellula a dividersi in due cellule figlie."),
                        Block.Text("Si compone di due fasi principali: l'interfase (preparazione) e la fase M (divisione)."),
                        Block.Bullet("Interfase G1: la cellula cresce e svolge le sue funzioni."),
                        Block.Bullet("Interfase S: il DNA viene duplicato."),
                        Block.Bullet("Interfase G2: la cellula si prepara alla divisione."),
                        Block.Bullet("Fase M: mitosi (divisione del nucleo) + citodieresi (divisione del citoplasma)."),
                        Block.Key("Prima di dividersi la cellula deve obbligatoriamente duplicare il proprio DNA."),
                    )
                ),
                Section(
                    "La mitosi", listOf(
                        Block.Text("La mitosi produce due cellule figlie geneticamente identiche alla madre (stesso numero di cromosomi). Serve per la crescita e la riparazione dei tessuti."),
                        Block.Bullet("Profase: i cromosomi si condensano; sparisce la membrana nucleare."),
                        Block.Bullet("Metafase: i cromosomi si allineano al centro (piastra equatoriale)."),
                        Block.Bullet("Anafase: i cromatidi si separano e migrano ai poli opposti."),
                        Block.Bullet("Telofase: si riformano i nuclei; la citodieresi completa la divisione."),
                        Block.Key("Mitosi = clonazione cellulare: le due figlie sono identiche alla cellula madre."),
                    )
                ),
                Section(
                    "La meiosi", listOf(
                        Block.Text("La meiosi produce quattro cellule aploidi (gameti: cellule uovo e spermatozoi) con metà corredo cromosomico."),
                        Block.Text("Si compone di due divisioni successive (meiosi I e II). Durante la profase I avviene il crossing-over: i cromosomi omologhi si scambiano pezzi."),
                        Block.Bullet("Meiosi I: separa i cromosomi omologhi (da diploide ad aploide)."),
                        Block.Bullet("Meiosi II: separa i cromatidi (come una mitosi)."),
                        Block.Bullet("Crossing-over: ricombinazione genetica, fonte di variabilità."),
                        Block.Key("La meiosi dimezza il numero di cromosomi e mescola i geni: garantisce la variabilità della prole."),
                    )
                ),
                Section(
                    "Riproduzione asessuata e sessuata", listOf(
                        Block.Text("La riproduzione asessuata produce individui identici al genitore, in modo rapido: scissione (batteri), gemmazione (lieviti, idra), frammentazione (planaria, stelle marine)."),
                        Block.Text("La riproduzione sessuata prevede la fusione di due gameti e mescola il patrimonio genetico dei due genitori."),
                        Block.Key("La riproduzione sessuata genera variabilità genetica, vantaggiosa per adattarsi ai cambiamenti."),
                    )
                )
            )
        ),

        Chapter(
            7, "Anatomia umana", "🫀",
            "Il corpo umano: apparato circolatorio, respiratorio, digerente, nervoso, escretore, muscolo-scheletrico ed endocrino.",
            listOf(
                Section(
                    "Apparato circolatorio", listOf(
                        Block.Text("Trasporta a tutte le cellule ossigeno e nutrienti e rimuove scorie e anidride carbonica."),
                        Block.Text("Il cuore è un muscolo con quattro cavità: due atri e due ventricoli. Il sangue percorre due circuiti."),
                        Block.Bullet("Piccola circolazione: dal cuore ai polmoni (ossigenazione) e ritorno."),
                        Block.Bullet("Grande circolazione: dal cuore a tutto il corpo e ritorno."),
                        Block.Text("Il sangue è formato da plasma (parte liquida), globuli rossi (trasportano ossigeno, emoglobina), globuli bianchi (difesa) e piastrine (coagulazione). Le arterie portano il sangue dal cuore, le vene lo riportano."),
                        Block.Key("Il cuore è una doppia pompa: un circuito polmonare e uno sistemico in serie."),
                    )
                ),
                Section(
                    "Apparato respiratorio", listOf(
                        Block.Text("Permette gli scambi gassosi: ossigeno dall'aria alle cellule, anidride carbonica in direzione opposta."),
                        Block.Text("L'aria percorre: naso → faringe → laringe → trachea → bronchi → polmoni."),
                        Block.Text("Nei polmoni l'aria raggiunge gli alveoli, minuscole sacche circondate da capillari dove avviene lo scambio. La respirazione è mossa dal diaframma e dai muscoli intercostali."),
                        Block.Key("L'ossigeno passa dagli alveoli al sangue per diffusione: la superficie enorme degli alveoli rende lo scambio molto efficiente."),
                    )
                ),
                Section(
                    "Apparato digerente", listOf(
                        Block.Text("Il cibo viene demolito in molecole semplici (nutrienti) che passano nel sangue (assorbimento)."),
                        Block.Bullet("Bocca: masticazione e amilasi (prima digestione degli amidi)."),
                        Block.Bullet("Esofago: trasporta il bolo allo stomaco."),
                        Block.Bullet("Stomaco: acido cloridrico e pepsina demoliscono le proteine."),
                        Block.Bullet("Intestino tenue: avviene la digestione completa e l'assorbimento dei nutrienti (villi)."),
                        Block.Bullet("Intestino crasso: assorbe acqua e forma le feci."),
                        Block.Text("Fegato (produce bile) e pancreas (enzimi e bicarbonato) sono ghiandole accessorie fondamentali."),
                        Block.Key("L'assorbimento dei nutrienti avviene nell'intestino tenue grazie ai villi."),
                    )
                ),
                Section(
                    "Apparato nervoso", listOf(
                        Block.Text("Raccoglie informazioni, le elabora e coordina le risposte del corpo. L'unità è il neurone."),
                        Block.Text("I neuroni comunicano tramite impulsi nervosi; il passaggio tra un neurone e l'altro avviene nella sinapsi con neurotrasmettitori."),
                        Block.Bullet("Sistema nervoso centrale (SNC): encefalo e midollo spinale, centri di elaborazione."),
                        Block.Bullet("Sistema nervoso periferico (SNP): nervi che collegano SNC a organi e muscoli."),
                        Block.Bullet("Arco riflesso: risposta rapida e involontaria che passa dal midollo."),
                        Block.Key("L'encefalo è il centro di controllo; il midollo gestisce i riflessi rapidi."),
                    )
                ),
                Section(
                    "Apparato escretore", listOf(
                        Block.Text("Filtra il sangue e allontana i prodotti di scarto mantenendo costante l'ambiente interno (omeostasi)."),
                        Block.Text("I reni contengono milioni di nefroni dove il sangue viene filtrato: si forma l'urina (acqua, urea, sali)."),
                        Block.Text("Il percorso dell'urina: reni → ureteri → vescica → uretra."),
                        Block.Key("I reni regolano acqua e sali del sangue: mantengono l'equilibrio (omeostasi)."),
                    )
                ),
                Section(
                    "Apparato muscolo-scheletrico", listOf(
                        Block.Text("Formato da ossa (sostegno e protezione), articolazioni e muscoli (movimento)."),
                        Block.Text("I muscoli scheletrici lavorano a coppie di antagonisti: il flessore piega l'arto, l'estensore lo distende."),
                        Block.Text("Il movimento nasce dalla contrazione muscolare, alimentata dall'ATP; i muscoli sono attaccati alle ossa tramite i tendini."),
                        Block.Key("Scheletro = impalcatura; muscoli = motori che la muovono sfruttando leve e articolazioni."),
                    )
                ),
                Section(
                    "Sistema endocrino", listOf(
                        Block.Text("Regola le funzioni del corpo tramite gli ormoni, messaggeri chimici trasportati dal sangue."),
                        Block.Bullet("Ipofisi: 'ghiandola master', controlla le altre ghiandole."),
                        Block.Bullet("Tiroide: regola il metabolismo (tiroxina)."),
                        Block.Bullet("Pancreas: insulina e glucagone regolano la glicemia."),
                        Block.Bullet("Surrenali: adrenalina, risposta allo stress."),
                        Block.Bullet("Ovaie e testicoli: ormoni sessuali."),
                        Block.Key("Il sistema endocrino è una 'rete di segnale' lenta ma duratura: steady gli ormoni coordinano crescita, metabolismo e riproduzione."),
                    )
                )
            )
        ),

        Chapter(
            8, "Ecologia e evoluzione", "🌍",
            "Gli ecosistemi, le catene alimentari e i meccanismi dell'evoluzione con la selezione naturale.",
            listOf(
                Section(
                    "L'ecosistema", listOf(
                        Block.Text("L'ecosistema è l'insieme degli esseri viventi (biocenosi) e dell'ambiente in cui vivono (biotopo) in una data area."),
                        Block.Text("I fattori abiotici (luce, temperatura, acqua, suolo) e quelli biotici (i rapporti tra organismi) interagiscono continuamente."),
                        Block.Text("L'habitat è il luogo fisico in cui vive una specie; la nicchia ecologica è il suo 'mestiere' (come e di cosa si nutre, dove vive)."),
                        Block.Key("Un ecosistema è un sistema aperto: materia e flussi di energia entrano ed escono continuamente."),
                    )
                ),
                Section(
                    "Catene e reti alimentari", listOf(
                        Block.Text("L'energia scorre tra gli organismi attraverso i rapporti alimentari."),
                        Block.Bullet("Produttori: piante, alghe (autotrofi), base della catena."),
                        Block.Bullet("Consumatori primari: erbivori."),
                        Block.Bullet("Consumatori secondari e terziari: carnivori."),
                        Block.Bullet("Decompositori: batteri e funghi che riciclano la materia morta."),
                        Block.Text("Una rete alimentare è l'insieme di più catene intrecciate. A ogni passaggio si perde circa il 90% dell'energia (piramide ecologica)."),
                        Block.Key("Senza decompositori gli ecosistemi si 'bloccherebbero': sono i riciclatori della materia."),
                    )
                ),
                Section(
                    "La selezione naturale", listOf(
                        Block.Text("Charles Darwin, dopo il viaggio sul brigantino Beagle (con i famosi fringuelli delle Galápagos), propose la teoria dell'evoluzione per selezione naturale."),
                        Block.Text("Le sue basi sono semplici e potenti."),
                        Block.Bullet("Gli individui di una specie sono diversi tra loro (variabilità)."),
                        Block.Bullet("Gli organismi producono più figli di quanti possano sopravvivere (sovrabbondanza)."),
                        Block.Bullet("Chi ha i caratteri più adatti all'ambiente ha più probabilità di sopravvivere e riprodursi."),
                        Block.Bullet("I caratteri vantaggiosi si diffondono nelle generazioni successive."),
                        Block.Key("Selezione naturale = sopravvivenza e riproduzione differenziale degli individui più adatti all'ambiente."),
                    )
                ),
                Section(
                    "Specie, speciazione e prove dell'evoluzione", listOf(
                        Block.Text("Una specie è un gruppo di organismi che possono incrociarsi tra loro e generare prole fertile."),
                        Block.Text("La speciazione è la formazione di una nuova specie, tipicamente quando una popolazione viene isolata (barriera geografica) e accumula differenze."),
                        Block.Text("Le prove dell'evoluzione arrivano da più fronti."),
                        Block.Bullet("Fossili: documentano i cambiamenti nel tempo."),
                        Block.Bullet("Anatomia comparata: strutture omologhe con origini comuni (arto di uomo, balena, pipistrello)."),
                        Block.Bullet("Biologia molecolare: le sequenze del DNA mostrano parentele."),
                        Block.Bullet("Biogeografia: specie simili in aree vicine o isolate."),
                        Block.Key("L'evoluzione è un fatto documentato; la selezione naturale ne è il meccanismo principale."),
                    )
                ),
                Section(
                    "Biodiversità e conservazione", listOf(
                        Block.Text("La biodiversità (varietà di specie, geni ed ecosistemi) è la più grande ricchezza del pianeta e la base della sua stabilità."),
                        Block.Text("Le principali minacce sono la distruzione degli habitat, l'inquinamento, il cambiamento climatico e l'introduzione di specie invasive."),
                        Block.Text("La conservazione (aree protette, piani di gestione, educazione) mira a preservare gli ecosistemi e i loro servizi per le generazioni future."),
                        Block.Key("Ogni specie perduta è un patrimonio genetico unico che non potrà mai essere ricreato."),
                    )
                )
            )
        )
    )

    // =========================================================================
    // GLOSSARIO
    // =========================================================================
    val glossary = listOf(
        GlossaryTerm("Adenosina trifosfato (ATP)", "Molecola che immagazzina e fornisce energia alle reazioni cellulari; perde un fosfato diventando ADP e liberando energia."),
        GlossaryTerm("Alveoli", "Minuscole sacche dei polmoni, circondate da capillari, dove avviene lo scambio di ossigeno e anidride carbonica."),
        GlossaryTerm("Amminoacidi", "I 20 'mattoni' che formano le proteine, uniti da legami peptidici."),
        GlossaryTerm("Anafase", "Fase della mitosi in cui i cromatidi gemelli si separano e migrano verso i poli della cellula."),
        GlossaryTerm("Antigene e anticorpo", "L'antigene è una molecola riconosciuta come estranea; l'anticorpo è una proteina prodotta per neutralizzarla."),
        GlossaryTerm("Apparato di Golgi", "Organulo che modifica, impacchetta e distribuisce le proteine prodotte dalla cellula."),
        GlossaryTerm("Arco riflesso", "Risposta nervosa rapida e involontaria che passa attraverso il midollo spinale senza coinvolgere l'encefalo."),
        GlossaryTerm("Assortimento indipendente", "Terza legge di Mendel: geni diversi si trasmettono in modo indipendente alla formazione dei gameti."),
        GlossaryTerm("Autotrofi", "Organismi capaci di produrre da soli le proprie molecole organiche (fotosintesi), come piante e alghe."),
        GlossaryTerm("Biodiversità", "La varietà di specie, di geni e di ecosistemi presenti sulla Terra."),
        GlossaryTerm("Bioma", "Grande comunità ecologica caratteristica di una zona climatica (foresta pluviale, deserto, tundra)."),
        GlossaryTerm("Biomolecole", "Le molecole caratteristiche dei viventi: carboidrati, lipidi, proteine, acidi nucleici."),
        GlossaryTerm("Catalizzatore", "Sostanza che accelera una reazione senza essere consumata; gli enzimi sono catalizzatori biologici."),
        GlossaryTerm("Catena alimentare", "Sequenza produttori → consumatori → decompositori attraverso cui passa l'energia in un ecosistema."),
        GlossaryTerm("Cellula", "La più piccola unità strutturale e funzionale degli esseri viventi."),
        GlossaryTerm("Cellulosa", "Polisaccaride strutturale che forma la parete delle cellule vegetali."),
        GlossaryTerm("Chloroplasti (cloroplasti)", "Organuli delle cellule vegetali che contengono la clorofilla e svolgono la fotosintesi."),
        GlossaryTerm("Ciclo di Krebs", "Tappa della respirazione cellulare che si svolge nel mitocondrio e libera anidride carbonica."),
        GlossaryTerm("Ciclo cellulare", "Sequenza di eventi dalla nascita di una cellula alla sua divisione in due cellule figlie."),
        GlossaryTerm("Citodieresi", "Divisione del citoplasma che completa la divisione cellulare dopo la mitosi."),
        GlossaryTerm("Citoscheletro", "Rete di proteine che dà forma alla cellula e permette i movimenti interni."),
        GlossaryTerm("Codone", "Tripletta di basi dell'mRNA che corrisponde a un amminoacido nel codice genetico."),
        GlossaryTerm("Crossing-over", "Scambio di segmenti tra cromosomi omologhi durante la meiosi; fonte di variabilità genetica."),
        GlossaryTerm("Denaturazione", "Perdita della struttura tridimensionale di una proteina (es. per calore), che la rende non funzionante."),
        GlossaryTerm("Diploide", "Cella o individuo con due copie di ogni cromosoma (2n), tipico delle cellule somatiche."),
        GlossaryTerm("DNA", "Acido desossiribonucleico: doppia elica che conserva l'informazione genetica. Basi A-T, G-C."),
        GlossaryTerm("Energia di attivazione", "Energia minima necessaria perché una reazione chimica possa avvenire; gli enzimi la abbassano."),
        GlossaryTerm("Enzima", "Proteina che accelera specificamente una reazione chimica senza essere consumata."),
        GlossaryTerm("Esocitosi", "Processo con cui la cellula espelle sostanze tramite vescicole che si fondono con la membrana."),
        GlossaryTerm("Eterozigote", "Individuo con due alleli diversi per un dato gene (es. Aa)."),
        GlossaryTerm("Eterotrofi", "Organismi che si nutrono di altri organismi (animali, funghi)."),
        GlossaryTerm("Eucariote", "Cellula provvista di nucleo e organuli; tipica di animali, piante, funghi e protisti."),
        GlossaryTerm("Fagocitosi", "Tipo di endocitosi in cui la cellula ingloba particelle solide."),
        GlossaryTerm("Fenotipo", "Il carattere osservabile di un individuo, risultato di genotipo e ambiente."),
        GlossaryTerm("Fermentazione", "Demolizione del glucosio senza ossigeno; produce solo 2 ATP (lattica o alcolica)."),
        GlossaryTerm("Fosfolipidi", "Lipidi che formano il doppio strato della membrana cellulare."),
        GlossaryTerm("Fotosintesi", "Processo con cui piante e alghe trasformano luce, acqua e CO2 in glucosio e ossigeno."),
        GlossaryTerm("Gameti", "Cellule riproduttive aploidi (cellula uovo e spermatozoo)."),
        GlossaryTerm("Geni", "Segmenti di DNA che contengono l'informazione per produrre proteine."),
        GlossaryTerm("Genotipo", "Il corredo genetico di un individuo per un dato carattere."),
        GlossaryTerm("Glicolisi", "Prima tappa della respirazione: nel citoplasma, il glucosio viene scisso con produzione di 2 ATP."),
        GlossaryTerm("Glucosio", "Zucchero semplice (monosaccaride), principale fonte di energia delle cellule."),
        GlossaryTerm("Habitat", "Il luogo fisico in cui una specie vive."),
        GlossaryTerm("Haploide (aploide)", "Cella con una sola copia di ogni cromosoma (n), come i gameti."),
        GlossaryTerm("Ingegneria genetica", "Insieme di tecniche che permettono di modificare il DNA (OGM, CRISPR, terapia genica)."),
        GlossaryTerm("Insulina", "Ormone prodotto dal pancreas che abbassa la glicemia."),
        GlossaryTerm("Interfase", "Fase del ciclo cellulare in cui la cellula cresce e duplica il DNA (G1, S, G2)."),
        GlossaryTerm("Legge della dominanza", "Prima legge di Mendel: incrociando linee pure, nella prima generazione compare solo la forma dominante."),
        GlossaryTerm("Legame a idrogeno", "Attrazione debole che tiene unite le molecole dell'acqua e le due eliche del DNA."),
        GlossaryTerm("Legame peptidico", "Legame che unisce gli amminoacidi in una proteina."),
        GlossaryTerm("Lisosomi", "Organuli che digeriscono sostanze e parti esaurite della cellula."),
        GlossaryTerm("Meiosi", "Divisione che produce quattro gameti aploidi con ricombinazione genetica."),
        GlossaryTerm("Membrana plasmatica", "Doppio strato di fosfolipidi che delimita la cellula e regola gli scambi."),
        GlossaryTerm("Metafase", "Fase della mitosi in cui i cromosomi si allineano al centro della cellula."),
        GlossaryTerm("Mitocondri", "Organuli in cui avviene la respirazione cellulare; producono la maggior parte dell'ATP."),
        GlossaryTerm("Mitosi", "Divisione che produce due cellule identiche alla madre; serve a crescita e riparazione."),
        GlossaryTerm("Mutazione", "Cambiamento permanente della sequenza del DNA; fonte della variabilità genetica."),
        GlossaryTerm("Nefrone", "Unità funzionale del rene dove il sangue viene filtrato."),
        GlossaryTerm("Neurone", "Cellula specializzata del sistema nervoso che trasmette gli impulsi."),
        GlossaryTerm("Nicchia ecologica", "Il 'ruolo' di una specie nell'ecosistema (dove vive, di cosa si nutre, come interagisce)."),
        GlossaryTerm("Nucleotide", "Unità del DNA e dell'RNA: base azotata + zucchero + fosfato."),
        GlossaryTerm("Nucleo", "Organulo eucariote che contiene il DNA."),
        GlossaryTerm("Omeostasi", "La capacità dell'organismo di mantenere costanti le condizioni interne."),
        GlossaryTerm("Osmosi", "Diffusione dell'acqua attraverso una membrana semipermeabile, secondo il gradiente."),
        GlossaryTerm("Parete cellulare", "Rivestimento rigido di cellulosa delle cellule vegetali."),
        GlossaryTerm("Piramide ecologica", "Rappresentazione della perdita di energia (circa 90%) a ogni livello della catena alimentare."),
        GlossaryTerm("Piastrine", "Frammenti cellulari del sangue che intervengono nella coagulazione."),
        GlossaryTerm("Procariote", "Cellula senza nucleo; tipica di batteri e archei."),
        GlossaryTerm("Ribosomi", "Strutture che sintetizzano le proteine a partire dall'mRNA."),
        GlossaryTerm("RNA", "Acido ribonucleico: singolo filamento coinvolto nella sintesi delle proteine (mRNA, tRNA, rRNA)."),
        GlossaryTerm("Segregazione", "Seconda legge di Mendel: gli alleli di un gene si separano nei gameti."),
        GlossaryTerm("Selezione naturale", "Sopravvivenza e riproduzione differenziale degli individui più adatti all'ambiente; meccanismo dell'evoluzione."),
        GlossaryTerm("Sinapsi", "Punto di comunicazione tra due neuroni, mediata da neurotrasmettitori."),
        GlossaryTerm("Speciazione", "Formazione di una nuova specie, spesso per isolamento geografico."),
        GlossaryTerm("Spirale (doppia elica)", "Struttura a scala attorcigliata del DNA, con basi accoppiate A-T e G-C."),
        GlossaryTerm("Trascrizione", "Copia di un gene di DNA in mRNA nel nucleo."),
        GlossaryTerm("Traduzione", "Sintesi della proteina: i ribosomi leggono l'mRNA e assemblano gli amminoacidi."),
        GlossaryTerm("Trasporto attivo", "Movimento di sostanze contro gradiente che richiede ATP (es. pompa sodio-potassio)."),
        GlossaryTerm("Trasporto passivo", "Movimento di sostanze secondo gradiente senza consumo di energia (diffusione, osmosi)."),
        GlossaryTerm("Turgore", "Pressione interna della cellula vegetale dovuta al vacuolo pieno d'acqua.")
    )

    // =========================================================================
    // FLASHCARD
    // =========================================================================
    val flashcards = listOf(
        Flashcard("La cellula", "Qual è la differenza fondamentale tra procarioti ed eucarioti?", "I procarioti (batteri) non hanno nucleo né organuli membranosi; gli eucarioti hanno un nucleo definito e organuli specializzati."),
        Flashcard("La cellula", "Dove avviene la respirazione cellulare?", "Nei mitocondri, che producono la maggior parte dell'ATP."),
        Flashcard("La cellula", "Quali tre strutture ha in più la cellula vegetale rispetto a quella animale?", "Parete di cellulosa, cloroplasti e grande vacuolo centrale."),
        Flashcard("La cellula", "Cosa produce l'apparato di Golgi?", "Modifica, impacchetta e distribuisce le proteine costruite dalla cellula."),
        Flashcard("La cellula", "Enuncia i tre punti della teoria cellulare.", "1) Tutti i viventi sono fatti di cellule. 2) La cellula è l'unità strutturale e funzionale. 3) Ogni cellula deriva da un'altra cellula."),
        Flashcard("La cellula", "Qual è la funzione dei lisosomi?", "Digerire sostanze estranee e parti esaurite della cellula (digestione intracellulare)."),

        Flashcard("Biomolecole", "Qual è la formula del glucosio e a quale famiglia appartiene?", "C6H12O6; è un monosaccaride (carboidrato semplice)."),
        Flashcard("Biomolecole", "Perché l'acqua è un solvente così buono?", "Perché è una molecola polare: attira e scioglie ioni e molecole polari."),
        Flashcard("Biomolecole", "Cosa sono i legami peptidici?", "I legami che uniscono gli amminoacidi per formare le proteine."),
        Flashcard("Biomolecole", "Che funzione hanno i fosfolipidi?", "Formano il doppio strato della membrana plasmatica."),
        Flashcard("Biomolecole", "Cosa fa un enzima e perché è 'specifico'?", "Accelera una reazione senza consumarsi; riconosce un solo substrato grazie al sito attivo."),
        Flashcard("Biomolecole", "Quali sono le quattro strutture di una proteina?", "Primaria (sequenza), secondaria (elica/foglietto), terziaria (ripiegamento 3D), quaternaria (più catene)."),

        Flashcard("Membrana e trasporto", "Che cos'è l'osmosi?", "Il passaggio di acqua attraverso una membrana semipermeabile, da dove è più concentrata verso dove è meno concentrata."),
        Flashcard("Membrana e trasporto", "Il trasporto passivo richiede energia? Perché?", "No: le sostanze si muovono spontaneamente secondo il gradiente di concentrazione."),
        Flashcard("Membrana e trasporto", "Fai un esempio di trasporto attivo.", "La pompa sodio-potassio, che spinge Na+ fuori e K+ dentro contro gradiente usando ATP."),
        Flashcard("Membrana e trasporto", "Cosa succede a una cellula in ambiente ipertonico?", "Perde acqua e si raggrinzisce (crenazione)."),
        Flashcard("Membrana e trasporto", "Differenza tra fagocitosi e pinocitosi.", "Fagocitosi (solidi) e pinocitosi (liquidi) sono entrambe endocitosi."),
        Flashcard("Membrana e trasporto", "Cosa mantiene 'fluida' la membrana e perché è importante?", "Il doppio strato di fosfolipidi e il colesterolo; la fluidità permette alle proteine di muoversi e alla cellula di cambiare forma."),

        Flashcard("Metabolismo", "Cosa produce la fotosintesi e in quali fasi avviene?", "Glucosio + ossigeno, in due fasi: fase luce (tilacoidi) e ciclo di Calvin (stroma)."),
        Flashcard("Metabolismo", "Quante molecole di ATP si ricavano dalla fermentazione e dalla respirazione aerobica?", "2 dalla fermentazione; fino a 36-38 dalla respirazione aerobica per glucosio."),
        Flashcard("Metabolismo", "Dove avvengono glicolisi e ciclo di Krebs?", "Glicolisi nel citoplasma; ciclo di Krebs nel mitocondrio."),
        Flashcard("Metabolismo", "Che cosa sono gli autotrofi?", "Organismi che producono da soli le proprie molecole organiche (fotosintesi)."),
        Flashcard("Metabolismo", "Perché si dice che l'ATP è la 'moneta energetica'?", "Perché immagazzina energia nei legami fosfato e la cede quando si rompe in ADP + fosfato."),
        Flashcard("Metabolismo", "In cosa differiscono fermentazione lattica e alcolica?", "Lattica: nei muscoli, produce lattato. Alcolica: nei lieviti, produce etanolo e CO2."),

        Flashcard("Genetica", "Quali basi si appaiano nel DNA?", "Adenina-Timina e Guanina-Citosina."),
        Flashcard("Genetica", "Che cosa dice la prima legge di Mendel?", "Legge della dominanza: incrociando linee pure, nella prima generazione compare la forma dominante."),
        Flashcard("Genetica", "Cos'è la trascrizione?", "La copia di un gene di DNA in mRNA, che avviene nel nucleo."),
        Flashcard("Genetica", "Cos'è il crossing-over?", "Lo scambio di segmenti tra cromosomi omologhi nella meiosi, che aumenta la variabilità genetica."),
        Flashcard("Genetica", "Differenza tra fenotipo e genotipo.", "Il genotipo è il corredo genetico; il fenotipo è il carattere osservabile."),
        Flashcard("Genetica", "Cosa significa che la replicazione del DNA è semiconservativa?", "Ogni nuova doppia elica contiene un filamento vecchio e uno nuovo: il filamento originale fa da stampo."),

        Flashcard("Riproduzione e ciclo cellulare", "Quali sono le fasi della mitosi?", "Profase, metafase, anafase, telofase (seguita dalla citodieresi)."),
        Flashcard("Riproduzione e ciclo cellulare", "Differenza tra mitosi e meiosi (risultato).", "Mitosi: 2 cellule identiche, diploidi. Meiosi: 4 cellule aploidi con ricombinazione."),
        Flashcard("Riproduzione e ciclo cellulare", "In quale fase del ciclo cellulare il DNA viene duplicato?", "Nella fase S dell'interfase."),
        Flashcard("Riproduzione e ciclo cellulare", "Perché la riproduzione sessuata è vantaggiosa?", "Perché mescola i geni dei due genitori e genera variabilità, adattandosi meglio ai cambiamenti."),
        Flashcard("Riproduzione e ciclo cellulare", "Cosa sono la gemmazione e la frammentazione?", "Modi di riproduzione asessuata (lieviti e idra; stelle marine e planarie)."),
        Flashcard("Riproduzione e ciclo cellulare", "Quando la cellula è aploide?", "Nei gameti (n), che si formano con la meiosi."),

        Flashcard("Anatomia umana", "Quali sono i due circuiti del sangue?", "Piccola circolazione (cuore-polmoni) e grande circolazione (cuore-corpo)."),
        Flashcard("Anatomia umana", "Dove avviene lo scambio di ossigeno nel polmone?", "Negli alveoli, circondati da capillari."),
        Flashcard("Anatomia umana", "Nome delle quattro cavità del cuore.", "Atrio destro, atrio sinistro, ventricolo destro, ventricolo sinistro."),
        Flashcard("Anatomia umana", "Quali enzimi degradano le proteine nello stomaco?", "La pepsina, in ambiente acido (acido cloridrico)."),
        Flashcard("Anatomia umana", "Cosa succede nella sinapsi?", "Il segnale passa da un neurone all'altro tramite neurotrasmettitori."),
        Flashcard("Anatomia umana", "Che ruolo hanno insulina e glucagone?", "Regolano la glicemia: l'insulina la abbassa, il glucagone la alza."),

        Flashcard("Ecologia e evoluzione", "Chi sono i decompositori e perché sono fondamentali?", "Batteri e funghi che degradano la materia morta, riciclando i nutrienti."),
        Flashcard("Ecologia e evoluzione", "Quanta energia passa a ogni livello della catena alimentare?", "Circa il 10% (il 90% si perde in calore e metabolismo)."),
        Flashcard("Ecologia e evoluzione", "Enuncia i punti chiave della selezione naturale.", "Variabilità, sovrapproduzione, lotta per la sopravvivenza, riproduzione differenziale dei più adatti."),
        Flashcard("Ecologia e evoluzione", "Differenza tra habitat e nicchia ecologica.", "Habitat = il luogo; nicchia = il 'mestiere' della specie nell'ecosistema."),
        Flashcard("Ecologia e evoluzione", "Quali prove supportano l'evoluzione?", "Fossili, anatomia comparata, biologia molecolare (DNA), biogeografia."),
        Flashcard("Ecologia e evoluzione", "Che cos'è la speciazione?", "La formazione di nuove specie, di solito quando popolazioni isolate accumulano differenze."),
    )

    // =========================================================================
    // QUIZ
    // =========================================================================
    val quiz = listOf(
        Question("La cellula", "Quale struttura è presente SOLO nelle cellule eucariote?",
            listOf("Ribosomi", "Nucleo", "DNA", "Membrana"), 1,
            "Il nucleo delimitato da membrana è la caratteristica distintiva degli eucarioti; i ribosomi, il DNA e la membrana esistono anche nei procarioti."),
        Question("La cellula", "I mitocondri sono gli organuli che...",
            listOf("Sintetizzano le proteine", "Digestiscono i materiali", "Producono ATP con la respirazione", "Contengono la clorofilla"), 2,
            "Nei mitocondri avviene la respirazione cellulare, che produce la maggior parte dell'ATP."),
        Question("La cellula", "Quale di queste strutture è tipica della cellula vegetale?",
            listOf("Lisosomi", "Centrioli", "Parete di cellulosa", "Ciglia"), 2,
            "La parete di cellulosa, insieme a cloroplasti e vacuolo, caratterizza la cellula vegetale."),
        Question("La cellula", "La teoria cellulare afferma che...",
            listOf("Ogni cellula deriva da un'altra cellula", "Le cellule si formano spontaneamente", "Solo gli animali hanno cellule", "I virus sono cellule"), 0,
            "Un principio base: omnis cellula e cellula, ogni cellula deriva da una preesistente."),
        Question("La cellula", "Quale organulo modifica e impacchetta le proteine destinate all'esterno?",
            listOf("Il nucleolo", "L'apparato di Golgi", "Il ribosoma", "Il lisosoma"), 1,
            "L'apparato di Golgi riceve le proteine dal RER, le modifica e le spedisce."),
        Question("La cellula", "Nei procarioti il DNA si trova...",
            listOf("Nel nucleo", "Nei mitocondri", "Libero nel citoplasma", "Nei ribosomi"), 2,
            "I procarioti non hanno nucleo: il DNA circolare è libero nel citoplasma."),
        Question("La cellula", "Qual è la funzione dei ribosomi?",
            listOf("Produrre ATP", "Sintetizzare proteine", "Digerire sostanze", "Conservare il DNA"), 1,
            "I ribosomi assembrano gli amminoacidi in proteine seguendo l'mRNA."),
        Question("La cellula", "Il vacuolo centrale delle cellule vegetali serve a...",
            listOf("Produrre energia", "Mantenere il turgore", "Trasportare ossigeno", "Sintetizzare DNA"), 1,
            "Il grande vacuolo pieno d'acqua spinge la parete e mantiene la cellula turgida."),

        Question("Biomolecole", "Quale zucchero è la principale fonte di energia delle cellule?",
            listOf("Saccarosio", "Glucosio", "Cellulosa", "Lattosio"), 1,
            "Il glucosio (C6H12O6) è il 'combustibile' universale delle cellule."),
        Question("Biomolecole", "I monosaccaridi sono...",
            listOf("Polimeri di amminoacidi", "Zuccheri semplici", "Grassi", "Acidi nucleici"), 1,
            "I monosaccaridi (glucosio, fruttosio) sono zuccheri semplici, unità dei carboidrati."),
        Question("Biomolecole", "Quali molecole formano il doppio strato della membrana?",
            listOf("Proteine", "Trigliceridi", "Fosfolipidi", "Polisaccaridi"), 2,
            "I fosfolipidi, con le teste idrofile e le code idrofobe, formano la membrana."),
        Question("Biomolecole", "Gli enzimi accelerano le reazioni...",
            listOf("Consumandosi nel processo", "Aumentando la temperatura", "Abbassando l'energia di attivazione", "Producendo ATP"), 2,
            "Gli enzimi abbassano l'energia di attivazione e non vengono consumati."),
        Question("Biomolecole", "Le proteine sono formate da catene di...",
            listOf("Nucleotidi", "Amminoacidi", "Monosaccaridi", "Acidi grassi"), 1,
            "I monomeri delle proteine sono i 20 amminoacidi, uniti da legami peptidici."),
        Question("Biomolecole", "Quale struttura di una proteina è la sequenza di amminoacidi?",
            listOf("Primaria", "Secondaria", "Terziaria", "Quaternaria"), 0,
            "La struttura primaria è proprio l'ordine lineare degli amminoacidi."),
        Question("Biomolecole", "Quali basi si appaiano nel DNA?",
            listOf("A-C, G-T", "A-T, G-C", "A-G, T-C", "A-A, T-T"), 1,
            "Adenina con Timina e Guanina con Citosina, unite da legami a idrogeno."),
        Question("Biomolecole", "Perché l'acqua ha un elevato calore specifico?",
            listOf("Per i legami peptidici", "Per i legami a idrogeno", "Per i doppi legami", "Per la presenza di sali"), 1,
            "I legami a idrogeno richiedono molta energia per rompersi: l'acqua assorbe e cede calore lentamente."),

        Question("Membrana e trasporto", "Il trasporto passivo...",
            listOf("Consuma ATP", "Va contro gradiente", "Seguire il gradiente senza energia", "Usa la pompa sodio-potassio"), 2,
            "Nel trasporto passivo (diffusione e osmosi) le sostanze seguono il gradiente senza ATP."),
        Question("Membrana e trasporto", "L'osmosi è il passaggio di...",
            listOf("Ioni attraverso canali", "Acqua attraverso la membrana", "Proteine attraverso il Golgi", "Gas attraverso gli alveoli"), 1,
            "L'osmosi è la diffusione dell'acqua attraverso una membrana semipermeabile."),
        Question("Membrana e trasporto", "In ambiente ipotonico una cellula animale tende a...",
            listOf("Perdere acqua", "Rimanere uguale", "Assorbire acqua e gonfiarsi", "Dividersi"), 2,
            "In ambiente ipotonico l'acqua entra: la cellula si gonfia (può anche scoppiare)."),
        Question("Membrana e trasporto", "La pompa sodio-potassio è un esempio di...",
            listOf("Diffusione", "Osmosi", "Trasporto attivo", "Fagocitosi"), 2,
            "Sposta ioni contro gradiente consumando ATP: trasporto attivo."),
        Question("Membrana e trasporto", "La fagocitosi è un tipo di...",
            listOf("Esocitosi", "Endocitosi", "Trasporto attivo", "Osmosi"), 1,
            "Fagocitosi (solidi) e pinocitosi (liquidi) sono forme di endocitosi."),
        Question("Membrana e trasporto", "Quale molecola rende la membrana più stabile senza toglierle fluidità?",
            listOf("Glucosio", "Colesterolo", "Amido", "Urea"), 1,
            "Il colesterolo, presente nella membrana animale, ne aumenta la stabilità senza togliere fluidità."),
        Question("Membrana e trasporto", "Le proteine canale permettono il passaggio di...",
            listOf("Qualsiasi molecola", "Solo acqua", "Sostanze specifiche secondo gradiente", "Nucleotidi"), 2,
            "Le proteine canale sono selettive e permettono la diffusione facilitata di ioni e molecole specifiche."),
        Question("Membrana e trasporto", "La membrana è definita semipermeabile perché...",
            listOf("Blocca tutto", "Fa passare tutto", "Lascia passare selettivamente alcune sostanze", "È impermeabile all'acqua"), 2,
            "Lascia passare liberamente acqua e piccole molecole ma regola il passaggio di altre sostanze."),

        Question("Metabolismo", "L'equazione della fotosintesi produce...",
            listOf("Glucosio e ossigeno", "ATP e CO2", "Acqua e ossigeno", "Solo energia"), 0,
            "6CO2 + 6H2O + luce → C6H12O6 + 6O2."),
        Question("Metabolismo", "Quanti ATP ricava una cellula per glucosio in aerobiosi?",
            listOf("2", "~10", "~36-38", "100"), 2,
            "La respirazione aerobica arriva fino a 36-38 ATP per molecola di glucosio."),
        Question("Metabolismo", "Dove ha sede il ciclo di Krebs?",
            listOf("Citoplasma", "Mitocondrio", "Ribosoma", "Nucleo"), 1,
            "Il ciclo di Krebs si svolge nella matrice mitocondriale."),
        Question("Metabolismo", "La fermentazione lattica avviene...",
            listOf("Nei lieviti", "Nei muscoli senza ossigeno", "Nei cloroplasti", "Nello stomaco"), 1,
            "Nei muscoli durante sforzi intensi produce lattato e affaticamento."),
        Question("Metabolismo", "Quali organismi sono autotrofi?",
            listOf("Animali", "Funghi", "Piante e alghe", "Virus"), 2,
            "Piante e alghe producono sostanza organica tramite la fotosintesi."),
        Question("Metabolismo", "Com'è fatta la molecola di ATP?",
            listOf("Adenina + zucchero + 3 fosfati", "Due zuccheri + fosfato", "Una base e un amminoacido", "Un lipide e glucosio"), 0,
            "ATP = adenina + ribosio + tre gruppi fosfato; rimuovendo un fosfato libera energia."),
        Question("Metabolismo", "Dove avviene la glicolisi?",
            listOf("Nel mitocondrio", "Nei ribosomi", "Nel citoplasma", "Nel nucleo"), 2,
            "La glicolisi avviene nel citoplasma e produce 2 ATP senza ossigeno."),
        Question("Metabolismo", "Il ciclo di Calvin usa quale fonte di energia per fissare l'anidride carbonica?",
            listOf("Glucosio dall'ATP e NADPH prodotti nella fase luce", "Ossigeno direttamente", "Solo luce", "Anidride carbonica senza energia"), 0,
            "Il ciclo di Calvin usa ATP e NADPH della fase luminosa per fissare la CO2 e costruire zuccheri."),

        Question("Genetica", "Secondo la prima legge di Mendel...",
            listOf("Gli alleli si separano nei gameti", "Il dominante nasconde il recessivo nella F1", "I geni si assortiscono in modo indipendente", "Il DNA si duplica"), 1,
            "Legge della dominanza: nella F1 compare solo il carattere dominante."),
        Question("Genetica", "Da due eterozigoti (Aa × Aa) la probabilità di figlio recessivo (aa) è...",
            listOf("25%", "50%", "75%", "100%"), 0,
            "Quadrati di Punnett: AA 25%, Aa 50%, aa 25%."),
        Question("Genetica", "Il crossing-over avviene durante...",
            listOf("La mitosi", "La meiosi", "La glicolisi", "La trascrizione"), 1,
            "Nella profase I della meiosi i cromosomi omologhi si scambiano segmenti."),
        Question("Genetica", "La trascrizione produce...",
            listOf("Proteine", "mRNA", "ATP", "DNA duplicato"), 1,
            "Un gene di DNA viene copiato in mRNA, che poi sarà tradotto."),
        Question("Genetica", "Il codice genetico è letto a...",
            listOf("Coppie", "Triplette di basi (codoni)", "Quartetti", "Singole basi"), 1,
            "Ogni codone di 3 basi corrisponde a un amminoacido."),
        Question("Genetica", "Che cos'è una mutazione puntiforme?",
            listOf("Cambio di un singolo nucleotide", "Cambio di numero di cromosomi", "Perdita di un gene intero", "Duplicazione del DNA"), 0,
            "La mutazione puntiforme modifica una singola base del DNA."),
        Question("Genetica", "La replicazione del DNA è detta semiconservativa perché...",
            listOf("Usa RNA come stampo", "Ogni elica ha un filamento vecchio e uno nuovo", "Richiede enzimi", "Avviene nel citoplasma"), 1,
            "Ogni molecola figlia conserva un filamento originale e uno appena sintetizzato."),
        Question("Genetica", "Quale tecnica è detta 'forbici molecolari'?",
            listOf("PCR", "Clonazione", "CRISPR-Cas9", "Elettroforesi"), 2,
            "CRISPR-Cas9 taglia il DNA in punti precisi: la rivoluzione dell'ingegneria genetica."),

        Question("Riproduzione e ciclo cellulare", "Nella fase S dell'interfase...",
            listOf("La cellula muore", "Il DNA viene duplicato", "Avviene la citodieresi", "Si formano i gameti"), 1,
            "Nella fase S il DNA si duplica in preparazione alla divisione."),
        Question("Riproduzione e ciclo cellulare", "La mitosi produce...",
            listOf("4 cellule aploidi", "2 cellule identiche diploidi", "2 cellule aploidi", "1 cellula gigante"), 1,
            "La mitosi dà 2 cellule figlie geneticamente identiche alla madre."),
        Question("Riproduzione e ciclo cellulare", "L'anafase è la fase in cui...",
            listOf("Si condensa la cromatina", "I cromatidi si separano verso i poli", "Si forma il fuso", "Si riformano i nuclei"), 1,
            "Nell'anafase i cromatidi gemelli migrano ai poli opposti."),
        Question("Riproduzione e ciclo cellulare", "La meiosi serve a...",
            listOf("Riparare i tessuti", "Formare i gameti", "Far crescere l'organismo", "Produrre cloni"), 1,
            "La meiosi forma i gameti con metà corredo cromosomico e ricombinazione."),
        Question("Riproduzione e ciclo cellulare", "Un gamete umano ha...",
            listOf("46 cromosomi (2n)", "23 cromosomi (n)", "92 cromosomi", "Nessun cromosoma"), 1,
            "I gameti sono aploidi: 23 cromosomi; la fecondazione ripristina 46."),
        Question("Riproduzione e ciclo cellulare", "La gemmazione è...",
            listOf("Riproduzione asessuata", "Riproduzione sessuata", "Un tipo di meiosi", "Una mutazione"), 0,
            "Gemmazione (lieviti), scissione e frammentazione sono forme di riproduzione asessuata."),
        Question("Riproduzione e ciclo cellulare", "Il vantaggio della riproduzione sessuata è...",
            listOf("È più veloce", "Genera variabilità genetica", "Non richiede due genitori", "Produce cellule identiche"), 1,
            "Mescolando i geni dei due genitori aumenta la variabilità e l'adattamento."),
        Question("Riproduzione e ciclo cellulare", "L'interfase comprende le fasi...",
            listOf("G1, S, G2", "Profase e metafase", "Anafase e telofase", "Quattro divisioni"), 0,
            "Interfase = G1 (crescita), S (duplicazione DNA), G2 (preparazione)."),

        Question("Anatomia umana", "La piccola circolazione porta il sangue...",
            listOf("Dal cuore ai polmoni e ritorno", "Dal cuore a tutto il corpo", "Dai reni al cuore", "Dal fegato all'intestino"), 0,
            "Piccola circolazione = cuore → polmoni (ossigenazione) → cuore."),
        Question("Anatomia umana", "Lo scambio di ossigeno e CO2 avviene negli...",
            listOf("Bronchi", "Alveoli", "Trachea", "Ventricoli"), 1,
            "Gli alveoli, circondati da capillari, sono il luogo degli scambi gassosi."),
        Question("Anatomia umana", "Quante cavità ha il cuore umano?",
            listOf("2", "3", "4", "6"), 2,
            "Due atri e due ventricoli (quattro cavità)."),
        Question("Anatomia umana", "La pepsina, attiva nello stomaco, digerisce...",
            listOf("I carboidrati", "Le proteine", "I lipidi", "Le vitamine"), 1,
            "La pepsina ammattisce le proteine in ambiente acido."),
        Question("Anatomia umana", "Dove vengono assorbiti i nutrienti?",
            listOf("Nello stomaco", "Nell'intestino tenue", "Nel crasso", "Nell'esofago"), 1,
            "L'intestino tenue, con i villi, è il principale organo dell'assorbimento."),
        Question("Anatomia umana", "Il neurone trasmette l'impulso attraverso la...",
            listOf("Sinapsi", "Pompa ionica", "Alveolo", "Nefrone"), 0,
            "Alla sinapsi il segnale passa da un neurone all'altro con i neurotrasmettitori."),
        Question("Anatomia umana", "L'insulina prodotta dal pancreas...",
            listOf("Alza la glicemia", "Abbassa la glicemia", "Regola il calcio", "Digera i grassi"), 1,
            "L'insulina fa entrare il glucosio nelle cellule e abbassa la glicemia."),
        Question("Anatomia umana", "I nefroni sono le unità funzionali...",
            listOf("Dei polmoni", "Dei reni", "Del cuore", "Del cervello"), 1,
            "Nei nefroni dei reni il sangue viene filtrato per formare l'urina."),

        Question("Ecologia e evoluzione", "I decompositori...",
            listOf("Producono energia dal sole", "Riciclano la materia organica morta", "Sono solo carnivori", "Fissano l'azoto"), 1,
            "Batteri e funghi degradano la sostanza organica e liberano i nutrienti."),
        Question("Ecologia e evoluzione", "A ogni livello della catena alimentare si perde circa...",
            listOf("Il 10% dell'energia", "Il 90% dell'energia", "Tutta l'energia", "Nessuna energia"), 1,
            "Solo circa il 10% dell'energia passa al livello successivo."),
        Question("Ecologia e evoluzione", "La selezione naturale agisce su...",
            listOf("I genitori", "I singoli individui e i loro caratteri", "Le specie intere in blocco", "Solo i fossili"), 1,
            "Agisce sugli individui: chi ha caratteri vantaggiosi sopravvive e si riproduce di più."),
        Question("Ecologia e evoluzione", "Un insieme di catene alimentari intrecciate si chiama...",
            listOf("Piramide", "Rete alimentare", "Catena lineare", "Biomassa"), 1,
            "La rete alimentare mostra tutti i rapporti alimentari di un ecosistema."),
        Question("Ecologia e evoluzione", "L'habitat di una specie è...",
            listOf("Il suo 'mestiere'", "Il luogo in cui vive", "La sua dieta", "Il suo DNA"), 1,
            "Habitat = luogo fisico; nicchia = ruolo ecologico."),
        Question("Ecologia e evoluzione", "Quale prova dell'evoluzione viene dall'anatomia comparata?",
            listOf("Le sequenze di DNA", "Le strutture omologhe", "I conteggi di specie", "La fotosintesi"), 1,
            "L'arto di uomo, balena e pipistrello ha un'origine comune: strutture omologhe."),
        Question("Ecologia e evoluzione", "La speciazione avviene di solito quando...",
            listOf("Una popolazione viene isolata", "Le specie si estinguono", "I produttori aumentano", "Il clima è stabile"), 0,
            "L'isolamento permette alle popolazioni di accumulare differenze fino a diventare specie distinte."),
        Question("Ecologia e evoluzione", "Chi è considerato il padre della teoria dell'evoluzione per selezione naturale?",
            listOf("Mendel", "Darwin", "Watson", "Hooke"), 1,
            "Charles Darwin, autore de 'L'origine delle specie' (1859)."),
    )

    // =========================================================================
    // Helpers
    // =========================================================================
    fun searchChapters(q: String): List<Chapter> {
        val query = q.trim().lowercase()
        if (query.isEmpty()) return chapters
        return chapters.filter {
            it.title.lowercase().contains(query) ||
                it.summary.lowercase().contains(query) ||
                it.sections.any { s ->
                    s.title.lowercase().contains(query) ||
                        s.blocks.any { b -> b.toString2().lowercase().contains(query) }
                }
        }
    }

    fun searchGlossary(q: String): List<GlossaryTerm> {
        val query = q.trim().lowercase()
        if (query.isEmpty()) return glossary.sortedBy { it.term.lowercase() }
        return glossary.filter {
            it.term.lowercase().contains(query) || it.def.lowercase().contains(query)
        }.sortedBy { it.term.lowercase() }
    }

    private fun Block.toString2(): String = when (this) {
        is Block.Text -> s
        is Block.Bullet -> s
        is Block.Key -> s
    }

    val allFlashcards: List<Flashcard> = flashcards
    val allQuestions: List<Question> = quiz
}