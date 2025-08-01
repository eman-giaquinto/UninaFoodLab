package Model;


//Definisce la classe Appartiene
public class Appartiene {

    private String FKNome_Ricetta;
    private String FKNome_Ingrediente;
    private Integer Quantitativo_Singolare;

    //definisco il costruttore della classe Appartiene

    public Appartiene (String FKNome_Ricetta, String FKNome_Ingrediente, Integer Quantitativo_Singolare)
    {
        this.FKNome_Ingrediente = FKNome_Ingrediente;
        this.FKNome_Ricetta = FKNome_Ricetta;
        this.Quantitativo_Singolare = Quantitativo_Singolare;
    }

    //Ottengo i quantitativi

    public Integer GetQuantitativo_Singolare(){
        return Quantitativo_Singolare;
    }

    //Ottengo i nomi degli ingredienti

    public String GetNome_Ingredienti(){
        return FKNome_Ingrediente;
    }


    //Ottengo i nomi delle ricette

    public String GetNome_Ricette(){
        return FKNome_Ricetta;
    }


    //Da ragionare con Emanuele per eventuali metodi setter


}
