package game.ecs.system;

import game.ecs.component.AnimationComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;

/**
 * Classe responsable de la gestion des animations de sprite.
 * @see AbstractSystem
 */
public class AnimationSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe AnimationSystem.
	 */
	public AnimationSystem()
	{
		super();
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(AnimationComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Get required components
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			AnimationComponent animation = entity.getComponent(AnimationComponent.class);
			
			// Update animation component
			animation.setFrameCount(animation.getFrameCount() + 1);
			if (animation.getFrameCount() > animation.getFramesBeforeUpdate())
			{
				animation.setFrameCount(0);
				// Update sprite indexes in the spritesheet
				sprite.setSpriteColIndex((sprite.getSpriteColIndex() + 1) % sprite.getCols());
				sprite.setSpriteRowIndex(animation.getNbDirection() * animation.getState() + animation.getDirection());
			}
		}
	}
	
	/*----------------------------------------*/
}
