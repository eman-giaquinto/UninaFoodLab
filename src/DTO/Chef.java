package DTO;

public class Chef {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String descrizione;
	
	public Chef(String username, String password, String nome, String cognome, String descrizione) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.descrizione = descrizione;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getPresentazione() {
		return getNome() + " " + getCognome();
	}
	

}
