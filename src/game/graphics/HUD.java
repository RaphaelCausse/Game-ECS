package game.graphics;

import java.util.List;
import game.ecs.component.ColliderComponent;
import game.ecs.component.AttackComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.Player;
import game.ecs.entity.items.AbstractItem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utils.Settings.Positions;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Window;

/**
 * Classe qui represente l'HUD.
 */
public class HUD
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	private Player linked;
	private Image healthbarEmpty;
	private Image healthFill;
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
		healthbarEmpty = new Image(ResFiles.HEALTH_BAR_EMPTY);
		healthFill = new Image(ResFiles.HEALTH_FILL);
		inventoryBar = new Image(ResFiles.INVENTORY);
		currentItemBorder = new Image(ResFiles.CURRENT_ITEM_BORDER);
	}
	
	/**
	 * Afficher l'HUD.
	 */
	public void render()
	{
//		renderRelativePosition();
		renderInventory();
		renderStats();
	}
	
	public void renderRelativePosition()
	{
		ColliderComponent relativePos = linked.getComponent(ColliderComponent.class);
		gctx.setFont(new Font("Arial", 14));
        gctx.setFill(Color.WHITE);
        String posText = "Pos (x: " + (int)relativePos.getBounds().getMinX() + ", y: " + (int)relativePos.getBounds().getMinY() + ")";
        gctx.fillText(posText, 0, Window.SCREEN_H-2);
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
			Positions.INVENTORY_BAR_X + Sprites.FIRST_BORDER_X + idx*Sprites.CURRENT_ITEM_BORDER_SIZE + idx*1, 	// dst X
			Positions.INVENTORY_BAR_Y + Sprites.FIRST_BORDER_Y													// dst Y
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
		
		// Render current item text
		if (inventory.getCurrentIndex() < inventory.getInventory().size())
		{
			gctx.setFont(new Font("Arial", 14));
	        gctx.setFill(Color.WHITE);
	        String itemText = inventory.getCurrentItem().getName();
	        gctx.fillText(itemText, Window.SCREEN_W/2 - itemText.length()*3 - itemText.length()%10, Positions.INVENTORY_BAR_Y - 4);
		}
	}
	
	/**
	 * Afficher la barre de points de vie.
	 */
	public void renderStats()
	{
		// Render health stat
		HealthComponent health = linked.getComponent(HealthComponent.class);
		double pourcentage = ((double) health.getCurrentHealth() / (double) health.getMaxHealth());
		gctx.drawImage(
			healthbarEmpty,				// image
			Positions.HEALTH_BAR_X, 	// dst X
			Positions.HEALTH_BAR_Y, 	// dst Y
			Sprites.HEALTH_BAR_W,		// dst W
			Sprites.HEALTH_BAR_H		// dst H			
		);
		gctx.drawImage(
			healthFill,					// image
			Positions.HEALTH_FILL_X,	// dst X
			Positions.HEALTH_FILL_Y,	// dst Y
			Math.floor(Sprites.HEALTH_FILL_W * pourcentage),	// dst Y
			Sprites.HEALTH_FILL_H					// dst Y
		);
		
		// Render damage stat
		AttackComponent damage = linked.getComponent(AttackComponent.class);
		gctx.setFont(new Font("Arial", 12));
		gctx.setStroke(Color.YELLOW);
        gctx.setFill(Color.YELLOW);
        String damageText = "ATK : " + damage.getDamage();
        gctx.strokeText(damageText, 5, 32);
        gctx.fillText(damageText, 5, 32);
	}
	
	/*----------------------------------------*/
}
