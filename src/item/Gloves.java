package item;

import character.Stats;

public class Gloves extends Armor
{
	private String name;
	private Stats effect;
	
	public Gloves()
	{
		name = "Gloves";
		effect = new Stats(0,5,0,5,0,10);
	}

	public String getName() 
	{
		return this.name;
	}

	public Stats getEffect() 
	{
		return effect;
	}

	@Override
	public String toString() {
		return "Gloves [name=" + name + ", effect=" + effect + "]";
	}
	
}
