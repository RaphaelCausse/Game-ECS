package game.ecs.entity;

import java.util.Random;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DamageComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.FlyingRing;
import game.ecs.entity.items.HealthPotion;
import game.ecs.entity.items.Key;
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
	
	private String name;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Monster.
	 * @param _name Nom du monstre
	 * @param x Position en X
	 * @param y Position en Y
	 * @param isBoss Est monstre Boss ou non
	 */
	public Monster(String _name, int x, int y, boolean isBoss)
	{
		super();
		name = _name;
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
			Movement.RIGHT
		);
		addComponent(movement);
		
		SpriteComponent sprite = new SpriteComponent(
			(isBoss) ? ResFiles.MONSTER_BOSS_SPRITESHEET : ResFiles.MONSTER_SPRITESHEET,
			Sprites.MONSTER_SIZE,
			Sprites.MONSTER_SIZE
		);
		addComponent(sprite);
		
		AnimationComponent animation = new AnimationComponent(
			Sprites.ANIM_FRAMES,
			AnimationState.IDLE,
			Movement.NB_DIRECTIONS
		);
		addComponent(animation);
		
		ColliderComponent collider = new ColliderComponent(
			x,
			y,
			sprite.getSpriteWidth() * ((sprite.getScale() == 0) ? 1 : sprite.getScale()),
			sprite.getSpriteHeight() * ((sprite.getScale() == 0) ? 1 : sprite.getScale()),
			0,
			0,
			true
		);
		addComponent(collider);
		
		HealthComponent health = new HealthComponent(
			(isBoss) ? Stats.MONSTER_BOSS_MAX_HEALTH : Stats.MONSTER_MAX_HEALTH
		);
		addComponent(health);
		
		DamageComponent damage = new DamageComponent(
			(isBoss) ? Stats.MONSTER_BOSS_BASE_DAMAGE : Stats.MONSTER_BASE_DAMAGE
		);
		addComponent(damage);
		
		InventoryComponent inventory = new InventoryComponent(2);
		addComponent(inventory);
		
		if (isBoss)
		{
			AbstractItem key = new Key(x, y);
			inventory.addItem(key);
		}
		else
		{
			Random random = new Random();
			int rand = random.nextInt(0, 4);
			if (rand == 0 || rand == 2)
			{
				AbstractItem potion = new HealthPotion(x, y);
				inventory.addItem(potion);
			}
			else
			{
				AbstractItem ring = new FlyingRing(x, y);
				inventory.addItem(ring);
			}
		}
	}

	/*----------------------------------------*/
	
	public String getName() { return name; }
}
