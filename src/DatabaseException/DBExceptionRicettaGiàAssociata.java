package DatabaseException;

public class DBExceptionRicettaGiàAssociata extends Exception{
	private String messaggioErroreSchermo = "La ricetta selezionata è stata già associata alla sessione pratica scelta.";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

	
}
