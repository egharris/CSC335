package model;

import java.util.Random;
/**
 * object of a die
 * @author jeremyjalnos StevenBroussard
 *
 */
public class Dice implements Comparable<Dice>{
	
	//instance variable
	private int diceValue;
	
	//"roll" dice
	static Random randomDice;
	
	/**
	 * constructor to initialize the instance variables
	 */
	public Dice (){
		randomDice = new Random();
		diceValue = 0;
	}
	
	/**
	 * rolls a die
	 * @return random value of die
	 */
	public int rollDice() {
		return randomDice.nextInt(5) + 1;
	}
	
	/*public void rollDice(){
		diceValue = randomDice.nextInt(5) + 1;
	}*/
	
	//get dice value
	
	/**
	 * to get the value of the die
	 * @return diceValue
	 */
	public int getDiceValue(){
		return diceValue;
	}

	@Override
	public int compareTo(Dice o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
