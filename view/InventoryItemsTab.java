/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;

import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import java.util.*;


public class InventoryItemsTab extends JPanel{
  //Attributes
  private static final int X_ELEMENTS = 8;
  private static final int Y_ELEMENTS = 4;
  private static final float ELEMENT_SIZE = 0.90f;
  private static final float SKILLS_ELEMENTS = 5;
  private static final float BONUS_ELEMENTS = 4;
  private static final float SKILL_SIZE = 0.70f;
  private static final int LINE_ELEMENTS = X_ELEMENTS - 2;

  private ImageIcon icon;

  //Constructor
  public InventoryItemsTab()
  {
    this.icon = new ImageIcon("view/icon.jpg");
  }

  //Methods
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    int h= this.getHeight();
    int w = this.getWidth();
    g.drawImage(icon.getImage(),(this.getWidth()-w)/2,(this.getHeight()-h)/2,w,h,this);
  }

  /***************************************************/

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    //this.setBackground(Color.GRAY);

    //Getting size of the window
    float width = getWidth();
    float height = getHeight();

    //Direction
    Direction d = new Direction( ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 0.25f);
    d.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);

    //Circles
    Circle cir = new Circle();
    cir.setDirection(d);
    cir.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    cir.setSide(ELEMENT_SIZE);

    //Skills
    Circle skills = new Circle();
    skills.setDirection(d);
    skills.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    skills.setSide(SKILL_SIZE);

    //Strings
    DrawString string = new DrawString();
    string.setDirection(d);
    string.setScale(width/X_ELEMENTS, height/Y_ELEMENTS);
    string.setSide(1.0f);

    //Drawing skills
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(2));

    g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
    g.setColor(new Color(0x484339));
    string.draw(g, "Skills");

    d.right(2);
    for(int i=0;i<SKILLS_ELEMENTS;++i)
    {
      g2.setColor(Color.BLACK);
      cir.draw(g2);
      g.setColor(Colors.getSkillColors()[i]);
      cir.fill(g);

      d.right(1);
    }

    d.resetMove();
    d.right(1.8f);

    for(Category skill : Game.players.get(Game.playerIndex).getAvailableSkills().keySet())
    {
      int quantity = Game.players.get(Game.playerIndex).getAvailableSkills().get(skill);

      //Drawing its each skill at its correct place
      switch(skill)
      {
        case T2S:
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 19));
          g.setColor(Color.BLACK);
          string.draw(g, "T2S");

          d.down(0.5f);

          d.right(0.2f);
          g.setColor(new Color(0x484339));
          string.draw(g, "x" + quantity);
          d.left(0.2f);

          d.up(0.5f);
        break;
        case EC:
          d.right(1f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 19));
          g.setColor(Color.BLACK);
          string.draw(g, "EC");

          d.down(0.5f);

          d.right(0.2f);
          g.setColor(new Color(0x484339));
          string.draw(g, "x" + quantity);
          d.left(0.2f);

          d.up(0.5f);
          d.left(1f);
        break;
        case TM:
          d.right(2f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 19));
          g.setColor(Color.BLACK);
          string.draw(g, "TM");

          d.down(0.5f);

          d.right(0.2f);
          g.setColor(new Color(0x484339));
          string.draw(g, "x" + quantity);
          d.left(0.2f);

          d.up(0.5f);
          d.left(2f);
        break;
        case CS:
          d.right(3f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 19));
          g.setColor(Color.BLACK);
          string.draw(g, "CS");

          d.down(0.5f);

          d.right(0.2f);
          g.setColor(new Color(0x484339));
          string.draw(g, "x" + quantity);
          d.left(0.2f);

          d.up(0.5f);
          d.left(3f);
        break;
        case JOKER:
          d.right(4f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 19));
          g.setColor(Color.BLACK);
          string.draw(g, "JOKER");

          d.down(0.5f);

          d.right(0.2f);
          g.setColor(new Color(0x484339));
          string.draw(g, "x" + quantity);
          d.left(0.2f);

          d.up(0.5f);
          d.left(4f);
        break;
      }
    }

    //Drawing bonus
    d.resetMove();
    d.down(2);
    g.setColor(new Color(0x605A4D));
    string.draw(g, "Bonus");

    d.right(2);

    for(int i=0;i<BONUS_ELEMENTS;++i)
    {
      g2.setColor(Color.BLACK);
      cir.draw(g2);
      d.right(1);
    }

    d.resetMove();
    d.down(2.5f);
    d.right(2);

    for(Bonus bonus : Game.players.get(Game.playerIndex).getAvailableBonus().keySet())
    {
      g.setColor(new Color(0x484339));
      int quantity = Game.players.get(Game.playerIndex).getAvailableBonus().get(bonus);
      //Drawing its each bonus at its correct place
      switch(bonus)
      {
        case KEEP_IN_HAND:
          string.draw(g, "x" + quantity);
        break;
        case MOVE_HOURS:
          d.right(1);
          string.draw(g, "x" + quantity);
          d.left(1);
        break;
        case EARN_HOURS:
          d.right(2);
          string.draw(g, "x" + quantity);
          d.left(2);
        break;
        case IMSI_TOKEN:
          d.right(3);
          string.draw(g, "x" + quantity);
          d.left(3);
        break;
      }

    }



  }
}
