package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.component.ColliderComponent;
import game.ecs.component.FlagECS;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.graphics.Camera;
import game.graphics.GameMap;

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
			
			// Handle map borders
			if (position.getX() <= -collider.getOffset().getX())
			{
				position.setX(-collider.getOffset().getX());
			}
			if (position.getY() <= -collider.getOffset().getY())
			{
				position.setY(-collider.getOffset().getY());
			}
			// TODO
//			if (position.getX() + collider.getBounds().getWidth() >= map.getCols() * map.getTileWidth())
//			{
//				position.setX((map.getCols() * map.getTileWidth()) - (int)collider.getBounds().getWidth() + collider.getOffset().getX());
//			}
//			if (position.getY() + collider.getBounds().getHeight() >= map.getRows() * map.getTileHeight())
//			{
//				position.setY((map.getRows() * map.getTileHeight()) - (int)collider.getBounds().getHeight() + collider.getOffset().getY());
//			}
				
			// Update collider component
			collider.updateCollider(position);
			
			// TODO check collision with other entity nearby
			
			// Restore stable flag
			collider.setFlag(FlagECS.STABLE);
		}

	}
	
	/*----------------------------------------*/
}
