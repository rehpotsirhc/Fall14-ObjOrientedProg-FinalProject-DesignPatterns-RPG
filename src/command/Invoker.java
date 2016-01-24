package command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

//Command pattern  ...this is the invoker
public class Invoker {

    private Queue<ICommand> commands = new LinkedList<ICommand>();
    private Queue<ICommand> enemyCommands = new LinkedList<ICommand>();
    private String log;

    public void addCommand(ICommand c) {
    	commands.add(c);
    }
    
    public void addEnemyCommand(ICommand c){
    	enemyCommands.add(c);
    }

    public void runCommand() {
	
    	if (commands.size() > 0) {
	    	ICommand c = commands.remove();
	    log	+= c.execute()  + '\n';
		}
    }
    
    public void runEnemyCommand() {
    	
    	if (enemyCommands.size() > 0) {
	    	ICommand c = enemyCommands.remove();
	    log += c.execute() + '\n';
		}
    }
    
    //I added this in cause there is no way i'm going to call the above functions x amount of times in battleController.
    public void Run()
    {
    	
    	//clear log string here at the beginning of run
    	log = "";
    	
    	
    	while(commands.size() > 0)
    	{
    		runCommand();
    	}
    	
    	while (enemyCommands.size() > 0)
    	{
    		runEnemyCommand();
    	}
    }

    //this needs to be implemented
	public String getCommandLog() {
		
		return this.log;  //Character attacks character
	}
}
