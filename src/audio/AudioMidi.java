package audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

public class AudioMidi {
	
	private Sequencer sequencer;
	private boolean running;
	
	public AudioMidi() {
		running = false;
		try {
			sequencer = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private void setup() {
		running = true;
		try {
			sequencer.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play(File audioFile, boolean loop) {
		if (!running) setup();
		InputStream is;
		try {
			is = new BufferedInputStream(new FileInputStream(audioFile));
			if (sequencer.isRunning()) sequencer.stop();
			sequencer.setSequence(is);
	    	sequencer.start();
			if (loop) sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		} catch (IOException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		running = false;
		sequencer.stop();
		sequencer.close();
	}
	
	public boolean isRunning() {
		return running;
	}
}
