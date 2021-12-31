/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;
import java.util.*;

/**
 * Class defining a association type mention.
 */
public class MentionAssociations extends Mention {
  //Attributs
  private List<Associations> associations;

  //Constructor
  public MentionAssociations(int id, int points, int numberOfElements, Bonus bonus, List<Associations>  associations, String name)
  {
    super(id, points, numberOfElements, bonus, name);
    this.associations = associations;
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    //for each element, checking if element is in associations list
    for(Associations a : this.associations)
    {
      int value = a.getPos();
      boolean isIn = false;

      for(Card c : p.getProject()) if( (c instanceof CardAssociations) &&  c.getElement() == value) isIn = true;
      if(!isIn) return false;
    }
    return true;
  }
}
