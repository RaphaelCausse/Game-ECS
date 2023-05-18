package game.ecs.component;

import game.ecs.FlagECS;

/**
 * Astract class that represents a base component for the entity.
 */
public abstract class AbstractComponent
{
	/*----------------------------------------*/
	
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

	/**
	 * Get component flag to tell if it needs to be updated by systems.
	 * @return flag
	 */
	public FlagECS getFlag() { return flag; }
	
	/**
	 * Set component flag to tell if it needs to be updated by systems.
	 * @param _flag New flag
	 */
	public void setFlag(FlagECS _flag) { flag = _flag; }
}
