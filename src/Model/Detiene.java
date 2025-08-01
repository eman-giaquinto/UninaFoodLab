package Model;

//Definisce la classe Detiene

public class Detiene {

    private Integer FKSessione_Pratica;
    private String FKNome_Ricetta;


    //Definisco il costruttore della classe Detiene

    public Detiene(Integer FKSessione_Pratica , String FKNome_Ricetta){
        this.FKSessione_Pratica = FKSessione_Pratica;
        this.FKNome_Ricetta = FKNome_Ricetta;
    }

    //Ottengo FKSessione_Pratica

    public Integer GetFKSessione_Pratica(){
        return FKSessione_Pratica;
    }

    //Ottengo FKNome_Ricetta

    public String GetFKNome_Ricetta(){
        return FKNome_Ricetta;
    }


    //Da ragionare con Emanuele per eventuali metodi setter

}
