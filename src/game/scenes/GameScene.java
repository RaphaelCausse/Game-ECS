package game.scenes;

import java.util.HashMap;
import java.util.Map;

import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.Blacksmith;
import game.ecs.entity.EntityManager;
import game.ecs.entity.Monster;
import game.ecs.entity.Player;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.Key;
import game.ecs.system.SystemManager;
import game.graphics.Camera;
import game.graphics.GameMap;
import game.graphics.HUD;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import utils.Settings.Positions;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Stats;
import utils.Settings.Window;

/**
 * Classe responsable de la scene de jeu, geree par le gestionnaire de scene.
 * @see AbstractScene
 */
public class GameScene extends AbstractScene
{
	/*----------------------------------------*/
	
	private Timeline gameloop;
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
		
		// Init gameloop
		gameloop = new Timeline(new KeyFrame(Window.FRAME_TIME, event -> {
			update();
		}));
		gameloop.setCycleCount(Animation.INDEFINITE);
		
		// Create game world
		initialize();
	}
	
	@Override
	public void start()
	{
		gameloop.play();
	}
	
	@Override
	public void update()
	{
		systemManager.update();
	}
	
	@Override
	public void stop()
	{
		
	}
	
	/**
	 * Initialisation de la scene de jeu.
	 */
	@SuppressWarnings("static-access")
	public void initialize()
	{
		// Create entites
		AbstractEntity player = new Player(
			Positions.PLAYER_SPAWN_X,
			Positions.PLAYER_SPAWN_Y
		);
		AbstractEntity blacksmith = new Blacksmith(
			Positions.BLACKSMITH_SPAWN_X,
			Positions.BLACKSMITH_SPAWN_Y
		);
		AbstractEntity boss = new Monster(
			"Ghost Wizard",
			Positions.MONSTER_BOSS_SPAWN1_X,
			Positions.MONSTER_BOSS_SPAWN1_Y,
			ResFiles.GHOST_WIZARD_SPRITESHEET,
			Sprites.MONSTER_GHOST_WIZARD_W,
			Sprites.MONSTER_GHOST_WIZARD_H,
			Sprites.MONSTER_GHOST_WIZARD_ANIM_FRAMES,
			Stats.MONSTER_BOSS_MAX_HEALTH,
			Stats.MONSTER_BOSS_BASE_DAMAGE,
			Stats.MONSTER_BOSS_ATTACK_COOLDOWN
		);
		AbstractItem key = new Key(
			(int)boss.getComponent(PositionComponent.class).getX(),
			(int)boss.getComponent(PositionComponent.class).getY()
		);
		boss.getComponent(InventoryComponent.class).addItem(key);
		
		// Create Game map, Camera and HUD
		GameMap map = new GameMap(gctx);
		Camera camera = new Camera(map, (Player)player);
		HUD hud = new HUD(gctx, (Player)player);
		
		// Init ECS managers
		entityManager = EntityManager.getInstance();
		systemManager = new SystemManager(this, camera, hud);
				
		// Add entities
		entityManager.addEntity(player.getUID(), player);
		entityManager.addEntity(blacksmith.getUID(), blacksmith);
		entityManager.addEntity(boss.getUID(), boss);
	}
	
	/*----------------------------------------*/
}
