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
        if (istanzaConnessioneDB == null) {
        	istanzaConnessioneDB = new ConnessioneDB();
        }
        return istanzaConnessioneDB;
    }
    
    public Connection getConnection() {
    	
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
            
        	} catch (FileNotFoundException e) {
        		messaggioErrore = "File di configurazione non trovato";
	        } catch (IOException e) {
	        	messaggioErrore = "Errore durante la lettura del file";
		    } catch (SQLException e) {
		    	messaggioErrore = "Errore nella comunicazione con il database";
		    } catch (ClassNotFoundException e) {
		    	messaggioErrore = "Driver connessione al database non trovato";
	        }

        return connessione_SQL;
    }
    
    
}
