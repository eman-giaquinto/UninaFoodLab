package DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class SessioneOnline {

	private int idSessioneOnline;
	
	private Piattaforma piattaforma;
	
	private LocalDate dataSessione;
	
	private LocalTime orarioInizio;
	
	private LocalTime orarioFine;
	
	private String link;
	
	private Corso corsoDiRiferimento;

	/* Classe interna che rappresenta l' enum Piattaforma */
	public enum Piattaforma{
		GOOGLE_MEET("Google Meet"),
		ZOOM("Zoom"),
		MICROSOFT_TEAMS("Microsoft Teams"),
		CISCO_WEBEX("Cisco Webex");
		
		// Descrizione ottenuta dall' enum non modificabile
        private final String descrizione;
        
        // Costruttore per la creazione di un oggetto di tipo TipoCorso
        Piattaforma(String descrizione) {
            this.descrizione = descrizione;
        }

        public String getDescrizione() {
            return descrizione;
        }
        
        // Metodo pubblico e statico per essere chiamato dall' esterno
        public static Piattaforma ottieniPiattaformaFormattata(String descrizione) {
            for (Piattaforma tipo : Piattaforma.values()) {
                if (tipo.descrizione.equalsIgnoreCase(descrizione)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Nessuna piattaforma trovata per la descrizione: " + descrizione);
        }
	}
	
	// Costruttore per il recupero delle sessioni online
	public SessioneOnline(int idSessioneOnline, Piattaforma piattaforma, LocalDate dataSessione, LocalTime orarioInizio,
			LocalTime orarioFine, String link,Corso corsoDiRiferimento) {
		this.idSessioneOnline=idSessioneOnline;
		this.piattaforma=piattaforma;
		this.dataSessione=dataSessione;
		this.orarioInizio=orarioInizio;
		this.orarioFine=orarioFine;
		this.link=link;
		this.corsoDiRiferimento=corsoDiRiferimento;
	}
	
	// Costruttore per l' inserimento delle sessioni online
	public SessioneOnline(Piattaforma piattaforma, LocalDate dataSessione, LocalTime orarioInizio,
			LocalTime orarioFine, String link,Corso corsoDiRiferimento) {
		this.piattaforma=piattaforma;
		this.dataSessione=dataSessione;
		this.orarioInizio=orarioInizio;
		this.orarioFine=orarioFine;
		this.link=link;
		this.corsoDiRiferimento=corsoDiRiferimento;
	}

	public int getIdSessioneOnline() {
		return idSessioneOnline;
	}

	public void setIdSessioneOnline(int idSessioneOnline) {
		this.idSessioneOnline = idSessioneOnline;
	}

	public Piattaforma getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(Piattaforma piattaforma) {
		this.piattaforma = piattaforma;
	}

	public LocalDate getDataSessione() {
		return dataSessione;
	}

	public void setDataSessione(LocalDate dataSessione) {
		this.dataSessione = dataSessione;
	}

	public LocalTime getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(LocalTime orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public LocalTime getOrarioFine() {
		return orarioFine;
	}

	public void setOrarioFine(LocalTime orarioFine) {
		this.orarioFine = orarioFine;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Corso getCorsoDiRiferimento() {
		return corsoDiRiferimento;
	}

	public void setCorsoDiRiferimento(Corso corsoDiRiferimento) {
		this.corsoDiRiferimento = corsoDiRiferimento;
	}
	
	public static String[] ottieniDescrizioniPiattaforme() {
		Piattaforma[] tuttiITipi = Piattaforma.values();
        
        String[] descrizioniPiattaforme;
     
        descrizioniPiattaforme = new String[tuttiITipi.length];
            
            for (int i = 0; i < tuttiITipi.length; i++) {
            	descrizioniPiattaforme[i] = tuttiITipi[i].getDescrizione();
            }
   
        return descrizioniPiattaforme;
    }
	
}
