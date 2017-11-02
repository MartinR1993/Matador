package test;

import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

import controllers.*;
import entities.*;

public class TestJail {
	
	//Variables
	private Player player;
	private DiceCup diceCup;
	private Dice diceOne, diceTwo;
	
	//This is where we set up the test.
	@Before
	public void setUp() throws Exception {
		diceOne = new Dice(1,1);
		diceTwo = new Dice(1,1);
		diceCup = new DiceCup(diceOne,diceTwo);
		player = new Player();
		player.getPlayerAccount().setBalance(30000);
		player.setPlayerName("Player");	
	}
	
	//Here we test that if you roll doubles three times in a row,
	//you're jailed.
	@Test
	public void test2Ens() {
		
		boolean expected2 = false;
		boolean actual = player.isJailed;
		assertEquals(expected2, actual);
		
		for (int i = 0; i < 3; i++) {
			diceCup.shake();
			player.addTurnCounter();
		}
		
		int expected = diceCup.getDiceOne();
		int expected1 = diceCup.getDiceTwo();
		assertEquals(expected, expected1);
		
	while(diceCup.getDiceOne() == diceCup.getDiceTwo() && player.getTurnCounter() < 3);
		if(player.getTurnCounter() == 3) {
			player.isJailed = true;
		}
		
		expected2 = true;
		actual = player.isJailed;
		assertEquals(expected2, actual);
	}
}