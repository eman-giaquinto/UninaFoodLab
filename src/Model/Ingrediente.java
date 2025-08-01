package Model;

//Definisce la classe Ingrediente
public class Ingrediente {

    private String Nome;
    private String Tipologia;

    //definisco il costruttore della classe Ingrediente

    public Ingrediente(String Nome, String Tipologia){
        this.Nome = Nome;
        this.Tipologia = Tipologia;
    }

    //Ottengo i nomi degli Ingredienti

    public String GetNomeIngrediente(){
        return Nome;
    }

    //Ottengo i nomi delle tipologie degli Ingredienti

    public String GetTipologiaIngrediente(){
        return Tipologia;
    }


    //Da ragionare con Emanuele per eventuali metodi setter

}
