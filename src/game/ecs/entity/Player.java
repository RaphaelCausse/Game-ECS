package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.items.Key;
import utils.Settings.Movement;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
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
	 * @param velocity Vitesse de deplacement
	 * @param animFrames Nombres de frames de l'animation
	 */
	public Player(int x, int y, int velocity, int animFrames)
	{
		super();
		initialize(x, y, velocity, animFrames);
		cameraX = Window.SCREEN_W/2 - getComponent(SpriteComponent.class).getSpriteWidth()/2;
		cameraY = Window.SCREEN_H/2 - getComponent(SpriteComponent.class).getSpriteHeight()/2;
	}
	
	/**
	 * Initialisation des composants de l'entity.
	 * @param x Position en X
	 * @param y position en Y
	 * @param velocity Vitesse de deplacement
	 * @param animFrames Nombres de frames de l'animation
	 */
	public void initialize(int x, int y, double velocity, int animFrames)
	{
		// Create and add components
		KeyInputComponent inputs = new KeyInputComponent();
		addComponent(inputs);

		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);

		MovementComponent movement = new MovementComponent(velocity, Movement.DOWN);
		addComponent(movement);

		SpriteComponent sprite = new SpriteComponent(ResFiles.PLAYER_SPRITESHEET, Sprites.PLAYER_SIZE, Sprites.PLAYER_SIZE);
		addComponent(sprite);

		AnimationComponent animation = new AnimationComponent(animFrames, Movement.IDLE, Movement.NB_DIRECTIONS);
		addComponent(animation);

		ColliderComponent collider = new ColliderComponent(
			x + Sprites.PLAYER_SIZE/2,	// x
			y + Sprites.PLAYER_SIZE/2,	// y
			Sprites.PLAYER_SIZE/3,		// w
			Sprites.PLAYER_SIZE/4,		// h
			Sprites.PLAYER_SIZE/3,		// ox
			Sprites.PLAYER_SIZE/2,		// oy
			true						// isMoveable
		);
		addComponent(collider);
		
		// TODO
		InventoryComponent inventory = new InventoryComponent(7);
		addComponent(inventory);
		Key key = new Key(x, y, true);
		Key key2 = new Key(x, y, true);
		inventory.addItem(key);
		inventory.addItem(key2);
//		EntityManager.addEntity(key.getUID(), key);
	}
	
	/*----------------------------------------*/
}
