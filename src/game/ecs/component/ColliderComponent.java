package game.ecs.component;

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
	}
	
	public BoundingBox getUpdatedBounds(PositionComponent position)
	{
		return new BoundingBox(
			position.getX() + offset.getX(),
			position.getY() + offset.getY(),
			bounds.getWidth(),
			bounds.getHeight()
		);
	}
	
	public void updateCollider(PositionComponent position)
	{
		bounds = getUpdatedBounds(position);
	}
	
	/*----------------------------------------*/
	
	public BoundingBox getBounds() { return bounds; }
	
	public Point2D getOffset() { return offset; }
}
