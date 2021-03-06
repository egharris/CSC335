package model;

import javax.swing.JOptionPane;

/**
 * Allows a human user to play a game of Risk. Sends messages back and forth to the GUI to 
 * record the user's decisions and interactions. 
 * @author Steven Broussard, Elizabeth Harris, Jeremy Jalnos, Becca Simon
 */
public class HumanPlayer extends Player{
	private Territory currentTerritory, attackFromTerritory, attackToTerritory, reinforceFrom, reinforceTo;
	private boolean territoryChosen;
	private boolean armiesChosen;

	/**
	 * Constructs a new HumanPlayer object using the Player constructor. The player's
	 * name is set to the parameter and territoryChosen is set to false. 
	 * @param name
	 */
	public HumanPlayer(String name){
		super(name);
		territoryChosen = false;
	}

	/**
	 * Prompts the user to select a territory on which to place an army
	 */
	@Override
	public void placeArmy() {
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_PLACE_ARMY);
		while(territoryChosen == false) {
			setChanged();
			notifyObservers();
			if(territoryChosen == true) {
				territoryChosen = false;
				return;
			}
		}
		territoryChosen = false;
		return;

	}

	/**
	 * Prompts the user to select a territory to attack from
	 */
	@Override
	public Territory attackFrom() {
		attackFromTerritory = null;
		attackToTerritory = null;
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_ATTACK_FROM);
		while (!territoryChosen) {
			setChanged();
			notifyObservers();
			if(getDoneAttacking() == true) {
				return null;
			}
			if(territoryChosen == true) {
				territoryChosen = false;
				return attackFromTerritory;
			}
		}
		// choose one of your territories with two or more armies to attack from
		// if you no longer want to attack, return null
		return null;
	}

	/**
	 * Prompts the user to select an enemy territory to attack
	 */
	@Override
	public Territory attackTo(Territory attackFrom) {
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_ATTACK_TO);
		while (!territoryChosen) {
			setChanged();
			notifyObservers();
			if(territoryChosen == true) {
				territoryChosen = false;
				return attackToTerritory;
			}
		}
		// choose another player's territory adjacent to attackFrom
		return null;
	}

	/**
	 * Prompts the human user to select territories to reinforce from and to, 
	 * and select a number of armies to move
	 */
	@Override
	public void reinforceArmies() {
		boolean reinforcePossible = false;
		for(Territory t: getTerritoriesOwned()) {
			for(Territory a: t.getAdjacent()) {
				if(a.getCurrentOwner() == this && t.getNumArmies() > 1)
					reinforcePossible = true;
			}
		}
		if(!reinforcePossible)
			return;

		reinforceFrom = null;
		reinforceTo = null;
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_REINFORCE_FROM);
		while (!territoryChosen) {
			setChanged();
			notifyObservers(ObserverMessages.HUMAN_SELECT_REINFORCE_FROM);
			if (territoryChosen == true) {
				boolean possible = false;
				for(Territory t: reinforceFrom.getAdjacent()) {
					if(t.getCurrentOwner() == this) {
						possible = true;
					}
				}
				if(!possible) {
					territoryChosen = false;
				}
				else if(reinforceFrom.getCurrentOwner() == this && reinforceFrom.getNumArmies() > 1) {
					territoryChosen = false;
					break;
				}
				else
					territoryChosen = false;
			}
		}
		this.setChanged();
		notifyObservers(ObserverMessages.HUMAN_SELECT_REINFORCE_TO);
		while (!territoryChosen) {
			setChanged();
			notifyObservers(ObserverMessages.HUMAN_SELECT_REINFORCE_TO);
			if (territoryChosen == true) {
				if(reinforceTo.getCurrentOwner() == this && reinforceFrom.getAdjacent().contains(reinforceTo)) {
					territoryChosen = false;
					break;
				}
				else
					territoryChosen = false;
			}
		}
		int armies = 0;
		do {
			String message = JOptionPane.showInputDialog("<html>Please enter the number of armies to move from " + 
					reinforceFrom.getName() + " to " + reinforceTo.getName() + "<br>If you dont want" +
					" to reinforce, enter 0.", "0");
			try {
				armies = Integer.parseInt(message);
			}catch(Exception e) { armies = -1;}
		}while(armies < 0 && armies > reinforceFrom.getNumArmies() - 1);

		reinforceFrom.setNumArmies(reinforceFrom.getNumArmies() - armies);
		reinforceTo.setNumArmies(reinforceTo.getNumArmies() + armies);
	}
	/**
	 * getter for territoryChosen
	 * @return
	 */
	public boolean isTerritoryChosen() {
		return territoryChosen;
	}

	/**
	 * setter for territoryChosen
	 * @param territoryChosen
	 */
	public void setTerritoryChosen(boolean territoryChosen) {
		this.territoryChosen = territoryChosen;
	}

	/**
	 * getter for currentTerritory, the user's most recently selected territory
	 * @return
	 */
	public Territory getCurrentTerritory() {
		return currentTerritory;
	}

	/**
	 * setter for currentTerritory
	 * @param currentTerritory
	 */
	public void setCurrentTerritory(Territory currentTerritory) {
		this.currentTerritory = currentTerritory;
	}

	/**
	 * getter for armiesChosen
	 * @return
	 */
	public boolean getArmiesChosen() {
		return armiesChosen;
	}

	/**
	 * setter for armiesChosen
	 * @param armiesChosen
	 */
	public void setArmiesChosen(boolean armiesChosen) {
		this.armiesChosen = armiesChosen;
	}

	/**
	 * setter for attackFromTerritory
	 * @param t
	 */
	public void setAttackFrom(Territory t) {
		attackFromTerritory = t;
	}

	/**
	 * setter for attackToTerritory
	 * @param t
	 */
	public void setAttackTo(Territory t) {
		attackToTerritory = t;
	}

	/**
	 * getter for attackFromTerritory
	 * @return
	 */
	public Territory getAttackFrom() {
		return attackFromTerritory;
	}

	/**
	 * setter for reinforceFrom
	 * @param t
	 */
	public void setReinforceFrom(Territory t) {
		reinforceFrom = t;
	}
	
	/**
	 * setter for reinforceTo
	 * @param t
	 */
	public void setReinforceTo(Territory t) {
		reinforceTo = t;
	}
	
	/**
	 * getter for reinforceFrom
	 * @return
	 */
	public Territory getReinforceFrom() {
		return reinforceFrom;
	}
}
