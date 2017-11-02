package entities;

import desktop_resources.GUI;

import java.util.ResourceBundle;

import controllers.GameBoard;

public class Territory extends Ownable {

	//Global variables of this class,
	//which also called fields.
	//This private fields can only be seen in this class.
	private TerritoryData td;

	//The Territory constructor takes eight parameters.
	public Territory(int price, String feltNavn, GameBoard gb, ResourceBundle rb, String fieldColour, int fieldNumber, int housePrice, int[] rents) {
		super(price, feltNavn, gb);
		this.td = new TerritoryData(owner, rb, price, gb, feltNavn, fieldColour, fieldNumber, housePrice, rents);
	}

	@Override
	public int getRent(Player player) {
		return td.getRent();
	}

	//This method makes the text, that are being showed in the GUI
	//when a player lands on the Territory fields.
	@Override
	public String getFeltBesked(Player player) {
		if(owner == null){
			return player.getPlayerName()+", "+getResourceBundle().getString("Owned")+" "+getFeltNavn()+"."; 
		}

		else if (owner.getPlayerName().equalsIgnoreCase(player.getPlayerName()))
			return player.getPlayerName()+", "+getResourceBundle().getString("Owned4");

		else if (owner.getPlayerAccount().isBankrupt() == true)
			return player.getPlayerName()+", "+getResourceBundle().getString("Owned")+" "+getFeltNavn()+", "+getResourceBundle().getString("Owned1")+" "+owner.getPlayerName()+
					", "+ getResourceBundle().getString("Owned2")+" "+owner.getPlayerName()+" "+ getResourceBundle().getString("Bankrupt");

		else{
			if(getHouseCounter()==0)
				return player.getPlayerName()+", "+getResourceBundle().getString("Owned")+" "+getFeltNavn()+", "+getResourceBundle().getString("Owned1")+" "+owner.getPlayerName()+"\n"+ getResourceBundle().getString("Owned3")+" "+getRents(player)[getHouseCounter()]+" kr.";
			else if(getHouseCounter()==1)
				return player.getPlayerName()+", "+getResourceBundle().getString("Owned")+" "+getFeltNavn()+", "+ getResourceBundle().getString("Owned6")+" "+getHouseCounter()+" "+getResourceBundle().getString("Owned7")+" "+getResourceBundle().getString("Owned1")+" "+owner.getPlayerName()+"\n"+ getResourceBundle().getString("Owned3")+" "+getRents(player)[getHouseCounter()]+" kr.";
			else if(getHouseCounter()>1 && getHouseCounter()<5)
				return player.getPlayerName()+", "+getResourceBundle().getString("Owned")+" "+getFeltNavn()+", "+ getResourceBundle().getString("Owned6")+" "+getHouseCounter()+" "+getResourceBundle().getString("Owned8")+" "+getResourceBundle().getString("Owned1")+" "+owner.getPlayerName()+"\n"+ getResourceBundle().getString("Owned3")+" "+getRents(player)[getHouseCounter()]+" kr.";
			else 
				return player.getPlayerName()+", "+getResourceBundle().getString("Owned")+" "+getFeltNavn()+", "+ getResourceBundle().getString("Owned6")+" "+getHotelCounter()+" "+getResourceBundle().getString("Owned9")+" "+getResourceBundle().getString("Owned1")+" "+owner.getPlayerName()+"\n"+ getResourceBundle().getString("Owned3")+" "+getRents(player)[getHouseCounter()]+" kr.";
		}
	}

	@Override
	public void setOwner(Player player) {
		td.setOwner(player);
	}

	@Override
	public Player getOwner() {	
		return td.getOwner();
	}

	//This method is used when a player lands on a Territory field.
	//If the field is not owned by an player, then the player have the buyFieldOption,
	//but if the field is owned, then the player have to pay the rent to the owner.
	@Override
	public void landOnField(Player player) {
		GUI.showMessage(getFeltBesked(player));
		if(owner == null) {
			buyFieldOption(player);
		}
		else  if (owner.isBankrupt(player) == true){

		} else {
			player.getPlayerAccount().transfer(owner.getPlayerAccount(), getRents(player)[getHouseCounter()]);	
			GUI.setBalance(player.getPlayerName(), player.getBalance(player));
			GUI.setBalance(owner.getPlayerName(), owner.getBalance(owner));
		}
	}

