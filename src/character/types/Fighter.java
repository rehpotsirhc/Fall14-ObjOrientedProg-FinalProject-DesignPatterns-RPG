package character.types;

import item.Weapons;
import character.Character;
import character.Player;
import character.Stats;

public class Fighter extends Player {

	public Fighter(String name, Stats baseStats, Weapons weapon,
			IAttackClass attackClass) {

		super(name, baseStats, weapon, attackClass);

	}

	@Override
	/*
	 * The fighter's special does two normal attacks
	 */
	public void special(Character attacker, Character target) {

		attacker.getAttackClass().attack(attacker, target);
		attacker.getAttackClass().attack(attacker, target);

	}

	@Override
	public Stats getBaseStatsAddedOnLevelUp() {

		return new Stats(5, 5, 1, 2, 1, 2);

	}

	
}
