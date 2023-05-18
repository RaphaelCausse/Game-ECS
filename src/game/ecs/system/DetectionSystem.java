package game.ecs.system;

import game.ecs.component.DetectionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.graphics.GameMap;

/**
 * Class responsible of detections.
 * @see AbstractSystem
 */
public class DetectionSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private GameMap map;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of DetectionSystem class.
	 * @param _map Game map
	 */
	public DetectionSystem(GameMap _map)
	{
		super();
		map = _map;
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(DetectionComponent.class);
		for (AbstractEntity entity : entities)
		{
			if (entity.hasComponent(DetectionComponent.class))
			{
				// Get nearby entities and map object within entity detection bounds
				DetectionComponent detection = entity.getComponent(DetectionComponent.class);
				detection.updateNearbyEntities(map, entity);
			}
		}
	}

	/*----------------------------------------*/
}
