package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.ExpertAIPlayer;
import model.HumanPlayer;
import model.IntermediateAIPlayer;
import model.ObserverMessages;
import model.Player;
import model.SimpleAIPlayer;
import model.Territory;
import songplayer.SongPlayer;
import controller.RiskController;
import explosion.Explosion;

@SuppressWarnings("serial")
/**
 * Graphical user interface for the game of Risk. Interacts with RiskController to display game events. 
 * Consists visually of a MapPanel, a SidePanel, and a label panel. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 *
 */
public class RiskGUI extends JFrame implements Observer{
	private String baseDir = System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "sound" + System.getProperty("file.separator");
	private RiskController controller;
	private JFrame frame;
	private JPanel labelPanel;
	private MapPanel mapPanel;
	private SidePanel sidePanel;
	private JLabel label;
	private ArrayList<Player> players;
	private Player currentPlayer;
	private HumanPlayer human;
	private TypeOfPlay typeOfPlay;

	/**
	 * Constructs a new RiskGUI object. Calls the JFrame superconstructor, then displays the splash screen
	 * and player setup screen using helper methods. Proceeds to the main screen, calls a helper method
	 * to set up the frame, and plays the game. 
	 */
	public RiskGUI() {
		super();
		splashScreen();
		setUpPlayers();

		currentPlayer = players.get(0);

		typeOfPlay = TypeOfPlay.DO_NOTHING;

		controller = new RiskController();
		controller.addObserver(this);
		controller.setPlayers(players);
		controller.sendTerritoriesToPlayers();


		setUpFrame();
		controller.populateBoard();
		controller.playGame();
	}

	/**
	 * Helper method to display a splash screen. Displays the Risk logo as well as information 
	 * about the game and its developers, and plays the 1812 Overture. 
	 */
	public void splashScreen() {

		ImageIcon logo = new ImageIcon("images/Risk_logo.png");
		SongPlayer.playFile(baseDir + "01_1812_Overture_Op.wav");

		JLabel about = new JLabel();
		about.setText("<html>About This Game"
				+ "<br>Risk is a turn-based strategy game for two to six players. "
				+ "<br>The object of the game is to achieve global conquest. Your goal is to occupy every territory"
				+ "<br>on the board by eliminating other players, capturing their territories with dice rolls."
				+ "<br><br>Developers: RISKy Business (Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Rebecca Simon)."
				+ "<br>University of Arizona, CSc 335, Fall 2014.");
		JOptionPane.showMessageDialog(null, about, "SPLASH!", JOptionPane.OK_CANCEL_OPTION, logo);
	}

