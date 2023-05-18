package game.ecs.component;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.AbstractEntity;
import utils.Settings.Window;

/**
 *
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
	 * Constructeur de la classe InteractComponent.
	 */
	public InteractComponent(List<String> _dialogs)
	{
		super();
		activated = false;
		interacting = false;
		canInteract = true;
		dialogs = _dialogs;
		dialogDelay = Window.FPS*5;
		countDelay = 0;
	}
	
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
	
	public boolean isActivated() { return activated; }
	
	public List<String> getDialogs() { return dialogs; }
	
	public void setActivated(boolean _activated) { activated = _activated; }
}
