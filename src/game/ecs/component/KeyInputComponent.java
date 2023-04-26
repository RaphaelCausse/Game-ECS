package game.ecs.component;

import java.util.HashMap;
import java.util.Map;
import utils.Settings.Actions;
import utils.Settings.Movement;

/**
 * Classe qui indique au KeyInput system que l'entity recoit des inputs.
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
		inputMap.put(Actions.ACTIVATE, false);
		inputMap.put(Actions.ATTACK, false);
	}
	
	/*----------------------------------------*/
	
	public Boolean getInput(int key)
	{
		if (inputMap.containsKey(key))
		{
			return inputMap.get(key);
		}
		return false;
	}
	
	public Map<Integer, Boolean> getInputMap() { return inputMap; }
}
