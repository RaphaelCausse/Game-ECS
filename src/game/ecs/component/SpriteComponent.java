package game.ecs.component;

import javafx.scene.image.Image;

/**
 * Classe qui represente un composant de sprite.
 * @see AbstractComponent
 */
public class SpriteComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Image spritesheet;
    public int rows;
    public int cols;
    private int spriteWidth;
    private int spriteHeight;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe SpriteComponent.
	 * @param filename Fichier de l'image spritesheet
	 * @param width Largeur d'un sprite
	 * @param heigth Hauteur d'un sprite
	 */
	public SpriteComponent(String filename, int width, int height)
	{
		super();
		spritesheet = new Image(filename);
    	spriteWidth = width;
    	spriteHeight = height;
    	rows = (int) spritesheet.getHeight() / spriteHeight;
        cols = (int) spritesheet.getWidth() / spriteWidth;
	}
	
	/*----------------------------------------*/
	
	public Image getSpritesheet() { return spritesheet; }
	
	public int getRows() { return rows;}
	
	public int getCols() { return cols;}
    
    public int getSpriteWidth() { return spriteWidth; }
    
    public int getSpriteHeight() { return spriteHeight; }
}
