package game.ecs.component;

import java.util.HashMap;
import java.util.Map;
import utils.Settings.Actions;
import utils.Settings.Movement;

/**
 * Classe qui represente les inputs possibles de l'entite.
 * @see AbstractComponent
 */
public class KeyInputComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Map<Integer, Boolean> inputMap;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe InputComponent.
	 */
	public KeyInputComponent()
	{
		super();
		inputMap = new HashMap<Integer, Boolean>();
		inputMap.put(Movement.UP, false);
		inputMap.put(Movement.RIGHT, false);
		inputMap.put(Movement.DOWN, false);
		inputMap.put(Movement.LEFT, false);
		inputMap.put(Actions.ATTACK, false);
		inputMap.put(Actions.INTERACT, false);
		inputMap.put(Actions.INVENTORY_LEFT, false);
		inputMap.put(Actions.INVENTORY_RIGHT, false);
		inputMap.put(Actions.USE_ITEM, false);
		inputMap.put(Actions.DROP_ITEM, false);
	}
	
	/*----------------------------------------*/
	
	public Boolean getInput(int key) { return inputMap.getOrDefault(key, false); }
	
	public Map<Integer, Boolean> getInputMap() { return inputMap; }
}
