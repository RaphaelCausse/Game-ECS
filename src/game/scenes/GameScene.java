package game.scenes;

import java.util.HashMap;
import java.util.Map;
import game.ecs.EntityManager;
import game.ecs.SystemManager;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.Blacksmith;
import game.ecs.entity.Player;
import game.graphics.Camera;
import game.graphics.GameMap;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.Settings.Movement;
import utils.Settings.Positions;
import utils.Settings.Sprites;
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
	// Managers for ECS
	private EntityManager entityManager;
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
		
		initialize();
	}
	
	/**
	 * Initialisation du jeu.
	 */
	@SuppressWarnings("static-access")
	public void initialize()
	{
		// Create entities
		AbstractEntity player = new Player(
			Positions.PLAYER_SPAWN_X,
			Positions.PLAYER_SPAWN_Y,
			Movement.PLAYER_SPEED,
			Sprites.ANIM_FRAMES
		);
		AbstractEntity blacksmith = new Blacksmith(
			Positions.BLACKSMITH_SPAWN_X,
			Positions.BLACKSMITH_SPAWN_Y,
			Sprites.ANIM_FRAMES
		);
		
		// Game map and camera
		GameMap map = new GameMap(gctx);
		Camera camera = new Camera(map, (Player) player);
		
		// Init ECS managers
		entityManager = EntityManager.getInstance();
		systemManager = new SystemManager(this, camera);
				
		// Add entities
		entityManager.addEntity(player.getUID(), player);
		entityManager.addEntity(blacksmith.getUID(), blacksmith); 
	}

	@Override
	public void update()
	{
		systemManager.update();
	}
	
	/*----------------------------------------*/
}
