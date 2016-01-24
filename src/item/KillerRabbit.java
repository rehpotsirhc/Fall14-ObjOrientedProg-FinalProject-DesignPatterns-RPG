package item;

import character.Stats;

public class KillerRabbit extends Weapons
{
	public KillerRabbit(String name, Stats equipEffect, Stats useEffect)
	{
		super(name, equipEffect, useEffect);
	}
	public String getName() 
	{
		return this.name;
	}

	@Override
	public String toString() {
		return "KillerRabbit [name=" + getName() + ", equipEffect=" + getEquipEffect()
				+ ", useEffect=" + getUseEffect() + "]";
	}
	
	@Override
	public int getPrice()
	{
		return 213;
	}
}
