package game.graphics;

import java.util.List;

import game.ecs.EntityManager;
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
	
	public Camera(GameMap _map, Player _followed)
	{
		map = _map;
		followed = _followed;
		followedPosition = followed.getComponent(PositionComponent.class).getPos();
		offset = new Point2D(0, 0);
		width = Window.SCREEN_W;
		height = Window.SCREEN_H;
	}
	
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
	
	public void render()
	{		
		updateOffset();
		// Render map
		renderMapLayer(map.getLayerTexture());
		renderMapLayer(map.getLayerObjects());
		
		// Render entities
		renderEntities();
		// Render player
		renderFollowed();
		
		renderMapLayer(map.getLayerObjectsAbove());
	}
	
	public void renderMapLayer(int[][] layer)
	{
		for (int row = 0; row < map.getRows(); row++)
		{
			for (int col = 0; col < map.getCols(); col++)
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
			
			// TMP draw borders and top left corner
			map.getGraphicsContext().setStroke(Color.RED);
			map.getGraphicsContext().strokeRect(
				position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(),
				position.getY() - followedPosition.getY() + followed.cameraY + offset.getY(),
				sprite.getSpriteWidth(),
				sprite.getSpriteHeight()
			);
			map.getGraphicsContext().setFill(Color.YELLOW);
			map.getGraphicsContext().fillRect(
				position.getX() - followedPosition.getX() + followed.cameraX + offset.getX()-1,
				position.getY() - followedPosition.getY() + followed.cameraY + offset.getY()-1,
				2,
				2
			);
		}
	}
	
	public void renderFollowed()
	{
		SpriteComponent sprite = followed.getComponent(SpriteComponent.class);
		
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
		
		// TMP draw borders and top left corner
		map.getGraphicsContext().setStroke(Color.RED);
		map.getGraphicsContext().strokeRect(
			followed.cameraX + offset.getX(),
			followed.cameraY + offset.getY(),
			sprite.getSpriteWidth(),
			sprite.getSpriteHeight()
		);
		map.getGraphicsContext().setFill(Color.YELLOW);
		map.getGraphicsContext().fillRect(
			followed.cameraX + offset.getX()-1,
			followed.cameraY + offset.getY()-1,
			2,
			2
		);
	}
	
	/*----------------------------------------*/
	
	public GameMap getMap() { return map; }
	
}
