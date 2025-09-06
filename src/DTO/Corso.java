package DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Corso {
	private int idCorso;
	
	private TipoCorso tipoDiCorso;
	
	private LocalDate dataInizio;
	
	private FrequenzaSessione frequenzaSessione;
	
	private LocalDate dataFine;
		
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
        public static TipoCorso ottieniTipoDaDescrizione(String descrizione) {
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

        // Costruttore per la creazione di un oggetto di tipo TipoCorso
        FrequenzaSessione(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getDescrizione() {
            return descrizione;
        }
        
        // Metodo pubblico e statico per essere chiamato dall' esterno
        public static FrequenzaSessione ottieniTipoDaDescrizione(String descrizione) {
            for (FrequenzaSessione freq : FrequenzaSessione.values()) {
                if (freq.descrizione.equalsIgnoreCase(descrizione)) {
                    return freq;
                }
            }
            throw new IllegalArgumentException("Nessuna frequenza sessione trovata per la descrizione: " + descrizione);
        }
           
    }

	public Corso(int idCorso, TipoCorso tipoDiCorso, LocalDate dataInizio, FrequenzaSessione frequenzaSessione,
			LocalDate dataFine) {
		this.idCorso = idCorso;
		this.tipoDiCorso = tipoDiCorso;
		this.dataInizio = dataInizio;
		this.frequenzaSessione = frequenzaSessione;
		this.dataFine = dataFine;
	}
	
	public int getIdCorso() {
		return idCorso;
	}

	public TipoCorso getTipoDiCorso() {
		return tipoDiCorso;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public FrequenzaSessione getFrequenzaSessione() {
		return frequenzaSessione;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}
	
	public static String[] ottieniDescrizioniTipiDiCorso() {
        TipoCorso[] tuttiITipi = TipoCorso.values();
        
        //Creo un array più grande di uno per fare spazio all'elemento iniziale
        String[] descrizioni = new String[tuttiITipi.length + 1];
        
        descrizioni[0] = "Tutti";

        //Itero e completo il resto dell'array, partendo dall'indice 1
        for (int i = 0; i < tuttiITipi.length; i++) {
            descrizioni[i + 1] = tuttiITipi[i].getDescrizione();
        }
        
        return descrizioni;
    }
	
	
	
	
	
}
