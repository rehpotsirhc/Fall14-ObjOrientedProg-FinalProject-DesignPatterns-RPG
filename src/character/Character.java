package character;

import java.util.ArrayList;
import java.util.List;

import character.types.IAttackClass;
import item.Armor;
import item.Weapons;

public abstract class Character {

	private Equipped equipment;

	private Stats battleEffects;

	private Stats baseStats;

	private IAttackClass attackClass;
	
	private String name;

	private boolean dead;

	public Character(String name, Stats baseStats, Weapons weapon, IAttackClass attackClass) {
		equipment = new Equipped();
		this.baseStats = baseStats;
		battleEffects = new Stats();
		equipment.setWeapon(weapon);
		this.attackClass = attackClass;
		dead = false;
		this.name = name;
	}

	public void restore() {
		battleEffects = new Stats();
	}
	
	public void revive(){
		dead = false;
	}

	public void beAffected(Stats effects) {

		// doing damage
		if (effects.getHealth() < 0) {
			// decrease the damage by character's defense
			effects.setHealth(effects.getHealth()
					+ currentCondition().getDefense());

			// make sure the effect isn't changed to a healing one
			if (effects.getHealth() > 0)
				effects.setHealth(0);
		}

		battleEffects = battleEffects.add(effects);

		// make sure character's can't get healed beyond their max health,
		// stamina, and mana
		

		if (battleEffects.getHealth() > 0)
			battleEffects.setHealth(0);
		if (battleEffects.getStamina() > 0)
			battleEffects.setStamina(0);
		if (battleEffects.getMana() > 0)
			battleEffects.setMana(0);

	}

	public Stats currentCondition() {

		Stats sum = baseStats.add(battleEffects);

		sum = sum.add(equipment.getArmorValue());

		sum = sum.add(equipment.getWeaponValue());

		if (sum.getStamina() < 0)
			sum.setStamina(0);
		if (sum.getMana() < 0)
			sum.setMana(0);
		if (sum.getStrength() < 0)
			sum.setStrength(0);
		if (sum.getIntelligence() < 0)
			sum.setIntelligence(0);
		if (sum.getDefense() < 0)
			sum.setDefense(0);
		
		//added this in so if the player dies his health doesn't go negative.  
		if(sum.getHealth() <= 0)
		{
			dead = true;
			sum.setHealth(0);
		}
		return sum;

	}
	
	public Weapons getWeapon(){
		return equipment.getWeapon();
	}

	public Weapons setWeapon(Weapons w) {
		Weapons oldWeapon = null;
		equipment.setWeapon(w);
		return oldWeapon;
	}

	public Armor addArmor(Armor a) {
		Armor oldArmor = null;
		oldArmor = equipment.equipArmor(a);
		return oldArmor;
	}

	

	public Stats getBaseStats() {
		return baseStats;
	}
	public String getName() {
		return name;
	}
	
	public void setBaseStats(Stats newStats){
		baseStats = newStats;
	}

	public IAttackClass getAttackClass() {
		return attackClass;
	}

	public boolean isDead(){
		return dead;
	}
}
