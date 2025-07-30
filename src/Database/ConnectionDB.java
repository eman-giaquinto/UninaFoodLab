package Database;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionDB {
    //Connessione
    private static ConnectionDB conDB = null;
    //Connessione ad SQL
    private Connection conn_SQL = null;


    // costruttore della connessione
    public ConnectionDB() {
    }

    //Proviamo l'ottenimento della connessione (metodo)
    public static ConnectionDB getConnectionDB() {
        if (conDB == null) {
            conDB = new ConnectionDB();
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
