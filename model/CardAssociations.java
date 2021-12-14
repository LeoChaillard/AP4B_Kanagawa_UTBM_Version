/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;


public class CardAssociations extends Card{
  //Attributes
  private Associations association;

  //Constructors
  public CardAssociations()
  {

  }

  /***************************************************/

  public CardAssociations(int id, int numberSkills, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, Category project, Associations association)
  {
    super(id,numberSkills, scoringPointsSkills, scoringPointsProjects, bonus,branch,skills,project);
    this.association = association;
  }

  //Methods

  //redéfinir isUsed()
  public boolean isUsed()
  {
    //code
    return false;
  }
 }
