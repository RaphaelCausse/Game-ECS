package game.ecs.component;

import utils.Point2D;

/**
 * Class that represents a positon component.
 * @see AbstractComponent
 */
public class PositionComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Point2D pos;

	/*----------------------------------------*/

	/**
	 * Constructor of PositionComponent class.
	 * @param x
	 * @param y
	 */
	public PositionComponent(double x, double y)
	{
		pos = new Point2D(x, y);
	}
	
	/**
	 * Translate X position.
	 * @param dx Translate X
	 */
	public void translateX(double dx)
	{
		pos.translateX(dx); 
	}
	
	/**
	 * Translate Y position.
	 * @param dy Translate Y
	 */
	public void translateY(double dy)
	{ 
		pos.translateY(dy);
	}

	/**
	 * Translate position.
	 * @param dx Translate X
	 * @param dy Translate Y
	 */
	public void translate(double dx, double dy)
	{
		pos.translateX(dx);
		pos.translateY(dy);
	}

	/*----------------------------------------*/
	
	/**
	 * Get position point.
	 * @return pos
	 */
	public Point2D getPos() { return pos; }
	
	/**
	 * Get Y position.
	 * @return x
	 */
	public double getX() { return pos.getX(); }

	/**
	 * Get Y position.
	 * @return y
	 */
	public double getY() { return pos.getY(); }
	
	/**
	 * Set position.
	 * @param x New X position
	 * @param y New Y position
	 */
	public void setPos(double x, double y)
	{ 
		pos.setX(x);
		pos.setY(y);
	}
	
	/**
	 * Set X position.
	 * @param x New X position
	 */
	public void setX(double x) { pos.setX(x); }
	
	/**
	 * Set Y position.
	 * @param y New Y position
	 */
	public void setY(double y) { pos.setY(y); }
}
