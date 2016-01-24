package game;

import item.Armor;
import item.CompositeArmor;
import character.Stats;

public class driver {
	public static void main(String[] args) {

		// //these 8 lines of code are for the command pattern
		// Fight newAttack = new Sword();//these 4 lines are for a normal attack
		// Attack onCommand = new Attack(newAttack);
		// DeviceButton onPressed = new DeviceButton(onCommand);
		// onPressed.press(); //press button
		//
		// Fight speciaLattack = new Sword();//these 4 lines are for special
		// attack
		// SpecialAttack onCommando = new SpecialAttack(speciaLattack);
		// DeviceButton onPressedo = new DeviceButton(onCommando);
		// onPressedo.press();
		//
		// Fight defends = new Sword();
		// Defend onCommandoo = new Defend(defends);
		// DeviceButton onPressed3 = new DeviceButton(onCommandoo);
		// onPressed3.press();
		//
		// just testing out the CompositeArmor class
		Armor helmet = new Armor("helmet", new Stats(1, 1, 1, 1, 1, 1));
		Armor chestPlate = new Armor("chestplate", new Stats(1, 1, 1, 1, 1, 1));

		CompositeArmor ca = new CompositeArmor(helmet, chestPlate);

		System.out.println(ca);

	}
}
