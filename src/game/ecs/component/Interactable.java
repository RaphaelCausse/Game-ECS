package game.ecs.component;

import game.ecs.entity.AbstractEntity;

/**
 *
 */
public interface Interactable
{
	/**
	 * Comportement d'interaction entre deux entites.
	 * @param sender Entite qui lance l'interaction
	 * @param receiver Entite qui recoit l'interaction
	 */
	public void interact(AbstractEntity sender, AbstractEntity receiver);
}
