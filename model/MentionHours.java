/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

/**
 * Class defining a hour type mention.
 */
public class MentionHours extends Mention {
  //Constructor
  public MentionHours(int id, int points, int numberOfElements, Bonus bonus, String name)
  {
    super(id, points, numberOfElements, bonus, name);
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    //suming up number of used and non-used EARN_HOURS bonus
    return (getNumberOfElements() <= (p.getAvailableBonus().get(Bonus.EARN_HOURS) + p.getNumberUsedHours()));
  }
}
