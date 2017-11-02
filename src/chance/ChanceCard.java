package chance;


import entities.Player;

public abstract class ChanceCard {
	
	private String cardName;

	public ChanceCard(String cardName) {
		this.cardName = cardName;
	}
	
	public String toString() {
		return cardName;
	}
	
	public abstract void executeCard(Player player);

}
