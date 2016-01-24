package item;

import java.util.Random;

import character.Stats;

public class ItemsFactory {

	// used by weapons and armor
	private Stats baseEquipEffect;
	private Stats baseEquipEffectAddedPerLevel;
	private Random r = new Random();
	private Random myRand = new Random();

	// used by Useables and weapons
	private Stats baseUseEffect;
	private Stats baseUseEffectAddedPerLevel;
	
	//Array of weapon names
	private String[] weaponNames = {"Thrasher","Mase","BattleAxe","Dragon Scourge","Alien Technology","Vorpal Sword","Archaic Test Quesion","Fluffy Bunny Slippers","Heavy Book of Doom"};

	public Weapons createFigherWeapon(int level) {
		baseEquipEffectAddedPerLevel = new Stats(r.nextInt(2), r.nextInt(2), 0, r.nextInt(3), 0, r.nextInt(2));
		baseUseEffectAddedPerLevel = new Stats(-(r.nextInt(10) + 1), -r.nextInt(2), -r.nextInt(2), 0, 0, 0);

		baseEquipEffect = new Stats(0, 0, 0, 1, 0, 0);
		baseUseEffect = new Stats(-5, 0, 0, 0, 0, 0);

		return createWeapon(level);

	}
	


	public Weapons createEnemyFigherWeapon(int level) {
		baseEquipEffectAddedPerLevel = new Stats(r.nextInt(2), r.nextInt(2), 0, r.nextInt(3), 0, r.nextInt(2));
		baseUseEffectAddedPerLevel = new Stats(-(r.nextInt(10) + 1), -r.nextInt(2), -r.nextInt(2), 0, 0, 0);

		baseEquipEffect = new Stats(0, 0, 0, 1, 0, 0);
		baseUseEffect = new Stats(-2, 0, 0, 0, 0, 0);

		return createWeapon(level);

	}

	public Weapons createStaff(int level) {
		
		baseEquipEffectAddedPerLevel = new Stats(0, 0, r.nextInt(3), 0, r.nextInt(3), 0);
		baseUseEffectAddedPerLevel = new Stats(-r.nextInt(2), -r.nextInt(2), -r.nextInt(2), 0, 0, 0);

		baseEquipEffect = new Stats(0, 0, 0, 0, 1, 0);
		baseUseEffect = new Stats(-4, 0, 0, 0, 0, 0);

		return createWeapon(level);

	}


	public Armor createArmor(int level) {
		Random r = new Random();
		baseEquipEffectAddedPerLevel = new Stats(0, 0, 0, 0, 0, r.nextInt(2) + 1);
		baseEquipEffect = new Stats(0, 0, 0, 0, 0, r.nextInt(6) + 1);

		Stats effects = calculateEffect(level, baseEquipEffect,
				baseEquipEffectAddedPerLevel);

		return new Armor("Armor, level " + level, effects);

	}

	public Useable createPotion(int level) {
		Random r = new Random();
		baseUseEffectAddedPerLevel = new Stats(r.nextInt(3), r.nextInt(3), r.nextInt(3), 0, 0, 0);
		baseUseEffect = new Stats(r.nextInt(3) + 5, r.nextInt(3) + 5, r.nextInt(3) + 5, 0, 0, 0);

		Stats effects = calculateEffect(level, baseUseEffect,
				baseUseEffectAddedPerLevel);

		return new Useable("Potion, level " + level, effects, 1);

	}

	public Items randomItem(int level) {
		Random r = new Random();

		int t = r.nextInt(10);

		if (t == 0) {
			return createFigherWeapon(level);
		} else if (t == 1) {
			return createStaff(level);
		} else if (t == 2) {
			return createArmor(level);
		}

		return createPotion(level);

	}

	private Weapons createWeapon(int level) {
		Stats equipEffect = calculateEffect(level, baseEquipEffect,
				baseEquipEffectAddedPerLevel);
		Stats useEffect = calculateEffect(level, baseUseEffect,
				baseUseEffectAddedPerLevel);
		String weaponName = weaponNames[myRand.nextInt(weaponNames.length)];

		return new Weapons(weaponName, equipEffect, useEffect);

	}

	private Stats calculateEffect(int level, Stats base, Stats added) {

		double multiplier = 1 + ((double) level / 4);
		added = added.multiplier(multiplier);
		return base.add(added);

	}

}
