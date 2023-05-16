package game.ecs.system;

import java.util.Map;

import game.ecs.FlagECS;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.scenes.GameScene;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.Settings.Actions;
import utils.Settings.Movement;

/**
 * Classe responsable de la gestion des inputs attaches a un node.
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
	 * @param _scene Scene auquelle est attachee le systeme d'inputs.
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
			KeyInputComponent input = entity.getComponent(KeyInputComponent.class);
			Map<Integer, Boolean> inputMap = input.getInputMap();
			
			// Check if component needs to be update
			if (input.getFlag() == FlagECS.STABLE)
			{
				continue;
			}
			
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			
			// Keys pressed
			if (isPressed(KeyCode.Z))
			{
				if (inputMap.containsKey(Movement.UP))
				{
					inputMap.put(Movement.UP, true);
					movement.setFlag(FlagECS.TO_UPDATE);
				}
			}
			if (isPressed(KeyCode.D))
			{
				if (inputMap.containsKey(Movement.RIGHT))
				{
					inputMap.put(Movement.RIGHT, true);
					movement.setFlag(FlagECS.TO_UPDATE);
				}
			}
			if (isPressed(KeyCode.S))
			{
				if (inputMap.containsKey(Movement.DOWN))
				{
					inputMap.put(Movement.DOWN, true);
					movement.setFlag(FlagECS.TO_UPDATE);
				}
			}
			if (isPressed(KeyCode.Q))
			{
				if (inputMap.containsKey(Movement.LEFT))
				{
					inputMap.put(Movement.LEFT, true);
					movement.setFlag(FlagECS.TO_UPDATE);
				}
			}
			if (isPressed(KeyCode.SPACE))
			{
				if (inputMap.containsKey(Actions.ATTACK))
				{
					inputMap.put(Actions.ATTACK, true);
					movement.setFlag(FlagECS.TO_UPDATE);
				}
			}
			if (isPressed(KeyCode.E))
			{
				if (inputMap.containsKey(Actions.INTERACT))
				{
					inputMap.put(Actions.INTERACT, true);
					// TODO
				}
			}
			InventoryComponent inventory = entity.getComponent(InventoryComponent.class);
			if (inventory != null)
			{
				if (isPressed(KeyCode.LEFT))
				{
					if (inputMap.containsKey(Actions.INVENTORY_LEFT))
					{
						inputMap.put(Actions.INVENTORY_LEFT, true);
						inventory.setFlag(FlagECS.TO_UPDATE);
					}
				}
				if (isPressed(KeyCode.RIGHT))
				{
					if (inputMap.containsKey(Actions.INVENTORY_RIGHT))
					{
						inputMap.put(Actions.INVENTORY_RIGHT, true);
						inventory.setFlag(FlagECS.TO_UPDATE);
					}
				}
			}
			if (isPressed(KeyCode.A))
			{
				if (inputMap.containsKey(Actions.USE_ITEM))
				{
					inputMap.put(Actions.USE_ITEM, true);
					inventory.setFlag(FlagECS.TO_UPDATE);
				}
			}
			if (isPressed(KeyCode.G))
			{
				if (inputMap.containsKey(Actions.DROP_ITEM))
				{
					inputMap.put(Actions.DROP_ITEM, true);
					inventory.setFlag(FlagECS.TO_UPDATE);
				}
			}
			
			// Keys released
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
			if (isReleased(KeyCode.SPACE))
			{
				inputMap.put(Actions.ATTACK, false);
				movement.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.E))
			{
				inputMap.put(Actions.INTERACT, false);
				// TODO
			}
			if (isReleased(KeyCode.LEFT))
			{
				inputMap.put(Actions.INVENTORY_LEFT, false);
				inventory.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.RIGHT))
			{
				inputMap.put(Actions.INVENTORY_RIGHT, false);
				inventory.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.A))
			{
				inputMap.put(Actions.USE_ITEM, false);
				inventory.setFlag(FlagECS.TO_UPDATE);
			}
			if (isReleased(KeyCode.G))
			{
				inputMap.put(Actions.DROP_ITEM, false);
				inventory.setFlag(FlagECS.TO_UPDATE);
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
