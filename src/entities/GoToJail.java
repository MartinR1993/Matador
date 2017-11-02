package entities;

import java.util.ResourceBundle;

import controllers.GameBoard;
import desktop_resources.GUI;

public class GoToJail extends Felt {

	//variable
	private ResourceBundle rb;

	//Constructor for this class.
	public GoToJail(String feltNavn, GameBoard gameBoard, ResourceBundle rb) {
		super(feltNavn);
		this.rb = rb;
	}

	//Sets the player in the Jail field and makes him Jailed.
	private void goToJail(Player player) {
		player.isJailed = true;
		player.setCurrentField(10);
		GUI.removeAllCars(player.getPlayerName());
		GUI.setCar(player.getCurrentField()+1, player.getPlayerName());	
	}

	//Shows a message when a player lands on the field 
	@Override
	public void landOnField(Player player) {
		GUI.showMessage(getFeltBesked(player));
		goToJail(player);
	}

	//Makes a String
	@Override
	public String getFeltBesked(Player player) {
		return player.getPlayerName()+", "+rb.getString("Jail2");
	}
}