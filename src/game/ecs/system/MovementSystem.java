package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.FlagECS;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpritesComponent;
import game.ecs.entity.AbstractEntity;
import utils.Settings.Movement;
import utils.Settings.SpriteState;

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
				SpritesComponent sprites = entity.getComponent(SpritesComponent.class);
				if (inputs.getInput(Movement.UP) == true)
				{
					movement.translateY((-1)*movement.getSpeed());
					sprites.setState(SpriteState.WALK);
					sprites.setSpriteDirection(Movement.UP);
				}
				if (inputs.getInput(Movement.DOWN) == true)
				{
					movement.translateY(movement.getSpeed());
					sprites.setState(SpriteState.WALK);
					sprites.setSpriteDirection(Movement.DOWN);
				}
				if (inputs.getInput(Movement.RIGHT) == true)
				{
					movement.translateX(movement.getSpeed());
					sprites.setState(SpriteState.WALK);
					sprites.setSpriteDirection(Movement.RIGHT);
				}
				if (inputs.getInput(Movement.LEFT) == true)
				{
					movement.translateX((-1)*movement.getSpeed());
					sprites.setState(SpriteState.WALK);
					sprites.setSpriteDirection(Movement.LEFT);
				}
				sprites.setFlag(FlagECS.TO_UPDATE);
			}
			
			// TODO Update for entities that movement is not based on inputs
			
			
			// Update components flag
			movement.setFlag(FlagECS.STABLE);
		}
	}

	/*----------------------------------------*/
}
