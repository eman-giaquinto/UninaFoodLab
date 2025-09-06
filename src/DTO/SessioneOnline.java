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
	
	private int fkCorso;
	
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
	
	public SessioneOnline(int idSessioneOnline, Piattaforma piattaforma, LocalDate dataSessione, Time orarioInizio,
			Time orarioFine, String link, int fkCorso) {
		this.idSessioneOnline = idSessioneOnline;
		this.piattaforma = piattaforma;
		this.dataSessione = dataSessione;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.link = link;
		this.fkCorso = fkCorso;
	}

	public int getIdSessioneOnline() {
		return idSessioneOnline;
	}

	public Piattaforma getPiattaforma() {
		return piattaforma;
	}

	public LocalDate getDataSessione() {
		return dataSessione;
	}

	public Time getOrarioInizio() {
		return orarioInizio;
	}

	public Time getOrarioFine() {
		return orarioFine;
	}

	public String getLink() {
		return link;
	}

	public int getFkCorso() {
		return fkCorso;
	}

	
}
