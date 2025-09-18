package DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Corso {
	private int idCorso;
	
	private TipoCorso tipoDiCorso;
	
	private LocalDate dataInizio;
	
	private FrequenzaSessione frequenzaSessione;
	
	private LocalDate dataFine;
	
	private Chef chefDiRiferimento;
	
	private ArrayList<SessionePratica> sessioniPratiche = new ArrayList<>();

	private ArrayList<SessioneOnline> sessioniOnline = new ArrayList<>();
		
	/* Classe interna che rappresenta l' enum TipoCorso */
	public enum TipoCorso {
	    PANIFICAZIONE("Panificazione"),
	    CUCINA_ASIATICA("Cucina Asiatica"),
	    PASTICCERIA("Pasticceria"),
	    CUCINA_NAPOLETANA("Cucina Napoletana"),
	    ROSTICCERIA("Rosticceria"),
	    CUCINA_VEGETARIANA("Cucina Vegetariana"),
	    CUCINA_FRANCESE("Cucina Francese"),
	    GELATERIA("Gelateria"),
	    PIZZERIA("Pizzeria"),
	    CUCINA_GRECA("Cucina Greca"),
	    CONFETTERIA("Confetteria"),
	    CUCINA_MESSICANA("Cucina Messicana");
		
		// Descrizione ottenuta dall' enum non modificabile
        private final String descrizione;

        // Costruttore per la creazione di un oggetto di tipo TipoCorso
        TipoCorso(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getDescrizione() {
            return descrizione;
        }
        
        // Metodo pubblico e statico per essere chiamato dall' esterno
        public static TipoCorso ottieniTipoDiCorsoFormattato(String descrizione) {
            for (TipoCorso tipo : TipoCorso.values()) {
                if (tipo.descrizione.equalsIgnoreCase(descrizione)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Nessun tipo di corso trovato per la descrizione: " + descrizione);
        }
           
    }
	
	/* Classe interna che rappresenta l' enum FrequenzaSessione */
	public enum FrequenzaSessione {
        GIORNALIERA("Giornaliera"),
        SETTIMANALE("Settimanale"),
        MENSILE("Mensile");

		// Descrizione ottenuta dall' enum non modificabile
        private final String descrizione;

        // Costruttore per la creazione di un oggetto di tipo FrequenzaSessione
        FrequenzaSessione(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getDescrizione() {
            return descrizione;
        }
        
        // Metodo pubblico e statico per essere chiamato dall' esterno
        public static FrequenzaSessione ottieniFrequenzaSessioneFormattata(String descrizione) {
            for (FrequenzaSessione freq : FrequenzaSessione.values()) {
                if (freq.descrizione.equalsIgnoreCase(descrizione)) {
                    return freq;
                }
            }
            throw new IllegalArgumentException("Nessuna frequenza sessione trovata per la descrizione: " + descrizione);
        }
           
    }

	// Costruttore per il recupero dei corsi
	public Corso(int idCorso, TipoCorso tipoDiCorso, LocalDate dataInizio, FrequenzaSessione frequenzaSessione,
			LocalDate dataFine,Chef chefDiRiferimento) {
		this.idCorso = idCorso;
		this.tipoDiCorso = tipoDiCorso;
		this.dataInizio = dataInizio;
		this.frequenzaSessione = frequenzaSessione;
		this.dataFine = dataFine;
		this.chefDiRiferimento = chefDiRiferimento;

	}
	
	// Costruttore per l'inserimento dei corsi
	public Corso(TipoCorso tipoDiCorso, LocalDate dataInizio, FrequenzaSessione frequenzaSessione,
			LocalDate dataFine,Chef chefDiRiferimento) {
		this.tipoDiCorso = tipoDiCorso;
		this.dataInizio = dataInizio;
		this.frequenzaSessione = frequenzaSessione;
		this.dataFine = dataFine;
		this.chefDiRiferimento = chefDiRiferimento;
	}

	public int getIdCorso() {
		return idCorso;
	}

	public void setIdCorso(int idCorso) {
		this.idCorso = idCorso;
	}

	public TipoCorso getTipoDiCorso() {
		return tipoDiCorso;
	}

	public void setTipoDiCorso(TipoCorso tipoDiCorso) {
		this.tipoDiCorso = tipoDiCorso;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public FrequenzaSessione getFrequenzaSessione() {
		return frequenzaSessione;
	}

	public void setFrequenzaSessione(FrequenzaSessione frequenzaSessione) {
		this.frequenzaSessione = frequenzaSessione;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public Chef getChefDiRiferimento() {
		return chefDiRiferimento;
	}
	
	public void setChefDiRiferimento(Chef chefDiRiferimento) {
		this.chefDiRiferimento = chefDiRiferimento;
	}

	public ArrayList<SessionePratica> getSessioniPratiche() {
		return sessioniPratiche;
	}

	public void setSessioniPratiche(ArrayList<SessionePratica> sessioniPratiche) {
		this.sessioniPratiche = sessioniPratiche;
	}

	public ArrayList<SessioneOnline> getSessioniOnline() {
		return sessioniOnline;
	}

	public void setSessioniOnline(ArrayList<SessioneOnline> sessioniOnline) {
		this.sessioniOnline = sessioniOnline;
	}

	// Metodo utilizzato per il filtraggio dei corsi e per aggiungere un corso
	public static String[] ottieniDescrizioniTipiDiCorsi(String aggiungiFiltro) {
        TipoCorso[] tuttiITipi = TipoCorso.values();
        String[] descrizioniTipiDiCorso;
        
        if(aggiungiFiltro.equals("Tutti"))
        {
            //Creo un array più grande di uno per fare spazio all'elemento iniziale
            descrizioniTipiDiCorso = new String[tuttiITipi.length + 1];
            descrizioniTipiDiCorso[0] = "Tutti";
            
            //Itero e completo il resto dell'array, partendo dall'indice 1
            for (int i = 0; i < tuttiITipi.length; i++) {
            	descrizioniTipiDiCorso[i + 1] = tuttiITipi[i].getDescrizione();
            }
            
        }
        else
        {
        	/* Restituisco solo le descrizioni dei tipi di corsi */
            descrizioniTipiDiCorso = new String[tuttiITipi.length];
            
            for (int i = 0; i < tuttiITipi.length; i++) {
            	descrizioniTipiDiCorso[i] = tuttiITipi[i].getDescrizione();
            }
        }
        return descrizioniTipiDiCorso;
    }
	
	// Metodo utilizzato per l' aggiunta di un corso
	public static String[] ottieniDescrizioniFrequenzeSessioni() {
		// Ottengo i valori dell' enum 
        FrequenzaSessione[] tuttiITipi = FrequenzaSessione.values();
        
        String[] descrizioniFrequenzeSessioni;
     
        /* Creo un array String e inserisco le descrizioni dei valori recuperati prima*/
        descrizioniFrequenzeSessioni = new String[tuttiITipi.length];
            
        for (int i = 0; i < tuttiITipi.length; i++) {
        	descrizioniFrequenzeSessioni[i] = tuttiITipi[i].getDescrizione();
        }
            
        return descrizioniFrequenzeSessioni;
    }
}
