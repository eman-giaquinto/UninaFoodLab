package DAO;

import java.util.ArrayList;

import DTO.Corso;
import DTO.SessioneOnline;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;

public interface SessioneOnlineDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<SessioneOnline> ottieniSessioniOnline(Corso corsoSelezionato) throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniOnlineNonTrovate;

}
