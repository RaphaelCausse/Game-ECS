package game.ecs.component;

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
	
//	/**
//	 * 
//	 * @param entity
//	 */
//	public void moveRandom(AbstractEntity entity)
//	{
//		updateMoveCount++;
//		if (updateMoveCount > Sprites.ANIM_FRAMES / 2)
//		{
//			updateMoveCount = 0;
//			// Get a randow direction
//			Random rand = new Random();
//	        int randomDirection = rand.nextInt(4);
//	        // Decide to follow the random direciton or the previous one
//	        int followPrevious = rand.nextInt(100);
//	        if (followPrevious > 10)
//	        {
//	        	randomDirection = previousRandomMove;
//	        }
//	        
//	        // Move entity
//	        PositionComponent position = entity.getComponent(PositionComponent.class);
//	    	if (randomDirection == Movement.UP)
//	    	{
//	    		previousRandomMove = Movement.UP;
//	    		position.translateY((-1) * velocity);
//	    	}
//	    	if (randomDirection == Movement.RIGHT)
//	    	{
//	    		previousRandomMove = Movement.RIGHT;
//	    		position.translateX(velocity);
//	    	}
//			if (randomDirection == Movement.DOWN)
//			{
//				previousRandomMove = Movement.DOWN;
//				position.translateY(velocity);
//			}
//			if (randomDirection == Movement.LEFT)
//			{
//				previousRandomMove = Movement.LEFT;
//				position.translateX((-1) * velocity);
//			}
//		}
//	}
	
//	/**
//	 * 
//	 * @param entity
//	 */
//	public void moveToPlayer(AbstractEntity entity)
//	{
//		// TODO Move entity towards player position
//	}
	
	/*----------------------------------------*/

	public double getVelocity() { return velocity; }
	
	public int getDirection() { return direction; }
	
	public void setVelocity(double _velocity) { velocity = _velocity; }
	
	public void setDirection(int _direction) { direction = _direction; }
}
