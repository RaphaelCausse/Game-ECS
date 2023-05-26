package game.ecs.entity.items;

import game.Game;
import game.ecs.component.ColliderComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.Monster;
import utils.Settings.GameStatus;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Class that represents a poison potion that kill a monster.
 * @see AbstractItem
 */
public class PoisonPotion extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of PoisonPotion class.
	 * @param x X position
	 * @param y Y position
	 */
	public PoisonPotion(int x, int y)
	{
		super("Poison Potion (Kills a Golden Wyrm)");
		initialize(x, y);
	}

	@Override
	public void initialize(int x, int y)
	{
		// Create and add components
		PositionComponent position = new PositionComponent(x, y);
		addComponent(position);
		
		SpriteComponent sprite = new SpriteComponent(ResFiles.ITEM_POISON_POTION, Sprites.ITEM_SIZE, Sprites.ITEM_SIZE);
		addComponent(sprite);
		
		ColliderComponent collider = new ColliderComponent(
			x,							// x
			y,							// y
			sprite.getSpriteWidth(),	// w
			sprite.getSpriteHeight(),	// h
			0,							// ox
			0,							// oy
			false						// isMoveable
		);
		collider.setCollides(false);
		addComponent(collider);
	}
	
	@Override
	public void useItem(AbstractEntity owner, AbstractEntity target)
	{
		if (target instanceof Monster && ((Monster) target).getName() == "Golden Wyrm")
		{
			// Kill monster
			HealthComponent targetHealth = target.getComponent(HealthComponent.class);
			targetHealth.setCurrentHeath(0);
			used = true;
			
			// Remove item from inventory
			InventoryComponent ownerInventory = owner.getComponent(InventoryComponent.class);
			ownerInventory.removeItem(this);
			
			Game.gameStatus = GameStatus.GAME_WIN;
		}
	}

	/*----------------------------------------*/
}
