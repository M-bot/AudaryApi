package org.audary.api;

import java.io.File;
import java.io.PrintWriter;

import org.audary.api.speech.STTClient;

public class Main {

	/**
	 * A very simple command line wrapper for the Audary Speech to Text API (Based
	 * of Google Cloud Speech)
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		for (String file : args) {
			STTClient stt = new STTClient();
			String transcription = stt.transcription(new File(file));
			PrintWriter pw = new PrintWriter(new File(file + ".out"));
			pw.print(transcription);
			pw.close();
		}
	}

}
