/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package listeners;
import manager.*;
import view.*;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;

/**
 * Class defining a mouse listener for
 * whenever the player clicks on a temporary hand card.
 * His mouse click position will be checked and his choices displayed.
 */
public class RightPanelMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public RightPanelMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    if(!this.game.isEndOfGame())
    {
      //Treating cards in temporary hand
      if(this.game.isPickingUpColumn() && !this.game.getPlayers().get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
      {
        Point pos = evt.getPoint();

        //Player has to click on his cards to treat them
        if(this.game.getWindow().getRightPanel().temporaryHandContains(pos))
        {
          System.out.println("click temporary card");
          this.game.getWindow().getTreatCardsPane().setVisible(true); //displaying panel with card treatement choices
          this.game.getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          this.game.getWindow().repaint();
        }

      }
    }
  }

  /***************************************************/

  @Override
  public void mouseExited(MouseEvent evt) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

}
