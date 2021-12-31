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
import java.awt.Point;

import javax.swing.JFrame;

/**
 * Class defining a mouse listener for
 * whenever the player needs to pick up a board's column.
 * His mouse click position will be checked and the chosen
 * cards (+ his hand cards) will be transfered to his temporary hand.
 */
public class BoardMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public BoardMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    //Picking up a column
    Player player = this.game.getPlayers().get(this.game.getPlayerIndex());
    if(this.game.isPickingUpColumn() && player.getTemporaryHand().isEmpty())
    {
      Board board = this.game.getWindow().getBoard();
      for(int i=0;i<this.game.getNumberPlayers();++i) //For each column (one player = one column)
      {
        Point pos = evt.getPoint();

        //Checking if the player clicked on one of the columns
        if( (!board.isColumnEmpty(i)) && board.columnContains(i, pos)) //if column contains click coordinates
        {
          //Add chosen cards (+ cards in hand) to player's temporary hand
          System.out.println("adding cards to temporary hand");
          Card [][] boardSlots = this.game.getWindow().getBoard().getSlots();
          for(int j=0;j<3;++j) if(boardSlots[i][j] != null) player.addCardTemporaryHand(boardSlots[i][j]); //Chosen cards
          player.transferHandToTemporaryHand(); //Hand cards
          this.game.getWindow().getBoard().removeColumn(i+1);

          //Updating window back to default state
          this.game.getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          this.game.getWindow().getBoard().resetHighlight();
          this.game.getWindow().repaint();
        }
      }
    }
  }

  /***************************************************/

  @Override
  public void mouseExited(MouseEvent evt){}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

}
