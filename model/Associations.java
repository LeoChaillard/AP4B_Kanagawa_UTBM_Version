/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public enum Associations{
  //Attributes
  AE(0), BDS(1), BDF(2), CLUBS(3), NULL(4);

  Associations(int pos)
  {
    this.pos = pos;
  }


  private int pos;

  public int getPos(){return this.pos;}
}
