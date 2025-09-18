package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {
	
    private static ConnessioneDB istanzaConnessioneDB = null;
    private Connection connessione_SQL = null;
    protected String messaggioErrore;

        
    private ConnessioneDB() {}

    public static ConnessioneDB getConnectionDB() {
    	// Se l' istanza di connessione al DB non è stata mai fatta, la creo per la prima volta
        if (istanzaConnessioneDB == null) {
        	istanzaConnessioneDB = new ConnessioneDB();
        }
        return istanzaConnessioneDB;
    }
    
    public Connection getConnection() throws FileNotFoundException,IOException,SQLException,ClassNotFoundException {
    	
        String urlDB = null;
        String userDB = null; 
        String passwordDB = null;
        BufferedReader buffer = null;
        
        try {
        	// Quando la connessione non esiste oppure è stata chiusa 
            if (connessione_SQL == null || connessione_SQL.isClosed()) {
            	
	            // Registro il driver
	            Class.forName("org.postgresql.Driver");
	                
	            /* Leggo dal file e ricavo url,user, e password del Database*/
				buffer = new BufferedReader(new FileReader(new File("src/configurationDB/configDB.txt")));
		                
				urlDB = buffer.readLine();
		        userDB = buffer.readLine();
		        passwordDB = buffer.readLine();
		                
		        buffer.close();
		        
	            // Chiamata al DriverManager per ottenere la connessione con i parametri necessari   
                connessione_SQL = DriverManager.getConnection(urlDB, userDB, passwordDB);
            	}
            
            /* Rilancio eccezioni alla finestra di login*/
        	} catch (FileNotFoundException err) {
        		throw err;
	        } catch (IOException err) {
        		throw err;
		    } catch (SQLException err) {
        		throw err;
		    } catch (ClassNotFoundException err) {
        		throw err;
	        }

        return connessione_SQL;
    }
    
    
}
