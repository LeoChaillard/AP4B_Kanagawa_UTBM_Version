/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import manager.*;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Class defining the panel
 * displaying the game winner and the ladder.
 */
 public class DisplayWinnerPane extends JPanel{
   //Attributes
   private JLabel winner;
   private JLabel ladder;
   private String winnerName;

   //Constructor
   public DisplayWinnerPane()
   {
     //Set up of the panel
     this.setBackground(new Color(0,0,0,100));
     this.setLayout(new GridBagLayout());
     this.setMinimumSize(new Dimension(800,0));
     this.setPreferredSize(new Dimension(800,0));
     this.setVisible(false);
     this.setOpaque(false);

     //Work on project JLabel
     Font font = new Font("Arial",Font.BOLD,30);
     this.winner = new JLabel(winnerName);
     this.winner.setFont(font);
     this.winner.setForeground(Color.BLACK);
     this.winner.setHorizontalAlignment(SwingConstants.CENTER);

     //Ladder JLabel
     Font font2 = new Font("Arial",Font.BOLD,30);
     this.ladder= new JLabel();
     this.ladder.setFont(font2);
     this.ladder.setForeground(Color.BLACK);
     this.ladder.setHorizontalAlignment(SwingConstants.CENTER);

     //GridBagConstraints and adding components
     GridBagConstraints c = new GridBagConstraints();
     c.fill = GridBagConstraints.HORIZONTAL;
     c.gridx = 1;
     c.gridy = 2;
     c.insets = new Insets(0,0,50,0);
     this.add(ladder, c);

     c.fill = GridBagConstraints.HORIZONTAL;
     c.gridx = 1;
     c.gridy = 1;
     this.add(winner, c);
   }

   //Methods
   public void setWinner(String winner){this.winner.setText(winner);}

   /***************************************************/

   public void displayLadder()
   {
     //From biggest score to lowest
     int [] scores = new int[Game.numberOfPlayers];
     String [] names = new String[Game.numberOfPlayers];
     int tmp=0,max=0,swap=-1;
     String tmpName;
     for(int i=0;i<Game.numberOfPlayers;++i)
     {
       scores[i] = Game.players.get(i).getFinalPoints();
       names[i] = Game.players.get(i).getName();
     }

     while(swap != 0)
     {
       swap = 0;
       for(int i=1; i<Game.numberOfPlayers;++i)
       {
         if(scores[i] > scores[i-1])
         {
           ++swap;
           //Swapping scores
           tmp = scores[i];
           scores[i] = scores[i-1];
           scores[i-1] = tmp;

           //Swapping names
           tmpName = names[i];
           names[i] = names[i-1];
           names[i-1] = tmpName;
         }
       }
     }

     //Creating string that will contains the ladder
     String toDisplay = "<html>";
     for(int i=0;i<Game.numberOfPlayers;++i)
     {
       toDisplay = toDisplay + names[i] + " : " + scores[i] + "<br>";
     }
     toDisplay = toDisplay + "</html>";

     this.ladder.setText(toDisplay);
   }

   /***************************************************/

   @Override
   protected void paintComponent(Graphics g) //Painting the background to get the transparency effect
   {
     g.setColor( getBackground() );
     g.fillRect(0,0,getWidth(),getHeight());
     super.paintComponent(g);
   }
 }
