package game.ecs.component;

import java.util.HashMap;
import java.util.Map;
import game.ecs.entity.items.AbstractItem;

/**
 *
 */
public class InventoryComponent extends AbstractComponent
{
	/*----------------------------------------*/

	private Map<AbstractItem, Integer> inventory;
	private int maxLength;
	private int selectedIndex;

	/*----------------------------------------*/
	
	public InventoryComponent(int _maxLength)
	{
		super();
		inventory = new HashMap<AbstractItem, Integer>();
		maxLength = _maxLength;
	}
	
	public void addItem(AbstractItem item)
	{
		if (!isFull())
		{
			inventory.put(item, (inventory.containsKey(item)) ? (inventory.get(item) + 1) : 1);
		}
	}
	
	public void removeItem(AbstractItem item)
	{
		if (inventory.get(item) > 1)
		{
			inventory.put(item, (inventory.get(item) - 1));
		}
		else
		{
			inventory.remove(item);
		}
	}
	
	public boolean isFull()
	{
		return inventory.size() == maxLength;
	}

	/*----------------------------------------*/
	
	public Map<AbstractItem, Integer> getInventory() { return inventory; }
	
	public int getSelectedIndex() { return selectedIndex; }
	
}
