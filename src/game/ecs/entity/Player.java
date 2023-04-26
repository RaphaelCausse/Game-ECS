package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.Movement;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Classe qui represente un joueur.
 * @see AbstractEntity
 */
public class Player extends AbstractEntity
{
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Player.
	 * @param x
	 * @param y
	 * @param velocity
	 */
	public Player(int x, int y, int velocity, int animFrameCount)
	{
		super();
		initialize(x, y, velocity, animFrameCount);
	}
	
	/**
	 * Initialisation des composants de l'entity.
	 * @param x Position en X
	 * @param y position en Y
	 * @param velocity Vitesse de deplacement
	 */
	public void initialize(int x, int y, int velocity, int animFrameCount)
	{
		// Create components
		KeyInputComponent inputs = new KeyInputComponent();
		MovementComponent movement = new MovementComponent(x, y, velocity, Movement.IDLE, Movement.DOWN, 4);
		SpriteComponent sprite = new SpriteComponent(ResFiles.PLAYER_SPRITESHEET, Sprites.PLAYER_SIZE, Sprites.PLAYER_SIZE);
		AnimationComponent animation = new AnimationComponent(animFrameCount);
		
		// Add components to entity
		addComponent(inputs);
		addComponent(movement);
		addComponent(sprite);
		addComponent(animation);
	}
	
	/*----------------------------------------*/
}
