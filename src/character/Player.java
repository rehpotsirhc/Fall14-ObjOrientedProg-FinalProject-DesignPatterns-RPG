package character;

import item.Armor;
import item.Items;
import item.Useable;
import item.Weapons;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import character.types.IAttackClass;

public abstract class Player extends Character {

	private String name;

	private int level;
	private int xpUntilLevel;
	private int xp;
	private final int xpLevelMutiplier = 2;
	
	private Party myParty;


	// some equipped class to represent the player's armor

	public Player(String name, Stats baseStats, Weapons weapon,
			IAttackClass attackClass) {
		super(name, baseStats, weapon, attackClass);
		this.name = name;
		this.level = 1;
		this.xp = 0;
		this.xpUntilLevel = 100;
	

		// set xp to get to level 2
	}
	
	public void setParty(Party p)
	{
	    myParty = p;
	}
	
	public void rest(){
		revive();
		restore();
	}



	public int getLevel() {
		return level;
	}

	public int getXpUntilLevel() 
	{
		return xpUntilLevel;
	}

	public int getXp() 
	{
		return xp;
	}

	public void addXp(int xp) {

		this.xp += xp;

		// if the player has enough xp to level up
		if (xp >= xpUntilLevel) {

			levelUp();

		}

	}

	public abstract Stats getBaseStatsAddedOnLevelUp();

	/*
	 * Equips armor by adding it to the player's equiped armor set and the
	 * player's inventory (if not already present)
	 */
	public void equipArmor(Armor a) {

		Armor oldArmor = addArmor(a);
		if(oldArmor != null){
			myParty.addItem(oldArmor);
		}
	}

	/*
	 * Dequips armor by removing it from the player's equiped armor set. If the
	 * armor is not already in the player's inventory, the armor is added to the
	 * inventory so it's not lost forever
	 */
	public String getName() {
		return name;
	}

	// this can have a default implementation
	public void useItem(Useable useableItem, Character target) 
	{
		if (!myParty.getInventory().contains(useableItem) && useableItem.canUse()) {
			useableItem.use();
			target.beAffected(useableItem.getEffect());

		}

		// its all used up
		if (!useableItem.canUse()) {
		    myParty.getInventory().remove(useableItem);
		}
	}

	public abstract void special(Character attacker, Character target);

	/*
	 * We could make this a strategy so we can easily change the way we increase
	 * a character's stats on level up
	 * 
	 * This is the way its implemented now:
	 * 
	 * This method will add the character's current base stats (stats at full
	 * health without armor or a weapon) to stats defined in the class that
	 * implements getBaseStatsAddedOnLevelUp() - right now that's mage, healer,
	 * and fighter This makes it so we can change the way we increase stats
	 * based on the character type, e.g., more health for fighter and more mana
	 * for mage These stats are then multiplied according to the player's level
	 * Leveling from 1 -> 2, they are multiplied by 1.2 Leveling from 2-3, they
	 * are multiplied by 1.3 The multiplication rounds (see the multiplier
	 * method of stats)
	 */
	private void levelUp() {
		// increase level
		level++;

		xpUntilLevel *= xpLevelMutiplier;

		// roll over extra xp
		xp = xp - xpUntilLevel;

		// increase stats
		Stats newStats = getBaseStats().add(getBaseStatsAddedOnLevelUp());
		double multiplier = 1 + ((double) level / 10);
		newStats = newStats.multiplier(multiplier);
		
		setBaseStats(newStats);

	}


}
