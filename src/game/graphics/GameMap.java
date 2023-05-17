package game.graphics;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.EntityManager;
import game.ecs.entity.MapObject;
import game.ecs.entity.items.AbstractItem;
import game.ecs.entity.items.DamagePotion;
import game.ecs.entity.items.HealthPotion;
import game.ecs.entity.items.PoisonPotion;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import utils.CSVReader;
import utils.Settings.ResFiles;
import utils.Settings.Sprites;

/**
 * Classe qui represente la map de jeu.
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
	 * Constructeur de la classe GameMap.
	 * @param _gctx Contexte graphique
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
	 * Chargement du tileset de la map.
	 * @param filename Fichier image
	 * @param tileW Largeur d'une tuile
	 * @param tileH Hauteur d'une tuile
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
	 * Creation des objets de la map.
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
	 * Creer et faire apparaitre les items sur la map.
	 */
	public void spawnItemsOnMap()
	{
		// Initialize all items potions on the map
		
		int[] damagePotionsX = {330, 1386};
		int[] damagePotionsY = {580, 384};
		for (int x = 0, y = 0; x < damagePotionsX.length && y < damagePotionsY.length; x++, y++)
		{
			AbstractItem item = new DamagePotion(damagePotionsX[x], damagePotionsY[y]);
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
	}
	
	/**
	 * Affiche en debug une couche de la map.
	 * @param layer Couche de la map
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
	
	public GraphicsContext getGraphicsContext() { return gctx; }
	
	public Image getTile(int index) { return tileset[index]; }
	
	public int getRows() { return layerTexture.length; }
	
	public int getCols() { return layerTexture[0].length; }
	
	public int getTileWidth() { return tileWidth; }

	public int getTileHeight() { return tileHeight; }
	
	public List<MapObject> getMapObjects() { return mapObjects; }
}
