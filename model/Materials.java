/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

/**
 * Enumeration defining the card materials.
 */
public enum Materials{
  //Attributs
  SERVER(0), HYDROGEN(1), WORKSHOP(2), MECANICAL_PIECES(3), LATHE(4), VR_HEADSET(5), NULL(6);
  private int pos;

  //Constructor
  Materials(int pos)
  {
    this.pos = pos;
  }

  //Methods
  public int getPos(){return this.pos;}
}
