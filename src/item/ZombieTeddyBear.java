package item;

import character.Stats;

public class ZombieTeddyBear extends Weapons
{
	public ZombieTeddyBear(String name, Stats equipEffect, Stats useEffect)
	{
		super(name, equipEffect, useEffect);
	}
	public String getName() 
	{
		return this.name;
	}

	@Override
	public String toString() {
		return "ZombieTeddyBear [name=" + getName() + ", equipEffect=" + getEquipEffect()
				+ ", useEffect=" + getUseEffect() + "]";
	}
	
	@Override
	public int getPrice()
	{
		return 200;
	}
	
	
	
}
