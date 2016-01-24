package game.view;



import item.Items;
import item.Useable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import command.Attack;
import command.Defend;
import command.EnemyAttack;
import command.Invoker;
import command.Special;
import command.UseItem;
import character.Character;
import character.CharacterFactory;
import character.Enemy;
import character.Party;
import character.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import game.MainApp;

public class BattleController implements IController
{
	//this is useful to know what turn it is
	private enum Turn
	{
		player1,
		player2,
		enemy,
	}
	
	//this is the players party
	private Party charParty;
	
	private Invoker invoker = new Invoker();
	
	//this is the enemies you fight
	private List<Enemy> enemies = new ArrayList<Enemy>();
	
	//this is the current turn
	private Turn attackTurn = Turn.player1;
	
	private String log  = "";
	
	// i used this so i could know if i clicked special or not
	private boolean specialClicked =false;
	
	private boolean itemClicked = false;
	
	
	//these are all references to the individual parts in the GUI heirachy
	@FXML 
	private Button attackButton;
	
	@FXML 
	private Button chooseEnemy1;
	
	@FXML 
	private Button chooseEnemy2;
	
	@FXML 
	private Button chooseEnemy3;
	
	@FXML
	private Button choosePlayer1;
	
	@FXML
	private Button choosePlayer2;
	
	@FXML 
	private Button defendButton;
	
	@FXML 
	private Button specialButton;
	
	@FXML 
	private Button itemButton;
	
	@FXML 
	private Button runButton;
	
	@FXML
	private Button endGameOKButton;
	
	@FXML 
	private Button backButton;
	
	@FXML 
	private Pane itemPane;
	
	@FXML
	private Pane attackChoicePane;
	
	@FXML 
	private Pane controlsPane;
	
	@FXML
	private Pane endGamePane;
	
	@FXML 
	private ListView<Useable> inventoryList;
	private ObservableList<Useable> listViewData = FXCollections.observableArrayList();
	
	@FXML
	private TextArea endGameLogText;
	
	@FXML
	private TextArea logText;
	
	@FXML
	private TextArea healthManaTextPlayer1;
	
	@FXML
	private Text turnText;
	
	@FXML
	private TextArea healthManaTextPlayer2;
	
	@FXML
	private TextArea healthManaTextEnemy1;
	
	@FXML
	private TextArea healthManaTextEnemy2;
	
	@FXML
	private TextArea healthManaTextEnemy3;
	
	
	
	//pointer back to main app
	private MainApp mainApp;
	
	public BattleController()
	{
		
	}
	
	//this function handles the enemy's turn.  It creates an enemy attack command and the tell the invoker to run the commands
	private void EnemyTurn()
	{
		for(Enemy enemy:enemies)
		{
			invoker.addEnemyCommand(new EnemyAttack(enemy, RandomPlayer()));
		}
		
		invoker.Run();
		
		log += invoker.getCommandLog() + '\n';
		logText.setText(log);
		
	}

	//this handles the turns.  I call this after each command is made It also updates the health visually and checks for the
	//end of the battle.  this is the equivalent of the game loop
	private void TurnHandler()
	{
		if(attackTurn.equals(Turn.player1))
		{
			SwitchTurn();
		}
		else if(attackTurn.equals(Turn.player2))
		{
			SwitchTurn();
			
		}
		
		if(attackTurn.equals(Turn.enemy))
		{
			EnemyTurn();
			
			
			UpdateHealth();
			SwitchTurn();
			CheckForBattleEnd();
		}
		
		
	}
	
	//this checks if the battle is over
	private void CheckForBattleEnd() 
	{
		if(charParty.getPlayerOne().isDead() && charParty.getPlayerTwo().isDead())
		{
			endGameLogText.setText("You lose Sucker");
			controlsPane.setVisible(false);
			endGamePane.setVisible(true);
			
			//should we restore health if we lose? no.  send to title screen.
			
		}
		else
		{
			boolean enemiesDead = true;
			for(Enemy enemy:enemies)
			{
				if(!enemy.isDead())
					enemiesDead = false;
			}
			if(enemiesDead)
			{
				List<Items> loot = new LinkedList<Items>();
				int totalXP =0;
				String endResults = "You recieved ";
				for(Enemy enemy:enemies)
				{
					totalXP += enemy.getXP();
					loot.addAll(enemy.loot());
				}
				for(Items item: loot)
				{
					endResults += item.getName() +",\n";
					charParty.addItem(item);
				}
				AddXP(totalXP);
				charParty.getPlayerOne().rest();
				charParty.getPlayerTwo().rest();
				endResults += "\nand " + totalXP + " XP.";
				endGameLogText.setText(endResults);
				controlsPane.setVisible(false);
				endGamePane.setVisible(true);
				
			}
		}
		
	}

	
	private void AddXP(int totalXP) {
		if(!charParty.getPlayerOne().isDead())
		{
			charParty.getPlayerOne().addXp(totalXP);
		}
		
		if(!charParty.getPlayerTwo().isDead())
		{
			charParty.getPlayerTwo().addXp(totalXP);
		}
	}

