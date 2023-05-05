package game.graphics;

import java.util.List;

import game.ecs.EntityManager;
import game.ecs.component.ColliderComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.Player;
import javafx.scene.paint.Color;
import utils.Point2D;
import utils.Settings.Sprites;
import utils.Settings.Window;

public class Camera
{
	/*----------------------------------------*/
	
	private GameMap map;
	private Player followed;
	private Point2D followedPosition;
	private Point2D offset;
	private int width;
	private int height;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Camera.
	 * @param _map
	 * @param _followed
	 */
	public Camera(GameMap _map, Player _followed)
	{
		map = _map;
		followed = _followed;
		followedPosition = followed.getComponent(PositionComponent.class).getPos();
		offset = new Point2D(0, 0);
		width = Window.SCREEN_W;
		height = Window.SCREEN_H;
	}
	
	/**
	 * 
	 */
	public void updateOffset()
	{
		// X Offset 
		if (followedPosition.getX() + Sprites.PLAYER_SIZE/2 - width/2 < 0)
		{
			offset.setX(followedPosition.getX() + Sprites.PLAYER_SIZE/2 - width/2);
		}
		else if (followedPosition.getX() + Sprites.PLAYER_SIZE/2 + width/2 > map.getCols() * map.getTileWidth())
		{
			offset.setX((followedPosition.getX() + Sprites.PLAYER_SIZE/2 + width/2) - map.getCols() * map.getTileWidth());
		}
		else
		{
			offset.setX(0);
		}
		// Y Offset
		if (followedPosition.getY() + Sprites.PLAYER_SIZE/2 - height/2 < 0)
		{
			offset.setY(followedPosition.getY() + Sprites.PLAYER_SIZE/2 - height/2);
		}
		else if (followedPosition.getY() + Sprites.PLAYER_SIZE/2 + height/2 > map.getRows() * map.getTileHeight())
		{
			offset.setY((followedPosition.getY() + Sprites.PLAYER_SIZE/2 + height/2) - map.getRows() * map.getTileHeight());
		}
		else 
		{
			offset.setY(0);
		}
	}
	
	/**
	 * 
	 */
	public void render()
	{		
		updateOffset();
		
		// Render map
		renderMapLayer(map.getLayerTexture());
		renderMapLayer(map.getLayerObjects());
		
		// TODO Render map objects
		
		// Render entities and the followed entity
		renderEntities();
		renderFollowed();
		
		// Render elements above all to create a depth illusion
		renderMapLayer(map.getLayerObjectsAbove());
	}
	
	/**
	 * 
	 * @param layer
	 */
	public void renderMapLayer(int[][] layer)
	{
		// Render tiles visible by the camera view
		int startX = (int) Math.max(0, (followedPosition.getX() - width/2 - offset.getX()) / map.getTileWidth());
	    int startY = (int) Math.max(0, (followedPosition.getY() - height/2 - offset.getY()) / map.getTileHeight());
	    int endX = (int) Math.min(map.getCols(), (followedPosition.getX() + width/2 - offset.getX()) / map.getTileWidth() + 2);
	    int endY = (int) Math.min(map.getRows(), (followedPosition.getY() + height/2 - offset.getY()) / map.getTileHeight() + 2);

	    for (int row = startY; row < endY; row++)
	    {
	        for (int col = startX; col < endX; col++)
	        {
				if (layer[row][col] != -1)
				{
					int x = col * map.getTileWidth();
					int y = row * map.getTileHeight();

					map.getGraphicsContext().drawImage(
						map.getTile(layer[row][col]),
						x - followedPosition.getX() + followed.cameraX + offset.getX(), // dst X
						y - followedPosition.getY() + followed.cameraY + offset.getY(), // dst Y
						map.getTileWidth(), // dst W
						map.getTileWidth() // dst H
					);
				}
			}
		}
	}
	
	/**
	 * 
	 */
	public void renderEntities()
	{
		List<AbstractEntity> entities = EntityManager.getEntitiesWithComponent(SpriteComponent.class);
		for (AbstractEntity entity : entities)
		{
			if (entity.getUID() == followed.getUID())
			{
				continue;
			}
			PositionComponent position = entity.getComponent(PositionComponent.class);
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			ColliderComponent collider = entity.getComponent(ColliderComponent.class);
			
			map.getGraphicsContext().drawImage(
				sprite.getSpritesheet(), 
				sprite.getSpriteColIndex() * sprite.getSpriteWidth(), // src X
				sprite.getSpriteRowIndex() * sprite.getSpriteHeight(), // src Y
				sprite.getSpriteWidth(), // src W
				sprite.getSpriteHeight(), // src H
				position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(), // dst X
				position.getY() - followedPosition.getY() + followed.cameraY + offset.getY(), // dst Y
				sprite.getSpriteWidth(), // dst W
				sprite.getSpriteHeight() // dst H
			);
			
			// TMP draw borders and top left corner, collider bounds
//			map.getGraphicsContext().setStroke(Color.RED);
//			map.getGraphicsContext().strokeRect(
//				position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(),
//				position.getY() - followedPosition.getY() + followed.cameraY + offset.getY(),
//				sprite.getSpriteWidth(),
//				sprite.getSpriteHeight()
//			);
			map.getGraphicsContext().setStroke(Color.BLUE);
			map.getGraphicsContext().strokeRect(
				collider.getBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
				collider.getBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
				collider.getBounds().getWidth(),
				collider.getBounds().getHeight()
			);
		}
	}
	
	/**
	 * 
	 */
	public void renderFollowed()
	{
		SpriteComponent sprite = followed.getComponent(SpriteComponent.class);
		ColliderComponent collider = followed.getComponent(ColliderComponent.class);
		
		map.getGraphicsContext().drawImage(
			sprite.getSpritesheet(), 
			sprite.getSpriteColIndex() * sprite.getSpriteWidth(), // src X
			sprite.getSpriteRowIndex() * sprite.getSpriteHeight(), // src Y
			sprite.getSpriteWidth(), // src W
			sprite.getSpriteHeight(), // src H
			followed.cameraX + offset.getX(), // dst X
			followed.cameraY + offset.getY(), // dst Y
			sprite.getSpriteWidth(), // dst W
			sprite.getSpriteHeight() // dst H
		);
		
		// TMP draw borders and top left corner, collider bounds
//		map.getGraphicsContext().setStroke(Color.RED);
//		map.getGraphicsContext().strokeRect(
//			followed.cameraX + offset.getX(),
//			followed.cameraY + offset.getY(),
//			sprite.getSpriteWidth(),
//			sprite.getSpriteHeight()
//		);
		map.getGraphicsContext().setStroke(Color.BLUE);
		map.getGraphicsContext().strokeRect(
			collider.getBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
			collider.getBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
			collider.getBounds().getWidth(),
			collider.getBounds().getHeight()
		);
	}
	
	/*----------------------------------------*/
	
	public GameMap getMap() { return map; }
	
}
