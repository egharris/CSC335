package controller;

import java.util.ArrayList;

import model.*;

public class RiskController {
	private ArrayList<Territory> territories;
	private Territory alaska, alberta, centralAmerica, easternUS, greenland, northwest,
		ontario, quebec, westernUS, argentina, brazil, peru, venezuela, 
		greatBritain, iceland, northernEurope, scandinavia, southernEurope, 
		ukraine, westernEurope, congo, eastAfrica, egypt, madagascar, northAfrica,
		southAfrica, afghanistan, china, india, irkutsk, japan, kamchatka, 
		middleEast, mongolia, siam, siberia, ural, yakutsk, easternAustralia,
		indonesia, newGuinea, westernAustralia;
	private ArrayList<Player> players;
	private ArrayList<Card> deckOfCards;
	
	public RiskController() {
		// TODO should the constructor take an ArrayList of players? seems like the simplest way
		setUpTerritories();
		//setUpDeck();
		
		// (TODO find out how many players and what kinds; create and add the players)
		//populateBoard();
		//playGame();
	}
	
	public void addPlayer(Player newPlayer) {
		
	}
	
	public void populateBoard() {
		if (players.size() == 2)
			for (Player p : players) {
				p.setNumArmies(40);
			}
		else if (players.size() == 3)
			for (Player p : players) {
				p.setNumArmies(35);
			}
		else if (players.size() == 4)
			for (Player p : players) {
				p.setNumArmies(30);
			}
		else if (players.size() == 5)
			for (Player p : players) {
				p.setNumArmies(25);
			}
		else if (players.size() == 6)
			for (Player p : players) {
				p.setNumArmies(20);
			}
		else
			// TODO later, println's should be messages in the GUI instead
			System.out.println("Illegal number of players.\n"
					+ "Number of players must be between two and six inclusive.");

		for (int i = 0; i < 42; i++) {
			for (Player p : players) {
				p.chooseTerritory(); 
			}
		} // all territories chosen
		
		// TODO while there are unallocated armies remaining, players must put armies on their territories
		// while (players.get(0).getNumArmies() > 0)
		//     for (Player p : players)
		//         p.placeArmy();
	}
	
	public void playGame() {
		while (players.size() > 1) {
			for (Player p : players) {
				p.playTurn();
				// TODO what else needs to be handled here? How and where should battles occur? 
			}
		}
		System.out.println(players.get(0).getName()+" won!");
	}
	
	// TODO a static method to determine the winner of a dice battle (takes parameters from two players)
	
