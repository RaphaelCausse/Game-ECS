package game.graphics;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.EntityManager;
import game.ecs.entity.MapObject;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.AttackPotion;
import game.ecs.entity.items.HealthPotion;
import game.ecs.entity.items.InsensitiveRing;
import game.ecs.entity.items.PoisonPotion;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import utils.CSVReader;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Class that represent Game map.
 */
public class GameMap
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	private Image[] tileset;
	public int[][] layerTexture;
	public int[][] layerObjects;
	public int[][] layerObjectsAbove;
	private int tileWidth;
	private int tileHeight;
	private List<MapObject> mapObjects;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of GameMap class.
	 * @param _gctx Graphic context
	 */
	public GameMap(GraphicsContext _gctx)
	{
		gctx = _gctx;
		loadTileset(ResFiles.MAP_TILESET, Sprites.TILE_SIZE, Sprites.TILE_SIZE);
		layerTexture = CSVReader.readCSV(ResFiles.MAP_TEXTURE);
		layerObjects = CSVReader.readCSV(ResFiles.MAP_OBJECTS);
		layerObjectsAbove = CSVReader.readCSV(ResFiles.MAP_OBJECTS_ABOVE);
		createMapObjects();
		spawnItemsOnMap();
	}
	
	/**
	 * Load map tileset.
	 * @param filename Tileset file name
	 * @param tileW Tile width
	 * @param tileH TIle heigth
	 */
	public void loadTileset(String filename, int tileW, int tileH)
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
	
	/**
	 * Create map objects.
	 */
	public void createMapObjects()
	{
		mapObjects = new ArrayList<MapObject>();
		for (int row = 0; row < getRows(); row++)
		{
			for (int col = 0; col < getCols(); col++)
			{
				if (layerObjects[row][col] == -1)
				{
					continue;
				}
				MapObject newMapObject = new MapObject(col * getTileWidth(), row * getTileHeight(), layerObjects[row][col]);
				mapObjects.add(newMapObject);
			}
		}
	}
	
	/**
	 * Create items on map.
	 */
	public void spawnItemsOnMap()
	{
		// Initialize all items potions on the map
		
		int[] damagePotionsX = {330, 1386};
		int[] damagePotionsY = {580, 384};
		for (int x = 0, y = 0; x < damagePotionsX.length && y < damagePotionsY.length; x++, y++)
		{
			AbstractItem item = new AttackPotion(damagePotionsX[x], damagePotionsY[y]);
			EntityManager.addEntity(item.getUID(), item);
		}
		
		int[] healthPotionsX = {0,   200, 333,  610, 770,  1270, 1000, 512, 1570};
		int[] healthPotionsY = {385, 980, 1165, 800, 1040, 815,  0,    256, 50};
		for (int x = 0, y = 0; x < healthPotionsX.length && y < healthPotionsY.length; x++, y++)
		{
			AbstractItem item = new HealthPotion(healthPotionsX[x], healthPotionsY[y]);
			EntityManager.addEntity(item.getUID(), item);
		}
		
		int[] poisonPotionsX = {400};
		int[] poisonPotionsY = {1000};
		for (int x = 0, y = 0; x < poisonPotionsX.length && y < poisonPotionsY.length; x++, y++)
		{
			AbstractItem item = new PoisonPotion(poisonPotionsX[x], poisonPotionsY[y]);
			EntityManager.addEntity(item.getUID(), item);
		}
		
		int[] insensitiveRingX = {690};
		int[] insensitiveRingY = {1111};
		for (int x = 0, y = 0; x < insensitiveRingX.length && y < insensitiveRingY.length; x++, y++)
		{
			AbstractItem item = new InsensitiveRing(insensitiveRingX[x], insensitiveRingY[y]);
			EntityManager.addEntity(item.getUID(), item);
		}
	}
	
	/**
	 * Display in console a map layer, for debug usage.
	 * @param layer Game map layer
	 */
	public void logMapLayer(int[][] layer)
	{
		for (int row = 0; row < layer.length; row++)
		{
			for (int col = 0; col < layer[0].length; col++)
			{
				System.out.print(layer[row][col] + ", ");
			}
			System.out.print("\n");
		}
		System.out.println("-----------------------------------");
	}
	
	/*----------------------------------------*/
	
	/**
	 * Get graphic context.
	 * @return gctx
	 */
	public GraphicsContext getGraphicsContext() { return gctx; }
	
	/**
	 * Get tile in the tileset by its index.
	 * @param index Index of tile in tileset
	 * @return tile
	 */
	public Image getTile(int index) { return tileset[index]; }
	
	/**
	 * Get number of rows in game map.
	 * @return rows
	 */
	public int getRows() { return layerTexture.length; }
	
	/**
	 * Get number of columns in game map.
	 * @return cols
	 */
	public int getCols() { return layerTexture[0].length; }
	
	/**
	 * Get tile width.
	 * @return tileWidth
	 */
	public int getTileWidth() { return tileWidth; }

	/**
	 * Get tile height.
	 * @return tileHeight
	 */
	public int getTileHeight() { return tileHeight; }
	
	/**
	 * Get list of map objects.
	 * @return mapObjects
	 */
	public List<MapObject> getMapObjects() { return mapObjects; }
}
