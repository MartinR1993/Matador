package controllers;

import entities.*;

import java.awt.Color;
import java.util.ResourceBundle;

import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_resources.GUI;

public class GameBoard {

	private DiceCup diceCup;
	private Field[] guiFields;
	private Felt[] logicFields;
	private ResourceBundle rb;
	private String languageChosen;
	private String language;
	private String country;
	private Felt[] territoryList;


	//GameBoard constructor
	public GameBoard(DiceCup cup) {
		guiFields = createGUIFields();
		diceCup = cup;
		initFields();
		logicFields = createLogicFields();
		GUI.create(guiFields);
		
	}

	public GameBoard(DiceCup cup, LanguageSelector ls) {
		guiFields = createGUIFields();
		diceCup = cup;
		initFields();
		GUI.create(guiFields);	
		languageChosen = GUI.getUserButtonPressed("Vælg Sprog / Select Language", "Dansk", "English");
		languageSelect(languageChosen);
		rb = ls.selectLanguage(language, country);
		logicFields = createLogicFields();
		GUI.showMessage(rb.getString("Velkommen"));
	}
	
	private void languageSelect(String languageChosen) {
		if(languageChosen.equalsIgnoreCase("Dansk")) {
			language = "da";
			country = "DK";
		} else {
			language = "en";
			country = "US";
		}
		
	}