	//this sets up the battle.  Its called from main app after I point battle controller to the main app.  
	//I use this so I can make sure i have the party loaded before i do any enemy spawning.
	@Override
	public void Initialize() 
	{
		charParty = mainApp.getParty();
		
		CreateEnemies();
		PopulateItemList();
		
		UpdateHealth();
		logText.setText(log);
	}

	//this is the run button it will take you back to the main page.  I haven't set up probability of success yet
	@FXML
	private void OnRunButtonClicked()
	{
		mainApp.setParty(charParty);
		mainApp.SwitchMainStage();
	}
	
	//these are the arrow buttons to attack enemy 1 - 3.  It checks if you have clicked special or normal attack
	//and then calls the respective CreateAttack or special function to finish the command.
	@FXML
	private void OnChooseEnemy1ButtonClicked()
	{
		if(specialClicked)
		{
			specialClicked = false;
			CreateSpecial(enemies.get(0));
		}
		else if (itemClicked)
		{
			itemClicked = false;
			CreateItem(enemies.get(0));
		}
		else
		{
			CreateAttack(enemies.get(0));
		}
		
		attackChoicePane.setVisible(false);
	}
	
	@FXML
	private void OnChooseEnemy2ButtonClicked()
	{
		if(specialClicked)
		{
			specialClicked = false;
			CreateSpecial(enemies.get(1));
		}
		else if (itemClicked)
		{
			itemClicked = false;
			CreateItem(enemies.get(1));
		}
		else
		{
			CreateAttack(enemies.get(1));
		}
		
		attackChoicePane.setVisible(false);
	}
	
	@FXML
	private void OnChooseEnemy3ButtonClicked()
	{
		if(specialClicked)
		{
			specialClicked = false;
			CreateSpecial(enemies.get(2));
		}
		else if (itemClicked)
		{
			itemClicked = false;
			CreateItem(enemies.get(2));
		}
		else
		{
			CreateAttack(enemies.get(2));
		}
		
		attackChoicePane.setVisible(false);
	}
	
	//this causes the selection arrows to appear
	@FXML
	private void OnAttackButtonClicked()
	{
		ShowSelection();
		controlsPane.setVisible(false);
	}
	
	//this is where the Attack command is made.  I get the correct player by using the turn to know who's
	//turn it is to attack.
	private void CreateAttack(Character enemy)
	{
		Player attacker = getCorrectPlayer();
		
		invoker.addCommand(new Attack(attacker,enemy));
		
		TurnHandler();
	}
	
	
	//same as above but with special
	private void CreateSpecial(Character enemy)
	{
		Player attacker = getCorrectPlayer();
		
		invoker.addCommand(new Special(attacker,enemy));
		
		TurnHandler();
	}
	
	
	//this would be the same as above but when i get the inventory it gives me items
	//I need useables so the item system/ inventory needs to be modified.
	private void CreateItem(Character target)
	{
		Player user = getCorrectPlayer();
		
		Useable item  = inventoryList.getSelectionModel().getSelectedItem();
		//create an item command here.
		invoker.addCommand(new UseItem(item, user, target));
		
		//remove the item from the party
		listViewData.remove(item);
		TurnHandler();
	}

	//This creates a defend command
	@FXML
	private void OnDefendButtonClicked()
	{
		Player defender = getCorrectPlayer();
		
		invoker.addCommand(new Defend(defender));
		
		TurnHandler();
	}
	
	//this brings up the selection menu
	@FXML
	private void OnSpecialButtonClicked()
	{
		specialClicked = true;
		ShowSelection();
		
		controlsPane.setVisible(false);
	}
	
	//shows the enemy selection.  It checks to see if the enemy exists and is alive.
	private void ShowSelection() {
		
		if(enemies.get(0).isDead())
		{
			chooseEnemy1.setVisible(false);
		}
		if(enemies.size() < 2 )
		{
			chooseEnemy2.setVisible(false);
		}
		else if (enemies.get(1).isDead())
		{
			chooseEnemy2.setVisible(false);
		}
		if(enemies.size() < 3 )
		{
			chooseEnemy3.setVisible(false);
		}
		else if(enemies.get(2).isDead())
		{
			chooseEnemy3.setVisible(false);
		}
		
		attackChoicePane.setVisible(true);
	}

	//brings up the item select menu
	@FXML
	private void OnItemButtonClicked()
	{
		itemClicked = true;
		controlsPane.setVisible(false);
		itemPane.setVisible(true);
		ShowSelection();
		
	}
	
	@FXML 
	private void OnBackButtonClicked()
	{
		itemClicked = false;
		controlsPane.setVisible(true);
		itemPane.setVisible(false);
		attackChoicePane.setVisible(false);
	}
	
