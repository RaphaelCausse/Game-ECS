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
	public PositionComponent(int x, int y)
	{
		pos = new Point2D(x, y);
		setFlag(FlagECS.TO_UPDATE);
	}
	
	public void translateX(int dx)
	{
		pos.translateX(dx); 
	}
	
	public void translateY(int dy)
	{ 
		pos.translateY(dy);
	}

	public void translate(int dx, int dy)
	{
		pos.translateX(dx);
		pos.translateY(dy);
	}

	/*----------------------------------------*/
	public Point2D getPos() { return pos; }
	
	public int getX() { return pos.getX(); }

	public int getY() { return pos.getY(); }
	
	public void setPos(int x, int y)
	{ 
		pos.setX(x);
		pos.setY(y);
	}
	
	public void setX(int x) { pos.setX(x); }
	
	public void setY(int y) { pos.setY(y); }
}
