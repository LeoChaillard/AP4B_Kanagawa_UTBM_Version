/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import listeners.PointingCursorListener;

import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Window extends JFrame{
  //Attributes
  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_LENGTH = 1000;
  private static final int RIGHT_SIDE = WINDOW_LENGTH - WINDOW_HEIGHT;

  private Board boardPanel;
  private RightPanel rightPanel;
  private TreatCardsPane treatCardsPanel;
  private JLayeredPane layeredPane;
  private JButton menuButton;
  private JButton inventoryButton;
  private Inventory inventory;

  //Constructor
  public Window()
  {
    this.boardPanel = new Board();
    this.rightPanel = new RightPanel(RIGHT_SIDE);
    this.treatCardsPanel = new TreatCardsPane();
    this.layeredPane = new JLayeredPane();
    this.inventory = new Inventory();
  }

  public void initWindow()
  {
    //Setting windows' parameters
    this.setTitle("Kanagawa");
    this.setBounds(10,10,WINDOW_LENGTH,WINDOW_HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    //Menu Button
    ImageIcon gear = new ImageIcon("view/menu_gear.png");
    this.menuButton = new JButton(gear);
    this.menuButton.setBorder(BorderFactory.createEmptyBorder());
    this.menuButton.setBorderPainted(false);
    this.menuButton.setOpaque(false);
    this.menuButton.setContentAreaFilled(false);
    this.menuButton.setBounds(940, 720, 40, 40);

    //Inventory button
    this.inventoryButton = new JButton("Inventory (E)");
    this.inventoryButton.setBorder(BorderFactory.createEmptyBorder());
    this.inventoryButton.setBorderPainted(false);
    this.inventoryButton.setOpaque(false);
    this.inventoryButton.setContentAreaFilled(false);
    this.inventoryButton.setBounds(790, 720, 90, 40);

    //Mapping 'E' key to Inventory opening/closing
    this.inventoryButton.getInputMap(this.inventoryButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "Inventory");
    this.inventoryButton.getActionMap().put("Inventory", new AbstractAction(){
      @Override
      public void actionPerformed(ActionEvent evt)
      {
        openCloseInventory();
      }
    });
    //Action listener for clicking the inventoryButton
    this.inventoryButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent evt)
      {
        openCloseInventory();
      }
    });
    this.inventoryButton.addMouseListener(new PointingCursorListener(this));

    //Adding elements to frame
    this.getContentPane().add(this.menuButton);
    this.getContentPane().add(this.inventoryButton);
    this.getContentPane().add(this.rightPanel,BorderLayout.EAST);
    this.getContentPane().add(this.layeredPane, BorderLayout.CENTER);

    this.layeredPane.setPreferredSize(new Dimension(WINDOW_HEIGHT,WINDOW_LENGTH-RIGHT_SIDE));

    this.layeredPane.add(inventory, 2);

    this.boardPanel.setSize(this.layeredPane.getPreferredSize());
    this.boardPanel.setLocation(0,0);
    this.layeredPane.add(this.boardPanel, 1);

    this.treatCardsPanel.setSize(this.layeredPane.getPreferredSize());
    this.treatCardsPanel.setLocation(0,0);
    this.layeredPane.add(this.treatCardsPanel, 0);
  }

  /***************************************************/

  public void openCloseInventory()
  {
    if(!this.inventory.isVisible()) this.inventory.setVisible(true);
    else this.inventory.setVisible(false);
  }

  /***************************************************/

  public TreatCardsPane getTreatCardsPane(){return this.treatCardsPanel;}

  /***************************************************/

  public Board getBoard(){return this.boardPanel;}

  /***************************************************/

  public JButton getMenuButton(){return this.menuButton;}

  /***************************************************/

  public RightPanel getRightPanel(){return this.rightPanel;}

  /***************************************************/

  public Inventory getInventory(){return this.inventory;}

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
