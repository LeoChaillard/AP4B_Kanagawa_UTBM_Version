/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

 package model;

 public class Hour{
   //Attributes
   private Bonus hour;
   private boolean firstTimeUsed;
   private boolean usedInTurn;
   private Category lastTurnPosition;


   //Constructor
   public Hour()
   {
     this.hour = Bonus.EARN_HOURS;
     this.firstTimeUsed = true;
     this.usedInTurn = false;
     this.lastTurnPosition = Category.NULL;
   }


   //Methods
   public boolean isFirstTimeUsed(){return this.firstTimeUsed;}
   public void setFirstTimeUsed(boolean firstTimeUsed){this.firstTimeUsed = firstTimeUsed;}
   public boolean wasUsedInTurn(){return this.usedInTurn;}
   public void setUsedInTurn(boolean used){this.usedInTurn = used;}
   public Category getLasTurnPosition(){return this.lastTurnPosition;}
   public void setLasTurnPosition(Category position){this.lastTurnPosition = position;}

 }
