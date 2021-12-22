/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package manager;
import listeners.*;
import model.*;
import view.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Game implements ActionListener{
  //Attributes
  private static final int MAX_PLAYERS = 4;
  public static final int TOTAL_NUMBER_CARDS = 72;
  private static final int CARDS_FOR_WIN = 2; //Should be 11
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
  public static int numberOfPlayers;
  public static int firstPlayer;
  public static int lastPlayer;
  public static int passedTurns;
  public static int playerIndex;
  private boolean isPickingUpColumn;
  public static boolean newTurn;
  public static int pickedCards;

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
    this.newTurn = true;
    this.pickedCards = 0;
  }

  //Methods
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
        CardStarter starterCard = cardFromStarters();
        this.players.get(i).addStarterCardToProject(starterCard);
      } catch(Exception e) {}
    }
  }

  /***************************************************/

  public CardStarter cardFromStarters() throws IOException
  {
    //Getting random id card
    Random randId = new Random();
    int id = randId.nextInt(MAX_PLAYERS+1); //Not taking 0 into account
    while(id == 0  || this.starterCards.contains(id)) id = randId.nextInt(MAX_PLAYERS+1); //Not taking 0 into account
    this.starterCards.add(id);

    //Looking into the database for the card attributes
    String[] data = Files.readAllLines(Paths.get("dataBase/starter_cards.txt")).get(id).split("\t");
    String[] d = data[2].split("\\*"); //d[0] : nb elements, d[1] : attribute

    //Creating a card from these attributes
    /* CardType(cardId, skillsCategory, firstBonus, secondBonus, secondBonusQuantity, branch) */
    return new CardStarter(id, Category.valueOf(data[0]), Bonus.valueOf(data[1]), Bonus.valueOf(d[1]), Integer.parseInt(d[0]), Branch.valueOf(data[3]));
  }

  /***************************************************/

  public static Card cardFromDeck() throws IOException
  {
    ++pickedCards;
    //Getting random id card
    Random randId = new Random();
    int id = randId.nextInt(TOTAL_NUMBER_CARDS+1); //Not taking 0 into account
    while(id == 0  || pickedUpCards.contains(id)) id = randId.nextInt(TOTAL_NUMBER_CARDS+1); //Not taking 0 into account
    pickedUpCards.add(id);

    //Looking into the database for the card attributes
    Card deckCard = null;
    String[] data = Files.readAllLines(Paths.get("dataBase/deck.txt")).get(id).split("\t");
    String[] d = data[6].split("\\*"); //d[0] : nb elements, d[1] : attribute

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
    setFirstPlayer(this.playerIndex);
    setLastPlayer( (this.firstPlayer + this.numberOfPlayers - 1)%this.numberOfPlayers);

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
    this.window.getBoard().addMouseListener(new BoardMouseListener(this));
    this.window.getBoard().addMouseMotionListener(new BoardMotionListener(this));

    this.window.getRightPanel().addMouseListener(new RightPanelMouseListener(this));
    this.window.getRightPanel().addMouseMotionListener(new RightPanelMotionListener(this));

    this.window.getTreatCardsPane().getWorkProjectLabel().addMouseListener(new TreatCardsPanelProjectMouseListener(this));
    this.window.getTreatCardsPane().getWorkProjectLabel().addMouseListener(new PointingCursorListener(this.window));

    this.window.getTreatCardsPane().getSkillsLabel().addMouseListener(new TreatCardsPanelSkillsMouseListener(this));
    this.window.getTreatCardsPane().getSkillsLabel().addMouseListener(new PointingCursorListener(this.window));

    this.window.getTreatCardsPane().getKeepCardLabel().addMouseListener(new TreatCardsPanelKeepMouseListener(this));
    this.window.getTreatCardsPane().getKeepCardLabel().addMouseListener(new PointingCursorListener(this.window));

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
    this.window.getRightPanel().getPassLabel().addMouseListener(new RightPanelPassMouseListener(this));

    this.window.getRightPanel().getPickColumnLabel().addMouseListener(new PointingCursorListener(this.window));
    this.window.getRightPanel().getPickColumnLabel().addMouseListener(new RightPanelPickMouseListener(this));

    this.menu.getResume().setEnabled(false);
    this.menu.draw();
  }


  /***************************************************/

  public void resetGame()
  {
    //for(int i=0;i<this.numberOfPlayers;++i) this.players.get(i).reset();
    this.players = null;
    this.players = new ArrayList<Player>(4);
    
    this.window.getBoard().removeAll();
    this.window.getTreatCardsPane().setVisible(false);
    this.menu.getResume().setEnabled(true);
    this.pickedUpCards.clear();
    this.starterCards.clear();
    this.numberOfPlayers = 0;
    this.passedTurns = 0;
    this.firstPlayer = 0;
    this.isPickingUpColumn = false;
    this.newTurn = true;
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

  public void setFirstPlayer(int index)
  {
    this.firstPlayer = index;
  }

  /***************************************************/

  public void setLastPlayer(int index)
  {
    this.lastPlayer = index;
  }

  /***************************************************/

  public void passTurn()
  {
    ++this.passedTurns;
  }

  /***************************************************/

  public void nextTurn()
  {
    this.players.get(this.playerIndex).resetHours();

    setCurrentPlayer((playerIndex+1)%numberOfPlayers);
    window.getRightPanel().updateInfos(players.get(playerIndex).getName());

    //New turn mid game
    if(this.passedTurns > 0 && this.playerIndex == this.firstPlayer)
    {
      System.out.println("new turn");
      this.passedTurns = 0;
      this.window.getBoard().addRow(this.numberOfPlayers);
      this.window.repaint();
    }

    //New "big" turn
    if(this.checkGameProgress() && this.window.getBoard().areAllColumnsEmpty())
    {
      System.out.println("new big turn");
      this.window.getBoard().removeAll();
      this.passedTurns = 0;
      this.newTurn = true;
      this.window.getBoard().addRow(this.numberOfPlayers);
      this.newTurn = false;
      this.window.repaint();
    }

    if(!checkGameProgress() && this.window.getBoard().areAllColumnsEmpty())
    {
      System.out.println("stopping game");
      countPlayersPoints();

    }
  }

  /***************************************************/

  public void checkMention()
  {

  }

  /***************************************************/

  public boolean checkGameProgress()
  {
    if(this.pickedCards >= TOTAL_NUMBER_CARDS) return false;

    for(int i=0;i<this.numberOfPlayers;++i)
    {
      if(this.players.get(i).getProject().size() >= CARDS_FOR_WIN) return false;
    }

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

  /***************************************************/

  public boolean isPickingUpColumn(){return this.isPickingUpColumn;}
  public Window getWindow(){return this.window;}
  public int getPlayerIndex(){return this.playerIndex;}
  public void setPickingUpColumn(boolean col){this.isPickingUpColumn = col;}
  public int getNumberPlayers(){return this.numberOfPlayers;}
}