	public ResourceBundle getBundle() {
		return rb;
	}
	//Initializes the fields as their respective type.
	private Felt[] createLogicFields() {
		Felt[] logiskeFelter = new Felt[40];
		logiskeFelter[0] = new StartField("Start");
		logiskeFelter[1] = new Territory(1200, "Rødovrevej", this, rb, "blue",2, 1000, new int[] {50,250,750,2250,4000,6000});
		logiskeFelter[2] = new ChanceField("Prøv Lykken");
		logiskeFelter[3] = new Territory(1200, "Hvidovrevej", this, rb, "blue",4, 1000, new int[] {50,250,750,2250,4000,6000});
		logiskeFelter[4] = new Tax(4000, "Betal indkomstskat", rb);
		logiskeFelter[5] = new ShippingCompany(4000, "SFL-Færgerne", this, rb);
		logiskeFelter[6] = new Territory(2000, "Roskildevej", this, rb, "pink",7, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000});
		logiskeFelter[7] = new ChanceField("Prøv Lykken");
		logiskeFelter[8] = new Territory(2000, "Valby Langgade", this, rb, "pink",9, 1000, new int[] {100, 600, 1800, 5400, 8000, 11000});
		logiskeFelter[9] = new Territory(2400, "Allégade", this, rb, "pink",10, 1000, new int[] {150, 800, 2000, 6000, 9000, 12000});
		logiskeFelter[10] = new Jail("I Fængsel", this, rb);
		logiskeFelter[11] = new Territory(2800, "Frederiksberg Allé", this, rb, "green",12, 2000, new int[] {200, 1000, 3000, 9000, 12500, 15000});
		logiskeFelter[12] = new Brewery(3000, "Tuborg",diceCup, this, rb);
		logiskeFelter[13] = new Territory(2800, "Bülowsvej", this, rb, "green",14, 2000, new int[] {200, 1000, 3000, 9000, 12500, 15000});
		logiskeFelter[14] = new Territory(3200, "Gl. Kongevej", this, rb, "green",15, 2000, new int[] {250, 1250, 3750, 10000, 14000, 18000});
		logiskeFelter[15] = new ShippingCompany(4000, "DSB Kalundborg/Århus", this, rb);
		logiskeFelter[16] = new Territory(3600, "Bernstorffsvej", this, rb, "gray",17, 2000, new int[] {300, 1400, 4000, 11000, 15000, 19000});
		logiskeFelter[17] = new ChanceField("Prøv Lykken");
		logiskeFelter[18] = new Territory(3600, "Hellerupvej", this, rb, "gray",19, 2000, new int[] {300, 1400, 4000, 11000, 15000, 19000});
		logiskeFelter[19] = new Territory(4000, "Strandvej", this, rb, "gray",20, 2000, new int[] {350, 1600, 4400, 12000, 16000, 20000});
		logiskeFelter[20] = new Parking("Parkering", rb);
		logiskeFelter[21] = new Territory(4400, "Trianglen", this, rb, "red",22, 3000, new int[] {350, 1800, 5000, 14000, 17500, 21000});
		logiskeFelter[22] = new ChanceField("Prøv Lykken");
		logiskeFelter[23] = new Territory(4400, "Østerbrogade", this, rb, "red",24, 3000, new int[] {350, 1800, 5000, 14000, 17500, 21000});
		logiskeFelter[24] = new Territory(4800, "Grønningen", this, rb, "red",25, 3000, new int[] {400, 2000, 6000, 15000, 18500, 22000});
		logiskeFelter[25] = new ShippingCompany(4000, "DFDS Seaways", this, rb);
		logiskeFelter[26] = new Territory(5200, "Bredgade", this, rb, "white",27, 3000, new int[] {450, 2200, 6600, 16000, 19500, 23000});
		logiskeFelter[27] = new Territory(5200, "Kgs. Nytorv", this, rb, "white",28, 3000, new int[] {450, 2200, 6600, 16000, 19500, 23000});
		logiskeFelter[28] = new Brewery(3000, "Coca-Cola",diceCup, this, rb);
		logiskeFelter[29] = new Territory(5600, "Østergade", this, rb, "white",30, 3000, new int[] {500, 2400, 7200, 17000, 20500, 24000});
		logiskeFelter[30] = new GoToJail("De Fængsles", this, rb);
		logiskeFelter[31] = new Territory(6000, "Amagertorv", this, rb, "yellow",32, 4000, new int[] {550, 2600, 7800, 18000, 22000, 25000});
		logiskeFelter[32] = new Territory(6000, "Vimmelskaftet", this, rb, "yellow",33, 4000, new int[] {550, 2600, 7800, 18000, 22000, 25000});
		logiskeFelter[33] = new ChanceField("Prøv Lykken");
		logiskeFelter[34] = new Territory(6400,  "Nygade", this, rb, "yellow",35, 4000, new int[] {600, 3000, 9000, 20000, 24000, 28000});
		logiskeFelter[35] = new ShippingCompany(4000, "DSB Halsskov/Knudshoved", this, rb);
		logiskeFelter[36] = new ChanceField("Prøv Lykken");
		logiskeFelter[37] = new Territory(7000, "Frederiksberggade", this, rb, "magenta",38, 4000, new int[] {700, 3500, 10000, 22000, 26000, 30000});
		logiskeFelter[38] = new Tax(2000, "Ekstraordinær statsskat", rb);
		logiskeFelter[39] = new Territory(8000, "Rådhuspladsen", this, rb, "magenta",40, 4000, new int[] {1000, 4000, 12000, 28000, 34000, 40000});

