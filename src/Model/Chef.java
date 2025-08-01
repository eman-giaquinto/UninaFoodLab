package Model;

//Questa classe rappresenta lo Chef

public class Chef {
    private String Username;
    private String Password;
    private String Nome;
    private String Cognome;
    private String Descrizione;


//Definiamo il costruttore della classe Chef

    public Chef(String Username, String Password, String Nome, String Cognome, String Descrizione) {
        this.Username = Username;
        this.Password = Password;
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.Descrizione = Descrizione;
    }


    //Ottiene l'username dello Chef
    public String GetUsername() {
        return Username;
    }

    //Ottiene la password dello Chef
    public String GetPassword(){
        return Password;
    }

    //Ottiene il nome dello chef

    public String GetNomeChef(){
        return Nome;
    }

    //Ottiene il cognome dello Chef
    public String GetCognomeChef(){
        return Cognome;
    }

    //Ottiene la descrizione dello Chef
    public String GetDescrizione(){
        return Descrizione;
    }


    //Da ragionare con Emanuele per eventuali metodi setter

}







