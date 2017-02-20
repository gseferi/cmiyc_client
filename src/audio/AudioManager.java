package audio;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//import client.Client;

public class AudioManager implements Runnable {
	
	public boolean running;

	public AudioManager() {
		
	}
	
	public static void main(String[] args) {
		//playMusic();
	}

	@Override
	public void run() {
		running = true;
		playMusic();
		running = false;
	}
	
	public void playMusic() {
		try {
			
			URL url = getClass().getResource("output.wav");
	        File file = new File(url.getPath());
	        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
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
	
}
