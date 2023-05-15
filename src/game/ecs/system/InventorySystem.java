package game.ecs.system;

import game.ecs.FlagECS;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import utils.Settings.Actions;

/**
 * Classe responsable de la gestion de l'inventaire.
 * @see AbstractSystem
 */
public class InventorySystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private boolean pressed;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe InventorySystem.
	 */
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
			
			// Update component to move current inventory item index
			KeyInputComponent inputs = entity.getComponent(KeyInputComponent.class);
			
			// Key pressed or repeated
	        if (inputs.getInput(Actions.INVENTORY_LEFT) == true)
	        {
	            if (!pressed)
	            {
	                inventory.moveCurrentIndex(-1);
	                pressed = true;
	            }
	            continue;
	        }
	        if (inputs.getInput(Actions.INVENTORY_RIGHT) == true)
	        {
	            if (!pressed)
	            {
	                inventory.moveCurrentIndex(1);
	                pressed = true;
	            }
	            continue;
	        }
	            
	        // Key released
	        if (inputs.getInput(Actions.INVENTORY_LEFT) == false || inputs.getInput(Actions.INVENTORY_RIGHT) == false)
	        {
	            pressed = false;
	        }
	        
			
			// Restore stable flag
			inventory.setFlag(FlagECS.STABLE);
		}
	}

	/*----------------------------------------*/
	
}