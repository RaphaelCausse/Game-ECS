package utils;

import javafx.util.Duration;

/**
 * Class responsible of application and game settings.
 */
public class Settings
{
	/**
	 * Application settings.
	 */
	public static class App
	{
		// Window settings
		public static final String TITLE = "Project-Unknown";
		public static final int SCREEN_W = 1000;
		public static final int SCREEN_H = SCREEN_W/16*9;
		// Framerate settings
		public static final int FPS = 60;
		public static final Duration FRAME_TIME = Duration.millis(1000/FPS);
	}
	
	/**
	 *	Game status.
	 */
	public enum GameStatus
	{
		GAME_RUNNING, GAME_WIN, GAME_OVER
	}

	/**
	 * Directory paths to access resources files.
	 */
	public static class DirPaths
	{
		public static final String MAPS_DIR = "resources/res/maps/";
		public static final String ASSETS_DIR = "res/assets/";
		public static final String TILES = ASSETS_DIR + "tiles/";
		public static final String ENTITIES = ASSETS_DIR + "entities/";
		public static final String HUD = ASSETS_DIR + "hud/";	
		public static final String ITEMS = ASSETS_DIR + "items/";	
	}
	
	/**
	 * Resource file paths.
	 */
	public static class ResFiles
	{
		// Maps csv layers
		public static final String MAP_TEXTURE = DirPaths.MAPS_DIR + "map_texture.csv";
		public static final String MAP_OBJECTS = DirPaths.MAPS_DIR + "map_objects.csv";
		public static final String MAP_OBJECTS_ABOVE = DirPaths.MAPS_DIR + "map_objects_above.csv";
		// Tilesets
		public static final String MAP_TILESET = DirPaths.TILES + "tileset.png";
		// Spritesheets
		public static final String PLAYER_SPRITESHEET = DirPaths.ENTITIES + "player_spritesheet.png";
		public static final String BLACKSMITH_SPRITESHEET = DirPaths.ENTITIES + "blacksmith_spritesheet.png";
		public static final String FLAME_GLEOK_SPRITESHEET = DirPaths.ENTITIES + "flame_gleok_spritesheet.png";
		public static final String GHOST_WIZARD_SPRITESHEET = DirPaths.ENTITIES + "ghost_wizard_spritesheet.png";
		public static final String GLODEN_WYRM_SPRITESHEET = DirPaths.ENTITIES + "golden_wyrm_spritesheet.png";
		// HUD
		public static final String HEALTH_BAR_EMPTY = DirPaths.HUD + "healthbar_empty.png";
		public static final String HEALTH_FILL = DirPaths.HUD + "health_fill.png";
		public static final String INVENTORY = DirPaths.HUD + "inventory.png";
		public static final String CURRENT_ITEM_BORDER = DirPaths.HUD + "current.png";
		public static final String SPACE_KEY = DirPaths.HUD + ".png";
		public static final String LEFT_ARROW_KEY = DirPaths.HUD + ".png";
		public static final String LEFT_RIGHT_KEY = DirPaths.HUD + ".png";
		// Items
		public static final String ITEM_KEY = DirPaths.ITEMS + "key.png";
		public static final String ITEM_DAMAGE_POTION = DirPaths.ITEMS + "damage_potion.png";
		public static final String ITEM_HEALTH_POTION = DirPaths.ITEMS + "health_potion.png";
		public static final String ITEM_POISON_POTION = DirPaths.ITEMS + "poison_potion.png";
		public static final String POISON_DAMAGE_ORBE = DirPaths.ITEMS + "poison_damage_orbe.png";
	}
	
