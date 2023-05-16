package game.ecs.system;

import game.ecs.FlagECS;
import game.ecs.component.ColliderComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.items.AbstractItem;
import game.graphics.GameMap;
import utils.CollisionBounds;

/**
 * Classe responsable de la gestion des collisions entre les entites.
 * @see AbstractSystem
 */
public class CollisionSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private GameMap map;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe CollisionSystem.
	 */
	public CollisionSystem(GameMap _map)
	{
		super();
		map = _map;
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(ColliderComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Check if component needs to be updated 
			ColliderComponent collider = entity.getComponent(ColliderComponent.class);
			if (collider.getFlag() == FlagECS.STABLE)
			{
				continue;
			}
			
			// Get required components
			PositionComponent position = entity.getComponent(PositionComponent.class);
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			CollisionBounds entityBounds = collider.getBounds();
			collider.updateBounds(position);
			
			// Check map borders collisions
			if (position.getX() <= -collider.getOffset().getX())
			{
				position.setX(-collider.getOffset().getX());
			}
			if (position.getY() <= -collider.getOffset().getY())
			{
				position.setY(-collider.getOffset().getY());
			}
			if (position.getX() + sprite.getSpriteWidth() - collider.getOffset().getX() >= map.getCols() * map.getTileWidth())
			{
				position.setX((map.getCols() * map.getTileWidth()) - (entityBounds.getWidth() + collider.getOffset().getX()));
			}
			if (position.getY() + sprite.getSpriteHeight() - collider.getOffset().getY() + entityBounds.getHeight() >= map.getRows() * map.getTileHeight())
			{
				position.setY((map.getRows() * map.getTileHeight()) - (entityBounds.getHeight() + collider.getOffset().getY()));
			}

			// Check collisions between entities if entity is able to move
			if (collider.isMoveable())
			{
				// Get nearby entities and map object within entity detection bounds
				collider.updateNearbyEntities(map, entity);
				
				// Check collisions with other nearby entities, map objects and items
				for (AbstractEntity nearbyEntity : collider.getNearbyEntities())
				{
				    ColliderComponent nearbyCollider = nearbyEntity.getComponent(ColliderComponent.class);
				    CollisionBounds nearbyBounds = nearbyCollider.getBounds();
				    
				    if (entityBounds.intersects(nearbyBounds))
				    {
				    	// Get item on ground when colliding, add it to the inventory
				    	if (nearbyEntity instanceof AbstractItem)
				    	{
				    		InventoryComponent inventory = entity.getComponent(InventoryComponent.class);
				    		if (!inventory.isFull())
				    		{
				    			inventory.addItem((AbstractItem)nearbyEntity);
				    			EntityManager.removeEntity(nearbyEntity.getUID());
				    		}
				    		continue;
				    	}
				    	
				    	// Collisions with rigid entities
				    	if (collider.doCollides() && nearbyCollider.doCollides())
						{
						    // Determine collision direction
						    double xOverlap = entityBounds.getMaxX() - nearbyBounds.getMinX();
						    double xReverseOverlap = nearbyBounds.getMaxX() - entityBounds.getMinX();
						    double yOverlap = entityBounds.getMaxY() - nearbyBounds.getMinY();
						    double yReverseOverlap = nearbyBounds.getMaxY() - entityBounds.getMinY();
		
						    double smallestOverlap = Math.min(
					    		Math.min(Math.abs(xOverlap), Math.abs(xReverseOverlap)),
					    		Math.min(Math.abs(yOverlap), Math.abs(yReverseOverlap))
					    	);
		
						    double xDisplacement = 0.0;
						    double yDisplacement = 0.0;
		
						    if (smallestOverlap == Math.abs(xOverlap))
						    {
						        xDisplacement = -xOverlap-1;
						    }
						    if (smallestOverlap == Math.abs(xReverseOverlap))
						    {
						        xDisplacement = xReverseOverlap+1;
						    }
						    if (smallestOverlap == Math.abs(yOverlap))
						    {
						        yDisplacement = -yOverlap-1;
						    }
						    if (smallestOverlap == Math.abs(yReverseOverlap))
						    {
						        yDisplacement = yReverseOverlap+1;
						    }
		
						    // Move entity in opposite direction of collision
						    position.translate((int)xDisplacement, (int)yDisplacement);
						    collider.updateBounds(position);
						    continue;
						}
					}
				}
			}
			
			// Restore stable flag
			collider.setFlag(FlagECS.STABLE);
		}
	}
	
	/*----------------------------------------*/
}
