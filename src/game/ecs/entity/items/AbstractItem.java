package game.ecs.entity.items;

import game.ecs.entity.AbstractEntity;

/**
 * Abstract class that represents an item.
 * @see AbstractEntity
 */
public abstract class AbstractItem extends AbstractEntity
{
	/*----------------------------------------*/
	
	protected String name;
	protected boolean used;
	protected boolean inInventory;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of AbstractItem class.
	 * @param _name
	 */
	public AbstractItem(String _name)
	{
		super();
		name = _name;
		inInventory = false;
	}
	
	/**
	 * Initialize item components.
	 * @param x X position
	 * @param y Y position
	 */
	public abstract void initialize(int x, int y);

	/**
	 * Use item.
	 * @param owner Entity that owns the item
	 * @param target Entity targeted by the item
	 */
	public abstract void useItem(AbstractEntity owner, AbstractEntity target);
	
	/*----------------------------------------*/
	
	/**
	 * Get item name.
	 * @return name
	 */
	public String getName() { return name; }
	
	/**
	 * Check if item is used.
	 * @return used
	 */
	public boolean isUsed() { return used; }
	
	/**
	 * Check if item is in inventory.
	 * @return inInventory
	 */
	public boolean isInInventory() { return inInventory; }
	
	/**
	 * Set in inventory.
	 * @param _inInventory
	 */
	public void setInInventory(boolean _inInventory) { inInventory = _inInventory; }
}
