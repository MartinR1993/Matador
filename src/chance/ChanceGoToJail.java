package chance;

import desktop_resources.GUI;
import entities.Player;

public class ChanceGoToJail extends ChanceCard {

	public ChanceGoToJail(String cardName) {
		super(cardName);
	}

	@Override
	public void executeCard(Player player) {
		GUI.removeAllCars(player.getPlayerName());
		player.setCurrentField(11);
		player.isJailed = true;
		GUI.setCar(player.getCurrentField(), player.getPlayerName());		
	}

}
