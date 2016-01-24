package item;

import javafx.scene.control.TextArea;
import character.Stats;

public interface Items {
	public Stats getEffect();
	public String getName();

	public int getPrice();
}
