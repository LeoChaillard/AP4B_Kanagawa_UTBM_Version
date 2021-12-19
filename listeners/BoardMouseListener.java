package listeners;
import manager.*;
import view.*;
import model.*;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

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
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

  @Override
  public void mouseClicked(MouseEvent evt)
  {
    Player player = Game.players.get(this.game.getPlayerIndex());
    //Picking up a column
    if(this.game.isPickingUpColumn() && player.getTemporaryHand().isEmpty())
    {
      for(int i=0;i<this.game.getNumberPlayers();++i) //For each column (one player = one column)
      {
        int x = evt.getX();
        int y = evt.getY();

        //Checking if the player clicked on one of the columns
        if( (!this.game.getWindow().getBoard().isColumnEmpty(i)) && (Board.columnsXCoordinate[i] - Board.columnWidth/2 <=x) && (Board.columnsXCoordinate[i] + Board.columnWidth/2 >=x) && (Board.columnsYCoordinate[i] - Board.columnHeight <=y) && (Board.columnsYCoordinate[i] + Board.columnHeight >=y) )
        {
          //Add chosen card to player's hand
          System.out.println("adding cards to temporary hand");
          Card [][] boardSlots = this.game.getWindow().getBoard().getSlots();
          for(int j=0;j<3;++j) if(boardSlots[i][j] != null) player.addCardTemporaryHand(boardSlots[i][j]);
          this.game.getWindow().getBoard().removeColumn(i+1);

          //Updating window
          this.game.getWindow().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          this.game.getWindow().getBoard().resetHighlight();
          this.game.getWindow().repaint();
        }
      }
    }
  }

  /***************************************************/

  @Override
  public void mouseExited(MouseEvent evt)
  {
    this.game.getWindow().getBoard().resetHighlight();
    this.game.getWindow().repaint();
  }
}
