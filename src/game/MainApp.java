package game;

import game.view.IController;

import java.io.IOException;

import character.Party;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application 
{
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private Party charParty;
    
	@Override
	public void start(Stage primaryStage) 
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Conquer Shimovita!");
		
		initRootLayout();
		
		SwitchTitleStage();
	}

	private void ShowScene(String sceneName)
	{
		 try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource(sceneName));
	            AnchorPane Overview = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(Overview);
	            
	            IController controller = loader.getController();
	            controller.setMainApp(this);
	            controller.Initialize();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}
	
	private void initRootLayout() {
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		
	}
	
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public void SwitchBattleStage()
	{
		ShowScene("view/Battle.fxml");
		
	}
	
	public void SwitchMainStage()
	{
		ShowScene("view/MainScreen.fxml");
		
	}
	
	public void SwitchInventoryStage()
	{
		ShowScene("view/InventoryLayout.fxml");
		
	}
	
	public void SwitchShopStage()
	{
		ShowScene("view/ShopLayout.fxml");
		
	}
	
	public void SwitchTitleStage()
	{
		ShowScene("view/Title.fxml");
		
	}
	
	public void SwitchCharacterStage()
	{
		ShowScene("view/CharacterSetup.fxml");
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Party getParty(){
		return charParty;
	}
	
	public void setParty(Party us){
		charParty = us;
	}
	
}
