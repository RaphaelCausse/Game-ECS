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
 * Class that represents a poison malus potion.
 * @see AbstractItem
 */
public class PoisonPotion extends AbstractItem
{
	/*----------------------------------------*/
	
	private int value = 30;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of PoisonPotion class.
	 * @param x X position
	 * @param y Y position
	 */
	public PoisonPotion(int x, int y)
	{
		super("Poison Potion (???)");
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
	public void useItem(AbstractEntity owner, AbstractEntity target)
	{
		// Reduice health
		HealthComponent targetHealth = owner.getComponent(HealthComponent.class);
		int newHealthValue = (targetHealth.getCurrentHealth() - value < 0) ?
				0 : targetHealth.getCurrentHealth() - value;
		targetHealth.setCurrentHeath(newHealthValue);
		used = true;
		
		// Remove item from inventory
		InventoryComponent ownerInventory = owner.getComponent(InventoryComponent.class);
		ownerInventory.removeItem(this);
	}

	/*----------------------------------------*/
}
