/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public abstract class Mention {
  //Attributes
  private int id;
  private int points;
  private int numberOfElements;
  private Bonus bonus;

  //Methods
  public abstract boolean checkCriteria(Player p);

  //mettre des getters et setters



}