	/**
	 * Helper method to set up the main screen. Arranges the map panel, side panel, and label. 
	 */
	public void setUpFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1160, 680);
		frame.setTitle("RISK");

		JPanel basePanel = new JPanel();
		basePanel.setLayout(new BorderLayout());

		JPanel mapAndLabelPanel = new JPanel();
		mapAndLabelPanel.setLayout(new BorderLayout());

		mapPanel = new MapPanel(this);

		mapAndLabelPanel.add(mapPanel, BorderLayout.CENTER);

		sidePanel = new SidePanel(this);
		basePanel.add(sidePanel, BorderLayout.EAST);

		setUpLabelPanel();
		mapAndLabelPanel.add(labelPanel, BorderLayout.SOUTH);

		basePanel.add(mapAndLabelPanel, BorderLayout.CENTER);

		frame.add(basePanel);
		frame.setVisible(true);
	}

	/**
	 * Helper method to set up players and listeners and assign colors to the players. 
	 * Calls a helper method to display the player setup screen. 
	 */
	public void setUpPlayers() {
		players = new ArrayList<Player>();
		selectPlayers();
		while(players.size() > 6 || players.size() < 2) {
			JOptionPane.showMessageDialog(null, "You must select between 2 and 6 players", "Error!",
					JOptionPane.ERROR_MESSAGE);
			players = new ArrayList<Player>();
			selectPlayers();
		}
		switch(players.size()) {
		case 6:
			players.get(5).setColor(new Color(140, 74, 255));//purple
			players.get(5).addObserver(this);
		case 5:
			players.get(4).setColor(Color.YELLOW);
			players.get(4).addObserver(this);
		case 4:
			players.get(3).setColor(Color.MAGENTA);
			players.get(3).addObserver(this);
		case 3:
			players.get(2).setColor(new Color(255, 114, 0));//orange
			players.get(2).addObserver(this);
		case 2:
			players.get(1).setColor(Color.GREEN);
			players.get(1).addObserver(this);
		case 1:
			players.get(0).setColor(Color.CYAN);
			players.get(0).addObserver(this);
		}

	}

	/**
	 * Helper method to display the player setup screen. Prompts the user to select between two
	 * and six players inclusive. The user can customize player names and types. 
	 */
	public void selectPlayers() {
		JLabel instructionLabel = new JLabel();
		instructionLabel.setText("<html>Instructions:"
				+ "<br>Please select between 2-6 players and determine a level of difficulty for each."
				+ "<br>If you do not want a player, select Not Playing."
				+ "<br>Enter a name in the Name field or the default name will be used."
				+ "<br> ---------------------------------------------------------------------");


		//Player 1
		JLabel p1 = new JLabel("Player 1 (Blue):");

		JPanel p1NamePanel = new JPanel();
		p1NamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel p1Name = new JLabel("   Name: ");
		JTextField p1EnterName = new JTextField();
		p1EnterName.setText("Player 1");
		p1EnterName.setSize(200, 20);

		p1NamePanel.add(p1);
		p1NamePanel.add(p1Name);
		p1NamePanel.add(p1EnterName);


		JPanel p1ButtonPanel = new JPanel();
		p1ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JRadioButton p1Human = new JRadioButton("Human");
		JRadioButton p1Simple = new JRadioButton("Simple AI");
		JRadioButton p1Inter = new JRadioButton("Intermediate AI");
		JRadioButton p1Expert = new JRadioButton("Expert AI");

		ButtonGroup p1BG = new ButtonGroup();
		p1BG.add(p1Human);
		p1BG.add(p1Simple);
		p1BG.add(p1Inter);
		p1BG.add(p1Expert);

		JLabel p1ButtonBuffer = new JLabel();
		p1ButtonBuffer.setSize(50, 9);

		p1ButtonPanel.add(p1ButtonBuffer);
		p1ButtonPanel.add(p1Human);
		p1ButtonPanel.add(p1Simple);
		p1ButtonPanel.add(p1Inter);
		p1ButtonPanel.add(p1Expert);


		//Player 2
		JLabel p2 = new JLabel("Player 2 (Green):");


		JPanel p2NamePanel = new JPanel();
		p2NamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel p2Name = new JLabel("   Name: ");
		JTextField p2EnterName = new JTextField();
		p2EnterName.setText("Player 2");
		p2EnterName.setSize(200, 20);

		p2NamePanel.add(p2);
		p2NamePanel.add(p2Name);
		p2NamePanel.add(p2EnterName);


		JPanel p2ButtonPanel = new JPanel();
		p2ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JRadioButton p2Human = new JRadioButton("Human");
		JRadioButton p2Simple = new JRadioButton("Simple AI");
		JRadioButton p2Inter = new JRadioButton("Intermediate AI");
		JRadioButton p2Expert = new JRadioButton("Expert AI");

		ButtonGroup p2BG = new ButtonGroup();
		p2BG.add(p2Human);
		p2BG.add(p2Simple);
		p2BG.add(p2Inter);
		p2BG.add(p2Expert);

		JLabel p2ButtonBuffer = new JLabel();
		p2ButtonBuffer.setSize(50, 10);

		p2ButtonPanel.add(p2ButtonBuffer);
		p2ButtonPanel.add(p2Human);
		p2ButtonPanel.add(p2Simple);
		p2ButtonPanel.add(p2Inter);
		p2ButtonPanel.add(p2Expert);


		//Player 3
		JLabel p3 = new JLabel("Player 3 (Orange):");


		JPanel p3NamePanel = new JPanel();
		p3NamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel p3Name = new JLabel("   Name: ");
		JTextField p3EnterName = new JTextField();
		p3EnterName.setText("Player 3");
		p3EnterName.setSize(200, 20);

		p3NamePanel.add(p3);
		p3NamePanel.add(p3Name);
		p3NamePanel.add(p3EnterName);


		JPanel p3ButtonPanel = new JPanel();
		p3ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JRadioButton p3Human = new JRadioButton("Human");
		JRadioButton p3Simple = new JRadioButton("Simple AI");
		JRadioButton p3Inter = new JRadioButton("Intermediate AI");
		JRadioButton p3Expert = new JRadioButton("Expert AI");
		JRadioButton p3NA = new JRadioButton("Not Playing");

		ButtonGroup p3BG = new ButtonGroup();
		p3BG.add(p3Human);
		p3BG.add(p3Simple);
		p3BG.add(p3Inter);
		p3BG.add(p3Expert);
		p3BG.add(p3NA);

		JLabel p3ButtonBuffer = new JLabel();
		p3ButtonBuffer.setSize(50, 9);

		p3ButtonPanel.add(p3ButtonBuffer);
		p3ButtonPanel.add(p3Human);
		p3ButtonPanel.add(p3Simple);
		p3ButtonPanel.add(p3Inter);
		p3ButtonPanel.add(p3Expert);
		p3ButtonPanel.add(p3NA);

		//Player 4
		JLabel p4 = new JLabel("Player 4 (Pink):");


		JPanel p4NamePanel = new JPanel();
		p4NamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel p4Name = new JLabel("   Name: ");
		JTextField p4EnterName = new JTextField();
		p4EnterName.setText("Player 4");
		p4EnterName.setSize(200, 20);

		p4NamePanel.add(p4);
		p4NamePanel.add(p4Name);
		p4NamePanel.add(p4EnterName);


		JPanel p4ButtonPanel = new JPanel();
		p4ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JRadioButton p4Human = new JRadioButton("Human");
		JRadioButton p4Simple = new JRadioButton("Simple AI");
		JRadioButton p4Inter = new JRadioButton("Intermediate AI");
		JRadioButton p4Expert = new JRadioButton("Expert AI");
		JRadioButton p4NA = new JRadioButton("Not Playing");

		ButtonGroup p4BG = new ButtonGroup();
		p4BG.add(p4Human);
		p4BG.add(p4Simple);
		p4BG.add(p4Inter);
		p4BG.add(p4Expert);
		p4BG.add(p4NA);

		JLabel p4ButtonBuffer = new JLabel();
		p4ButtonBuffer.setSize(50, 9);

		p4ButtonPanel.add(p4ButtonBuffer);
		p4ButtonPanel.add(p4Human);
		p4ButtonPanel.add(p4Simple);
		p4ButtonPanel.add(p4Inter);
		p4ButtonPanel.add(p4Expert);
		p4ButtonPanel.add(p4NA);


		//Player 5
		JLabel p5 = new JLabel("Player 5 (Yellow):");


		JPanel p5NamePanel = new JPanel();
		p5NamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel p5Name = new JLabel("   Name: ");
		JTextField p5EnterName = new JTextField();
		p5EnterName.setText("Player 5");
		p5EnterName.setSize(200, 20);

		p5NamePanel.add(p5);
		p5NamePanel.add(p5Name);
		p5NamePanel.add(p5EnterName);


		JPanel p5ButtonPanel = new JPanel();
		p5ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JRadioButton p5Human = new JRadioButton("Human");
		JRadioButton p5Simple = new JRadioButton("Simple AI");
		JRadioButton p5Inter = new JRadioButton("Intermediate AI");
		JRadioButton p5Expert = new JRadioButton("Expert AI");
		JRadioButton p5NA = new JRadioButton("Not Playing");

		ButtonGroup p5BG = new ButtonGroup();
		p5BG.add(p5Human);
		p5BG.add(p5Simple);
		p5BG.add(p5Inter);
		p5BG.add(p5Expert);
		p5BG.add(p5NA);

		JLabel p5ButtonBuffer = new JLabel();
		p5ButtonBuffer.setSize(50, 9);

		p5ButtonPanel.add(p5ButtonBuffer);
		p5ButtonPanel.add(p5Human);
		p5ButtonPanel.add(p5Simple);
		p5ButtonPanel.add(p5Inter);
		p5ButtonPanel.add(p5Expert);
		p5ButtonPanel.add(p5NA);


		//Player 6
		JLabel p6 = new JLabel("Player 6 (Purple):");


		JPanel p6NamePanel = new JPanel();
		p6NamePanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JLabel p6Name = new JLabel("   Name: ");
		JTextField p6EnterName = new JTextField();
		p6EnterName.setText("Player 6");
		p6EnterName.setSize(200, 20);

		p6NamePanel.add(p6);
		p6NamePanel.add(p6Name);
		p6NamePanel.add(p6EnterName);


		JPanel p6ButtonPanel = new JPanel();
		p6ButtonPanel.setLayout(new FlowLayout(FlowLayout.LEADING));

		JRadioButton p6Human = new JRadioButton("Human");
		JRadioButton p6Simple = new JRadioButton("Simple AI");
		JRadioButton p6Inter = new JRadioButton("Intermediate AI");
		JRadioButton p6Expert = new JRadioButton("Expert AI");
		JRadioButton p6NA = new JRadioButton("Not Playing");

		ButtonGroup p6BG = new ButtonGroup();
		p6BG.add(p6Human);
		p6BG.add(p6Simple);
		p6BG.add(p6Inter);
		p6BG.add(p6Expert);
		p6BG.add(p6NA);

		JLabel p6ButtonBuffer = new JLabel();
		p6ButtonBuffer.setSize(50, 9);

		p6ButtonPanel.add(p6ButtonBuffer);
		p6ButtonPanel.add(p6Human);
		p6ButtonPanel.add(p6Simple);
		p6ButtonPanel.add(p6Inter);
		p6ButtonPanel.add(p6Expert);
		p6ButtonPanel.add(p6NA);

		//set default selected
		p1Human.setSelected(true);
		p2Simple.setSelected(true);
		p3NA.setSelected(true);
		p4NA.setSelected(true);
		p5NA.setSelected(true);
		p6NA.setSelected(true);


		Object[] message = {instructionLabel, p1NamePanel, p1ButtonPanel, p2NamePanel, 
				p2ButtonPanel, p3NamePanel, p3ButtonPanel, p4NamePanel, p4ButtonPanel,
				p5NamePanel, p5ButtonPanel, p6NamePanel, p6ButtonPanel};
		int option = JOptionPane.showConfirmDialog(null, message, "Select your opponents",
				JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.CANCEL_OPTION) {
			System.exit(0);
		}
		//add the players to the array
		else{
			//add player 1
			if(p1Human.isSelected())
				players.add(new HumanPlayer(p1EnterName.getText()));
			else if(p1Simple.isSelected())
				players.add(new SimpleAIPlayer(p1EnterName.getText()));
			else if(p1Inter.isSelected())
				players.add(new IntermediateAIPlayer(p1EnterName.getText()));
			else if(p1Expert.isSelected())
				players.add(new ExpertAIPlayer(p1EnterName.getText()));

			//player 2
			if(p2Human.isSelected())
				players.add(new HumanPlayer(p2EnterName.getText()));
			else if(p2Simple.isSelected())
				players.add(new SimpleAIPlayer(p2EnterName.getText()));
			else if(p2Inter.isSelected())
				players.add(new IntermediateAIPlayer(p2EnterName.getText()));
			else if(p2Expert.isSelected())
				players.add(new ExpertAIPlayer(p2EnterName.getText()));

			//add player 3
			if(p3Human.isSelected())
				players.add(new HumanPlayer(p3EnterName.getText()));
			else if(p3Simple.isSelected())
				players.add(new SimpleAIPlayer(p3EnterName.getText()));
			else if(p3Inter.isSelected())
				players.add(new IntermediateAIPlayer(p3EnterName.getText()));
			else if(p3Expert.isSelected())
				players.add(new ExpertAIPlayer(p3EnterName.getText()));

			//add player 4
			if(p4Human.isSelected())
				players.add(new HumanPlayer(p4EnterName.getText()));
			else if(p4Simple.isSelected())
				players.add(new SimpleAIPlayer(p4EnterName.getText()));
			else if(p4Inter.isSelected())
				players.add(new IntermediateAIPlayer(p4EnterName.getText()));
			else if(p4Expert.isSelected())
				players.add(new ExpertAIPlayer(p4EnterName.getText()));

			//add player 5
			if(p5Human.isSelected())
				players.add(new HumanPlayer(p5EnterName.getText()));
			else if(p5Simple.isSelected())
				players.add(new SimpleAIPlayer(p5EnterName.getText()));
			else if(p5Inter.isSelected())
				players.add(new IntermediateAIPlayer(p5EnterName.getText()));
			else if(p5Expert.isSelected())
				players.add(new ExpertAIPlayer(p5EnterName.getText()));

			//add player 6
			if(p6Human.isSelected())
				players.add(new HumanPlayer(p6EnterName.getText()));
			else if(p6Simple.isSelected())
				players.add(new SimpleAIPlayer(p6EnterName.getText()));
			else if(p6Inter.isSelected())
				players.add(new IntermediateAIPlayer(p6EnterName.getText()));
			else if(p6Expert.isSelected())
				players.add(new ExpertAIPlayer(p6EnterName.getText()));

		}
	}

	/**
	 * Helper method to set up the label panel at the bottom of the screen. 
	 */
	public void setUpLabelPanel() {
		labelPanel = new JPanel();
		labelPanel.setPreferredSize(new Dimension(960, 40));
		labelPanel.setVisible(true);

		label = new JLabel();
		label.setSize(900, 50);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setText("You have started a new game!");

		labelPanel.add(label);
	}

	/**
	 * Setter for the label panel message
	 * @param message
	 */
	public void setLabel(String message) {
		label.setText(message);
	}

	/**
	 * Getter for the RiskController
	 * @return
	 */
	public RiskController getController() {
		return controller;
	}

	/**
	 * Main method to run the game
	 * @param args
	 */
	public static void main(String[] args) {
		new RiskGUI();
	}

	/**
	 * Prompts the user to select a territory. Used in the initial territory claiming, 
	 * army placement, and gameplay (attacking). 
	 * @param t
	 */
	public void territorySelected(Territory t) {
		if(typeOfPlay == TypeOfPlay.SELECT_TERRITORY) {
			if(t.getCurrentOwner() != null) {
				label.setText("That territory is already occupied, please try again");
			}
			else {
				t.setCurrentOwner(human);
				t.setNumArmies(1);
				human.setNumArmies(human.getNumArmies() - 1);
				human.addTerritory(t);
				mapPanel.repaint();
				label.setText("You have claimed the territory " + t.getName());
				typeOfPlay = TypeOfPlay.DO_NOTHING;
				human.setTerritoryChosen(true);
				//System.out.println("NumArmies = " + human.getNumArmies());
			}
		}
		else if(typeOfPlay == TypeOfPlay.PLACE_ARMY) {
			if(t.getCurrentOwner() != human) {
				label.setText("You don't own that territory...");
			}
			else {
				t.setNumArmies(t.getNumArmies() + 1);
				human.setNumArmies(human.getNumArmies() - 1);
				label.setText("You have added an army to " + t.getName());
				typeOfPlay = TypeOfPlay.DO_NOTHING;
				human.setTerritoryChosen(true);
			}
		}
		else if (typeOfPlay == TypeOfPlay.ATTACK_FROM) {
			boolean possible = false;

			for(Territory a: t.getAdjacent()) {
				if(a.getCurrentOwner() != human)
					possible = true;
			}
			if(possible) {
				label.setText("You selected " + t.getName() + " to attack from.");
				human.setAttackFrom(t);
				human.setTerritoryChosen(true);
				typeOfPlay = TypeOfPlay.DO_NOTHING;
			}
			else
				label.setText("You cannot attack from " + t.getName() + "! Please select another");

		}
		else if (typeOfPlay == TypeOfPlay.ATTACK_TO){
			label.setText("You are attacking " + t.getName());
			human.setAttackTo(t);
			human.setTerritoryChosen(true);
			typeOfPlay = TypeOfPlay.DO_NOTHING;
		}
		else if(typeOfPlay == TypeOfPlay.FORTIFY_FROM) {
			label.setText("You are moving armies from " + t.getName());
			human.setReinforceFrom(t);
			human.setTerritoryChosen(true);
			typeOfPlay = TypeOfPlay.DO_NOTHING;
		}
		else if(typeOfPlay == TypeOfPlay.FORTIFY_TO) {
			label.setText("You are moving armies from " + t.getName());
			human.setReinforceTo(t);
			human.setTerritoryChosen(true);
			typeOfPlay = TypeOfPlay.DO_NOTHING;
		}
		else {
			label.setText("It is not your turn");
		}
	}

	/**
	 * Updates the label panel and side panel, and plays explosions as appropriate, 
	 * throughout gameplay. 
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof RiskController) {}
		else
			currentPlayer = (Player) o;
		if(o instanceof HumanPlayer)
			human = (HumanPlayer) o;

		if(arg instanceof String) {
			label.setText((String) arg); 
			sidePanel.repaint();
		}
		else if(arg == ObserverMessages.HUMAN_PLACE_ARMY) {
			boolean allTaken = true;
			for(Territory t: controller.getTerritories()) {
				if(t.getCurrentOwner() == null)
					allTaken = false;
			}
			if(!allTaken) {
				typeOfPlay = TypeOfPlay.SELECT_TERRITORY;
				label.setText("Please select a Territory to claim");

			}

			else {
				typeOfPlay = TypeOfPlay.PLACE_ARMY;
				label.setText("You have " + human.getNumArmies() + " armies left to place.  "
						+ "Please select a territory to place an army.");

			}
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_ATTACK_FROM) {
			label.setText("Please select one of your territories to attack from.");
			typeOfPlay = TypeOfPlay.ATTACK_FROM;
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_ATTACK_TO) {
			label.setText("Please select an enemy territory to attack.");
			typeOfPlay = TypeOfPlay.ATTACK_TO;
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_REINFORCE_FROM) {
			label.setText("Please select a territory to move armies from (to fortify).");
			typeOfPlay = TypeOfPlay.FORTIFY_FROM;
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_REINFORCE_TO) {
			label.setText("Please select a territory to fortify (must be adjacent to " + human.getReinforceFrom() + ".");
			typeOfPlay = TypeOfPlay.FORTIFY_TO;
		}

		else if (arg == ObserverMessages.HUMAN_SELECT_NUM_ARMIES) {
			label.setText("Please enter a number of armies to move.");
			typeOfPlay = TypeOfPlay.FORTIFY_ARMIES;
		}

		else if (arg == ObserverMessages.HUMAN_TRY_AGAIN_ARMIES) {
			label.setText("The number you select must be less than the number armies in your territory");
			typeOfPlay = TypeOfPlay.FORTIFY_ARMIES;
		}
		else if (arg == ObserverMessages.START_EXPLOSION) {
			Explosion e = new Explosion(0, 0);
			Territory t = controller.getDefendingTerritory();
			e.setPosition((int)t.getLabelPosition().getX() + 10, 
					(int)t.getLabelPosition().getY() - e.getSprite().getHeight()/2 + 20);
			mapPanel.getSplosions().add(e);
			e.start();
		}
		else if (arg == ObserverMessages.NEW_TURN) {
			sidePanel.repaint();
		}
		mapPanel.repaint();
		sidePanel.repaint();
	}

	/**
	 * getter for human
	 * @return
	 */
	public Player getHumanPlayer() {
		return human;
	}

	/**
	 * getter for players
	 * @return
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}

	/**
	 * getter for currentPlayer
	 * @return
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Enum for the type of play, designating the phase of the turn. 
	 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
	 *
	 */
	private enum TypeOfPlay {
		SELECT_TERRITORY, PLACE_ARMY, ATTACK_FROM, ATTACK_TO, FORTIFY_FROM, FORTIFY_TO, FORTIFY_ARMIES, DO_NOTHING
	}

}
