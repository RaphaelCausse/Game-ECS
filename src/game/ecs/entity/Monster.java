package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DamageComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.AnimationState;
import utils.Settings.Movement;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Stats;

/**
 * Classe qui represente un montre ennemi.
 * @see AbstractEntity
 */
public class Monster extends AbstractEntity
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Monster.
	 * @param x Position en X
	 * @param y Position en Y
	 * @param isBoss Est monstre Boss ou non
	 */
	public Monster(int x, int y, boolean isBoss)
	{
		super();
		initialize(x, y, isBoss);
	}
	
	/**
	 * Initialisation des composants de l'entite.
	 * @param x Position en X
	 * @param y Position en Y
	 * @param isBoss Est monstre Boss ou non
	 */
	public void initialize(int x, int y, boolean isBoss)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		MovementComponent movement = new MovementComponent(
			(isBoss) ? Movement.MONSTER_BOSS_SPEED : Movement.MONSTER_SPEED,
			0
		);
		addComponent(movement);
		
		SpriteComponent sprite = new SpriteComponent(
			(isBoss) ? ResFiles.MONSTER_BOSS_SPRITESHEET : ResFiles.MONSTER_SPRITESHEET,
			(isBoss) ? Sprites.MONSTER_BOSS_SIZE : Sprites.MONSTER_SIZE,
			(isBoss) ? Sprites.MONSTER_BOSS_SIZE : Sprites.MONSTER_SIZE
		);
		addComponent(sprite);
		
		AnimationComponent animation = new AnimationComponent(Sprites.ANIM_FRAMES, AnimationState.IDLE, 1);
		addComponent(animation);
		
		ColliderComponent collider = new ColliderComponent(x, y, sprite.getSpriteWidth(), sprite.getSpriteHeight(), 0, 0, true);
		addComponent(collider);
		
		HealthComponent health = new HealthComponent(Stats.MONSTER_MAX_HEALTH);
		addComponent(health);
		
		DamageComponent damage = new DamageComponent(Stats.MONSTER_BASE_DAMAGE);
		addComponent(damage);
	}

	/*----------------------------------------*/
}
