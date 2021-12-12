/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/
package model;

public class CardCertificates extends Card{

    //Attributs
    private int certificates; 


    //constructeur
    public CardCertificates(int certificates){
    this.certificates = certificates;
    }


    //redéfinir isUsed()
    public boolean isUsed(){
        //code
        return false;
    }
}