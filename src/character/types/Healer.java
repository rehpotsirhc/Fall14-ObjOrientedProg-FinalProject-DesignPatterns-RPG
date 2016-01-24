package character.types;

import item.Weapons;
import character.Character;
import character.Player;
import character.Stats;

public class Healer extends Player {

	public Healer(String name, Stats baseStats, Weapons weapon,
			IAttackClass attackClass) {

		super(name, baseStats, weapon, attackClass);

	}

	@Override
	public void special(Character healer, Character target) {

		Stats heal = new Stats(0, 0, 0, 0, 0, 0);
		Stats healCosts = new Stats(0, 0, -10, 0, 0, 0);

		int healHealth = healer.currentCondition().getIntelligence();
		int healStamina = healer.currentCondition().getIntelligence();

		heal.setHealth(healHealth);
		heal.setStamina(healStamina);

		healer.beAffected(healCosts);
		target.beAffected(heal);

	}

	@Override
	public Stats getBaseStatsAddedOnLevelUp() {

		return new Stats(4, 1, 5, 1, 2, 1);

	}

}
