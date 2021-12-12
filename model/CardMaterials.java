/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/
package model;

public class CardMaterials extends Card{

  private Materials materials; // étant donné qu'il y a 1 matériau par carte il faut mettre materials sans s en attribut
                              // à modifier même dans diagramme de classes
  //constructeur
    public CardMaterials(Materials materials){
    this.materials = materials;
    }
  //redéfinir isUsed()
  public boolean isUsed(){
        //code
        return false;
    }
 }