package game.ecs.entity;

import game.ecs.component.KeyInputComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpritesComponent;
import game.graphics.Sprites;
import javafx.scene.image.Image;
import utils.Settings.ResFiles;
import utils.Settings.SpriteSize;
import utils.Settings.SpriteState;

/**
 * Classe qui represente un joueur.
 * @see AbstractEntity
 */
public class Player extends AbstractEntity
{
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Player.
	 */
	public Player(int x, int y, int speed)
	{
		super();
		initialize(x, y, speed);
	}
	
	/**
	 * Initialisation des composants de l'entity.
	 */
	public void initialize(int x, int y, int speed)
	{
		// Init components
		KeyInputComponent inputs = new KeyInputComponent();
		MovementComponent movement = new MovementComponent(x, y, speed);
		SpritesComponent sprites = new SpritesComponent();
		sprites.addSprite(SpriteState.IDLE, new Sprites(new Image(ResFiles.PLAYER_IDLE), SpriteSize.PLAYER_SIZE, SpriteSize.PLAYER_SIZE));
		sprites.addSprite(SpriteState.WALK, new Sprites(new Image(ResFiles.PLAYER_WALK), SpriteSize.PLAYER_SIZE, SpriteSize.PLAYER_SIZE));
		
		// Add components
		addComponent(inputs);
		addComponent(movement);
		addComponent(sprites);
	}
	
	/*----------------------------------------*/
}
