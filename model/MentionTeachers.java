/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;
import java.util.*;

/**
 * Class defining a teacher type mention.
 */
public class MentionTeachers extends Mention {
  //Attributs
  private boolean sameElement;

  //Constructor
  public MentionTeachers(int id, int points, int numberOfElements, Bonus bonus, boolean sameElement, String name)
  {
    super(id, points, numberOfElements, bonus, name);
    this.sameElement = sameElement;
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    int teachers = 0;
    if(this.sameElement) //suming up the number of same teacher elements
    {
      Map<Teachers, Integer> teachersMap = new HashMap<Teachers, Integer>();
      teachersMap.put(Teachers.GECHTER, 0);
      teachersMap.put(Teachers.PAIRE, 0);
      teachersMap.put(Teachers.BAUME, 0);
      teachersMap.put(Teachers.ROTH, 0);

      for(Card c : p.getProject())
        if( (c instanceof CardTeachers) && Teachers.values()[c.getElement()] != Teachers.NULL)
          teachersMap.replace(Teachers.values()[c.getElement()], teachersMap.get(Teachers.values()[c.getElement()]) + 1);

      for(Teachers t : teachersMap.keySet())
        if(teachersMap.get(t) >= this.getNumberOfElements()) return true;
    }
    else //counting the number of different teacher elements
    {
      Set<Teachers> teachersSet = new HashSet<Teachers>();
      for(Card c : p.getProject())
        if( (c instanceof CardTeachers) &&  Teachers.values()[c.getElement()] != Teachers.NULL && !teachersSet.contains(Teachers.values()[c.getElement()]))
          teachersSet.add(Teachers.values()[c.getElement()]);

      if(teachersSet.size() >= this.getNumberOfElements()) return true;
    }
    return false;
  }
}
