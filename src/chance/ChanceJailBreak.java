package chance;

import entities.Player;

public class ChanceJailBreak extends ChanceCard {

	public ChanceJailBreak(String cardName) {
		super(cardName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeCard(Player player) {
		player.addFreeCard();
		
	}

}
