package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.component.FlagECS;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import utils.Settings.Movement;

/**
 * Classe responsable de la gestion des mouvements.
 * @see AbstractSystem
 */
public class MovementSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe MovementSystem
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
			// Update movement component and collision component
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			// TODO CollisionComponent
			
			// Check if component needs to be update
			if (movement.getFlag() == FlagECS.STABLE /* TODO Collision flag*/)
			{
				continue;
			}
			
			// Update for entities with movements based on inputs
			if (entity.hasComponent(KeyInputComponent.class)) 
			{
				KeyInputComponent inputs = entity.getComponent(KeyInputComponent.class);
				int keyPressed = 0;
				// KEY PRESSED
				if (inputs.getInput(Movement.UP) == true)
				{
					keyPressed++;
					movement.translateY((-1)*movement.getVelocity());
					movement.setDirection(Movement.UP);
					movement.setState(Movement.WALK);
				}
				if (inputs.getInput(Movement.DOWN) == true)
				{
					keyPressed++;
					movement.translateY(movement.getVelocity());
					movement.setDirection(Movement.DOWN);
					movement.setState(Movement.WALK);
				}
				if (inputs.getInput(Movement.RIGHT) == true)
				{
					keyPressed++;
					movement.translateX(movement.getVelocity());
					movement.setDirection(Movement.RIGHT);
					movement.setState(Movement.WALK);
				}
				if (inputs.getInput(Movement.LEFT) == true)
				{
					keyPressed++;
					movement.translateX((-1)*movement.getVelocity());
					movement.setDirection(Movement.LEFT);
					movement.setState(Movement.WALK);
				}
				// KEY RELEASED
				if (keyPressed == 0)
				{
					movement.setState(Movement.IDLE);
				}
				entity.getComponent(SpriteComponent.class).setFlag(FlagECS.TO_UPDATE);
			}
			
			// TODO Update for entities that movement is not based on inputs
			
			
			// Update components flag
			movement.setFlag(FlagECS.STABLE);
		}
	}

	/*----------------------------------------*/
}
