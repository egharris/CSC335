package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class ExpertAIPlayer extends Player {

	private ArrayList<Territory> NA;
	private ArrayList<Territory> SA;
	private ArrayList<Territory> EU;
	private ArrayList<Territory> AF;
	private ArrayList<Territory> AS;
	private ArrayList<Territory> AU;
	private Continent chosenContinent;
	private Random genRan;
	private int numAttacks;

	public ExpertAIPlayer(String name) {
		super(name);
		if (debug)
			System.out.println("New ExpertAIPlayer created: " + name);
	}

	private void setContinents() {
		NA = new ArrayList<Territory>();
		SA = new ArrayList<Territory>();
		EU = new ArrayList<Territory>();
		AF = new ArrayList<Territory>();
		AS = new ArrayList<Territory>();
		AU = new ArrayList<Territory>();

		for(Territory t: getAllTerritories()) {
			switch(t.getContinent()) {
			case NORTH_AMERICA:
				NA.add(t);
				break;
			case SOUTH_AMERICA:
				SA.add(t);
				break;
			case EUROPE:
				EU.add(t);
				break;
			case AFRICA:
				AF.add(t);
				break;
			case ASIA:
				AS.add(t);
				break;
			case AUSTRALIA:
				AU.add(t);
				break;
			}
		}
	}

	/**
	 * place an army down in an empty continent or less then two armies in the continent. After the first army is 
	 * placed, then clusters around the it and try to take over the continent.
	 */

	@Override
	public void placeArmy() {
		if(debug) System.out.println("placeArmy called by "+getName());
		Territory selected = null;

		genRan = new Random();

		// determines if you are still in territory selecting mode or if in army placing mode
		boolean allSelected = true;
		for (Territory t : getAllTerritories()) {
			if (t.getCurrentOwner() == null) {
				allSelected = false;
			}
		}

		if (allSelected == false) {
			// if it is the first territory to select
			if (getTerritoriesOwned().size() == 0) {
				setContinents();
				// check continent to see if there is another player with land here, empty or less than 2 other territories taken

				//compatible is trying to get a continent to yourself
				boolean NACompatible = false;
				boolean SACompatible = false;
				boolean EUCompatible = false;
				boolean AFCompatible = false;
				boolean ASICompatible = false;
				boolean AUSCompatible = false;
				boolean isTaken = false;//somebody else has claimed a territory in the continent

				//North America Availability
				for (Territory n: NA) {
					if (n.getCurrentOwner() != null)
						isTaken = true;
				}
				if (!isTaken)
					NACompatible = true;

				isTaken = false;

				//South America Compatible
				for (Territory n: SA) {
					if (n.getCurrentOwner() != null)
						isTaken = true;
				}
				if (!isTaken)
					SACompatible = true;

				isTaken = false;

				// Europe Compatible
				for (Territory n: EU) {
					if (n.getCurrentOwner() != null)
						isTaken = true;
				}
				if (!isTaken)
					EUCompatible = true;

				isTaken = false;

				// Africa Compatible
				for (Territory n: AF) {
					if (n.getCurrentOwner() != null)
						isTaken = true;
				}
				if (!isTaken) 
					AFCompatible = true;
				isTaken = false;

				// Asia Compatible
				for (Territory n: AS) {
					if (n.getCurrentOwner() != null)
						isTaken = true;
				}
				if (!isTaken) 
					ASICompatible = true;
				isTaken = false;

				// Australia Compatible
				for (Territory n: AU) {
					if (n.getCurrentOwner() != null)
						isTaken = true;
				}
				if (!isTaken) 
					AUSCompatible = true;
				isTaken = false;


				ArrayList<Continent> compatibleContinent = new ArrayList<Continent>();


				if (NACompatible) {
					compatibleContinent.add(Continent.NORTH_AMERICA);
				}
				if (SACompatible) {
					compatibleContinent.add(Continent.SOUTH_AMERICA);
				}
				if (EUCompatible) {
					compatibleContinent.add(Continent.EUROPE);
				}
				if (AFCompatible) {
					compatibleContinent.add(Continent.AFRICA);
				}
				if (ASICompatible) {
					compatibleContinent.add(Continent.ASIA);
				}
				if (AUSCompatible) {
					compatibleContinent.add(Continent.AUSTRALIA);
				}


				int r = genRan.nextInt(compatibleContinent.size());
				if(debug) System.out.println(compatibleContinent.get(r).toString());

				if (compatibleContinent.get(r) == Continent.NORTH_AMERICA) {
					int k = genRan.nextInt(NA.size());
					selected = NA.get(k);
					chosenContinent = Continent.NORTH_AMERICA;
				}

				else if (compatibleContinent.get(r) == Continent.SOUTH_AMERICA) {
					int k = genRan.nextInt(SA.size());
					selected = SA.get(k);
					chosenContinent = Continent.SOUTH_AMERICA;
				}

				else if (compatibleContinent.get(r) == Continent.EUROPE) {
					int k = genRan.nextInt(EU.size());
					selected = EU.get(k);
					chosenContinent = Continent.EUROPE;
				}

				else if (compatibleContinent.get(r) == Continent.AFRICA) {
					int k = genRan.nextInt(AF.size());
					selected = AF.get(k);
					chosenContinent = Continent.AFRICA;
				}

				else if (compatibleContinent.get(r) == Continent.ASIA) {
					int k = genRan.nextInt(AS.size());
					selected = AS.get(k);
					chosenContinent = Continent.ASIA;
				}

				else if (compatibleContinent.get(r) == Continent.AUSTRALIA) {
					int k = genRan.nextInt(AU.size());
					selected = AU.get(k);
					chosenContinent = Continent.AUSTRALIA;
				}

				selected.setCurrentOwner(this);
				selected.setNumArmies(1);
				addTerritory(selected);
				setNumArmies(getNumArmies() - 1);

				setChanged();
				notifyObservers("" + this.getName() + " claimed " + selected.getName());

				if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
				if(debug) System.out.println(getAllTerritories());


			}// if its the first one

			else {
				//determine if there is a territory still available on the choosen continent
				boolean stillAvailable = false;
				if(chosenContinent == Continent.NORTH_AMERICA) {
					for(Territory t: NA) {
						if(t.getCurrentOwner() == null)
							stillAvailable = true;
					}
				}
				else if(chosenContinent == Continent.SOUTH_AMERICA) {
					for(Territory t: SA) {
						if(t.getCurrentOwner() == null)
							stillAvailable = true;
					}
				}
				else if(chosenContinent == Continent.EUROPE) {
					for(Territory t: EU) {
						if(t.getCurrentOwner() == null)
							stillAvailable = true;
					}
				}
				else if(chosenContinent == Continent.AFRICA) {
					for(Territory t: AF) {
						if(t.getCurrentOwner() == null)
							stillAvailable = true;
					}
				}
				else if(chosenContinent == Continent.ASIA) {
					for(Territory t: AS) {
						if(t.getCurrentOwner() == null)
							stillAvailable = true;
					}
				}
				else if(chosenContinent == Continent.AUSTRALIA) {
					for(Territory t: AU) {
						if(t.getCurrentOwner() == null)
							stillAvailable = true;
					}
				}
				selected = null;
				if(stillAvailable) {
					for(int i = 0; i < 7; i++) {//try clustering in the continent
						int n = 0;
						while(selected == null && n < getTerritoriesOwned().size()) {
							Territory clusterAround = getTerritoriesOwned().get(n);
							Territory temp = clusterAround.getAdjacent().get(genRan.nextInt(clusterAround.getAdjacent().size()));
							if(temp.getCurrentOwner() == null && temp.getContinent() == chosenContinent) {
								selected = temp;
							}
							n++;
						}
					}
					for(Territory t: getAllTerritories()) {
						if(selected == null && t.getCurrentOwner() == null && t.getContinent() == chosenContinent) {
							selected = t;
						}
					}
				}
				else {
					for(int i = 0; i < 10; i++) {//try clustering around any territory
						int n = 0;
						while(selected == null && n < getTerritoriesOwned().size()) {
							Territory clusterAround = getTerritoriesOwned().get(n);
							Territory temp = clusterAround.getAdjacent().get(genRan.nextInt(clusterAround.getAdjacent().size()));
							if(temp.getCurrentOwner() == null)
								selected = temp;
							n++;
						}
					}
				}

				while(selected == null) {
					int n = genRan.nextInt(42);
					if(getAllTerritories().get(n).getCurrentOwner() == null)
						selected = getAllTerritories().get(n);
				}

				if(debug) System.out.println("SelectedPlace: " + selected.toString());
				selected.setCurrentOwner(this);
				selected.setNumArmies(1);
				addTerritory(selected);
				setNumArmies(getNumArmies() - 1);
				setChanged();
				notifyObservers("" + this.getName() + " claimed " + selected.getName());
				if(debug) System.out.println("Army successfully placed in " + selected.getName() + " by "+getName());
				if(debug) System.out.println(getAllTerritories());

			}// allSelected if statement
		}//end of choosing empty territories
		else {
			int r = genRan.nextInt(getTerritoriesOwned().size());
			for(int i = 0; i < 10; i++) {
				if(selected == null) {
					Territory temp = getTerritoriesOwned().get(r);
					for(Territory t: temp.getAdjacent()) {
						if(t.getCurrentOwner() != this) {
							selected = temp;
						}
					}
				}
			}
			if(selected == null) {
				selected = getTerritoriesOwned().get(genRan.nextInt(getTerritoriesOwned().size()));
			}
			selected.setNumArmies(selected.getNumArmies() + 1);
			setNumArmies(getNumArmies() - 1);
			setChanged();
			notifyObservers("" + this.getName() + " placed an army in " + selected.getName());
			if (debug) System.out.println("Army successfully placed in owned territory by "+getName());
		}
	}

	/**
	 * finds the smallest army owned and adds the required amount of armies to defend itself from
	 * the enemy in the adjacent territories
	 * @param takeArmy
	 * @param reinforceThis
	 */

	@Override
	public void reinforceArmies() {
		if (debug)
			System.out.println("reinforceArmies called by " + getName());
		if (numTimesCalled < 1) {
			reinforce();
			numTimesCalled++;
		} else {
			//			int x = takeArmy.getNumArmies();
			//			int y = reinforceThis.getNumArmies();
			//			takeArmy.setNumArmies(x -= numTroopsTake);
			//			reinforceThis.setNumArmies(y += numTroopsTake);
			//			numTimesCalled = 0;
		}

	}

	private int numTroopsTake = 0;
	private int numTimesCalled = 0;

	/**
	 * helper method for rienforceArmies
	 */

	public void reinforce() { // helper method for reinforce armies method
		if (debug)
			System.out.println("reinforce called by " + getName());

		Territory high = null;
		Territory low = null;
		int highTroop = 0;
		int lowTroop = 1000;
		boolean take = false;
		boolean adjacentNotOwned = false;

		for (int i = 0; i <= getTerritoriesOwned().size(); i++) {
			for (int j = 0; j <= getTerritoriesOwned().get(i).getAdjacent()
					.size(); j++) {// check all territories look through all
				// armies find lowest with competitors or
				// ones with no competitors and give to
				// territories more in need

				if (getTerritoriesOwned().get(i).getAdjacent().get(j)
						.getCurrentOwner() != this) { // checks for un-owned
					// neighbors
					if (getTerritoriesOwned().get(i).getAdjacent().get(j)
							.getNumArmies() >= getTerritoriesOwned().get(i)
							.getNumArmies()) {// checks to see if neigbor has
						// more armies than us
						if (getTerritoriesOwned().get(i).getNumArmies() < lowTroop) {// checks
							// to
							// see
							// if
							// we
							// are
							// more
							// screwed
							// than
							// other
							// territories
							low = getTerritoriesOwned().get(i);// sets this as
							// the low
							// territory
							lowTroop = getTerritoriesOwned().get(i)
									.getNumArmies(); // sets how many current
							// armies there are here
						}// if statement
					}// if statement
				}// if statement
			}// for loop
			for (int e = 0; e <= getTerritoriesOwned().get(i).getAdjacent()
					.size(); e++) {// loops through adjacent territories
				if (getTerritoriesOwned().get(i).getAdjacent().get(e)
						.getCurrentOwner() != this) {// checks if we don't own
					// an adjacent land
					adjacentNotOwned = true;// if we don't own something we
					// can't automatically take from
					// this territory
					if (getTerritoriesOwned().get(i).getNumArmies() >= highTroop) {
						//checks to see if this is the highest available troop count
						if (getTerritoriesOwned().get(i).getAdjacent().get(e)
								.getNumArmies() <= (getTerritoriesOwned()
										.get(i).getNumArmies() * 2) / 3) { 
							// checks to see if they should take from here for sure
							take = true;
							highTroop = getTerritoriesOwned().get(i)
									.getNumArmies();
							high = getTerritoriesOwned().get(i);
						}
					} else {
						take = false;
					}
				}// if statement
			}// for loop

			if (adjacentNotOwned == false) {
				take = true;
				highTroop = getTerritoriesOwned().get(i).getNumArmies();
				high = getTerritoriesOwned().get(i);
				break;
			}// if statement
			if (take) {
				numTroopsTake = (highTroop * 2) / 3;
			}
		}// master for loop

		//		reinforceArmies(high, low);

	}// close helper method

	/**
	 * attack with the maximum armies
	 * @return Territory
	 */

	@Override
	public Territory attackFrom() {
		if (debug) System.out.println("attackFrom called by "+getName());
		Territory choosenTerritory = null;

		Continent continentToAttack = determineContinent();
		
		if(continentToAttack == Continent.NORTH_AMERICA) {
			while(choosenTerritory == null) {
				Territory t = NA.get(genRan.nextInt(NA.size()));
				if(t.getCurrentOwner() != this) {
					for(Territory a: t.getAdjacent()) {
						if(a.getCurrentOwner() == this) {
							if(choosenTerritory == null)
								choosenTerritory = a;
							else if(a.getNumArmies() > choosenTerritory.getNumArmies())
								choosenTerritory = a;
						}
					}
				}
			}
		}
		else if(continentToAttack == Continent.SOUTH_AMERICA) {
			while(choosenTerritory == null) {
				Territory t = SA.get(genRan.nextInt(SA.size()));
				if(t.getCurrentOwner() != this) {
					for(Territory a: t.getAdjacent()) {
						if(a.getCurrentOwner() == this) {
							if(choosenTerritory == null)
								choosenTerritory = a;
							else if(a.getNumArmies() > choosenTerritory.getNumArmies())
								choosenTerritory = a;
						}
					}
				}
			}
		}
		else if(continentToAttack == Continent.EUROPE) {
			while(choosenTerritory == null) {
				Territory t = EU.get(genRan.nextInt(EU.size()));
				if(t.getCurrentOwner() != this) {
					for(Territory a: t.getAdjacent()) {
						if(a.getCurrentOwner() == this) {
							if(choosenTerritory == null)
								choosenTerritory = a;
							else if(a.getNumArmies() > choosenTerritory.getNumArmies())
								choosenTerritory = a;
						}
					}
				}
			}
		}
		else if(continentToAttack == Continent.AFRICA) {
			while(choosenTerritory == null) {
				Territory t = AF.get(genRan.nextInt(AF.size()));
				if(t.getCurrentOwner() != this) {
					for(Territory a: t.getAdjacent()) {
						if(a.getCurrentOwner() == this) {
							if(choosenTerritory == null)
								choosenTerritory = a;
							else if(a.getNumArmies() > choosenTerritory.getNumArmies())
								choosenTerritory = a;
						}
					}
				}
			}
		}
		else if(continentToAttack == Continent.ASIA) {
			while(choosenTerritory == null){
				Territory t = AS.get(genRan.nextInt(AS.size()));
				if(t.getCurrentOwner() != this) {
					for(Territory a: t.getAdjacent()) {
						if(a.getCurrentOwner() == this) {
							if(choosenTerritory == null)
								choosenTerritory = a;
							else if(a.getNumArmies() > choosenTerritory.getNumArmies())
								choosenTerritory = a;
						}
					}
				}
			}
		}
		else if(continentToAttack == Continent.AUSTRALIA) {
			while(choosenTerritory == null) {
				Territory t = AU.get(genRan.nextInt(AU.size()));
				if(t.getCurrentOwner() != this) {
					for(Territory a: t.getAdjacent()) {
						if(a.getCurrentOwner() == this) {
							if(choosenTerritory == null)
								choosenTerritory = a;
							else if(a.getNumArmies() > choosenTerritory.getNumArmies())
								choosenTerritory = a;
						}
					}
				}
			}
		}
		while(choosenTerritory == null) {
			Territory t = getTerritoriesOwned().get(genRan.nextInt(getTerritoriesOwned().size()));
			for(Territory a: t.getAdjacent()) {
				if(a.getCurrentOwner() != this)
					choosenTerritory = t;
			}
		}
		return choosenTerritory;

	}

	private Continent determineContinent() {
		int numNA = 0;
		int numSA = 0;
		int numEU = 0;
		int numAF = 0;
		int numAS = 0;
		int numAU = 0;
		for(Territory t: NA) {
			if(t.getCurrentOwner() != this)
				numNA++;
		}
		for(Territory t: SA) {
			if(t.getCurrentOwner() != this)
				numSA++;
		}
		for(Territory t: EU) {
			if(t.getCurrentOwner() != this)
				numEU++;
		}
		for(Territory t: AF) {
			if(t.getCurrentOwner() != this)
				numAF++;
		}
		for(Territory t: AS) {
			if(t.getCurrentOwner() != this)
				numAS++;
		}
		for(Territory t: AU) {
			if(t.getCurrentOwner() != this)
				numAU++;
		}
		ArrayList<Continent> possible = new ArrayList<Continent>();
		
		//if own all, make it a large number so everything else is less than it
		if(numNA != 0 && numNA < 9)
			possible.add(Continent.NORTH_AMERICA);
		else
			numNA = 20;
		if(numSA != 0 && numSA < 4)
			possible.add(Continent.SOUTH_AMERICA);
		else
			numSA = 20;
		if(numEU != 0 && numEU < 7)
			possible.add(Continent.EUROPE);
		else
			numEU = 20;
		if(numAF != 0 && numAF < 6)
			possible.add(Continent.AFRICA);
		else
			numAF = 20;
		if(numAS != 0 && numAS < 12)
			possible.add(Continent.ASIA);
		else
			numAS = 20;
		if(numAU != 0 && numAU < 4)
			possible.add(Continent.AUSTRALIA);
		else
			numAU = 20;
		
		if(numNA < numSA) {
			if(numNA < numEU) {
				if(numNA < numAF) {
					if(numNA < numAS) {
						if(numNA < numAU) {
							if(possible.contains(Continent.NORTH_AMERICA))
								return Continent.NORTH_AMERICA;
						}
					}
				}
			}
		}
		else if(numSA < numEU) {
			if(numSA < numAF) {
				if(numSA < numAS) {
					if(numSA < numAU) {
						if(possible.contains(Continent.SOUTH_AMERICA))
							return Continent.SOUTH_AMERICA;
					}
				}
			}
		}
		else if(numEU < numAF) {
			if(numEU < numAS) {
				if(numEU < numAU) {
					if(possible.contains(Continent.EUROPE)) {
						if(possible.contains(Continent.EUROPE))
							return Continent.EUROPE;
					}
				}
			}
		}
		else if(numAF < numAS) {
			if(numAF < numAU) {
				if(possible.contains(Continent.AFRICA))
					return Continent.AFRICA;
			}
		}
		else if(numAS < numAU) {
			if(possible.contains(Continent.ASIA))
				return Continent.ASIA;
		}
		else if(possible.contains(Continent.AUSTRALIA))
			return Continent.AUSTRALIA;
		return null;
		

	}

	/**
	 * attack to an army that is significantly smaller then its own
	 * @param attackFrom
	 * @return Territory
	 */

	@Override
	public Territory attackTo(Territory attackFrom) {
		if (debug) System.out.println("attackTo called by "+getName());
		// Semi-intelligently chooses a territory and places one army there
		int lowTroop = 1000;
		Territory attack = null;
		for(int i = 0; i< attackFrom.getAdjacent().size(); i++){
			if(attackFrom.getAdjacent().get(i).getCurrentOwner()!= this){
				if(attackFrom.getAdjacent().get(i).getNumArmies() < lowTroop){
					lowTroop = attackFrom.getAdjacent().get(i).getNumArmies();
					attack = attackFrom.getAdjacent().get(i);
				}
			}
		}
		numAttacks++;
		if(numAttacks == 9) {
			setDoneAttacking(true);
			numAttacks = 0;
		}
		return attack;
	}

}