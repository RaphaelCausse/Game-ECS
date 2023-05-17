package game.ecs.component;

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
		offset = new Point2D(ox, oy);
		isMoveable = _isMoveable;
		collides = true;
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
		
	/*----------------------------------------*/
	
	public CollisionBounds getBounds() { return bounds; }
	
	public Point2D getOffset() { return offset; }
	
	public boolean isMoveable() { return isMoveable; }
	
	public boolean doCollides() { return collides; }
	
	public void setBounds(double x, double y, double w, double h) { bounds = new CollisionBounds(x, y, w, h); }
	
	public void setCollides(boolean _collides) { collides = _collides; }
}
