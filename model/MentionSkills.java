/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class MentionSkills extends Mention {

  //Constructor
  public MentionSkills(int id, int points, int numberOfElements, Bonus bonus, String name)
  {
    super(id, points, numberOfElements, bonus, name);
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    //Nombre identique => code identique de Teacher
    return (getNumberOfElements() <= p.getAvailableSkills().get(Category.CS) || getNumberOfElements() <= p.getAvailableSkills().get(Category.TM) || getNumberOfElements() <= p.getAvailableSkills().get(Category.EC) || getNumberOfElements() <= p.getAvailableSkills().get(Category.T2S));
  }
}
