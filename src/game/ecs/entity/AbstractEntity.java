package game.ecs.entity;

import java.util.HashMap;
import java.util.Map;
import game.ecs.component.AbstractComponent;

/**
 * Classe abstraite qui represente une entity de base.
 */
public abstract class AbstractEntity
{
	/*----------------------------------------*/
	
	// Count track of number of entities
	public static int entityCount = 0;
	// Unique ID of entity
	private int uid;
	// Entity components collection
	private Map<Class<? extends AbstractComponent>, AbstractComponent> components;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe abstraite AbstractEntity.
	 */
	public AbstractEntity()
	{
		uid = entityCount++;
		components = new HashMap<Class<? extends AbstractComponent>, AbstractComponent>();
		
		// TODO Remove
		System.out.println("Entity created : " + uid);
	}
	
	/**
	 * Ajouter un composant a la liste des composants de l'entity.
	 * @param component Composant a ajouter
	 */
	public void addComponent(AbstractComponent component)
	{
		if (!hasComponent(component.getClass()))
		{
			components.put(component.getClass(), component);
		}
		
		System.out.println("Component added : " + component.getClass());
	}

	/**
	 * Enlever un composant a la liste des composants de l'entity.
	 * @param componentClass Classe du composant, cle de la collection
	 */
	public void removeComponent(Class<? extends AbstractComponent> componentClass)
	{
		if (hasComponent(componentClass))
		{
			components.remove(componentClass);
		}
	}
	
	/**
	 * Verifier si la collection de composant de l'entity comprend un composant.
	 * @param componentClass Classe du composant, cle de la collection
	 * @return true, false
	 */
	public boolean hasComponent(Class<? extends AbstractComponent> componentClass)
	{
        return components.containsKey(componentClass);
    }
	
	/*----------------------------------------*/
	
	/**
	 * Retourner le Unique ID de l'entity.
	 * @return uid
	 */
	public int getUID() { return uid; }
	
	/**
	 * Retourner un composant de la collection de l'entity.
	 * @return component
	 */
	public <T extends AbstractComponent> T getComponent(Class<T> componentClass)
	{
        return componentClass.cast(components.get(componentClass));
    }
}
