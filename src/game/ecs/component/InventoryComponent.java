package game.ecs.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.items.AbstractItem;

/**
 * Class that represents the inventory of the entity.
 * @see AbstractComponent
 */
public class InventoryComponent extends AbstractComponent
{
	/*----------------------------------------*/

	private List<AbstractItem> inventory;
	private int maxLength;
	private int currentIndex;

	/*----------------------------------------*/
	
	/**
	 * Constructor of InventoryComponent class.
	 * @param _maxLength Taille max de l'inventaire
	 */
	public InventoryComponent(int _maxLength)
	{
		super();
		inventory = new ArrayList<AbstractItem>();
		maxLength = _maxLength;
		currentIndex = 0;
	}
	
	/**
	 * Add an item to the inventory.
	 * @param item Item to add
	 */
	public void addItem(AbstractItem item)
	{
		if (!isFull())
		{
			inventory.add(item);
			currentIndex = inventory.indexOf(item);
			item.setInInventory(true);
		}
	}
	
	/**
	 * Remove an item from the inventory.
	 * @param item Item to remove
	 */
	public void removeItem(AbstractItem item)
	{
		if (inventory.contains(item))
		{
			inventory.remove(item);
			item.setInInventory(false);
		}
	}
	
	/**
	 * Drop an item on the ground.
	 * @param item Item to drop
	 */
	public void dropItem(AbstractEntity owner, AbstractItem item)
	{
		if (inventory.contains(item))
		{
			// Remove item from inventory
			inventory.remove(item);
			item.setInInventory(false);
			// Update item components
			PositionComponent itemPosition = item.getComponent(PositionComponent.class);
			ColliderComponent itemCollider = item.getComponent(ColliderComponent.class);
			
			// Determine drop position with a random offset
	        Random random = new Random();
        	int randOffsetX = random.nextInt(42) - 16;
        	int randOffsetY = random.nextInt(42) - 16;
        	int dropPositionX = (int)owner.getComponent(ColliderComponent.class).getBounds().getCenterX() + randOffsetX;
			int dropPositionY = (int)owner.getComponent(ColliderComponent.class).getBounds().getCenterY() + randOffsetY;
			// Set drop position
			itemPosition.setPos(dropPositionX, dropPositionY);
			itemCollider.updateBounds(itemPosition);
			EntityManager.addEntity(item.getUID(), item);
		}
	}
	
	/**
	 * Drop all the inventory on the ground.
	 * @param owner Owner of inventory
	 */
	public void dropInventory(AbstractEntity owner)
	{
		List<AbstractItem> toDrop = new ArrayList<>();
		for (AbstractItem item : inventory)
		{
			toDrop.add(item);
			item.setInInventory(false);
			// Update item components
			PositionComponent itemPosition = item.getComponent(PositionComponent.class);
			ColliderComponent itemCollider = item.getComponent(ColliderComponent.class);
			// Determine drop position with a random offset
	        Random random = new Random();
        	int randOffsetX = random.nextInt(20, 40);
        	int randOffsetY = random.nextInt(20, 40);
        	int randSign = random.nextInt(-1, 2);
        	if (randSign == 0)
        	{
        		randSign = 1;
        	}
        	int dropPositionX = (int)owner.getComponent(ColliderComponent.class).getBounds().getCenterX() + randOffsetX*randSign;
			int dropPositionY = (int)owner.getComponent(ColliderComponent.class).getBounds().getCenterY() + randOffsetY*randSign;
			// Set drop position
			itemPosition.setPos(dropPositionX, dropPositionY);
			itemCollider.updateBounds(itemPosition);
			EntityManager.addEntity(item.getUID(), item);
		}
		inventory.removeAll(toDrop);
	}
	
	/**
	 * Check if inventory is full.
	 * @return true, false
	 */
	public boolean isFull()
	{
		return inventory.size() == maxLength;
	}
	
	/**
	 * Update current item index in inventory.
	 * @param incr Increment
	 */
	public void moveCurrentIndex(int incr)
	{
		if (currentIndex + incr < 0)
		{
			currentIndex = maxLength - 1;
		}
		else if (currentIndex + incr > maxLength - 1)
		{
			currentIndex = 0;
		}
		else
		{
			currentIndex += incr;
		}
	}

	/*----------------------------------------*/
	
	/**
	 * Get the inventory.
	 * @return inventory
	 */
	public List<AbstractItem> getInventory() { return inventory; }
	
	/**
	 * Get current item index in inventory.
	 * @return currentIndex
	 */
	public int getCurrentIndex() { return currentIndex; }
	
	/**
	 * Get current item in inventory.
	 * @return item
	 */
	public AbstractItem getCurrentItem() { return inventory.get(currentIndex); }
	
	/**
	 * Set current item index in inventory.
	 * @param idx New index
	 */
	public void setCurrentIndex(int idx)
	{
		if (idx < maxLength) 
		{
			currentIndex = idx;
		}
	}
}
