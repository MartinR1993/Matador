package entities;

import java.util.ArrayList;
import entities.Territory;

public class Player 
{
	//Global variables of this class,
	//which also called fields.
	//This private fields can only be seen in this class.
	private String playerName;
	private PlayerAccount playerAccount;
	private int currentField=1, fleetCounter,breweryCounter,jailRollCounter,freeCardCounter, blueTerritoryCounter, pinkTerritoryCounter, 
			greenTerritoryCounter, grayTerritoryCounter, redTerritoryCounter, whiteTerritoryCounter, yellowTerritoryCounter, magentaTerritoryCounter;
	public boolean isJailed;
	private int hotelCounter;
	private int houseCounter;
	private ArrayList<Territory> OwnedProperties;
	private ArrayList<String> buildingReadyFields;
	public int turnCounter;

	//The constructor, which sets different counters and makes the account. 
	//Also adds two arraylists.
	public Player() 
	{
		OwnedProperties = new ArrayList<Territory>();
		buildingReadyFields = new ArrayList<String>();
		playerAccount = new PlayerAccount(30000);
		fleetCounter = 0;
		breweryCounter = 0;
		houseCounter = 0;
		hotelCounter = 0;
		jailRollCounter = 0;
		freeCardCounter = 0;
		blueTerritoryCounter = 0;
		pinkTerritoryCounter = 0;
		greenTerritoryCounter = 0;
		grayTerritoryCounter = 0;
		redTerritoryCounter = 0;
		whiteTerritoryCounter = 0;
		yellowTerritoryCounter = 0;
		magentaTerritoryCounter = 0;
	}

