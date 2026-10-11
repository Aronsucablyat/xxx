package com.brahma.bioquaderno.data

/**
 * Materie del TRIENNIO (parte A): indirizzo Economico (AFM e Turismo)
 * e indirizzo Informatica e Telecomunicazioni.
 */
fun triennioASubjects(): List<Subject> = listOf(
    // =========================================================================
    // AFM — ECONOMIA AZIENDALE
    // =========================================================================
    Subject(
        id = "economia-aziendale", name = "Economia aziendale", emoji = "💼", indirizzo = "AFM", colorHex = 0xFF6D4C41,
        chapters = listOf(
            Chapter(0, "L'azienda e la contabilità", "🧾",
                "Tipi di azienda, patrimonio e il bilancio d'esercizio.",
                listOf(
                    Section("Le aziende", listOf(
                        Block.Text("L'azienda è un'organizzazione di persone e beni che produce beni o servizi per soddisfare bisogni; l'impresa è l'azienda che opera sul mercato con rischio."),
                        Block.Text("Le imprese si distinguono per settore (commerciali, industriali, di servizi), per dimensione e per forma giuridica."),
                        Block.Key("Azienda = organizzazione; impresa = azienda che opera sul mercato assumendo un rischio."),
                    )),
                    Section("Il bilancio d'esercizio", listOf(
                        Block.Text("Il bilancio è il documento che ogni anno fotografa la situazione dell'azienda: attività, passività e patrimonio netto."),
                        Block.Text("Lo Stato patrimoniale descrive il patrimonio; il Conto economico misura costi, ricavi e utile o perdita."),
                        Block.Key("Attività - Passività = Patrimonio netto."),
                    )),
                )),
            Chapter(0, "Costi, ricavi e gestione", "📊",
                "La gestione aziendale e il calcolo del risultato economico.",
                listOf(
                    Section("Costi e ricavi", listOf(
                        Block.Text("I costi sono le risorse consumate (materie, lavoro, energia); i ricavi sono i valori incassati dalle vendite."),
                        Block.Text("Utile = ricavi - costi; se i costi superano i ricavi l'azienda è in perdita."),
                        Block.Key("Utile = Ricavi - Costi (in una gestione semplice)."),
                    )),
                    Section("Fattori produttivi", listOf(
                        Block.Text("I fattori produttivi sono le risorse usate per produrre: lavoro, capitale, materie prime, informazioni."),
                        Block.Text("L'efficienza misura quanto bene l'azienda usa le risorse: produrre di più con le stesse risorse = più efficiente."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Azienda", "Organizzazione di persone e beni che produce beni o servizi per soddisfare bisogni."),
            GlossaryTerm("Impresa", "Azienda che opera sul mercato assumendo un rischio economico."),
            GlossaryTerm("Bilancio", "Documento annuale che rappresenta la situazione patrimoniale ed economica dell'azienda."),
            GlossaryTerm("Stato patrimoniale", "Parte del bilancio che descrive attività, passività e patrimonio netto."),
            GlossaryTerm("Utile", "Risultato positivo: ricavi maggiori dei costi."),
            GlossaryTerm("Conto economico", "Parte del bilancio che misura costi, ricavi e risultato d'esercizio."),
        ),
        flashcards = listOf(
            Flashcard("Azienda", "Differenza tra azienda e impresa?", "L'impresa è l'azienda che opera sul mercato assumendo un rischio."),
            Flashcard("Bilancio", "Che cos'è lo Stato patrimoniale?", "La parte del bilancio che mostra attività, passività e patrimonio netto."),
            Flashcard("Bilancio", "Formula dell'utile?", "Utile = Ricavi - Costi."),
            Flashcard("Gestione", "Che cosa misura il Conto economico?", "Costi, ricavi e risultato d'esercizio."),
            Flashcard("Gestione", "Quali sono i fattori produttivi?", "Lavoro, capitale, materie prime e informazioni."),
        ),
        questions = listOf(
            Question("Azienda", "L'impresa si distingue dall'azienda perché:",
                listOf("Non ha dipendenti", "Opera sul mercato con rischio economico", "È sempre pubblica", "Non produce nulla"), 1,
                "L'impresa assume un rischio operando sul mercato."),
            Question("Bilancio", "Attività - Passività =", 
                listOf("Utile", "Patrimonio netto", "Costi", "Ricavi"), 1,
                "Il patrimonio netto è la differenza tra attività e passività."),
            Question("Bilancio", "Se i costi sono 10.000 € e i ricavi 8.000 €, l'azienda:",
                listOf("Ha un utile di 2.000 €", "Ha una perdita di 2.000 €", "È in pareggio", "Ha ricavi doppi"), 1,
                "Perdita = costi - ricavi = 2.000 €."),
            Question("Gestione", "Il documento che mostra la situazione patrimoniale è:",
                listOf("Conto economico", "Stato patrimoniale", "Fattura", "Budget"), 1,
                "Lo Stato patrimoniale."),
            Question("Gestione", "Produrre di più con le stesse risorse significa essere più:",
                listOf("Costosi", "Efficienti", "Ricchi", "Famosi"), 1,
                "L'efficienza è produrre al meglio con le risorse disponibili."),
        ),
    ),

    // =========================================================================
    // AFM — DIRITTO
    // =========================================================================
    Subject(
        id = "diritto", name = "Diritto", emoji = "🏛️", indirizzo = "AFM", colorHex = 0xFF4527A0,
        chapters = listOf(
            Chapter(0, "Il contratto e le obbligazioni", "📜",
                "Gli elementi del contratto e la vita delle obbligazioni.",
                listOf(
                    Section("Le obbligazioni", listOf(
                        Block.Text("L'obbligazione è il rapporto tra un creditore (a cui è dovuta una prestazione) e un debitore."),
                        Block.Text("Le fonti delle obbligazioni sono il contratto, il fatto illecito e ogni altro atto previsto dalla legge."),
                        Block.Key("Obbligazione = rapporto tra creditore e debitore, da cui nasce una prestazione."),
                    )),
                    Section("Il contratto", listOf(
                        Block.Text("Il contratto è l'accordo tra due o più parti per costituire, modificare o estinguere un rapporto giuridico patrimoniale."),
                        Block.Bullet("Elementi essenziali: accordo, oggetto, causa e forma (quando richiesta)."),
                        Block.Bullet("I contratti tipici: compravendita, locazione, mandato, assicurazione."),
                        Block.Key("Senza accordo, oggetto o causa il contratto è nullo."),
                    )),
                )),
            Chapter(0, "Il diritto del lavoro", "👷",
                "Lavoro subordinato e autonomo, diritti del lavoratore e contratto di lavoro.",
                listOf(
                    Section("Lavoro subordinato e autonomo", listOf(
                        Block.Text("Nel lavoro subordinato il lavoratore è alle dipendenze del datore e segue le sue direttive; nel lavoro autonomo svolge un'attività senza vincolo di subordinazione."),
                        Block.Text("Il contratto di lavoro subordinato tutela il lavoratore: orari, ferie, retribuzione e contributi."),
                    )),
                    Section("Diritti e tutele", listOf(
                        Block.Bullet("Retribuzione: proporzionata a quantità e qualità del lavoro."),
                        Block.Bullet("Tutela della salute e sicurezza sul lavoro."),
                        Block.Bullet("Divieto di discriminazione e licenziamenti ingiustificati."),
                        Block.Key("La Costituzione (art. 35-40) tutela il lavoro in tutte le sue forme."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Obbligazione", "Rapporto giuridico tra creditore e debitore che impone una prestazione."),
            GlossaryTerm("Contratto", "Accordo tra due o più parti per costituire, modificare o estinguere un rapporto patrimoniale."),
            GlossaryTerm("Compravendita", "Contratto con cui una parte trasferisce la proprietà di un bene in cambio di un prezzo."),
            GlossaryTerm("Lavoro subordinato", "Attività svolta alle dipendenze del datore di lavoro."),
            GlossaryTerm("Lavoro autonomo", "Attività svolta senza vincolo di subordinazione (es. libero professionista)."),
            GlossaryTerm("Datore di lavoro", "Soggetto che assume e dirige i lavoratori."),
        ),
        flashcards = listOf(
            Flashcard("Obbligazioni", "Chi è il creditore?", "Chi ha diritto a ricevere la prestazione dovuta."),
            Flashcard("Contratto", "Quali sono gli elementi essenziali del contratto?", "Accordo, oggetto, causa e forma quando richiesta."),
            Flashcard("Contratto", "Che cos'è la compravendita?", "Il contratto che trasferisce la proprietà di un bene in cambio di un prezzo."),
            Flashcard("Lavoro", "Che cos'è il lavoro subordinato?", "Il lavoro svolto alle dipendenze del datore."),
            Flashcard("Lavoro", "In quale articolo la Costituzione tutela il lavoro?", "Negli articoli 35-40."),
        ),
        questions = listOf(
            Question("Obbligazioni", "Nel rapporto di obbligazione, chi deve eseguire la prestazione è:",
                listOf("Il creditore", "Il debitore", "Il giudice", "Il notaio"), 1,
                "Il debitore deve eseguire la prestazione dovuta al creditore."),
            Question("Contratto", "Un contratto privo di accordo è:",
                listOf("Annullabile", "Nullo", "Valido", "Rinnovabile"), 1,
                "Senza accordo manca un elemento essenziale: il contratto è nullo."),
            Question("Lavoro", "Il libero professionista svolge attività:",
                listOf("Subordinata", "Autonoma", "Occulta", "Accessoria"), 1,
                "Il professionista non è alle dipendenze: lavoro autonomo."),
            Question("Lavoro", "La retribuzione deve essere:",
                listOf("Uguale per tutti", "Proporzionata a quantità e qualità del lavoro", "Fissata dal lavoratore", "Solo in natura"), 1,
                "Proporzionata alla quantità e qualità del lavoro (Costituzione)."),
            Question("Contratto", "La locazione è un contratto che riguarda:",
                listOf("La vendita di un bene", "L'uso temporaneo di un bene dietro canone", "Il lavoro", "L'assicurazione"), 1,
                "La locazione concede l'uso di un bene (es. casa) dietro pagamento."),
        ),
    ),

    // =========================================================================
    // AFM — ECONOMIA POLITICA
    // =========================================================================
    Subject(
        id = "economia-politica", name = "Economia politica", emoji = "📊", indirizzo = "AFM", colorHex = 0xFF283593,
        chapters = listOf(
            Chapter(0, "Il sistema economico", "🔄",
                "Circuiti economici, domanda e offerta, la moneta.",
                listOf(
                    Section("Il circuito economico", listOf(
                        Block.Text("Nel circuito economico le famiglie offrono lavoro e consumano; le imprese producono e retribuiscono; lo Stato regola e ridistribuisce."),
                        Block.Text("Il PIL (prodotto interno lordo) è il valore dei beni e servizi finali prodotti in un Paese in un anno."),
                        Block.Key("PIL = valore di tutto ciò che un Paese produce in un anno."),
                    )),
                    Section("Domanda, offerta e moneta", listOf(
                        Block.Text("La domanda è la quantità che i compratori vogliono acquistare a un dato prezzo; l'offerta è quella che i produttori vogliono vendere."),
                        Block.Text("La moneta svolge tre funzioni: mezzo di scambio, unità di conto e riserva di valore."),
                        Block.Key("Se l'offerta supera la domanda, il prezzo tende a scendere e viceversa."),
                    )),
                )),
            Chapter(0, "Lo Stato: bilancio e imposte", "🏦",
                "Bilancio pubblico, entrate e uscite, imposte e tasse.",
                listOf(
                    Section("Il bilancio pubblico", listOf(
                        Block.Text("Il bilancio dello Stato elenca le entrate (soprattutto imposte) e le uscite (spesa pubblica: sanità, scuola, pensioni)."),
                        Block.Text("Se le uscite superano le entrate c'è il deficit, che si copre con il debito pubblico."),
                        Block.Key("Deficit = uscite maggiori delle entrate → aumenta il debito pubblico."),
                    )),
                    Section("Imposte e tasse", listOf(
                        Block.Bullet("Imposta: pagata per finanziare i servizi generali, in base alla capacità contributiva (es. IRPEF)."),
                        Block.Bullet("Tassa: pagata per un servizio specifico (es. tasse scolastiche)."),
                        Block.Bullet("Imposte dirette: su reddito e patrimonio; indirette: su consumi e scambi (es. IVA)."),
                        Block.Key("L'IRPEF è un'imposta diretta e progressiva sul reddito delle persone."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("PIL", "Valore di mercato dei beni e servizi finali prodotti in un Paese in un anno."),
            GlossaryTerm("Domanda", "Quantità di un bene che i compratori desiderano acquistare a un certo prezzo."),
            GlossaryTerm("Offerta", "Quantità di un bene che i produttori sono disposti a vendere."),
            GlossaryTerm("Moneta", "Mezzo di scambio, unità di conto e riserva di valore."),
            GlossaryTerm("Deficit", "Eccedenza delle uscite pubbliche sulle entrate."),
            GlossaryTerm("IVA", "Imposta indiretta sui consumi e sugli scambi."),
        ),
        flashcards = listOf(
            Flashcard("Sistema", "Che cos'è il PIL?", "Il valore dei beni e servizi finali prodotti in un Paese in un anno."),
            Flashcard("Sistema", "Quali sono le tre funzioni della moneta?", "Mezzo di scambio, unità di conto, riserva di valore."),
            Flashcard("Stato", "Se il prezzo sale, la domanda di solito…", "Scende."),
            Flashcard("Stato", "Che cos'è il deficit pubblico?", "L'eccedenza delle uscite sulle entrate dello Stato."),
            Flashcard("Imposte", "L'IVA è un'imposta…", "Indiretta, sui consumi."),
        ),
        questions = listOf(
            Question("Sistema", "Il PIL misura:",
                listOf("Il debito delle famiglie", "Il valore di ciò che un Paese produce in un anno", "Il numero di imprese", "Il tasso di disoccupazione"), 1,
                "PIL = valore dei beni e servizi finali prodotti."),
            Question("Sistema", "Se l'offerta supera la domanda, il prezzo tende a:",
                listOf("Salire", "Scendere", "Restare uguale", "Raddoppiare"), 1,
                "L'eccesso di offerta spinge i prezzi verso il basso."),
            Question("Stato", "Il debito pubblico cresce quando:",
                listOf("Le entrate superano le uscite", "Le uscite superano le entrate", "Il PIL cresce", "Le tasse calano"), 1,
                "Il deficit viene coperto ricorrendo al debito."),
            Question("Imposte", "L'IRPEF è un'imposta:",
                listOf("Indiretta", "Diretta e progressiva sul reddito", "Sui consumi", "Sui giochi"), 1,
                "È diretta (colpisce il reddito) e progressiva (cresce col reddito)."),
            Question("Imposte", "La tassa si paga per:",
                listOf("Finanziare i servizi generali", "Un servizio specifico", "Il reddito", "Il patrimonio"), 1,
                "La tassa è legata a un servizio specifico (es. tasse scolastiche)."),
        ),
    ),

    // =========================================================================
    // TURISMO — DISCIPLINE TURISTICHE E AZIENDALI
    // =========================================================================
    Subject(
        id = "discipline-turistiche", name = "Discipline turistiche e aziendali", emoji = "✈️",
        indirizzo = "Turismo", colorHex = 0xFF00838F,
        chapters = listOf(
            Chapter(0, "Il fenomeno turistico", "🌴",
                "Il turismo come attività economica e la filiera turistica.",
                listOf(
                    Section("Tipologie di turismo", listOf(
                        Block.Text("Il turismo è lo spostamento temporaneo di persone fuori dal luogo di residenza per svago, lavoro, cultura o salute."),
                        Block.Bullet("Turismo balneare, montano, culturale, enogastronomico, religioso, d'affari."),
                        Block.Bullet("Domanda turistica: chi viaggia; offerta turistica: servizi e strutture."),
                        Block.Key("Il turismo è un settore economico in forte crescita, trainante per l'Italia."),
                    )),
                    Section("La filiera turistica", listOf(
                        Block.Text("La filiera turistica unisce tutti i soggetti che servono il turista: trasporti, strutture ricettive, guide, agenzie, ristorazione."),
                        Block.Text("La ricettività comprende hotel, B&B, campeggi, villaggi; l'accessibilità è data dai trasporti."),
                    )),
                )),
            Chapter(0, "L'impresa turistica", "🏨",
                "Agenzie di viaggio, tour operator e prezzi.",
                listOf(
                    Section("Agenzie e tour operator", listOf(
                        Block.Text("L'agenzia di viaggio vende al pubblico servizi e pacchetti; il tour operator costruisce i pacchetti e li vende tramite le agenzie."),
                        Block.Text("Il pacchetto turistico è l'insieme di trasporto + alloggio + altri servizi venduti a prezzo unico."),
                        Block.Key("Tour operator = produttore del pacchetto; agenzia = venditore al cliente."),
                    )),
                    Section("Prezzi e costi", listOf(
                        Block.Text("Il prezzo di un viaggio copre i costi (trasporto, hotel, commissioni) più il margine di guadagno."),
                        Block.Text("Le politiche di prezzo usano sconti fuori stagione (low season) per riempire le strutture."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Turismo", "Spostamento temporaneo fuori dal luogo di residenza per svago, lavoro, cultura o salute."),
            GlossaryTerm("Domanda turistica", "I turisti e i loro bisogni."),
            GlossaryTerm("Offerta turistica", "L'insieme dei servizi e delle strutture per i turisti."),
            GlossaryTerm("Pacchetto turistico", "Trasporto + alloggio + servizi venduti a prezzo unico."),
            GlossaryTerm("Tour operator", "Impresa che costruisce e organizza i pacchetti turistici."),
            GlossaryTerm("Agenzia di viaggio", "Intermediario che vende servizi e pacchetti ai clienti."),
        ),
        flashcards = listOf(
            Flashcard("Fenomeno", "Che cosa è la domanda turistica?", "I turisti e i loro bisogni di viaggio."),
            Flashcard("Fenomeno", "Che cos'è la filiera turistica?", "Tutti i soggetti che servono il turista: trasporti, ricettività, agenzie, guide."),
            Flashcard("Impresa", "Chi costruisce i pacchetti turistici?", "Il tour operator."),
            Flashcard("Impresa", "Che cos'è un pacchetto turistico?", "Trasporto + alloggio + altri servizi a prezzo unico."),
            Flashcard("Impresa", "In bassa stagione i prezzi di solito…", "Scendono per riempire le strutture."),
        ),
        questions = listOf(
            Question("Fenomeno", "La struttura ricettiva è parte dell'offerta:",
                listOf("Turistica", "Domanda", "Fiscale", "Politica"), 0,
                "Strutture e servizi sono offerta turistica."),
            Question("Fenomeno", "Il turista che viaggia per vedere musei pratica il turismo:",
                listOf("Balneare", "Culturale", "Sportivo", "Religioso"), 1,
                "Musei e arte = turismo culturale."),
            Question("Impresa", "Il tour operator:",
                listOf("Vende solo biglietti aerei", "Costruisce i pacchetti turistici", "Gestisce gli hotel", "Fornisce la ristorazione"), 1,
                "È il produttore dei pacchetti."),
            Question("Impresa", "Il prezzo di un viaggio comprende:",
                listOf("Solo il trasporto", "Costi + margine", "Solo l'hotel", "Le tasse comunali"), 1,
                "Costi del servizio più il margine dell'azienda."),
            Question("Fenomeno", "L'Italia è considerata una destinazione turistica:",
                listOf("Minore", "Primaria", "Inesistente", "Solo invernale"), 1,
                "L'Italia è una delle principali destinazioni al mondo."),
        ),
    ),

    // =========================================================================
    // TURISMO — GEOGRAFIA TURISTICA
    // =========================================================================
    Subject(
        id = "geografia-turistica", name = "Geografia turistica", emoji = "🗺️", indirizzo = "Turismo", colorHex = 0xFF2E7D32,
        chapters = listOf(
            Chapter(0, "Il turismo in Italia", "🇮🇹",
                "Le grandi aree turistiche italiane e la loro offerta.",
                listOf(
                    Section("Le destinazioni italiane", listOf(
                        Block.Text("L'Italia offre bellezze naturali e artistiche: città d'arte (Roma, Firenze, Venezia), laghi e montagne (Alpi, Dolomiti), coste (Sicilia, Sardegna, costiera amalfitana)."),
                        Block.Text("Ogni regione ha un suo prodotto turistico: enogastronomia, terme, eventi, arte."),
                    )),
                    Section("I flussi turistici", listOf(
                        Block.Text("I flussi turistici misurano arrivi e presenze dei turisti in una destinazione."),
                        Block.Text("La stagionalità concentra i turisti in pochi mesi: l'obiettivo è destagionalizzare (diffondere il turismo tutto l'anno)."),
                        Block.Key("Presenze = numero di notti trascorse dai turisti in una destinazione."),
                    )),
                )),
            Chapter(0, "Turismo internazionale e sostenibile", "🌍",
                "Le grandi aree del turismo mondiale e i nuovi trend.",
                listOf(
                    Section("Le mete mondiali", listOf(
                        Block.Text("Le principali aree turistiche mondiali: Europa (Spagna, Francia, Italia), America (USA, Messico, Brasile), Asia (Cina, Tailandia, Giappone)."),
                        Block.Text("Il turismo genera ricchezza ma anche impatti ambientali: trasporti e strutture consumano risorse."),
                    )),
                    Section("Turismo sostenibile", listOf(
                        Block.Text("Il turismo sostenibile rispetta l'ambiente e le comunità locali, preservando le risorse per il futuro."),
                        Block.Bullet("Ridurre gli sprechi e l'inquinamento."),
                        Block.Bullet("Valorizzare le tradizioni e l'economia locale."),
                        Block.Key("Turismo sostenibile = turismo che può durare nel tempo senza danneggiare il territorio."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Flusso turistico", "Movimento di turisti verso una destinazione."),
            GlossaryTerm("Presenze", "Numero di notti trascorse dai turisti in una località."),
            GlossaryTerm("Arrivi", "Numero di turisti che raggiungono una destinazione."),
            GlossaryTerm("Stagionalità", "Concentrazione dei turisti in alcuni periodi dell'anno."),
            GlossaryTerm("Destagionalizzazione", "Strategia per distribuire il turismo su tutto l'anno."),
            GlossaryTerm("Turismo sostenibile", "Turismo che rispetta ambiente e comunità locali."),
        ),
        flashcards = listOf(
            Flashcard("Italia", "Che differenza c'è tra arrivi e presenze?", "Gli arrivi contano i turisti; le presenze contano le notti trascorse."),
            Flashcard("Italia", "Che cos'è la stagionalità?", "La concentrazione dei turisti in pochi mesi dell'anno."),
            Flashcard("Italia", "Come si combatte la stagionalità?", "Con la destagionalizzazione: offerte e eventi tutto l'anno."),
            Flashcard("Mondo", "Il turismo sostenibile protegge…", "L'ambiente e le comunità locali."),
            Flashcard("Mondo", "Una delle principali destinazioni mondiali è…", "L'Europa (Spagna, Francia, Italia)."),
        ),
        questions = listOf(
            Question("Italia", "Le presenze turistiche misurano:",
                listOf("I turisti in un anno", "Le notti trascorse dai turisti", "Le agenzie", "I voli"), 1,
                "Presenze = notti trascorse."),
            Question("Italia", "La costa amalfitana è un esempio di turismo:",
                listOf("Montano", "Balneare e paesaggistico", "Sportivo", "Industriale"), 1,
                "Turismo balneare e di paesaggio."),
            Question("Mondo", "Il turismo sostenibile:",
                listOf("Massimizza i profitti", "Rispetta ambiente e comunità", "Ignora il clima", "Solo d'inverno"), 1,
                "Protegge le risorse per il futuro."),
            Question("Italia", "Venezia è una destinazione di turismo:",
                listOf("Balneare", "Culturale e d'arte", "Montano", "Termale"), 1,
                "Città d'arte per eccellenza."),
            Question("Mondo", "Il principale flusso turistico mondiale è diretto verso:",
                listOf("L'Europa", "L'Antartide", "L'Africa interna", "Il Polo Nord"), 0,
                "L'Europa è la prima area turistica mondiale."),
        ),
    ),

    // =========================================================================
    // TURISMO — DIRITTO E LEGISLAZIONE TURISTICA
    // =========================================================================
    Subject(
        id = "diritto-turistico", name = "Diritto e legislazione turistica", emoji = "🧳",
        indirizzo = "Turismo", colorHex = 0xFF6A1B9A,
        chapters = listOf(
            Chapter(0, "La normativa del turismo", "🧾",
                "Le regole del settore e la tutela del turista.",
                listOf(
                    Section("Il quadro normativo", listOf(
                        Block.Text("Il turismo è regolato da norme nazionali, regionali e comunitarie; lo Stato italiano ha una legge-quadro sul turismo."),
                        Block.Text("Le Regioni legiferano su agenzie di viaggio, professioni turistiche e promozione."),
                    )),
                    Section("La tutela del turista", listOf(
                        Block.Text("Il turista è un consumatore: ha diritto a informazioni chiare, prezzi trasparenti e tutele in caso di disservizi."),
                        Block.Text("In caso di problemi (volo cancellato, hotel diverso) il turista può chiedere rimborso o risarcimento."),
                        Block.Key("Turista = consumatore: vale il Codice del consumo."),
                    )),
                )),
            Chapter(0, "Contratti e pacchetti turistici", "📜",
                "Il contratto di viaggio, il pacchetto e la responsabilità.",
                listOf(
                    Section("I contratti turistici", listOf(
                        Block.Text("I contratti principali: trasporto, albergo, pacchetto turistico, assicurazione di viaggio."),
                        Block.Text("Il contratto di pacchetto turistico deve essere scritto e indicare prezzi, servizi e condizioni."),
                    )),
                    Section("Responsabilità", listOf(
                        Block.Text("Tour operator e agenzia rispondono dei danni al turista per inesatta esecuzione del pacchetto."),
                        Block.Text("Esiste il diritto di recesso e di assistenza: in caso di cancellazione o riduzione dei servizi si ha diritto al rimborso."),
                        Block.Key("Recesso e rimborso sono diritti del turista in caso di mancata o alterata prestazione."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Turista", "Consumatore che acquista servizi turistici."),
            GlossaryTerm("Pacchetto turistico", "Combinazione di trasporto e alloggio con altri servizi, venduta a prezzo unico."),
            GlossaryTerm("Recesso", "Diritto di ritirarsi dal contratto."),
            GlossaryTerm("Codice del consumo", "Normativa che tutela i consumatori, compresi i turisti."),
            GlossaryTerm("Tour operator", "Soggetto che organizza i pacchetti turistici."),
            GlossaryTerm("Rimborso", "Restituzione del denaro in caso di mancata prestazione."),
        ),
        flashcards = listOf(
            Flashcard("Normativa", "Chi legifera sul turismo in Italia?", "Lo Stato (legge-quadro) e le Regioni."),
            Flashcard("Normativa", "Il turista è considerato…", "Un consumatore: vale il Codice del consumo."),
            Flashcard("Contratti", "Come deve essere il contratto di pacchetto turistico?", "Scritto, con prezzi, servizi e condizioni chiare."),
            Flashcard("Responsabilità", "In caso di servizi ridotti il turista ha diritto a…", "Recesso, rimborso o risarcimento."),
            Flashcard("Contratti", "Quali sono i principali contratti turistici?", "Trasporto, albergo, pacchetto, assicurazione."),
        ),
        questions = listOf(
            Question("Normativa", "Il turista viene tutelato come:",
                listOf("Venditore", "Consumatore", "Dipendente", "Titolare"), 1,
                "Vale il Codice del consumo."),
            Question("Contratti", "Il pacchetto turistico è:",
                listOf("Solo un volo", "Trasporto + alloggio + servizi a prezzo unico", "Un'assicurazione", "Un biglietto museale"), 1,
                "Combinazione di più servizi a prezzo unico."),
            Question("Responsabilità", "Se l'hotel non corrisponde a quanto promesso, il turista può:",
                listOf("Non fare nulla", "Chiedere risarcimento", "Scrivere su internet solo", "Cambiare Paese"), 1,
                "Ha diritto al risarcimento per inesatta esecuzione."),
            Question("Normativa", "Le Regioni regolano in materia di turismo:",
                listOf("Solo i trasporti aerei", "Agenzie, professioni e promozione", "Il PIL", "Le valute"), 1,
                "Regolano agenzie di viaggio, professioni e promozione."),
            Question("Contratti", "Il diritto di recesso permette di:",
                listOf("Prenotare due volte", "Ritirarsi dal contratto", "Non pagare", "Cambiare nome"), 1,
                "Ritirarsi dal contratto alle condizioni previste."),
        ),
    ),

    // =========================================================================
    // INFORMATICA E TELECOMUNICAZIONI — INFORMATICA
    // =========================================================================
    Subject(
        id = "informatica", name = "Informatica", emoji = "💻", indirizzo = "Informatica e Telecomunicazioni", colorHex = 0xFF0277BD,
        chapters = listOf(
            Chapter(0, "Fondamenti di informatica", "🖥️",
                "Hardware, software, rappresentazione dei dati e reti.",
                listOf(
                    Section("Hardware e software", listOf(
                        Block.Text("L'hardware è la parte fisica del computer (CPU, RAM, disco, scheda video); il software è l'insieme dei programmi."),
                        Block.Text("La CPU esegue le istruzioni; la RAM è la memoria di lavoro veloce ma volatile; il disco (SSD/HDD) conserva i dati."),
                        Block.Bullet("Sistema operativo: gestisce hardware e applicazioni (es. Windows, Android, Linux)."),
                        Block.Bullet("Software applicativo: programmi per l'utente (es. Word, browser)."),
                        Block.Key("Hardware = corpo del computer; software = istruzioni che lo fanno funzionare."),
                    )),
                    Section("Rappresentazione dei dati", listOf(
                        Block.Text("Il computer lavora con il sistema binario: solo 0 e 1. Un bit è la cifra binaria; 8 bit formano un byte."),
                        Block.Text("I testi si codificano con tabelle (ASCII, Unicode); le immagini con pixel e i colori con RGB."),
                        Block.Key("Bit = 0 o 1; byte = 8 bit."),
                    )),
                )),
            Chapter(0, "Programmazione", "⌨️",
                "Algoritmi, linguaggi e struttura di un programma.",
                listOf(
                    Section("Algoritmi e diagrammi di flusso", listOf(
                        Block.Text("Un algoritmo è una sequenza finita di istruzioni che risolve un problema; si descrive con pseudocodice o diagrammi di flusso."),
                        Block.Text("Le struttura fondamentali sono sequenza, selezione (se…allora…altrimenti) e iterazione (ripeti mentre…)."),
                        Block.Key("Algoritmo = ricetta precisa: passi finiti e ordinati per arrivare al risultato."),
                    )),
                    Section("I linguaggi di programmazione", listOf(
                        Block.Text("I linguaggi (Python, Java, C++, JavaScript) traducono l'algoritmo in istruzioni per il computer."),
                        Block.Bullet("Variabile: contenitore di un valore (es. x = 5)."),
                        Block.Bullet("Condizione: «if» per prendere decisioni."),
                        Block.Bullet("Ciclo: «for» e «while» per ripetere istruzioni."),
                        Block.Key("Da problema → algoritmo → programma scritto in un linguaggio."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Hardware", "Parte fisica del computer."),
            GlossaryTerm("Software", "Insieme dei programmi che fanno funzionare il computer."),
            GlossaryTerm("CPU", "Processore: esegue le istruzioni dei programmi."),
            GlossaryTerm("Bit", "Cifra binaria: 0 o 1. Otto bit = un byte."),
            GlossaryTerm("Algoritmo", "Sequenza finita di istruzioni che risolve un problema."),
            GlossaryTerm("Variabile", "Contenitore di un valore in un programma."),
        ),
        flashcards = listOf(
            Flashcard("Fondamenti", "Che differenza c'è tra hardware e software?", "Hardware = fisico; software = programmi e istruzioni."),
            Flashcard("Fondamenti", "Quanti bit ci sono in un byte?", "8."),
            Flashcard("Fondamenti", "A cosa serve la RAM?", "È la memoria di lavoro veloce e volatile."),
            Flashcard("Programmazione", "Che cos'è un algoritmo?", "Una sequenza finita di istruzioni per risolvere un problema."),
            Flashcard("Programmazione", "Quali sono le strutture fondamentali?", "Sequenza, selezione (if) e iterazione (cicli)."),
            Flashcard("Programmazione", "A cosa serve una variabile?", "A conservare un valore durante l'esecuzione."),
        ),
        questions = listOf(
            Question("Fondamenti", "Il sistema binario usa solo:",
                listOf("0 e 1", "1 e 2", "A e B", "Nord e Sud"), 0,
                "Il computer lavora con 0 e 1."),
            Question("Fondamenti", "La memoria che perde i dati allo spegnimento è:",
                listOf("Il disco", "La RAM", "La scheda video", "Il monitor"), 1,
                "La RAM è volatile."),
            Question("Programmazione", "Un algoritmo deve essere:",
                listOf("Casuale", "Finito e ordinato", "Senza istruzioni", "Solo grafico"), 1,
                "Sequenza finita e ordinata di istruzioni."),
            Question("Programmazione", "L'istruzione che ripete un blocco si chiama:",
                listOf("Ciclo", "Funzione", "Procedura", "Commento"), 0,
                "I cicli (for/while) ripetono le istruzioni."),
            Question("Fondamenti", "Il sistema operativo:",
                listOf("È un'applicazione", "Gestisce hardware e applicazioni", "È la RAM", "È un virus"), 1,
                "Il sistema operativo è il software di base."),
            Question("Programmazione", "Python, Java e C++ sono:",
                listOf("Sistemi operativi", "Linguaggi di programmazione", "Hardware", "Tipi di RAM"), 1,
                "Sono linguaggi di programmazione."),
        ),
    ),

    // =========================================================================
    // INFORMATICA E TELECOMUNICAZIONI — SISTEMI E RETI
    // =========================================================================
    Subject(
        id = "sistemi-reti", name = "Sistemi e reti", emoji = "🖧", indirizzo = "Informatica e Telecomunicazioni", colorHex = 0xFF00838F,
        chapters = listOf(
            Chapter(0, "Le reti di computer", "🕸️",
                "Tipi di reti, modello ISO/OSI e dispositivi di rete.",
                listOf(
                    Section("Classificazione delle reti", listOf(
                        Block.Text("Una rete collega computer per condividere risorse e dati. In base all'estensione: PAN, LAN (locale), MAN (metropolitana), WAN (ampia, es. Internet)."),
                        Block.Bullet("LAN: una rete locale (scuola, ufficio, casa)."),
                        Block.Bullet("WAN: rete geografica che collega più reti."),
                        Block.Key("LAN = piccolo spazio; WAN = grande estensione (Internet è una WAN)."),
                    )),
                    Section("Il modello ISO/OSI", listOf(
                        Block.Text("Il modello ISO/OSI divide la comunicazione in 7 livelli: dal livello fisico (cavi e segnali) al livello applicazione (servizi per l'utente)."),
                        Block.Text("Ogni livello usa i servizi di quello inferiore e offre servizi a quello superiore."),
                        Block.Key("ISO/OSI = modello a 7 livelli che standardizza le comunicazioni di rete."),
                    )),
                )),
            Chapter(0, "Internet e sicurezza", "🔐",
                "Protocolli, indirizzi IP e sicurezza informatica.",
                listOf(
                    Section("Protocolli e indirizzi", listOf(
                        Block.Text("Il protocollo è l'insieme di regole della comunicazione. TCP/IP è la base di Internet; HTTP/HTTPS serve per il web."),
                        Block.Text("L'indirizzo IP identifica un dispositivo nella rete; il DNS traduce i nomi (www…com) in indirizzi IP."),
                        Block.Key("TCP/IP = linguaggio comune di Internet; DNS = rubrica che traduce i nomi in IP."),
                    )),
                    Section("Sicurezza informatica", listOf(
                        Block.Bullet("Firewall: filtro che protegge la rete dagli accessi non autorizzati."),
                        Block.Bullet("Crittografia: codifica dei dati per proteggerli durante la trasmissione."),
                        Block.Bullet("Antivirus e aggiornamenti: proteggono da malware e vulnerabilità."),
                        Block.Key("La difesa in profondità combina firewall, crittografia e aggiornamenti."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("LAN", "Rete locale che collega dispositivi in un'area limitata."),
            GlossaryTerm("WAN", "Rete geografica che collega reti distanti (es. Internet)."),
            GlossaryTerm("Protocollo", "Insieme di regole per la comunicazione tra sistemi."),
            GlossaryTerm("Indirizzo IP", "Identificatore numerico di un dispositivo in una rete."),
            GlossaryTerm("DNS", "Sistema che traduce i nomi di dominio in indirizzi IP."),
            GlossaryTerm("Firewall", "Sistema che filtra il traffico di rete e blocca accessi non autorizzati."),
        ),
        flashcards = listOf(
            Flashcard("Reti", "Differenza tra LAN e WAN?", "LAN è locale; WAN è geografica (es. Internet)."),
            Flashcard("Reti", "Quanti livelli ha il modello ISO/OSI?", "7."),
            Flashcard("Internet", "Quale protocollo è alla base di Internet?", "TCP/IP."),
            Flashcard("Internet", "A cosa serve il DNS?", "A tradurre i nomi dei siti in indirizzi IP."),
            Flashcard("Sicurezza", "A cosa serve il firewall?", "A filtrare il traffico e bloccare accessi non autorizzati."),
            Flashcard("Sicurezza", "Che cos'è la crittografia?", "La codifica dei dati per proteggerli."),
        ),
        questions = listOf(
            Question("Reti", "Internet può essere definita una:",
                listOf("LAN", "WAN", "PAN", "SAN"), 1,
                "Internet è la più grande rete geografica (WAN)."),
            Question("Reti", "Il livello che riguarda i cavi e i segnali è:",
                listOf("Applicazione", "Fisico", "Sessione", "Trasporto"), 1,
                "Il livello fisico è il primo del modello ISO/OSI."),
            Question("Internet", "L'indirizzo che identifica un dispositivo nella rete si chiama:",
                listOf("HTML", "IP", "URL", "USB"), 1,
                "Indirizzo IP."),
            Question("Sicurezza", "Un programma malevolo che danneggia il computer è un:",
                listOf("Firewall", "Malware", "Browser", "Driver"), 1,
                "Il malware (virus, worm, ransomware) è software dannoso."),
            Question("Sicurezza", "Proteggere i dati cifrandoli significa:",
                listOf("Eliminarli", "Codificarli", "Copiarli", "Stamparli"), 1,
                "La crittografia codifica i dati."),
            Question("Reti", "Il modello che standardizza la comunicazione in 7 livelli è:",
                listOf("TCP", "ISO/OSI", "HTTP", "HTML"), 1,
                "Il modello ISO/OSI."),
        ),
    ),

    // =========================================================================
    // INFORMATICA E TELECOMUNICAZIONI — TECNOLOGIE E PROGETTAZIONE
    // =========================================================================
    Subject(
        id = "tecn-progettazione", name = "Tecnologie e progettazione di sistemi informatici", emoji = "🛠️",
        indirizzo = "Informatica e Telecomunicazioni", colorHex = 0xFF37474F,
        chapters = listOf(
            Chapter(0, "Progettazione del software", "📐",
                "Il ciclo di vita del software e i database.",
                listOf(
                    Section("Il ciclo di vita del software", listOf(
                        Block.Text("Il software nasce con un ciclo di vita: analisi dei requisiti → progettazione → implementazione → test → manutenzione."),
                        Block.Text("Le metodologie agili (es. Scrum) sviluppano per piccoli incrementi con feedback continui dell'utente."),
                        Block.Key("Requisiti chiari e test continui riducono errori e costi."),
                    )),
                    Section("I database", listOf(
                        Block.Text("Un database è una raccolta organizzata di dati. Il modello relazionale usa tabelle collegate tra loro."),
                        Block.Text("SQL è il linguaggio per interrogare i database (SELECT, INSERT, UPDATE, DELETE)."),
                        Block.Key("Database relazionale = tabelle + SQL."),
                    )),
                )),
            Chapter(0, "Servizi web e sicurezza", "🔐",
                "Come funzionano i siti e i servizi online.",
                listOf(
                    Section("Architetture client-server", listOf(
                        Block.Text("Nel modello client-server il client (browser/app) chiede servizi al server, che li fornisce."),
                        Block.Text("Il web usa HTML per la struttura, CSS per lo stile, JavaScript per l'interattività."),
                    )),
                    Section("Sicurezza delle applicazioni", listOf(
                        Block.Bullet("Autenticazione: verificare chi è l'utente (password, 2FA)."),
                        Block.Bullet("Ruoli e permessi: ognuno accede solo a ciò che gli spetta."),
                        Block.Bullet("Backup: copie di sicurezza dei dati."),
                        Block.Key("La sicurezza si progetta dall'inizio: autenticazione, permessi, backup."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Requisiti", "Cosa deve fare il software, stabiliti con l'utente."),
            GlossaryTerm("Ciclo di vita", "Fasi della vita del software: analisi, progettazione, implementazione, test, manutenzione."),
            GlossaryTerm("Database", "Raccolta organizzata di dati."),
            GlossaryTerm("SQL", "Linguaggio per interrogare e gestire i database relazionali."),
            GlossaryTerm("Client-server", "Modello in cui il client chiede servizi al server."),
            GlossaryTerm("Backup", "Copia di sicurezza dei dati."),
        ),
        flashcards = listOf(
            Flashcard("Progettazione", "Quali sono le fasi del ciclo di vita del software?", "Analisi, progettazione, implementazione, test, manutenzione."),
            Flashcard("Progettazione", "A cosa serve il SQL?", "A interrogare e gestire i database."),
            Flashcard("Web", "Quale linguaggio dà la struttura a una pagina web?", "HTML."),
            Flashcard("Web", "Nel modello client-server, chi fornisce i servizi?", "Il server."),
            Flashcard("Sicurezza", "La verifica dell'identità dell'utente si chiama…", "Autenticazione."),
        ),
        questions = listOf(
            Question("Progettazione", "La fase in cui si scrive il codice è:",
                listOf("Analisi", "Implementazione", "Manutenzione", "Vendita"), 1,
                "L'implementazione è la scrittura del codice."),
            Question("Progettazione", "Un database relazionale è organizzato in:",
                listOf("File", "Tabelle", "Pagine", "Cartelle"), 1,
                "Tabelle collegate tra loro."),
            Question("Web", "Il linguaggio per lo stile delle pagine è:",
                listOf("HTML", "CSS", "SQL", "Java"), 1,
                "CSS definisce lo stile."),
            Question("Web", "Nel modello client-server il client:",
                listOf("Fornisce i servizi", "Chiede i servizi", "Solo memorizza", "Gestisce la rete"), 1,
                "Il client chiede, il server fornisce."),
            Question("Sicurezza", "Una password di sicurezza tra due verifiche si chiama:",
                listOf("2FA", "HTML", "API", "URL"), 0,
                "Autenticazione a due fattori."),
        ),
    ),

    // =========================================================================
    // INFORMATICA E TELECOMUNICAZIONI — GESTIONE PROGETTO
    // =========================================================================
    Subject(
        id = "gestione-progetto", name = "Gestione progetto e organizzazione d'impresa", emoji = "📈",
        indirizzo = "Informatica e Telecomunicazioni", colorHex = 0xFF00897B,
        chapters = listOf(
            Chapter(0, "Il progetto", "🎯",
                "Fasi, obiettivi e strumenti di gestione di un progetto.",
                listOf(
                    Section("Che cos'è un progetto", listOf(
                        Block.Text("Il progetto è un insieme di attività con un obiettivo, tempi e risorse definite; è unico e ha inizio e fine."),
                        Block.Text("Gli stakeholder sono tutti i soggetti interessati al progetto (clienti, utenti, team, fornitori)."),
                        Block.Key("Progetto = attività uniche, con obiettivo, tempi e risorse."),
                    )),
                    Section("Fasi e strumenti", listOf(
                        Block.Bullet("Fasi: avvio, pianificazione, esecuzione, monitoraggio, chiusura."),
                        Block.Bullet("WBS: scomposizione del lavoro in attività più piccole."),
                        Block.Bullet("Diagramma di Gantt: rappresentazione visiva di attività e tempi."),
                        Block.Key("Il Gantt mostra attività, durate e dipendenze in un grafico a barre."),
                    )),
                )),
            Chapter(0, "Organizzazione e costi", "🏢",
                "La struttura aziendale e la stima dei costi di progetto.",
                listOf(
                    Section("L'organizzazione aziendale", listOf(
                        Block.Text("L'azienda si organizza per funzioni (produzione, marketing, IT) o per progetto; le organizzazioni moderne sono per processi."),
                        Block.Text("Il project manager coordina persone, tempi e risorse per raggiungere l'obiettivo."),
                    )),
                    Section("Costi e tempi", listOf(
                        Block.Text("Il budget stima i costi: personale, attrezzature, licenze; le milestone sono tappe di controllo."),
                        Block.Text("Rischio di progetto: evento incerto che può influire su costi o tempi; si valuta probabilità e impatto."),
                        Block.Key("Gestire i rischi = valutare probabilità e impatto, con un piano di mitigazione."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Progetto", "Insieme di attività uniche con obiettivo, tempi e risorse definite."),
            GlossaryTerm("Stakeholder", "Soggetto interessato al progetto (cliente, utente, team, fornitore)."),
            GlossaryTerm("WBS", "Scomposizione del lavoro del progetto in attività più piccole."),
            GlossaryTerm("Gantt", "Diagramma a barre che mostra attività e tempi del progetto."),
            GlossaryTerm("Milestone", "Tappa di controllo del progetto."),
            GlossaryTerm("Budget", "Stima dei costi del progetto."),
        ),
        flashcards = listOf(
            Flashcard("Progetto", "Quali sono le fasi di un progetto?", "Avvio, pianificazione, esecuzione, monitoraggio, chiusura."),
            Flashcard("Progetto", "A cosa serve la WBS?", "A scomporre il lavoro in attività più piccole."),
            Flashcard("Strumenti", "Il diagramma di Gantt serve a…", "Mostrare attività, tempi e dipendenze."),
            Flashcard("Organizzazione", "Chi coordina persone e risorse del progetto?", "Il project manager."),
            Flashcard("Costi", "Che cos'è una milestone?", "Una tappa di controllo."),
        ),
        questions = listOf(
            Question("Progetto", "Un'attività con obiettivo, tempi e risorse definite è un…",
                listOf("Processo", "Progetto", "Prodotto", "Fornitore"), 1,
                "Il progetto è unico con inizio e fine."),
            Question("Strumenti", "Il diagramma a barre che mostra i tempi è:",
                listOf("Gantt", "ISO/OSI", "PIL", "DNS"), 0,
                "Il diagramma di Gantt."),
            Question("Organizzazione", "I clienti e gli utenti di un progetto sono:",
                listOf("Fornitori", "Stakeholder", "Concorrenti", "Azionisti"), 1,
                "Gli stakeholder sono tutti gli interessati."),
            Question("Costi", "Il budget serve a:",
                listOf("Vendere di più", "Stimare i costi del progetto", "Assumere", "Pubblicizzare"), 1,
                "Il budget stima i costi."),
            Question("Progetto", "La tappa di controllo durante il progetto è una:",
                listOf("Milestone", "Funzione", "Release", "Sanzione"), 0,
                "Le milestone verificano l'avanzamento."),
        ),
    ),

    // =========================================================================
    // INFORMATICA E TELECOMUNICAZIONI — TELECOMUNICAZIONI
    // =========================================================================
    Subject(
        id = "telecomunicazioni", name = "Telecomunicazioni", emoji = "📡",
        indirizzo = "Informatica e Telecomunicazioni", colorHex = 0xFF4E342E,
        chapters = listOf(
            Chapter(0, "Segnali e trasmissione", "📶",
                "Segnali analogici e digitali, modulazione e mezzi trasmissivi.",
                listOf(
                    Section("Segnali analogici e digitali", listOf(
                        Block.Text("Il segnale analogico varia con continuità (es. voce); il segnale digitale assume valori discreti (0 e 1)."),
                        Block.Text("Il digitale è più robusto ai disturbi e può essere compresso e corretto dagli errori."),
                        Block.Key("Analogico = continuo; digitale = discretizzato in bit."),
                    )),
                    Section("Modulazione e mezzi", listOf(
                        Block.Text("La modulazione adatta il segnale al mezzo: la radio modula su onde portanti (AM, FM)."),
                        Block.Bullet("Doppino in rame: reti telefoniche."),
                        Block.Bullet("Fibra ottica: luce, altissime velocità, immunità ai disturbi."),
                        Block.Bullet("Wireless: onde radio e microonde."),
                        Block.Key("La fibra ottica trasmette dati con la luce ed è la più veloce."),
                    )),
                )),
            Chapter(0, "Reti e servizi di telecomunicazione", "📱",
                "Telefonia mobile, banda e Internet.",
                listOf(
                    Section("La telefonia mobile", listOf(
                        Block.Text("La telefonia cellulare divide il territorio in celle, ciascuna servita da una stazione radio base."),
                        Block.Text("Le generazioni: 4G (velocità elevate) e 5G (bassissima latenza, IoT, auto connesse)."),
                        Block.Key("Cella = area servita da una stazione radio; l'utente passa da una cella all'altra in roaming."),
                    )),
                    Section("Banda e servizi", listOf(
                        Block.Text("La banda è la capacità di trasmettere: la larghezza di banda si misura in bit al secondo (bit/s)."),
                        Block.Text("La fibra e il 5G abilitano servizi moderni: streaming HD, cloud, smart home, telemedicina."),
                    )),
                )),
        ),
        glossary = listOf(
            GlossaryTerm("Segnale analogico", "Segnale che varia con continuità nel tempo."),
            GlossaryTerm("Segnale digitale", "Segnale con valori discreti (0 e 1)."),
            GlossaryTerm("Modulazione", "Tecnica per adattare un segnale al mezzo trasmissivo (es. AM, FM)."),
            GlossaryTerm("Fibra ottica", "Mezzo trasmissivo che usa la luce; velocità altissime e nessun disturbo."),
            GlossaryTerm("Cella", "Area coperta da una stazione radio nella telefonia mobile."),
            GlossaryTerm("Larghezza di banda", "Capacità di trasmissione, misurata in bit/s."),
        ),
        flashcards = listOf(
            Flashcard("Segnali", "Differenza tra segnale analogico e digitale?", "Analogico continuo; digitale a valori discreti (0/1)."),
            Flashcard("Segnali", "Perché la fibra ottica è così veloce?", "Trasmette con la luce ed è immune ai disturbi."),
            Flashcard("Segnali", "AM e FM sono tecniche di…", "Modulazione."),
            Flashcard("Mobile", "Come si chiama l'area servita da una stazione radio base?", "Cella."),
            Flashcard("Mobile", "Quale generazione mobile offre bassissima latenza?", "Il 5G."),
        ),
        questions = listOf(
            Question("Segnali", "Un segnale che assume solo valori 0 e 1 è:",
                listOf("Analogico", "Digitale", "Sinusoidale", "Termico"), 1,
                "Il digitale è discretizzato in bit."),
            Question("Segnali", "La fibra ottica trasmette:",
                listOf("Elettricità", "Luce", "Suono", "Calore"), 1,
                "Trasmette impulsi di luce."),
            Question("Mobile", "Nella telefonia cellulare il territorio è diviso in:",
                listOf("Celle", "Fusi", "Stati", "Token"), 0,
                "Ogni cella ha una stazione radio base."),
            Question("Mobile", "Il 5G rispetto al 4G offre:",
                listOf("Più latenza", "Bassissima latenza", "Meno velocità", "Nessun internet"), 1,
                "Latenza minima per IoT e auto connesse."),
            Question("Segnali", "La capacità di trasmissione si misura in:",
                listOf("Volt", "bit/s", "Metri", "Hertz"), 1,
                "La banda si misura in bit al secondo."),
        ),
    ),
)