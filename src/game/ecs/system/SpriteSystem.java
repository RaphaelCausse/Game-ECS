package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.FlagECS;
import game.ecs.component.SpritesComponent;
import game.ecs.entity.AbstractEntity;

/**
 * Classe responsable de la gestion des sprites.
 */
public class SpriteSystem extends AbstractSystem
{	
	/*----------------------------------------*/
	
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe SpriteSystem.
	 */
	public SpriteSystem()
	{
		super();
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(SpritesComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Update sprite component
			SpritesComponent sprites = entity.getComponent(SpritesComponent.class);
			
			// Check for entities that need need to be update
			if (sprites.getFlag() == FlagECS.STABLE)
			{
				continue;
			}
			
			
			// TODO Update sprites index data based on time for animation
		}
	}
	
	/*----------------------------------------*/
}
