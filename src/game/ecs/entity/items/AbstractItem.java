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

	/**
	 * Utiliser l'item sur l'entite cible.
	 * @param sender Entite qui effectue l'action de l'item
	 * @param receiver Entite cible par l'action de l'item
	 */
	public abstract void useItem(AbstractEntity sender, AbstractEntity receiver);
	
	/*----------------------------------------*/
	
	public String getName() { return name; }
	
	public boolean isInInventory() { return inInventory; }
	
	public void setInInventory(boolean _inInventory) { inInventory = _inInventory; }
}
