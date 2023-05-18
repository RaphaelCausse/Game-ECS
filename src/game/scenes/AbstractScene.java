package game.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.Settings.App;

/**
 * Abstract class that represents a base scene for the application.
 * @see Scene
 */
public abstract class AbstractScene extends Scene
{
	/*----------------------------------------*/
	
	protected Pane root;
	
	/*----------------------------------------*/
	
	/**
     * Constructor of AbstractScene class.
     * @param _sceneManager Scene manager
     * @param _root Root node of scene graph
     */
	public AbstractScene(SceneManager _sceneManager, Pane _root)
	{
		super(_root, App.SCREEN_W, App.SCREEN_H);
		root = _root;
	}
	
	/**
	 * Initialize current scene.
	 */
	public abstract void initialize();
	
	/**
	 * Start current scene.
	 */
	public abstract void start();
	
	/**
	 * Update current scene.
	 */
	public abstract void update();
	
	/*----------------------------------------*/
}
