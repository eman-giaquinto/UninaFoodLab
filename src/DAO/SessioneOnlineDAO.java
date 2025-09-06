package DAO;

import java.util.ArrayList;

import DTO.SessioneOnline;
import DatabaseException.DBExceptionRisultatoIndefinito;
import DatabaseException.DBExceptionSessioniOnlineNonTrovate;

public interface SessioneOnlineDAO {
	/* LOGICA TASTO VISUALIZZA */
	ArrayList<SessioneOnline> ottieniSessioniOnline(int idCorsoRicavato) throws DBExceptionRisultatoIndefinito,
	DBExceptionSessioniOnlineNonTrovate;

}
