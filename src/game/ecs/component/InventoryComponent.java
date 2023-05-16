package game.ecs.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.items.AbstractItem;

/**
 * Classe qui represente l'inventaire d'item de l'entite.
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
	 * Constructeur de la classe InventoryComponent.
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
	 * Ajouter un item a l'inventaire.
	 * @param item Item a ajouter
	 */
	public void addItem(AbstractItem item)
	{
		if (!isFull())
		{
			inventory.add(item);
			item.setInInventory(true);
		}
	}
	
	/**
	 * Retirer un item de l'inventaire.
	 * @param item Item a retirer
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
	 * Deposer un item de l'inventaire au sol.
	 * @param item Item a deposer
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
			
			// TODO Improve: set drop pos in the opposite direction the entity is facing
			
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
	 * Verifier si l'inventaire est plein.
	 * @return true, false
	 */
	public boolean isFull()
	{
		return inventory.size() == maxLength;
	}
	
	/**
	 * Mise a jour de l'index de l'item courant dans l'inventaire.
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
	
	public List<AbstractItem> getInventory() { return inventory; }
	
	public int getCurrentIndex() { return currentIndex; }
	
	public AbstractItem getCurrentItem() { return inventory.get(currentIndex); }
	
	public void setCurrentIndex(int idx)
	{
		if (idx < maxLength) 
		{
			currentIndex = idx;
		}
	}
}
