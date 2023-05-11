package game.ecs.component;

import java.util.ArrayList;
import java.util.List;
import game.ecs.FlagECS;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.graphics.GameMap;
import utils.CollisionBounds;
import utils.Point2D;

/**
 * Classe qui represente un composant de collision.
 * @see AbstractComponent
 */
public class ColliderComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private CollisionBounds bounds;
	private CollisionBounds detection;
	private List<AbstractEntity> nearbyEntities;
	private Point2D offset;
	private boolean isMoveable;
	private boolean collides;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe ColliderComponent.
	 * @param x Position en X
	 * @param y Position en Y
	 * @param width Largeur de la box de collision
	 * @param height Hauteur de la box de collision
	 * @param ox Offset X 
	 * @param oy Offset Y
	 */
	public ColliderComponent(int x, int y, int width, int height, int ox, int oy, boolean _isMoveable)
	{
		bounds = new CollisionBounds(x + ox, y + oy, width, height);
		detection = new CollisionBounds(x + ox - (2 * width), y + oy - (2 * height), 5 * width, 5 * height);
		nearbyEntities = new ArrayList<AbstractEntity>();
		offset = new Point2D(ox, oy);
		isMoveable = _isMoveable;
		collides = false;
		setFlag(FlagECS.TO_UPDATE);
	}
	
	/**
	 * Mettre a jour les bordures de collision par rapport a la position.
	 * @param position Position a suivre
	 */
	public void updateBounds(PositionComponent position)
	{
		double x = position.getX() - bounds.getMinX() + offset.getX();
		double y = position.getY() - bounds.getMinY() + offset.getY();
		bounds.shift(x, y);
	}
	
	/**
	 * Mettre a jour la bordures de detection des entites proches.
	 * @param position
	 */
	public void updateDetectionBounds(PositionComponent position)
	{
		double x = position.getX() - detection.getMinX() + offset.getX() - (2 * bounds.getWidth());
		double y = position.getY() - detection.getMinY() + offset.getY() - (2 * bounds.getHeight());
		detection.shift(x, y);
	}
	
	/**
	 * Mettre a jour la liste des entites proches dans la map.
	 * @param entity
	 */
	public void updateNearbyEntities(GameMap map, AbstractEntity entity)
	{
		PositionComponent position = entity.getComponent(PositionComponent.class);
		
		// Update detection bounds 
		updateDetectionBounds(position);
		
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
	
	public CollisionBounds getBounds() { return bounds; }
	
	public Point2D getOffset() { return offset; }
	
	public CollisionBounds getDetectionBounds() { return detection; }
	
	public List<AbstractEntity> getNearbyEntities() { return nearbyEntities; }
	
	public boolean isMoveable() { return isMoveable; }
	
	public boolean collides() { return collides; }
	
	public void setBounds(double x, double y, double w, double h) { bounds = new CollisionBounds(x, y, w, h); }
	
	public void setCollides(boolean _collides) { collides = _collides; }
}
