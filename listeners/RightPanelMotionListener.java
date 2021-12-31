/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package listeners;
import manager.*;
import view.*;

import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Point;

/**
 * Class defining a mouse motion listener for
 * whenever the player clicks on a temporary hand card.
 * His mouse's cursor will be updated.
 */
public class RightPanelMotionListener implements MouseMotionListener{
  //Attributes
  private Game game;

  //Constructor
  public RightPanelMotionListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseDragged(MouseEvent evt) {}

  @Override
  public void mouseMoved(MouseEvent evt)
  {
    //Treating cards in temporary hand
    if(this.game.isPickingUpColumn() && !this.game.getPlayers().get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
    {
      Point pos = evt.getPoint();

      //Changing cursor when goes over temporary card
      if(this.game.getWindow().getRightPanel().temporaryHandContains(pos))
      {
        this.game.getWindow().setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.game.getWindow().repaint();
      }
      else
      {
        this.game.getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.game.getWindow().repaint();
      }

    }
  }

}
