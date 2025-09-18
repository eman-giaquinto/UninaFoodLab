package DAO;

import java.util.ArrayList;

import DTO.Ingrediente;
import DTO.Ricetta;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface IngredienteDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<Ingrediente> ottieniIngredientiRicetta(Ricetta ricettaDiRiferimento) throws DBExceptionRisultatoIndefinito;

}
