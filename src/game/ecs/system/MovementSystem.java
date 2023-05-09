package game.ecs.system;

import game.ecs.FlagECS;
import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import utils.Settings.Actions;
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
					dy = -1;
					movement.setDirection(Movement.UP);
					animation.setState(Movement.WALK);
					collider.setFlag(FlagECS.TO_UPDATE);
				}
				if (inputs.getInput(Movement.DOWN) == true)
				{
					keyPressed++;
					dy = 1;
					movement.setDirection(Movement.DOWN);
					animation.setState(Movement.WALK);
					collider.setFlag(FlagECS.TO_UPDATE);
				}
				if (inputs.getInput(Movement.RIGHT) == true)
				{
					keyPressed++;
					dx = 1;
					movement.setDirection(Movement.RIGHT);
					animation.setState(Movement.WALK);
					collider.setFlag(FlagECS.TO_UPDATE);
				}
				if (inputs.getInput(Movement.LEFT) == true)
				{
					keyPressed++;
					dx = -1;
					movement.setDirection(Movement.LEFT);
					animation.setState(Movement.WALK);
					collider.setFlag(FlagECS.TO_UPDATE);
				}
				if (inputs.getInput(Actions.ATTACK) == true)
				{
					keyPressed++;
					animation.setState(Movement.ATTACK);
					collider.setFlag(FlagECS.TO_UPDATE);
				}
				// Normalize diagonal vector
				if (dx != 0 && dy != 0)
				{
					dx /= 1.41;
					dy /= 1.41;
				}
				// New position by adding normalized velocity
				position.translate(dx * movement.getVelocity(), dy * movement.getVelocity());
				
				// Keys released
				if (keyPressed == 0)
				{
					animation.setState(Movement.IDLE);
				}
				
				// Restore stable flag
				movement.setFlag(FlagECS.STABLE);
			}
			
			// Update for entities that movement is not based on inputs
//			else
//			{
//				movement.moveRandom(entity);
//				collider.setFlag(FlagECS.TO_UPDATE);
//			}
		}
	}

	/*----------------------------------------*/
}
