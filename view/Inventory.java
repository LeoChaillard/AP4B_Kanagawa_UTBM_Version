/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;

import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;

import java.util.*;

/**
 * Class Defining the player's inventory.
 * It contains two tabs for cards and items.
 */
public class Inventory extends JInternalFrame{
  //Attributes
  private static final int INVENTORY_HEIGHT = 503;
  private static final int INVENTORY_LENGTH = 790;

  private ImageIcon icon;
  private ImagePanel background;
  private JTabbedPane tabbedPane;
  private InventoryCardsTab firstTab;
  private InventoryItemsTab secondTab;

  //Constructor
  public Inventory()
  {
    ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null); //Erasing the frame top bar
    setTitle("Inventory");
    setSize(INVENTORY_LENGTH,INVENTORY_HEIGHT);
    setResizable(false);
    setVisible(false);
    toFront();
    setLocation(0,260);
    setOpaque(false);

    this.firstTab = new InventoryCardsTab();
    this.secondTab = new InventoryItemsTab();

    this.tabbedPane = new JTabbedPane();
    this.tabbedPane.addTab("Cards", new ImageIcon(), firstTab);
    this.tabbedPane.addTab("Items", new ImageIcon(), secondTab);

    this.add(this.tabbedPane);
  }

  //Methods
  public InventoryItemsTab getInventoryItemsTab(){return this.secondTab;}
  public InventoryCardsTab getInventoryCardsTab(){return this.firstTab;}
}
