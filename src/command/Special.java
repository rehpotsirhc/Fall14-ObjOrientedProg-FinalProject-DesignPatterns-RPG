package command;

import character.Character;
import character.Player;

// Command Pattern this is a "Concrete Command"
public class Special implements ICommand {

	private Player attacker;
	private Character target;

	public Special(Player attacker, Character target) {

		this.attacker = attacker;
		this.target = target;
	}

	@Override
	public String execute() 
	{
		if(!attacker.isDead())
		{
			attacker.special(attacker, target);
		}
		return attacker.getName() + " uses special on " + target.getName();
		
	}
}
