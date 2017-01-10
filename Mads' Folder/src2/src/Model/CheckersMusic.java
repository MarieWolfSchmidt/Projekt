package Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class CheckersMusic {
	
	Clip music;
	
	public void activateMusic()  {
		 try {
			 InputStream in = new FileInputStream("theme.wav");
			 InputStream bufferedIn = new BufferedInputStream(in);
			 this.music = AudioSystem.getClip();
			 AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
			 
			 this.music.open(ais);
			 this.music.loop(Clip.LOOP_CONTINUOUSLY);
			 

		 
		 } catch (Exception ex){
			 System.out.println("Sound file not found");
			 
		 }
		 
		 
	 }
	public void stopMusic() {
		 music.stop();
	 }

}
