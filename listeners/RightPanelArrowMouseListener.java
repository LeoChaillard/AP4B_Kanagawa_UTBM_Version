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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * Class defining a mouse listener for
 * the temporary hand cards scrolling.
 */
public class RightPanelArrowMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public RightPanelArrowMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    if(!this.game.isEndOfGame())
    {
      Player player = this.game.getPlayers().get(this.game.getPlayerIndex());
      if(player.getTemporaryHand().size() > 1)
      {
        Card toSwap = player.getTemporaryHand().get(player.getTemporaryHand().size()-1); //Last temporary hand card is at the front
        for(int i=player.getTemporaryHand().size()-1;i>0;--i) player.getTemporaryHand().set(i, player.getTemporaryHand().get(i-1));
        player.getTemporaryHand().set(0, toSwap); //Moving card to the tempory hand back
        this.game.getWindow().getRightPanel().repaint();
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
