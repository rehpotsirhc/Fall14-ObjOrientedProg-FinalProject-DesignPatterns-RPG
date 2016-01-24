package game.view;

import character.Party;
import character.Stats;
import item.Gir;
import item.HolyHandGrenade;
import item.Items;
import item.ItemsFactory;
import item.KillerRabbit;
import item.ZombieTeddyBear;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import game.MainApp;

public class ShopController implements IController
{

	private Party party;
	@FXML
	private Button sellButton;
	
	@FXML
	private Button buyButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private TextArea moneyText;
	
	@FXML
	private TextArea sellPriceText;
		
	@FXML
	private TextArea buyPriceText;
	
	@FXML 
	private ListView<Items> inventoryList;
	private ObservableList<Items> inventoryListViewData = FXCollections.observableArrayList();
	
	@FXML 
	private ListView<Items> shopList;
	private ObservableList<Items> shopListViewData = FXCollections.observableArrayList();
	
	
	
	private MainApp mainApp;
	@Override
	public void setMainApp(MainApp mainApp) 
	{
		this.mainApp = mainApp;
	}
	
	@FXML
	private void OnBackButtonClicked(){
		mainApp.SwitchMainStage();
	}
	
	@FXML
	private void OnSellButtonClicked()
	{
		Items item = inventoryList.getSelectionModel().getSelectedItem();
		if(item != null)
		{
			party.addPartyGold(item.getPrice());
			party.getInventory().remove(item);
			UpdateMoney();
			
			PopulateItemList();
		}
		
		
	}

	@FXML
	private void OnBuyButtonClicked()
	{
		
		Items item = shopList.getSelectionModel().getSelectedItem();
		if(item != null)
		{
			if(party.getPartyGold() >= item.getPrice())
			{
				party.subPartyGold(item.getPrice());
				party.addItem(item);
				UpdateMoney();
				
				PopulateItemList();
			}
		}
			
		
		
		
	}
	
	@FXML
	private void OnInventoryItemClicked()
	{
		UpdatePrice();
	}
	
	@FXML
	private void OnShopItemClicked()
	{
		UpdatePrice();
	}
	private void UpdatePrice() 
	{
		Items inventoryItem = inventoryList.getSelectionModel().getSelectedItem();
		if(inventoryItem != null)
			sellPriceText.setText("$" + inventoryItem.getPrice());
		else
			sellPriceText.setText("");
		
		Items shopItem = shopList.getSelectionModel().getSelectedItem(); 
		
		if(shopItem != null)
			buyPriceText.setText("$" + shopList.getSelectionModel().getSelectedItem().getPrice());
		else
			buyPriceText.setText("");
		
	}

	@Override
	public void Initialize() 
	{
		party = mainApp.getParty();
		
		GenerateShop();
		PopulateItemList();
		UpdateMoney();
		
	}
	
	
	private void UpdateMoney() 
	{
		moneyText.setText("$" + party.getPartyGold());
		
	}

	private void GenerateShop() 
	{
		ItemsFactory factory = new ItemsFactory();
		shopListViewData.add(new Gir("Gir", new Stats(1,1,1,1,1,1), new Stats(-5,-5,0,-5,0,0)));
		shopListViewData.add(new HolyHandGrenade("HolyHandGrenade", new Stats(-25,0,0,0,0,0), 3));
		shopListViewData.add(new KillerRabbit("Cleece", new Stats(10,15,6,10,2,1), new Stats(-25,-5,0,-10,2,-10)));
		shopListViewData.add(new ZombieTeddyBear("Xypher", new Stats(6,5,0,10,2,-1), new Stats(-10,-5,0,-10,2,-1)));
		
		for(int i = 0; i < 10; ++i)
		{
			shopListViewData.add(factory.randomItem(party.getPlayerOne().getLevel()));
		}
		shopList.setItems(shopListViewData);
		
	}

	private void PopulateItemList() 
	{
		inventoryListViewData.clear();
		for(Items item :party.getInventory())
		{
			inventoryListViewData.add(item);
		}
		inventoryList.setItems(inventoryListViewData);
	}
	
}
