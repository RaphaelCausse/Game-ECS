package game.ecs.component;

import game.ecs.FlagECS;

/**
 * Classe abstraire qui represente un composant des entites.
 */
public abstract class AbstractComponent
{
	/*----------------------------------------*/
	
	// Update component flag
	protected FlagECS flag;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe abstraite AbstractComponent;
	 */
	public AbstractComponent()
	{
		flag = FlagECS.TO_UPDATE;
	}
	
	/*----------------------------------------*/

	public FlagECS getFlag() { return flag; }
	
	public void setFlag(FlagECS _flag) { flag = _flag; }
}
