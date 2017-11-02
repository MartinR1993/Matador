package chance;

import controllers.GameBoard;
import desktop_resources.GUI;
import entities.Player;

public class ChanceMoveTo extends ChanceCard {

	private int moveTo;
	private GameBoard gb;

	public ChanceMoveTo(String cardName, int moveTo, GameBoard gameBoard) {
		super(cardName);
		this.moveTo = moveTo;
		this.gb = gameBoard;
	}

	@Override
	public void executeCard(Player player) {
		if(player.getCurrentField() > (moveTo)){
			if(!player.isJailed) {
				player.adjustBalance(player, 4000);
				GUI.setBalance(player.getPlayerName(), player.getBalance(player));
			}
		}
		GUI.removeAllCars(player.getPlayerName());
		player.setCurrentField(moveTo-1);
		GUI.setCar(player.getCurrentField()+1, player.getPlayerName());
		gb.getlogicFields()[player.getCurrentField()].landOnField(player);
		
	}
	
	

}
