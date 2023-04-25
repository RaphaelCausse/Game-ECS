package game.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import utils.CSVReader;
import utils.Settings.ResFiles;
import utils.Settings.SpriteSize;

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
	
	public GameMap(GraphicsContext _gctx)
	{
		gctx = _gctx;
		mapTexture = CSVReader.readCSV(ResFiles.MAP_TEXTURE);
		mapObjects = CSVReader.readCSV(ResFiles.MAP_OBJECTS);
		mapObjectsAbove = CSVReader.readCSV(ResFiles.MAP_OBJECTS_ABOVE);
		loadTileSet(ResFiles.MAP_TILESET, SpriteSize.TILE_SIZE, SpriteSize.TILE_SIZE);
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
	
	public void renderMapTexture()
	{
		for (int r = 0; r < mapTexture.length; r++)
		{
			for (int c = 0; c < mapTexture[r].length; c++)
			{
				gctx.drawImage(getTile(mapTexture[r][c]), c*tileWidth, r*tileHeight);
			}
		}
	}
	
	public void renderMapObjects()
	{
		for (int r = 0; r < mapObjects.length; r++)
		{
			for (int c = 0; c < mapObjects[r].length; c++)
			{
				if (mapObjects[r][c] != -1)
				{
					gctx.drawImage(getTile(mapObjects[r][c]), c*tileWidth, r*tileHeight);
				}
			}
		}
	}
	
	public void renderMapObjectsAbove()
	{
		for (int r = 0; r < mapObjectsAbove.length; r++)
		{
			for (int c = 0; c < mapObjectsAbove[r].length; c++)
			{
				if (mapObjectsAbove[r][c] != -1)
				{
					gctx.drawImage(getTile(mapObjectsAbove[r][c]), c*tileWidth, r*tileHeight);
				}
			}
		}
	}
	
	/*----------------------------------------*/
	
	public Image getTile(int index) { return tileset[index]; }
}
