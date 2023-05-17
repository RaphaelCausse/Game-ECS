package game.ecs.component;

import game.ecs.entity.AbstractEntity;

/**
 * Classe qui represente un composant de movement.
 * @see AbstractComponent
 */
public class MovementComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private double velocity;
	private int direction;
	
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
	}
	
	/**
	 * Deplacement aleatoire de l'entite vers un point.
	 * @param entity
	 */
	public void moveRandom(AbstractEntity entity)
	{
		
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void moveToTarget(AbstractEntity entity, AbstractEntity target)
	{
		// Get components
		PositionComponent entityPosition = entity.getComponent(PositionComponent.class);
		MovementComponent entityMovement = entity.getComponent(MovementComponent.class);
		ColliderComponent entityCollider = entity.getComponent(ColliderComponent.class);
		ColliderComponent targetCollider = target.getComponent(ColliderComponent.class);
		
		System.out.println("Entity collider" + entityCollider.getBounds().getCenterX());
		System.out.println("Entity collider" + entityCollider.getBounds().getCenterY());
		System.out.println("Target collider" + targetCollider.getBounds().getCenterX());
		System.out.println("Target collider" + targetCollider.getBounds().getCenterY());
		
		// Move
        double dx = targetCollider.getBounds().getCenterX() - entityCollider.getBounds().getCenterX();
        double dy = targetCollider.getBounds().getCenterY() - entityCollider.getBounds().getCenterY();
        
        
        System.out.println("(dx: " + dx + ", dy: " + dy + ")");
        
        
        double angle = Math.atan2(dy, dx);
        double px =  Math.cos(angle);
        double py =  Math.sin(angle);
        double x = px * entityMovement.getVelocity();
        double y = py * entityMovement.getVelocity();
        
//        System.out.println("AVANT TRANSLATE (x: "+ x + ", y: " + y + ")");
        
        entityPosition.translate(x, y);
        
//        System.out.println("APRES TRANSLATE (x: "+ x + ", y: " + y + ")");
		
		// Flag animation
	}
	
	/*----------------------------------------*/

	public double getVelocity() { return velocity; }
	
	public int getDirection() { return direction; }
	
	public void setVelocity(double _velocity) { velocity = _velocity; }
	
	public void setDirection(int _direction) { direction = _direction; }
}
