package listeners;
import manager.*;
import view.*;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

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
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e)
  {
    //Picking up a column
    if(this.game.isPickingUpColumn() && Game.players.get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
    {
      for(int i=0;i<this.game.getNumberPlayers();++i)
      {
        int x = e.getX();
        int y = e.getY();

        //Highlighting a column when the mouse goes over it
        if( (!this.game.getWindow().getBoard().isColumnEmpty(i)) &&(Board.columnsXCoordinate[i] - Board.columnWidth/2 <=x) && (Board.columnsXCoordinate[i] + Board.columnWidth/2 >=x) && (Board.columnsYCoordinate[i] - Board.columnHeight <=y) && (Board.columnsYCoordinate[i] + Board.columnHeight >=y) )
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

}
