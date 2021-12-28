/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;
import listeners.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DropTarget;

import java.awt.BorderLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

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
  private boolean finishedMovingHours;
  private JButton finishedMoving;
  private int [][] cirCoords;

  //Constructor
  public InventoryItemsTab()
  {
    this.cirCoords = new int[5][2];
    this.finishedMoving = new JButton("Finished moving hours");
    this.finishedMoving.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent e)
      {
        finishedMovingHours = true;
        finishedMoving.setEnabled(false);
      }
    });
    this.add(finishedMoving, BorderLayout.CENTER);
    this.finishedMovingHours = false;
    this.icon = new ImageIcon("assets/icon.jpg");

     MouseAdapter ma = new MouseAdapter() {

          private Point offset;
          private int index;

          @Override
          public void mousePressed(MouseEvent e) {
            if(!finishedMovingHours)
            {
              Player p = Game.players.get(Game.playerIndex);
              for(int j=0;j<5;++j)
              {
                for(int i=0;i<p.getImages().size();++i)
                {
                    if(!p.getBlockedImages().contains(i))
                    {
                      Rectangle bounds = getImageBounds(p.getImages().get(i), p.getPoints().get(i));
                      Point mp = e.getPoint();
                      if (bounds.contains(mp))
                      {
                          offset = new Point();
                          offset.x = mp.x - bounds.x;
                          offset.y = mp.y - bounds.y;
                          index = i;
                          break;
                      }
                    }
                }
              }
            }
          }

          @Override
          public void mouseReleased(MouseEvent e) {
              offset = null;
              index = -1;
          }

          @Override
          public void mouseDragged(MouseEvent e) {
              if (offset != null && index != -1) {
                  Player p = Game.players.get(Game.playerIndex);
                  Point mp = e.getPoint();
                  p.getPoints().get(index).x = mp.x - offset.x;
                  p.getPoints().get(index).y = mp.y - offset.y;
                  repaint();
              }
          }

      };
      addMouseListener(ma);
      addMouseMotionListener(ma);

}

  //Methods
  public ImageIcon getHourImage(){return this.icon;}
  public void setFinishedMovingHours(boolean b){this.finishedMovingHours = b;}
  public JButton getFinishedMovingButton(){return this.finishedMoving;}
  public boolean skillsContains(float cirX, float cirY, float width, float height, float x, float y)
  {
    return (cirX - width/2 <= x)  && (cirX + width/2 >= x)  && (cirY - height/2 <= y) && (cirY + height/2 >= y);
  }

  /***************************************************/

  public Rectangle getImageBounds(BufferedImage b, Point p) {
      Rectangle bounds = new Rectangle(0, 0, 0, 0);
      Player player = Game.players.get(Game.playerIndex);
      if (player.getImages() != null) {
          bounds.setLocation(p);
          bounds.setSize(b.getWidth(), b.getHeight());
      }
      return bounds;
  }

  /***************************************************/
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

    //Hours
    DrawImage hour = new DrawImage();
    hour.setDirection(d);
    hour.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    hour.setSide(1.0f);

    ImageIcon icon = new ImageIcon("assets/ovni.png");

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
      this.cirCoords[i][0] = cir.getX();
      this.cirCoords[i][1] = cir.getY();
      d.right(1);
    }

    d.resetMove();
    d.right(1.8f);

    for(Category skill : Game.players.get(Game.playerIndex).getAvailableSkills().keySet())
    {
      int quantity = Game.players.get(Game.playerIndex).getAvailableSkills().get(skill);

      //Drawing its each skill at its correct place
      int offset = 0;
      switch(skill)
      {
        case T2S:
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 19));
          g.setColor(Color.BLACK);
          string.draw(g, "T2S");

          Player pT2S = Game.players.get(Game.playerIndex);
          //Hours on each skill category
          if(finishedMovingHours)
          {
            for(int j=0;j<pT2S.getImages().size();++j)
            {
              if(!pT2S.getBlockedImages().contains(j) && this.skillsContains(cirCoords[0][0], cirCoords[0][1], cir.getWidth(), cir.getHeight(), (pT2S.getPoints().get(j).x + hour.getWidth()/2),  (pT2S.getPoints().get(j).y + hour.getHeight()/2)) )
              {
                if(pT2S.getAvailableSkills().get(skill) >= (pT2S.getHoursOnCategories(skill).size()+1) && (pT2S.getHours().get(j).isFirstTimeUsed() || pT2S.getAvailableBonus().get(Bonus.MOVE_HOURS) != 0))
                {
                  System.out.println("T2S");
                  pT2S.getBlockedImages().add(j);
                  pT2S.getHoursOnCategories(skill).add(pT2S.getHours().get(j));

                  System.out.println(pT2S.getHours().get(j).getLasTurnPosition());
                  if(pT2S.getHours().get(j).isFirstTimeUsed() || pT2S.getHours().get(j).wasUsedInTurn() || pT2S.getHours().get(j).getLasTurnPosition() == Category.T2S)
                  {
                    System.out.println("not removing moving hour");
                    pT2S.getHours().get(j).setLasTurnPosition(Category.T2S);
                    pT2S.getHours().get(j).setFirstTimeUsed(false);
                  }
                  else
                  {
                    pT2S.getHours().get(j).setLasTurnPosition(Category.T2S);
                    pT2S.moveHours();
                  }

                }
                else
                {
                  pT2S.getPoints().set(j, new Point(0,0));
                  pT2S.getHours().get(j).setLasTurnPosition(Category.NULL);
                  this.repaint();
                }
              }

            }
          }

          d.down(0.5f);

          d.right(0.2f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
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

          Player pEC = Game.players.get(Game.playerIndex);
          //Hours on each skill category
          if(finishedMovingHours)
          {
            for(int j=0;j<pEC.getImages().size();++j)
            {
              if(!pEC.getBlockedImages().contains(j) && this.skillsContains(cirCoords[1][0], cirCoords[1][1], cir.getWidth(), cir.getHeight(), (pEC.getPoints().get(j).x + hour.getWidth()/2),  (pEC.getPoints().get(j).y + hour.getHeight()/2)) )
              {
                if(pEC.getAvailableSkills().get(skill) >= (pEC.getHoursOnCategories(skill).size()+1) && (pEC.getHours().get(j).isFirstTimeUsed() || pEC.getAvailableBonus().get(Bonus.MOVE_HOURS) != 0))
                {
                  System.out.println("EC");
                  pEC.getBlockedImages().add(j);
                  pEC.getHoursOnCategories(skill).add(pEC.getHours().get(j));

                  if(pEC.getHours().get(j).isFirstTimeUsed() || pEC.getHours().get(j).wasUsedInTurn() || pEC.getHours().get(j).getLasTurnPosition() == Category.EC)
                  {
                    System.out.println("not removing moving hour");
                    pEC.getHours().get(j).setLasTurnPosition(Category.EC);
                    pEC.getHours().get(j).setFirstTimeUsed(false);
                  }
                  else
                  {
                    pEC.getHours().get(j).setLasTurnPosition(Category.EC);
                    pEC.moveHours();
                  }
                }
                else
                {
                   pEC.getPoints().set(j, new Point(0,0));
                   pEC.getHours().get(j).setLasTurnPosition(Category.NULL);
                   this.repaint();
                 }
              }

            }
          }

          d.down(0.5f);

          d.right(0.2f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
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

          Player pTM = Game.players.get(Game.playerIndex);
          //Hours on each skill category
          if(finishedMovingHours)
          {
            for(int j=0;j<pTM.getImages().size();++j)
            {
              if(!pTM.getBlockedImages().contains(j) && this.skillsContains(cirCoords[2][0], cirCoords[2][1], cir.getWidth(), cir.getHeight(), (pTM.getPoints().get(j).x + hour.getWidth()/2),  (pTM.getPoints().get(j).y + hour.getHeight()/2)))
              {
                if(pTM.getAvailableSkills().get(skill) >= (pTM.getHoursOnCategories(skill).size()+1) && (pTM.getHours().get(j).isFirstTimeUsed() || pTM.getAvailableBonus().get(Bonus.MOVE_HOURS) != 0))
                {
                  System.out.println("TM");
                  pTM.getBlockedImages().add(j);
                  pTM.getHoursOnCategories(skill).add(pTM.getHours().get(j));

                  if(pTM.getHours().get(j).isFirstTimeUsed() || pTM.getHours().get(j).wasUsedInTurn() || pTM.getHours().get(j).getLasTurnPosition() == Category.TM)
                  {
                    System.out.println("not removing moving hour");
                    pTM.getHours().get(j).setLasTurnPosition(Category.TM);
                    pTM.getHours().get(j).setFirstTimeUsed(false);
                  }
                  else
                  {
                    pTM.getHours().get(j).setLasTurnPosition(Category.TM);
                    pTM.moveHours();
                  }
                }
                else
                {
                  pTM.getPoints().set(j, new Point(0,0));
                  pTM.getHours().get(j).setLasTurnPosition(Category.NULL);
                  this.repaint();
                }
              }

            }
          }

          d.down(0.5f);

          d.right(0.2f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
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

          Player pCS = Game.players.get(Game.playerIndex);
          //Hours on each skill category
          if(finishedMovingHours)
          {
            for(int j=0;j<pCS.getImages().size();++j)
            {
              if(!pCS.getBlockedImages().contains(j) && this.skillsContains(cirCoords[3][0], cirCoords[3][1], cir.getWidth(), cir.getHeight(), (pCS.getPoints().get(j).x + hour.getWidth()/2),  (pCS.getPoints().get(j).y + hour.getHeight()/2)))
              {
                if(pCS.getAvailableSkills().get(skill) >= (pCS.getHoursOnCategories(skill).size()+1) && (pCS.getHours().get(j).isFirstTimeUsed() || pCS.getAvailableBonus().get(Bonus.MOVE_HOURS) != 0))
                {
                  System.out.println("CS");
                  pCS.getBlockedImages().add(j);
                  pCS.getHoursOnCategories(skill).add(pCS.getHours().get(j));

                  if(pCS.getHours().get(j).isFirstTimeUsed() || pCS.getHours().get(j).wasUsedInTurn() || pCS.getHours().get(j).getLasTurnPosition() == Category.CS)
                  {
                    System.out.println("not removing moving hour");
                    pCS.getHours().get(j).setLasTurnPosition(Category.CS);
                    pCS.getHours().get(j).setFirstTimeUsed(false);
                  }
                  else
                  {
                    pCS.getHours().get(j).setLasTurnPosition(Category.CS);
                    pCS.moveHours();
                  }
                }
                else
                {
                  pCS.getPoints().set(j, new Point(0,0));
                  pCS.getHours().get(j).setLasTurnPosition(Category.NULL);
                  this.repaint();
                }
              }

            }
          }


          d.down(0.5f);

          d.right(0.2f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
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

          Player pJOKER = Game.players.get(Game.playerIndex);
          //Hours on each skill category
          if(finishedMovingHours)
          {
            for(int j=0;j<pJOKER.getImages().size();++j)
            {
              if(!pJOKER.getBlockedImages().contains(j) && this.skillsContains(cirCoords[4][0], cirCoords[4][1], cir.getWidth(), cir.getHeight(), (pJOKER.getPoints().get(j).x + hour.getWidth()/2),  (pJOKER.getPoints().get(j).y + hour.getHeight()/2)))
              {
                if(pJOKER.getAvailableSkills().get(skill) >= (pJOKER.getHoursOnCategories(skill).size()+1) && (pJOKER.getHours().get(j).isFirstTimeUsed() || pJOKER.getAvailableBonus().get(Bonus.MOVE_HOURS) != 0))
                {
                  System.out.println("JOKER");
                  pJOKER.getBlockedImages().add(j);
                  pJOKER.getHoursOnCategories(skill).add(pJOKER.getHours().get(j));

                  if(pJOKER.getHours().get(j).isFirstTimeUsed() || pJOKER.getHours().get(j).wasUsedInTurn() || pJOKER.getHours().get(j).getLasTurnPosition() == Category.JOKER)
                  {
                    System.out.println("not removing moving hour");
                    pJOKER.getHours().get(j).setLasTurnPosition(Category.JOKER);
                    pJOKER.getHours().get(j).setFirstTimeUsed(false);
                  }
                  else
                  {
                    pJOKER.getHours().get(j).setLasTurnPosition(Category.JOKER);
                    pJOKER.moveHours();
                  }
                }
                else
                {
                  pJOKER.getPoints().set(j, new Point(0,0));
                  pJOKER.getHours().get(j).setLasTurnPosition(Category.NULL);
                  this.repaint();
                }
              }

            }
          }

          d.down(0.5f);

          d.right(0.2f);
          g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
          g.setColor(new Color(0x484339));
          string.draw(g, "x" + quantity);
          d.left(0.2f);

          d.up(0.5f);
          d.left(4f);
        break;
      }
    }

    //Drawing hours
    d.resetMove();
    d.down(1);
    g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
    g.setColor(new Color(0x605A4D));
    string.draw(g, "Hours");

    d.right(2);

    g.setColor(Color.BLACK);

    for(int i=0;i<Game.players.get(Game.playerIndex).getHours().size();++i)
    {
      Player p = Game.players.get(Game.playerIndex);
      g.drawImage(p.getImages().get(i), p.getPoints().get(i).x, p.getPoints().get(i).y, hour.getWidth(), hour.getHeight(), null);
    }


    //Drawing bonus
    d.resetMove();
    d.down(2);
    g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
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

    for(Bonus bonus : Game.players.get(Game.playerIndex).getAvailableBonus().keySet() )
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
