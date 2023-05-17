package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.AnimationState;
import utils.Settings.Movement;

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
	 */
	public Monster(String _name, int x, int y, String resfile, int w, int h, int animFrames, int hp, int dmg, int cooldown)
	{
		super();
		name = _name;
		initialize(x, y, resfile, w, h, animFrames, hp, dmg, cooldown);
	}
	
	/**
	 * Initialisation des composants de l'entite.
	 * @param x Position en X
	 * @param y Position en Y
	 * @param isBoss Est monstre Boss ou non
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
		
		AttackComponent damage = new AttackComponent(dmg, cooldown);
		addComponent(damage);
		
		InventoryComponent inventory = new InventoryComponent(1);
		addComponent(inventory);
	}

	/*----------------------------------------*/
	
	public String getName() { return name; }
}
