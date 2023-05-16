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
 * Classe qui represente un objet de la map de jeu.
 * @see AbstractEntity
 */
public class MapObject extends AbstractEntity
{
	/*----------------------------------------*/
	
	private int imageIndex;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe MapObject.
	 * @param x
	 * @param y
	 * @param index
	 */
	public MapObject(int x, int y, int index)
	{
		super();
		imageIndex = index;
		initialize(x, y);
	}
	
	/**
	 * Initialisation des composants de l'objet.
	 * @param x
	 * @param y
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
		patternBottom.addAll(Arrays.asList());
		List<Integer> patternTopLeft = new ArrayList<>(); 	// Top left side colliders
		patternTopLeft.addAll(Arrays.asList(45));
		List<Integer> patternTopRight = new ArrayList<>(); 	// Top right side colliders
		patternTopRight.addAll(Arrays.asList(44));
		List<Integer> patternFull = new ArrayList<>();	 	// Full colliders
		patternFull.addAll(Arrays.asList(26, 28, 37, 42, 43, 46, 47, 54, 56, 57, 58, 59, 60, 61, 68, 70, 71, 72, 73, 74, 75, 82, 83));
		
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
			ColliderComponent collider = new ColliderComponent(x, y, Sprites.TILE_SIZE, Sprites.TILE_SIZE/2, 0, Sprites.TILE_SIZE/2, false);
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
	
	public int getImageIndex() { return imageIndex; }
	
	public void setImageIndex(int _imageIndex) { imageIndex = _imageIndex; } 
}
