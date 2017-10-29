package org.audary.api.azure;

import com.google.api.client.util.Key;

public class StringResponse {
	@Key("text()")
	String string;
	
	public StringResponse() {
	}
	
	public StringResponse(String s) {
		string = s;
	}
	
	@Override
	public String toString() {
		return string;
	}
}
