package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AudioWav {
	private boolean running;
	private Clip player;
	
	public AudioWav() {
		running = false;
		try {
			player = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play(File audiofile, boolean loop) {
		try {
			if(running) stop();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audiofile);
			player.open(ais);
			player.start();
			if (loop) player.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
		  System.err.println(e.getMessage());
		}
	}
	
	public void stop() {
		running = false;
		player.stop();
		player.drain();
		player.close();
	}
	
	public boolean isRunning() {
		return running;
	}
}
