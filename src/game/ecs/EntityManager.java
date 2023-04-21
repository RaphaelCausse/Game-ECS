package game.ecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game.ecs.component.AbstractComponent;
import game.ecs.entity.AbstractEntity;

/**
 * Classe responsable de la gestion des entites.
 */
public class EntityManager
{
	/*----------------------------------------*/
	
	// Instance of itsef
	private static EntityManager entityManager = new EntityManager();
	// Collection of entities assiciated with their uid
	private static Map<Integer, AbstractEntity> entities = new HashMap<Integer, AbstractEntity>();
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur prive de la classe EntityManager.
	 */
	private EntityManager()
	{
	}
	
	/**
	 * Ajouter une entite dans la collection des entities.
	 * @param uid Unique ID de l'entity a ajouter
	 * @param entity Entite a ajouter
	 */
	public static void addEntity(int uid, AbstractEntity entity)
	{
		entities.put(uid, entity);
	}
	
	/**
	 * Enlever une entite de la collection des entites.
	 * @param uid Unique ID de l'entity a enlever
	 */
	public static void removeEntity(int uid)
	{
		entities.remove(uid);
	}
	
	/*----------------------------------------*/
	
	/**
	 * Retourner l'instance de la classe.
	 * @return entityManager
	 */
	public static EntityManager getInstance() { return entityManager; }
		
	/**
	 * Retourner l'entite avec le uid correspondant.
	 * @param uid Unique ID de l'entite a recuperer
	 * @return entity
	 */
	public static AbstractEntity getEntity(int uid) { return entities.get(uid); }
	
	/**
	 * Recuperer toutes les entites qui contiennent un composant donne.
	 * @param componentClass Classe du composant à chercher
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
}
