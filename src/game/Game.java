package game;

import game.scenes.GameScene;
import game.scenes.SceneManager;
import javafx.scene.Group;
import javafx.stage.Stage;
import utils.Settings.Window;;

/**
 * Classe responsable de la creation et de l'execution du jeu.
 */
public class Game
{
	/*----------------------------------------*/
	
	private SceneManager sceneManager;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Game.
	 * @param stage Stage principal de l'application
	 */
	public Game(Stage stage) 
	{
		// Setup stage
		stage.setTitle(Window.TITLE);
		stage.setResizable(false);
		
		// Init SceneManager and Scenes
		sceneManager = new SceneManager(stage);
		
		GameScene gameScene = new GameScene(sceneManager, new Group());
		
		sceneManager.pushScene(gameScene);
	}
	
	/**
	 * Afficher et lance l'execution de la scene courant.
	 */
	public void run()
	{
		sceneManager.getStage().show();
		sceneManager.start();
	}
	
	/**
	 * Mettre a jour le gestionnaire de scene et tous ses elements.
	 */
	public void update()
	{
		sceneManager.update();
	}
	
	/**
	 * Stopper le jeu.
	 */
	public void stop()
	{
		sceneManager.stop();
	}
	
	/*----------------------------------------*/
}
