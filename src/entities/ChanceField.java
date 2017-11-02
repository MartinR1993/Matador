package entities;

public class ChanceField extends Felt {

	//Constructor
	public ChanceField(String feltNavn) {
		super(feltNavn);
	}

	@Override
	public void landOnField(Player player) {	
	}

	//Takes the String from MessageBundle
	@Override
	public String getFeltBesked(Player player) {
		return "PrøvLykken";
	}
}