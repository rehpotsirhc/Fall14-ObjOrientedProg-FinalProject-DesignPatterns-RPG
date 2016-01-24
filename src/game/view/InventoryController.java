package game.view;

import character.Party;
import character.Player;
import item.Armor;
import item.Items;
import item.Useable;
import item.Weapons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import game.MainApp;

public class InventoryController implements IController {

	private Party party;
	private boolean selectionInProcess = false;
	
	private String myLog = "";
	
	@FXML
	private Button equipButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button equipPlayer1;
	
	@FXML 
	private Button equipPlayer2;
	
	@FXML 
	private Pane selectionPane;
	
	@FXML 
	private TextArea log;
	
	@FXML 
	private TextArea player1Info;
	
	@FXML 
	private TextArea player2Info;
	
	@FXML 
	private ListView<Items> inventoryList;
	private ObservableList<Items> listViewData = FXCollections.observableArrayList();
	
	private MainApp mainApp;
	
	
	@Override
	public void setMainApp(MainApp mainApp) 
	{
		this.mainApp = mainApp;
		
	}
	
	@FXML
	private void OnBackButtonClicked()
	{
		if(!selectionInProcess)
			mainApp.SwitchMainStage();
		else
		{
			selectionPane.setVisible(false);
			equipButton.setVisible(true);
			selectionInProcess = false;
		}
	}

	@FXML
	private void OnEquipPlayer1ButtonClicked()
	{
		UseitemOnPlayer(party.getPlayerOne(), inventoryList.getSelectionModel().getSelectedItem());
		
		selectionPane.setVisible(false);
		equipButton.setVisible(true);
		selectionInProcess = false;
	}

	
	@FXML
	private void OnEquipPlayer2ButtonClicked()
	{
		UseitemOnPlayer(party.getPlayerTwo(), inventoryList.getSelectionModel().getSelectedItem());
		selectionPane.setVisible(false);
		equipButton.setVisible(true);
		selectionInProcess = false;
		
	}

	@FXML
	private void OnEquipUseButtonClicked()
	{
		selectionPane.setVisible(true);
		equipButton.setVisible(false);
		selectionInProcess = true;
	}

	@Override
	public void Initialize() {
		party = mainApp.getParty();
		
		PopulateItemList();
		UpdateStats();
	}
	
	private void UpdateStats() {
		player1Info.setText(party.getPlayerOne().getName() + '\n' +  party.getPlayerOne().currentCondition().toString());
		player2Info.setText(party.getPlayerTwo().getName() + '\n' +  party.getPlayerTwo().currentCondition().toString());
		
	}

	private void PopulateItemList() 
	{
		listViewData.clear();
		for(Items item :party.getInventory())
		{
			listViewData.add(item);
		}
		inventoryList.setItems(listViewData);
	}
	
	//this will work we just need to juggle the items in and out of the inventory.
	private void UseitemOnPlayer(Player player, Items selectedItem) 
	{
		if(selectedItem instanceof Useable)
		{
			player.useItem((Useable)selectedItem, player);
			myLog += "Used " + selectedItem.getName() + '\n';
			
			if(((Useable) selectedItem).canUse() == false){
				party.getInventory().remove(selectedItem);
			}

		}
		else if(selectedItem instanceof Armor)
		{
			player.equipArmor((Armor)selectedItem);
			myLog += "Equipped " + selectedItem.getName() +'\n';
			
			party.getInventory().remove(selectedItem);
			
		}
		else if(selectedItem instanceof Weapons)
		{
			player.setWeapon((Weapons)selectedItem);
			
			myLog += "Equipped " + selectedItem.getName() +'\n';
			
			party.getInventory().remove(selectedItem);
		}
		
		log.setText(myLog);
		PopulateItemList();
		UpdateStats();
	}


}
