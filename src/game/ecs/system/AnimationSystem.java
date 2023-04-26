package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.component.AnimationComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;

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
			// Update animation component
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			AnimationComponent animation = entity.getComponent(AnimationComponent.class);
			
			animation.setFrameCount(animation.getFrameCount() + 1);
			if (animation.getFrameCount() > animation.getFrameUpdateValue())
			{
				animation.setFrameCount(0);
				animation.setFrameIndex((animation.getFrameIndex() + 1) % sprite.getCols());
			}
		}
	}
	
	/*----------------------------------------*/
}
