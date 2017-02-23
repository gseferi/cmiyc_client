package audio;

public class AudioHandler {
	
	private AudioMidi midi;
	private AudioWav wav;
	
	public AudioHandler() {
		midi = new AudioMidi();
		wav = new AudioWav();
	}
	
	private void play(Sound sound, boolean loop) {
		switch(sound.getType()) {
			case MIDI:
				midi.play(sound.getAudiofile(), loop);
			case WAV:
				wav.play(sound.getAudiofile(), loop);
		}
	}
	
	public void playLoop(Sound sound) {
		play(sound, true);
	}
	
	public void play(Sound sound) {
		play(sound, false);
	}
	
	public void stop() {
		if (midi.isRunning()) midi.stop();
		if (wav.isRunning()) wav.stop();
	}
	
}
