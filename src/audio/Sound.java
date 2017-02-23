package audio;

import java.io.File;
import java.net.URL;

public enum Sound {
	//FOOTSTEP ("footsteps.wav"), MUSIC_MAIN ("output.midi", AudioType.MIDI), DOOR_OPEN ("open.wav"), BATTERY_LOW ("battery.wav"), WINDOW_BREAK ("window.wav");
	MUSIC_MAIN ("snow4.mid", AudioType.MIDI);
	
	private File audiofile;
	private AudioPlayer player;

	private Sound(String filename) {
		this.audiofile = new File(getClass().getResource(filename).getPath());
		this.player = new AudioWav(this.audiofile);
	}
	
	private Sound(String filename, AudioType type) {
		URL url = getClass().getResource(filename);
		this.audiofile = new File(url.getPath());
		switch(type) {
		case MIDI:
			this.player = new AudioMidi(this.audiofile);
		case WAV:
			this.player = new AudioWav(this.audiofile);
		}
	}

	public void play() {
		this.player.play(false);
	}
	
	public void playLoop() {
		this.player.stop();
	}
	
	public void stop() {
		this.player.stop();
	}
}
