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

}
