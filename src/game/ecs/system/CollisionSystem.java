package game.ecs.system;

import game.ecs.component.ColliderComponent;
import game.ecs.component.FlagECS;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
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
				position.setX((map.getCols() * map.getTileWidth()) - ((int)collider.getBounds().getWidth() + collider.getOffset().getX()));
			}
			if (position.getY() + sprite.getSpriteHeight() - collider.getOffset().getY() >= map.getRows() * map.getTileHeight())
			{
				position.setY((map.getRows() * map.getTileHeight()) - ((int)collider.getBounds().getHeight() + collider.getOffset().getY()));
			}

			// Check collisions with other entity nearby and map objects
			
			
			// Update collider bounds to follow new position
			collider.updateBounds(position);
			
			// Restore stable flag
			collider.setFlag(FlagECS.STABLE);
		}

	}
	
	/*----------------------------------------*/
}
