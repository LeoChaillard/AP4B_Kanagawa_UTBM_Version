/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import java.awt.Color;

public class Colors{
  //Attributes
  public static final Color[] backgroundColors = {
      new Color(0xFFFFFF), //Board background
      new Color(0xF2E1C1) //RightPanel background
  };

  public static final Color[] boardColors = {
      new Color(0xc4342d), //Visible slots
      new Color(0xc4342d), //Not Visible slots
      new Color(0xfdf1b8), //Columns background
      new Color(153,153,153,50) //Highlighted columns
  };

  public static final Color[] cardColors = {
      new Color(0x8C5C10) //Learn skills part
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
          new Color(0x36D800), //INFO
          new Color(0x95e5e5), //GMC
          new Color(0xff896a), //EDIM
          new Color(0xffe783), //ENERGIE
          new Color(0x800080) //IMSI
  };

  //Methods
  public final static Color[] getBackgroundColors(){return backgroundColors;}
  public static final Color[] getSkillColors(){return skillColors;}
  public static final Color[] getBoardColors(){return boardColors;}
  public static final Color[] getCardColors(){return cardColors;}
  public static final Color[] getCardThemeColors(){return cardThemeColors;}
  public static final Color[] getBranchColors(){return branchColors;}




}
