package game.ecs.system;

import game.ecs.FlagECS;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InteractComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.MapObject;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.DamagePotion;
import game.ecs.entity.items.HealthPotion;
import game.ecs.entity.items.Key;
import game.ecs.entity.items.PoisonPotion;
import game.graphics.GameMap;
import utils.Settings.Actions;
import utils.Settings.MapObjectsID;

/**
 * Classe responsable de la gestion de l'inventaire.
 * @see AbstractSystem
 */
public class InventorySystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private boolean pressed;
	private GameMap map;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe InventorySystem.
	 */
	public InventorySystem(GameMap _map)
	{
		super();
		pressed = false;
		map = _map;
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
			
			if (inputs != null)
			{
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
		        if (inputs.getInput(Actions.USE_ITEM) == true)
		        {
		        	if (!pressed)
		        	{
		        		if (inventory.getCurrentIndex() < inventory.getInventory().size())
			        	{
			        		AbstractItem item = inventory.getCurrentItem();
			        		
			        		// Item Key
				        	if (item instanceof Key)
				        	{
				        		// Check if there is a chest nearby to open it
				        		DetectionComponent detection = entity.getComponent(DetectionComponent.class);
				        		for (AbstractEntity nearbyEntity : detection.getNearbyEntities())
				        		{
				        			if (nearbyEntity.hasComponent(InteractComponent.class))
				        			{
				        				MapObject interactableObject = (MapObject) nearbyEntity;
				        				InteractComponent interact = interactableObject.getComponent(InteractComponent.class);
				        				if (interactableObject.getImageIndex() == MapObjectsID.CHEST && !interact.isActivated())
				        				{
				        					// Open chest with key
				        					item.useItem(entity, interactableObject);
				        					PositionComponent objectPosition = interactableObject.getComponent(PositionComponent.class);
				        					int row = (int)objectPosition.getY() / map.getTileHeight() - 1;
				        					int col = (int)objectPosition.getX() / map.getTileWidth();
				        					map.layerObjectsAbove[row][col] = MapObjectsID.CHEST_OPEN_T;
				        					break;
				        				}
				        				pressed = true;
				        			}
				        		}
				        	}
				        	// Item DamagePotion
				        	else if (item instanceof DamagePotion)
				        	{
			        			item.useItem(entity, entity);
			        			pressed = true;
				        	}
				        	// Item HealthPotion
				        	else if (item instanceof HealthPotion)
				        	{
				        		HealthComponent health = entity.getComponent(HealthComponent.class);
				        		if (health.getCurrentHealth() < health.getMaxHealth())
				        		{
				        			item.useItem(entity, entity);
				        			pressed = true;
				        		}
				        	}
				        	// Item PoisonPotion
				        	else if (item instanceof PoisonPotion)
				        	{
				        		HealthComponent health = entity.getComponent(HealthComponent.class);
				        		if (health.getCurrentHealth() > 0)
				        		{
				        			item.useItem(entity, entity);
				        			pressed = true;
				        		}
				        	}
			        	}
		        	}
		        	continue;
		        }
		        if (inputs.getInput(Actions.DROP_ITEM) == true)
		        {
		        	if (!pressed)
		        	{
		        		if (inventory.getCurrentIndex() < inventory.getInventory().size())
			        	{
			        		AbstractItem item = inventory.getCurrentItem();
			        		inventory.dropItem(entity, item);
			        		pressed = true;
			        	}
		        	}
		        	continue;
		        }
		            
		        // Key released
		        if (inputs.getInput(Actions.INVENTORY_LEFT) == false || inputs.getInput(Actions.INVENTORY_RIGHT) == false)
		        {
		            pressed = false;
		        }
			}
	        
			// Restore stable flag
			inventory.setFlag(FlagECS.STABLE);
		}
	}

	/*----------------------------------------*/
	
}