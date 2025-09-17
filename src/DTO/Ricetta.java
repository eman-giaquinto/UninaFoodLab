package DTO;

import java.util.ArrayList;

public class Ricetta {
	private String nome;
	
	private String descrizione;
	
	private GradoDifficoltà gradoDifficoltà;
	
	private ArrayList<SessionePratica> sessioniPratiche = new ArrayList<>();
	
	//private ArrayList<Ingrediente> ingredienti = new ArrayList<>();
	
	// Enum per il grado di difficoltà delle ricette
	public enum GradoDifficoltà {
	    FACILE("Facile"),
	    MEDIO("Medio"),
	    DIFFICILE("Difficile");

		// Descrizione ottenuta dall' enum non modificabile
        private final String descrizione;

        // Costruttore per la creazione di un oggetto di tipo grado difficoltà
		GradoDifficoltà(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getDescrizione() {
            return descrizione;
        }
        
        // Questo metodo deve essere pubblico per essere chiamato dall'esterno
        public static GradoDifficoltà ottieniGradoDiDifficoltàFormattata(String descrizione) {
            for (GradoDifficoltà tipo : GradoDifficoltà.values()) {
                if (tipo.descrizione.equalsIgnoreCase(descrizione)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Nessun tipo di grado difficoltà trovato per la descrizione: " + descrizione);
        }
	        
	}
	
	// Costruttore per il recupero delle ricette associate ad una sessione pratica
	public Ricetta(String nome, String descrizione, GradoDifficoltà gradoDifficoltà) {
		this.nome=nome;
		this.descrizione=descrizione;
		this.gradoDifficoltà=gradoDifficoltà;
	}
	
	// Costruttore per l' associazione di una ricetta ad una sessione pratica
	public Ricetta(String nome) {
		this.nome=nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public GradoDifficoltà getGradoDifficoltà() {
		return gradoDifficoltà;
	}

	public void setGradoDifficoltà(GradoDifficoltà gradoDifficoltà) {
		this.gradoDifficoltà = gradoDifficoltà;
	}

	public ArrayList<SessionePratica> getSessioniPratiche() {
		return sessioniPratiche;
	}

	public void setSessioniPratiche(ArrayList<SessionePratica> sessioniPratiche) {
		this.sessioniPratiche = sessioniPratiche;
	}

		
}