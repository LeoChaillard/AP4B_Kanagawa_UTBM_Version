/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Window extends JFrame{
  //Attributes
  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_LENGTH = 1000;
  private static final int RIGHT_SIDE = WINDOW_LENGTH - WINDOW_HEIGHT;

  private Board boardPanel;
  private RightPanel rightPanel;
  private JButton menuButton;

  //Constructor
  public Window()
  {
    this.boardPanel = new Board();
    this.rightPanel = new RightPanel(RIGHT_SIDE);
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

    //Adding elements to frame
    this.getContentPane().add(this.menuButton);
    this.getContentPane().add(this.rightPanel,BorderLayout.EAST);
    this.getContentPane().add(this.boardPanel,BorderLayout.CENTER);
  }

  /***************************************************/

  public Board getBoard(){return this.boardPanel;}

  /***************************************************/

  public JButton getMenuButton(){return this.menuButton;}

  /***************************************************/

  public RightPanel getRightPanel(){return this.rightPanel;}

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
