package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.FlagECS;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import utils.Settings.Interaction;
import utils.Settings.Movement;
import utils.Settings.Window;

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
		
		// TODO Remove
		System.out.println("System created : " + this.getClass());
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
				// TODO Diagonals normal speed
				
				// Cardinal directions
				if (inputs.getInput(Movement.UP) == true)
				{
					movement.translateY((-1)*movement.getSpeed());
				}
				if (inputs.getInput(Movement.RIGHT) == true)
				{
					movement.translateX(movement.getSpeed());
				}
				if (inputs.getInput(Movement.DOWN) == true)
				{
					movement.translateY(movement.getSpeed());
				}
				if (inputs.getInput(Movement.LEFT) == true)
				{
					movement.translateX((-1)*movement.getSpeed());
				}
				// TEMPORARY
				if (inputs.getInput(Interaction.ACTIVATE) == true)
				{
					movement.setPos(Window.SCREEN_W/2, Window.SCREEN_H/2);
				}
			}
			
			// TODO Update for entities that movement is not based on inputs
			
			
			// Update components flag
			movement.setFlag(FlagECS.STABLE);
			entity.getComponent(SpriteComponent.class).setFlag(FlagECS.TO_UPDATE);
			
			// TODO Remove
			System.out.println("UID: " + entity.getUID() + " ; Pos:" + movement.getPos());
		}
	}

	/*----------------------------------------*/
}
