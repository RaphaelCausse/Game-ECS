package game.ecs.entity.items;

import game.ecs.component.ColliderComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.MapObject;
import utils.Settings.MapObjectsID;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Class that represents a chest key.
 * @see AbstractItem
 */
public class Key extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constuctor of Key class.
	 * @param x X position
	 * @param y Y position
	 */
	public Key(int x, int y)
	{
		super("Chest key");
		initialize(x, y);
	}
	
	@Override
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_KEY, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
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
		// Open chest: update map object
		MapObject targetObject = (MapObject) target;
		targetObject.setImageIndex(MapObjectsID.CHEST_OPEN_B);
		used = true;
		
		// Remove item from inventory
		InventoryComponent ownerInventory = owner.getComponent(InventoryComponent.class);
		ownerInventory.removeItem(this);
	}
	
	/*----------------------------------------*/
}
