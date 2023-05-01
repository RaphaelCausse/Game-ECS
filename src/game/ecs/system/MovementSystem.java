package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.component.AnimationComponent;
import game.ecs.component.FlagECS;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
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
			
			// Update for entities with movements based on inputs
			if (entity.hasComponent(KeyInputComponent.class)) 
			{
				KeyInputComponent inputs = entity.getComponent(KeyInputComponent.class);
				PositionComponent position = entity.getComponent(PositionComponent.class);
				AnimationComponent animation = entity.getComponent(AnimationComponent.class);
				
				// Keys pressed
				int keyPressed = 0;
				if (inputs.getInput(Movement.UP) == true)
				{
					keyPressed++;
					position.translateY((-1) * movement.getVelocity());
					animation.setDirection(Movement.UP);
					animation.setState(Movement.WALK);
				}
				if (inputs.getInput(Movement.DOWN) == true)
				{
					keyPressed++;
					position.translateY(movement.getVelocity());
					animation.setDirection(Movement.DOWN);
					animation.setState(Movement.WALK);
				}
				if (inputs.getInput(Movement.RIGHT) == true)
				{
					keyPressed++;
					position.translateX(movement.getVelocity());
					animation.setDirection(Movement.RIGHT);
					animation.setState(Movement.WALK);
				}
				if (inputs.getInput(Movement.LEFT) == true)
				{
					keyPressed++;
					position.translateX((-1) * movement.getVelocity());
					animation.setDirection(Movement.LEFT);
					animation.setState(Movement.WALK);
				}
				if (inputs.getInput(Actions.ATTACK) == true)
				{
					keyPressed++;
					animation.setState(Movement.ATTACK);
				}
				
				// Keys released
				if (keyPressed == 0)
				{
					animation.setState(Movement.IDLE);
				}
				
				// Update components flag
				movement.setFlag(FlagECS.STABLE);
			}
			
			// Update for entities that movement is not based on inputs
			else
			{
				movement.moveRandom(entity);
			}
		}
	}

	/*----------------------------------------*/
}
