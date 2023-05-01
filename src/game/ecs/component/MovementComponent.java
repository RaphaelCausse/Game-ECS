package game.ecs.component;

import java.util.Random;
import game.ecs.entity.AbstractEntity;
import utils.Settings.Movement;
import utils.Settings.Sprites;

/**
 * Classe qui represente un composant de movement.
 * @see AbstractComponent
 */
public class MovementComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int velocity;
	private int previousRandomMove;
	private int updateMoveCount;
	
	/*----------------------------------------*/

	/**
	 * Constructeur de la classe MovementComponent.
	 * @param _velocity Vitesse de deplacement
	 */
	public MovementComponent(int _velocity)
	{
		super();
		velocity = _velocity;
		updateMoveCount = 0;
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void moveRandom(AbstractEntity entity)
	{
		updateMoveCount++;
		if (updateMoveCount > Sprites.ANIM_FRAMES / 2)
		{
			updateMoveCount = 0;
			// Get a randow direction
			Random rand = new Random();
	        int randomDirection = rand.nextInt(4);
	        // Decide to follow the random direciton or the previous one
	        int followPrevious = rand.nextInt(100);
	        if (followPrevious > 10)
	        {
	        	randomDirection = previousRandomMove;
	        }
	        
	        // Move entity
	        PositionComponent position = entity.getComponent(PositionComponent.class);
	    	if (randomDirection == Movement.UP)
	    	{
	    		previousRandomMove = Movement.UP;
	    		position.translateY((-1) * velocity);
	    	}
	    	if (randomDirection == Movement.RIGHT)
	    	{
	    		previousRandomMove = Movement.RIGHT;
	    		position.translateX(velocity);
	    	}
			if (randomDirection == Movement.DOWN)
			{
				previousRandomMove = Movement.DOWN;
				position.translateY(velocity);
			}
			if (randomDirection == Movement.LEFT)
			{
				previousRandomMove = Movement.LEFT;
				position.translateX((-1) * velocity);
			}
		}
	}
	
	/**
	 * 
	 * @param entity
	 */
	public void moveToPlayer(AbstractEntity entity)
	{
		// TODO Move entity towards player position
	}
	
	/*----------------------------------------*/

	public int getVelocity() { return velocity; }
	
	public void setVelocity(int _velocity) { velocity = _velocity; }
	
}
