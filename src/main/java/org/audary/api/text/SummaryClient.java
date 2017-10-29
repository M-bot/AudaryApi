package org.audary.api.text;

import java.io.IOException;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class SummaryClient {
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();

	private static SummaryResponse parseResponse(HttpResponse response) throws IOException {
		return response.parseAs(SummaryResponse.class);
	}
	
	private final String apiKey;
	
	public SummaryClient(String apiKey) {
		this.apiKey = apiKey;
	}

	public SummaryResponse summary(String longArticle, int sentenceCount) throws IOException {
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest request) {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
				HttpHeaders h = new HttpHeaders();
				h.set("Expect", "");
				request.setHeaders(h);
			}
		});
		GenericUrl url = new GenericUrl("http://api.smmry.com/&SM_API_KEY=" + apiKey + "&SM_LENGTH=" + sentenceCount + "&SM_WITH_BREAK");
		HttpContent ht = ByteArrayContent.fromString(null, "sm_api_input="+longArticle);
		HttpRequest request = requestFactory.buildPostRequest(url, ht);
		return parseResponse(request.execute());
	}
}
