package command;

import character.Character;
import character.types.IAttackClass;

// Command Pattern this is a "Concrete Command"
public class Defend implements ICommand {

	private IAttackClass attackClass;
	private Character defender;

	public Defend(Character defender) {
		this.attackClass = defender.getAttackClass();
		this.defender = defender;
	}

	@Override
	public String execute() {
		if(!defender.isDead())
		{
			attackClass.defend(defender);
		}
		return this.defender.getName() + " blocked the strike ";
	}

}