		return logiskeFelter;
	}


	private Field[] createGUIFields(){
		//Creates the fields, making them ready to plot into the GUI.
		Field[] newGuiFields = new Field[40];
		newGuiFields[0] = new Start.Builder().setBgColor(Color.RED).build();

		newGuiFields[1] = new Street.Builder().setBgColor(Color.BLUE).build();
		newGuiFields[2] = new Chance.Builder().setBgColor(Color.CYAN).build();
		newGuiFields[3] = new Street.Builder().setBgColor(Color.BLUE).build();
		newGuiFields[4] = new Street.Builder().setBgColor(Color.DARK_GRAY).build();
		newGuiFields[5] = new Street.Builder().setBgColor(Color.LIGHT_GRAY).build();
		newGuiFields[6] = new Street.Builder().setBgColor(Color.PINK).build();
		newGuiFields[7] = new Chance.Builder().setBgColor(Color.CYAN).build();
		newGuiFields[8] = new Street.Builder().setBgColor(Color.PINK).build();
		newGuiFields[9] = new Street.Builder().setBgColor(Color.PINK).build();
		newGuiFields[10] = new Start.Builder().setBgColor(Color.GRAY).build();
		newGuiFields[11] = new Street.Builder().setBgColor(Color.GREEN).build();
		newGuiFields[12] = new Street.Builder().setBgColor(Color.ORANGE).build();
		newGuiFields[13] = new Street.Builder().setBgColor(Color.GREEN).build();
		newGuiFields[14] = new Street.Builder().setBgColor(Color.GREEN).build();
		newGuiFields[15] = new Street.Builder().setBgColor(Color.LIGHT_GRAY).build();
		newGuiFields[16] = new Street.Builder().setBgColor(Color.GRAY).build();
		newGuiFields[17] = new Chance.Builder().setBgColor(Color.CYAN).build();
		newGuiFields[18] = new Street.Builder().setBgColor(Color.GRAY).build();
		newGuiFields[19] = new Street.Builder().setBgColor(Color.GRAY).build();
		newGuiFields[20] = new Start.Builder().setBgColor(Color.WHITE).build();
		newGuiFields[21] = new Street.Builder().setBgColor(Color.RED).build();
		newGuiFields[22] = new Chance.Builder().setBgColor(Color.CYAN).build();
		newGuiFields[23] = new Street.Builder().setBgColor(Color.RED).build();
		newGuiFields[24] = new Street.Builder().setBgColor(Color.RED).build();
		newGuiFields[25] = new Street.Builder().setBgColor(Color.LIGHT_GRAY).build();
		newGuiFields[26] = new Street.Builder().setBgColor(Color.WHITE).build();
		newGuiFields[27] = new Street.Builder().setBgColor(Color.WHITE).build();
		newGuiFields[28] = new Street.Builder().setBgColor(Color.ORANGE).build();
		newGuiFields[29] = new Street.Builder().setBgColor(Color.WHITE).build();
		newGuiFields[30] = new Start.Builder().setBgColor(Color.GRAY).build();
		newGuiFields[31] = new Street.Builder().setBgColor(Color.YELLOW).build();
		newGuiFields[32] = new Street.Builder().setBgColor(Color.YELLOW).build();
		newGuiFields[33] = new Chance.Builder().setBgColor(Color.CYAN).build();
		newGuiFields[34] = new Street.Builder().setBgColor(Color.YELLOW).build();
		newGuiFields[35] = new Street.Builder().setBgColor(Color.LIGHT_GRAY).build();
		newGuiFields[36] = new Chance.Builder().setBgColor(Color.CYAN).build();
		newGuiFields[37] = new Street.Builder().setBgColor(Color.MAGENTA).build();
		newGuiFields[38] = new Street.Builder().setBgColor(Color.DARK_GRAY).build();
		newGuiFields[39] = new Street.Builder().setBgColor(Color.MAGENTA).build();


		return newGuiFields;

	}
	//Plotting the title and descriptopn in to the fields.
	private void initFields() {
		//Removes subtext for all fields.
		for(int i=0; i < guiFields.length; i++)
			guiFields[i].setSubText(" ");

		//Initializes every field with Title and description.

		guiFields[0].setTitle("Start"); guiFields[0].setDescription("Hvergang De passere modtag kr. 4000"); guiFields[0].setSubText("Start"); 
		guiFields[1].setTitle("Rødovrevej"); guiFields[1].setDescription("Hus/Hotel pris: 1000 <BR> Leje uden hus: 50 <BR> Leje 1 hus: 250<BR> Leje 2 hus: 750<BR> Leje 3 hus: 2250<BR> Leje 4 hus: 4000 <BR> Leje hotel: 6000"); guiFields[1].setSubText("Pris: 1200");
		guiFields[2].setTitle("Prøv Lykken"); guiFields[2].setDescription("Træk et kort"); guiFields[2].setSubText(" ");
		guiFields[3].setTitle("Hvidovrevej"); guiFields[3].setDescription("Hus/Hotel pris: 1000 <BR> Leje uden hus: 50 <BR> Leje 1 hus: 250<BR> Leje 2 hus: 750<BR> Leje 3 hus: 2250<BR> Leje 4 hus: 4000 <BR> Leje hotel: 6000"); guiFields[3].setSubText("Pris: 1200");
		guiFields[4].setTitle("Betal indkomst skat"); guiFields[4].setDescription("Betal indkomstskat 10% eller kr. 4000"); guiFields[4].setSubText("Tax");
		guiFields[5].setTitle("SFL Færgerne"); guiFields[5].setDescription("Pris: 4000 <BR> Leje m. 1 grund: 500<BR> Leje m. 2 grunde: 1000<BR> Leje m. 3 grunde: 2000<BR> Leje m. 4 grunde: 4000"); guiFields[5].setSubText("Pris: 4000");
		guiFields[6].setTitle("Roskildevej"); guiFields[6].setDescription("Hus/Hotel pris: 1000 <BR> Leje uden hus: 100 <BR> Leje 1 hus: 600<BR> Leje 2 hus: 1800<BR> Leje 3 hus: 5400<BR> Leje 4 hus: 8000 <BR> Leje hotel: 11000"); guiFields[6].setSubText("Pris: 2000");
		guiFields[7].setTitle("Prøv Lykken"); guiFields[7].setDescription("Træk et kort"); guiFields[7].setSubText(" ");
		guiFields[8].setTitle("Valby Langgade"); guiFields[8].setDescription("Hus/Hotel pris: 1000 <BR> Leje uden hus: 100 <BR> Leje 1 hus: 600<BR> Leje 2 hus: 1800<BR> Leje 3 hus: 5400<BR> Leje 4 hus: 8000 <BR> Leje hotel: 11000"); guiFields[8].setSubText("Pris: 2000");
		guiFields[9].setTitle("Allégade"); guiFields[9].setDescription("Hus/Hotel pris: 1000 <BR> Leje uden hus: 150 <BR> Leje 1 hus: 800<BR> Leje 2 hus: 2000<BR> Leje 3 hus: 6000<BR> Leje 4 hus: 9000 <BR> Leje hotel: 12000"); guiFields[9].setSubText("Pris: 2400");
		guiFields[10].setTitle("I Fængsel"); guiFields[10].setDescription("I Fængsel"); guiFields[10].setSubText("Jail");
		guiFields[11].setTitle("Frederiksberg Allé"); guiFields[11].setDescription("Hus/Hotel pris: 2000 <BR> Leje uden hus: 200 <BR> Leje 1 hus: 1000<BR> Leje 2 hus: 3000<BR> Leje 3 hus: 9000<BR> Leje 4 hus: 12500 <BR> Leje hotel: 15000"); guiFields[11].setSubText("Pris: 2800");
		guiFields[12].setTitle("Tuborg Bryggeri"); guiFields[12].setDescription("Leje m. ét Bryggeri: 100 x antal øjne <BR> Leje m. to Bryggerier: 200 x antal øjne"); guiFields[12].setSubText("Pris: 3000");
		guiFields[13].setTitle("Bülowsvej"); guiFields[13].setDescription("Hus/Hotel pris: 2000 <BR> Leje uden hus: 200 <BR> Leje 1 hus: 1000<BR> Leje 2 hus: 3000<BR> Leje 3 hus: 9000<BR> Leje 4 hus: 12500 <BR> Leje hotel: 15000"); guiFields[13].setSubText("Pris: 2800");
		guiFields[14].setTitle("Gl. Kongevej"); guiFields[14].setDescription("Hus/Hotel pris: 2000 <BR> Leje uden hus: 250 <BR> Leje 1 hus: 1250<BR> Leje 2 hus: 3750<BR> Leje 3 hus: 10000<BR> Leje 4 hus: 14000 <BR> Leje hotel: 18000"); guiFields[14].setSubText("Pris: 3200");
		guiFields[15].setTitle("DSB Århus/ Kalundborg"); guiFields[15].setDescription("Pris: 4000 <BR> Leje m. 1 grund: 500<BR> Leje m. 2 grunde: 1000<BR> Leje m. 3 grunde: 2000<BR> Leje m. 4 grunde: 4000"); guiFields[15].setSubText("Pris: 4000");
		guiFields[16].setTitle("Bernstorffsvej"); guiFields[16].setDescription("Hus/Hotel pris: 2000 <BR> Leje uden hus: 300 <BR> Leje 1 hus: 1400<BR> Leje 2 hus: 4000<BR> Leje 3 hus: 11000<BR> Leje 4 hus: 15000 <BR> Leje hotel: 19000"); guiFields[16].setSubText("Pris: 3600");
		guiFields[17].setTitle("Prøv Lykken"); guiFields[17].setDescription("Træk et kort"); guiFields[17].setSubText(" ");
		guiFields[18].setTitle("Hellerupvej"); guiFields[18].setDescription("Hus/Hotel pris: 2000 <BR> Leje uden hus: 300 <BR> Leje 1 hus: 1400<BR> Leje 2 hus: 4000<BR> Leje 3 hus: 11000<BR> Leje 4 hus: 15000 <BR> Leje hotel: 19000"); guiFields[18].setSubText("Pris: 3600");
		guiFields[19].setTitle("Strandvej"); guiFields[19].setDescription("Hus/Hotel pris: 2000 <BR> Leje uden hus: 350 <BR> Leje 1 hus: 1600<BR> Leje 2 hus: 4400<BR> Leje 3 hus: 12000<BR> Leje 4 hus: 16000 <BR> Leje hotel: 20000"); guiFields[19].setSubText("Pris: 4000");
		guiFields[20].setTitle("Parkering"); guiFields[20].setDescription("Der sker ingenting her!"); guiFields[20].setSubText("Parking");
		guiFields[21].setTitle("Trianglen"); guiFields[21].setDescription("Hus/Hotel pris: 3000 <BR> Leje uden hus: 350 <BR> Leje 1 hus: 1800<BR> Leje 2 hus: 5000<BR> Leje 3 hus: 14000<BR> Leje 4 hus: 17500 <BR> Leje hotel: 21000"); guiFields[21].setSubText("Pris: 4400");
		guiFields[22].setTitle("Prøv Lykken"); guiFields[22].setDescription("Træk et kort"); guiFields[22].setSubText(" ");
		guiFields[23].setTitle("Østerbrogade"); guiFields[23].setDescription("Hus/Hotel pris: 3000 <BR> Leje uden hus: 350 <BR> Leje 1 hus: 1800<BR> Leje 2 hus: 5000<BR> Leje 3 hus: 14000<BR> Leje 4 hus: 17500 <BR> Leje hotel: 21000"); guiFields[23].setSubText("Pris: 4400");
		guiFields[24].setTitle("Grønningen"); guiFields[24].setDescription("Hus/Hotel pris: 3000 <BR> Leje uden hus: 400 <BR> Leje 1 hus: 2000<BR> Leje 2 hus: 6000<BR> Leje 3 hus: 15000<BR> Leje 4 hus: 18500 <BR> Leje hotel: 22000"); guiFields[24].setSubText("Pris: 4800");
		guiFields[25].setTitle("DFDS Seaways"); guiFields[25].setDescription("Pris: 4000 <BR> Leje m. 1 grund: 500<BR> Leje m. 2 grunde: 1000<BR> Leje m. 3 grunde: 2000<BR> Leje m. 4 grunde: 4000"); guiFields[25].setSubText("Pris: 4000");
		guiFields[26].setTitle("Bredgade"); guiFields[26].setDescription("Hus/Hotel pris: 3000 <BR> Leje uden hus: 450 <BR> Leje 1 hus: 2200<BR> Leje 2 hus: 6600<BR> Leje 3 hus: 16000<BR> Leje 4 hus: 19500 <BR> Leje hotel: 23000"); guiFields[26].setSubText("Pris: 5200");
		guiFields[27].setTitle("Kgs. Nytorv"); guiFields[27].setDescription("Hus/Hotel pris: 3000 <BR> Leje uden hus: 450 <BR> Leje 1 hus: 2200<BR> Leje 2 hus: 6600<BR> Leje 3 hus: 16000<BR> Leje 4 hus: 19500 <BR> Leje hotel: 23000"); guiFields[27].setSubText("Pris: 5200");
		guiFields[28].setTitle("Coca-Cola Tapperi"); guiFields[28].setDescription("Leje m. ét Bryggeri: 100 x antal øjne <BR> Leje m. to Bryggerier: 200 x antal øjne"); guiFields[28].setSubText("Pris: 3000");
		guiFields[29].setTitle("Østergade"); guiFields[29].setDescription("Hus/Hotel pris: 3000 <BR> Leje uden hus: 500 <BR> Leje 1 hus: 2400<BR> Leje 2 hus: 7200<BR> Leje 3 hus: 17000<BR> Leje 4 hus: 20500 <BR> Leje hotel: 24000"); guiFields[29].setSubText("Pris: 5600");
		guiFields[30].setTitle("De Fængsles"); guiFields[30].setDescription("Du bliver overflyttet til fængslet"); guiFields[30].setSubText("GoToJail");
		guiFields[31].setTitle("Amagertorv"); guiFields[31].setDescription("Hus/Hotel pris: 4000 <BR> Leje uden hus: 550 <BR> Leje 1 hus: 2600<BR> Leje 2 hus: 7800<BR> Leje 3 hus: 18000<BR> Leje 4 hus: 22000 <BR> Leje hotel: 25000"); guiFields[31].setSubText("Pris: 6000");
		guiFields[32].setTitle("Vimmel- skaftet"); guiFields[32].setDescription("Hus/Hotel pris: 4000 <BR> Leje uden hus: 550 <BR> Leje 1 hus: 2600<BR> Leje 2 hus: 7800<BR> Leje 3 hus: 18000<BR> Leje 4 hus: 22000 <BR> Leje hotel: 25000"); guiFields[32].setSubText("Pris: 6000");
		guiFields[33].setTitle("Prøv Lykken"); guiFields[33].setDescription("Træk et kort"); guiFields[33].setSubText(" ");
		guiFields[34].setTitle("Nygade"); guiFields[34].setDescription("Hus/Hotel pris: 4000 <BR> Leje uden hus: 600 <BR> Leje 1 hus: 3000<BR> Leje 2 hus: 9000<BR> Leje 3 hus: 20000<BR> Leje 4 hus: 24000 <BR> Leje hotel: 28000"); guiFields[34].setSubText("Pris: 6400");
		guiFields[35].setTitle("DSB Halskov /Knudshoved"); guiFields[35].setDescription("Pris: 4000 <BR> Leje m. 1 grund: 500<BR> Leje m. 2 grunde: 1000<BR> Leje m. 3 grunde: 2000<BR> Leje m. 4 grunde: 4000"); guiFields[35].setSubText("Pris: 4000");
		guiFields[36].setTitle("Prøv Lykken"); guiFields[36].setDescription("Træk et kort"); guiFields[36].setSubText(" ");
		guiFields[37].setTitle("Frederiks- berggade"); guiFields[37].setDescription("Hus/Hotel pris: 4000 <BR> Leje uden hus: 700 <BR> Leje 1 hus: 3500<BR> Leje 2 hus: 10000<BR> Leje 3 hus: 22000<BR> Leje 4 hus: 26000 <BR> Leje hotel: 30000"); guiFields[37].setSubText("Pris: 7000");
		guiFields[38].setTitle("Ekstraordinær statsskat"); guiFields[38].setDescription("Ekstraordinær statsskat <BR> Betal kr. 2000"); guiFields[38].setSubText("Tax");
		guiFields[39].setTitle("Rådhus- pladsen"); guiFields[39].setDescription("Hus/Hotel pris: 4000 <BR> Leje uden hus: 1000 <BR> Leje 1 hus: 4000<BR> Leje 2 hus: 12000<BR> Leje 3 hus: 28000<BR> Leje 4 hus: 34000 <BR> Leje hotel: 40000"); guiFields[39].setSubText("Pris: 8000");
	}

	public void landOnField(int i, Player player){
		logicFields[i].landOnField(player);
	}

	public Field[] getGUIFields() {
		return guiFields;
	}

	public Felt[] getlogicFields() {
		return logicFields;
	}
	
	public Felt[] getTerritoryList() {
		for(int i = 0; i < guiFields.length; i++) {
			if(logicFields[i] instanceof Territory)
				territoryList[i] = logicFields[i];
		}
		return territoryList;
	}


}

