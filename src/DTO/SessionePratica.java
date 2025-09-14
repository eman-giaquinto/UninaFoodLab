package DTO;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SessionePratica {

	private int idSessionePratica;
	
	private int numeroAdesioni;
	
	private LocalDate dataSessione;
	
	private LocalTime orarioInizio;
	
	private LocalTime orarioFine;
	
	private Corso corsoDiRiferimento;
	
	private ArrayList<Ricetta> ricetteAssociate = new ArrayList<>();
	
	// Costruttore per il recupero delle sessioni pratiche
	public SessionePratica(int idSessionePratica, int numeroAdesioni, LocalDate dataSessione, LocalTime orarioInizio,
			LocalTime orarioFine,Corso corsoDiRiferimento) {
		this.idSessionePratica = idSessionePratica;
		this.numeroAdesioni = numeroAdesioni;
		this.dataSessione = dataSessione;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.corsoDiRiferimento=corsoDiRiferimento;
	}
	
	// Costruttore per per l'inserimento delle sessioni pratiche
	public SessionePratica(int numeroAdesioni, LocalDate dataSessione, LocalTime orarioInizio,LocalTime orarioFine,
			Corso corsoDiRiferimento) {
		this.numeroAdesioni = numeroAdesioni;
		this.dataSessione = dataSessione;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.corsoDiRiferimento=corsoDiRiferimento;
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

	public Corso getCorsoDiRiferimento() {
		return corsoDiRiferimento;
	}

	public void setCorsoDiRiferimento(Corso corsoDiRiferimento) {
		this.corsoDiRiferimento = corsoDiRiferimento;
	}

	public ArrayList<Ricetta> getRicetteAssociate() {
		return ricetteAssociate;
	}

	public void setRicetteAssociate(ArrayList<Ricetta> ricetteAssociate) {
		this.ricetteAssociate = ricetteAssociate;
	}
    
}
