package DTO;

public class Ricetta {
	private String nome;
	
	private String descrizione;
	
	private GradoDifficoltà gradoDifficoltà;
	
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
        public static GradoDifficoltà fromDescrizione(String descrizione) {
            for (GradoDifficoltà tipo : GradoDifficoltà.values()) {
                if (tipo.descrizione.equalsIgnoreCase(descrizione)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Nessun tipo di grado difficoltà trovato per la descrizione: " + descrizione);
        }
	        
	}
	
	public Ricetta(String nome, String descrizione, GradoDifficoltà gradoDifficoltà) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.gradoDifficoltà = gradoDifficoltà;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public GradoDifficoltà getGradoDifficoltà() {
		return gradoDifficoltà;
	}
		
}