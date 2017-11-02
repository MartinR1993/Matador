package chance;

import desktop_resources.GUI;
import entities.Player;

public class ChancePayPerProperty extends ChanceCard {

	private int amountPerHouse;
	private int amountPerHotel;
	private int hotelCounter;
	private int houseCounter;

	public ChancePayPerProperty(String cardName, int amountPerHouse, int amountPerHotel) {
		super(cardName);
		this.amountPerHouse = amountPerHouse;
		this.amountPerHotel = amountPerHotel;
		
	}

	@Override
	public void executeCard(Player player) {
		houseCounter = player.getHouseCounter();
		hotelCounter = player.getHotelCounter();
		player.adjustBalance(player, -(houseCounter*amountPerHouse+hotelCounter*amountPerHotel));
		GUI.setBalance(player.getPlayerName(), player.getBalance(player));
	}

}
