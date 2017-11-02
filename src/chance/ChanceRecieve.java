package chance;

import desktop_resources.GUI;
import entities.Player;

public class ChanceRecieve extends ChanceCard {

	private int amount;

	public ChanceRecieve(String cardName, int amount) {
		super(cardName);
		this.amount = amount;
	}

	@Override
	public void executeCard(Player player) {
		player.adjustBalance(player, amount);
		GUI.setBalance(player.getPlayerName(), player.getBalance(player));		
	}

}
