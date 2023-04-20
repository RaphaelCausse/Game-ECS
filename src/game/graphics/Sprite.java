package game.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Classe qui represente un ensemble de sprites.
 */
public class Sprite
{
	/*----------------------------------------*/
	
    private Image[] sprites;
    private int nbRows;
    private int nbCols;
    private int spriteCount;
    private int spriteWidth;
    private int spriteHeight;

    /*----------------------------------------*/
    
    /**
     * Contructeur de la classe Sprite. Charge tous les sprites d'un spritesheet.
     * @param spritesheet Image contenant tous les sprites
     * @param _spriteWidth Largeur d'un sprite
     * @param _spriteHeight Hauteur d'un sprite
     */
    public Sprite(Image spritesheet, int _spriteWidth, int _spriteHeight)
    {
    	spriteWidth = _spriteWidth;
    	spriteHeight = _spriteHeight;
    	
    	nbRows = (int) spritesheet.getHeight() / spriteHeight;
        nbCols = (int) spritesheet.getWidth() / spriteWidth;
        spriteCount = nbRows * nbCols;

        sprites = new Image[spriteCount];

        PixelReader reader = spritesheet.getPixelReader();

        for (int row = 0; row < nbRows; row++)
        {
            for (int col = 0; col < nbCols; col++)
            {
                int x = col * spriteWidth;
                int y = row * spriteHeight;

                WritableImage spriteImage = new WritableImage(reader, x, y, spriteWidth, spriteHeight);
                sprites[row * nbCols + col] = spriteImage;
            }
        }
    }

    /*----------------------------------------*/
    
    /**
     * Retourner le sprite correspondant a l'index.
     * @param index Index du sprite
     * @return image
     */
    public Image getSpriteImage(int index) { return sprites[index]; }
    
    /**
     * Retourner les sprites, tableau d'images.
     * @return sprites
     */
    public Image[] getSprites() { return sprites; }
}
