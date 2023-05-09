package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import game.ecs.FlagECS;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.graphics.GameMap;
import javafx.geometry.BoundingBox;
import utils.Point2D;

/**
 * Classe qui represente un composant de collision.
 * @see AbstractComponent
 */
public class ColliderComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private BoundingBox bounds;
	private Point2D offset;
	private BoundingBox detection;
	private List<AbstractEntity> nearbyEntities;
	
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
	public ColliderComponent(int x, int y, int width, int height, int ox, int oy)
	{
		bounds = new BoundingBox(x + ox, y + ox, width, height);
		offset = new Point2D(ox, oy);
		detection = new BoundingBox(x - width, y - height, 4 * width, 4 * height);
		nearbyEntities = new ArrayList<AbstractEntity>();
		setFlag(FlagECS.TO_UPDATE);
	}
	
	/**
	 * Mettre a jour les bordures de collision.
	 * @param position Position a suivre
	 */
	public void updateBounds(PositionComponent position)
	{
		bounds = new BoundingBox(
			position.getX() + offset.getX(),
			position.getY() + offset.getY(),
			bounds.getWidth(),
			bounds.getHeight()
		);
	}
	
	/**
	 * Mettre a jour la bordures de detection des entites proches.
	 * @param position
	 */
	public void updateDetectionBounds(PositionComponent position)
	{
		detection = new BoundingBox(
			position.getX() - bounds.getWidth(),
			position.getY() - bounds.getHeight(),
			detection.getWidth(),
			detection.getHeight()
		);
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
		
		nearbyEntities.clear();
		
		// Get nearby map object entities within detection bounds
		for (AbstractEntity mapObject : map.getMapObjects())
		{
			ColliderComponent objectCollider = mapObject.getComponent(ColliderComponent.class);
			
			if (detection.intersects(objectCollider.getBounds()))
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
	
	public BoundingBox getBounds() { return bounds; }
	
	public Point2D getOffset() { return offset; }
	
	public BoundingBox getDetectionBounds() { return detection; }
	
	public List<AbstractEntity> getNearbyEntities() { return nearbyEntities; }
	
	public void setBounds(int x, int y, int w, int h) { bounds = new BoundingBox(x, y, w, h); }
}
