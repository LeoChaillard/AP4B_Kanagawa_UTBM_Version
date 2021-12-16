/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package manager;
import model.*;
import view.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Cursor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Game implements ActionListener, MouseListener, MouseMotionListener{
  //Attributes
  private static final int MAX_PLAYERS = 4;
  private static final int TOTAL_NUMBER_CARDS = 72;
  private static final int X_ELEMENTS = 4;
  private static final int Y_ELEMENTS = 3;


  public static List<Player> players = new ArrayList<Player>(MAX_PLAYERS);
  private static Set pickedUpCards;
  private Set starterCards;
  private Window window;
  private Menu menu;
  private Random nominatePlayer;
  private Mention [] mentions;
  private int [] availableMentions;
  private int numberOfPlayers;
  private int playerIndex;
  private boolean isPickingUpColumn;

  private enum Actions
  {
    MENU,
    NEWGAME,
    RESUME
  }

  //Constructor
  public Game()
  {
    this.pickedUpCards = new HashSet();
    this.starterCards = new HashSet();
    this.window = new Window();
    this.menu = new Menu();
    this.nominatePlayer = new Random();
    this.isPickingUpColumn = false;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    if(this.isPickingUpColumn)
    {
      //System.out.println("x : " + x + " y : " + y);
      //System.out.println("columnWidth : " + Board.columnWidth + " columnHeight : " + Board.columnHeight);

      Player player = this.players.get(playerIndex);

      for(int i=0;i<this.numberOfPlayers;++i)
      {
          int x = evt.getX();
          int y = evt.getY();
          //Checking if the player clicked on one of the columns
          if( (Board.columnsXCoordinate[i] - Board.columnWidth/2 <=x) && (Board.columnsXCoordinate[i] + Board.columnWidth/2 >=x) && (Board.columnsYCoordinate[i] - Board.columnHeight <=y) && (Board.columnsYCoordinate[i] + Board.columnHeight >=y) )
          {
            //Add chosen card to player's hand
            System.out.println("adding cards to temporary hand");
            Card [][] boardSlots = window.getBoard().getSlots();
            for(int j=0;j<Y_ELEMENTS;++j) if(boardSlots[i][j] != null) player.addCardTemporaryHand(boardSlots[i][j]);
            this.window.getBoard().removeColumn(i+1);

            //Updating window
            this.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.window.getBoard().resetHighlight();
            this.window.repaint();
          }
      }
    }
  }

  /***************************************************/

  @Override
  public void mouseExited(MouseEvent evt)
  {
    this.window.getBoard().resetHighlight();
    this.window.repaint();
  }

  /***************************************************/

  @Override
  public void mouseMoved(MouseEvent e)
  {
    if(this.isPickingUpColumn)
    {
      for(int i=0;i<this.numberOfPlayers;++i)
      {
        int x = e.getX();
        int y = e.getY();

          //Highlighting a column when the mouse goes over it
          if( (Board.columnsXCoordinate[i] - Board.columnWidth/2 <=x) && (Board.columnsXCoordinate[i] + Board.columnWidth/2 >=x) && (Board.columnsYCoordinate[i] - Board.columnHeight <=y) && (Board.columnsYCoordinate[i] + Board.columnHeight >=y) )
          {
            this.window.setCursor(new Cursor(Cursor.HAND_CURSOR));
            this.window.getBoard().setHighlight(i, true);
            this.window.repaint();
          }
          else if(this.window.getBoard().isHighlighted(i)) //Going back to the default state
          {
            this.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            this.window.getBoard().setHighlight(i, false);
            this.window.repaint();
          }
      }
    }
  }

  /***************************************************/

  @Override
  public void mouseDragged(MouseEvent e) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

  /***************************************************/

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    //Menu Actions
    if (evt.getActionCommand() == Actions.NEWGAME.name())
    {
        this.window.setVisible(true);
        this.menu.setVisible(false);
        this.menu.pauseMusic();
        resetGame();

        createPlayers();
        distributeStarterCards();

        this.window.getBoard().addRow(this.numberOfPlayers);
        this.window.getBoard().addRow(this.numberOfPlayers);
        this.window.getRightPanel().updateInfos(this.players.get(this.playerIndex).getName());

        this.window.repaint();
    }

    if (evt.getActionCommand() == Actions.RESUME.name())
    {
        this.window.setVisible(true);
        this.menu.setVisible(false);
        this.menu.pauseMusic();
    }

    if (evt.getActionCommand() == Actions.MENU.name())
    {
        this.window.setVisible(false);
        this.menu.setVisible(true);
        this.menu.resumeMusic();
    }
  }

  /***************************************************/

  public void distributeStarterCards()
  {
    for(int i=0; i<this.numberOfPlayers;++i)
    {
      try{
        Card starterCard = cardFromStarters();
        this.players.get(i).addCardProject(starterCard);
      } catch(Exception e) {}
    }
  }

  /***************************************************/

  public Card cardFromStarters() throws IOException
  {
    //Getting random id card
    Random randId = new Random();
    int id = randId.nextInt(MAX_PLAYERS+1); //Not taking 0 into account
    while(id == 0  || this.starterCards.contains(id)) id = randId.nextInt(MAX_PLAYERS+1); //Not taking 0 into account
    this.starterCards.add(id);
    //System.out.println(id);

    //Looking into the database for the card attributes
    String[] data = Files.readAllLines(Paths.get("dataBase/starter_cards.txt")).get(id).split("\t");
    String[] d = data[2].split("\\*"); //d[0] : nb elements, d[1] : attribute

    /*for(int i=0;i<4;++i)
        System.out.println(i + ": " + data[i]);

    System.out.println(d[0] + " " + d[1] + "\n");*/

    /* CardType(cardId, skillsCategory, firstBonus, secondBonus, secondBonusQuantity, branch) */
    return new CardStarter(id, Category.valueOf(data[0]), Bonus.valueOf(data[1]), Bonus.valueOf(d[1]), Integer.parseInt(d[0]), Branch.valueOf(data[3]));
  }

  /***************************************************/

  public static Card cardFromDeck() throws IOException
  {
    //Getting random id card
    Random randId = new Random();
    int id = randId.nextInt(TOTAL_NUMBER_CARDS+1); //Not taking 0 into account
    while(id == 0  || pickedUpCards.contains(id)) id = randId.nextInt(TOTAL_NUMBER_CARDS+1); //Not taking 0 into account
    pickedUpCards.add(id);
    //System.out.println(id);

    //Looking into the database for the card attributes
    Card deckCard = null;
    String[] data = Files.readAllLines(Paths.get("dataBase/deck.txt")).get(id).split("\t");
    String[] d = data[6].split("\\*"); //d[0] : nb elements, d[1] : attribute

    /*for(int i=0;i<8;++i)
        System.out.println(i + ": " + data[i]);

    System.out.println(d[0] + " " + d[1]);*/

    //Creating new cards from these attributes

    /* CardType(cardId, skillsPoints, projectPoints, bonus, branch, skillsCategory, projectCategoriesQuantity , projectCategory, cardTheme ) */
    if(data[0].equals("Association")) deckCard = new CardAssociations(id,Integer.parseInt(data[3]), Integer.parseInt(data[4]), Bonus.valueOf(data[2]), Branch.valueOf(data[5]), Category.valueOf(data[1]), Integer.parseInt(d[0]), Category.valueOf(d[1]), Associations.valueOf(data[7]) );
    else if(data[0].equals("Materials")) deckCard = new CardMaterials(id, Integer.parseInt(data[3]), Integer.parseInt(data[4]), Bonus.valueOf(data[2]), Branch.valueOf(data[5]), Category.valueOf(data[1]), Integer.parseInt(d[0]), Category.valueOf(d[1]), Materials.valueOf(data[7]) );
    else if(data[0].equals("Teachers")) deckCard = new CardTeachers(id, Integer.parseInt(data[3]), Integer.parseInt(data[4]), Bonus.valueOf(data[2]), Branch.valueOf(data[5]), Category.valueOf(data[1]), Integer.parseInt(d[0]), Category.valueOf(d[1]), Teachers.valueOf(data[7]) );
    else if(data[0].equals("Certificates")) deckCard = new CardCertificates(id, Integer.parseInt(data[3]), Integer.parseInt(data[4]), Bonus.valueOf(data[2]), Branch.valueOf(data[5]), Category.valueOf(data[1]), Integer.parseInt(d[0]), Category.valueOf(d[1]), Integer.parseInt(data[7]) );

    return deckCard;
  }

  /***************************************************/

  private boolean isNumeric(String str)
  {
    return str.matches("[0-9.]+");
  }

  /***************************************************/

  private boolean isEmpty(String str)
  {
    return str == null || str.equals("");
  }

  /***************************************************/

  private void createPlayers()
  {
    //Asking for number of players
    while(this.numberOfPlayers < 2 || this.numberOfPlayers > 4)
    {
      String tempNumberPlayers = JOptionPane.showInputDialog( "Number of players (2-4) :" );
      if(!isEmpty(tempNumberPlayers) && isNumeric(tempNumberPlayers)) setNumberPlayers(Integer.parseInt(tempNumberPlayers));
    }
    setCurrentPlayer(nominatePlayer.nextInt(this.numberOfPlayers));

    //Creating players with default names
    for(int i = 0;i<this.numberOfPlayers;++i) this.players.add(new Player("Player " + (i+1)));

    //Asking for the player's name
    for(int i = 0;i<this.numberOfPlayers;++i)
    {
      String name = JOptionPane.showInputDialog( "Player " + (i+1) + " name:" );
      if(!isEmpty(name)) this.players.get(i).setName(name);
    }
  }

  /***************************************************/

  public void start()
  {
    initialize();
  }

  /***************************************************/

  public void initialize()
  {
    //Initializing windows
    this.window.initWindow();
    this.menu.initMenu();

    //Mouse listeners
    this.window.getBoard().addMouseListener(this);
    this.window.getBoard().addMouseMotionListener(this);

    //Adding listeners for menu interactions
    this.menu.getNewGame().addActionListener(this);
    this.menu.getNewGame().addMouseListener(new PointingCursorListener(this.menu));
    this.menu.getResume().addActionListener(this);
    this.menu.getResume().addMouseListener(new PointingCursorListener(this.menu));
    this.window.getMenuButton().addMouseListener(new PointingCursorListener(this.window));
    this.window.getMenuButton().addActionListener(this);

    //Setting action commands for menu buttons
    this.menu.getNewGame().setActionCommand(Actions.NEWGAME.name());
    this.menu.getResume().setActionCommand(Actions.RESUME.name());
    this.window.getMenuButton().setActionCommand(Actions.MENU.name());

    //Adding action listeners for player choices
    this.window.getRightPanel().getPassLabel().addMouseListener(new PointingCursorListener(this.window));
    this.window.getRightPanel().getPassLabel().addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent evt)
      {
        if(!isPickingUpColumn && players.get(playerIndex).getTemporaryHand().isEmpty())
        {
          nextTurn();
        }
        else
        {
          JOptionPane msg = new JOptionPane();
          msg.showMessageDialog( window, "Can't pass while picking up a column!", "Can't do that", JOptionPane.WARNING_MESSAGE);
        }
      }
    });

    this.window.getRightPanel().getPickColumnLabel().addMouseListener(new PointingCursorListener(this.window));
    this.window.getRightPanel().getPickColumnLabel().addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent evt)
      {
        if(!isPickingUpColumn)
        {
          JOptionPane msg = new JOptionPane();
          msg.showMessageDialog( window, "Please click on one available column that you wish", "Pick one column!", JOptionPane.INFORMATION_MESSAGE);
          isPickingUpColumn = true;
        }
        else
        {
          JOptionPane msg = new JOptionPane();
          msg.showMessageDialog( window, "Can't pick another column during same turn!", "Can't do that", JOptionPane.WARNING_MESSAGE);
        }
      }
    });


    this.menu.getResume().setEnabled(false);
    this.menu.draw();
  }

  /***************************************************/

  public void resetGame()
  {
    for(int i=0;i<this.numberOfPlayers;++i) this.players.get(i).reset();
    this.window.getBoard().removeAll();
    this.menu.getResume().setEnabled(true);
    this.pickedUpCards.clear();
    this.starterCards.clear();
    this.numberOfPlayers = 0;
    this.isPickingUpColumn = false;
  }


  /***************************************************/

  public boolean checkSkills(Category c)
  {
    return true;
  }

  /***************************************************/

  public boolean checkHoursAvailability(Category c)
  {
    return true;
  }

  /***************************************************/

  public void checkMention()
  {

  }

  /***************************************************/

  public void setNumberPlayers(int players)
  {
    this.numberOfPlayers = players;
  }


  /***************************************************/

  public void setCurrentPlayer(int index)
  {
    this.playerIndex = index;
  }

  /***************************************************/

  public void nextTurn()
  {
    setCurrentPlayer((playerIndex+1)%numberOfPlayers);
    window.getRightPanel().updateInfos(players.get(playerIndex).getName());
  }

  /***************************************************/

  public boolean checkGameProgress()
  {
    return true;
  }

  /***************************************************/

  public int countPlayersPoints()
  {
    return 0;
  }

  /***************************************************/

  public void offerMention()
  {

  }
}
