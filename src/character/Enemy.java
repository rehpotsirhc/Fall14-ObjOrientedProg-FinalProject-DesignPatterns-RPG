package character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import item.ItemsFactory;
import item.Weapons;
import item.Items;
import character.types.IAttackClass;

public class Enemy extends Character {

	private int level;
	private final int maxLootDrop = 5;

	public Enemy(Stats baseStats, Weapons weapon, IAttackClass attackClass,
			int level) {
		super("Enemy", baseStats, weapon, attackClass);

		this.level = level;

	}

	public List<Items> loot() {

		List<Items> items = new ArrayList<Items>();

		Random r = new Random();

		ItemsFactory it = new ItemsFactory();

		int toDrop = r.nextInt(maxLootDrop + 1);

		for (int i = 0; i < toDrop; i++) {
			Items ranItem = it.randomItem(level);
			items.add(ranItem);

		}

		return items;

	}

	public int getXP() {
		return 100;
	}

}
