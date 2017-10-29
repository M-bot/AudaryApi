package org.audary.api.speech;

import java.io.File;
import java.io.IOException;

public class STTClientTest {
	public static void main(String[] args) throws IOException, Exception {
		STTClient stt = new STTClient();
		System.out.println(stt.transcription(new File("MiguelAudio.wav")));
	}
}
