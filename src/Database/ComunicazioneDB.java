package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DatabaseException.DBExceptionChiusuraConnessioneNonRiuscita;
import DatabaseException.DBExceptionConnessioneNonRiuscita;
import DatabaseException.DBExceptionCreazioneStatementFallita;
import DatabaseException.DBExceptionOperazioneQueryDML;
import DatabaseException.DBExceptionRisultatoIndefinito;

public class ComunicazioneDB {
	
    private ConnessioneDB connessioneDB = null;
    private Connection connessioneSQL = null;
	private ResultSet risultato = null;
	private Statement statement = null;
	private int esitoPositivo;
	
	public ComunicazioneDB() throws DBExceptionConnessioneNonRiuscita, DBExceptionCreazioneStatementFallita {
		// Istanzio il singleton della connessione al DB oppure lo recupero
		connessioneDB = ConnessioneDB.getConnectionDB();
		
		creaConnessione();
	}
	
    protected Connection creaConnessione() throws DBExceptionConnessioneNonRiuscita, DBExceptionCreazioneStatementFallita{
    	// Creo la connessione per la prima volta se non c'è mai stata o è stata chiusa
    	connessioneSQL = connessioneDB.getConnection();

    	if(connessioneSQL==null) {
    		throw new DBExceptionConnessioneNonRiuscita();
    	}
    	
    	/* Se non ci sono stati problemi, creo il canale di comunicazione */
    	try {
			statement = connessioneSQL.createStatement();
    	} catch(SQLException e) {
    		throw new DBExceptionCreazioneStatementFallita();
    	}
    	
    	return connessioneSQL;
    }
    
    public ResultSet comunicazioneDBQuery(String comando) throws DBExceptionRisultatoIndefinito {
		/* Invio della query al DB con i dovuti controlli*/
		try { 
			risultato = statement.executeQuery(comando);
		} catch (SQLException e) {
				throw new DBExceptionRisultatoIndefinito();
		}
					
		return risultato;
	}
    
	public boolean prossimaTupla() throws DBExceptionRisultatoIndefinito  { 
		/* Verifico la corretta lettura della tupla con eventuali errori*/
		try {
			return risultato.next();
		} catch (SQLException e) {
			throw new DBExceptionRisultatoIndefinito();
		}
	}
	
	public int mandaQueryDML(String comando) throws SQLException {
		
		esitoPositivo = statement.executeUpdate(comando,Statement.RETURN_GENERATED_KEYS);
		
		// Se non vengono rilanciate eccezioni allora abbiamo avuto un esito positivo dell' operazione
		return esitoPositivo;
	
	}
	
//	public ResultSet numeroTuplaAggiunta() throws SQLException {
//	    risultato = statement.getGeneratedKeys();
//	    return risultato;
//	}
	
	public void terminaConnessione() throws DBExceptionChiusuraConnessioneNonRiuscita{
		/* Chiudo tutte le connessioni aperte con il database*/
		try {
			risultato.close();
			statement.close();
			connessioneSQL.close();
		} catch (SQLException e) {
			throw new DBExceptionChiusuraConnessioneNonRiuscita();
		}
	}
	    
}
