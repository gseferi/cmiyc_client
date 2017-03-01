package audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import util.Debug;

public class AudioWav implements AudioPlayer {
	private boolean running;
	private Clip player;
	private File audiofile;
	
	public AudioWav(File audiofile) {
		running = false;
		this.audiofile = audiofile;
		try {
			player = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play(boolean loop) {
		try {
			if(running) stop();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audiofile);
			player.open(ais);
			player.start();
			if (loop) player.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
		  System.err.println(e.getMessage());
		}
	}
	
	public void stop() {
		running = false;
		player.stop();
		player.drain();
		player.close();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setVol(float vol, float pan) {
		FloatControl volControl = (FloatControl) player.getControl(FloatControl.Type.MASTER_GAIN);
		FloatControl panControl = (FloatControl) player.getControl(FloatControl.Type.PAN);
		if (vol <= volControl.getMaximum() && vol >= volControl.getMinimum()) volControl.setValue(vol);
		else Debug.say("Invalid Volume parameter");
		if (-1 <= pan && pan <= 1) panControl.setValue(pan);
		else Debug.say("Invalid pan parameter");
	}
	
	public void setVol(float vol) {
		setVol(vol, (float) 0.5);
	}
}
