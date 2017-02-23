package audio;

import java.io.File;

public enum Sound {
	FOOTSTEP ("footsteps.wav"), MUSIC_MAIN ("output.wav"), DOOR_OPEN ("open.wav"), BATTERY_LOW ("battery.wav"), WINDOW_BREAK ("window.wav");
	
	private File audiofile;

	private Sound( String filename ) {
		this.audiofile = new File(getClass().getResource(filename).getPath());
	}

	public File getAudiofile() {
		return this.audiofile;
	}
}
