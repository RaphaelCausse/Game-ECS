package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import utils.CollisionBounds;
import utils.Point2D;

/**
 * Classe qui represente un composant de collision.
 * Class that represents collision bounds of the entity.
 * @see AbstractComponent
 */
public class ColliderComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private CollisionBounds bounds;
	private Point2D offset;
	private boolean isMoveable;
	private boolean collides;
	public List<Integer> mapObjectsAllowedForCollision;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of ColliderComponent class.
	 * @param x Top left corner X position
	 * @param y Top left corner Y position
	 * @param width Bounds width
	 * @param height Bounds height
	 * @param ox Offset X
	 * @param oy Offset Y
	 */
	public ColliderComponent(int x, int y, int width, int height, int ox, int oy, boolean _isMoveable)
	{
		bounds = new CollisionBounds(x + ox, y + oy, width, height);
		offset = new Point2D(ox, oy);
		isMoveable = _isMoveable;
		collides = true;
		mapObjectsAllowedForCollision = new ArrayList<>();
	}
	
	/**
	 * Update collisions bounds to follow entity position.
	 * @param position Position to follow
	 */
	public void updateBounds(PositionComponent position)
	{
		double x = position.getX() - bounds.getMinX() + offset.getX();
		double y = position.getY() - bounds.getMinY() + offset.getY();
		bounds.shift(x, y);
	}
		
	/*----------------------------------------*/
	
	/**
	 * Get collision bounds.
	 * @return bounds
	 */
	public CollisionBounds getBounds() { return bounds; }
	
	/**
	 * Get collision bounds offset.
	 * @return offset
	 */
	public Point2D getOffset() { return offset; }
	
	/**
	 * Check if is moveable to resolve collisions.
	 * @return true, false
	 */
	public boolean isMoveable() { return isMoveable; }
	
	/**
	 * Check if entity does collides.
	 * @return true, false
	 */
	public boolean doCollides() { return collides; }
	
	/**
	 * Set collisions bounds.
	 * @param x Top left corner X position
	 * @param y Top left corner Y position
	 * @param width Bounds width
	 * @param height Bounds height
	 */
	public void setBounds(double x, double y, double w, double h) { bounds = new CollisionBounds(x, y, w, h); }
	
	/**
	 * Set entity do collides
	 * @param _collides
	 */
	public void setCollides(boolean _collides) { collides = _collides; }
}
