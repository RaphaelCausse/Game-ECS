package game.ecs.entity;

import game.ecs.component.AnimationComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.Movement;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

public class Blacksmith extends AbstractEntity
{
	/*----------------------------------------*/
	
	public Blacksmith(int x, int y, int animFrames)
	{
		super();
		initialize(x, y, animFrames);
	}
	
	public void initialize(int x, int y, int animFrames)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);

		SpriteComponent sprite = new SpriteComponent(ResFiles.BLACKSMITH_SPRITESHEET, Sprites.SPRITE_SIZE, Sprites.SPRITE_SIZE);
		addComponent(sprite);

		AnimationComponent animation = new AnimationComponent(Sprites.ANIM_FRAMES/2, Movement.IDLE, 1);
		addComponent(animation);
		
		ColliderComponent collider = new ColliderComponent(x, y, Sprites.SPRITE_SIZE, Sprites.SPRITE_SIZE, 0, 0, false);
		addComponent(collider);
	}
	
	/*----------------------------------------*/
}
