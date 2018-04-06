

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class PapovaiHttpClientConnector {

	private static final String HOST = "https://www.paposms.com/webservice/1.0";
	private static final String SEND_REQUEST = HOST + "/send/";
	private static final String GET_REQUEST = HOST + "/get/";
	private static final String SEARCH_REQUEST = HOST + "/search/";

	private static final String USER = "<EMAIL>;
	private static final String PASSWORD = "<SENHA>";
	private static final String RETURN_FORMAT = "json";

	private static final String ENCODING = "UTF-8";
	private static final int HTTP_SUCCESS_CODE = 200;
	
	public void send(String numbers, String message, String date) throws Exception {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(SEND_REQUEST
				+ "?user=" + USER
				+ "&pass=" + PASSWORD
				+ "&numbers=" + numbers
				+ "&message=" + URLEncoder.encode(message, ENCODING)
				+ "&date=" + URLEncoder.encode(date, ENCODING)
				+ "&return_format=" + RETURN_FORMAT);
		String responseBody = execute(httpClient, request);
		System.out.println("Send Response Body: " + responseBody);
	}
	
	public void search(String numbers, String startDate, String endDate, String ids, String status, String delivered, String confirmationDate) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(SEARCH_REQUEST
				+ "?user=" + USER
				+ "&pass=" + PASSWORD
				+ "&numbers=" + numbers
				+ "&data_start=" + startDate
				+ "&data_end=" + endDate
				+ "&ids=" + ids
				+ "&status=" + status
				+ "&entregue=" + delivered
				+ "&data_confirmacao=" + confirmationDate
				+ "&return_format=" + RETURN_FORMAT);
		String responseBody = execute(httpClient, request);
		System.out.println("Search Response Body: " + responseBody);
	}

	public void get(String numbers, String startDate, String endDate, String ids, String read) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(GET_REQUEST
				+ "?user=" + USER
				+ "&pass=" + PASSWORD
				+ "&numbers=" + numbers
				+ "&data_start=" + startDate
				+ "&data_end=" + endDate
				+ "&ids=" + ids
				+ "&lido=" + read
				+ "&return_format=" + RETURN_FORMAT);
		String responseBody = execute(httpClient, request);
		System.out.println("Get Response Body: " + responseBody);
	}
	
	private String execute(HttpClient httpClient, HttpUriRequest request) {
		try {
			HttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HTTP_SUCCESS_CODE) {
				throw new RuntimeException("Request failed - HTTP error code: " + statusCode);
			}
			return EntityUtils.toString(response.getEntity(), ENCODING);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
