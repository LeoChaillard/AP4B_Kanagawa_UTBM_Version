/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

/**
 * Class defining a teacher type card.
 */
public class CardTeachers extends Card{
  //Attributes
  private Teachers teacher;

  //Constructors
  public CardTeachers()
  {

  }

  /***************************************************/

  public CardTeachers(int id, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, int projectCategoriesQuantity,Category project, Teachers teacher)
  {
    super(id, scoringPointsSkills, scoringPointsProjects,bonus, branch, skills, projectCategoriesQuantity, project);
    this.teacher = teacher;
  }

  //Methods
  public int getElement(){return this.teacher.getPos();}
 }
