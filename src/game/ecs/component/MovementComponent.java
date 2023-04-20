package game.ecs.component;

import utils.Point2D;
import utils.Settings.Movement;

/**
 * Classe qui represente un composant de movement compose de position et de vitesse.
 * @see AbstractComponent
 */
public class MovementComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Point2D pos;
	private int speed;
	
	/*----------------------------------------*/

	/**
	 * Constructeur de la classe MovementComponent.
	 * @param x Abscisse du point
	 * @param y Ordonnee du point
	 */
	public MovementComponent(int _x, int _y, int _speed)
	{
		super();
		pos = new Point2D(_x, _y);
		speed = _speed;
		
		// TODO Remove
		System.out.println("Component created : " + this.getClass());
	}
	
	/**
	 * Constructeur par defaut de la classe MovementComponent.
	 */
	public MovementComponent()
	{
		this(0, 0, 0);
	}
	
	/*----------------------------------------*/

	/**
	 * Retourner la position.
	 * @return position
	 */
	public Point2D getPos() { return pos; }
	
	/**
	 * Definir la position en abscisse et en ordonnee.
	 * @param x Abcsisse
	 * @param y	Ordonnee
	 */
	public void setPos(int x, int y)
	{
		pos.setX(x);
		pos.setY(y);
	}
	
	/**
	 * Retourner la vitesse de deplacement;
	 * @return speed
	 */
	public int getSpeed() { return speed; }
	
	/**
	 * Definir la vitesse de deplacement.
	 * @param _speed Vitesse de deplacement
	 */
	public void setSpeed(int _speed) { speed = _speed; }
	
	/**
	 * Faire une translation sur l'axe des abscisses.
	 * @param dx Portion d'abscisse supplementaire
	 */
	public void translateX(int dx) { pos.translateX(dx); }
	
	/**
	 * Faire une translation sur l'axe des abscisses.
	 * @param dx Portion d'abscisse supplementaire
	 */
	public void translateY(int dy) { pos.translateY(dy); }
	
	/**
	 * Faire une translation sur les deux axes.
	 * @param dx Portion d'abscisse supplementaire
	 * @param dy Portion d'ordonnee supplmentaire
	 */
	public void translate(int dx, int dy)
	{
		pos.translateX(dx);
		pos.translateY(dy);
	}
}
