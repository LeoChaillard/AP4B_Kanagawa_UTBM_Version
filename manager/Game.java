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

import java.awt.dnd.DropTargetListener;

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
  public static List<Mention> mentions;
  private Set<Integer> availableMentions;
  public static int numberOfPlayers;
  public static int firstPlayer;
  public static int lastPlayer;
  public static int passedTurns;
  public static int playerIndex;
  private boolean isPickingUpColumn;
  public static boolean newTurn;
  public static int pickedCards;
  private boolean endOfGame;

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
    this.mentions = new ArrayList<Mention>(19);
    this.endOfGame = false;
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
    initializeMentions();
  }

  /***************************************************/

  public void initializeMentions()
  {
    this.availableMentions = new HashSet<Integer>(19);
    for(int i=0;i<19;++i) this.availableMentions.add(i);
    int i = 0;
    //Certificates
    MentionCertificates certificates = new MentionCertificates(i, 3, 3, Bonus.NULL, "Certificates mention");
    mentions.add(certificates);
    certificates = new MentionCertificates(++i, 4, 4, Bonus.IMSI_TOKEN, "Certificates mention");
    mentions.add(certificates);
    certificates = new MentionCertificates(++i, 9, 5, Bonus.NULL, "Certificates mention");
    mentions.add(certificates);

    //Associations
    List<Associations> associationsList = new ArrayList<Associations>(3);
    associationsList.add(Associations.AE);
    associationsList.add(Associations.BDS);
    MentionAssociations association = new MentionAssociations(++i, 3, 2, Bonus.EARN_HOURS, associationsList, "Associations mention");
    mentions.add(association);

    associationsList.clear();
    associationsList.add(Associations.BDF);
    associationsList.add(Associations.CLUBS);
    association = new MentionAssociations(++i, 4, 2, Bonus.EARN_HOURS, associationsList, "Associations mention");
    mentions.add(association);

    associationsList.clear();
    associationsList.add(Associations.CLUBS);
    associationsList.add(Associations.AE);
    associationsList.add(Associations.BDS);
    association = new MentionAssociations(++i, 9, 3, Bonus.NULL, associationsList, "Associations mention");
    mentions.add(association);

    //Teachers
    MentionTeachers teachers = new MentionTeachers(++i, 3, 2, Bonus.NULL, false, "Teachers mention");
    mentions.add(teachers);
    teachers = new MentionTeachers(++i, 4, 3, Bonus.IMSI_TOKEN, false, "Teachers mention");
    mentions.add(teachers);
    teachers = new MentionTeachers(++i, 9, 3, Bonus.NULL, true, "Teachers mention");
    mentions.add(teachers);

    //Arrow
    MentionArrow arrows = new MentionArrow(++i, 1, 2, Bonus.EARN_HOURS, "Arrow mention");
    mentions.add(arrows);
    arrows = new MentionArrow(++i, 3, 3, Bonus.EARN_HOURS, "Arrow mention");
    mentions.add(arrows);

    //Hours
    MentionHours hours = new MentionHours(++i, 2, 3, Bonus.NULL, "Hours mention");
    mentions.add(hours);
    hours = new MentionHours(++i, 4, 4, Bonus.NULL, "Hours mention");
    mentions.add(hours);

    //Materials
    MentionMaterials materials = new MentionMaterials(++i, 3, 2, Bonus.NULL, "Materials mention");
    mentions.add(materials);
    materials = new MentionMaterials(++i, 4, 3, Bonus.IMSI_TOKEN, "Materials mention");
    mentions.add(materials);
    materials = new MentionMaterials(++i, 9, 4, Bonus.NULL, "Materials mention");
    mentions.add(materials);

    //Skills
    MentionSkills skills = new MentionSkills(++i, 2, 2, Bonus.NULL, "Skills mention");
    mentions.add(skills);
    skills = new MentionSkills(++i, 3, 3, Bonus.EARN_HOURS, "Skills mention");
    mentions.add(skills);
    skills = new MentionSkills(++i, 7, 4, Bonus.NULL, "Skills mention");
    mentions.add(skills);
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

    this.window.getInventory().getInventoryCardsTab().addMouseListener(new InventoryCardsTabTokensListener(this));
    //this.window.getInventory().getInventoryItemsTab().addMouseListener(new InventoryItemsTabMouseListener(this));
    //this.window.getInventory().getInventoryItemsTab().addMouseMotionListener(new InventoryItemsTabMouseListener(this));

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

    this.window.getRightPanel().getArrowButton().addMouseListener(new PointingCursorListener(this.window));
    this.window.getRightPanel().getArrowButton().addMouseListener(new RightPanelArrowMouseListener(this));

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
    this.window.getWinnerPane().setVisible(false);
    this.window.enableInventory();
    this.menu.getResume().setEnabled(true);
    this.pickedUpCards.clear();
    this.starterCards.clear();
    this.numberOfPlayers = 0;
    this.passedTurns = 0;
    this.firstPlayer = 0;
    this.isPickingUpColumn = false;
    this.newTurn = true;
    this.availableMentions = null;
    this.setEndOfGame(false);
    this.initializeMentions();
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

  public void setEndOfGame(boolean end)
  {
    this.endOfGame = end;
  }

  public void nextTurn()
  {
    int winner = 0;

    this.checkMention();
    this.players.get(this.playerIndex).resetHours();
    this.players.get(this.playerIndex).resetKeepInHand();
    this.players.get(this.playerIndex).resetHoursUsedInTurn();
    this.players.get(this.playerIndex).resetBlockedImages();
    this.players.get(this.playerIndex).resetHoursOnCategories();
    this.window.getInventory().getInventoryItemsTab().getFinishedMovingButton().setEnabled(true);
    this.window.getInventory().getInventoryItemsTab().setFinishedMovingHours(false);

    setCurrentPlayer((playerIndex+1)%numberOfPlayers); /*skipp players that picked up*/
    window.getRightPanel().updateInfos(players.get(playerIndex).getName());

    this.window.getInventory().getInventoryCardsTab().repaint();
    this.window.getInventory().getInventoryItemsTab().repaint();

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
      winner = countPlayersPoints();

      //Displaying the winner
      System.out.println("winner is : " + this.players.get(winner).getName());
      Color color = Colors.getBoardColors()[0];
      String hex = "#"+Integer.toHexString(color.getRGB()).substring(2);
      this.window.getWinnerPane().setWinner("<html><font color='" + hex + "'>" + this.players.get(winner).getName() + " has won !</font></html>");
      this.window.getWinnerPane().displayLadder();
      this.window.getWinnerPane().setVisible(true);
      this.window.disableInventory();
      this.setEndOfGame(true);
    }
  }

  /***************************************************/

  public void checkMention()
  {
    for(int mention=0;mention<19;++mention)
    {
      if(this.availableMentions.contains(mention))
      {
        if(!this.players.get(this.playerIndex).getDeniedMentions().contains(mention) && mentions.get(mention).checkCriteria(this.players.get(this.playerIndex)))
          this.offerMention(mentions.get(mention));
      }
    }
  }

  /***************************************************/

  public void offerMention(Mention mention)
  {
    String [] options = {"accept", "deny"};
    int x = JOptionPane.showOptionDialog(null, mention.getName() + ". Points to earn: " + mention.getPoints() + ". Bonus to earn: " + mention.getBonus(), "Mention offer : accept or deny", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    if(x == 0) //Accept
    {
      if(mention.getBonus() != Bonus.NULL)
      {
        this.players.get(this.playerIndex).getAvailableBonus().replace(mention.getBonus(), this.players.get(this.playerIndex).getAvailableBonus().get(mention.getBonus()) + 1);
      }
      this.players.get(this.playerIndex).getAcceptedMentions().add(mention.getId());
      this.availableMentions.remove(mention.getId());

      if(mention.getId() <= 2)
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(0);
        this.players.get(this.playerIndex).getDeniedMentions().add(1);
        this.players.get(this.playerIndex).getDeniedMentions().add(2);
      }
      if(3 <= mention.getId() && mention.getId() <= 5)
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(3);
        this.players.get(this.playerIndex).getDeniedMentions().add(4);
        this.players.get(this.playerIndex).getDeniedMentions().add(5);
      }
      if(6 <= mention.getId() && mention.getId() <= 8)
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(6);
        this.players.get(this.playerIndex).getDeniedMentions().add(7);
        this.players.get(this.playerIndex).getDeniedMentions().add(8);
      }
      if(9 <= mention.getId() && mention.getId() <= 10)
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(9);
        this.players.get(this.playerIndex).getDeniedMentions().add(10);
      }
      if(11 <= mention.getId() && mention.getId() <= 12)
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(11);
        this.players.get(this.playerIndex).getDeniedMentions().add(12);
      }
      if(13 <= mention.getId() && mention.getId() <= 15)
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(13);
        this.players.get(this.playerIndex).getDeniedMentions().add(14);
        this.players.get(this.playerIndex).getDeniedMentions().add(15);
      }
      if(16 <= mention.getId())
      {
        this.players.get(this.playerIndex).getDeniedMentions().add(16);
        this.players.get(this.playerIndex).getDeniedMentions().add(17);
        this.players.get(this.playerIndex).getDeniedMentions().add(18);
      }

    }
    if(x == 1) //Deny
    {
      this.players.get(this.playerIndex).getDeniedMentions().add(mention.getId());
    }

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
    int max = 0;
    int temp = 0;
    int toReturn = 0;

    for(int i=0; i<this.numberOfPlayers; ++i)
    {
      temp = players.get(i).countPoints();
      System.out.println("" + (i+1) + " : " + temp);
      if (temp>max)
      {
        max = temp;
        toReturn = i;
      }
    }

    return toReturn;
  }

  /***************************************************/

  public boolean isPickingUpColumn(){return this.isPickingUpColumn;}
  public Window getWindow(){return this.window;}
  public int getPlayerIndex(){return this.playerIndex;}
  public void setPickingUpColumn(boolean col){this.isPickingUpColumn = col;}
  public int getNumberPlayers(){return this.numberOfPlayers;}
  public boolean isEndOfGame(){return this.endOfGame;}

}
