package game.ecs.component;

/**
 *
 */
public class InteractComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int targetUID;
	
	/*----------------------------------------*/
	
	/**
	 * 
	 */
	public InteractComponent()
	{
		super();
	}

	/*----------------------------------------*/
	
	public int getTargetUID() { return targetUID; }
	
	public void setTargetUID(int _targetUID) { targetUID = _targetUID; } 
}
