package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.graphics.GameMap;
import utils.CollisionBounds;
import utils.Point2D;

public class DetectionComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private CollisionBounds detection;
	private Point2D offset;
	private List<AbstractEntity> nearbyEntities;
	
	/*----------------------------------------*/
	
	public DetectionComponent(int x, int y, int width, int height, int ox, int oy)
	{
		super();
		detection = new CollisionBounds(x - ox, y - oy, width, height);
		offset = new Point2D(ox, oy);
		nearbyEntities = new ArrayList<AbstractEntity>();
	}
	
	/**
	 * Mettre a jour la bordures de detection des entites proches.
	 * @param position Position a suivre
	 */
	public void updateDetectionBounds(AbstractEntity entity)
	{
		PositionComponent position = entity.getComponent(PositionComponent.class);
		double x = position.getX() - detection.getMinX() - offset.getX();
		double y = position.getY() - detection.getMinY() - offset.getY();
		detection.shift(x, y);
	}
	
	/**
	 * Mettre a jour la liste des entites proches dans la map.
	 * @param map Map de jeu
	 * @param entity Entite au centre de la zone de detection
	 */
	public void updateNearbyEntities(GameMap map, AbstractEntity entity)
	{
		// Update detection bounds 
		updateDetectionBounds(entity);
		
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
	
	public CollisionBounds getDetectionBounds() { return detection; }
	
	public List<AbstractEntity> getNearbyEntities() { return nearbyEntities; }
}
