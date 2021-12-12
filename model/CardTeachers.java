/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/
package model;

public class CardTeachers extends Card{

    private Teachers teachers; // étant donné qu'il y a 1 prof par carte il faut mettre teacher sans s en attribut
                                // à modifier même dans diagramme de classes

    //constructeur
    public CardTeachers(Teachers teachers){
    this.teachers = teachers;
    }


    //redéfinir isUsed()
    public boolean isUsed(){
        //code
        return false;
    }
 }