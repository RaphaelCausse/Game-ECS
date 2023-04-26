package game.ecs.component;

import utils.Point2D;

/**
 * Classe qui represente un composant de movement, position et vitesse.
 * @see AbstractComponent
 */
public class MovementComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Point2D pos;
	private int velocity;
	private int state;
	private int direction;
	private int nbDirections;
	
	/*----------------------------------------*/

	/**
	 * Constructeur de la classe MovementComponent.
	 * @param _x Origine en X
	 * @param _y Origine en Y
	 * @param _velocity Vitesse de deplacement
	 * @param _direction Direction initiale
	 * @param _state Etat initial
	 */
	public MovementComponent(int _x, int _y, int _velocity, int _state, int _direction, int _nbDirections)
	{
		super();
		pos = new Point2D(_x, _y);
		velocity = _velocity;
		state = _state;
		direction = _direction;
		nbDirections = _nbDirections;
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

	public int getVelocity() { return velocity; }
	
	public int getState() { return state; }
	
	public int getDirection() { return direction; }
	
	public int getNbDirection() { return nbDirections; }
	
	public void setPos(int x, int y) { pos.setX(x); pos.setY(y); }
	
	public void setVelocity(int _velocity) { velocity = _velocity; }
	
	public void setState(int _state) { state = _state; }
	
	public void setDirection(int _direction) { direction = _direction; }
}
