/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/
package model;


public class CardAssociations extends Card{

  private Associations associations; // étant donné qu'il y a 1 asso par carte il faut mettre association sans s en attribut
                                        // à modifier même dans diagramme de classes

  public CardAssociations(Associations associations){
    this.associations = associations;
  }

  //redéfinir isUsed()
  public boolean isUsed(){
        //code
        return false;
    }
 }