package audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioMidi implements Runnable {
	
	private Sequencer sequencer;
	public boolean running;
	
	public AudioMidi() {
		running = false;
	}

	public void run() {
		running = true;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			URL url = getClass().getResource("snow4.mid");
			InputStream is = new BufferedInputStream(new FileInputStream(new File(url.getPath())));
			while(running) {
				if(!sequencer.isRunning()) {
					System.out.println("here");
					sequencer.setSequence(is);
			    	sequencer.start();
				}
			}
			sequencer.stop();
			sequencer.close();
			System.out.println("here");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
