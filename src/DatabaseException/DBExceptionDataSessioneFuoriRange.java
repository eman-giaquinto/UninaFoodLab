package DatabaseException;

public class DBExceptionDataSessioneFuoriRange extends Exception{
	private String messaggioErroreSchermo = "La data della sessione inserita, non rientra nel range\n"
			+ "data inizio e fine del corso selezionato. Riprova!";

	public String getMessaggioErroreSchermo() {
		return messaggioErroreSchermo;
	}

	
}
