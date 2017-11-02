package chance;

import java.util.ResourceBundle;

import controllers.ChanceCardController;
import controllers.GameBoard;
import desktop_resources.GUI;
import entities.ChanceField;
import entities.Felt;
import entities.Player;

public class ChanceMove extends ChanceCard {

	private int antalFelter;
	private GameBoard gb;
	private ChanceCard chanceCard;
	private ResourceBundle rb;
	private ChanceCardController ccc;

	public ChanceMove(String cardName, int antalFelter, GameBoard gameBoard, ChanceCardController ccc, ResourceBundle rb) {
		super(cardName);
		this.antalFelter = antalFelter;
		this.gb = gameBoard;
		this.ccc = ccc;
		this.rb = rb;
	}

	@Override
	public void executeCard(Player player) {
		GUI.removeAllCars(player.getPlayerName());
		if(player.getCurrentField()+antalFelter > 0) {
			Felt currentField = gb.getlogicFields()[player.getCurrentField()+antalFelter];


			if(currentField instanceof ChanceField) {
				player.setCurrentField(player.getCurrentField()+antalFelter);
				GUI.setCar(player.getCurrentField()+1, player.getPlayerName());
				System.out.println(rb);
				GUI.showMessage(rb.getString(gb.getlogicFields()[player.getCurrentField()].getFeltBesked(player)));
				chanceCard = ccc.drawCard();
				GUI.showMessage(rb.getString(chanceCard.toString()));
				chanceCard.executeCard(player);
			} else 	{
				player.setCurrentField(player.getCurrentField()+antalFelter);
				GUI.setCar(player.getCurrentField()+1, player.getPlayerName());
			}
		} else if(player.getCurrentField() < 4) {
			player.setCurrentField(39);
			GUI.setCar(player.getCurrentField()+1, player.getPlayerName());
		}
		gb.getlogicFields()[player.getCurrentField()].landOnField(player);
	}
}
