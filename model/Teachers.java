/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/
package model;

/**
 * Enumeration defining the card teachers.
 */
public enum Teachers{
  //Attributes
  GECHTER(0), PAIRE(1), BAUME(2), ROTH(3), NULL(4);
  private int pos;

  //Constructor
  Teachers(int pos)
  {
    this.pos = pos;
  }

  //Methods
  public int getPos(){return this.pos;}
}
