package game.ecs.system;

import game.ecs.FlagECS;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import utils.Settings.Actions;

/**
 *
 */
public class InventorySystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private boolean pressed;
	
	/*----------------------------------------*/
	
	public InventorySystem()
	{
		super();
		pressed = false;
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(InventoryComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Check if component needs to be updated
			InventoryComponent inventory = entity.getComponent(InventoryComponent.class);
			if (inventory.getFlag() == FlagECS.STABLE)
			{
				continue;
			}

			// TODO FIX THIS SHIT
			
			
			// Update component
			KeyInputComponent inputs = entity.getComponent(KeyInputComponent.class);
			
			// Key pressed
			if (inputs.getInput(Actions.INVENTORY_LEFT) == true && pressed == false)
			{
				System.out.println("pressed");
				inventory.moveCurrentIndex(-1);
				pressed = true;
				break;
			}
			if (inputs.getInput(Actions.INVENTORY_RIGHT) == true && pressed == false)
			{
				System.out.println("pressed");
				inventory.moveCurrentIndex(1);
				pressed = true;
				break;
			}
			
			// Key released
			if (inputs.getInput(Actions.INVENTORY_LEFT) == false && pressed == true)
			{
				System.out.println("released");
				pressed = false;
			}
			if (inputs.getInput(Actions.INVENTORY_RIGHT) == false && pressed == true)
			{
				System.out.println("released");
				pressed = false;
			}
			
			// Restore stable flag
			inventory.setFlag(FlagECS.STABLE);
		}
	}

	/*----------------------------------------*/
	
}