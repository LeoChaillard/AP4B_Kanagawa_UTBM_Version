/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Polygon;
import java.awt.Font;
import javax.swing.ImageIcon;


 public class DrawCard extends Rectangle{
   //Attributes
   private static final float CARD_SIZE = 0.80f;
   private float cardWidth;
   private float cardHeight;
   private DrawString cardString;
   private Card toDraw;
   private boolean drawBack;
   private boolean inInventory;
   private ImageIcon icon;

   //Constructor
   public DrawCard(float width, float height)
   {
     this.cardWidth = width;
     this.cardHeight = height;
     this.cardString = new DrawString();
     this.drawBack = false;
     this.inInventory = false;
   }

   //Methods
   public void setToDraw(Card toDraw){this.toDraw = toDraw;}
   public void setDrawBack(boolean drawBack){this.drawBack = drawBack;}
   public void setInInventory(boolean inInventory){this.inInventory = inInventory;}

   /***************************************************/

   @Override
   public void fill(Graphics g)
   {
     //Draw back card
     if(this.drawBack)
     {
       this.cardString.setDirection(getDirection());
       setSide(CARD_SIZE);
       //Back card theme
       if(this.toDraw instanceof CardCertificates)
       {
         g.setColor(Colors.getCardThemeColors()[0]);
         setSide(CARD_SIZE);
         super.fill(g);

         setSide(0.3f);
         g.setColor(Color.WHITE);
         super.fill(g);
       }
       else if(this.toDraw instanceof CardAssociations)
       {
         g.setColor(Colors.getCardThemeColors()[1]);
         setSide(CARD_SIZE);
         super.fill(g);

         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
         g.setColor(Color.WHITE);
         getDirection().left(0.25f);
         this.cardString.draw(g,"Association");
         getDirection().right(0.25f);
       }
       else if(this.toDraw instanceof CardTeachers)
       {
         g.setColor(Colors.getCardThemeColors()[2]);
         setSide(CARD_SIZE);
         super.fill(g);

         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
         g.setColor(Color.BLACK);
         getDirection().left(0.2f);
         this.cardString.draw(g,"Teacher");
         getDirection().right(0.2f);
       }
       if(this.toDraw instanceof CardMaterials)
       {
         g.setColor(Colors.getCardThemeColors()[3]);
         setSide(CARD_SIZE);
         super.fill(g);

         setSide(0.3f);
         g.setColor(Color.BLACK);
         this.draw(g);
       }
       setSide(CARD_SIZE);
     }

     //Draw card visible face
     if(!this.drawBack)
     {
       this.cardString.setDirection(getDirection());
       Circle points = new Circle();
       points.setDirection(getDirection());
       points.setScale(this.cardWidth/3, this.cardHeight/6);
       points.setSide(0.8f);
       Rectangle category = new Rectangle();
       category.setDirection(getDirection());
       category.setScale(this.cardWidth/3, this.cardHeight/6);
       category.setSide(0.8f);


       //Learn skills part
       getDirection().setScale(this.cardWidth, this.cardHeight/6);
       setScale(this.cardWidth, this.cardHeight/6);
       setSide(1);
       g.setColor(Colors.getCardColors()[0]);
       super.fill(g);

       if(!(this.toDraw instanceof CardStarter))
       {
         //Scoring points
         getDirection().left(0.30f);
         g.setColor(Color.BLACK);
         points.draw(g);

         getDirection().left(0.03f);
         getDirection().down(0.17f);
         if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
         else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
         this.cardString.draw(g, "" + this.toDraw.getScoringPointsSkills());

         getDirection().up(0.17f);
         getDirection().right(0.33f);

         //Category
         getDirection().right(0.30f);
         category.draw(g);

         getDirection().left(0.1f);
         getDirection().down(0.17f);
          g.setColor(Color.BLACK);
         if(this.toDraw.getCategorySkills() == Category.T2S)
         {
           if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 9));
           else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
           this.cardString.draw(g, "T2S");
         }
         else if(this.toDraw.getCategorySkills() == Category.EC)
         {
           if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 9));
           else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
           this.cardString.draw(g, "EC");
         }
         else if(this.toDraw.getCategorySkills() == Category.TM)
         {
           if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 9));
           else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
           this.cardString.draw(g, "TM");
         }
         else if(this.toDraw.getCategorySkills() == Category.CS)
         {
           if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 9));
           else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
           this.cardString.draw(g, "CS");
         }
         else if(this.toDraw.getCategorySkills() == Category.JOKER)
         {
           if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 6));
           else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
           this.cardString.draw(g, "JOKER");
         }

         getDirection().left(0.2f);
         getDirection().up(0.17f);

       }

       //Branch part
       getDirection().down(1);
       this.cardString.setDirection(getDirection());

       if(this.toDraw.getBranch() == Branch.INFO)
       {
         g.setColor(Colors.getBranchColors()[0]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
         getDirection().left(0.25f);
         this.cardString.draw(g, "INFO");
         getDirection().right(0.25f);
       }
       else if(this.toDraw.getBranch() == Branch.GMC)
       {
         g.setColor(Colors.getBranchColors()[1]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
         getDirection().left(0.25f);
         this.cardString.draw(g, "GMC");
         getDirection().right(0.25f);
       }
       else if(this.toDraw.getBranch() == Branch.EDIM)
       {
         g.setColor(Colors.getBranchColors()[2]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
         getDirection().left(0.25f);
         this.cardString.draw(g, "EDIM");
         getDirection().right(0.25f);
       }
       else if(this.toDraw.getBranch() == Branch.ENERGIE)
       {
         g.setColor(Colors.getBranchColors()[3]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
         getDirection().left(0.25f);
         this.cardString.draw(g, "ENERGIE");
         getDirection().right(0.25f);
       }
       else if(this.toDraw.getBranch() == Branch.IMSI)
       {
         g.setColor(Colors.getBranchColors()[4]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
         getDirection().left(0.25f);
         this.cardString.draw(g, "IMSI");
         getDirection().right(0.25f);
       }

       //Theme part
       getDirection().down(2);
       setScale(this.cardWidth, this.cardHeight/2);

       if(this.toDraw instanceof CardCertificates)
       {
         g.setColor(Colors.getCardThemeColors()[0]);
         super.fill(g);
         int instances = this.toDraw.getElement();
         this.icon = new ImageIcon("assets/certificates.png");
       }
       else if(this.toDraw instanceof CardAssociations)
       {
         g.setColor(Colors.getCardThemeColors()[1]);
         super.fill(g);
         switch(Associations.values()[this.toDraw.getElement()])
         {
           case AE:
            this.icon = new ImageIcon("assets/ae.png");
           break;
           case BDS:
            this.icon = new ImageIcon("assets/bds.png");
           break;
           case BDF:
            this.icon = new ImageIcon("assets/bdf.png");
           break;
           case CLUBS:
            this.icon = new ImageIcon("assets/clubs.png");
           break;
           case NULL:
            this.icon = null;
           break;
         }

       }
       else if(this.toDraw instanceof CardTeachers)
       {
        g.setColor(Colors.getCardThemeColors()[2]);
        super.fill(g);
        switch(Teachers.values()[this.toDraw.getElement()])
        {
          case GECHTER:
           this.icon = new ImageIcon("assets/gechter.png");
          break;
          case PAIRE:
           this.icon = new ImageIcon("assets/paire.png");
          break;
          case BAUME:
           this.icon = new ImageIcon("assets/baume.png");
          break;
          case ROTH:
           this.icon = new ImageIcon("assets/roth.png");
          break;
          case NULL:
           this.icon = null;
          break;
        }

       }
       else if(this.toDraw instanceof CardMaterials)
       {
          g.setColor(Colors.getCardThemeColors()[3]);
          super.fill(g);
          switch(Materials.values()[this.toDraw.getElement()])
          {
            case SERVER:
             this.icon = new ImageIcon("assets/server.png");
            break;
            case HYDROGEN:
             this.icon = new ImageIcon("assets/hydrogen.png");
            break;
            case WORKSHOP:
             this.icon = new ImageIcon("assets/workshop.png");
            break;
            case MECANICAL_PIECES:
             this.icon = new ImageIcon("assets/mecanical.png");
            break;
            case LATHE:
             this.icon = new ImageIcon("assets/lathe.png");
            break;
            case VR_HEADSET:
             this.icon = new ImageIcon("assets/vr.png");
            break;
            case NULL:
             this.icon = null;
            break;
          }
        }
        else if(this.toDraw instanceof CardStarter)
        {
          g.setColor(Color.BLACK);
          super.fill(g);
        }


       getDirection().up(1);
       getDirection().left(0.3f);

       g.setColor(Color.BLACK);
       points.draw(g);

       getDirection().left(0.03f);
       getDirection().down(0.17f);

       if(inInventory) g.setFont(new Font("Baskerville Old Face", Font.BOLD, 10));
       else g.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
       this.cardString.draw(g, "" + this.toDraw.getScoringPointsProjects());

       getDirection().right(0.03f);
       getDirection().up(0.17f);

       getDirection().down(1);
       getDirection().right(0.3f);


       DrawImage theme = new DrawImage();
       theme.setDirection(getDirection());
       theme.setScale(this.cardWidth, this.cardHeight/3);
       theme.setSide(1.0f);

       if(this.toDraw instanceof CardCertificates)
       {
        for(int i=0;i<this.toDraw.getElement();++i)
        {
          theme.draw(g, this.icon);
          getDirection().right(0.15f);
        }
        for(int i=0;i<this.toDraw.getElement();++i) getDirection().left(0.15f);
       }
       else if(this.icon != null) theme.draw(g, this.icon);


       //Project skill category part
       getDirection().down(2);
       setScale(this.cardWidth, this.cardHeight/6);

       if(this.toDraw.getCategoryProject() == Category.T2S)
       {
         g.setColor(Colors.getSkillColors()[0]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
         this.cardString.draw(g, "T2S x" + this.toDraw.getProjectCategoriesQuantity());
       }
       else if(this.toDraw.getCategoryProject() == Category.EC)
       {
         g.setColor(Colors.getSkillColors()[1]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
         this.cardString.draw(g, "EC x" + this.toDraw.getProjectCategoriesQuantity());
       }
       else if(this.toDraw.getCategoryProject() == Category.TM)
       {
         g.setColor(Colors.getSkillColors()[2]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
         this.cardString.draw(g, "TM x" + this.toDraw.getProjectCategoriesQuantity());
       }
       else if(this.toDraw.getCategoryProject() == Category.CS)
       {
         g.setColor(Colors.getSkillColors()[3]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
         this.cardString.draw(g, "CS x" + this.toDraw.getProjectCategoriesQuantity());
       }
       else if(this.toDraw.getCategoryProject() == Category.JOKER)
       {
         g.setColor(Colors.getSkillColors()[4]);
         super.fill(g);
         g.setColor(Color.BLACK);
         g.setFont(new Font("Baskerville Old Face", Font.BOLD, 12));
         this.cardString.draw(g, "JOKER");
       }
       else super.fill(g); //Starter cards

       getDirection().up(5.0f);
     }

   }

   public void draw(Graphics g)
   {
     super.draw(g);
   }



 }
