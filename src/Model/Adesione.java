package Model;

//Definisco la classe Adesione

public class Adesione {

    private Integer FKIDUtente;
    private Integer FKSessione_Pratica;

    //Definisco il costruttore della classe Adesione

    public Adesione(Integer FKIDUtente , Integer FKSessione_Pratica){
        this.FKIDUtente = FKIDUtente;
        this.FKSessione_Pratica = FKSessione_Pratica;
    }

    //Ottengo FKIDUtente

    public Integer GetFKIDUtente(){
        return FKIDUtente;
    }

    //Ottengo FKSessione_Pratica

    public Integer GetFKSessione_Pratica(){
        return FKSessione_Pratica;
    }

    //Da ragionare con Emanuele per eventuali metodi setter

}
