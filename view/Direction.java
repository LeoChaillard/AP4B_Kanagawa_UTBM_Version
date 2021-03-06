/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;

/**
 * Class defining x and y movements on the screen
 * relatively to the chosen origin position and scale.
 */
public class Direction{
  //Attributes
  private final float originX;
  private final float originY;
  private float xMove;
  private float yMove;
  private float scaleX = 1f;
  private float scaleY = 1f;

  //Constructor
  public Direction(float originX,float originY)
  {
    this.originX = originX;
    this.originY = originY;
  }

  //Methods
  public void setScale(float sX,float sY){this.scaleX = sX;this.scaleY = sY;}

  /***************************************************/

  public void move(float x, float y)
  {
    this.xMove += x;
    this.yMove += y;
  }

  /***************************************************/

  public void left (float l){this.move(-l,0);}

  /***************************************************/

  public void right (float r){this.move(r,0);}

  /***************************************************/

  public void up (float u){this.move(0,-u);}

  /***************************************************/

  public void down (float d){this.move(0,d);}

  /***************************************************/

  public float getMoveX()
  {
    return xMove;
  }

  /***************************************************/

  public float getMoveY()
  {
    return yMove;
  }

  /***************************************************/

  public float getX() {return (originX + getMoveX()) * this.scaleX;}

  /***************************************************/

  public float getY() {return (originY + getMoveY()) * this.scaleY;}

  /***************************************************/

  public void setMove(float x,float y)
  {
    this.xMove = x;
    this.yMove = y;
  }

  /***************************************************/

  public void resetMove() {setMove(0, 0);}
}
