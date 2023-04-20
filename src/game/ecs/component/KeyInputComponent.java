package game.ecs.component;

import java.util.HashMap;
import java.util.Map;
import utils.Settings.Interaction;
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
		inputMap.put(Interaction.ACTIVATE, false);
		inputMap.put(Interaction.ATTACK, false);
		
		// TODO Remove
		System.out.println("Component created : " + this.getClass());
	}
	
	/*----------------------------------------*/
	
	/**
	 * Retourner la valeur de l'input associe a la key dans la collection.
	 * @param key Cle de la collection
	 * @return true, false
	 */
	public Boolean getInput(int key)
	{
		if (inputMap.containsKey(key))
		{
			return inputMap.get(key);
		}
		return false;
	}
	
	/**
	 * Retourner la collection d'inputs.
	 * @return inputMap
	 */
	public Map<Integer, Boolean> getInputMap() { return inputMap; }
}