	/**
	 * Sprites settings.
	 */
	public static class Sprites
	{
		// Sizes
		public static final int TILE_SIZE = 32;
		public static final int SPRITE_SIZE = 32;
		public static final int PLAYER_SIZE = 64;
		public static final int MONSTER_SIZE = 32;
		public static final int MONSTER_GHOST_WIZARD_W = 56;
		public static final int MONSTER_GHOST_WIZARD_H = 82;
		public static final int POISON_DAMAGE_ORBE_W = 27;
		public static final int POISON_DAMAGE_ORBE_H = 24;
		// Animations
		public static final int ANIM_FRAMES = 12;
		public static final int MONSTER_ANIM_FRAMES = 8;
		public static final int MONSTER_GHOST_WIZARD_ANIM_FRAMES = 16;
		// HUD
		public static final int HEALTH_BAR_W = 160;
		public static final int HEALTH_BAR_H = 15;
		public static final int HEALTH_FILL_W = 147;
		public static final int HEALTH_FILL_H = 10;
		public static final int INVENTORY_W = 160;
		public static final int INVENTORY_H = 32;
		public static final int CURRENT_ITEM_BORDER_SIZE = 20;
		public static final int FIRST_BORDER_X = 7;
		public static final int FIRST_BORDER_Y = 6;
		public static final int ITEM_SIZE = 16;
		public static final int FIRST_ITEM_X = 9;
		public static final int FIRST_ITEM_Y = 8;
		public static final int ITEM_SPACING = 5;
	}
	
	/**
	 * Movement settings.
	 */
	public static class Movement
	{
		// Directions
		public static final int NB_DIRECTIONS = 4;
		public static final int UP = 0;
		public static final int RIGHT = 1;
		public static final int DOWN = 2;
		public static final int LEFT = 3;
		// Speeds
		public static final int PLAYER_SPEED = 2;
		public static final int MONSTER_SPEED = 1;
	}
	
	/**
	 * Animation states.
	 */
	public static class AnimationState
	{
		public static final int IDLE = 0;
		public static final int WALK = 1;
		public static final int ATTACK = 2;
	}
	
	/**
	 * Actions in the game.
	 */
	public static class Actions
	{
		public static final int ATTACK = 64;
		public static final int INVENTORY_LEFT = 65;
		public static final int INVENTORY_RIGHT = 66;
		public static final int USE_ITEM = 67;
		public static final int DROP_ITEM = 68;
	}
	
	/**
	 * Positions settings.
	 */
	public static class Positions
	{
		// Spawn positions
		public static final int PLAYER_SPAWN_X = 10;
		public static final int PLAYER_SPAWN_Y = 1184;
		public static final int BLACKSMITH_SPAWN_X = 224;
		public static final int BLACKSMITH_SPAWN_Y = 1024;
		public static final int MONSTER_BOSS_SPAWN1_X = 140;
		public static final int MONSTER_BOSS_SPAWN1_Y = 240;
		public static final int MONSTER_BOSS_SPAWN2_X = 1410;
		public static final int MONSTER_BOSS_SPAWN2_Y = 125;
		// HUD positions
		public static final int HEALTH_BAR_X = 4;
		public static final int HEALTH_BAR_Y = 5;
		public static final int HEALTH_FILL_X = HEALTH_BAR_X + (Sprites.HEALTH_BAR_W - Sprites.HEALTH_FILL_W);
		public static final int HEALTH_FILL_Y = HEALTH_BAR_Y + 1;
		public static final int INVENTORY_BAR_X = App.SCREEN_W/2 - Sprites.INVENTORY_W/2;
		public static final int INVENTORY_BAR_Y = App.SCREEN_H - Sprites.INVENTORY_H;
	}
	
	/**
	 * Stats of game entities.
	 */
	public static class Stats
	{
		// Player
		public static final int PLAYER_MAX_HEALTH = 100;
		public static final int PLAYER_BASE_DAMAGE = 20;
		public static final int PLAYER_ATTACK_COOLDOWN = App.FPS;
		// Monster
		public static final int MONSTER_BOSS_MAX_HEALTH = 1200;
		public static final int MONSTER_BOSS_BASE_DAMAGE = 20;
		public static final int MONSTER_BOSS_ATTACK_COOLDOWN = App.FPS*4;
		public static final int MONSTER_MAX_HEALTH = 400;
		public static final int MONSTER_BASE_DAMAGE = 10;
		public static final int MONSTER_ATTACK_COOLDOWN = App.FPS*2;
	}
	
	/**
	 * Map objects ID.
	 */
	public static class MapObjectsID
	{
		public static final int CHEST = 42;
		public static final int CHEST_OPEN_B = 43;
		public static final int CHEST_OPEN_T = 29;
	}
}
