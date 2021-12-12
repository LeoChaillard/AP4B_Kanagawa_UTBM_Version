/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;


public class CardAssociations extends Card{
  //Attributes
  private Associations associations; // étant donné qu'il y a 1 asso par carte il faut mettre association sans s en attribut
                                        // à modifier même dans diagramme de classes
  //Constructors
  public CardAssociations()
  {

  }

  /***************************************************/

  public CardAssociations(Associations associations)
  {
    this.associations = associations;
  }

  //Methods
  
  //redéfinir isUsed()
  public boolean isUsed()
  {
    //code
    return false;
  }
 }
