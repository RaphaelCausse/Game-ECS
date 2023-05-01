package game.ecs.entity;

import game.ecs.component.AnimationComponent;
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
		// Init components
		PositionComponent position = new PositionComponent(x, y);
		SpriteComponent sprite = new SpriteComponent(ResFiles.BLACKSMITH_SPRITESHEET, Sprites.SPRITE_SIZE, Sprites.SPRITE_SIZE);
		AnimationComponent animation = new AnimationComponent(Sprites.ANIM_FRAMES/2, Movement.IDLE, 0, 1);
		
		// Add components
		addComponent(position);
		addComponent(sprite);
		addComponent(animation);
	}
	
	/*----------------------------------------*/
}
