package Model;

//Definisce la classe Ricetta
public class Ricetta {

    public enum enum_GradoDiff{Facile, Medio, Difficile}

    private String Nome;
    private String Descrizione;
    private enum_GradoDiff GradoDiff;

    //Definisco il costruttore della classe Ricetta

    public Ricetta(String Nome, String Descrizione, enum_GradoDiff GradoDiff){
        this.Nome = Nome;
        this.Descrizione = Descrizione;
        this.GradoDiff = GradoDiff;
    }

    //Ottengo nomi delle ricette

    public String GetNomeRicetta(){
        return Nome;
    }

    //Ottengo Descrizione delle Ricette

    public String GetDescrizioneRicetta(){
        return Descrizione;
    }

    //Ottengo gradi di difficoltà di preparazione delle ricette

    public enum_GradoDiff GetGradoDiff(){
        return GradoDiff;
    }


    //Da ragionare con Emanuele per eventuali metodi setter


}
