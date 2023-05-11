package game.ecs.system;

import java.util.ArrayList;
import java.util.List;

import game.graphics.Camera;
import game.graphics.HUD;
import javafx.scene.Scene;

/**
 * Classe responsable de la gestion des sytemes.
 */
public class SystemManager
{
	/*----------------------------------------*/
	
    private List<AbstractSystem> systems;
    
    /*----------------------------------------*/
    
    /**
     * Contructeur de la classe SystemManager.
     * @param scene
     */
    public SystemManager(Scene scene, Camera camera, HUD hud)
    {
    	systems = new ArrayList<AbstractSystem>();
    	
    	// Order systems for execution
    	addSystem(new KeyInputSystem(scene));
    	addSystem(new MovementSystem());
    	addSystem(new CollisionSystem(camera.getMap()));
    	addSystem(new AnimationSystem());
    	addSystem(new InventorySystem());
    	addSystem(new RenderSystem(camera, hud));
    }

    /**
     * Ajouter un systeme a la liste des systemes.
     * @param system Systeme a ajouter
     */
    public void addSystem(AbstractSystem system)
    {
        systems.add(system);
    }

    /**
     * Enlever un systeme de la liste des systemes.
     * @param system Systeme a enlever
     */
    public void removeSystem(AbstractSystem system)
    {
        systems.remove(system);
    }

    /**
     * Mettre a jour les systemes.
     */
    public void update()
    {
        for (AbstractSystem system : systems)
        {
            system.run();
        }
    }
    
    /*----------------------------------------*/
}