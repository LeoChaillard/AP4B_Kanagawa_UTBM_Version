package listeners;
import manager.*;
import view.*;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

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
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e)
  {
    //Treating cards in temporary hand
    if(this.game.isPickingUpColumn() && !Game.players.get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
    {
      int x = e.getX();
      int y = e.getY();

      //Changing cursor when goes over temporary card
      if( (RightPanel.temporaryCardXCoordinate - RightPanel.temporaryCardWidth/2 <= x) && (RightPanel.temporaryCardXCoordinate + RightPanel.temporaryCardWidth/2 >= x) && (RightPanel.temporaryCardYCoordinate - RightPanel.temporaryCardHeight/2 <= y) && (RightPanel.temporaryCardYCoordinate + RightPanel.temporaryCardHeight/2 >= y))
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
