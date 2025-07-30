package Controller;

import Database.ConnectionDB;

public class Controller {

    // Database
    private ConnectionDB connessioneDatabase;
    public static void main(String[] args) {
        System.out.println("Sono il controller");
        Controller c = new Controller();
    }

    public Controller(){
        connessioneDatabase = new ConnectionDB();
    }
}
