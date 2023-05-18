package game.ecs.component;

import game.ecs.entity.AbstractEntity;

/**
 * Interface for interaction between entities.
 */
public interface Interactable
{
	/**
	 * Interaction between two entities.
	 * @param sender Entity that sends the interaction
	 * @param receiver Entite that receives the interaction
	 */
	public void interact(AbstractEntity sender, AbstractEntity receiver);
}
