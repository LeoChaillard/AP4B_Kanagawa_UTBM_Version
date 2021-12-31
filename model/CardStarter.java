/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

/**
 * Class defining a starter type card.
 */
public class CardStarter extends Card{
  //Attributes
  private Bonus secondBonus;
  private int secondBonusQuantity;

  //Constructors
  public CardStarter()
  {

  }

  /***************************************************/

  public CardStarter(int id, Category skills, Bonus bonus, Bonus secondBonus, int secondBonusQuantity, Branch branch)
  {
    super(id, skills, bonus, branch);
    this.secondBonus = secondBonus;
    this.secondBonusQuantity = secondBonusQuantity;
  }

  //Methods
  public Bonus getSecondBonus(){return this.secondBonus;}
  public int getSecondBonusQuantity(){return this.secondBonusQuantity;}
 }
