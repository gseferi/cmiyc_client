package audio;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioHandler implements Runnable {
	
	public boolean running;

	public AudioHandler() {
		
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
		
	}
	
}
