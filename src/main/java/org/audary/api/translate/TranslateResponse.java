package org.audary.api.translate;

import com.google.api.client.util.Key;

public class TranslateResponse  {
	@Key("text()")
	String string;
	
	public String getText() {
		return string;
	}
}