package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.AbstractEntity;
import utils.Settings.App;

/**
 * Class that represents an interaction component.
 */
public class InteractComponent extends AbstractComponent implements Interactable
{
	/*----------------------------------------*/
	
	private boolean activated;
	public boolean canInteract;
	public boolean interacting;
	private List<String> dialogs;
	public int currentDialogIndex;
	private int dialogDelay;
	private int countDelay;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of InteractComponent class.
	 */
	public InteractComponent(List<String> _dialogs)
	{
		super();
		activated = false;
		interacting = false;
		canInteract = true;
		dialogs = _dialogs;
		dialogDelay = App.FPS*5;
		countDelay = 0;
	}
	
	/**
	 * Default constructor of InteractComponent class.
	 */
	public InteractComponent()
	{
		this(new ArrayList<>());
	}
	
	@Override
	public void interact(AbstractEntity sender, AbstractEntity receiver)
	{		
		InteractComponent receiverInteract = receiver.getComponent(InteractComponent.class);
		interacting = true;
		receiverInteract.interacting = true;
		receiverInteract.setActivated(true);
		countDelay++;
		if (countDelay > dialogDelay)
		{
			currentDialogIndex++;
			if (currentDialogIndex >= dialogs.size())
			{
				currentDialogIndex = 0;
				receiverInteract.setActivated(false);
			}
			countDelay = 0;
		}		
	}

	/*----------------------------------------*/
	
	/**
	 * Check if is activated.
	 * @return true, false
	 */
	public boolean isActivated() { return activated; }
	
	/**
	 * Get list of dialogs.
	 * @return dialogs
	 */
	public List<String> getDialogs() { return dialogs; }
	
	/**
	 * Set activated.
	 * @param _activated
	 */
	public void setActivated(boolean _activated) { activated = _activated; }
}