	//This method gives the player the opportunity to buy the Territory field,
	//the given player has landed on.
	@Override
	public void buyFieldOption(Player player) {
		String buy = GUI.getUserButtonPressed(getResourceBundle().getString("Købe")+" "+getPrice()+" kr.?", getResourceBundle().getString("Ja"),getResourceBundle().getString("Nej"));
		if(buy.equals(getResourceBundle().getString("Ja"))) {
			GUI.setOwner(player.getCurrentField()+1, player.getPlayerName());
			player.adjustBalance(player, -getPrice());
			player.adjustPropertyValue(player, getPrice());
			GUI.setBalance(player.getPlayerName(), player.getBalance(player));
			this.owner = player;
			player.addProperty(this);
			
			if (getColour() == "blue") {
				player.addBlueTerritoryCounter();
			}
			else if (getColour() == "pink") {
				player.addPinkTerritoryCounter();
			}
			else if (getColour() == "green") {
				player.addGreenTerritoryCounter();
			}
			else if (getColour()== "gray") {
				player.addGrayTerritoryCounter();
			}
			else if (getColour() == "red") {
				player.addRedTerritoryCounter();
			}
			else if (getColour() == "white") {
				player.addWhiteTerritoryCounter();
			}
			else if (getColour() == "yellow") {
				player.addYellowTerritoryCounter();
			}
			else {
				player.addMagentaTerritoryCounter();
			}
			player.addHouseList();
			getGb().getGUIFields()[player.getCurrentField()].setSubText(getResourceBundle().getString("Ejer")+" "+ player.getPlayerName());
		}
	}

	//This method sets the houses/hotel on the player and the territory, it also adjust
	//the money from the players account.
	public void buyHouse(Player player) {
		if(getHouseCounter()==4){
			td.addHotelCounter();
			player.addHotelCounter();
		}
		if(getHouseCounter()==5){
			td.addHouseCounter();
			player.addHouseCounter();
			player.adjustBalance(player, -getHousePrice());
			player.adjustPropertyValue(player, getPrice());
		}
		else{
			player.adjustBalance(player, -getHousePrice());
			td.addHouseCounter();
			player.addHouseCounter();
			player.adjustPropertyValue(player, getPrice());
		}
		removeFromHouseList(player);
	}
	
	//This method removes the territory from the HusListe when the player has more than 4
	//houses on it.
	public void removeFromHouseList(Player player) {
		if(getHouseCounter() > 4) {
			player.removeString(getFeltNavn());
		}	
	}

	@Override
	public int getPrice() {
		return td.getPrice();
	}
	public int getFieldNumber(){
		return td.getFieldNumber();
	}
	public int getHousePrice() {
		return td.getHousePrice();
	}
	public int getHouseCounter(){
		return td.getHouseCounter();
	}
	public int getHotelCounter(){
		return td.getHotelCounter();
	}
	public String getFeltNavn() {
		return td.getFeltNavn();
	}
	public ResourceBundle getResourceBundle() {
		return td.getResourceBundle();
	}
	public GameBoard getGb() {
		return td.getGb();
	}
	public String getColour() {
		return td.getColour();
	}
	public int[] getRents(Player player){
		return td.getRents();
	}

	//We made this class so we could split the territory class into a controller class
	//and an entity class.
	private class TerritoryData {

		//Global variables of this class,
		//which also called fields.
		//This private fields can only be seen in this class.
		private int rent;
		private int houseCounter, hotelCounter;
		private Player owner;
		private ResourceBundle rb;
		private int price;
		private GameBoard gb;
		private String feltNavn;
		private String colour;
		private int fieldNumber;
		private int housePrice;
		private int[] rents;

		//The TerritoryData constructor.
		private TerritoryData(Player owner, ResourceBundle rb, int price, GameBoard gb, String feltNavn, String fieldColour, int fieldNumber, int housePrice, int[] rents) {
			this.owner = owner;
			this.rb = rb;
			this.price = price;
			this.gb = gb;
			this.feltNavn = feltNavn;
			this.colour = fieldColour;
			this.fieldNumber = fieldNumber;
			this.housePrice = housePrice;
			this.rents = rents;
			houseCounter = 0;
			hotelCounter = 0;
		}
		public int getHotelCounter() {
			return hotelCounter;
		}
		public void addHotelCounter(){
			hotelCounter++;
		}
		private String getFeltNavn() {
			return feltNavn;
		}
		private Player getOwner() {
			return owner;
		}
		private void setOwner(Player owner) {
			this.owner = owner;
		}
		private int getRent() {
			return rent;
		}
		private int getHouseCounter() {
			return houseCounter;
		}
		private int getPrice() {
			return price;
		}
		private int[] getRents() {
			return rents;
		}
		private int getHousePrice() {
			return housePrice;
		}
		private String getColour() {
			return colour;
		}
		private ResourceBundle getResourceBundle() {
			return rb;
		}
		private GameBoard getGb() {
			return gb;
		}
		private int getFieldNumber(){
			return fieldNumber;
		}
		private void addHouseCounter() {
			houseCounter++;
		}
	}
}