/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class CardStarter extends Card{
  //Attributes
  private Bonus secondBonus;

  //Constructors
  public CardStarter()
  {

  }

  /***************************************************/

  public CardStarter(int id, Category skills, Bonus bonus, Bonus secondBonus, int secondBonusQuantity, Branch branch)
  {
    super(id, skills, bonus, branch);
    this.secondBonus = secondBonus;
    this.secondBonus.setQuantity(secondBonusQuantity);
  }

  //Methods
  public boolean isUsed()
  {
    return false;
  }

  public Bonus getSecondBonus(){return this.secondBonus;}
 }
