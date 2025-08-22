package DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessionePratica {

    private int idSessionePratica;
    private int numeroAdesioni;
    private LocalDate dataSessione;
    private LocalTime orarioInizio;
    private LocalTime orarioFine;

    public SessionePratica(int idSessionePratica, int numeroAdesioni, LocalDate dataSessione,
                            LocalTime orarioInizio, LocalTime orarioFine) {
    	super();
        this.idSessionePratica = idSessionePratica;
        this.numeroAdesioni = numeroAdesioni;
        this.dataSessione = dataSessione;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public int getIdSessionePratica() {
        return idSessionePratica;
    }
    public void setIdSessionePratica(int idSessionePratica) {
        this.idSessionePratica = idSessionePratica;
    }

    public int getNumeroAdesioni() {
        return numeroAdesioni;
    }
    public void setNumeroAdesioni(int numeroAdesioni) {
        this.numeroAdesioni = numeroAdesioni;
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
}
