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
public class PoisonPotion extends AbstractItem
{
	/*----------------------------------------*/
	
	private int value;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe PoisonPotion.
	 * @param _name Nom de l'item
	 */
	public PoisonPotion(int x, int y)
	{
		super("Poison Potion");
		value = 30;
		initialize(x, y);
	}

	@Override
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_POISON_POTION, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
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
		// Reduice health
		HealthComponent receiverHealth = sender.getComponent(HealthComponent.class);
		int newHealthValue = (receiverHealth.getCurrentHealth() - value < 0) ?
				0 : receiverHealth.getCurrentHealth() - value;
		receiverHealth.setCurrentHeath(newHealthValue);
		used = true;
		
		// Remove item from inventory
		InventoryComponent senderInventory = sender.getComponent(InventoryComponent.class);
		senderInventory.removeItem(this);
	}

	/*----------------------------------------*/
}
