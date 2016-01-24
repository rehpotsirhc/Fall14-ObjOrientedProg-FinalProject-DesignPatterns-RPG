package game.view;

import character.Party;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import game.MainApp;

public class MainScreenController implements IController {

	private Party party;
	
	@FXML
	private Button fightButton;
	
	@FXML 
	private Button inventoryButton;
	
	@FXML
	private Button storeButton;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private Button bossButton;
 
	@FXML
	private TextArea player1Stats;
	
	@FXML
	private TextArea player2Stats;
	
	@FXML
	private void OnFightButtonClicked()
	{
		mainApp.setParty(party);
		mainApp.SwitchBattleStage();
	}
	
	@FXML
	private void OnInventoryButtonClicked()
	{
		mainApp.setParty(party);
		mainApp.SwitchInventoryStage();
	}
	
	@FXML
	private void OnStoreButtonClicked()
	{
		mainApp.setParty(party);
		mainApp.SwitchShopStage();
	}
	
	@FXML
	private void OnSaveButtonClicked()
	{
		
	}
	
	@FXML
	private void OnBossButtonClicked()
	{
		mainApp.SwitchBattleStage();
	}
	
	private MainApp mainApp;
	
	@Override
	public void setMainApp(MainApp mainApp) 
	{
		
		this.mainApp = mainApp;
		
	}

	@Override
	public void Initialize() 
	{
		party = mainApp.getParty();
		
		UpdateStats();
		
	}

	private void UpdateStats() 
	{
			player1Stats.setText(party.getPlayerOne().getName() + '\n' +  party.getPlayerOne().currentCondition().toString());
			player2Stats.setText(party.getPlayerTwo().getName() + '\n' + party.getPlayerTwo().currentCondition().toString());
			
		
	}

}
