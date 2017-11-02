package test;

import static org.junit.Assert.*;

import java.util.ResourceBundle;

import org.junit.Before;
import org.junit.Test;

import controllers.GameBoard;
import entities.LanguageSelector;
import entities.Player;
import controllers.DiceCup;
import entities.Territory;

public class TestBuyHouse {

	//Variables
	private Player player;
	private GameBoard gb;
	private DiceCup diceCup;
	private Territory territory1;
	private ResourceBundle rb;
	private LanguageSelector ls;
	
	//This is where we set up the test.
	@Before
	public void setUp() throws Exception {
		ls = new LanguageSelector("da", "DK");
		gb = new GameBoard(diceCup, ls);
		rb = ls.selectLanguage("da", "DK");
		player = new Player();
		player.getPlayerAccount().setBalance(30000);
		player.setPlayerName("Player");
		territory1 = new Territory(1200, "Rødovrevej", gb, rb, "blue",2, 1000, new int[] {50,250,750,2250,4000,6000});
	}
	
	//Test 1.
	//Here we test that you can buy a house, and that the housecounter,
	//on the territory works, and that the price is adjusted from the players account.
	@Test
	public void testBuyHouse() {
		int expected = 30000;
		int actual = player.getBalance(player);
		assertEquals(expected, actual);
		
		territory1.setOwner(player);
		territory1.buyHouse(player);
		
		expected = 29000;
		actual = player.getBalance(player);
		assertEquals(expected, actual);
		
		int houseExpected = 1;
		int houseActual = territory1.getHouseCounter();
		assertEquals(houseExpected, houseActual);
	}
	//Test 2. 
	//Test that you can buy a hotel, and the hotelcounter on the territory works.
	@Test
	public void testBuyHotel() {
		int expected = 30000;
		int actual = player.getBalance(player);
		assertEquals(expected, actual);
		
		territory1.setOwner(player);
		
		//5 houses = 1 hotel
		territory1.buyHouse(player);
		territory1.buyHouse(player);
		territory1.buyHouse(player);
		territory1.buyHouse(player);
		territory1.buyHouse(player);
		
		expected = 25000;
		actual = player.getBalance(player);
		assertEquals(expected, actual);
		
		int hotelExpected = 1;
		int hotelActual = territory1.getHotelCounter();
		assertEquals(hotelExpected, hotelActual);
	}
}