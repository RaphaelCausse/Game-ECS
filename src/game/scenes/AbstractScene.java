package game.scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import utils.Settings.Window;

/**
 * Classe abstraite qui represente une scene de base pour JavaFX.
 * @see Scene
 */
public abstract class AbstractScene extends Scene
{
	/*----------------------------------------*/
	
	private SceneManager sceneManager;
	
	/*----------------------------------------*/
	
	/**
     * Constructeur de la classe AbstractScene.
     * @param _sceneManager Gestionnaire de scene
     * @param _root Groupe racine de la scene
     */
	public AbstractScene(SceneManager _sceneManager, Group _root)
	{
		super(_root, Window.SCREEN_W, Window.SCREEN_H);
		sceneManager = _sceneManager;
	}
	
	/**
	 * Mettre a jour la scene et tout ses elements.
	 */
	public abstract void update();
	
	/*----------------------------------------*/

	public SceneManager getSceneManager() { return sceneManager; }
}
