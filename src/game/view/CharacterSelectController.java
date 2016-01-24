package game.view;

import java.util.LinkedList;
import java.util.List;

import item.HealingPotion;
import item.Items;
import character.CharacterFactory;
import character.Party;
import character.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import game.MainApp;

public class CharacterSelectController implements IController
{
	
	@FXML
	private Button readyUp;
	
	@FXML 
	private TextArea description1;
	
	@FXML 
	private TextArea description2;
	
	@FXML
	private MenuItem fighter1;
	
	@FXML
	private MenuItem mage1;
	
	@FXML
	private MenuItem healer1;
	
	@FXML
	private MenuItem fighter2;
	
	@FXML
	private MenuItem mage2;
	
	@FXML
	private MenuItem healer2;
	
	@FXML
	private TextField character1Name;
	
	@FXML
	private TextField character2Name;
	
	@FXML
	private MenuButton menuButton1;
	
	@FXML
	private MenuButton menuButton2;
	
	private MainApp mainApp;
	
	private final String fighterText = "A fighter is an expert at melee\ncombat.  They are skilled with\nmany weapons and can wear the\nheaviest of armors.";
	private final String mageText = "A mage is a master of the arcane.\nTheir physical abilities suffer,\nnot being able to wield mighty\nweapons or wear thick armor.\nTheir powerful spells make up for\nthis lack of defense.";
	private final String healerText = "A healer's job is to heal\nthe wounded (duh!).  Like\na mage, Healers have a lack of\nphysical skills.  But when you\ncan heal wounds, who cares?";
	@FXML
    private void initialize() {

	}
	
	@FXML 
	private void OnFighter1ButtonClicked()
	{
		description1.setText(fighterText);
		menuButton1.setText(fighter1.getText());
	}
	
	@FXML 
	private void OnMage1ButtonClicked()
	{
		description1.setText(mageText);
		menuButton1.setText(mage1.getText());
	}
	
	@FXML 
	private void OnHealer1ButtonClicked()
	{
		description1.setText(healerText);
		menuButton1.setText(healer1.getText());
	}
	
	@FXML 
	private void OnFighter2ButtonClicked()
	{
		description2.setText(fighterText);
		menuButton2.setText(fighter2.getText());
	}
	
	@FXML 
	private void OnMage2ButtonClicked()
	{
		description2.setText(mageText);
		menuButton2.setText(mage2.getText());
	}
	
	@FXML 
	private void OnHealer2ButtonClicked()
	{
		description2.setText(healerText);
		menuButton2.setText(healer2.getText());
	}
	
	@FXML 
	private void OnReadyUpButtonClicked()
	{
		if(!character1Name.getText().equals("") &&
				!menuButton1.getText().equals("Type") &&
				!character2Name.getText().equals("") &&
				!menuButton2.getText().equals("Type"))
		{
			//create character
			
			Player player1 = GetCorrectCharacter(menuButton1.getText(), character1Name.getText());
			Player player2 = GetCorrectCharacter(menuButton2.getText(), character2Name.getText());
			
			Party playerParty = new Party(player1, player2);
			
			// i gave the party a healing potion to see if i could get it to work.
			LinkedList<Items> startingItems = new LinkedList<Items>();
			startingItems.add(new HealingPotion());
			playerParty.setInventory(startingItems);
			
			playerParty.addPartyGold(50);
			player1.setParty(playerParty);
			player2.setParty(playerParty);
			
			mainApp.setParty(playerParty);
			mainApp.SwitchMainStage();
		}
	}
	
	private Player GetCorrectCharacter(String type, String name) {
		CharacterFactory characterCreator = new CharacterFactory();
		
		if(type.equals("Fighter"))
		{
			return characterCreator.createFighter(name);
		}
		else if(type.equals("Mage"))
		{
			return characterCreator.createMage(name);
		}
		else if(type.equals("Healer"))
		{
			return characterCreator.createHealer(name);
		}
		return null;
	}

	@Override
	public void setMainApp(MainApp mainApp) 
	{
		
		this.mainApp = mainApp;
	}

	@Override
	public void Initialize() {
		// TODO Auto-generated method stub
		
	}
}
