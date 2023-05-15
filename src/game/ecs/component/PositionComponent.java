package game.ecs.component;

import utils.Point2D;

/**
 * Classe qui represente un composant de position.
 * @see AbstractComponent
 */
public class PositionComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Point2D pos;

	/*----------------------------------------*/

	/**
	 * Constructeur de la classe PositionComponent.
	 * @param x
	 * @param y
	 */
	public PositionComponent(double x, double y)
	{
		pos = new Point2D(x, y);
	}
	
	/**
	 * Translater la position en X.
	 * @param dx Decalage en X
	 */
	public void translateX(double dx)
	{
		pos.translateX(dx); 
	}
	
	/**
	 * Translater la position en Y.
	 * @param dy Decalage en Y
	 */
	public void translateY(double dy)
	{ 
		pos.translateY(dy);
	}

	/**
	 * Translater la position.
	 * @param dx Decalage en X
	 * @param dy Decalage en Y
	 */
	public void translate(double dx, double dy)
	{
		pos.translateX(dx);
		pos.translateY(dy);
	}

	/*----------------------------------------*/
	
	public Point2D getPos() { return pos; }
	
	public double getX() { return pos.getX(); }

	public double getY() { return pos.getY(); }
	
	public void setPos(double x, double y)
	{ 
		pos.setX(x);
		pos.setY(y);
	}
	
	public void setX(double x) { pos.setX(x); }
	
	public void setY(double y) { pos.setY(y); }
}
