package GUI;

import Database.ComunicazioneDB;
import Database.ConnessioneDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

    //LO STATEMENT CI SERVE PER ESEGUIRE QUERY
    ComunicazioneDB d1 = new ComunicazioneDB();

    Statement stmt = null;

        try {
        stmt = conn_SQL.createStatement();

        String comando4 = "SELECT * FROM registra";
        ResultSet rs = stmt.executeQuery(comando4);

        while (rs.next()) {
            int fkcorso = rs.getInt("fkcorso");
            String fkusername = rs.getString("fkusername");
            System.out.println("IDCorso: " + fkcorso + " Nome_Chef: " + fkusername + "");

        }

        rs.close();
        stmt.close();

    }catch (
    SQLException throwables) {
        throwables.printStackTrace();
        System.err.println( throwables.getClass().getName()+": "+ throwables.getMessage() );
        System.exit(0);
    }

}
