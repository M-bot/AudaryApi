package org.audary.api.text;

import java.io.IOException;

public class SummaryClientTest {
	public static void main(String[] args) throws IOException {
		SummaryClient s = new SummaryClient(System.getenv("SMMRY_APPLICATION_CREDENTIALS"));
		System.out.println(s.summary("", 2));
	}
}
