package DAO;

import java.util.ArrayList;

import DTO.Corso;
import DTO.SessionePratica;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;

public interface SessionePraticaDAO  {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<SessionePratica> ottieniSessioniPratiche(Corso corsoSelezionato) throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniPraticheNonTrovate;
}
