package game.ecs.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.ecs.component.AbstractComponent;

/**
 * Class responsible of entity management.
 */
public class EntityManager
{
	/*----------------------------------------*/
	
	private static EntityManager entityManager = new EntityManager();
	private static Map<Integer, AbstractEntity> entities = new HashMap<Integer, AbstractEntity>();
	
	/*----------------------------------------*/
	
	/**
	 * Private constructor of EntityManager class.
	 */
	private EntityManager()
	{
	}
	
	/**
	 * Add entity into the hashmap.
	 * @param uid Unique ID of entity
	 * @param entity Entite to add
	 */
	public static void addEntity(int uid, AbstractEntity entity)
	{
		entities.put(uid, entity);
	}
	
	/**
	 * Remove entity from hashmap.
	 * @param uid Unique ID of entity to remove
	 */
	public static void removeEntity(int uid)
	{
		entities.remove(uid);
	}
	
	/**
	 * Get lsit of entity with a specific component.
	 * @param componentClass Component to search
	 * @return entities
	 */
	public static List<AbstractEntity> getEntitiesWithComponent(Class<? extends AbstractComponent> componentClass)
	{
	    List<AbstractEntity> entitiesWithComponent = new ArrayList<AbstractEntity>();
	    for (AbstractEntity entity : entities.values())
	    {
	        if (entity.hasComponent(componentClass))
	        {
	            entitiesWithComponent.add(entity);
	        }
	    }
	    return entitiesWithComponent;
	}
	
	/*----------------------------------------*/

	/**
	 * Get instance of EntityManager.
	 * @return entityManager
	 */
	public static EntityManager getInstance() { return entityManager; }

	/**
	 * Get entity with the corresponding UID.
	 * @param uid Unique ID of searched entity
	 * @return entity
	 */
	public static AbstractEntity getEntity(int uid) { return entities.get(uid); }
}
