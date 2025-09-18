package DTO;

import java.util.ArrayList;

public class Ingrediente {

	private String nome;
	
	private ArrayList<Ricetta> ricetteDiAppartenenza;
	
	// Costruttore per il recupero di ingredienti
	public Ingrediente(String nome){
		this.nome = nome;
	}
	
	// Costruttore per il recupero di un ingrediente per più ricette
	public Ingrediente(String nome, ArrayList<Ricetta> ricetteDiAppartenenza){
		this.nome = nome;
		this.ricetteDiAppartenenza = new ArrayList<>(ricetteDiAppartenenza);;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Ricetta> getRicetteDiAppartenenza() {
		return ricetteDiAppartenenza;
	}

	public void setRicetteDiAppartenenza(ArrayList<Ricetta> ricetteDiAppartenenza) {
		this.ricetteDiAppartenenza = ricetteDiAppartenenza;
	}
	
}
