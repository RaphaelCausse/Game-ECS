package game.ecs.system;

import java.util.List;
import game.ecs.entity.AbstractEntity;

/**
 * Classe abstraite qui represente un systeme qui interagit avec les composants associes.
 */
public abstract class AbstractSystem
{
	/*----------------------------------------*/
	
	protected List<AbstractEntity> entities;
	
	/*----------------------------------------*/

	/**
	 * Executer le systeme.
	 */
	public abstract void run();
	
	/*----------------------------------------*/
}
