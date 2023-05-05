package game.ecs.component;

import javafx.scene.image.Image;
import utils.Point2D;

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
	private int spriteRowIndex;
	private int spriteColIndex;
	
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
		spriteRowIndex = 0;
		spriteColIndex = 0;
		setFlag(FlagECS.TO_UPDATE);
	}
	
	/*----------------------------------------*/
	
	public Image getSpritesheet() { return spritesheet; }
	
	public int getRows() { return rows;}
	
	public int getCols() { return cols;}
    
    public int getSpriteWidth() { return spriteWidth; }
    
    public int getSpriteHeight() { return spriteHeight; }
    
	public int getSpriteRowIndex() { return spriteRowIndex; }
	
	public int getSpriteColIndex() { return spriteColIndex; }
	
	public Point2D getSpriteCenter() { return new Point2D(spriteWidth/2, spriteHeight/2); }
	
	public void setSpriteRowIndex(int _spriteRowIndex) { spriteRowIndex = _spriteRowIndex; }
	
	public void setSpriteColIndex(int _spriteColIndex) { spriteColIndex = _spriteColIndex; }
}
