package item;
import character.Stats;



public class Weapons implements Items
{
	protected String name;
	private Stats equipEffect; //this is the effect the character gets from just equiping the weapon. 
	private Stats useEffect; //this is the effect the weapon's attack has
	
	protected Weapons()
	{
		
	}
	
	public Weapons(String name, Stats equipEffect, Stats useEffect)
	{
		this.name = name;
		this.equipEffect = equipEffect;
		this.useEffect = useEffect;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Stats getEffect() 
	{
		return equipEffect;
		
	}
	
	public Stats getEquipEffect()
	{
	    return equipEffect;
	}
	
	public Stats getUseEffect()
	{
	    return useEffect;
	}

	@Override
	public String toString() {
		return "Weapons [name=" + name + ", equip effect=" + equipEffect + ", use effect = "+useEffect+"]";
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 13;
	}

}