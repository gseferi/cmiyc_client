package audio;

import java.io.File;
import java.net.URL;

public enum Sound {
	//FOOTSTEP ("footsteps.wav"), MUSIC_MAIN ("output.midi", AudioType.MIDI), DOOR_OPEN ("open.wav"), BATTERY_LOW ("battery.wav"), WINDOW_BREAK ("window.wav");
	MUSIC_MAIN ("output.wav");
	
	private File audiofile;
	private AudioPlayer player;
	private AudioType type;

	private Sound(String filename) {
		this.audiofile = new File(getClass().getResource(filename).getPath());
		this.player = new AudioWav(this.audiofile);
		this.type = AudioType.WAV;
	}
	
	private Sound(String filename, AudioType type) {
		URL url = getClass().getResource(filename);
		this.audiofile = new File(url.getPath());
		switch(type) {
		case MIDI:
			this.player = new AudioMidi(this.audiofile);
			this.type = AudioType.MIDI;
		case WAV:
			this.player = new AudioWav(this.audiofile);
			this.type = AudioType.WAV;
		}
	}

	public void play() {
		this.player.play(false);
	}
	
	public void playLoop() {
		this.player.play(true);;
	}
	
	public void stop() {
		this.player.stop();
	}
	
	public void setVol(double vol, double pan) {
		if (this.type == AudioType.WAV) this.player.setVol((float) vol, (float) pan);
	}
	
	public void setVol(double vol) {
		if (this.type == AudioType.WAV) this.player.setVol((float) vol, (float) 0.5);
	}
}
