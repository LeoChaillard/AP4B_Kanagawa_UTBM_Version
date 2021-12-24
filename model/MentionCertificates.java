/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class MentionCertificates extends Mention {

  //Constructor
  public MentionCertificates(int id, int points, int numberOfElements, Bonus bonus, String name)
  {
    super(id, points, numberOfElements, bonus, name);
  }

  //Methods
  public boolean checkCriteria(Player p)
  {
    //check number of certificates overall in project
    //sum
    int sum = 0;
    for(Card c : p.getProject()) if(c instanceof CardCertificates) sum += c.getElement();

    return getNumberOfElements() <= sum;
  }
}
