package game.ecs.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import game.ecs.component.ColliderComponent;
import game.ecs.component.InteractComponent;
import game.ecs.component.PositionComponent;
import utils.Settings.MapObjectsID;
import utils.Settings.Sprites;

/**
 * Class that represents a map object.
 * @see AbstractEntity
 */
public class MapObject extends AbstractEntity
{
	/*----------------------------------------*/
	
	private int imageIndex;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of MapObject class.
	 * @param x X position
	 * @param y Y position
	 * @param index Image index according to tileset
	 */
	public MapObject(int x, int y, int index)
	{
		super();
		imageIndex = index;
		initialize(x, y);
	}
	
	/**
	 * Initialize entity components.
	 * @param x X position
	 * @param y Y position
	 */
	public void initialize(int x, int y)
	{
		// Index lists for different colliders patterns
		List<Integer> patternLeft = new ArrayList<>(); 		// Left side colliders
		patternLeft.addAll(Arrays.asList(7, 21, 35, 48, 49, 62, 76));
		List<Integer> patternRight = new ArrayList<>();		// Right side colliders
		patternRight.addAll(Arrays.asList(9, 23, 34, 37, 51, 63, 77));
		List<Integer> patternTop = new ArrayList<>(); 		// Top side colliders
		patternTop.addAll(Arrays.asList(78, 79));
		List<Integer> patternBottom = new ArrayList<>(); 	// Bottom side colliders
		patternBottom.addAll(Arrays.asList(68));
		List<Integer> patternTopLeft = new ArrayList<>(); 	// Top left side colliders
		patternTopLeft.addAll(Arrays.asList(45));
		List<Integer> patternTopRight = new ArrayList<>(); 	// Top right side colliders
		patternTopRight.addAll(Arrays.asList(44));
		List<Integer> patternFull = new ArrayList<>();	 	// Full colliders
		patternFull.addAll(Arrays.asList(26, 28, 37, 42, 43, 46, 47, 54, 56, 57, 58, 59, 60, 61, 70, 71, 72, 73, 74, 75, 82, 83));
		
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		// Set collider according to corresponding pattern
		if (patternLeft.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE/2, Sprites.TILE_SIZE, 0, 0, false);
			addComponent(collider);
		}
		else if (patternRight.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE/2, Sprites.TILE_SIZE, Sprites.TILE_SIZE/2, 0, false);
			addComponent(collider);
		}
		else if (patternTop.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE, Sprites.TILE_SIZE/2, 0, 0, false);
			addComponent(collider);
		}
		else if (patternBottom.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE, Sprites.TILE_SIZE/3*2, 0, Sprites.TILE_SIZE/3, false);
			addComponent(collider);
		}
		else if (patternTopLeft.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE/2, Sprites.TILE_SIZE/2, 0, 0, false);
			addComponent(collider);
		}
		else if (patternTopRight.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE/2, Sprites.TILE_SIZE/2, Sprites.TILE_SIZE/2, 0, false);
			addComponent(collider);
		}
		else if (patternFull.contains(imageIndex))
		{
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE, Sprites.TILE_SIZE, 0, 0, false);
			addComponent(collider);
		}
		
		// Set interactable if specific map object that can interact with entities
		if (imageIndex == MapObjectsID.CHEST)
		{
			InteractComponent interact = new InteractComponent();
			addComponent(interact);
		}
	}

	/*----------------------------------------*/
	
	/**
	 * Get image index according to tileset.
	 * @return imageIndex
	 */
	public int getImageIndex() { return imageIndex; }
	
	/**
	 * Set image index according to tileset.
	 * @param _imageIndex New image index
	 */
	public void setImageIndex(int _imageIndex) { imageIndex = _imageIndex; } 
}
