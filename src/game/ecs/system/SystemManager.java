package game.ecs.system;

import java.util.ArrayList;
import java.util.List;

import game.graphics.Camera;
import game.graphics.HUD;
import javafx.scene.Scene;

/**
 * Class responsible of system management.
 */
public class SystemManager
{
	/*----------------------------------------*/
	
    private List<AbstractSystem> systems;
    
    /*----------------------------------------*/
    
    /**
     * Contructor of SystemManager class.
     * @param scene Scene
     */
    public SystemManager(Scene scene, Camera camera, HUD hud)
    {
    	systems = new ArrayList<AbstractSystem>();
    	
    	// Order systems for execution
    	addSystem(new KeyInputSystem(scene));
    	addSystem(new MovementSystem());
    	addSystem(new AnimationSystem());
    	addSystem(new DetectionSystem(camera.getMap()));
    	addSystem(new CollisionSystem(camera.getMap()));
    	addSystem(new AttackSystem());
    	addSystem(new InteractSystem());
    	addSystem(new InventorySystem(camera.getMap()));
    	addSystem(new RenderSystem(camera, hud));
    }

    /**
     * Add a system to the list.
     * @param system System to add
     */
    public void addSystem(AbstractSystem system)
    {
        systems.add(system);
    }

    /**
     * Remove a system from the list.
     * @param system System to remove
     */
    public void removeSystem(AbstractSystem system)
    {
        systems.remove(system);
    }

    /**
     * Update the systems.
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