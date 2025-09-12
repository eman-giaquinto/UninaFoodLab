package DAO;

import java.util.ArrayList;

import DTO.Ricetta;
import DTO.SessionePratica;
import DatabaseException.DBExceptionRicetteNonTrovate;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface RicettaDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<Ricetta> ottieniRicetteSessionePratica(SessionePratica sessionePraticaSelezionata) 
	throws DBExceptionRisultatoIndefinito,DBExceptionRicetteNonTrovate;

}
