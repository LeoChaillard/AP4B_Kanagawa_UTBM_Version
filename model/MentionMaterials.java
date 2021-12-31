/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;
import java.util.*;

/**
 * Class defining a material type mention.
 */
public class MentionMaterials extends Mention {
  //Constructor
  public MentionMaterials(int id, int points, int numberOfElements, Bonus bonus, String name)
  {
    super(id, points, numberOfElements, bonus, name);
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    //counting the number of different elements
    Set<Materials> materialsSet = new HashSet<Materials>();
    for(Card c : p.getProject())
      if( (c instanceof CardMaterials) &&  Materials.values()[c.getElement()] != Materials.NULL && !materialsSet.contains(Materials.values()[c.getElement()]))
        materialsSet.add(Materials.values()[c.getElement()]);

      if(materialsSet.size() >= this.getNumberOfElements()) return true;
    return false;
  }
}
