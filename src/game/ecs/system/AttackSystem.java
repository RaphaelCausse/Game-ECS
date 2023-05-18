package game.ecs.system;

import java.util.Random;

import game.ecs.component.AnimationComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.Monster;
import utils.Settings.AnimationState;
import utils.Settings.Sprites;

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
			if (!(entity instanceof Monster))
			{
				attack.updateAttackHitbox(position);
			}
			
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
						if (!attack.hasAttacked())
						{
							attack.dealDamageTo(nearbyEntity);
							attack.setHasAttacked(true);
						}
						
						// Drop all items if the entity dies
						if (nearbyHealth.isDead())
						{
							InventoryComponent nearbyInventory = nearbyEntity.getComponent(InventoryComponent.class);
							EntityManager.removeEntity(nearbyEntity.getUID());
							nearbyInventory.dropInventory(nearbyEntity);
						}
					}
				}
			}
			if (entity instanceof Monster && attack.isAttacking())
			{
				AbstractEntity target = EntityManager.getEntity(attack.targetUID);
				ColliderComponent targetCollider = target.getComponent(ColliderComponent.class);
				HealthComponent targetHealth = target.getComponent(HealthComponent.class);
				
				// Check if entity deals damage to target
				if (attack.getAttackHitbox() != null && attack.getAttackHitbox().intersects(targetCollider.getBounds()))
				{
					if (!attack.hasAttacked())
					{
						attack.dealDamageTo(target);
						attack.setHasAttacked(true);
					}
					if (targetHealth.isDead())
					{
						// GAME OVER
						
					}
				}
				// New attack if cooldown allows it
				if (attack.cooldownCount >= attack.attackCooldown)
				{
					attack.cooldownCount = 0;
					attack.setHasAttacked(false);
					attack.removeAttackHitbox();
					
					// Create a random damage zone near the player
					Random random = new Random();
					int dx = random.nextInt(-32, 32);
					int dy = random.nextInt(-32, 32);
					
					attack.addAttackHitBox(
						(int)targetCollider.getBounds().getCenterX() + dx,
						(int)targetCollider.getBounds().getCenterY() + dy,
						Sprites.POISON_DAMAGE_ORBE_W,
						Sprites.POISON_DAMAGE_ORBE_H,
						0, 
						0
					);
				}
				attack.cooldownCount++;
			}
		}
	}

}
