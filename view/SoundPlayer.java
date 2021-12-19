/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;



public class SoundPlayer
{
  //Attributes
  private Clip clip;
  private AudioInputStream audioStream;
  private String path;

  //Constructor
  public SoundPlayer(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
  {
    this.path = path;
    this.audioStream = AudioSystem.getAudioInputStream(new File(this.path).getAbsoluteFile());
    this.clip = AudioSystem.getClip();
    clip.open(this.audioStream);
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    setVolume(0);
  }

  //Methods
  public void play()
  {
    clip.start();
  }

  /***************************************************/

  public void pause() throws UnsupportedAudioFileException, IOException, LineUnavailableException
  {
    clip.stop();
  }

  /***************************************************/

  public void resume() throws IOException, LineUnavailableException, UnsupportedAudioFileException
  {
    clip.close();
    resetAudioStream();
    this.play();
  }

  /***************************************************/

  private void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException
  {
    this.audioStream = AudioSystem.getAudioInputStream(new File(this.path).getAbsoluteFile());
    clip.open(this.audioStream);
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public float getVolume()
  {
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    return (float) Math.pow(10f, gainControl.getValue() / 20f);
  }

  /***************************************************/

  public void setVolume(float volume)
  {

    if (volume < 0f || volume > 1f) throw new IllegalArgumentException("Volume not valid: " + volume);
    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gainControl.setValue(20f * (float) Math.log10(volume));

  }

}
