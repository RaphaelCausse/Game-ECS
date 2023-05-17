package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DamageComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InteractComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.AnimationState;
import utils.Settings.Movement;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Stats;
import utils.Settings.Window;

/**
 * Classe qui represente un joueur.
 * @see AbstractEntity
 */
public class Player extends AbstractEntity
{
	/*----------------------------------------*/
	
	public int cameraX;
	public int cameraY;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Player.
	 * @param x Position en X
	 * @param y Position en Y
	 * @param animFrames Nombres de frames de l'animation
	 */
	public Player(int x, int y)
	{
		super();
		initialize(x, y);
		cameraX = Window.SCREEN_W/2 - getComponent(SpriteComponent.class).getSpriteWidth()/2;
		cameraY = Window.SCREEN_H/2 - getComponent(SpriteComponent.class).getSpriteHeight()/2;
	}
	
	/**
	 * Initialisation des composants de l'entite.
	 * @param x Position en X
	 * @param y position en Y
	 * @param animFrames Nombres de frames de l'animation
	 */
	public void initialize(int x, int y)
	{
		// Create and add components
		KeyInputComponent inputs = new KeyInputComponent();
		addComponent(inputs);

		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);

		MovementComponent movement = new MovementComponent(Movement.PLAYER_SPEED, Movement.DOWN);
		addComponent(movement);

		SpriteComponent sprite = new SpriteComponent(ResFiles.PLAYER_SPRITESHEET, Sprites.PLAYER_SIZE, Sprites.PLAYER_SIZE);
		addComponent(sprite);

		AnimationComponent animation = new AnimationComponent(Sprites.ANIM_FRAMES, AnimationState.IDLE, Movement.NB_DIRECTIONS);
		addComponent(animation);

		ColliderComponent collider = new ColliderComponent(
			x + sprite.getSpriteWidth()/2,	// x
			y + sprite.getSpriteHeight()/2,	// y
			sprite.getSpriteWidth()/3,		// w
			sprite.getSpriteHeight()/4,		// h
			sprite.getSpriteWidth()/3,		// ox
			sprite.getSpriteHeight()/2,		// oy
			true							// isMoveable
		);
		addComponent(collider);
//		x + ox - (2 * width), y + oy - (2 * height), 5 * width, 5 * height
		DetectionComponent detection = new DetectionComponent(
			x,
			y,
			sprite.getSpriteWidth() * 3,
			sprite.getSpriteHeight() * 3,
			sprite.getSpriteWidth(),
			sprite.getSpriteHeight()
		);
		addComponent(detection);
		
		InteractComponent interact = new InteractComponent();
		addComponent(interact);
		
		InventoryComponent inventory = new InventoryComponent(7);
		addComponent(inventory);
		
		HealthComponent health = new HealthComponent(Stats.PLAYER_MAX_HEALTH);
		health.setCurrentHeath(health.getMaxHealth()/2);
		addComponent(health);
		
		DamageComponent damage = new DamageComponent(Stats.PLAYER_BASE_DAMAGE);
		addComponent(damage);
	}
	
	/*----------------------------------------*/
}
