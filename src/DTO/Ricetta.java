package DTO;

public class Ricetta {

    private String nome;
    private String descrizione;
    private GradoDiff gradoDiff;

    public Ricetta(String nome, String descrizione, GradoDiff gradoDiff) {
    	super();
        this.nome = nome;
        this.descrizione = descrizione;
        this.gradoDiff = gradoDiff;
    }

    public enum GradoDiff {
        Facile,
        Medio,
        Difficile
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public GradoDiff getGradoDiff() {
        return gradoDiff;
    }
    public void setGradoDiff(GradoDiff gradoDiff) {
        this.gradoDiff = gradoDiff;
    }
}
