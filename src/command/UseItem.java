package command;

import item.Useable;
import character.Player;
import character.Character;

public class UseItem implements ICommand {

    private Useable item;
    private Player user;
    private Character target;
    
    public UseItem(Useable item, Player user, Character target)
    {
    	this.item = item;
    	this.user = user;
    	this.target = target;
    }
    
    @Override
    public String execute() 
    {
    	if(!user.isDead())
    		user.useItem(item, target);
    	return user.getName() + " is using  " + item.getName() +" on "+ target.getName();
    }

}
