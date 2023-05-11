package game.graphics;

import game.ecs.component.InventoryComponent;
import game.ecs.entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Settings.Positions;
import utils.Settings.ResFiles;

/**
 *
 */
public class HUD
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	private Player linked;
	private Image inventoryBar;
	
	/*----------------------------------------*/
	
	public HUD(GraphicsContext _gctx, Player _linked)
	{
		gctx = _gctx;
		linked = _linked;
		inventoryBar = new Image(ResFiles.INVENTORY);
	}
	
	public void render()
	{
		gctx.drawImage(inventoryBar, Positions.INVENTORY_BAR_X, Positions.INVENTORY_BAR_Y);
	}

	public void renderInventoryItems()
	{
		InventoryComponent inventory = linked.getComponent(InventoryComponent.class);
	}
	
	public void renderHealth()
	{
		
	}
	
	/*----------------------------------------*/
}
