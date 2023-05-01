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
		public static final int SCREEN_W = 800;
		public static final int SCREEN_H = SCREEN_W/4*3;
		// Framerate settings
		public static final int FPS = 60;
		public static final Duration FRAME_TIME = Duration.millis(1000/FPS);
		// Camera
		public static final int CAMERA_SCALE = 2;
	}
	
	/**
	 * Chemins des differents repertoires.
	 */
	public static class DirPaths
	{
		public static final String MAPS_DIR = "resources/res/maps/";
		public static final String ASSETS_DIR = "res/assets/";
		public static final String PLAYER = ASSETS_DIR + "player/";
		public static final String NPC = ASSETS_DIR + "npc/";
		public static final String TILES = ASSETS_DIR + "tiles/";
	}
	
	/**
	 * Chemins des fichiers ressources.
	 */
	public static class ResFiles
	{
		// Spritesheets
		public static final String PLAYER_SPRITESHEET = DirPaths.PLAYER + "player_spritesheet.png";
		public static final String BLACKSMITH_SPRITESHEET = DirPaths.NPC + "blacksmith_spritesheet.png";
		// Maps csv
		public static final String MAP_TEXTURE = DirPaths.MAPS_DIR + "map_texture.csv";
		public static final String MAP_OBJECTS = DirPaths.MAPS_DIR + "map_objects.csv";
		public static final String MAP_OBJECTS_ABOVE = DirPaths.MAPS_DIR + "map_objects_above.csv";
		// Tilesets
		public static final String MAP_TILESET = DirPaths.TILES + "tileset.png";
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
		// Animations
		public static final int ANIM_FRAMES = 12;
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
		// States
		public static final int IDLE = 0;
		public static final int WALK = 1;
		public static final int ATTACK = 2;
		// Speeds
		public static final int PLAYER_SPEED = 3;
	}
	
	/**
	 * Interactions entre les entities.
	 */
	public static class Actions
	{
		public static final int ACTIVATE = 64;
		public static final int ATTACK = 65;
	}
	
	/**
	 * Positions de spawn des entites.
	 */
	public static class Positions
	{
		// Spawn positions
		public static final int PLAYER_SPAWN_X = 10;
		public static final int PLAYER_SPAWN_Y = 1216;
		public static final int BLACKSMITH_SPAWN_X = 224;
		public static final int BLACKSMITH_SPAWN_Y = 1024;
	}
}
