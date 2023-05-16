package game.ecs.entity.items;

import game.ecs.entity.AbstractEntity;

/**
 * Classe abstraite qui represente un item.
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
	 * Initialisation des composants de l'item.
	 * @param x Position en X
	 * @param y Position en Y
	 */
	public abstract void initialize(int x, int y);

	/**
	 * Utiliser l'item sur l'entite cible.
	 * @param sender Entite qui effectue l'action de l'item
	 * @param receiver Entite cible par l'action de l'item
	 */
	public abstract void useItem(AbstractEntity sender, AbstractEntity receiver);
	
	/*----------------------------------------*/
	
	public String getName() { return name; }
	
	public boolean isUsed() { return used; }
	
	public boolean isInInventory() { return inInventory; }
	
	public void setInInventory(boolean _inInventory) { inInventory = _inInventory; }
}
