/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class CardMaterials extends Card{
  //Attributs
  private Materials material;

  //Constructors
  public CardMaterials()
  {

  }

  /***************************************************/

  public CardMaterials(int id, int numberSkills, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, Category project, Materials material)
  {
    super(id,numberSkills, scoringPointsSkills, scoringPointsProjects, bonus,branch,skills,project);
    this.material = material;
  }

  //Methods

  //redéfinir isUsed()
  public boolean isUsed()
  {
    //code
    return false;
  }
 }
