package item;

import character.Stats;

public class HealingPotion extends Useable
{
	String name;
	Stats effect;
	int NumberOfTimesAllowed;
	private int numberofTimesUsed;
	
	public HealingPotion()
	{
		this.name = "HealingPotion";
		this.effect = new Stats(25,0,0,0,0,0);
		this.NumberOfTimesAllowed = 1;
		this.numberofTimesUsed = 0;
	}
	public String getName() 
	{
		return this.name;
	}

	@Override
	public String toString() {
		return "HealingPotion [name=" + name + ", effect=" + effect
				+ ", NumberOfTimesAllowed=" + NumberOfTimesAllowed + "]";
	}
	
	

}
