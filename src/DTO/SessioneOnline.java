package DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessioneOnline {

    private int idSessioneOnline;
    private Piattaforma piattaforma;
    private LocalDate dataSessione;
    private LocalTime orarioInizio;
    private LocalTime orarioFine;
    private String link;

    public SessioneOnline(int idSessioneOnline, Piattaforma piattaforma, LocalDate dataSessione,
                           LocalTime orarioInizio, LocalTime orarioFine, String link) {
    	super();
        this.idSessioneOnline = idSessioneOnline;
        this.piattaforma = piattaforma;
        this.dataSessione = dataSessione;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.link = link;
    }

    public enum Piattaforma {
        Google_Meet,
        Zoom,
        Microsoft_Teams,
        Cisco_Webex
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
}
