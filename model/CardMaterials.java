/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package model;

public class CardMaterials extends Card{
  //Attributs
  private Materials materials; // étant donné qu'il y a 1 matériau par carte il faut mettre materials sans s en attribut
                              // à modifier même dans diagramme de classes
  //Constructors
  public CardMaterials()
  {

  }

  /***************************************************/

  public CardMaterials(Materials materials)
  {
    this.materials = materials;
  }

  //Methods

  //redéfinir isUsed()
  public boolean isUsed()
  {
    //code
    return false;
  }
 }
