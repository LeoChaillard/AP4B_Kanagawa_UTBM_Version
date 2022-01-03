/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;
import manager.*;

import java.util.*;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Point;

/**
 * Class defining a player and his actions.
 */
public class Player {
  //Attributes
  private static final int MAX_PROJECT_ELEMENTS = 17;

  private List<Card> hand;
  private List<Card> project;
  private List<Card> temporaryHand;
  private Map<Bonus, Integer> availableBonus;
  private Map<Category, Integer> availableSkills;
  private Map<Category, List<Hour>> hoursOnCategories;
  private List<Hour> hours;
  private Set<Integer> acceptedMentions;
  private Set<Integer> deniedMentions;

  private int usedHours;
  private int movedHours;
  private int inHand;
  private int finalPoints;

  private String name;
  private List<BufferedImage> img;
  private List<Point> imgPoint;
  private Set<Integer> blockedImages;

  //Constructors
  public Player()
  {
    this.project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
    this.hand = new ArrayList<Card>(3);
    this.temporaryHand = new ArrayList<Card>(3);
    this.availableBonus = new HashMap<Bonus, Integer>(4);
    this.availableSkills = new HashMap<Category, Integer>(5);
    this.hoursOnCategories = new HashMap<Category, List<Hour>>(5);
    this.hours = new ArrayList<Hour>();
    this.usedHours = 0;
    this.movedHours = 0;
    this.inHand = 0;
    this.finalPoints = 0;
    this.acceptedMentions = new HashSet<Integer>(19);
    this.deniedMentions = new HashSet<Integer>(19);

    this.img = new ArrayList<BufferedImage>(10);
    this.imgPoint = new ArrayList<Point>(10);
    this.blockedImages = new HashSet<Integer>();

    for(int i=0;i<10;++i)
    {
      this.imgPoint.add(new Point(200, 160));
      BufferedImage b;
      try {
          b = ImageIO.read(new File("assets/hour.png"));
          this.img.add(b);
      } catch (IOException ex) {
          ex.printStackTrace();
        }
    }

    this.initializeHourImages();
    this.initializeBonus();
    this.initializeSkills();
    this.initializeHoursOnCategories();
  }

  /***************************************************/

  public Player(String name)
  {
    this.project = new ArrayList<Card>(MAX_PROJECT_ELEMENTS);
    this.hand = new ArrayList<Card>(4);
    this.temporaryHand = new ArrayList<Card>(4);
    this.availableBonus = new HashMap<Bonus, Integer>(4);
    this.availableSkills = new HashMap<Category, Integer>(5);
    this.hoursOnCategories = new HashMap<Category, List<Hour>>(5);
    this.hours = new ArrayList<Hour>();
    this.usedHours = 0;
    this.movedHours = 0;
    this.inHand = 0;
    this.acceptedMentions = new HashSet<Integer>(19);
    this.deniedMentions = new HashSet<Integer>(19);
    this.name = name;

    this.img = new ArrayList<BufferedImage>(10);
    this.imgPoint = new ArrayList<Point>(10);
    this.blockedImages = new HashSet<Integer>();

    this.initializeHourImages();
    this.initializeBonus();
    this.initializeSkills();
    this.initializeHoursOnCategories();
  }

  //Methods
  public void initializeHourImages()
  {
    for(int i=0;i<10;++i)
    {
      this.imgPoint.add(new Point(200, 160));
      BufferedImage b;
      try {
          b = ImageIO.read(new File("assets/hour.png"));
          this.img.add(b);
      } catch (IOException ex) {
          ex.printStackTrace();
        }
    }
  }

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

  public void initializeHoursOnCategories()
  {
    this.hoursOnCategories.put(Category.CS,new ArrayList<Hour>());
    this.hoursOnCategories.put(Category.TM,new ArrayList<Hour>());
    this.hoursOnCategories.put(Category.EC,new ArrayList<Hour>());
    this.hoursOnCategories.put(Category.T2S,new ArrayList<Hour>());
    this.hoursOnCategories.put(Category.JOKER,new ArrayList<Hour>());
  }


