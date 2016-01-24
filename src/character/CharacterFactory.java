package character;

import item.Armor;
import item.ItemsFactory;
import item.Weapons;
import character.types.DefaultAttackClass;
import character.types.Fighter;
import character.types.Healer;
import character.types.Mage;

public class CharacterFactory {

	private Stats base;
	private Stats enemyBase;
	private Stats addedPerLevel;

	public CharacterFactory() {
		base = new Stats(5, 5, 0, 1, 0, 3);
		enemyBase = new Stats(2,2,0,1,0,1);
		addedPerLevel = new Stats(5, 5, 0, 1, 0, 1);
	}

	public Enemy createEnemy(int level) {
		ItemsFactory factory = new ItemsFactory();

		Weapons w = factory.createEnemyFigherWeapon(level);
		Armor a = factory.createArmor(level);

		Stats added = addedPerLevel.multiplier(level);

		Stats stats = enemyBase.add(added);

		Enemy e = new Enemy(stats, w, new DefaultAttackClass(), level);

		e.addArmor(a);

		return e;

	}

	public Fighter createFighter(String name) {
		ItemsFactory factory = new ItemsFactory();

		Weapons w = factory.createFigherWeapon(1);
		Armor a = factory.createArmor(1);

		Stats added = new Stats(10, 3, 0, 5, 0, 3);

		base = base.add(added);

		Fighter f = new Fighter(name, base, w, new DefaultAttackClass());
		f.addArmor(a);

		return f;

	}

	public Mage createMage(String name) {
		ItemsFactory factory = new ItemsFactory();

		Weapons w = factory.createStaff(1);

		Stats added = new Stats(6, 0, 10, 0, 15, 4);

		base = base.add(added);

		return new Mage(name, base, w, new DefaultAttackClass());
	}

	public Healer createHealer(String name) {
		ItemsFactory factory = new ItemsFactory();

		Weapons w = factory.createStaff(1);

		Stats added = new Stats(8, 0, 10, 1, 5, 4);

		base = base.add(added);
		return new Healer(name, base, w, new DefaultAttackClass());
	}

}
