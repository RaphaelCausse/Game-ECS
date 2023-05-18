package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.AnimationState;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Class that represents a Non Playable Character.
 * @see AbstractEntity
 */
public class NPC extends AbstractEntity
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of NPC class.
	 * @param x X position
	 * @param y Y position
	 */
	public NPC(int x, int y)
	{
		super();
		initialize(x, y);
	}
	
	/**
	 * Initialize entity components.
	 * @param x X position
	 * @param y Y position
	 */
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);

		SpriteComponent sprite = new SpriteComponent(ResFiles.BLACKSMITH_SPRITESHEET, Sprites.SPRITE_SIZE, Sprites.SPRITE_SIZE);
		addComponent(sprite);

		AnimationComponent animation = new AnimationComponent(Sprites.ANIM_FRAMES/2, AnimationState.IDLE, 1);
		addComponent(animation);
		
		ColliderComponent collider = new ColliderComponent(x, y, sprite.getSpriteWidth(), sprite.getSpriteHeight(), 0, 0, false);
		addComponent(collider);
		
		DetectionComponent detection = new DetectionComponent(
			x,								// x
			y,								// y
			sprite.getSpriteWidth()*4,		// w
			sprite.getSpriteHeight()*2,		// h
			sprite.getSpriteWidth()*3/2,	// ox
			0								// oy
		);
		addComponent(detection);
	}
	
	/*----------------------------------------*/
}
