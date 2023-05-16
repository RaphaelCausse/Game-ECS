package game.ecs.entity.items;

import game.ecs.component.ColliderComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 *
 */
public class HealthPotion extends AbstractItem
{
	/*----------------------------------------*/
	
	private int restoreValue;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe HealthPotion.
	 * @param _name Nom de l'item
	 */
	public HealthPotion(int x, int y)
	{
		super("Health Potion");
		restoreValue = 10;
		initialize(x, y);
	}

	@Override
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_HEALTH_POTION, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
		addComponent(sprite);
		
		ColliderComponent collider = new ColliderComponent(
			x,							// x
			y,							// y
			sprite.getSpriteWidth(),	// w
			sprite.getSpriteHeight(),	// h
			0,							// ox
			0,							// oy
			false						// isMoveable
		);
		collider.setCollides(false);
		addComponent(collider);
	}
	
	@Override
	public void useItem(AbstractEntity sender, AbstractEntity receiver)
	{
		// Restore health
		HealthComponent receiverHealth = sender.getComponent(HealthComponent.class);
		int newHealthValue = (receiverHealth.getCurrentHealth() + restoreValue > receiverHealth.getMaxHealth()) ?
			receiverHealth.getMaxHealth() :
			receiverHealth.getCurrentHealth() + restoreValue;
		receiverHealth.setCurrentHeath(newHealthValue);
		used = true;
		
		// Remove item from inventory
		InventoryComponent senderInventory = sender.getComponent(InventoryComponent.class);
		senderInventory.removeItem(this);
	}

	/*----------------------------------------*/
}
