package DTO;

import java.util.ArrayList;

public class Chef {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	
	private ArrayList<Corso> corsiAssociati = new ArrayList<>();

	
	// Costruttore per la verifica dell' accesso
	public Chef(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// Costruttore per l' accesso eseguito con successo
	public Chef(String username, String password, String nome, String cognome) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public ArrayList<Corso> getCorsiAssociati() {
		return corsiAssociati;
	}

	public void setCorsiAssociati(ArrayList<Corso> corsiAssociati) {
		this.corsiAssociati = corsiAssociati;
	}
	
	public String getPresentazione() {
		return getNome() + " " + getCognome();
	}	

}
