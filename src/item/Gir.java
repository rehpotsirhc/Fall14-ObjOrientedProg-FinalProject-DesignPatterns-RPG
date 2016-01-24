package item;

import character.Stats;

public class Gir extends Weapons
{
	public Gir(String name, Stats equipEffect, Stats useEffect)
	{
		super(name, equipEffect, useEffect);
	}
	public String getName() 
	{
		return this.name;
	}

	@Override
	public String toString() {
		return "LET'S SING THE DOOM SONG!\nDoomDoomDoomDoomDoomDoom!\nGir [name=" + getName() + ", equipEffect=" + getEquipEffect()
				+ ", useEffect=" + getUseEffect() + "]";
	}
	
	@Override
	public int getPrice()
	{
		return 150;
	}
	
}
