package game.ecs.system;

import game.ecs.FlagECS;
import game.ecs.component.AnimationComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.Player;
import utils.Settings.Actions;
import utils.Settings.AnimationState;
import utils.Settings.Movement;

/**
 * Classe responsable de la gestion des mouvements.
 * @see AbstractSystem
 */
public class MovementSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe MovementSystem.
	 */
	public MovementSystem()
	{
		super();
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(MovementComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Check if component needs to be update
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			if (movement.getFlag() == FlagECS.STABLE)
			{
				continue;
			}
			
			// Get required components
			PositionComponent position = entity.getComponent(PositionComponent.class);
			ColliderComponent collider = entity.getComponent(ColliderComponent.class);
			
			// Update for entities with movements based on inputs
			if (entity.hasComponent(KeyInputComponent.class))
			{
				KeyInputComponent inputs = entity.getComponent(KeyInputComponent.class);
				AnimationComponent animation = entity.getComponent(AnimationComponent.class);
				
				// Keys pressed
				int keyPressed = 0;
				double dx = 0;
				double dy = 0;
				if (inputs.getInput(Movement.UP) == true)
				{
					keyPressed++;
					dy -= 1;
					movement.setDirection(Movement.UP);
					if (!animation.isInAnimation())
					{
						animation.setState(AnimationState.WALK);
					}
				}
				if (inputs.getInput(Movement.DOWN) == true)
				{
					keyPressed++;
					dy += 1;
					movement.setDirection(Movement.DOWN);
					if (!animation.isInAnimation())
					{
						animation.setState(AnimationState.WALK);
					}
				}
				if (inputs.getInput(Movement.RIGHT) == true)
				{
					keyPressed++;
					dx += 1;
					movement.setDirection(Movement.RIGHT);
					if (!animation.isInAnimation())
					{
						animation.setState(AnimationState.WALK);
					}
				}
				if (inputs.getInput(Movement.LEFT) == true)
				{
					keyPressed++;
					dx -= 1;
					movement.setDirection(Movement.LEFT);
					if (!animation.isInAnimation())
					{
						animation.setState(AnimationState.WALK);
					}
				}
				if (inputs.getInput(Actions.ATTACK) == true)
				{
					keyPressed++;
					animation.setState(AnimationState.ATTACK);
					if (!animation.isInAnimation())
					{
						animation.setAnimationFrameCount(0);
						animation.setInAnimation(true);
						AttackComponent attack = entity.getComponent(AttackComponent.class);
						attack.setAttacking(true);
						attack.setFlag(FlagECS.TO_UPDATE);
					}
				}
				// Normalize diagonal vector
				if (dx != 0 && dy != 0)
				{
					dx /= 1.41;
					dy /= 1.41;
				}
				// New position by adding normalized velocity
				position.translate(dx * movement.getVelocity(), dy * movement.getVelocity());
				
				collider.setFlag(FlagECS.TO_UPDATE);
				
				// Keys released
				if (keyPressed == 0)
				{
					if (!animation.isInAnimation())
					{
						animation.setState(AnimationState.IDLE);
						if (entity.hasComponent(AttackComponent.class))
						{
							AttackComponent attack = entity.getComponent(AttackComponent.class);
							attack.setAttacking(false);
						}
					}
				}
				
				// Restore stable flag
				movement.setFlag(FlagECS.STABLE);
			}
			// Update for entities that movement is not based on inputs
			else
			{
				if (entity.hasComponent(DetectionComponent.class))
				{
					DetectionComponent detection = entity.getComponent(DetectionComponent.class);
					for (AbstractEntity nearbyEntity : detection.getNearbyEntities())
					{
						// Move towards player if inside detection bounds
						if (nearbyEntity instanceof Player)
						{
							movement.moveToTarget(entity, nearbyEntity);
							collider.setFlag(FlagECS.TO_UPDATE);
							
							// Attack player if close enough to entity
							AttackComponent entityAttack = entity.getComponent(AttackComponent.class);
							ColliderComponent nearbyCollider = nearbyEntity.getComponent(ColliderComponent.class);
							ColliderComponent entityCollider = entity.getComponent(ColliderComponent.class);
							if (Math.abs(nearbyCollider.getBounds().getCenterX() - entityCollider.getBounds().getCenterX()) < 200 &&
								Math.abs(nearbyCollider.getBounds().getCenterY() - entityCollider.getBounds().getCenterY()) < 200)
							{
								entityAttack.setAttacking(true);
								entityAttack.targetUID = nearbyEntity.getUID();
							}
							else
							{
								entityAttack.setAttacking(false);
							}
						}
					}
				}
			}
		}
	}

	/*----------------------------------------*/
}
