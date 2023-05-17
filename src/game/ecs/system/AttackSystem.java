package game.ecs.system;

import game.ecs.component.AnimationComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.Monster;
import utils.Settings.AnimationState;

public class AttackSystem extends AbstractSystem
{

	public AttackSystem()
	{
		super();
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(AttackComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Update component
			PositionComponent position = entity.getComponent(PositionComponent.class);
			AnimationComponent animation = entity.getComponent(AnimationComponent.class);
			AttackComponent attack = entity.getComponent(AttackComponent.class);
			attack.updateAttackHitbox(position);
			
			if (animation.getState() == AnimationState.ATTACK)
			{
				// Check if entity is attacking nearby entities
				DetectionComponent detection = entity.getComponent(DetectionComponent.class);		
				for (AbstractEntity nearbyEntity : detection.getNearbyEntities())
				{
					// Check if attack touches nearby entity
					if (nearbyEntity.hasComponent(AttackComponent.class) && attack.isHitting(nearbyEntity))
					{
						// Compute damages
						HealthComponent nearbyHealth = nearbyEntity.getComponent(HealthComponent.class);
						attack.dealDamageTo(nearbyEntity);
						
						// Drop all items if the entity dies
						if (nearbyHealth.isDead())
						{
							attack.setHasAttacked(true);
							InventoryComponent nearbyInventory = nearbyEntity.getComponent(InventoryComponent.class);
							EntityManager.removeEntity(nearbyEntity.getUID());
							nearbyInventory.dropInventory(nearbyEntity);
						}
					}
				}
			}
			if (entity instanceof Monster && attack.isAttacking())
			{
			}
		}
	}

}
