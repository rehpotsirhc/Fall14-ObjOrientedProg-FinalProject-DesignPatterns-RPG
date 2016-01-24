package item;

import character.Stats;

public class Chestplate extends Armor
{
	private String name;
	private Stats effect;
	
	public Chestplate()
	{
		name = "Chestplate";
		effect = new Stats(0,5,0,5,0,10);
	}

	public String getName() 
	{
		return name;
	}

	public Stats getEffect() 
	{
		return effect;
	}

	@Override
	public String toString() {
		return "Chestplate [name=" + name + ", effect=" + effect + "]";
	}

}
