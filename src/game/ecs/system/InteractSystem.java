package game.ecs.system;

import game.ecs.component.DetectionComponent;
import game.ecs.component.InteractComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;

/**
 *
 */
public class InteractSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	public int interactions;
	
	/*----------------------------------------*/

	/**
	 * Constructeur de la classe InteractSystem.
	 */
	public InteractSystem()
	{
		super();
		interactions = 0;
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(InteractComponent.class);
		for (AbstractEntity entity : entities)
		{
			InteractComponent interact = entity.getComponent(InteractComponent.class);
			DetectionComponent detection = entity.getComponent(DetectionComponent.class);

			if (detection != null)
			{
				for (AbstractEntity nearbyEntity : detection.getNearbyEntities())
				{
					if (nearbyEntity.hasComponent(InteractComponent.class))
					{
						InteractComponent nearbyInteract = nearbyEntity.getComponent(InteractComponent.class);
						if (interact.canInteract && nearbyInteract.canInteract)
						{
							interact.interact(entity, nearbyEntity);
						}
					}
					else
					{
						interact.interacting = false;
					}
				}
			}
			if (interact.interacting == false)
			{
				interact.setActivated(false);
			}
		}

	}

	/*----------------------------------------*/
}
