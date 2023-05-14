package game.ecs.component;

import java.util.HashMap;
import java.util.Map;

import game.ecs.FlagECS;
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
		inputMap.put(Actions.INVENTORY_LEFT, false);
		inputMap.put(Actions.INVENTORY_RIGHT, false);
		setFlag(FlagECS.TO_UPDATE);
	}
	
	/*----------------------------------------*/
	
	public Boolean getInput(int key) { return inputMap.getOrDefault(key, false); }
	
	public Map<Integer, Boolean> getInputMap() { return inputMap; }
}
