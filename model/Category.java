/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public enum Category{
  //Attributs
  CS(1), TM(1), EC(1), T2S(1), JOKER(1), NULL(0);

  private int quantity;

  //Constructor
  Category(int quantity)
  {
    this.quantity = quantity;
  }

  //Methods
  public int getQuantity(){return this.quantity;}

  /***************************************************/

  public void setQuantity(int quantity){this.quantity = quantity;}
}
