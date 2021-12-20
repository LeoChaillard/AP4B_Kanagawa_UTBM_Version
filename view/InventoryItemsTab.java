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


public class InventoryItemsTab extends JPanel{
  //Attributes

  //Constructor
  public InventoryItemsTab()
  {

  }

  //Methods
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    this.setBackground(Color.WHITE);

    g.drawOval(200,200,200,200);

    g.setColor(Color.YELLOW);
    g.fillOval(200,200,200,200);

    g.setColor(Color.BLACK);
    g.fillOval(260,250,10,10);

    g.fillOval(330,250,10,10);

  }
}
