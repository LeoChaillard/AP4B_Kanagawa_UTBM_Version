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
  private String name;

  //Constructor
  public Mention(int id, int points, int numberOfElements, Bonus bonus, String name)
  {
    this.id = id;
    this.points = points;
    this.numberOfElements = numberOfElements;
    this.bonus = bonus;
    this.name = name;
  }

  //Methods
  public abstract boolean checkCriteria(Player p);

  public Bonus getBonus(){return this.bonus;}

  public int getId(){return this.id;}

  public int getNumberOfElements(){return this.numberOfElements;}

  public int getPoints(){return this.points;}

  public String getName(){return this.name;}



}
