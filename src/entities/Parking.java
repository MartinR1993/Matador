package entities;

import java.util.ResourceBundle;
import desktop_resources.GUI;

public class Parking extends Felt {
	
	//Variables
	private ResourceBundle rb;

	//Constructor
	public Parking(String feltNavn, ResourceBundle rb) {
		super(feltNavn);
		this.rb = rb;
	}
	
	//landOnField method which shows a message.
	@Override
	public void landOnField(Player player) {
		GUI.showMessage(getFeltBesked(player));
	}
	
	//This method makes the String.
	@Override
	public String getFeltBesked(Player player) {
		return player.getPlayerName()+", " +rb.getString("Park");
	}
}
