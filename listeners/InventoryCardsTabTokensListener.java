package listeners;
import manager.*;
import view.*;
import model.*;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JOptionPane;

public class InventoryCardsTabTokensListener extends MouseAdapter{
  //Attributes
  private Game game;

  //Constructor
  public InventoryCardsTabTokensListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    if(!this.game.isEndOfGame())
    {
        if(Game.players.get(Game.playerIndex).getAvailableBonus().get(Bonus.IMSI_TOKEN) > 0)
        {
          Player player = Game.players.get(Game.playerIndex);
          for(int i=1;i<player.getProject().size();++i)
          {
            Point p = evt.getPoint();
            if(this.game.getWindow().getInventory().getInventoryCardsTab().cardContains(i, p)) //if card contains click coordinates
            {
              String [] options = new String[3];
              String info = "INFO";
              String gmc = "GMC";
              String edim = "EDIM";
              String energie = "ENERGIE";
              switch(player.getProject().get(i).getBranch())
              {
                case INFO:
                  options[0] = gmc;
                  options[1] = edim;
                  options[2] = energie;
                break;
                case GMC:
                  options[0] = info;
                  options[1] = edim;
                  options[2] = energie;
                break;
                case EDIM:
                  options[0] = info;
                  options[1] = gmc;
                  options[2] = energie;
                break;
                case ENERGIE:
                  options[0] = info;
                  options[1] = gmc;
                  options[2] = edim;
                break;
              }
              int x = JOptionPane.showOptionDialog(null,"Choose a new branch!", "Use of an imsi token", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

              if(x == 0) player.getProject().get(i).setBranch(Branch.valueOf(options[0]));
              else if(x == 1) player.getProject().get(i).setBranch(Branch.valueOf(options[1]));
              else if(x == 2) player.getProject().get(i).setBranch(Branch.valueOf(options[2]));

              this.game.getWindow().getInventory().getInventoryCardsTab().repaint();
              Game.players.get(Game.playerIndex).getAvailableBonus().replace(Bonus.IMSI_TOKEN, Game.players.get(Game.playerIndex).getAvailableBonus().get(Bonus.IMSI_TOKEN) - 1);
            }
          }
        }
      }
    }


}
