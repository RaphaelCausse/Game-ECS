package game;

import game.scenes.GameScene;
import game.scenes.SceneManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
	private Timeline gameloop;
	
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
		
		// Init gameloop
		gameloop = new Timeline(new KeyFrame(Window.FRAME_TIME, event -> {
			update();
		}));
		gameloop.setCycleCount(Animation.INDEFINITE);
	}
	
	/**
	 * Afficher la scene courante et lance la boucle de jeu.
	 */
	public void run()
	{
		sceneManager.getStage().show();
		gameloop.play();
	}
	
	/**
	 * Mettre a jour le gestionnaire de scene et tous ses elements.
	 */
	public void update()
	{
		sceneManager.update();
	}
	
	/**
	 * Stopper l'application.
	 */
	public void stop()
	{
		gameloop.stop();
	}
	
	/*----------------------------------------*/
}
