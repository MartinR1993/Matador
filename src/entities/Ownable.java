package entities;

import controllers.GameBoard;

public abstract class Ownable extends Felt{
	
	//This is an abstract class that extends from the abstract Felt class.
	//the class is abstract because other classes have to extend
	//from this class. Its the body of all classes, that are ownable.
	protected Player owner;
	
	public Ownable(int price, String feltNavn, GameBoard gb) {
		super(feltNavn);
		this.owner = null;
	}
	
	//Abstract methods. 
	//These methods are being overrided in the subclasses.
	public abstract int getRent(Player player);
	
	public abstract void setOwner(Player player);
	
	public abstract Player getOwner();
	
	public abstract void buyFieldOption(Player player);

	public void landOnField(Player player) {
		getFeltBesked(player);
		if(!player.equals(owner)){
			if(owner == null)
				buyFieldOption(player);
			else
				getRent(player);
		}
	}
	
	public abstract String getFeltBesked(Player player);
	
	public abstract int getPrice();

}
