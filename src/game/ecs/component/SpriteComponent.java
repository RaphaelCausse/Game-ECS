package game.ecs.component;

import javafx.scene.image.Image;
import utils.Point2D;

/**
 * Class that represents a sprite component.
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
	private double scale;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of SpriteComponent class.
	 * @param filename Spritesheet file name
	 * @param width Sprite width
	 * @param heigth Sprite height
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
		scale = 0.0;
	}
	
	/*----------------------------------------*/
	
	/**
     * Get spritesheet.
     * @return spritesheet.
     */
    public Image getSpritesheet() { return spritesheet; }

    /**
     * Get number of rows in spritesheet.
     * @return rows.
     */
    public int getRows() { return rows; }

    /**
     * Get number of columns in spritesheet.
     * @return cols.
     */
    public int getCols() { return cols; }

    /**
     * Get sprite width.
     * @return width.
     */
    public int getSpriteWidth() { return spriteWidth; }

    /**
     * Get sprite height.
     * @return height.
     */
    public int getSpriteHeight() { return spriteHeight; }

    /**
     * Get sprite row index.
     * @return rowIndex.
     */
    public int getSpriteRowIndex() { return spriteRowIndex; }

    /**
     * Get sprite column index.
     * @return columnIndex.
     */
    public int getSpriteColIndex() { return spriteColIndex; }

    /**
     * Get sprite center point.
     * @return center.
     */
    public Point2D getSpriteCenter() { return new Point2D(spriteWidth/2, spriteHeight/2); }

    /**
     * Get sprite scale.
     * @return scale.
     */
    public double getScale() { return scale; }

    /**
     * Set sprite row index.
     * @param _spriteRowIndex New sprite row index.
     */
    public void setSpriteRowIndex(int _spriteRowIndex) { spriteRowIndex = _spriteRowIndex; }

    /**
     * Set sprite column index.
     * @param _spriteColIndex New sprite column index.
     */
    public void setSpriteColIndex(int _spriteColIndex) { spriteColIndex = _spriteColIndex; }

    /**
     * Set the sprite scale.
     * @param _scale New sprite scale.
     */
    public void setScale(double _scale) { scale = _scale; }
}
