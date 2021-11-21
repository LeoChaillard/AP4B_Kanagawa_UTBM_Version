import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Window extends JFrame{
  //Attributes
  private Board boardPanel;

  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_LENGTH = 1000;

  //Constructor
  public Window()
  {
    this.boardPanel = new Board();
  }

  public void initWindow()
  {
    this.setTitle("Kanagawa");
    this.setBounds(10,10,WINDOW_LENGTH,WINDOW_HEIGHT);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.getContentPane().add(boardPanel,BorderLayout.CENTER);
  }

  /***************************************************/

  public void draw () {
    EventQueue.invokeLater(() -> {
    setVisible(true);
    });
  }
}
