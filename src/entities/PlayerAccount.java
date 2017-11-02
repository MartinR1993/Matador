package entities;

public class PlayerAccount {

	//Attribut of the class.
	private int balance;
	private int propertyValue;

	//The contructor of PlayerAccount, which sets the the balance 
	//for the player.
	public PlayerAccount(int balance) 
	{
		if(balance < 0)
			this.balance = 0;
		else
			this.balance = balance;
	}

	//This method shows whether the player is bankrupt or not.
	public boolean isBankrupt() {
		if(this.balance <= 0)
			return true;
		else 
			return false;
	}

	//This method adjust the balance of the player.
	//if the players balance is lower than zero, then it's 
	//changed to zero, and the player is bankrupt.
	public void adjustBalance(int amount) 
	{
		if((this.balance + amount)<0)
			this.balance = 0;
		else
			this.balance = this.balance + amount;
	}

	//This method sets the balance of the players starting account.
	public void setBalance(int amount) 
	{
		if(amount < 0)
			this.balance = 0;
		else
			this.balance = amount;
	}

	public int getBalance() 
	{
		return this.balance;
	}
	
	//This method tranfers from one player to another.
	public void transfer(PlayerAccount playerAccount, int amount) {
		if((this.balance - amount) > 0) {
		adjustBalance(-amount);
		playerAccount.adjustBalance(amount);
		} else {
			
			this.balance = 0;
		}
	}
	
	//This method adjusts the total value of the player.
	public void adjustPropertyValue(int amount) {
		if(amount > 0)
			propertyValue += amount;
		else
			propertyValue -= amount;
	}
	
	public int getPropertyValue() {
		return propertyValue;
	}
}
