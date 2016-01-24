package item;


import character.Stats;


public class Armor implements Items
{
	

	private String name;
	private Stats effect;
	
	protected Armor()
	{
	    
	}
	
	public Armor(String name, Stats effect)
	{
		this.name = name;
		this.effect = effect;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Stats getEffect() 
	{
		
	    return effect;
		
	}
	
	
	protected void setName(String name)
	{
	    this.name = name;
	}
	
	protected void setEffect(Stats effect)
	{
	    this.effect = effect;
	}
	@Override
	public String toString() {
		return "Armor [name=" + name + ", effect=" + effect + "]";
	}

	@Override
	public int getPrice() {
		
		return 10;
	}


}
