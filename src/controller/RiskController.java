package controller;

import java.util.ArrayList;

import model.*;

public class RiskController {
	private ArrayList<Territory> territories;
	private ArrayList<Player> players;
	private ArrayList<Card> deckOfCards;
	
	public RiskController() {
		
	}
	
	public void addPlayer(Player newPlayer) {
		
	}
	
	public void populateBoard() {
		
	}
	
	public void playGame() {
		
	}
	
	private void setUpTerritories() {
		Territory alaska = new Territory("Alaska", Continent.NORTH_AMERICA);
		Territory alberta = new Territory("Alberta", Continent.NORTH_AMERICA);
		Territory centralAmerica = new Territory("Central America", Continent.NORTH_AMERICA);
		Territory easternUS = new Territory("Eastern United States", Continent.NORTH_AMERICA);
		Territory greenland = new Territory("Greenland", Continent.NORTH_AMERICA);
		Territory northwest = new Territory("Northwest Territory", Continent.NORTH_AMERICA);
		Territory ontario = new Territory("Ontario", Continent.NORTH_AMERICA);
		Territory quebec = new Territory("Quebec", Continent.NORTH_AMERICA);
		Territory westernUS = new Territory("Western United States", Continent.NORTH_AMERICA);
		
		Territory argentina = new Territory("Argentina", Continent.SOUTH_AMERICA);
		Territory brazil = new Territory("Brazil", Continent.SOUTH_AMERICA);
		Territory peru = new Territory("Peru", Continent.SOUTH_AMERICA);
		Territory venezuela = new Territory("Venezuela", Continent.SOUTH_AMERICA);
		
		Territory greatBritain = new Territory("Great Britain", Continent.EUROPE);
		Territory iceland = new Territory("Iceland", Continent.EUROPE);
		Territory northernEurope = new Territory("Northern Europe", Continent.EUROPE);
		Territory scandinavia = new Territory("Scandinavia", Continent.EUROPE);
		Territory southernEurope = new Territory("Southern Europe", Continent.EUROPE);
		Territory ukraine = new Territory("Ukraine", Continent.EUROPE);
		Territory westernEurope = new Territory("Western Europe", Continent.EUROPE);
		
		Territory congo = new Territory("Congo", Continent.AFRICA);
		Territory eastAfrica = new Territory("East Africa", Continent.AFRICA);
		Territory egypt = new Territory("Egypt", Continent.AFRICA);
		Territory madagascar = new Territory("Madagascar", Continent.AFRICA);
		Territory northAfrica = new Territory("North Africa", Continent.AFRICA);
		Territory southAfrica = new Territory("South Africa", Continent.AFRICA);
		
		Territory afghanistan = new Territory("Afghanistan", Continent.ASIA);
		Territory china = new Territory("China", Continent.ASIA);
		Territory india = new Territory("India", Continent.ASIA);
		Territory irkutsk = new Territory("Irkutsk", Continent.ASIA);
		Territory japan = new Territory("Japan", Continent.ASIA);
		Territory kamchatka = new Territory("Kamchatka", Continent.ASIA);
		Territory middleEast = new Territory("Middle East", Continent.ASIA);
		Territory mongolia = new Territory("Mongolia", Continent.ASIA);
		Territory siam = new Territory("Siam", Continent.ASIA);
		Territory siberia = new Territory("Siberia", Continent.ASIA);
		Territory ural = new Territory("Ural", Continent.ASIA);
		Territory yakutsk = new Territory("Yakutsk", Continent.ASIA);
		
		Territory easternAustralia = new Territory("Eastern Australia", Continent.AUSTRALIA);
		Territory indonesia = new Territory("Indonesia", Continent.AUSTRALIA);
		Territory newGuinea = new Territory("New Guinea", Continent.AUSTRALIA);
		Territory westernAustralia = new Territory("Western Australia", Continent.AUSTRALIA);
		
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
		
		// adjacent for China
		
		// adjacent for India
		
		// adjacent for Irkutsk
		
		// adjacent for Japan
		
		// adjacent for Kamchatka
		
		// adjacent for Middle East
		
		// adjacent for Mongolia
		
		// adjacent for Siam
		
		// adjacent for Siberia
		
		// adjacent for Ural
		
		// adjacent for Yakutsk
		
		// adjacent for Eastern Australia
		
		// adjacent for Indonesia
		
		// adjacent for New Guinea
		
		// adjacent for Western Australia
		
	}
	
	private void setUpDeck() {
		// Don't need cards until iteration 2
	}
}
