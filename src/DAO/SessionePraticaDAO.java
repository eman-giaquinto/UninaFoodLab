package DAO;

import java.util.ArrayList;

import DTO.SessionePratica;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;

public interface SessionePraticaDAO  {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<SessionePratica> ottieniSessioniPratiche(int idCorsoRicavato) throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniPraticheNonTrovate;
}
