package item;

import character.Stats;

public class Helmet extends Armor
{
	private String name;
	private Stats effect;
	
	public Helmet()
	{
		name = "Helmet";
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
		return "Helmet [name=" + name + ", effect=" + effect + "]";
	}

	
}
