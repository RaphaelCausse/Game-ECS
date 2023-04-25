package utils;

import javafx.util.Duration;

public final class Settings
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
		public static final String PLAYER_IDLE = DirPaths.PLAYER + "player-idle.png";
		public static final String PLAYER_WALK = DirPaths.PLAYER + "player-walk.png";
		public static final String PLAYER_ATTACK = DirPaths.PLAYER + "player-attack.png";
		// Maps csv
		public static final String MAP_TEXTURE = DirPaths.MAPS_DIR + "map_texture.csv";
		public static final String MAP_OBJECTS = DirPaths.MAPS_DIR + "map_objects.csv";
		public static final String MAP_OBJECTS_ABOVE = DirPaths.MAPS_DIR + "map_objects_above.csv";
		// Tilesets
		public static final String MAP_TILESET = DirPaths.TILES + "tileset.png";
	}
	
	/**
	 * Taille des sprites.
	 */
	public static class SpriteSize
	{
		public static final int TILE_SIZE = 32;
		public static final int PLAYER_SIZE = 64;
		public static final int PLAYER_SCALE= 2;
	}
	
	/**
	 * Etats de mouvement des sprites.
	 */
	public static class SpriteState
	{
		public static final int IDLE = 0;
		public static final int WALK = 1;
		public static final int ATTACK = 2;
	}
	
	/**
	 * Constantes de movements.
	 */
	public static class Movement
	{
		// Directions
		public static final int UP = 0;
		public static final int RIGHT = 1;
		public static final int DOWN = 2;
		public static final int LEFT = 3;
		// Movements
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
}
