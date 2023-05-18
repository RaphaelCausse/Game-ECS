package game.ecs.system;

import java.util.List;
import game.ecs.entity.AbstractEntity;

/**
 * Abstract class that represents a system interacting with components.
 */
public abstract class AbstractSystem
{
	/*----------------------------------------*/
	
	protected List<AbstractEntity> entities;
	
	/*----------------------------------------*/

	/**
	 * Execute the system to interact with components.
	 */
	public abstract void run();
	
	/*----------------------------------------*/
}
