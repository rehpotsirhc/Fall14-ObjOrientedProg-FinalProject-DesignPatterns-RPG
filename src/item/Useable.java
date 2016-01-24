package item;
import character.Stats;


public class Useable implements Items
{
	String name;
	Stats effect;
	int NumberOfTimesAllowed;
	private int numberofTimesUsed;
	
	public Useable()
	{
		
	}
	
	public Useable(String name, Stats effect, int NumberOfTimesAllowed)
	{
		this.name = name;
		this.effect = effect;
		this.NumberOfTimesAllowed = NumberOfTimesAllowed;
		this.numberofTimesUsed = 0;
	}
	
	public String getName() {
		return this.name;
	}

	public int getNumberOfTimesAllowed() {
		return NumberOfTimesAllowed;
	}
	
	/*
	 * returns true if its been used less than its allowed
	 */
	public boolean canUse()
	{
	    return numberofTimesUsed < NumberOfTimesAllowed;
	}
	
	public void use()
	{
	    numberofTimesUsed++;
	}

	@Override
	public Stats getEffect() 
	{
		return effect;
		
	}

	@Override
	public String toString() {
		return "Useable [name=" + name + ", effect=" + effect
				+ ", NumberOfTimesAllowed=" + NumberOfTimesAllowed + "]";
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 6;
	}
	
	
}
