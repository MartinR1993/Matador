package chance;

import entities.Player;
/************************************************************************
 * @author Mikkel Hansen												*
 * 																		*
 * Denne klasse bliver ikke brugt, da jeg ikke kunne					*
 *  få den til at virke ordenligt										*
 *																		*
 ************************************************************************/
public class ChanceRecieveFromAll extends ChanceCard {
	
	private int amount;
	private Player[] players;

	public ChanceRecieveFromAll(String cardName, int amount, Player[] players) {
		super(cardName);
		this.amount = amount;
		this.players = players;
	}

	@Override
	public void executeCard(Player player) {
		for(int i = 0; i < players.length; i++) {
			if(!players[i].equals(player)) {
				players[i].transfer(players[i], player, amount);
			}
		}

	}

}
