package character.types;

import item.Weapons;
import character.Character;
import character.Player;
import character.Stats;

public class Mage extends Player {

	public Mage(String name, Stats baseStats, Weapons weapon,
			IAttackClass attackClass) {

		super(name, baseStats, weapon, attackClass);

	}

	@Override
	public void special(Character spellCaster, Character target) {

		Stats spell = new Stats(0, 0, 0, 0, 0, 0);
		Stats spellCosts = new Stats(0, 0, -10, 0, 0, 0);

		int healthDamage = -(2 * spellCaster.currentCondition()
				.getIntelligence());

		spell.setHealth(healthDamage);

		spellCaster.beAffected(spellCosts);
		target.beAffected(spell);
	}

	@Override
	public Stats getBaseStatsAddedOnLevelUp() {

		return new Stats(3, 1, 6, 1, 2, 1);

	}

}
