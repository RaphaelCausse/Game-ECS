package game.ecs.entity.items;

import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Classe qui represente un item cle de coffre.
 * @see AbstractItem
 */
public class Key extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constucteur de la classe Key.
	 * @param x Position sur la map en X
	 * @param y Position sur la map en Y
	 * @param inInventory Est dans l'inventaire
	 */
	public Key(int x, int y, boolean inInventory)
	{
		super("Chest key");
		initialize(x, y);
	}
	
	/**
	 * Initialisation des composants.
	 * @param x Position sur la map en X
	 * @param y Position sur la map en Y
	 */
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_KEY, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
		addComponent(sprite);
	}

	/*----------------------------------------*/
}
