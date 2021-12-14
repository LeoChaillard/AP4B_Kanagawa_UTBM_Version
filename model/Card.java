/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public abstract class Card {

  //Attributs
  private int id;
  private int numberOfCategorySkills;
  private int scoringPointsSkills;
  private int scoringPointsProjects;
  private Bonus bonus;
  private Branch branch;
  private Category categorySkills;
  private Category categoryProject;

  //Constructors
  public Card()
  {

  }

  /***************************************************/

  public Card(int id, int numberSkills, int scoringPointsSkills, int scoringPointsProjects, Bonus bonus,Branch branch, Category skills, Category project)
  {
    this.id = id;
    this.numberOfCategorySkills = numberSkills;
    this.scoringPointsSkills = scoringPointsSkills;
    this.scoringPointsProjects = scoringPointsProjects;
    this.bonus = bonus;
    this.branch = branch;
    this.categorySkills = skills;
    this.categoryProject = project;
  }

  //Methods
  public void setid(int id){
    this.id = id;
  }

  public int getid(){
    return this.id;
  }

  public void setscoringPointsSkills(int scs){
    this.scoringPointsSkills = scs;
  }

  public int getscoringPointsSkills(){
    return this.scoringPointsSkills;
  }

  public void setscoringPointsProjects(int scp){
    this.id = scp;
  }

  public int getscoringPointsProjects(){
    return this.scoringPointsProjects;
  }

  public void setbonus(Bonus bonus){
    this.bonus = bonus;
  }

  public Bonus getbonus(){
    return this.bonus;
  }

  public void setbranch(Branch branch){
    this.branch = branch;
  }

  public Branch getbranch(){
    return this.branch;
  }

  public void setcategorySkills(Category cs){
    this.categorySkills = cs;
  }

  Category getcategorySkills(){
    return this.categorySkills;
  }

  public void setcategoryProject(Category cp){
    this.categoryProject = cp;
  }

  Category getcategoryProject(){
    return this.categoryProject;
  }

  public abstract boolean isUsed();


}
