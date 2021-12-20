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
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import java.util.*;


public class Inventory extends JInternalFrame{
  //Attributes
  private static final int INVENTORY_HEIGHT = 503;
  private static final int INVENTORY_LENGTH = 790;
  private static final int X_ELEMENTS = 8;
  private static final int Y_ELEMENTS = 4;
  private static final float ELEMENT_SIZE = 0.96f;
  private static final float CARD_SIZE = 0.90f;
  private static final int LINE_ELEMENTS = X_ELEMENTS - 2;

  private ImageIcon icon;
  private ImagePanel background;
  private JTabbedPane tabbedPane;
  private InventoryCardsTab firstTab;
  private InventoryItemsTab secondTab;

  //Constructor
  public Inventory()
  {
    ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
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

}