	//Setter method for setting player name.
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}
	public void addTurnCounter() {
		turnCounter++;
	}
	public int getTurnCounter() {
		return turnCounter;
	}
	//It simply returns the player name
	public String getPlayerName()
	{
		return playerName;
	}
	public PlayerAccount getPlayerAccount()
	{
		return playerAccount;
	}
	public int getCurrentField() {
		return currentField;
	}
	public void setCurrentField(int currentField) {
		this.currentField = currentField;
	}
	public void addBlueTerritoryCounter() {
		blueTerritoryCounter++;
	}
	public int getBlueTerritoryCounter() {
		return blueTerritoryCounter;
	}
	public void addPinkTerritoryCounter() {
		pinkTerritoryCounter++;
	}
	public int getPinkTerritoryCounter() {
		return pinkTerritoryCounter;
	}
	public void addGreenTerritoryCounter() {
		greenTerritoryCounter++;
	}
	public int getGreenTerritoryCounter() {
		return greenTerritoryCounter;
	}
	public void addGrayTerritoryCounter() {
		grayTerritoryCounter++;
	}
	public int getGrayTerritoryCounter() {
		return grayTerritoryCounter;
	}
	public void addRedTerritoryCounter() {
		redTerritoryCounter++;
	}
	public int getRedTerritoryCounter() {
		return redTerritoryCounter;
	}
	public void addWhiteTerritoryCounter() {
		whiteTerritoryCounter++;
	}
	public int getWhiteTerritoryCounter() {
		return whiteTerritoryCounter;
	}
	public void addYellowTerritoryCounter() {
		yellowTerritoryCounter++;
	}
	public int getYellowTerritoryCounter() {
		return yellowTerritoryCounter;
	}
	public void addMagentaTerritoryCounter() {
		magentaTerritoryCounter++;
	}
	public int getMagentaTerritoryCounter() {
		return magentaTerritoryCounter;
	}


	public void addFleetCounter() {
		fleetCounter++;
	}
	public int getFleetCounter(Player player) {
		return fleetCounter;
	}
	public void addBreweryCounter() {
		breweryCounter++;
	}
	public int getBreweryCounter(Player player) {
		return breweryCounter;
	}
	public void removeHouseCounter() {
		houseCounter -= 4;
	}
	public void addHouseCounter() {
		houseCounter++;
	}
	public int getHouseCounter() {
		return houseCounter;
	}
	public void addHotelCounter() {
		hotelCounter++;
	}
	public int getHotelCounter() {
		return hotelCounter;
	}
	public void setJailRoll(int JailRollCounter)
	{
		this.jailRollCounter = JailRollCounter;
	}
	public int getJailRoll()
	{
		return jailRollCounter;
	}
	public void addJailRollCounter() {
		jailRollCounter++;
	}
	public int getFreeCardCounter(){
		return freeCardCounter;
	}
	public void addFreeCard() {
		freeCardCounter++;
	}
	public void useFreeCard() {
		if(freeCardCounter > 0)
			freeCardCounter--;
	}
	public int getBalance(Player player) {
		return player.getPlayerAccount().getBalance();
	}
	//this method adjust the players balance
	public void adjustBalance(Player player, int amount) {
		player.getPlayerAccount().adjustBalance(amount);
	}
	public void setBalance(Player player, int amount) {
		player.getPlayerAccount().setBalance(amount);
	}
	//This method tranfers from one player to another.
	public void transfer(Player fromPlayer, Player toPlayer, int amount) {
		fromPlayer.getPlayerAccount().transfer(toPlayer.getPlayerAccount(), amount);
	}
	public boolean isBankrupt(Player player) {
		return player.getPlayerAccount().isBankrupt();
	}
	public int getPropertyValue(Player player) {
		return player.getPlayerAccount().getPropertyValue();
	}
	public void adjustPropertyValue(Player player, int amount) {
		player.getPlayerAccount().adjustPropertyValue(amount);
	}

	//Adds territories that you buy to the arraylist
	public void addProperty(Territory territory) {
		OwnedProperties.add(territory);
	}

	//This method converts the ArrayList of owned territories to an Array
	public Territory[] getHusliste(){		
		Territory[] ownedTerritories = new Territory[OwnedProperties.size()];
		ownedTerritories = OwnedProperties.toArray(ownedTerritories);		

		return ownedTerritories;
	}
	//This method converts the ArrayList of territories ready for buildings to an Array
	public String[] getListOfHouseReadyTerritories(){		
		String[] stringFieldNames = new String[buildingReadyFields.size()];
		stringFieldNames = buildingReadyFields.toArray(stringFieldNames);		

		return stringFieldNames;
	}
	//
	public void removeString(String navn) {
		buildingReadyFields.remove(navn);
	}
	//This method adds the names of territories to an arraylist if they are ready to get builded on
	public void addHouseList(){
		if(!buildingReadyFields.contains("Rødovrevej") && !buildingReadyFields.contains("Hvidovrevej")){
			if(getBlueTerritoryCounter()==2){
				buildingReadyFields.add("Rødovrevej");
				buildingReadyFields.add("Hvidovrevej");
			}
		}
		if(!buildingReadyFields.contains("Roskildevej") && !buildingReadyFields.contains("Valby Langgade") && !buildingReadyFields.contains("Allégade")){
			if(getPinkTerritoryCounter()==3){
				buildingReadyFields.add("Roskildevej");
				buildingReadyFields.add("Valby Langgade");
				buildingReadyFields.add("Allégade");
			}
		}
		if(!buildingReadyFields.contains("Frederiksberg Allé") && !buildingReadyFields.contains("Bülowsvej") && !buildingReadyFields.contains("Gl. Kongevej")){
			if(getGreenTerritoryCounter()==3){
				buildingReadyFields.add("Frederiksberg Allé");
				buildingReadyFields.add("Bülowsvej");
				buildingReadyFields.add("Gl. Kongevej");
			}
		}
		if(!buildingReadyFields.contains("Bernstorffsvej") && !buildingReadyFields.contains("Hellerupvej") && !buildingReadyFields.contains("Strandvej")){
			if(getGrayTerritoryCounter()==3){
				buildingReadyFields.add("Bernstorffsvej");
				buildingReadyFields.add("Hellerupvej");
				buildingReadyFields.add("Strandvej");
			}
		}
		if(!buildingReadyFields.contains("Trianglen") && !buildingReadyFields.contains("Østerbrogade") && !buildingReadyFields.contains("Grønningen")){
			if(getRedTerritoryCounter()==3){
				buildingReadyFields.add("Trianglen");
				buildingReadyFields.add("Østerbrogade");
				buildingReadyFields.add("Grønningen");
			}
		}
		if(!buildingReadyFields.contains("Bredgade") && !buildingReadyFields.contains("Kgs. Nytorv") && !buildingReadyFields.contains("Østergade")){
			if(getWhiteTerritoryCounter()==3){
				buildingReadyFields.add("Bredgade");
				buildingReadyFields.add("Kgs. Nytorv");
				buildingReadyFields.add("Østergade");
			}
		}
		if(!buildingReadyFields.contains("Amagertorv") && !buildingReadyFields.contains("Vimmelskaftet") && !buildingReadyFields.contains("Nygade")){
			if(getYellowTerritoryCounter()==3){
				buildingReadyFields.add("Amagertorv");
				buildingReadyFields.add("Vimmelskaftet");
				buildingReadyFields.add("Nygade");
			}
		}
		if(!buildingReadyFields.contains("Frederiksberggade") && !buildingReadyFields.contains("Rådhuspladsen")){
			if(getMagentaTerritoryCounter()==2){
				buildingReadyFields.add("Frederiksberggade");
				buildingReadyFields.add("Rådhuspladsen");
			}
		}	
	}
	public void resetTurnCounter() {
		turnCounter = 0;
	}
}