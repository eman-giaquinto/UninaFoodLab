package Model;

//Definisce la classe Iscrizione
public class Iscrizione {

    private Integer FKIDUtente;
    private Integer FKCorso;

    //Definisco il costruttore della classe Iscrizione

    public Iscrizione (Integer FKIDUtente, Integer FKCorso)
    {
        this.FKIDUtente = FKIDUtente;
        this.FKCorso = FKCorso;
    }

    //Ottengo gli IDUtente
    public Integer GetFKIDUtente (){
        return FKIDUtente;
    }

    //Ottengo gli IDCorso
    public Integer GetFKCorso (){
        return FKCorso;
    }

    //Da ragionare con Emanuele per eventuali metodi setter

}
