package model;

public class HumanPlayer extends Player{

	

	@Override
	public void placeArmy() {
		// choose one of your territories and place one army there
	}

	@Override
	public Territory attackFrom() {
		// choose one of your territories with two or more armies to attack from
		// if you no longer want to attack, return null
		return null;
	}

	@Override
	public Territory attackTo(Territory attackFrom) {
		// TODO Auto-generated method stub
		// choose another player's territory adjacent to attackFrom
		return null;
	}



	@Override
	public void reinforceArmies(Territory takeArmy, Territory reinforceThis) {
		// TODO Auto-generated method stub
		
	}

}
