package character;

import item.Armor;
import item.Weapons;

public class Equipped {
	private Weapons weapon;
	private Armor armor1;
	private Armor armor2;
	private Armor armor3;
	
	public Equipped(){
		weapon = null;
		armor1 = null;
		armor2 = null;
		armor3 = null;
	}
	
	public Armor equipArmor(Armor myArmor){
		if(armor1 == null){
			armor1 = myArmor;
			return null;
		}
		else if(armor2 == null){
			armor2 = myArmor;
			return null;
		}
		else if(armor3 == null){
			armor3 = myArmor;
			return null;
		}
		else{
			Armor oldArmor = armor1;
			armor1 = myArmor;
			return oldArmor;
		}
	}
	
	public Stats getArmorValue(){
		Stats currStats = new Stats();
		if(armor1 != null){
			currStats.add(armor1.getEffect());
		}
		if(armor2 != null){
			currStats.add(armor2.getEffect());
		}
		if(armor3 != null){
			currStats.add(armor3.getEffect());
		}
		return currStats;
	}
	
	public Stats getWeaponValue(){
		if(weapon != null){
			return weapon.getEquipEffect();
		}
		else return new Stats();
	}
	
	public Weapons getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}
	
	public Armor getArmor1() {
		return armor1;
	}
	public void setArmor1(Armor armor1) {
		this.armor1 = armor1;
	}
	
	public Armor getArmor2() {
		return armor2;
	}
	public void setArmor2(Armor armor2) {
		this.armor2 = armor2;
	}
	public Armor getArmor3() {
		return armor3;
	}
	public void setArmor3(Armor armor3) {
		this.armor3 = armor3;
	}
	
	
}
