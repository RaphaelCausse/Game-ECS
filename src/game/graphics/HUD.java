package game.graphics;

import java.util.List;
import game.ecs.component.InventoryComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.Player;
import game.ecs.entity.items.AbstractItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Settings.Positions;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Classe qui represente l'HUD.
 */
public class HUD
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	private Player linked;
	private Image inventoryBar;
	private Image currentItemBorder;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe HUD.
	 * @param _gctx Contexte graphique
	 * @param _linked Entite liee a l'HUD pour l'affichage de ses donnees
	 */
	public HUD(GraphicsContext _gctx, Player _linked)
	{
		gctx = _gctx;
		linked = _linked;
		inventoryBar = new Image(ResFiles.INVENTORY);
		currentItemBorder = new Image(ResFiles.CURRENT_ITEM_BORDER);
	}
	
	/**
	 * Afficher l'HUD.
	 */
	public void render()
	{
		renderInventory();
		renderHealth();
	}

	/**
	 * Afficher l'inventaire et les items qu'il contient.
	 */
	public void renderInventory()
	{
		// Render inventory bar
		gctx.drawImage(
			inventoryBar,				// image
			Positions.INVENTORY_BAR_X,	// dst X
			Positions.INVENTORY_BAR_Y	// dst Y
		);
		
		InventoryComponent inventory = linked.getComponent(InventoryComponent.class);
		// Render border of current item
		int idx = inventory.getCurrentIndex();
		gctx.drawImage(
			currentItemBorder,	// image
			Positions.INVENTORY_BAR_X + Sprites.FIRST_BORDER_X + idx*Sprites.BORDER_SIZE + idx*1, 	// dst X
			Positions.INVENTORY_BAR_Y + Sprites.FIRST_BORDER_Y										// dst Y
		);
	
		// Render items
		List<AbstractItem> items = inventory.getInventory();
		for (int i = 0; i < items.size(); i++)
		{
			SpriteComponent sprite = items.get(i).getComponent(SpriteComponent.class);
			gctx.drawImage(
				sprite.getSpritesheet(),	// image
				Positions.INVENTORY_BAR_X + Sprites.FIRST_ITEM_X + i*Sprites.ITEM_SIZE + i*Sprites.ITEM_SPACING, 	// dst X
				Positions.INVENTORY_BAR_Y + Sprites.FIRST_ITEM_Y,													// dst Y
				Sprites.ITEM_SIZE,			// dst W
				Sprites.ITEM_SIZE			// dst H
			);
		}
	}
	
	/**
	 * Afficher la barre de points de vie.
	 */
	public void renderHealth()
	{
		// TODO
		// HealthComponent health = linked.getComponent(HealthComponent.class);
	}
	
	/*----------------------------------------*/
}
