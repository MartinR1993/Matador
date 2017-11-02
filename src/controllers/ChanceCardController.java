package controllers;

import java.util.Random;

import chance.ChanceCard;

public class ChanceCardController {

	private ChanceCard[] chanceCards;
	private Random generator;

	public ChanceCardController(ChanceCard[] cc) {
		this.chanceCards = cc;
		generator = new Random();
	}

	public ChanceCard drawCard() {
		ChanceCard drawn;
		drawn = chanceCards[0];
		for(int i=0;i<chanceCards.length-1;i++){
			chanceCards[i]=chanceCards[i+1];
		}
		chanceCards[chanceCards.length-1] = drawn;
		return drawn;
	}
	
	public void shuffle() {
		for(int i = 0; i < chanceCards.length; i++) {
			int j = generator.nextInt(chanceCards.length);
			ChanceCard tmp = chanceCards[i];
			chanceCards[i] = chanceCards[j];
			chanceCards[j] = tmp;
		}
	}

}
