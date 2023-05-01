package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
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
	
	public int cameraX ;
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
	public void initialize(int x, int y, int velocity, int animFrames)
	{
		// Create components
		KeyInputComponent inputs = new KeyInputComponent();
		PositionComponent position = new PositionComponent(x, y);
		MovementComponent movement = new MovementComponent(velocity);
		SpriteComponent sprite = new SpriteComponent(ResFiles.PLAYER_SPRITESHEET, Sprites.PLAYER_SIZE, Sprites.PLAYER_SIZE);
		AnimationComponent animation = new AnimationComponent(animFrames, Movement.IDLE, Movement.DOWN, Movement.NB_DIRECTIONS);
		
		// Add components to entity
		addComponent(inputs);
		addComponent(position);
		addComponent(movement);
		addComponent(sprite);
		addComponent(animation);
	}
	
	/*----------------------------------------*/
}
