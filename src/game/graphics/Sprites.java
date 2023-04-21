package game.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Classe qui represente un ensemble de sprites.
 */
public class Sprites
{
	/*----------------------------------------*/
	
    private Image[][] sprites;
    public int rows;
    public int cols;
    private int spriteWidth;
    private int spriteHeight;

    /*----------------------------------------*/
    
    /**
     * Contructeur de la classe Sprite. Charge tous les sprites d'un spritesheet.
     * @param spritesheet Image contenant tous les sprites
     * @param _spriteWidth Largeur d'un sprite
     * @param _spriteHeight Hauteur d'un sprite
     */
    public Sprites(Image spritesheet, int _spriteWidth, int _spriteHeight)
    {
    	spriteWidth = _spriteWidth;
    	spriteHeight = _spriteHeight;
    	
    	rows = (int) spritesheet.getHeight() / spriteHeight;
        cols = (int) spritesheet.getWidth() / spriteWidth;

        sprites = new Image[rows][cols];

        PixelReader reader = spritesheet.getPixelReader();

        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                int x = col * spriteWidth;
                int y = row * spriteHeight;

                WritableImage spriteImage = new WritableImage(reader, x, y, spriteWidth, spriteHeight);
                sprites[row][col] = spriteImage;
            }
        }
    }

    /*----------------------------------------*/
    
    /**
     * Retourner le sprite correspondant a l'index.
     * @param direction Direction du sprite, row
     * @param index Index du sprite, col
     * @return image
     */
    public Image getSpriteImage(int direction, int index) { return sprites[direction][index]; }
    
    /**
     * Retourner les sprites, matrice d'images.
     * @return sprites
     */
    public Image[][] getSprites() { return sprites; }
}
