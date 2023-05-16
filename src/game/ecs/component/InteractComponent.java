package game.ecs.component;

import game.ecs.entity.AbstractEntity;

/**
 *
 */
public class InteractComponent extends AbstractComponent implements Interactable
{
	/*----------------------------------------*/
	
	private boolean activated;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe InteractComponent.
	 */
	public InteractComponent()
	{
		super();
		activated = false;
	}
	
	@Override
	public void interact(AbstractEntity sender, AbstractEntity receiver)
	{
	}

	/*----------------------------------------*/
	
	public boolean isActivated() { return activated; }
	
	public void setActivated(boolean _activated) { activated = _activated; }
}
