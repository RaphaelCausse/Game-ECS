package game.ecs;

import java.util.ArrayList;
import java.util.List;
import game.ecs.system.AbstractSystem;
import game.ecs.system.KeyInputSystem;
import game.ecs.system.MovementSystem;
import game.ecs.system.RenderSystem;
import game.ecs.system.SpriteSystem;
import game.scenes.GameScene;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
    public SystemManager(Scene scene, GraphicsContext gctx)
    {
    	systems = new ArrayList<AbstractSystem>();
    	
    	// Order systems for execution
    	addSystem(new KeyInputSystem(scene));
    	addSystem(new MovementSystem());
    	addSystem(new SpriteSystem());
    	addSystem(new RenderSystem(gctx));
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