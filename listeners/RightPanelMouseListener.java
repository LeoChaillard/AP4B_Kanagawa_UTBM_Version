package listeners;
import manager.*;
import view.*;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

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
  public void mouseExited(MouseEvent evt) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

  @Override
  public void mouseClicked(MouseEvent evt)
  {
    //Treating cards in temporary hand
    if(this.game.isPickingUpColumn() && !Game.players.get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
    {
      int x = evt.getX();
      int y = evt.getY();

      //Player has to click on his cards to treat them
      if( (RightPanel.temporaryCardXCoordinate - RightPanel.temporaryCardWidth/2 <= x) && (RightPanel.temporaryCardXCoordinate + RightPanel.temporaryCardWidth/2 >= x) && (RightPanel.temporaryCardYCoordinate - RightPanel.temporaryCardHeight/2 <= y) && (RightPanel.temporaryCardYCoordinate + RightPanel.temporaryCardHeight/2 >= y))
      {
        System.out.println("click temporary card");
        this.game.getWindow().getTreatCardsPane().setVisible(true);
        this.game.getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.game.getWindow().repaint();
      }

    }
  }
}
