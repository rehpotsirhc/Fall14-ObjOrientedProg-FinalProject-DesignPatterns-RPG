package game;

import item.Armor;
import item.Useable;

import java.util.Scanner;

import command.Attack;
import command.Defend;
import command.ICommand;
import command.Invoker;
import command.Special;
import command.UseItem;
import character.CharacterFactory;
import character.Enemy;
import character.Player;
import character.Character;

/*
 * This example shows the basic use of the commands, the invoker, players, enemies, and factories
 * 
 * It's definitely not complete.
 * 
 * It doesn't handle what happens when a player or enemy dies, e.g., giving loot (this method is in the enemy class, just not used
 * 
 * It also doesn't handle a player accessing their inventory to equip armor or use items
 */

public class Example {

	private static CharacterFactory charFactory;
	private static Player mage;
	private static Player warrior;
	private static Enemy one;
	private static Enemy two;
	private static Invoker invoker;

	public static void main(String[] args) {

		int level = 1;

		// initialize game

		invoker = new Invoker();

		charFactory = new CharacterFactory();
		mage = charFactory.createMage("Gandalf");
		warrior = charFactory.createFighter("Richard");

		// create some enemies

		one = charFactory.createEnemy(level);
		two = charFactory.createEnemy(level);

		Scanner a = new Scanner(System.in);
		String next = a.nextLine();
		while (next.equals("quit") == false) {
			doActionBasedOnStringInput(next);

			next = a.nextLine();
		}
		a.close();

	}

	private static void doActionBasedOnStringInput(String s) {
		ICommand c;
		if (s.equals("mage attack one")) {
			c = new Attack(mage, one);
			invoker.addCommand(c);

		}
		if (s.equals("mage special two")) {
			c = new Special(mage, two);
			invoker.addCommand(c);
		}
		if (s.equals("warrior attack one")) {
			c = new Attack(warrior, one);
			invoker.addCommand(c);
		}
		if (s.equals("warrior defend")) {
			c = new Defend(warrior);
			invoker.addCommand(c);
		}

		
		if (s.equals("execute")) {
			invoker.runCommand();

			printStats(warrior);
			printStats(mage);
			printStats(one);
			printStats(two);

		}

	}

	private static void printStats(Character c) {
		System.out.println(c.currentCondition());

	}

}
