package game.ecs.system;

import game.ecs.component.AnimationComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import utils.Settings.AnimationState;

/**
 * Classe responsable de la gestion des animations de sprite.
 * @see AbstractSystem
 */
public class AnimationSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
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
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			AnimationComponent animation = entity.getComponent(AnimationComponent.class);
			
			// Update animations frame count
			animation.setFrameCount(animation.getFrameCount() + 1);
			
			// Force animation to persist each frame until it is completed
			if (animation.isInAnimation() && animation.getState() == AnimationState.ATTACK)
			{
				if (animation.getFrameCount() > animation.getFramesBeforeUpdate())
				{
					animation.setFrameCount(0);
					// Count animation frame to stop when animation has been completed
					animation.setAnimationFrameCount(animation.getAnimationFrameCount() + 1);
					if (animation.getAnimationFrameCount() > animation.getAnimationFrames()/2)
					{
						animation.setAnimationFrameCount(0);
						animation.setInAnimation(false);
						if (entity.hasComponent(AttackComponent.class));
						{
							entity.getComponent(AttackComponent.class).setHasAttacked(false);
						}
						continue;
					}
					// Update sprite indexes in the spritesheet
					int direction = (movement == null) ? 0 : movement.getDirection();
					sprite.setSpriteColIndex((sprite.getSpriteColIndex() + 1) % sprite.getCols());
					sprite.setSpriteRowIndex(animation.getNbDirection() * animation.getState() + direction);
				}
			}
			// Update other animation
			else
			{
				if (animation.getFrameCount() > animation.getFramesBeforeUpdate())
				{
					animation.setFrameCount(0);
					// Update sprite indexes in the spritesheet
					int direction = (movement == null) ? 0 : movement.getDirection();
					sprite.setSpriteColIndex((sprite.getSpriteColIndex() + 1) % sprite.getCols());
					sprite.setSpriteRowIndex(animation.getNbDirection() * animation.getState() + direction);
				}
			}
		}
	}
	
	/*----------------------------------------*/
}
