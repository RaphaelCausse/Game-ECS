package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.AbstractEntity;
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
	private List<AbstractEntity> nearbyEntities;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe ColliderComponent.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ColliderComponent(int x, int y, int width, int height, int ox, int oy)
	{
		bounds = new BoundingBox(x + ox, y + ox, width, height);
		offset = new Point2D(ox, oy);
		setFlag(FlagECS.TO_UPDATE);
		nearbyEntities = new ArrayList<AbstractEntity>();
	}
	
	public void updateNearbyEntities()
	{
		
	}
	
	public void updateBounds(PositionComponent position)
	{
		bounds = new BoundingBox(
			position.getX() + offset.getX(),
			position.getY() + offset.getY(),
			bounds.getWidth(),
			bounds.getHeight()
		);
	}
	
	/*----------------------------------------*/
	
	public BoundingBox getBounds() { return bounds; }
	
	public Point2D getOffset() { return offset; }
}
