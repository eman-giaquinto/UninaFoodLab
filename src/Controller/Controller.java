package Controller;

import Database.ComunicazioneDB;

public class Controller {

    // Database
    private ComunicazioneDB comunicaDatabase;
    public static void main(String[] args) {
        System.out.println("Sono il controller");
        Controller c = new Controller();
    }

    public Controller(){
        // apro la comunicazione con il database
        comunicaDatabase = new ComunicazioneDB();
    }
}
