package controllers;

import entities.Dice;

public class DiceCup 
{

	//Global variables for this class,
	//which is also called fields.
	//These private fields can only be seen in this class.	
	private Dice diceOne;
	private Dice diceTwo;

	//DiceCup class constructor
	//which takes two classes of Dice
	public DiceCup() 
	{
		this.diceOne = new Dice();
		this.diceTwo = new Dice();
	}
	
	public DiceCup(Dice diceOne, Dice diceTwo){
		this.diceOne = diceOne;
		this.diceTwo = diceTwo;
	}

	//This Shake method is used to randomly choose
	//a number between the numbers of the dice.
	//Shake method returns a Dice[] => which is a Dice array.
	public void shake()
	{
		diceOne.roll();
		diceTwo.roll();
	}

	public int getSumResult()
	{
		int sum = diceOne.getValue()+ diceTwo.getValue(); 
		return sum;
	}

	public int getDiceOne() 
	{
		return diceOne.getValue();
	}

	public int getDiceTwo() 
	{
		return diceTwo.getValue();
	}
}