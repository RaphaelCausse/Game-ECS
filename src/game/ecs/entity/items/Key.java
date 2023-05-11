package game.ecs.entity.items;

import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

public class Key extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	public Key(int x, int y, boolean inInventory)
	{
		super("Cle de coffre");
		initialize(x, y);
	}
	
	public void initialize(int x, int y)
	{
		// Create components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_KEY, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
		addComponent(sprite);
	}

	/*----------------------------------------*/
}
