/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class MentionArrow extends Mention {

  //Constructor
  public MentionArrow(int id, int points, int numberOfElements, Bonus bonus, String name)
  {
    super(id, points, numberOfElements, bonus, name);
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    //counting used and remaining bonus
    return (getNumberOfElements() <= (p.getAvailableBonus().get(Bonus.MOVE_HOURS) + p.getNumberMovedHours()));
  }

}
