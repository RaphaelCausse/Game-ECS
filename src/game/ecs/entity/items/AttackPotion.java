package game.ecs.entity.items;

import game.ecs.component.ColliderComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Class that represents a attack bonus potion.
 */
public class AttackPotion extends AbstractItem
{
	/*----------------------------------------*/
	
	private int value = 20;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of DamagePotion class.
	 * @param x X position
	 * @param y Y position
	 */
	public AttackPotion(int x, int y)
	{
		super("Attack Potion (+20 ATK)");
		initialize(x, y);
	}

	@Override
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_DAMAGE_POTION, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
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
		// Increase damage
		AttackComponent targetDamage = owner.getComponent(AttackComponent.class);
		targetDamage.setDamage(targetDamage.getDamage() + value);
		used = true;
		
		// Remove item from inventory
		InventoryComponent ownerInventory = owner.getComponent(InventoryComponent.class);
		ownerInventory.removeItem(this);
	}

	/*----------------------------------------*/
}
