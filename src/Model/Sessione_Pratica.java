package Model;

import java.time.LocalTime;
import java.time.LocalDate;

//Definisce la classe Sessione Pratica

public class Sessione_Pratica {

    private Integer IDSessione_Pratica;
    private Integer NumeroAdesioni;
    private LocalDate DataSessione;
    private LocalTime OrarioInizio;
    private LocalTime OrarioFine;
    private Integer FKCorso;

    //Definisco il costruttore della classe Sessione_Pratica

    public Sessione_Pratica(Integer IDSessione_Pratica , Integer NumeroAdesioni, LocalDate DataSessione,
                            LocalTime OrarioInizio, LocalTime OrarioFine, Integer FKCorso){

        this.IDSessione_Pratica = IDSessione_Pratica;
        this.NumeroAdesioni = NumeroAdesioni;
        this.DataSessione = DataSessione;
        this.OrarioFine = OrarioFine;
        this.OrarioInizio = OrarioInizio;
        this.FKCorso = FKCorso;

    }

    //Ottengo IDSessione_Pratica

    public Integer GetIDSessione_Pratica(){
        return IDSessione_Pratica;
    }

    //Ottengo il numero di adesioni

    public Integer GetNumeroAdesioni(){
        return NumeroAdesioni;
    }

    //Ottengo la data della Sessione

    public LocalDate GetDataSessionePratica(){
        return DataSessione;
    }

    //Ottengo Orario di Inizio della Sessione Pratica

    public LocalTime GetOrarioInizioSessione_Pratica(){
        return OrarioInizio;
    }

    //Ottengo l'orario di fine della Sessione Pratica

    public LocalTime GetOrarioFineSessione_Pratica(){
        return OrarioFine;
    }

    //Ottengo FKCorso

    public Integer GetFKCorso_Sessione_Pratica(){
        return FKCorso;
    }

    //Da ragionare con Emanuele per eventuali metodi setter

}
