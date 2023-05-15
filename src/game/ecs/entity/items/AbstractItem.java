package game.ecs.entity.items;

import game.ecs.entity.AbstractEntity;

/**
 * Classe abstraite qui represente un item.
 * @see AbstractEntity
 */
public abstract class AbstractItem extends AbstractEntity
{
	/*----------------------------------------*/
	
	private String name;
	private boolean inInventory;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe abstraite AbstractItem.
	 * @param _name
	 */
	public AbstractItem(String _name)
	{
		super();
		name = _name;
		inInventory = false;
	}

	/*----------------------------------------*/
	
	public String getName() { return name; }
	
	public boolean isInInventory() { return inInventory; }
	
	public void setInInventory(boolean _inInventory) { inInventory = _inInventory; }
}
