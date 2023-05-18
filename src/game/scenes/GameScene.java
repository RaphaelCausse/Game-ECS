package game.scenes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import game.Game;
import game.ecs.component.InteractComponent;
import game.ecs.component.InventoryComponent;
import game.ecs.component.PositionComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.NPC;
import game.ecs.entity.EntityManager;
import game.ecs.entity.Monster;
import game.ecs.entity.Player;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.Key;
import game.ecs.system.SystemManager;
import game.graphics.Camera;
import game.graphics.GameMap;
import game.graphics.HUD;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import utils.Settings.GameStatus;
import utils.Settings.Positions;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Stats;
import utils.Settings.App;

/**
 * Class responsible of gameplay scene.
 * @see AbstractScene
 */
public class GameScene extends AbstractScene
{
	/*----------------------------------------*/
	
	
	public Canvas canvas;
	public GraphicsContext gctx;
	public static Map<KeyCode, Boolean> keyInputs = new HashMap<KeyCode, Boolean>();
	private EntityManager entityManager;
	private SystemManager systemManager;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of GameScene class.
	 * @param _sceneManager Scene manager
	 * @param _root	Root node of scene graph
	 */
	public GameScene(SceneManager _sceneManager, Pane _root)
	{
		super(_sceneManager, _root);
		
		// Init graphics elements
		canvas = new Canvas(App.SCREEN_W, App.SCREEN_H);
		gctx = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		
		// Create game world
		initialize();
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void initialize()
	{
		// Create and add entities to manager
		AbstractEntity player = new Player(
			Positions.PLAYER_SPAWN_X,
			Positions.PLAYER_SPAWN_Y
		);
		entityManager.addEntity(player.getUID(), player);
		
		AbstractEntity npc = new NPC(
			Positions.BLACKSMITH_SPAWN_X,
			Positions.BLACKSMITH_SPAWN_Y
		);
		List<String> dialogs = new ArrayList<>();
		dialogs.add("Hey, it's dangerous around here !\n"
				+ "Can you kill them all for me please ?\n"
				+ "I think they protect something precious...");
		InteractComponent interact = new InteractComponent(dialogs);
		npc.addComponent(interact);
		entityManager.addEntity(npc.getUID(), npc);
		
		AbstractEntity monsterBoss = new Monster(
			"Great Ghost Wizard",
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
			(int)monsterBoss.getComponent(PositionComponent.class).getX(),
			(int)monsterBoss.getComponent(PositionComponent.class).getY()
		);
		monsterBoss.getComponent(InventoryComponent.class).addItem(key);
		entityManager.addEntity(monsterBoss.getUID(), monsterBoss);
		
		AbstractEntity monsterGuardian = new Monster(
			"Guardian of Lost Souls",
			810,
			420,
			ResFiles.GHOST_WIZARD_SPRITESHEET,
			Sprites.MONSTER_GHOST_WIZARD_W,
			Sprites.MONSTER_GHOST_WIZARD_H,
			Sprites.MONSTER_GHOST_WIZARD_ANIM_FRAMES,
			Stats.MONSTER_MAX_HEALTH,
			Stats.MONSTER_BASE_DAMAGE,
			Stats.MONSTER_ATTACK_COOLDOWN
		);
		entityManager.addEntity(monsterGuardian.getUID(), monsterGuardian);
		
		AbstractEntity monsterMagician = new Monster(
			"Dark Ghost Magician",
			Positions.MONSTER_BOSS_SPAWN2_X,
			Positions.MONSTER_BOSS_SPAWN2_Y,
			ResFiles.GHOST_WIZARD_SPRITESHEET,
			Sprites.MONSTER_GHOST_WIZARD_W,
			Sprites.MONSTER_GHOST_WIZARD_H,
			Sprites.MONSTER_GHOST_WIZARD_ANIM_FRAMES,
			Stats.MONSTER_MAX_HEALTH,
			Stats.MONSTER_BASE_DAMAGE,
			Stats.MONSTER_ATTACK_COOLDOWN
		);
		entityManager.addEntity(monsterMagician.getUID(), monsterMagician);
		
		// Create Game map, Camera and HUD
		GameMap map = new GameMap(gctx);
		Camera camera = new Camera(map, (Player)player);
		HUD hud = new HUD(gctx, (Player)player);
		
		// Init ECS managers
		entityManager = EntityManager.getInstance();
		systemManager = new SystemManager(this, camera, hud);
	}
	
	@Override
	public void start()
	{
	}
	
	@Override
	public void update()
	{
		if (Game.gameStatus == GameStatus.GAME_RUNNING)
		{
			systemManager.update();
		}
		else
		{
			Game.sceneManager.activate("EndGameScene");
		}
	}
	
	/*----------------------------------------*/
}
