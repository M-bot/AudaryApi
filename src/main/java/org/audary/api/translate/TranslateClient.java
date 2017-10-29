package org.audary.api.translate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.audary.api.azure.AzureAuthToken;
import org.audary.api.azure.StringArrayResponse;
import org.audary.api.azure.StringResponse;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public class TranslateClient {
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final String BASE_URI = "https://api.microsofttranslator.com/V2/Http.svc";

	private HttpRequestFactory requestFactory;
	private XmlNamespaceDictionary namespaceDictionary;

	public TranslateClient(String apiKey) {
		AzureAuthToken token = new AzureAuthToken(apiKey);
		String azureToken = token.getAccessToken();
		namespaceDictionary = new XmlNamespaceDictionary();
		requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest request) {
				request.setParser(new XmlObjectParser(namespaceDictionary));
				HttpHeaders h = new HttpHeaders();
				List<String> auth = new ArrayList<String>();
				auth.add("Bearer " + azureToken);
				h.set("Authorization", auth);
				request.setHeaders(h);
			}
		});
	}

	public StringResponse translate(String text, String locale) throws IOException {
		GenericUrl url = new GenericUrl(
				BASE_URI + "/Translate?" + String.format("text=%s&from=en-US&to=%s", text, locale));
		
		HttpRequest request = requestFactory.buildGetRequest(url);
		HttpResponse hr = request.execute();
		return hr.parseAs(StringResponse.class);
	}

	public StringArrayResponse retrieveLocales() throws IOException {
		GenericUrl url = new GenericUrl(BASE_URI + "/GetLanguagesForTranslate");

		HttpRequest request = requestFactory.buildGetRequest(url);
		HttpResponse hr = request.execute();

		return hr.parseAs(StringArrayResponse.class);
	}
	
	public StringArrayResponse retrieveLanguages(StringArrayResponse locales, String locale) throws IOException {	    
		GenericUrl url = new GenericUrl(BASE_URI + "/GetLanguageNames?" + String.format("locale=%s", locale));
		
		HttpContent ht = new XmlHttpContent(namespaceDictionary, "ArrayOfstring", locales);
		HttpRequest request = requestFactory.buildPostRequest(url, ht);
		HttpResponse hr = request.execute();

		return hr.parseAs(StringArrayResponse.class);
	}
}
