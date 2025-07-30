package Database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnessioneDB {
    //Connessione
    private static ConnessioneDB conDB = null;
    //Connessione ad SQL
    private Connection conn_SQL = null;


    // costruttore della connessione
    private ConnessioneDB() {
    }

    //Proviamo l'ottenimento della connessione (metodo)
    protected static ConnessioneDB getConnectionDB() {
        if (conDB == null) {
            conDB = new ConnessioneDB();
        }

        //e la restituisce
        return conDB;
    }


    //ottenimento della conn_SQL (metodo)

    public Connection getConnection() {
        try {
            //se la connessione non esiste oppure è stata chiusa
            if (conn_SQL == null || conn_SQL.isClosed()) {
                //registra il driver
                Class.forName("org.postgresql.Driver");
                conn_SQL = DriverManager.getConnection("jdbc:postgresql://localhost:5432/UninaFoodLab?currentSchema=public", "postgres", "123");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return conn_SQL;

    }
}
