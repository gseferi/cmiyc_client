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

public class AudioMidi implements AudioPlayer {
	
	private Sequencer sequencer;
	private File audioFile;
	private boolean running;
	
	public AudioMidi(File audioFile) {
		running = false;
		this.audioFile = audioFile;
	}
	
	private void setup() {
		running = true;
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void play(boolean loop) {
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
	
	public void setVol(float vol, float pan) {};
}
