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
  private Map<Bonus, Integer> availableBonus;
  private Map<Category, Integer> availableSkills;
  private int usedHours;
  private int movedHours;
  private List<Mention> acceptedMentions;
  private List<Mention> deniedMentions;
  private String name;

  //Constructors
  public Player()
  {
    this.project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
    this.hand = new ArrayList<Card>(3);
    this.temporaryHand = new ArrayList<Card>(3);
    this.availableBonus = new HashMap<Bonus, Integer>(4);
    this.availableSkills = new HashMap<Category, Integer>(5);
    this.usedHours = 0;
    this.movedHours = 0;
    this.acceptedMentions = new ArrayList<Mention>(20);
    this.deniedMentions = new ArrayList<Mention>(20);

    this.initializeBonus();
    this.initializeSkills();
  }

  /***************************************************/

  public Player(String name)
  {
    this.project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
    this.hand = new ArrayList<Card>(3);
    this.temporaryHand = new ArrayList<Card>(3);
    this.availableBonus = new HashMap<Bonus, Integer>(4);
    this.availableSkills = new HashMap<Category, Integer>(5);
    this.usedHours = 0;
    this.movedHours = 0;
    this.acceptedMentions = new ArrayList<Mention>(10);
    this.deniedMentions = new ArrayList<Mention>(10);
    this.name = name;

    this.initializeBonus();
    this.initializeSkills();
  }

  //Methods
  /*public void reset()
  {
    this.hand.clear();
    this.temporaryHand.clear();
    this.project.clear();
    this.availableBonus.clear();
    this.availableSkills.clear();
    this.initializeBonus();
    this.initializeSkills();
  }*/


  /***************************************************/

  public void initializeBonus()
  {
    this.availableBonus.put(Bonus.KEEP_IN_HAND,0);
    this.availableBonus.put(Bonus.MOVE_HOURS,0);
    this.availableBonus.put(Bonus.EARN_HOURS,0);
    this.availableBonus.put(Bonus.IMSI_TOKEN,0);
  }

  /***************************************************/

  public void initializeSkills()
  {
    this.availableSkills.put(Category.CS,0);
    this.availableSkills.put(Category.TM,0);
    this.availableSkills.put(Category.EC,0);
    this.availableSkills.put(Category.T2S,0);
    this.availableSkills.put(Category.JOKER,0);
  }

  /***************************************************/

  public void transferHandToTemporaryHand()
  {
    for(Card c : this.hand) this.temporaryHand.add(c);
    this.hand.clear();
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
    this.addBonus(card.getBonus());
    for(int i =0;i<card.getProjectCategoriesQuantity();++i) this.addSkill(card.getCategorySkills());
    this.project.add(card);
  }

  /***************************************************/

  public void addStarterCardToProject(CardStarter card)
  {
    this.addBonus(card.getBonus());
    for(int i =0;i<card.getSecondBonusQuantity();++i) this.addBonus(card.getSecondBonus());
    this.addSkill(card.getCategorySkills());
    this.project.add(card);
  }

  /***************************************************/

  public void removeCardTemporaryHand()
  {
    if(this.temporaryHand.get(this.temporaryHand.size()-1) != null) this.temporaryHand.remove(this.temporaryHand.size()-1);
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

  public void resetHours()
  {
    this.availableBonus.replace(Bonus.EARN_HOURS, this.availableBonus.get(Bonus.EARN_HOURS) + this.usedHours);
    this.availableBonus.replace(Bonus.MOVE_HOURS, this.availableBonus.get(Bonus.MOVE_HOURS) + this.movedHours);
    this.usedHours = 0;
    this.movedHours = 0;
  }

  /***************************************************/

  public void moveHours()
  {
    ++usedHours;
    ++movedHours;
    this.availableBonus.replace(Bonus.EARN_HOURS, this.availableBonus.get(Bonus.EARN_HOURS) - 1);
    this.availableBonus.replace(Bonus.MOVE_HOURS, this.availableBonus.get(Bonus.MOVE_HOURS) - 1);
    /* taking in account first time MOVE_HOURS is used */
  }

  /***************************************************/

  public boolean workOnProject()
  {
    Card card = this.temporaryHand.get(this.temporaryHand.size()-1);
    if(checkSkills(card.getCategorySkills(), card.getProjectCategoriesQuantity()) && checkHoursAvailability())
    {
      this.moveHours();
      this.addCardProject(this.temporaryHand.get(this.temporaryHand.size()-1));
      this.removeCardTemporaryHand();
      return true;
    }
    return false;
  }

  /***************************************************/

  public void learnNewSkills()
  {
    this.addSkill(this.temporaryHand.get(this.temporaryHand.size()-1).getCategorySkills());
    this.removeCardTemporaryHand();
  }

  /***************************************************/

  public boolean keepCardInHand()
  {
    if(this.availableBonus.get(Bonus.KEEP_IN_HAND) > 0) //If at least one KEEP_IN_HAND bonus
    {
      this.addCardHand(this.temporaryHand.get(this.temporaryHand.size()-1));
      this.removeCardTemporaryHand();
      return true;
    }
    return false;
  }

  /***************************************************/

  public boolean checkSkills(Category c, int quantity)
  {
    return c != Category.NULL && this.availableSkills.get(c) > (quantity - 1);
  }

  /***************************************************/

  public boolean checkHoursAvailability()
  {
    return this.availableBonus.get(Bonus.EARN_HOURS) > 0 && this.availableBonus.get(Bonus.MOVE_HOURS) > 0;
  }

  /***************************************************/

  public void addSkill(Category skill)
  {
    if(skill != Category.NULL) this.availableSkills.replace(skill, this.availableSkills.get(skill) + 1);
  }

  /***************************************************/

  public void addBonus(Bonus bonus)
  {
    if(bonus != Bonus.NULL) this.availableBonus.replace(bonus, this.availableBonus.get(bonus) + 1);
  }

  /***************************************************/

  public Map<Bonus, Integer> getAvailableBonus(){return this.availableBonus;}

  /***************************************************/

  public Map<Category, Integer> getAvailableSkills(){return this.availableSkills;}

}
