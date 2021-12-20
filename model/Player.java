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

  private List<Card> hand;
  private List<Card> project;
  private List<Card> temporaryHand;
  private List<Bonus> availableBonus;
  private List<Category> availableSkills;
  private List<Mention> acceptedMentions;
  private List<Mention> deniedMentions;
  private String name;

  //Constructors
  public Player()
  {
    this.project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
    this.hand = new ArrayList<Card>(3);
    this.temporaryHand = new ArrayList<Card>(3);
    this.availableBonus = new ArrayList<Bonus>(20);
    this.availableSkills = new ArrayList<Category>(20);
    this.acceptedMentions = new ArrayList<Mention>(10);
    this.deniedMentions = new ArrayList<Mention>(10);
  }

  /***************************************************/

  public Player(String name)
  {
    this.project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
    this.hand = new ArrayList<Card>(3);
    this.temporaryHand = new ArrayList<Card>(3);
    this.availableBonus = new ArrayList<Bonus>(20);
    this.availableSkills = new ArrayList<Category>(20);
    this.acceptedMentions = new ArrayList<Mention>(10);
    this.deniedMentions = new ArrayList<Mention>(10);
    this.name = name;
  }

  //Methods
  public void reset()
  {
    this.hand.clear();
    this.temporaryHand.clear();
    this.project.clear();
  }

  /***************************************************/

  public void addCardHand(Card card)
  {
    this.hand.add(card);
  }

  /***************************************************/

  public void addCardTemporaryHand(Card card)
  {
    this.temporaryHand.add(card);
  }

  /***************************************************/

  public void addCardProject(Card card)
  {
    this.availableBonus.add(card.getBonus());
    this.availableSkills.add(card.getCategorySkills());
    this.project.add(card);
  }

  /***************************************************/

  public void addStarterCardToProject(CardStarter card)
  {
    this.availableBonus.add(card.getBonus());
    this.availableBonus.add(card.getSecondBonus());
    this.availableSkills.add(card.getCategorySkills());
    this.project.add(card);
  }

  /***************************************************/

  public void removeCardTemporaryHand()
  {
    if(this.temporaryHand.get(0) != null) this.temporaryHand.remove(0);
  }

  /***************************************************/

  public List<Card> getHand(){return this.hand;}

  /***************************************************/

  public List<Card> getProject(){return this.project;}

  /***************************************************/

  public List<Card> getTemporaryHand(){return this.temporaryHand;}

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

  }

  /***************************************************/

  public boolean workOnProject()
  {
    Card card = this.temporaryHand.get(0);
    if(checkSkills(card.getCategorySkills()) && checkHoursAvailability(card.getBonus()))
    {
      this.addCardProject(this.temporaryHand.get(0));
      this.removeCardTemporaryHand();
      return true;
    }
    return false;
  }

  /***************************************************/

  public void learnNewSkills()
  {
    this.availableSkills.add(this.temporaryHand.get(0).getCategorySkills());
    this.removeCardTemporaryHand();
  }

  /***************************************************/

  public boolean keepCardInHand()
  {
    if(this.availableBonus.contains(Bonus.KEEP_IN_HAND))
    {
      this.addCardHand(this.temporaryHand.get(0));
      this.removeCardTemporaryHand();
      return true;
    }
    return false;
  }

  /***************************************************/

  public boolean checkSkills(Category c)
  {
    return this.availableSkills.contains(c);
  }

  /***************************************************/

  public boolean checkHoursAvailability(Bonus b)
  {
    return this.availableBonus.contains(b);
  }



}