	private void setUpTerritories() {
		territories = new ArrayList<Territory>();
		
		// TODO for Iteration 2, need to set map color for each territory (instance variable in Territory)
		alaska = new Territory("Alaska", Continent.NORTH_AMERICA);
		alberta = new Territory("Alberta", Continent.NORTH_AMERICA);
		centralAmerica = new Territory("Central America", Continent.NORTH_AMERICA);
		easternUS = new Territory("Eastern United States", Continent.NORTH_AMERICA);
		greenland = new Territory("Greenland", Continent.NORTH_AMERICA);
		northwest = new Territory("Northwest Territory", Continent.NORTH_AMERICA);
		ontario = new Territory("Ontario", Continent.NORTH_AMERICA);
		quebec = new Territory("Quebec", Continent.NORTH_AMERICA);
		westernUS = new Territory("Western United States", Continent.NORTH_AMERICA);
		
		argentina = new Territory("Argentina", Continent.SOUTH_AMERICA);
		brazil = new Territory("Brazil", Continent.SOUTH_AMERICA);
		peru = new Territory("Peru", Continent.SOUTH_AMERICA);
		venezuela = new Territory("Venezuela", Continent.SOUTH_AMERICA);
		
		greatBritain = new Territory("Great Britain", Continent.EUROPE);
		iceland = new Territory("Iceland", Continent.EUROPE);
		northernEurope = new Territory("Northern Europe", Continent.EUROPE);
		scandinavia = new Territory("Scandinavia", Continent.EUROPE);
		southernEurope = new Territory("Southern Europe", Continent.EUROPE);
		ukraine = new Territory("Ukraine", Continent.EUROPE);
		westernEurope = new Territory("Western Europe", Continent.EUROPE);
		
		congo = new Territory("Congo", Continent.AFRICA);
		eastAfrica = new Territory("East Africa", Continent.AFRICA);
		egypt = new Territory("Egypt", Continent.AFRICA);
		madagascar = new Territory("Madagascar", Continent.AFRICA);
		northAfrica = new Territory("North Africa", Continent.AFRICA);
		southAfrica = new Territory("South Africa", Continent.AFRICA);
		
		afghanistan = new Territory("Afghanistan", Continent.ASIA);
		china = new Territory("China", Continent.ASIA);
		india = new Territory("India", Continent.ASIA);
		irkutsk = new Territory("Irkutsk", Continent.ASIA);
		japan = new Territory("Japan", Continent.ASIA);
		kamchatka = new Territory("Kamchatka", Continent.ASIA);
		middleEast = new Territory("Middle East", Continent.ASIA);
		mongolia = new Territory("Mongolia", Continent.ASIA);
		siam = new Territory("Siam", Continent.ASIA);
		siberia = new Territory("Siberia", Continent.ASIA);
		ural = new Territory("Ural", Continent.ASIA);
		yakutsk = new Territory("Yakutsk", Continent.ASIA);
		
		easternAustralia = new Territory("Eastern Australia", Continent.AUSTRALIA);
		indonesia = new Territory("Indonesia", Continent.AUSTRALIA);
		newGuinea = new Territory("New Guinea", Continent.AUSTRALIA);
		westernAustralia = new Territory("Western Australia", Continent.AUSTRALIA);
		
		// adjacent for Alaska
		ArrayList<Territory> adjacent = new ArrayList<Territory>();
		adjacent.add(northwest);
		adjacent.add(alberta);
		adjacent.add(kamchatka);
		alaska.setAdjacent(adjacent);
		
		// adjacent for Alberta
		adjacent = new ArrayList<Territory>();
		adjacent.add(alaska);
		adjacent.add(northwest);
		adjacent.add(westernUS);
		adjacent.add(ontario);
		alberta.setAdjacent(adjacent);
		
		// adjacent for Central America
		adjacent = new ArrayList<Territory>();
		adjacent.add(westernUS);
		adjacent.add(easternUS);
		adjacent.add(venezuela);
		centralAmerica.setAdjacent(adjacent);
		
		// adjacent for Eastern United States
		adjacent = new ArrayList<Territory>();
		adjacent.add(quebec);
		adjacent.add(westernUS);
		adjacent.add(ontario);
		adjacent.add(centralAmerica);
		easternUS.setAdjacent(adjacent);
		
		// adjacent for Greenland
		adjacent = new ArrayList<Territory>();
		adjacent.add(northwest);
		adjacent.add(ontario);
		adjacent.add(quebec);
		adjacent.add(iceland);
		greenland.setAdjacent(adjacent);
		
		// adjacent for Northwest Territories
		adjacent = new ArrayList<Territory>();
		adjacent.add(alaska);
		adjacent.add(alberta);
		adjacent.add(ontario);
		adjacent.add(greenland);
		northwest.setAdjacent(adjacent);
		
		// adjacent for Ontario
		adjacent = new ArrayList<Territory>();
		adjacent.add(northwest);
		adjacent.add(alberta);
		adjacent.add(greenland);
		adjacent.add(westernUS);
		adjacent.add(easternUS);
		adjacent.add(quebec);
		ontario.setAdjacent(adjacent);
		
		// adjacent for Quebec
		adjacent = new ArrayList<Territory>();
		adjacent.add(greenland);
		adjacent.add(easternUS);
		adjacent.add(ontario);
		quebec.setAdjacent(adjacent);
		
		// adjacent for Western United States
		adjacent = new ArrayList<Territory>();
		adjacent.add(alberta);
		adjacent.add(ontario);
		adjacent.add(easternUS);
		adjacent.add(centralAmerica);
		westernUS.setAdjacent(adjacent);
		
		// adjacent for Argentina
		adjacent = new ArrayList<Territory>();
		adjacent.add(peru);
		adjacent.add(brazil);
		argentina.setAdjacent(adjacent);
		
		// adjacent for Brazil
		adjacent = new ArrayList<Territory>();
		adjacent.add(venezuela);
		adjacent.add(peru);
		adjacent.add(argentina);
		adjacent.add(northAfrica);
		brazil.setAdjacent(adjacent);
		
		// adjacent for Peru
		adjacent = new ArrayList<Territory>();
		adjacent.add(venezuela);
		adjacent.add(brazil);
		adjacent.add(argentina);
		peru.setAdjacent(adjacent);
		
		// adjacent for Venezuela
		adjacent = new ArrayList<Territory>();
		adjacent.add(centralAmerica);
		adjacent.add(peru);
		adjacent.add(brazil);
		venezuela.setAdjacent(adjacent);
		
		// adjacent for Great Britain
		adjacent = new ArrayList<Territory>();
		adjacent.add(iceland);
		adjacent.add(scandinavia);
		adjacent.add(northernEurope);
		adjacent.add(westernEurope);
		greatBritain.setAdjacent(adjacent);
		
		// adjacent for Iceland
		adjacent = new ArrayList<Territory>();
		adjacent.add(greenland);
		adjacent.add(scandinavia);
		adjacent.add(greatBritain);
		iceland.setAdjacent(adjacent);
		
		// adjacent for Northern Europe
		adjacent = new ArrayList<Territory>();
		adjacent.add(greatBritain);
		adjacent.add(scandinavia);
		adjacent.add(westernEurope);
		adjacent.add(southernEurope);
		adjacent.add(ukraine);
		northernEurope.setAdjacent(adjacent);
		
		// adjacent for Scandinavia
		adjacent = new ArrayList<Territory>();
		adjacent.add(iceland);
		adjacent.add(greatBritain);
		adjacent.add(northernEurope);
		adjacent.add(ukraine);
		scandinavia.setAdjacent(adjacent);
		
		// adjacent for Southern Europe
		adjacent = new ArrayList<Territory>();
		adjacent.add(westernEurope);
		adjacent.add(northernEurope);
		adjacent.add(egypt);
		adjacent.add(northAfrica);
		adjacent.add(middleEast);
		adjacent.add(ukraine);
		southernEurope.setAdjacent(adjacent);
		
		// adjacent for Ukraine
		adjacent = new ArrayList<Territory>();
		adjacent.add(scandinavia);
		adjacent.add(northernEurope);
		adjacent.add(southernEurope);
		adjacent.add(middleEast);
		adjacent.add(afghanistan);
		adjacent.add(ural);
		ukraine.setAdjacent(adjacent);
		
		// adjacent for Western Europe
		adjacent = new ArrayList<Territory>();
		adjacent.add(greatBritain);
		adjacent.add(northernEurope);
		adjacent.add(southernEurope);
		adjacent.add(northAfrica);
		westernEurope.setAdjacent(adjacent);
		
		// adjacent for Congo
		adjacent = new ArrayList<Territory>();
		adjacent.add(northAfrica);
		adjacent.add(eastAfrica);
		adjacent.add(southAfrica);
		congo.setAdjacent(adjacent);
		
		// adjacent for East Africa
		adjacent = new ArrayList<Territory>();
		adjacent.add(middleEast);
		adjacent.add(northAfrica);
		adjacent.add(congo);
		adjacent.add(southAfrica);
		adjacent.add(madagascar);
		eastAfrica.setAdjacent(adjacent);
		
		// adjacent for Egypt
		adjacent = new ArrayList<Territory>();
		adjacent.add(middleEast);
		adjacent.add(southernEurope);
		adjacent.add(northAfrica);
		adjacent.add(eastAfrica);
		egypt.setAdjacent(adjacent);
		
		// adjacent for Madagascar
		adjacent = new ArrayList<Territory>();
		adjacent.add(eastAfrica);
		adjacent.add(southAfrica);
		madagascar.setAdjacent(adjacent);
		
		// adjacent for North Africa
		adjacent = new ArrayList<Territory>();
		adjacent.add(westernEurope);
		adjacent.add(southernEurope);
		adjacent.add(brazil);
		adjacent.add(egypt);
		adjacent.add(eastAfrica);
		adjacent.add(congo);
		northAfrica.setAdjacent(adjacent);
		
		// adjacent for South Africa
		adjacent = new ArrayList<Territory>();
		adjacent.add(congo);
		adjacent.add(eastAfrica);
		adjacent.add(madagascar);
		southAfrica.setAdjacent(adjacent);
		
		// adjacent for Afghanistan
		adjacent = new ArrayList<Territory>();
		adjacent.add(ukraine);
		adjacent.add(ural);
		adjacent.add(middleEast);
		adjacent.add(india);
		adjacent.add(china);
		afghanistan.setAdjacent(adjacent);
		
		// adjacent for China
		adjacent = new ArrayList<Territory>();
		adjacent.add(mongolia);
		adjacent.add(siberia);
		adjacent.add(ural);
		adjacent.add(afghanistan);
		adjacent.add(india);
		adjacent.add(siam);
		china.setAdjacent(adjacent);
		
		// adjacent for India
		adjacent = new ArrayList<Territory>();
		adjacent.add(middleEast);
		adjacent.add(afghanistan);
		adjacent.add(china);
		adjacent.add(siam);
		india.setAdjacent(adjacent);
		
		// adjacent for Irkutsk
		adjacent = new ArrayList<Territory>();
		adjacent.add(kamchatka);
		adjacent.add(yakutsk);
		adjacent.add(siberia);
		adjacent.add(mongolia);
		irkutsk.setAdjacent(adjacent);
		
		// adjacent for Japan
		adjacent = new ArrayList<Territory>();
		adjacent.add(kamchatka);
		adjacent.add(mongolia);
		japan.setAdjacent(adjacent);
		
		// adjacent for Kamchatka
		adjacent = new ArrayList<Territory>();
		adjacent.add(yakutsk);
		adjacent.add(irkutsk);
		adjacent.add(mongolia);
		adjacent.add(japan);
		adjacent.add(alaska);
		kamchatka.setAdjacent(adjacent);
		
		// adjacent for Middle East
		adjacent = new ArrayList<Territory>();
		adjacent.add(ukraine);
		adjacent.add(southernEurope);
		adjacent.add(egypt);
		adjacent.add(eastAfrica);
		adjacent.add(afghanistan);
		adjacent.add(india);
		middleEast.setAdjacent(adjacent);
		
		// adjacent for Mongolia
		adjacent = new ArrayList<Territory>();
		adjacent.add(kamchatka);
		adjacent.add(irkutsk);
		adjacent.add(japan);
		adjacent.add(siberia);
		adjacent.add(china);
		mongolia.setAdjacent(adjacent);
		
		// adjacent for Siam
		adjacent = new ArrayList<Territory>();
		adjacent.add(china);
		adjacent.add(india);
		adjacent.add(indonesia);
		adjacent.add(newGuinea);
		siam.setAdjacent(adjacent);
		
		// adjacent for Siberia
		adjacent = new ArrayList<Territory>();
		adjacent.add(ural);
		adjacent.add(china);
		adjacent.add(mongolia);
		adjacent.add(irkutsk);
		adjacent.add(yakutsk);
		siberia.setAdjacent(adjacent);
		
		// adjacent for Ural
		adjacent = new ArrayList<Territory>();
		adjacent.add(ukraine);
		adjacent.add(afghanistan);
		adjacent.add(siberia);
		adjacent.add(china);
		ural.setAdjacent(adjacent);
		
		// adjacent for Yakutsk
		adjacent = new ArrayList<Territory>();
		adjacent.add(siberia);
		adjacent.add(irkutsk);
		adjacent.add(kamchatka);
		yakutsk.setAdjacent(adjacent);
		
		// adjacent for Eastern Australia
		adjacent = new ArrayList<Territory>();
		adjacent.add(newGuinea);
		adjacent.add(westernAustralia);
		easternAustralia.setAdjacent(adjacent);
		
		// adjacent for Indonesia
		adjacent = new ArrayList<Territory>();
		adjacent.add(siam);
		adjacent.add(newGuinea);
		adjacent.add(westernAustralia);
		indonesia.setAdjacent(adjacent);
		
		// adjacent for New Guinea
		adjacent = new ArrayList<Territory>();
		adjacent.add(indonesia);
		adjacent.add(westernAustralia);
		adjacent.add(easternAustralia);
		newGuinea.setAdjacent(adjacent);
		
		// adjacent for Western Australia
		adjacent = new ArrayList<Territory>();
		adjacent.add(indonesia);
		adjacent.add(easternAustralia);
		adjacent.add(newGuinea);
		westernAustralia.setAdjacent(adjacent);
		
		territories.add(alaska);
		territories.add(alberta);
		territories.add(centralAmerica);
		territories.add(easternUS);
		territories.add(greenland);
		territories.add(northwest);
		territories.add(ontario);
		territories.add(quebec);
		territories.add(westernUS);
		territories.add(argentina);
		territories.add(brazil);
		territories.add(peru);
		territories.add(venezuela);
		territories.add(greatBritain);
		territories.add(iceland);
		territories.add(northernEurope);
		territories.add(scandinavia);
		territories.add(southernEurope);
		territories.add(ukraine);
		territories.add(westernEurope);
		territories.add(congo);
		territories.add(eastAfrica);
		territories.add(egypt);
		territories.add(madagascar);
		territories.add(northAfrica);
		territories.add(southAfrica);
		territories.add(afghanistan);
		territories.add(china);
		territories.add(india);
		territories.add(irkutsk);
		territories.add(japan);
		territories.add(kamchatka);
		territories.add(middleEast);
		territories.add(mongolia);
		territories.add(siam);
		territories.add(siberia);
		territories.add(ural);
		territories.add(yakutsk);
		territories.add(easternAustralia);
		territories.add(indonesia);
		territories.add(newGuinea);
		territories.add(westernAustralia);
	}
	
	private void setUpDeck() {
		// TODO don't need cards until iteration 2
	}
	
	public void attack(Territory attackingTerritory, Territory DefendingTerritory){
		//TODO: Figure out a way to call 
		// I think Controller does or should do this -Elizabeth
		
	}
	
	public ArrayList<Territory> getTerritories() {
		return territories;
	}
	
}
