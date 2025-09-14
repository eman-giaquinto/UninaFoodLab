package DAO;

import java.util.ArrayList;

import DTO.Corso;
import DTO.SessionePratica;
import DatabaseException.DBExceptionDataSessioneFuoriRange;
import DatabaseException.DBExceptionDataSessionePraticaDiversaMensile;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessionePraticaDuplicata;
import DatabaseException.DBExceptionSessioniPraticheNonTrovate;

public interface SessionePraticaDAO  {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<SessionePratica> ottieniSessioniPratiche(Corso corsoSelezionato) throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniPraticheNonTrovate;
	
	/* LOGICA TASTO AGGIUNGI */
	void creaSessionePratica(SessionePratica sessionePraticaDaAggiungere) throws DBExceptionOperazioneQueryDML,
	DBExceptionDataSessioneFuoriRange,DBExceptionSessionePraticaDuplicata,DBExceptionDataSessionePraticaDiversaMensile;
}
