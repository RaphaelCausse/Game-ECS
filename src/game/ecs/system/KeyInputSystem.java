package game.ecs.system;

import java.util.Map;
import game.ecs.EntityManager;
import game.ecs.component.FlagECS;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.entity.AbstractEntity;
import game.scenes.GameScene;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.Settings.Actions;
import utils.Settings.Movement;

/**
 * Classe responsable de la gestion des inputs attache a un node.
 * @see AbstractSystem
 */
public class KeyInputSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private Scene scene;
	private Map<KeyCode, Boolean> keyInputs;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe KeyInputSystem.
	 * @param _scene Scene auquelle est attachee le system d'inputs.
	 */
	public KeyInputSystem(Scene _scene)
	{
		super();
		scene = _scene;
		keyInputs = GameScene.keyInputs;
		initKeyEvents();
	}
	
	/**
	 * Initialisation des key event handlers sur la scene.
	 */
	public void initKeyEvents()
	{	
		// Node gets attached event handlers to it
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			keyInputs.put(event.getCode(), true);
			entities = EntityManager.getEntitiesWithComponent(KeyInputComponent.class);
			for (AbstractEntity entity : entities)
			{
				entity.getComponent(KeyInputComponent.class).setFlag(FlagECS.TO_UPDATE);
			}
		});
		
		scene.addEventHandler(KeyEvent.KEY_RELEASED, event ->
		{
			keyInputs.put(event.getCode(), false);
			entities = EntityManager.getEntitiesWithComponent(KeyInputComponent.class);
			for (AbstractEntity entity : entities)
			{
				entity.getComponent(KeyInputComponent.class).setFlag(FlagECS.TO_UPDATE);
			}
		});
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(KeyInputComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Update key input component
			KeyInputComponent inputs = entity.getComponent(KeyInputComponent.class);
			Map<Integer, Boolean> inputMap = inputs.getInputMap();
			
			// Check if component needs to be update
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			if (inputs.getFlag() == FlagECS.STABLE)
			{
				continue;
			}
			
			// KEY PRESSED
			if (isPressed(KeyCode.Z))
			{
				inputMap.put(Movement.UP, true);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isPressed(KeyCode.D))
			{
				inputMap.put(Movement.RIGHT, true);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isPressed(KeyCode.S))
			{
				inputMap.put(Movement.DOWN, true);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isPressed(KeyCode.Q))
			{
				inputMap.put(Movement.LEFT, true);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isPressed(KeyCode.E))
			{
				inputMap.put(Actions.ACTIVATE, true);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isPressed(KeyCode.SPACE))
			{
				inputMap.put(Actions.ATTACK, true);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			
			// KEY RELEASED
			if (isReleased(KeyCode.Z))
			{
				inputMap.put(Movement.UP, false);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.D))
			{
				inputMap.put(Movement.RIGHT, false);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.S))
			{
				inputMap.put(Movement.DOWN, false);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.Q))
			{
				inputMap.put(Movement.LEFT, false);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.E))
			{
				inputMap.put(Actions.ACTIVATE, false);
			}
			if (isReleased(KeyCode.SPACE))
			{
				inputMap.put(Actions.ATTACK, false);
			}
		}
	}

	/*----------------------------------------*/

	public boolean isPressed(KeyCode key)
	{ 
		if (keyInputs.containsKey(key))
		{
			return (keyInputs.get(key) == true); 
		}
		return false;
	}
	
	public boolean isReleased(KeyCode key)
	{
		if (keyInputs.containsKey(key))
		{
			return (keyInputs.get(key) == false);
		}
		return false;
	}
}
