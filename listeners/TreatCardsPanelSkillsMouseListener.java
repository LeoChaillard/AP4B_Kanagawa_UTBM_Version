package listeners;
import manager.*;
import view.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class TreatCardsPanelSkillsMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public TreatCardsPanelSkillsMouseListener(Game game)
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
    System.out.println("click on skills");
    Game.players.get(this.game.getPlayerIndex()).learnNewSkills();
    this.game.getWindow().repaint();
    this.game.getWindow().getTreatCardsPane().setVisible(false);

    //When temporary hand is empty, going to next turn
    if(Game.players.get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
    {
      this.game.setPickingUpColumn(false);
      this.game.nextTurn();
    }
  }

}
