package character;

public class Stats {

	@Override
	public String toString() {
		return "Health:" + health + "\nStamina: " + stamina + "\nMana: "
				+ mana + "\nStrength: " + strength + "\nIntelligence: "
				+ intelligence + "\nDefense: " + defense;
	}

	private int health;
	private int stamina;
	private int mana;
	private int strength;
	private int intelligence;
	private int defense;

	public Stats(int health, int stamina, int mana, int strength,
			int intelligence, int defense) {
		this.health = health;
		this.stamina = stamina;
		this.mana = mana;
		this.strength = strength;
		this.intelligence = intelligence;
		this.defense = defense;
	}

	public Stats() {
		this.health = 0;
		this.stamina = 0;
		this.mana = 0;
		this.strength = 0;
		this.intelligence = 0;
		this.defense = 0;
	}

	// If we make all damage negative then this will work just fine
	// So stats used as the effect on a weapon will have negative health
	// stats used as a character's condition will have positive health
	public Stats add(Stats other) {
		Stats toReturn = new Stats();

		toReturn.setHealth(this.health + other.getHealth());
		toReturn.setStamina(this.stamina + other.getStamina());
		toReturn.setMana(this.mana + other.getMana());
		toReturn.setStrength(this.strength + other.strength);
		toReturn.setIntelligence(this.intelligence + other.intelligence);
		toReturn.setDefense(this.defense + other.defense);

		return toReturn;

	}

	public Stats multiplier(double multiplier) {
		Stats toReturn = new Stats();

		int health = (int) Math.round((double) this.health * multiplier);
		int stamina = (int) Math.round((double) this.stamina * multiplier);
		int mana = (int) Math.round((double) this.mana * multiplier);
		int strength = (int) Math.round((double) this.strength * multiplier);
		int intelligence = (int) Math.round((double) this.intelligence
				* multiplier);
		int defense = (int) Math.round((double) this.defense * multiplier);

		toReturn.setHealth(health);
		toReturn.setStamina(stamina);
		toReturn.setMana(mana);
		toReturn.setStrength(strength);
		toReturn.setIntelligence(intelligence);
		toReturn.setDefense(defense);

		return toReturn;

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	
}
