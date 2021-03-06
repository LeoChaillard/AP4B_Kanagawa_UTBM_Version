/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/
package view;

import java.io.*;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Class defining the game's menu.
 * It contains an ImagePanel object in order to
 * draw the background image of the JFrame.
 */
public class Menu extends JFrame{
  //Attributes
  private JButton resume;
  private JButton newGame;
  private ImageIcon icon;
  private ImagePanel background;
  private SoundPlayer music;

  private static final int WINDOW_HEIGHT = 600;
  private static final int WINDOW_LENGTH = 500;
  private static String MUSIC_PATH = "music/naruto.wav";

  //Constructor
  public Menu()
  {
    this.icon = new ImageIcon("assets/icon.jpg");
    this.background = new ImagePanel(icon.getImage() );
    this.resume = new JButton("Resume");
    this.newGame = new JButton("New game");

    try{
    this.music = new SoundPlayer(MUSIC_PATH);
    } catch(IOException ioe) {}
    catch(UnsupportedAudioFileException unsupported) {}
    catch(LineUnavailableException line) {}
  }

  //Methods
  public JButton getResume(){return this.resume;}

  /***************************************************/

  public JButton getNewGame(){return this.newGame;}

  /***************************************************/

  public void initMenu()
  {
    //Set up of the JFrame
    this.setTitle("Kanagawa");
    //this.setIconImage(icon.getImage());
    this.setBounds(10,10,WINDOW_LENGTH,WINDOW_HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);

    //Buttons
    this.newGame.setBackground(Color.WHITE);
    this.newGame.setBounds(175,420,147,70);

    this.resume.setBackground(Color.WHITE);
    this.resume.setBounds(175,325,147,70);

    //Adding components to the JFrame
    this.setContentPane(background);
    this.add(resume);
    this.add(newGame);

    //Play music
    this.playMusic();
  }

  /***************************************************/

  public void playMusic()
  {
    try{
    this.music.play();
    } catch(Exception e) {}
  }

  /***************************************************/

  public void resumeMusic()
  {
    try{
      this.music.resume();
    } catch(IOException ioe) {}
    catch(UnsupportedAudioFileException unsupported) {}
    catch(LineUnavailableException line) {}
  }

  /***************************************************/

  public void pauseMusic()
  {
    try{
      this.music.pause();
    } catch(IOException ioe) {}
    catch(UnsupportedAudioFileException unsupported) {}
    catch(LineUnavailableException line) {}
  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
