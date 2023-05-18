package game.ecs.entity;

import java.util.HashMap;
import java.util.Map;
import game.ecs.component.AbstractComponent;

/**
 * Abstract class that represents a base entity.
 */
public abstract class AbstractEntity
{
	/*----------------------------------------*/
	
	public static int entityCount = 0;
	private int uid;
	private Map<Class<? extends AbstractComponent>, AbstractComponent> components;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of AbstractEntity class.
	 */
	public AbstractEntity()
	{
		uid = entityCount++;
		components = new HashMap<Class<? extends AbstractComponent>, AbstractComponent>();
	}
	
	/**
	 * Add a component to the entity.
	 * @param component Component to add
	 */
	public void addComponent(AbstractComponent component)
	{
		if (!hasComponent(component.getClass()))
		{
			components.put(component.getClass(), component);
		}
	}

	/**
	 * Remove a component from entity.
	 * @param componentClass Component class as key of hashmap
	 */
	public void removeComponent(Class<? extends AbstractComponent> componentClass)
	{
		if (hasComponent(componentClass))
		{
			components.remove(componentClass);
		}
	}
	
	/**
	 * Check if entity has a specific component.
	 * @param componentClass Component class as key of hashmap
	 * @return true, false
	 */
	public boolean hasComponent(Class<? extends AbstractComponent> componentClass)
	{
        return components.containsKey(componentClass);
    }
	
	/*----------------------------------------*/
	
	/**
	 * Get unique ID of entity.
	 * @return uid
	 */
	public int getUID() { return uid; }
	
	/**
	 * Get component of entity.
	 * @param <T> Component class type
	 * @param componentClass Component class as key of hashmap
	 * @return component
	 */
	public <T extends AbstractComponent> T getComponent(Class<T> componentClass)
	{
        return componentClass.cast(components.get(componentClass));
    }
}
