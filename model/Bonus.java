/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public enum Bonus{
  //Attributes
  KEEP_IN_HAND(1), MOVE_HOURS(1), EARN_HOURS(1), IMSI_TOKEN(1), NULL(0);

  private int quantity;

  //Constructor
  Bonus(int quantity)
  {
    this.quantity = quantity;
  }

  //Methods
  public void setQuantity(int quantity){this.quantity = quantity;}

  /***************************************************/

  public int getQuantity(){return this.quantity;}
}
