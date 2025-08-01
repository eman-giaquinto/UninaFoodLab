package Model;


//Definisce la classe Registra

public class Registra {

    private String FKUsername;
    private Integer FKCorso;

    //Definisco il costruttore della classe Registra

    public Registra(String FKUsername, Integer FKCorso){
        this.FKCorso = FKCorso;
        this.FKUsername = FKUsername;
    }

    //Ottiene FKCorso
    public Integer GetFKCorso(){
        return FKCorso;
    }

    //Ottiene FKUsername
    public String GetFKUsername (){
        return FKUsername;
    }

    //Da ragionare con Emanuele per eventuali metodi setter

}
