package game;

import game.scenes.AbstractScene;
import game.scenes.EndGameScene;
import game.scenes.GameScene;
import game.scenes.IntroScene;
import game.scenes.SceneManager;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Settings.App;
import utils.Settings.GameStatus;;

/**
 * Class responsible of game logic.
 */
public class Game
{
	/*----------------------------------------*/
	
	public static GameStatus gameStatus;
	private Timeline gameloop;
	public static SceneManager sceneManager;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of Game class.
	 * @param stage Main stage of application
	 */
	public Game(Stage stage) 
	{
		gameStatus = GameStatus.GAME_RUNNING;
		
		// Setup stage
		stage.setTitle(App.TITLE);
		stage.setResizable(false);
		
		// Init SceneManager and Scenes
		sceneManager = new SceneManager(stage);
		
		AbstractScene introScene = new IntroScene(sceneManager, new StackPane());
		AbstractScene gameScene = new GameScene(sceneManager, new StackPane());
		AbstractScene endgameScene = new EndGameScene(sceneManager, new StackPane());
		
		sceneManager.addScene("IntroScene", introScene);
		sceneManager.addScene("GameScene", gameScene);
		sceneManager.addScene("EndGameScene", endgameScene);
		
		// Init gameloop
		gameloop = new Timeline(new KeyFrame(App.FRAME_TIME, event -> {
			update();
		}));
		gameloop.setCycleCount(Animation.INDEFINITE);
	}
	
	/**
	 * Run the game.
	 */
	public void run()
	{
		gameloop.play();
		sceneManager.activate("IntroScene"); 
		sceneManager.start();
		sceneManager.getStage().show();
	}
	
	/**
	 * Update the game.
	 */
	public void update()
	{
		sceneManager.update();
	}
	
	/*----------------------------------------*/
}
