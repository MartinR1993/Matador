package entities;

import java.util.ResourceBundle;

import desktop_resources.GUI;
import entities.Player;

public class Tax extends Felt {

	//Global variables of this class,
	//which also called fields.
	//This private fields can only be seen in this class.
	private String Tax;
	private int Pay, taxAmount;
	private Player player;
	private ResourceBundle rb;

	//The tax Constructor
	public Tax(int tax, String feltNavn, ResourceBundle rb) {
		super(feltNavn);
		taxAmount = tax;
		this.rb = rb;
	}

	//The overrided landOnField method which shows the message,
	//and calls the payTax() method.
	@Override
	public void landOnField(Player player) {
		this.player = player;
		GUI.showMessage(getFeltBesked(player));
		payTax();
	}

	//The payTax method which calculate the tax depending on 
	//which field you lands on.
	private void payTax() {
		if(player.getCurrentField() == 4){
			Tax = GUI.getUserButtonPressed(rb.getString("Rent") +" "+ taxAmount +" "+ rb.getString("Rent1"), rb.getString("Pay")+" "+taxAmount+" kr.", rb.getString("Pay1"));
			if(Tax.equals(rb.getString("Pay")+" "+taxAmount+" kr.")){
			Pay = 4000;
			player.getPlayerAccount().adjustBalance(-Pay);
			GUI.setBalance(player.getPlayerName(), player.getPlayerAccount().getBalance());
			}
			else {
				player.getPlayerAccount().adjustBalance(-tenPercent(player));
				GUI.setBalance(player.getPlayerName(), player.getPlayerAccount().getBalance());
			}
		}
		else {
			Tax = GUI.getUserButtonPressed(rb.getString("Rent2"), rb.getString("Pay")+" "+taxAmount+" kr.");
			Pay = 2000;
			player.getPlayerAccount().adjustBalance(-Pay);
			GUI.setBalance(player.getPlayerName(), player.getPlayerAccount().getBalance());	
		}
	}

	//Calculates 10 percent of the total value of the player.
	private int tenPercent(Player player) {
		return (player.getBalance(player)+player.getPropertyValue(player))/10;
	}

	//This method makes the text, that are being showed in the GUI
	//when a player lands on the Tax fields.
	@Override
	public String getFeltBesked(Player player) {
		if(player.getCurrentField() == 4)
		return player.getPlayerName()+", "+rb.getString("Tax1");
		else
			return player.getPlayerName()+", "+rb.getString("Tax2");
	}
}
