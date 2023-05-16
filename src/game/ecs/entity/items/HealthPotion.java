package game.ecs.entity.items;

import game.ecs.component.HealthComponent;
import game.ecs.entity.AbstractEntity;

/**
 *
 */
public class HealthPotion extends AbstractItem
{
	/*----------------------------------------*/
	
	private int restoreValue;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe HealthPotion.
	 * @param _name Nom de l'item
	 */
	public HealthPotion(int _restoreValue)
	{
		super("Health Potion");
		restoreValue = _restoreValue;
	}

	@Override
	public void useItem(AbstractEntity sender, AbstractEntity receiver)
	{
		// Restore health
		HealthComponent senderHealth = sender.getComponent(HealthComponent.class);
		int newHealthValue = (senderHealth.getCurrentHealth() + restoreValue > senderHealth.getMaxHealth()) ?
			senderHealth.getMaxHealth() :
			senderHealth.getCurrentHealth() + restoreValue;
		senderHealth.setCurrentHeath(newHealthValue);
	}

	/*----------------------------------------*/
}
