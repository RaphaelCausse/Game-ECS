package game.scenes;

import java.util.HashMap;
import java.util.Map;
import game.ecs.EntityManager;
import game.ecs.SystemManager;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.Player;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.Settings.Movement;
import utils.Settings.Window;

/**
 * Classe responsable de la scene de jeu, geree par le gestionnaire de scene.
 * @see AbstractScene
 */
public class GameScene extends AbstractScene
{
	/*----------------------------------------*/
	
	// Graphics elements
	public Canvas canvas;
	public GraphicsContext gctx;
	// Key inputs buffer
	public static Map<KeyCode, Boolean> keyInputs = new HashMap<KeyCode, Boolean>();
	// Entity manager for ECS
	private EntityManager entityManager;
	// System manager for ECS
	private SystemManager systemManager;
	
	/*----------------------------------------*/
	
	/**
	 * Constructueur de la classe GameScene.
	 * @param _sceneManager Gestionnaire de scene
	 * @param _root	Racine de l'arbre de scene
	 */
	public GameScene(SceneManager _sceneManager, Group _root)
	{
		super(_sceneManager, _root);
		
		// Init graphics elements
		canvas = new Canvas(Window.SCREEN_W, Window.SCREEN_W);
		gctx = canvas.getGraphicsContext2D();
		_root.getChildren().add(canvas);
		
		// Init managers
		entityManager = EntityManager.getInstance();
		systemManager = new SystemManager(this, gctx);
		
		createEntities();
	}
	
	/**
	 * Creation des entites du jeu.
	 */
	@SuppressWarnings("static-access")
	public void createEntities()
	{
		AbstractEntity player = new Player(10, 10, Movement.PLAYER_SPEED);
		entityManager.addEntity(player.getUID(), player);
	}

	@Override
	public void update()
	{
		systemManager.update();
	}
	
	/*----------------------------------------*/
}
