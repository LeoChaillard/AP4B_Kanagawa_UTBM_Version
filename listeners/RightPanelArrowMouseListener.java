package listeners;
import manager.*;
import view.*;
import model.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

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
  public void mouseExited(MouseEvent evt) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

    @Override
    public void mouseClicked(MouseEvent evt)
    {
      if(!this.game.isEndOfGame())
      {
        Player p = Game.players.get(Game.playerIndex);
        if(p.getTemporaryHand().size() > 1)
        {
          Card tmp;
          Card toSwap = p.getTemporaryHand().get(p.getTemporaryHand().size()-1);
          for(int i=p.getTemporaryHand().size()-1;i>0;--i)
            p.getTemporaryHand().set(i, p.getTemporaryHand().get(i-1));
          p.getTemporaryHand().set(0, toSwap);
          this.game.getWindow().getRightPanel().repaint();
        }
      }
    }

}
