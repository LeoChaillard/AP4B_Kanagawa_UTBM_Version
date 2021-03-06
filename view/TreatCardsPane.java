/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

/**
 * Class defining the panel that will show
 * the player its available choies to treat
 * its temporary hand cards.
 */
 public class TreatCardsPane extends JPanel{
   //Attributes
   private JLabel workProject;
   private JLabel learnSkills;
   private JLabel keepCard;

   //Constructor
   public TreatCardsPane()
   {
     //Set up of the panel
     this.setBackground(new Color(0,0,0,100));
     this.setLayout(new GridBagLayout());
     this.setMinimumSize(new Dimension(800,0));
     this.setPreferredSize(new Dimension(800,0));
     this.setVisible(false);
     this.setOpaque(false);

     //Work on project JLabel
     Font font = new Font("Arial",Font.BOLD,25);
     this.workProject = new JLabel("Work on project");
     this.workProject.setFont(font);
     this.workProject.setForeground(Color.BLACK);
     this.workProject.setHorizontalAlignment(SwingConstants.CENTER);

     //Learn new skills JLabel
     this.learnSkills = new JLabel("Learn new skills");
     this.learnSkills.setFont(font);
     this.learnSkills.setForeground(Color.BLACK);
     this.learnSkills.setHorizontalAlignment(SwingConstants.CENTER);

     //Keep card in hand JLabel
     this.keepCard = new JLabel("Keep card in hand");
     this.keepCard.setFont(font);
     this.keepCard.setForeground(Color.BLACK);
     this.keepCard.setHorizontalAlignment(SwingConstants.CENTER);

     //GridBagConstraints and adding components
     GridBagConstraints c = new GridBagConstraints();
     c.fill = GridBagConstraints.HORIZONTAL;
     c.gridx = 0;
     c.gridy = 1;
     c.insets = new Insets(0,0,0,50);
     this.add(workProject, c);

     c.fill = GridBagConstraints.HORIZONTAL;
     c.gridx = 1;
     c.gridy = 1;
     c.insets = new Insets(0,0,0,50);
     this.add(learnSkills, c);

     c.fill = GridBagConstraints.HORIZONTAL;
     c.gridx = 2;
     c.gridy = 1;
     c.insets = new Insets(0,0,0,0);
     this.add(keepCard, c);
   }

   //Methods
   public JLabel getWorkProjectLabel(){return this.workProject;}
   public JLabel getSkillsLabel(){return this.learnSkills;}
   public JLabel getKeepCardLabel(){return this.keepCard;}

   /***************************************************/

   @Override
   protected void paintComponent(Graphics g) //Painting the background to get the transparency effect
   {
     g.setColor( getBackground() );
     g.fillRect(0,0,getWidth(),getHeight());
     super.paintComponent(g);
   }

 }
