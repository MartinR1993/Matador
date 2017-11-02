package entities;

import java.util.ResourceBundle;

import controllers.GameBoard;
import desktop_resources.GUI;

public class Jail extends Felt {
	
	//Variable
	private ResourceBundle rb;

	//Constructor
	public Jail(String feltNavn, GameBoard gameBoard, ResourceBundle rb) {
		super(feltNavn);
		this.rb = rb;
	}

	//Shows a message when a player lands on this field.
	@Override
	public void landOnField(Player player) {
		GUI.showMessage(getFeltBesked(player));
	}

	//Makes the String which is shown i landOnField
	@Override
	public String getFeltBesked(Player player) {
		return player.getPlayerName()+", "+rb.getString("Jail");
	}
}