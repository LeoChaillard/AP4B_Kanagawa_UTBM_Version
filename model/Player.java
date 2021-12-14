/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;
import java.util.*;

public class Player {
  //Attributes
  private static final int MAX_PROJECT_ELEMENTS = 13;

  private List<Card> hand[];
  private List<Card> project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
  private List<Card> temporaryHand[];
  private int availableBonus[];
  private int availableSkills[];
  private int acceptedMentions[];
  private int deniedMentions[];
  private String name;

  //Constructors
  public Player()
  {

  }

  /***************************************************/

  public Player(String name)
  {
    this.name = name;
  }

  //Methods
  public void addCardProject(Card card)
  {
    this.project.add(card);
  }

  /***************************************************/

  public void setName(String name)
  {
    this.name = name;
  }

  /***************************************************/

  public String getName()
  {
      return this.name;
  }

  /***************************************************/

  public void moveHours(int a, Category b)
  {
    // code
  }

  /***************************************************/

  public void pickUpCards(int index)
  {

  }

  /***************************************************/

  public void passTurn()
  {

  }

  /***************************************************/

  public boolean workOnProject(int index)
  {
    return true;
  }

  /***************************************************/

  public boolean learnNewSkills(int index)
  {
    return true;
  }

  /***************************************************/

  public boolean keepCardInHand(int index)
  {
    return true;
  }



}
