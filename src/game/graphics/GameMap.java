package game.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import utils.CSVReader;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;
import utils.Settings.Window;

/**
 *
 */
public class GameMap
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	private int[][] mapTexture;
	private int[][] mapObjects;
	private int[][] mapObjectsAbove;
	private Image[] tileset;
	private int tileWidth;
	private int tileHeight;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe GameMap.
	 * @param _gctx
	 */
	public GameMap(GraphicsContext _gctx)
	{
		gctx = _gctx;
		mapTexture = CSVReader.readCSV(ResFiles.MAP_TEXTURE);
		mapObjects = CSVReader.readCSV(ResFiles.MAP_OBJECTS);
		mapObjectsAbove = CSVReader.readCSV(ResFiles.MAP_OBJECTS_ABOVE);
		loadTileSet(ResFiles.MAP_TILESET, Sprites.TILE_SIZE, Sprites.TILE_SIZE);
	}
	
	public void loadTileSet(String filename, int tileW, int tileH)
	{	
		tileWidth = tileW;
		tileHeight = tileH;
		
		Image tilesheet = new Image(filename);
		int rows = (int) tilesheet.getHeight()/tileH;
		int cols = (int) tilesheet.getWidth()/tileW;
		
		tileset = new Image[rows*cols];
		
		PixelReader reader = tilesheet.getPixelReader();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = col * tileW;
                int y = row * tileH;

                WritableImage tile = new WritableImage(reader, x, y, tileW, tileH);
                tileset[row * cols + col] = tile;
            }
        }
	}
	
	public void renderMapLayer(int[][] layer)
	{
		for (int r = 0; r < layer.length; r++)
		{
			for (int c = 0; c < layer[r].length; c++)
			{
				if (layer[r][c] != -1)
				{
					gctx.drawImage(
							getTile(layer[r][c]),
							c*tileWidth*Window.CAMERA_SCALE, // dst X
							r*tileHeight*Window.CAMERA_SCALE, // dst Y
							tileWidth*Window.CAMERA_SCALE, // dst W
							tileHeight*Window.CAMERA_SCALE // dst H
					);
				}
			}
		}
	}
	
	/*----------------------------------------*/
	
	public Image getTile(int index) { return tileset[index]; }
	
	public int[][] getLayerTexture() { return mapTexture; }
	
	public int[][] getLayerObjects() { return mapObjects; }
	
	public int[][] getLayerObjectsAbove() { return mapObjectsAbove; }
}
