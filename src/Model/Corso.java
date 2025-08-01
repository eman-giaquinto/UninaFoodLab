package Model;

import java.time.LocalDate;

//Questa classe definisce il Corso.
public class Corso {

    //Rappresenta l'enum per tipo di corso
    public enum enum_tipo_corso {Panificazione, CucinaAsiatica, Pasticceria, CucinaNapoletana, Rosticceria,
    CucinaVegetariana, CucinaFrancese, Gelateria, Pizzeria, CucinaGreca, Confetteria, CucinaMessicana}

    //Rappresenta l'enum per frequenza della sessione
    public enum enum_freq_sessione {Giornaliera, Settimanale, Mensile}
    private Integer IDCorso;
    private enum_tipo_corso TipoDiCorso;
    private LocalDate DataInizio;
    private enum_freq_sessione Freq_sessione;
    private LocalDate DataFine;

    //Definisco il costruttore della classe Corso

    public Corso (Integer IDCorso, enum_tipo_corso TipoDiCorso, LocalDate DataInizio, enum_freq_sessione Freq_sessione
    , LocalDate DataFine){
        this.IDCorso = IDCorso;
        this.TipoDiCorso = TipoDiCorso;
        this.DataInizio = DataInizio;
        this.Freq_sessione = Freq_sessione;
        this.DataFine = DataFine;
    }

    //Ottiene IDCorso

    public Integer GetIDCorso(){
        return IDCorso;
    }

    //Ottiene il TipoDiCorso

    public enum_tipo_corso GetTipoDiCorso(){
        return TipoDiCorso;
    }

    //Ottiene la DataDiInizio del Corso

    public LocalDate GetDataDiInizio(){
        return DataInizio;
    }

    //Ottiene la frequenza delle sessioni

    public enum_freq_sessione GetFrequenzaSessioni(){
        return Freq_sessione;
    }

    //Ottiene la Data Di Fine del Corso

    public LocalDate GetDataFine(){
        return DataFine;
    }

    //Da ragionare con Emanuele per eventuali metodi setter
}
