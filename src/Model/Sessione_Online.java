package Model;


import java.time.LocalDate;
import java.time.LocalTime;

//Definisce la classe Sessione_Online

public class Sessione_Online {

    public enum enum_piattaforma{GoogleMeet, CiscoWebex, Zoom, MicrosoftTeams}

    private Integer IDSessione_Online;
    private enum_piattaforma Piattaforma;
    private LocalDate DataSessione;
    private LocalTime OrarioInizio;
    private LocalTime OrarioFine;
    private String Link;
    private Integer FKCorso;

    //Definisco il costruttore della classe Sessione_Online

    public Sessione_Online(Integer IDSessione_Online , enum_piattaforma Piattaforma , LocalDate DataSessione,
    LocalTime OrarioInizio, LocalTime OrarioFine, String Link, Integer FKCorso){

        this.IDSessione_Online = IDSessione_Online;
        this.Piattaforma = Piattaforma;
        this.DataSessione = DataSessione;
        this.OrarioInizio = OrarioInizio;
        this.OrarioFine = OrarioFine;
        this.Link = Link;
        this.FKCorso = FKCorso;
    }

    //Ottengo IDSessione_Online

    public Integer GetIDSessione_Online(){
        return IDSessione_Online;
    }

    //Ottengo le piattaforme

    public enum_piattaforma GetPiattaforma (){
        return Piattaforma;
    }

    //Ottengo le date della Sessione Online

    public LocalDate GetDataSessione_Online(){
        return DataSessione;
    }

    //Ottengo Orario Inizio Sessione Online

    public LocalTime GetOrarioInizio_Sessione_Online(){
        return OrarioInizio;
    }

    //Ottengo Orario Fine Sessione Online

    public LocalTime GetOrarioFine_Sessione_Online()
    {
        return OrarioFine;
    }

    //Ottengo link di partecipazione alle Sessioni Online

    public String GetLinkSessione_Online(){
        return Link;
    }

    //Ottengo FKCorso Sessione_Online

    public Integer GetFKCorsoSessione_Online(){
        return FKCorso;
    }


    //Da ragionare con Emanuele per eventuali metodi setter

}
