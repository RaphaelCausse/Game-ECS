package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.graphics.GameMap;
import utils.CollisionBounds;
import utils.Point2D;

/**
 * Class that represents a detection bounds for the entity.
 */
public class DetectionComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private CollisionBounds detection;
	private Point2D offset;
	private List<AbstractEntity> nearbyEntities;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of DetectionComponent class.
	 * @param x Top left corner X position
	 * @param y Top left corner Y position
	 * @param width Bounds width
	 * @param height Bounds height
	 * @param ox Offset X
	 * @param oy Offset Y
	 */
	public DetectionComponent(int x, int y, int width, int height, int ox, int oy)
	{
		super();
		detection = new CollisionBounds(x + ox, y + oy, width, height);
		offset = new Point2D(ox, oy);
		nearbyEntities = new ArrayList<AbstractEntity>();
	}
	
	/**
	 * Update detection bounds to follow entity position.
	 * @param position Position to follow
	 */
	public void updateDetectionBounds(PositionComponent position)
	{
		double x = position.getX() - detection.getMinX() - offset.getX();
		double y = position.getY() - detection.getMinY() - offset.getY();
		detection.shift(x, y);
	}
	
	/**
	 * Update list of nearby entities that are within the detection bounds.
	 * @param map Game map
	 * @param entity Entity at center of detection bounds
	 */
	public void updateNearbyEntities(GameMap map, AbstractEntity entity)
	{
		// Update detection bounds 
		updateDetectionBounds(entity.getComponent(PositionComponent.class));
		
		// Get nearby map object within detection bounds
		nearbyEntities.clear();
		for (AbstractEntity mapObject : map.getMapObjects())
		{
			ColliderComponent objectCollider = mapObject.getComponent(ColliderComponent.class);
			
			if (objectCollider != null && detection.intersects(objectCollider.getBounds()))
			{
				nearbyEntities.add(mapObject);
			}
		}
		// Get nearby entities within detection bounds
		for (AbstractEntity otherEntity : EntityManager.getEntitiesWithComponent(ColliderComponent.class))
		{
			if (entity.equals(otherEntity))
			{
				continue;
			}
			
			ColliderComponent entityCollider = otherEntity.getComponent(ColliderComponent.class);
			if (detection.intersects(entityCollider.getBounds()))
			{
				nearbyEntities.add(otherEntity);
			}
		}
	}

	/*----------------------------------------*/
	
	/**
	 * Get detection bounds.
	 * @return detection
	 */
	public CollisionBounds getDetectionBounds() { return detection; }
	
	/**
	 * Get list of nearby entities.
	 * @return nearbyEntities
	 */
	public List<AbstractEntity> getNearbyEntities() { return nearbyEntities; }
}
