package org.audary.api.text;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class SummaryResponse extends GenericJson {
	@Key
	private int sm_api_character_count;
	@Key
	private String sm_api_title;
	@Key
	private String sm_api_content;
	@Key
	private int sm_api_error = -1;
	@Key
	private String sm_api_message;
	
	/**
	 * @return character count of summary content
	 */
	public int getCharacterCount() {
		return sm_api_character_count;
	}
	
	/**
	 * @return summary title
	 */
	public String getTitle() {
		return sm_api_title;
	}
	
	/**
	 * @return summary content
	 */
	public String getContent() {
		return sm_api_content;
	}
	
	/**
	 * Returns null if there was no error
	 * @return summary api error
	 */
	public String getErrorMessage() {
		return sm_api_message;
	}
	
	/**
	 * Returns -1 if there was no error
	 * @return summary api error message
	 */
	public int getErrorCode() {
		return sm_api_error;
	}
}