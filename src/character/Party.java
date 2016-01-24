package character;

import item.Armor;
import item.Items;
import item.Useable;
import item.Weapons;

import java.util.LinkedList;
import java.util.List;

public class Party {
    private Player first;
    private Player second;

    private int partyGold;

    private LinkedList<Items> inventory;

    public Party() {
	first = null;
	second = null;
	inventory = new LinkedList<Items>();
	partyGold = 0;
    }

    public Party(Player one) {
	first = one;
	second = null;
	inventory = new LinkedList<Items>();
	partyGold = 100;
    }

    public Party(Party curr, Player two) {
	first = curr.getPlayerOne();
	second = two;
	inventory = new LinkedList<Items>();
	partyGold = 100;
    }

    public Party(Player one, Player two) {
	first = one;
	second = two;
	inventory = new LinkedList<Items>();
	partyGold = 100;
    }

    public Player getPlayerOne() {
	return first;
    }

    public Player getPlayerTwo() {
	return second;
    }

    public void setPlayerOne(Player character) {
	first = character;
    }

    public void setPlayerTwo(Player character) {
	second = character;
    }

    public void addPartyGold(int amount) {
	partyGold += amount;
    }

    public void subPartyGold(int amount) {
	partyGold -= amount;
    }

    public int getPartyGold() {
	return partyGold;
    }

    public LinkedList<Items> getInventory() {
	return inventory;
    }

    public void setInventory(LinkedList<Items> oldInv) {
	inventory = oldInv;
    }

    public void addItem(Items i) {

	if (!inventory.contains(i))
	    inventory.add(i);
    }

    public List<Useable> getUseables() {
	List<Useable> useables = new LinkedList<Useable>();

	for (Items i : inventory) {
	    if (i instanceof Useable) {
		useables.add((Useable) i);
	    }
	}

	return useables;
    }

    public List<Armor> getArmor() {
	
	List<Armor> armors = new LinkedList<Armor>();

	for (Items i : inventory) {
	    if (i instanceof Armor) {
		armors.add((Armor) i);
	    }
	}

	return armors;
    }

    public List<Weapons> getWeapons() {
	List<Weapons> weapons = new LinkedList<Weapons>();

	for (Items i : inventory) {
	    if (i instanceof Weapons) {
		weapons.add((Weapons) i);
	    }
	}

	return weapons;
    }
    

}
