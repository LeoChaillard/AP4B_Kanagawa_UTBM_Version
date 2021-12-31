/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package listeners;
import manager.*;
import view.*;
import model.*;

import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.swing.JFrame;

/**
 * Class defining a mouse motion listener for
 * whenever the player needs to pick up a board's column.
 * His mouse's cursor will be updated and the pointed
 * column will be highlighted.
 */
public class BoardMotionListener implements MouseMotionListener{
  //Attributes
  private Game game;

  //Constructor
  public BoardMotionListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseMoved(MouseEvent evt)
  {
    //Picking up a column
    Player player = this.game.getPlayers().get(this.game.getPlayerIndex());
    if(this.game.isPickingUpColumn() && player.getTemporaryHand().isEmpty())
    {
      Board board = this.game.getWindow().getBoard();
      for(int i=0;i<this.game.getNumberPlayers();++i) //For each column (one player = one column)
      {
        Point pos = evt.getPoint();

        //Highlighting a column when the mouse goes over it
        if( (!this.game.getWindow().getBoard().isColumnEmpty(i)) && board.columnContains(i, pos)) //if column contains click coordinates
        {
          this.game.getWindow().setCursor(new Cursor(Cursor.HAND_CURSOR));
          this.game.getWindow().getBoard().setHighlight(i, true);
          this.game.getWindow().repaint();
        }
        else if(this.game.getWindow().getBoard().isHighlighted(i)) //Going back to the default state
        {
          this.game.getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          this.game.getWindow().getBoard().setHighlight(i, false);
          this.game.getWindow().repaint();
        }
      }
    }
  }

  /***************************************************/

  @Override
  public void mouseDragged(MouseEvent e) {}

}
