package game.scenes;

import java.util.HashMap;
import javafx.stage.Stage;

/**
 * Class responsible of scenes management.
 */
public class SceneManager
{
	/*----------------------------------------*/
	
	private Stage stage;
	private AbstractScene activeScene;
	private HashMap<String, AbstractScene> scenes;
	
	/*----------------------------------------*/

	/**
	 * Constructor of SceneManager class.
	 * @param _stage Stage principal de l'application
	 */
	public SceneManager(Stage _stage)
	{
		stage = _stage;
		scenes = new HashMap<>();
	}
	
	/**
	 * Add a scene to manager.
	 * @param name Scene name
	 * @param scene Scene to add
	 */
	public void addScene(String name, AbstractScene scene) {
        if (!scenes.containsKey(name))
        {
            scenes.put(name, scene);
        }
    }
	
	/**
	 * Remove a scene from manager.
	 * @param name Scene name
	 */
	public void removeScene(String name) {
        if (scenes.containsKey(name))
        {
            scenes.remove(name);
        }
    }
	
	/**
	 * Activate a scene.
	 * @param name Scene name
	 */
	public void activate(String name) {
        activeScene = scenes.get(name);
        stage.setScene(activeScene);
        stage.sizeToScene();
        start();
    }
	
	/**
	 * Start active scene.
	 */
	public void start()
	{
		activeScene.start();
	}
	
	/**
	 * Update active scene.
	 */
	public void update()
	{
		activeScene.update();
	}
	
	/*----------------------------------------*/
	
	/**
	 * Get stage.
	 * @return stage
	 */
	public Stage getStage() { return stage; }

	/**
	 * Get active scene.
	 * @return activeScene
	 */
	public AbstractScene getActiveScene() { return activeScene; }
	
	/**
	 * Get specific scene.
	 * @param name Scene name
	 * @return activeScene
	 */
	public AbstractScene getScene(String name) { return scenes.get(name); }
}