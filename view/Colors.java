/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import java.awt.Color;

public class Colors{
  //Attributes
  public static final Color[] boardDarkColors = {
      new Color(0x9acd00),
      new Color(0xffcd00),
      new Color(0x2fcdcd),
      new Color(0xff3000)
  };

  public static final Color[] boardLightColors = {
      new Color(0x9acd00),
      new Color(0xffcd00),
      new Color(0x2fcdcd),
      new Color(0xff3000)
  };

  public static final Color[] cardLightColors = {
      new Color(0xcfff39),
      new Color(0xffe783),
      new Color(0x95e5e5),
      new Color(0xff896a)
};

  public static final Color[] cardDarkColors = {
          new Color(0x208000),
          new Color(0xCC7A00),
          new Color(0x001133),
          new Color(0x990000)
  };

  public static final Color[] skillColors = {
          Color.BLUE, //T2S
          Color.YELLOW, //EC
          Color.lightGray, //TM
          Color.GREEN, //CS
          Color.MAGENTA //JOKER
  };

  public static final Color[] cardThemeColors = {
          new Color(0x208000), //Certificates
          new Color(0x001133), //Associations
          new Color(0x990000), //Teachers
          new Color(0xCC7A00) //Materials
  };

  public static final Color[] branchColors = {
          new Color(0x208000), //INFO
          new Color(0x95e5e5), //GMC
          new Color(0xff896a), //EDIM
          new Color(0xffe783), //ENERGIE
          new Color(0x800080) //IMSI
  };


  //Methods
  public static final Color[] getSkillColors(){return skillColors;}
  public static final Color[] getBoardLightColors(){return boardLightColors;}
  public static final Color[] getBoardDarkColors(){return boardDarkColors;}
  public static final Color[] getCardLightColors(){return cardLightColors;}
  public static final Color[] getCardDarkColors(){return cardDarkColors;}
  public static final Color[] getCardThemeColors(){return cardThemeColors;}
  public static final Color[] getBranchColors(){return branchColors;}




}
