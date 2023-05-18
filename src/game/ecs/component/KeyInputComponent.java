package game.ecs.component;

import java.util.HashMap;
import java.util.Map;
import utils.Settings.Actions;
import utils.Settings.Movement;

/**
 * Class that represents possibles inputs for the entity.
 * @see AbstractComponent
 */
public class KeyInputComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Map<Integer, Boolean> inputMap;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of InputComponent class.
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
		inputMap.put(Actions.INVENTORY_LEFT, false);
		inputMap.put(Actions.INVENTORY_RIGHT, false);
		inputMap.put(Actions.USE_ITEM, false);
		inputMap.put(Actions.DROP_ITEM, false);
	}
	
	/*----------------------------------------*/
	
	/**
	 * Get input.
	 * @param key Key of input
	 * @return true, false
	 */
	public Boolean getInput(int key) { return inputMap.getOrDefault(key, false); }
	
	/**
	 * Get input hashmap containing all possibles inputs of the entity.
	 * @return inputMap
	 */
	public Map<Integer, Boolean> getInputMap() { return inputMap; }
}
