package Model;

//Questa classe definisce l'Utente
public class Utente {

    private Integer IDUtente;
    private String Nome;
    private String Cognome;

    //Definisco il costruttore della classe Utente

    public Utente (Integer IDUtente, String Nome, String Cognome)
    {
        this.IDUtente = IDUtente;
        this.Nome = Nome;
        this.Cognome = Cognome;
    }

    //Ottengo IDUtente

    public Integer GetIDUtente()
    {
        return IDUtente;
    }

    //Ottengo nome dell'utente

    public String GetNomeUtente(){
        return Nome;
    }

    //Ottengo cognome dell'utente

    public String GetCognomeUtente(){
        return Cognome;
    }


    //Da ragionare con Emanuele per eventuali metodi setter

}
