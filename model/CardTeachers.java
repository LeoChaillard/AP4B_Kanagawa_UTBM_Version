/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class CardTeachers extends Card{
  //Attributes
  private Teachers teacher;

  //Constructors
  public CardTeachers()
  {

  }

  /***************************************************/

  public CardTeachers(int id, int numberSkills, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, Category project, Teachers teacher)
  {
    super(id, numberSkills, scoringPointsSkills, scoringPointsProjects,bonus,branch,skills,project);
    this.teacher = teacher;
  }

  //Methods

  //redéfinir isUsed()
  public boolean isUsed()
  {
    //code
    return false;
  }
 }
