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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.swing.JOptionPane;

/**
 * Class defining a mouse listener for
 * whenever the player has at least an IMSI_TOKEN bonus available.
 * By clicking on one of his inventory project cards, he'll be able
 * to change the cards' branch.
 */
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
      Player player = this.game.getPlayers().get(this.game.getPlayerIndex());
      if(player.getAvailableBonus().get(Bonus.IMSI_TOKEN) > 0) //at least one IMSI_TOKEN bonus
      {
        for(int i=1;i<player.getProject().size();++i)
        {
          Point pos = evt.getPoint();
          if(this.game.getWindow().getInventory().getInventoryCardsTab().cardContains(i, pos)) //if card contains click coordinates
          {
            String [] options = new String[3];
            String info = "INFO";
            String gmc = "GMC";
            String edim = "EDIM";
            String energie = "ENERGIE";
            switch(player.getProject().get(i).getBranch()) //choices based on current card branch
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
              player.getAvailableBonus().replace(Bonus.IMSI_TOKEN, player.getAvailableBonus().get(Bonus.IMSI_TOKEN) - 1); //player loses one IMSI_TOKEN bonus
          }
        }
      }
    }
  }


}
