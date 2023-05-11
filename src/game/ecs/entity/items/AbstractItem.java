package game.ecs.entity.items;

import game.ecs.entity.AbstractEntity;

/**
 *
 */
public abstract class AbstractItem extends AbstractEntity
{
	/*----------------------------------------*/
	
	private String name;
	private boolean inInventory;
	
	/*----------------------------------------*/
	
	public AbstractItem(String _name)
	{
		super();
		name = _name;
	}

	/*----------------------------------------*/
	
	public String getName() { return name; }
	
	public boolean isInInventory() { return inInventory; }
	
	public void setInInventory(boolean b) { inInventory = b; }
	
}