	//selects which player to use the item on
	@FXML
	private void OnChoosePlayer1ButtonClicked()
	{
		if(specialClicked)
		{
			specialClicked = false;
			CreateSpecial(charParty.getPlayerOne());
		}
		else if (itemClicked)
		{
			itemClicked = false;
			CreateItem(charParty.getPlayerOne());
		}
		else
		{
			CreateAttack(charParty.getPlayerOne());
		}
		
		attackChoicePane.setVisible(false);
	}
	
	@FXML
	private void OnChoosePlayer2ButtonClicked()
	{
		if(specialClicked)
		{
			specialClicked = false;
			CreateSpecial(charParty.getPlayerTwo());
		}
		else if (itemClicked)
		{
			itemClicked = false;
			CreateItem(charParty.getPlayerTwo());
		}
		else
		{
			CreateAttack(charParty.getPlayerTwo());
		}
		
		attackChoicePane.setVisible(false);
	}
	
	
	//brings you back to the main page.
	@FXML
	private void OnEndGameOkButtonClicked()
	{
		if(isPartyDead())
		{
			mainApp.SwitchTitleStage();
		}
		else
		{
			mainApp.setParty(charParty);
			mainApp.SwitchMainStage();
		}
	}
	
	private boolean isPartyDead() {
		// TODO Auto-generated method stub
		return charParty.getPlayerOne().isDead() && charParty.getPlayerTwo().isDead();
	}

	//pointer back to main app
	@Override
	public void setMainApp(MainApp mainApp)
	{
		this.mainApp = mainApp;
		
	}

	//this gives me the correct player by what turn it is
	private Player getCorrectPlayer() 
	{
		if(attackTurn.equals(Turn.player1))
		{
			return charParty.getPlayerOne();
		}
		
		return charParty.getPlayerTwo();
	}

	//updates all the health values on the screen
	private void UpdateHealth() 
	{
		healthManaTextPlayer1.setText(charParty.getPlayerOne().getName() + "\nHealth: " + charParty.getPlayerOne().currentCondition().getHealth() + "\nMana: " + charParty.getPlayerOne().getBaseStats().getMana());
		healthManaTextPlayer2.setText(charParty.getPlayerTwo().getName() + "\nHealth: " + charParty.getPlayerTwo().currentCondition().getHealth() + "\nMana: " + charParty.getPlayerTwo().getBaseStats().getMana());
		
		healthManaTextEnemy1.setText("Health: " + enemies.get(0).currentCondition().getHealth());
		if(enemies.size() > 1)
			healthManaTextEnemy2.setText("Health: " + enemies.get(1).currentCondition().getHealth());
		if(enemies.size() > 2)
			healthManaTextEnemy3.setText("Health: " + enemies.get(2).currentCondition().getHealth());
	}

	//used to populate the list of items so we can see them when we click the item button.
	private void PopulateItemList() 
	{
		for(Useable item :charParty.getUseables())
		{
			listViewData.add(item);
		}
		inventoryList.setItems(listViewData);
	}

	//spawns 1-3 random enemies that will destroy you if there are more than 1
	private void CreateEnemies() 
	{
		CharacterFactory factory = new CharacterFactory();
		int enemyCount = new Random().nextInt(3) + 1;
		
		log += enemyCount + " enemies have appeared.\n";
		
		for(int i = 0; i < enemyCount; ++i)
		{
			//probably a code smell
			enemies.add(factory.createEnemy(charParty.getPlayerOne().getLevel()));
			
		}
		
		
		
	}

	//switches the turn
	private void SwitchTurn()
	{
		switch(attackTurn)
		{
			case player1:
			{
				if(!charParty.getPlayerTwo().isDead())
				{
					controlsPane.setVisible(true);
					attackTurn = Turn.player2;
					turnText.setText("Player 2 Turn");
				}
				else
				{
					attackTurn = Turn.enemy;
					controlsPane.setVisible(false);
					turnText.setText("Enemy Turn");
				}
				break;
			}
			case player2:
			{
				controlsPane.setVisible(false);
				attackTurn = Turn.enemy;
				turnText.setText("Enemy Turn");
				break;
			}
			case enemy:
			{
				controlsPane.setVisible(true);
				if(!charParty.getPlayerOne().isDead())
				{
					attackTurn = Turn.player1;
					turnText.setText("Player 1 Turn");
				}
				else
				{
					attackTurn = Turn.player2;
					turnText.setText("Player 2 Turn");
				}
				
				break;
			}
		}
	}
	
	
	//used by the enemies to chose who they attack.  Completely random.
	private Player RandomPlayer() 
	{
		int playerNumber = new Random().nextInt(2);
		
		if(playerNumber == 0)
		{
			if(!charParty.getPlayerOne().isDead())
			{
				return charParty.getPlayerOne();
			}
			return charParty.getPlayerTwo();
		}
		
		if(!charParty.getPlayerTwo().isDead())
		{
			return charParty.getPlayerTwo();
		}
		return charParty.getPlayerOne();

		
	}
	
}
