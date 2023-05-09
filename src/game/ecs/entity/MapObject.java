package game.ecs.entity;

import game.ecs.component.ColliderComponent;
import game.ecs.component.PositionComponent;
import utils.Settings.Sprites;

public class MapObject extends AbstractEntity
{
	/*----------------------------------------*/
	
	private int imageIndex;
	
	/*----------------------------------------*/
	
	public MapObject(int x, int y, int index)
	{
		super();
		initialize(x, y);
		imageIndex = index;
	}
	
	public void initialize(int x, int y)
	{
		// Init components
		PositionComponent position = new PositionComponent(x, y);
		ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE, Sprites.TILE_SIZE, 0, 0);
		
		// Add components
		addComponent(position);
		addComponent(collider);
	}

	/*----------------------------------------*/
	
	public int getImageIndex() { return imageIndex; }
	
}
