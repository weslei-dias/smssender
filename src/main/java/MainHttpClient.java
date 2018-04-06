public class MainHttpClient {

	public static void main(String[] args) throws Exception {
		PapovaiHttpClientConnector papovaiHttpClientConnector = new PapovaiHttpClientConnector();
		
		String numbers = "31992733991";
		String message = "Mensagem de teste.";
		String date = "2018-04-05 15:30";

		papovaiHttpClientConnector.send(numbers, message, date);
	}
}