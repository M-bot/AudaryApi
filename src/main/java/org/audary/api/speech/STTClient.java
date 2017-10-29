package org.audary.api.speech;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioSystem;

import org.audary.api.storage.CloudStorageHelper;

import com.google.api.gax.rpc.OperationFuture;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.longrunning.Operation;

public class STTClient {
	public String transcription(String gcsUri) throws Exception {
		return transcription(gcsUri, "en-US", 44100, AudioEncoding.LINEAR16);
	}

	public String transcription(String gcsUri, String locale, int sampleRate, AudioEncoding encoding)
			throws IOException, Exception {
		String ret = "";
		try (SpeechClient speech = SpeechClient.create()) {
			RecognitionConfig config = RecognitionConfig.newBuilder().setEncoding(encoding).setLanguageCode(locale)
					.setSampleRateHertz(sampleRate).build();
			RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

			OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata, Operation> response = speech
					.longRunningRecognizeAsync(config, audio);

			while (!response.isDone()) {
			}

			List<SpeechRecognitionResult> results = response.get().getResultsList();

			for (SpeechRecognitionResult result : results) {
				ret += " " + result.getAlternativesList().get(0).getTranscript() + ".";
			}
		}

		return ret;
	}

	public String transcription(File a) throws IOException, Exception {
		AudioFormat af = AudioSystem.getAudioFileFormat(a).getFormat();
		AudioEncoding ae = AudioEncoding.ENCODING_UNSPECIFIED;
		if (af.getSampleSizeInBits() == 16 && (af.getEncoding() == Encoding.PCM_FLOAT
				|| af.getEncoding() == Encoding.PCM_SIGNED || af.getEncoding() == Encoding.PCM_UNSIGNED)) {
			ae = AudioEncoding.LINEAR16;
		}
		
		String uri = CloudStorageHelper.uploadFile(a, "audary");
		return transcription(uri, "en-US", (int) af.getSampleRate(), ae);
	}

	public String transcription(File a, String locale, int sampleRate, AudioEncoding encoding)
			throws IOException, Exception {
		String uri = CloudStorageHelper.uploadFile(a, "audary");
		return transcription(uri, locale, sampleRate, encoding);
	}
}
