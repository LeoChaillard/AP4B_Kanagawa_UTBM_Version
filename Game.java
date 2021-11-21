import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Color;


public class Game {
  //Attributes
  private Window window;

  //Constructor
  public Game()
  {
    this.window = new Window();
  }

  //Methods
  public void run()
  {
    initialize();
  }

  /***************************************************/

  public void initialize()
  {
    this.window.initWindow();
    this.window.draw();
  }


}
