package entities;

public class StartField extends Felt {

	//Constructor
	public StartField(String feltNavn) {
		super(feltNavn);
	}

	@Override
	public void landOnField(Player player) {		
	}

	@Override
	public String getFeltBesked(Player player) {
		return null;
	}
}
