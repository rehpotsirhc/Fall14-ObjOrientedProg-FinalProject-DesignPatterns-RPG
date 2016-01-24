package item;

import character.Stats;

public class HolyHandGrenade extends Useable
{
	
	
	public HolyHandGrenade(String name, Stats effect, int numAllowed)
	{
		super(name, effect, numAllowed);
	}
	public String getName() 
	{
		return this.name;
	}

	@Override
	public String toString() {
		return "HolyHandGrenade [name=" + name + ", effect=" + effect
				+ ", NumberOfTimesAllowed=" + NumberOfTimesAllowed
				+ ", numberofTimesUsed=" + canUse() + "]";
	}
	
	@Override
	public int getPrice()
	{
		return 100;
	}
}
