package item;

import character.Stats;

public class CompositeArmor extends Armor {
	private Armor armor01;
	private Armor armor02;

	public CompositeArmor(Armor armor01, Armor armor02) {
		this.armor01 = armor01;
		this.armor02 = armor02;
		this.setName("Super" + armor01.getName() + armor02.getName());
		this.setEffect(armor01.getEffect().add(armor02.getEffect()));
	}

	@Override
	public String toString() {
		return "CompositeArmor [armor01=" + armor01 + ", armor02=" + armor02
				+ ", name=" + getName() + ", effect=" + getEffect() + "]";
	}

}
