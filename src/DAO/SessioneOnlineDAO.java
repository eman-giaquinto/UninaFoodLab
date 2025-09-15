package DAO;

import java.util.ArrayList;

import DTO.Corso;
import DTO.SessioneOnline;
import DatabaseException.DBExceptionDataSessioneOnlineDiversaMensile;
import DatabaseException.DBExceptionDataSessioneOnlineDiversaSettimanale;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioneOnlineDuplicata;
import DatabaseException.DBExceptionSessioneOnlineLinkDuplicato;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;

public interface SessioneOnlineDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<SessioneOnline> ottieniSessioniOnline(Corso corsoSelezionato) throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniOnlineNonTrovate;
	
	/* LOGICA TASTO AGGIUNGI */
	void creaSessioneOnline(SessioneOnline sessioneOnlineDaAggiungere)
	throws DBExceptionOperazioneQueryDML,DBExceptionSessioneOnlineDuplicata,DBExceptionDataSessioneOnlineDiversaSettimanale,
	DBExceptionDataSessioneOnlineDiversaMensile,DBExceptionSessioneOnlineLinkDuplicato;

}
