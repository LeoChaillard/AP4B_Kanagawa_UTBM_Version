/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

/**
 * Class defining a card.
 * This is an abstract class from which
 * different types of cards are derivated.
 */
public abstract class Card {
  //Attributs
  private int id;
  private int scoringPointsSkills;
  private int scoringPointsProjects;
  private Bonus bonus;
  private Branch branch;
  private Category categorySkills;
  private Category categoryProject;
  private int projectCategoriesQuantity;

  //Constructors
  public Card()
  {

  }

  /***************************************************/

  public Card(int id, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, int projectCategoriesQuantity, Category project)
  {
    this.id = id;
    this.scoringPointsSkills = scoringPointsSkills;
    this.scoringPointsProjects = scoringPointsProjects;
    this.bonus = bonus;
    this.branch = branch;
    this.categorySkills = skills;
    this.categoryProject = project;
    this.projectCategoriesQuantity = projectCategoriesQuantity;
  }

  /***************************************************/

  public Card(int id, Category skills, Bonus bonus, Branch branch) //Starter cards
  {
    this.id = id;
    this.categorySkills = skills;
    this.bonus = bonus;
    this.branch = branch;
  }

  //Methods
  public int getElement(){return -1;} //Meant to be overrided, getting the attribute of derivated classes
  public int getId(){return this.id;}
  public int getScoringPointsSkills(){return this.scoringPointsSkills;}
  public int getScoringPointsProjects(){return this.scoringPointsProjects;}
  public Bonus getBonus(){return this.bonus;}
  public Branch getBranch(){return this.branch;}
  public void setBranch(Branch branch){this.branch = branch;}
  public Category getCategorySkills(){return this.categorySkills;}
  public Category getCategoryProject(){return this.categoryProject;}
  public int getProjectCategoriesQuantity(){return this.projectCategoriesQuantity;}
}
