package game.ecs.component;

import game.ecs.entity.AbstractEntity;
import utils.Settings.Movement;

/**
 * Classe qui represente un composant de movement.
 * @see AbstractComponent
 */
public class MovementComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private double velocity;
	private int direction;
	private boolean canMove;
	
	/*----------------------------------------*/

	/**
	 * Constructeur de la classe MovementComponent.
	 * @param _velocity Vitesse de deplacement
	 * @param _direction Direction du deplacement
	 */
	public MovementComponent(double _velocity, int _direction)
	{
		super();
		velocity = _velocity;
		direction = _direction;
		canMove = true;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void moveToTarget(AbstractEntity entity, AbstractEntity target)
	{
		// Make the move every two frames
		if (canMove)
		{
			// Get components
			PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
			MovementComponent entityMovement = entity.getComponent(MovementComponent.class);
			ColliderComponent entityCollider = entity.getComponent(ColliderComponent.class);
			ColliderComponent targetCollider = target.getComponent(ColliderComponent.class);
			
			// Move
	        double dx = targetCollider.getBounds().getCenterX() - entityCollider.getBounds().getCenterX();
	        double dy = targetCollider.getBounds().getCenterY() - entityCollider.getBounds().getCenterY();
	        double angle = Math.atan2(dy, dx);
	        double px =  Math.cos(angle);
	        double py =  Math.sin(angle);
	        double x = px * entityMovement.getVelocity();
	        double y = py * entityMovement.getVelocity();
	        entityPosition.translate(x, y);
	        if (x > 0)
	        {
	        	entityMovement.setDirection(Movement.RIGHT);
	        }
	        else if (x < 0)
	        {
	        	entityMovement.setDirection(Movement.LEFT);
	        }
	        canMove = false;        
		}
		else 
		{
			canMove = true;
		}
	}
	
	/*----------------------------------------*/

	public double getVelocity() { return velocity; }
	
	public int getDirection() { return direction; }
	
	public void setVelocity(double _velocity) { velocity = _velocity; }
	
	public void setDirection(int _direction) { direction = _direction; }
}
