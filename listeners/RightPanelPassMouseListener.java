package listeners;
import manager.*;
import view.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class RightPanelPassMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public RightPanelPassMouseListener(Game game)
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
    if(!this.game.isEndOfGame())
    {
      if(this.game.getWindow().getBoard().getRemainingColumns() == 1)
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "You're the last player!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
      else if(Board.addedRows == 3)
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "All rows are already filled up!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
      else if(!this.game.isPickingUpColumn() && Game.players.get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
      {
        this.game.passTurn();
        this.game.nextTurn();
      }
      else
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "Can't pass while picking up a column!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
    }
  }

}
