package audio;

import java.io.File;

public enum Sound {
	FOOTSTEP ("footsteps.wav"), MUSIC_MAIN ("output.midi", AudioType.MIDI), DOOR_OPEN ("open.wav"), BATTERY_LOW ("battery.wav"), WINDOW_BREAK ("window.wav");
	
	private File audiofile;
	private AudioType type;

	private Sound( String filename ) {
		this.audiofile = new File(getClass().getResource(filename).getPath());
		this.type = AudioType.WAV;
	}
	
	private Sound(String filename, AudioType type) {
		this.audiofile = new File(getClass().getResource(filename).getPath());
		this.type = type;
	}

	public File getAudiofile() {
		return this.audiofile;
	}
	
	public AudioType getType() {
		return type;
	}
}
