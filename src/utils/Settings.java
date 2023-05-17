package utils;

import javafx.util.Duration;

public class Settings
{
	/**
	 * Parametres de l'application.
	 */
	public static class Window
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
	 * Chemins des differents repertoires.
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
	 * Chemins des fichiers ressources.
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
		public static final String MONSTER_SPRITESHEET = DirPaths.ENTITIES + "monster_spritesheet.png";
		public static final String MONSTER_BOSS_SPRITESHEET = DirPaths.ENTITIES + "monster_boss_spritesheet.png";
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
		public static final String ITEM_HEALTH_POTION = DirPaths.ITEMS + "health_potion.png";
		public static final String ITEM_FLYING_RING = DirPaths.ITEMS + "flying_ring.png";
	}
	
	/**
	 * Parametres des sprites.
	 */
	public static class Sprites
	{
		// Sizes
		public static final int TILE_SIZE = 32;
		public static final int SPRITE_SIZE = 32;
		public static final int PLAYER_SIZE = 64;
		public static final int MONSTER_SIZE = 32;
		// Animations
		public static final int ANIM_FRAMES = 12;
		public static final int MONSTER_BOSS_ANIM_FRAMES = 8;
		// HUD
		public static final int HEALTH_BAR_W = 108;
		public static final int HEALTH_BAR_H = 10;
		public static final int HEALTH_FILL_W = 100;
		public static final int HEALTH_FILL_H = 8;
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
	 * Parametres des movements.
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
		public static final int MONSTER_SPEED = 2;
		public static final int MONSTER_BOSS_SPEED = 1;
	}
	
	/**
	 * Etats d'animation.
	 */
	public static class AnimationState
	{
		public static final int IDLE = 0;
		public static final int WALK = 1;
		public static final int ATTACK = 2;
	}
	
	/**
	 * Actions et interactions avec les entites, le HUD.
	 */
	public static class Actions
	{
		public static final int ATTACK = 64;
		public static final int INTERACT = 65;
		public static final int INVENTORY_LEFT = 66;
		public static final int INVENTORY_RIGHT = 67;
		public static final int USE_ITEM = 68;
		public static final int DROP_ITEM = 69;
	}
	
	/**
	 * Positions par defaut des entites.
	 */
	public static class Positions
	{
		// Spawn positions
		public static final int PLAYER_SPAWN_X = 10;
		public static final int PLAYER_SPAWN_Y = 1184;
		public static final int BLACKSMITH_SPAWN_X = 224;
		public static final int BLACKSMITH_SPAWN_Y = 1024;
		public static final int MONSTER_BOSS_SPAWN_X = 140;
		public static final int MONSTER_BOSS_SPAWN_Y = 240;
		// HUD positions
		public static final int HEALTH_BAR_X = 4;
		public static final int HEALTH_BAR_Y = 5;
		public static final int HEALTH_FILL_X = HEALTH_BAR_X + (Sprites.HEALTH_BAR_W - Sprites.HEALTH_FILL_W);
		public static final int HEALTH_FILL_Y = HEALTH_BAR_Y;
		public static final int INVENTORY_BAR_X = Window.SCREEN_W/2 - Sprites.INVENTORY_W/2;
		public static final int INVENTORY_BAR_Y = Window.SCREEN_H - Sprites.INVENTORY_H;
	}
	
	/**
	 * Statistiques des entites.
	 */
	public static class Stats
	{
		// Player
		public static final int PLAYER_MAX_HEALTH = 100;
		public static final int PLAYER_BASE_DAMAGE = 20;
		// Monster
		public static final int MONSTER_BOSS_MAX_HEALTH = 400;
		public static final int MONSTER_BOSS_BASE_DAMAGE = 30;
		public static final int MONSTER_MAX_HEALTH = 150;
		public static final int MONSTER_BASE_DAMAGE = 10;
	}
	
	/**
	 * Identifiants des objets de la map.
	 */
	public static class MapObjectsID
	{
		public static final int CHEST = 42;
		public static final int CHEST_OPEN_B = 43;
		public static final int CHEST_OPEN_T = 29;
	}
}
