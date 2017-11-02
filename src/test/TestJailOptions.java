package test;

import static org.junit.Assert.*;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import controllers.*;
import desktop_resources.GUI;
import entities.*;

public class TestJailOptions {

	//Variables
	private Player player;
	private GameBoard gb;
	private DiceCup diceCup;
	private ResourceBundle rb;
	private LanguageSelector ls;
	private GoToJail gtj;
	private String jailedOption;
	private Dice diceOne, diceTwo;

	//This is where we set up the test.
	@Before
	public void setUp() throws Exception {
		diceOne = new Dice(1,1);
		diceTwo = new Dice(1,1);
		diceCup = new DiceCup(diceOne,diceTwo);
		ls = new LanguageSelector("da", "DK");
		gb = new GameBoard(diceCup, ls);
		rb = ls.selectLanguage("da", "DK");
		player = new Player();
		player.getPlayerAccount().setBalance(30000);
		player.setPlayerName("Player");
		gtj = new GoToJail("De Fængsles", gb, rb);
	}

	//The first test.
	//Here we test that you go to jail when you land on
	//the GoToJail field.
	@Test
	public void testGoToJail() {
		boolean expected = false;
		boolean actual = player.isJailed;
		assertEquals(expected, actual);

		gtj.landOnField(player);

		expected = true;
		actual = player.isJailed;
		assertEquals(expected, actual);
	}

	//The second test.
	//This test shows the pay opportunity when a player is jailed,
	//we test that the players account is adjusted with 1000, 
	//which are the price to go out of jail.
	@Test
	public void testJailOptionPay() {

		player.isJailed = true;

		int expected1 = 30000;
		int actual1 = player.getBalance(player);
		assertEquals(expected1, actual1);

		jailedOption = GUI.getUserButtonPressed(player.getPlayerName()+", "+rb.getString("Jail3"), rb.getString("Jail5"));

		if(jailedOption.equals(rb.getString("Jail5"))) {
			player.getPlayerAccount().adjustBalance(-1000);
			GUI.setBalance(player.getPlayerName(), player.getPlayerAccount().getBalance());
			player.isJailed = false;
		}

		boolean expected = false;
		boolean actual = player.isJailed;
		assertEquals(expected, actual);

		expected1 = 29000;
		actual1 = player.getBalance(player);
		assertEquals(expected1, actual1);
	}
	//Third test.
	//Test the diceroll opportunity.
	@Test
	public void testJailOptionDiceRoll() {

		player.isJailed = true;

		jailedOption = GUI.getUserButtonPressed(player.getPlayerName()+", "+rb.getString("Jail3"), rb.getString("Jail6"));

		if(jailedOption.equals(rb.getString("Jail6"))){
			diceCup.shake();
			GUI.setDice(diceCup.getDiceOne(), diceCup.getDiceTwo());

			if(diceCup.getDiceOne() == diceCup.getDiceTwo()){
				GUI.showMessage(player.getPlayerName()+", "+rb.getString("Jail8"));
				player.isJailed = false;
			}
		}
		boolean expected = false;
		boolean actual = player.isJailed;
		assertEquals(expected, actual);
	}
	//Fourth test.
	//Shows the freecard opportunity works.
	@Test
	public void testJailOptionFreeCard() {

		player.isJailed = true;
		player.addFreeCard();
		
		int expected1 = 1;
		int actual1 = player.getFreeCardCounter();
		assertEquals(expected1, actual1);

		jailedOption = GUI.getUserButtonPressed(player.getPlayerName()+", "+rb.getString("Jail3"), rb.getString("Jail7"));

		if(jailedOption.equals(rb.getString("Jail7"))) {
			player.useFreeCard();
			player.isJailed = false;
		}

		expected1 = 0;
		actual1 = player.getFreeCardCounter();
		assertEquals(expected1, actual1);
		
		boolean expected = false;
		boolean actual = player.isJailed;
		assertEquals(expected, actual);	
	}
}