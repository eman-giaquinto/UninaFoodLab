package DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class SessioneOnline {

	private int idSessioneOnline;
	
	private Piattaforma piattaforma;
	
	private LocalDate dataSessione;
	
	private Time orarioInizio;
	
	private Time orarioFine;
	
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
        public static Piattaforma fromDescrizione(String descrizione) {
            for (Piattaforma tipo : Piattaforma.values()) {
                if (tipo.descrizione.equalsIgnoreCase(descrizione)) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Nessuna piattaforma trovata per la descrizione: " + descrizione);
        }
	}
	
	// Costruttore per il recupero delle sessioni online
	public SessioneOnline(int idSessioneOnline, Piattaforma piattaforma, LocalDate dataSessione, Time orarioInizio,
			Time orarioFine, String link,Corso corsoDiRiferimento) {
		this.idSessioneOnline=idSessioneOnline;
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

	public Time getOrarioInizio() {
		return orarioInizio;
	}

	public void setOrarioInizio(Time orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public Time getOrarioFine() {
		return orarioFine;
	}

	public void setOrarioFine(Time orarioFine) {
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
	
}
