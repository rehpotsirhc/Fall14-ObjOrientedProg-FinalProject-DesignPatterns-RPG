package game.view;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import game.MainApp;

public class TitleController implements IController
{
	@FXML 
	private Button newGame;
	
	@FXML 
	private Button loadGame;
	
	private MainApp mainApp;
	
	public TitleController()
	{
		
	}
	
	@FXML
	private void OnNewGameButtonCLicked()
	{
		mainApp.SwitchCharacterStage();
	}
	
	@FXML
	private void OnLoadGameButtonCLicked()
	{
		mainApp.SwitchMainStage();
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





