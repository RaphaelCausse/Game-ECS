package game.graphics;

import java.util.ArrayList;
import java.util.List;

import game.ecs.entity.MapObject;
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
	private int[][] layerTexture;
	private int[][] layerObjects;
	private int[][] layerObjectsAbove;
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
	
	/*----------------------------------------*/
	
	public GraphicsContext getGraphicsContext() { return gctx; }
	
	public Image getTile(int index) { return tileset[index]; }
	
	public int[][] getLayerTexture() { return layerTexture; }
	
	public int getRows() { return layerTexture.length; }
	
	public int getCols() { return layerTexture[0].length; }
	
	public int[][] getLayerObjects() { return layerObjects; }
	
	public int[][] getLayerObjectsAbove() { return layerObjectsAbove; }
	
	public int getTileWidth() { return tileWidth; }

	public int getTileHeight() { return tileHeight; }
	
	public List<MapObject> getMapObjects() { return mapObjects; }
}
