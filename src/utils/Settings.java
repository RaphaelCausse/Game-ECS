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
		public static final int SCREEN_W = 900;
		public static final int SCREEN_H = 600;
		// Framerate settings
		public static final int FPS = 60;
		public static final Duration FRAME_TIME = Duration.millis(1000/FPS);
	}
	
	/**
	 * Chemins des differents repertoires.
	 */
	public static class DirPaths
	{
		public static final String ASSETS = "res/assets/";
		public static final String MAPS = "res/maps/";
		public static final String PLAYER = ASSETS + "player/";
	}
	
	/**
	 * Chemins des fichiers images.
	 */
	public static class ImageFiles
	{
		public static final String PLAYER_IDLE = DirPaths.PLAYER + "player-idle.png";
		public static final String PLAYER_WALK = DirPaths.PLAYER + "player-walk.png";
		public static final String PLAYER_ATTACK = DirPaths.PLAYER + "attack.png";
	}
	
	/**
	 * Taille des sprites.
	 */
	public static class SpriteSize
	{
		public static final int PLAYER_SIZE = 64;
		public static final int PLAYER_SCALE = 2;
	}
	
	/**
	 * Etats de mouvement des sprites.
	 */
	public enum SpriteState
	{
		IDLE, WALK, ATTACK, INERT
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
		public static final int PLAYER_SPEED = 5;
	}
	
	/**
	 * Interactions entre les entities.
	 */
	public static class Interaction
	{
		public static final int ACTIVATE = 64;
		public static final int ATTACK = 65;
	}
}
