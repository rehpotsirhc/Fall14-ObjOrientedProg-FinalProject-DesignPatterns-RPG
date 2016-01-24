package character.types;

import character.Character;

//Command pattern  this is the "remote / switch" 
public interface IAttackClass {

	public void attack(Character attacker, Character target);

	public void defend(Character defender);

}
