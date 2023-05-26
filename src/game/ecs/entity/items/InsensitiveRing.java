package game.ecs.entity.items;

import game.ecs.component.ColliderComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import utils.Settings.MapObjectsID;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Class that represents an insensitive ring, to go through specific map object.
 * @see AbstractItem
 */
public class InsensitiveRing extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of InsensitiveRing class.
	 * @param x X position
	 * @param y Y position
	 */
	public InsensitiveRing(int x, int y)
	{
		super("Insensitive Ring");
		initialize(x, y);
	}

	@Override
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_INSENSITIVE_RING, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
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
		ColliderComponent ownerCollider = owner.getComponent(ColliderComponent.class);
		ownerCollider.mapObjectsAllowedForCollision.add(MapObjectsID.TOMBSTONE_RIP);
	}

	/*----------------------------------------*/
}
