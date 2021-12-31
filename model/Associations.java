/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

/**
 * Enumeration defining the card associations.
 */
public enum Associations{
  //Attributes
  AE(0), BDS(1), BDF(2), CLUBS(3), NULL(4);

  private int pos; //position of the current element

  //Constructor
  Associations(int pos)
  {
    this.pos = pos;
  }

  //Methods
  public int getPos(){return this.pos;}
}
