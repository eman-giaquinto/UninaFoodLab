package DAO;

import java.util.ArrayList;

import DTO.Ricetta;
import DatabaseException.DBExceptionRicetteNonTrovate;
import DatabaseException.DBExceptionRisultatoIndefinito;

public interface RicettaDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<Ricetta> ottieniRicette(int idSessionePraticaRicavata) throws DBExceptionRisultatoIndefinito,
	DBExceptionRicetteNonTrovate;

}
