package entities;

public class Dice 
{
	//Global variables which also called fields
	private int value; //Used to keep track of the dice value.
	private int maxValue;//The maximum the dice may be able to roll.
	private int minValue;//The minimum the dice may be able to roll.


	//The Dice constructor, which sets the 
	//max and min value for the dice.
	public Dice()
	{
		this.maxValue=6;
		this.minValue=1;
	}

	//The second Dice constructor, which can set the 
	//max and min to other values. 
	public Dice(int maxValue, int minValue) 
	{
		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	//The roll method, randomly generates
	//a number between the max and min.
	public int roll() 
	{
		value =(int)(Math.random()* maxValue + minValue);
		return value;
	}

	public int getValue() 
	{
		return value;
	}
}