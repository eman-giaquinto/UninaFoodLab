package Database;

import java.sql.Connection;

public class ComunicazioneDB {
    private Connection conn_SQL = null;
    private ConnessioneDB connessioneDatabase = null;


    public ComunicazioneDB(){
        connessioneDatabase = ConnessioneDB.getConnectionDB();
        this.creaConnessione();
    }
    //istanzia la classe di connessione al DB

    protected Connection creaConnessione(){
        conn_SQL = connessioneDatabase.getConnection();


        if (conn_SQL == null) {
            System.out.println("Connessione non RIUSCITA!");
            System.exit(0);
        }

        System.out.println("Connessione OK!");

    return conn_SQL;
    }

}