  /***************************************************/

  public void resetHoursOnCategories()
  {
    this.hoursOnCategories.replace(Category.CS,new ArrayList<Hour>());
    this.hoursOnCategories.replace(Category.TM,new ArrayList<Hour>());
    this.hoursOnCategories.replace(Category.EC,new ArrayList<Hour>());
    this.hoursOnCategories.replace(Category.T2S,new ArrayList<Hour>());
    this.hoursOnCategories.replace(Category.JOKER,new ArrayList<Hour>());
  }

  /***************************************************/

  public void resetHoursUsedInTurn()
  {
    for(Hour h : this.hours) h.setUsedInTurn(false);
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
    if(this.availableBonus.get(Bonus.MOVE_HOURS) > 0)
    {
      System.out.println("removing moving hour");
      ++movedHours;
      this.availableBonus.replace(Bonus.MOVE_HOURS, this.availableBonus.get(Bonus.MOVE_HOURS) - 1);
    }
  }

  /***************************************************/

  public boolean workOnProject()
  {
    Card card = this.temporaryHand.get(this.temporaryHand.size()-1);
    if(this.availableBonus.get(Bonus.EARN_HOURS) >= card.getProjectCategoriesQuantity() && checkSkills(card.getCategoryProject(), card.getProjectCategoriesQuantity()) && checkHoursAvailability(card.getCategoryProject(), card.getProjectCategoriesQuantity()))
    {
      ++usedHours;
      this.availableBonus.replace(Bonus.EARN_HOURS, this.availableBonus.get(Bonus.EARN_HOURS) - 1);

      //Setting already used hours
      int used= 0;
      for(Hour h: this.hoursOnCategories.get(card.getCategoryProject()))
      {
        h.setUsedInTurn(true);
        ++used;
        if(used >= card.getProjectCategoriesQuantity()) break;
      }

      if(used < card.getProjectCategoriesQuantity() ) //If jokers should be used
      {
        for(Hour h: this.hoursOnCategories.get(Category.JOKER))
        {
          h.setUsedInTurn(true);
          ++used;
          if(used >= card.getProjectCategoriesQuantity()) break;
        }
      }

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
    this.addBonus(this.temporaryHand.get(this.temporaryHand.size()-1).getBonus());
    this.finalPoints += this.temporaryHand.get(this.temporaryHand.size()-1).getScoringPointsSkills();
    this.removeCardTemporaryHand();
  }

  /***************************************************/

  public boolean keepCardInHand()
  {
    if(this.availableBonus.get(Bonus.KEEP_IN_HAND) > 0) //If at least one KEEP_IN_HAND bonus
    {
      this.useKeepInHand();
      this.addCardHand(this.temporaryHand.get(this.temporaryHand.size()-1));
      this.removeCardTemporaryHand();
      return true;
    }
    return false;
  }

  /***************************************************/

  public boolean checkSkills(Category c, int quantity)
  {
    return c != Category.NULL && (this.availableSkills.get(c) + this.availableSkills.get(Category.JOKER)) > (quantity - 1);
  }

  /***************************************************/

  public boolean checkHoursAvailability(Category skill, int quantity)
  {
    System.out.println("hours on skill : " + this.hoursOnCategories.get(skill).size());
    //Checking if all hours are usable
    int needed = 0;
    for(Hour h: this.hoursOnCategories.get(skill))
    {
      if(!h.wasUsedInTurn()) ++needed;
      if(needed >= quantity) break;
    }

    if(needed < quantity ) //If jokers should be used
    {
      for(Hour h: this.hoursOnCategories.get(Category.JOKER))
      {
        if(!h.wasUsedInTurn()) ++needed;
        if(needed >= quantity) break;
      }
    }

    return (needed >= quantity) && (this.hoursOnCategories.get(skill).size() + this.hoursOnCategories.get(Category.JOKER).size()) >= quantity; //Checking if hours are well positionned
  }

  /***************************************************/

  public int countPoints()
  {
    int temp = 0;
    int max = 1;
    int imsi = 0;
    Branch lastBranch = null;

    //count the number of cards in the project:
    this.finalPoints += this.project.size();

    //count the longest branch streak:
    for(Card c: this.project)
    {
      if (c.getBranch() == Branch.IMSI || c.getBranch() == lastBranch)
      {
        if(c.getBranch() == Branch.IMSI) ++imsi;
        ++temp;
        if (temp>max) {max = temp;}
      }
      else if(imsi > 0)
      {
        temp = imsi+1;
        imsi = 0;
      }
      else
      {
        lastBranch = c.getBranch();
        temp = 1;
      }
    }
    System.out.println("biggest combo : " + max);
    this.finalPoints += max;

    //count the victory points bonuses and maluses:
    for(Card c: this.project)
    {
      this.finalPoints += c.getScoringPointsProjects();
    }
    //points from the Skills were already added during the game before deleting the Card instances

    //count the mention points:
    for(int i: this.acceptedMentions)
    {
      this.finalPoints += Game.mentions.get(i).getPoints();
    }

    return this.finalPoints;
  }

  /***************************************************/

  public void resetKeepInHand()
  {
    this.availableBonus.replace(Bonus.KEEP_IN_HAND, this.availableBonus.get(Bonus.KEEP_IN_HAND) + this.inHand);
    this.inHand = 0;
  }

  /***************************************************/

  public void useKeepInHand()
  {
    ++this.inHand;
    this.availableBonus.replace(Bonus.KEEP_IN_HAND, this.availableBonus.get(Bonus.KEEP_IN_HAND) - 1);
  }

  /***************************************************/

  public void addSkill(Category skill)
  {
    if(skill != Category.NULL) this.availableSkills.replace(skill, this.availableSkills.get(skill) + 1);
  }

  /***************************************************/

  public void addBonus(Bonus bonus)
  {
    if(bonus == Bonus.EARN_HOURS) this.hours.add(new Hour());

    if(bonus != Bonus.NULL) this.availableBonus.replace(bonus, this.availableBonus.get(bonus) + 1);
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

  public void removeCardTemporaryHand()
  {
    if(this.temporaryHand.get(this.temporaryHand.size()-1) != null) this.temporaryHand.remove(this.temporaryHand.size()-1);
  }

  /***************************************************/

  public void addCardTemporaryHand(Card card)
  {
    this.temporaryHand.add(card);
  }

  /***************************************************/

  public void addCardProject(Card card)
  {
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

  public void resetBlockedImages(){this.blockedImages.clear();}
  public Map<Bonus, Integer> getAvailableBonus(){return this.availableBonus;}
  public Map<Category, Integer> getAvailableSkills(){return this.availableSkills;}
  public Set<Integer> getAcceptedMentions(){return this.acceptedMentions;}
  public Set<Integer> getDeniedMentions(){return this.deniedMentions;}
  public int getNumberMovedHours(){return this.movedHours;}
  public int getNumberUsedHours(){return this.usedHours;}
  public List<Hour> getHoursOnCategories(Category cat){return this.hoursOnCategories.get(cat);}
  public List<BufferedImage> getImages(){return this.img;}
  public List<Point> getPoints(){return this.imgPoint;}
  public Set<Integer> getBlockedImages(){return this.blockedImages;}
  public List<Hour> getHours(){return this.hours;}
  public int getFinalPoints(){return this.finalPoints;}
  public void setName(String name){this.name = name;}
  public String getName(){return this.name;}
  public List<Card> getHand(){return this.hand;}
  public List<Card> getProject(){return this.project;}
  public List<Card> getTemporaryHand(){return this.temporaryHand;}
}
