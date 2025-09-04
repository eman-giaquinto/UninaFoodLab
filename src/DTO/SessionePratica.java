package DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SessionePratica {

	private int idSessionePratica;
	
	private int numeroAdesioni;
	
	private LocalDate dataSessione;
	
	private Time orarioInizio;
	
	private Time orarioFine;
	
	private int fkCorso;

	public SessionePratica(int idSessionePratica, int numeroAdesioni, LocalDate dataSessione, Time orarioInizio,Time orarioFine, int fkCorso) {
		this.idSessionePratica = idSessionePratica;
		this.numeroAdesioni = numeroAdesioni;
		this.dataSessione = dataSessione;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.fkCorso = fkCorso;
	}
	
	public int getIdSessionePratica() {
		return idSessionePratica;
	}

	public int getNumeroAdesioni() {
		return numeroAdesioni;
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

	public int getFkCorso() {
		return fkCorso;
	}
    
}
