package command;

import character.Character;
import character.types.IAttackClass;

public class EnemyAttack implements ICommand {

	private IAttackClass attackClass;
	private Character attacker;
	private Character target;

	public EnemyAttack(Character attacker, Character target) {
		this.attackClass = attacker.getAttackClass();
		this.attacker = attacker;
		this.target = target;
	}

	@Override
	public String execute() {
		if(!attacker.isDead())
		{
			attackClass.attack(attacker, target);
		}
		return this.attacker.getName() + " attacks " + this.target.getName()+" using "+this.target.getWeapon().getName();
	}
}
