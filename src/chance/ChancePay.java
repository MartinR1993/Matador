package chance;

import desktop_resources.GUI;
import entities.Player;

public class ChancePay extends ChanceCard {

	private int amount;

	public ChancePay(String cardName, int amount) {
		super(cardName);
		this.amount = amount;
	}

	@Override
	public void executeCard(Player player) {
		player.adjustBalance(player, -amount);
		GUI.setBalance(player.getPlayerName(), player.getBalance(player));		
	}

}
