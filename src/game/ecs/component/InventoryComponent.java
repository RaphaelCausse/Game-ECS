package game.ecs.component;

import java.util.ArrayList;
import java.util.List;
import game.ecs.entity.items.AbstractItem;

/**
 *
 */
public class InventoryComponent extends AbstractComponent
{
	/*----------------------------------------*/

	private List<AbstractItem> inventory;
	private int maxLength;
	private int currentIndex;

	/*----------------------------------------*/
	
	public InventoryComponent(int _maxLength)
	{
		super();
		inventory = new ArrayList<AbstractItem>();
		maxLength = _maxLength;
		currentIndex = 0;
	}
	
	public void addItem(AbstractItem item)
	{
		if (!isFull())
		{
			inventory.add(item);
			item.setInInventory(true);
		}
	}
	
	public void removeItem(AbstractItem item)
	{
		if (inventory.contains(item))
		{
			inventory.remove(item);
			item.setInInventory(false);
		}
	}
	
	public boolean isFull()
	{
		return inventory.size() == maxLength;
	}
	
	public void moveCurrentIndex(int d)
	{
		if (currentIndex + d < 0)
		{
			currentIndex = maxLength - 1;
		}
		else if (currentIndex + d > maxLength - 1)
		{
			currentIndex = 0;
		}
		else
		{
			currentIndex += d;
		}
	}

	/*----------------------------------------*/
	
	public List<AbstractItem> getInventory() { return inventory; }
	
	public int getCurrentIndex() { return currentIndex; }
	
	public void setCurrentIndex(int idx)
	{
		if (idx < maxLength) 
		{
			currentIndex = idx;
		}
	}
	
}
