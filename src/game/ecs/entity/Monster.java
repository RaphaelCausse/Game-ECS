package game.ecs.entity;

import java.util.Random;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.AttackPotion;
import game.ecs.entity.items.HealthPotion;
import game.ecs.entity.items.PoisonPotion;
import utils.Settings.AnimationState;
import utils.Settings.Movement;

/**
 * Class that represents an enemy monster.
 * @see AbstractEntity
 */
public class Monster extends AbstractEntity
{
	/*----------------------------------------*/
	
	private String name;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of Monster class.
	 * @param _name Monster name
	 * @param x X position
	 * @param y Y position
	 * @param resfile Spritesheet file name
	 * @param w Sprite width
	 * @param h Sprite heigth
	 * @param animFrames Number of frames of the animation
	 * @param hp Max health
	 * @param dmg Base damage
	 * @param cooldown Attack cooldown
	 */
	public Monster(String _name, int x, int y, String resfile, int w, int h, int animFrames, int hp, int dmg, int cooldown)
	{
		super();
		name = _name;
		initialize(x, y, resfile, w, h, animFrames, hp, dmg, cooldown);
	}
	
	/**
	 * Initialize entity components.
	 * @param _name Monster name
	 * @param x X position
	 * @param y Y position
	 * @param resfile Spritesheet file name
	 * @param w Sprite width
	 * @param h Sprite heigth
	 * @param animFrames Number of frames of the animation
	 * @param hp Max health
	 * @param dmg Base damage
	 * @param cooldown Attack cooldown
	 */
	public void initialize(int x, int y, String resfile, int w, int h, int animFrames, int hp, int dmg, int cooldown)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		MovementComponent movement = new MovementComponent(Movement.MONSTER_SPEED, Movement.RIGHT);
		addComponent(movement);
		
		SpriteComponent sprite = new SpriteComponent(resfile, w, h);
		addComponent(sprite);
		
		AnimationComponent animation = new AnimationComponent(animFrames, AnimationState.IDLE, Movement.NB_DIRECTIONS);
		addComponent(animation);
		
		ColliderComponent collider = new ColliderComponent(
			x,
			y,
			sprite.getSpriteWidth()/3*2,
			sprite.getSpriteHeight()/5*4,
			sprite.getSpriteWidth()/5,
			sprite.getSpriteHeight()/5 + 1,
			true
		);
		addComponent(collider);
		
		DetectionComponent detection = new DetectionComponent(
			x,
			y,
			sprite.getSpriteWidth()*7,
			sprite.getSpriteHeight()*5,
			sprite.getSpriteWidth()*3,
			sprite.getSpriteHeight()*2
		);
		addComponent(detection);
		
		HealthComponent health = new HealthComponent(hp);
		addComponent(health);
		
		AttackComponent attack = new AttackComponent(dmg, cooldown);
		addComponent(attack);
		
		InventoryComponent inventory = new InventoryComponent(2);
		addComponent(inventory);
		Random random = new Random();
		int rand = random.nextInt(0, 11);
		if (rand <= 3)
		{
			AbstractItem potion = new HealthPotion(x, y);
			inventory.addItem(potion);
		}
		else
		{
			if (rand <= 7)
			{
				AbstractItem potion = new PoisonPotion(x, y);
				inventory.addItem(potion);
			}
			else
			{
				AbstractItem potion = new AttackPotion(x, y);
				inventory.addItem(potion);
			}
		}
	}

	/*----------------------------------------*/
	
	/**
	 * Get monster name.
	 * @return name
	 */
	public String getName() { return name; }
}
