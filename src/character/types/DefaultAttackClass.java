package character.types;

import character.Character;
import character.Stats;

public class DefaultAttackClass implements IAttackClass {

	@Override
	public void attack(Character attacker, Character target) {

		Stats attackCosts = new Stats(0, -10, 0, 0, 0, 0);

		// adding an empty stats here so the the damage is a new object
		// if we don't do this then if we change damage, we're changing the
		// weapon's effects too!
		Stats damage = attacker.getWeapon().getUseEffect().add(new Stats());

		// damage is represented by negative stats
		// healing is represented by positive stats
		// if the weapon health effective is negative, then subtract the
		// attacker's strength
		// if the weapon health effect is positive, then add the attacker's
		// intelligence
		// this way the attacker's strength increases the attack if it's to do
		// damage and the attacker's intelligence increases the "attack" if its
		// healing

		int healthDamage = 0;
		if (damage.getHealth() <= 0)
			healthDamage = damage.getHealth()
					- attacker.currentCondition().getStrength();
		else
			healthDamage = damage.getHealth()
					+ attacker.currentCondition().getIntelligence();

		damage.setHealth(healthDamage);

		attacker.beAffected(attackCosts);
		target.beAffected(damage);

	}

	@Override
	public void defend(Character defender) {

		Stats defenseEffects = new Stats(0, -5, 0, 0, 0, 2);

		defender.beAffected(defenseEffects);
	}

}
