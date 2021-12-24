/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class CardCertificates extends Card{
  //Attributs
  private int certificate;

  //Constructors
  public CardCertificates()
  {

  }

  /***************************************************/

  public CardCertificates(int id, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, int projectCategoriesQuantity, Category project, int certificate)
  {
    super(id, scoringPointsSkills, scoringPointsProjects, bonus, branch, skills, projectCategoriesQuantity, project);
    this.certificate = certificate;
  }

  //Methods

  //redéfinir isUsed()
  public boolean isUsed()
  {
    //code
    return false;
  }

  public int getElement(){return certificate;}
}
