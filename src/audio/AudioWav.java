package audio;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioWav {
	private boolean running;
	
	public AudioWav() {
		running = false;
	}
	
	public void play(File audiofile) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(audiofile);
			Clip test = AudioSystem.getClip();
			test.open(ais);
			test.start();
			while (test.isRunning()) {}
			test.drain();
			test.close();
		} catch (Exception e) {
		  System.err.println(e.getMessage());
		}
	}
	
	public boolean isRunning() {
		return running;
	}
}
