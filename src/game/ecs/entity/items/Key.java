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
 * Classe qui represente un item cle de coffre.
 * @see AbstractItem
 */
public class Key extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constucteur de la classe Key.
	 * @param x Position sur la map en X
	 * @param y Position sur la map en Y
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
	public void useItem(AbstractEntity sender, AbstractEntity receiver)
	{
		// Open chest: update map object
		MapObject receiverObject = (MapObject) receiver;
		receiverObject.setImageIndex(MapObjectsID.CHEST_OPEN_B);
		used = true;
		
		// Remove item from inventory
		InventoryComponent senderInventory = sender.getComponent(InventoryComponent.class);
		senderInventory.removeItem(this);
	}
	
	/*----------------------------------------*/
}
