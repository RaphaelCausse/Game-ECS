package game.graphics;

import java.util.List;

import game.ecs.component.AttackComponent;
import game.ecs.component.ColliderComponent;
import game.ecs.component.DetectionComponent;
import game.ecs.component.HealthComponent;
import game.ecs.component.PositionComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.ecs.entity.EntityManager;
import game.ecs.entity.MapObject;
import game.ecs.entity.Monster;
import game.ecs.entity.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.Point2D;
import utils.Settings.Sprites;
import utils.Settings.Window;

/**
 * Classe qui represente une camera permettant l'affichage du jeu a travers sa vue.
 */
public class Camera
{
	/*----------------------------------------*/
	
	private GameMap map;
	private GraphicsContext gctx;
	private Player followed;
	private Point2D followedPosition;
	private Point2D origin;
	private Point2D end;
	private Point2D offset;
	private int width;
	private int height;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe Camera.
	 * @param _map Map du jeu
	 * @param _followed Entite suivie par la camera
	 */
	public Camera(GameMap _map, Player _followed)
	{
		map = _map;
		gctx = map.getGraphicsContext();
		followed = _followed;
		followedPosition = followed.getComponent(PositionComponent.class).getPos();
		origin = new Point2D(0, 0);
		end = new Point2D(0, 0);
		offset = new Point2D(0, 0);
		width = Window.SCREEN_W;
		height = Window.SCREEN_H;
	}
	
	/**
	 * Mettre a jour les limites de la vue de camera.
	 */
	public void updateCameraView()
	{
		origin.setX(Math.max(0, (followedPosition.getX() - width/2 - offset.getX()) / map.getTileWidth()));
	    origin.setY(Math.max(0, (followedPosition.getY() - height/2 - offset.getY()) / map.getTileHeight()));
	    end.setX(Math.min(map.getCols(), (followedPosition.getX() + width/2 - offset.getX()) / map.getTileWidth() + 2));
	    end.setY(Math.min(map.getRows(), (followedPosition.getY() + height/2 - offset.getY()) / map.getTileHeight() + 2));
	}
	
	/**
	 * Mettre a jour l'offset de la camera.
	 */
	public void updateCameraOffset()
	{
		// X Offset according to map position
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
		// Y Offset according to map position
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
	 * Afficher les elements graphiques visible par la camera.
	 */
	public void render()
	{		
		// Update camera view and offset
		updateCameraView();
		updateCameraOffset();
		
		// Render map
		renderMapLayer(map.layerTexture);
		
		// Render map objects, entities and the followed entity
		renderMapObjects();
		renderEntities();
		renderFollowed();
		
		// Render elements above all to create a depth illusion
		renderMapLayer(map.layerObjectsAbove);
	}
	
	/**
	 * Afficher une couche de la map visible par la camera.
	 * @param layer Couche de la map
	 */
	public void renderMapLayer(int[][] layer)
	{
	    // Render tiles visible by the camera
	    for (int row = (int)origin.getY(); row < (int)end.getY(); row++)
	    {
	        for (int col = (int)origin.getX(); col < (int)end.getX(); col++)
	        {
				if (layer[row][col] != -1)
				{
					int x = col * map.getTileWidth();
					int y = row * map.getTileHeight();

					gctx.drawImage(
						map.getTile(layer[row][col]), 	// image
						x - followedPosition.getX() + followed.cameraX + offset.getX(), // dst X
						y - followedPosition.getY() + followed.cameraY + offset.getY(), // dst Y
						map.getTileWidth(), 	// dst W
						map.getTileWidth() 		// dst H
					);
					// TMP draw borders
//					map.getGraphicsContext().setStroke(Color.GREEN);
//					map.getGraphicsContext().strokeRect(
//						x - followedPosition.getX() + followed.cameraX + offset.getX(),
//						y - followedPosition.getY() + followed.cameraY + offset.getY(),
//						map.getTileWidth(),
//						map.getTileWidth()
//					);
				}
			}
		}
	}
	
	/**
	 * Afficher les objet de la map visibles par la camera.
	 */
	public void renderMapObjects()
	{
		// Render map objects visible by camera view
		List<MapObject> mapObjects = map.getMapObjects();
		for (MapObject object : mapObjects)
		{
			PositionComponent position = object.getComponent(PositionComponent.class);
			
			// Check if object is inside camera view
			if (position.getX() < origin.getX() * map.getTileWidth() ||
				position.getX() > end.getX() * map.getTileWidth() ||
				position.getY() < origin.getY() * map.getTileHeight() ||
				position.getY() > end.getY() * map.getTileHeight())
			{
				continue;
			}
			
			// Render objects
			gctx.drawImage(
				map.getTile(object.getImageIndex()), 	// image
				position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(), // dst X
				position.getY() - followedPosition.getY() + followed.cameraY + offset.getY(), // dst Y
				map.getTileWidth(), 	// dst W
				map.getTileWidth() 		// dst H
			);
			// TMP draw collider bounds
//			if (object.hasComponent(ColliderComponent.class))
//			{
//				ColliderComponent collider = object.getComponent(ColliderComponent.class);
//				map.getGraphicsContext().setStroke(Color.BLUE);
//				map.getGraphicsContext().strokeRect(
//					collider.getBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
//					collider.getBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
//					collider.getBounds().getWidth(),
//					collider.getBounds().getHeight()
//				);
//			}
		}
	}
	
	/**
	 * Afficher les entites visibles par la camera.
	 */
	public void renderEntities()
	{
		// Render entities visible by the camera view.
		List<AbstractEntity> entities = EntityManager.getEntitiesWithComponent(SpriteComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Pass rendering of followed entity as it needs to be centered on the map
			if (entity.getUID() == followed.getUID())
			{
				continue;
			}
			
			// Get required components to render entity
			PositionComponent position = entity.getComponent(PositionComponent.class);
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			
			if (sprite != null)
			{
				// Check if entity is inside camera view
				if (position.getX() + sprite.getSpriteWidth() < origin.getX() * map.getTileWidth() ||
					position.getX() > end.getX() * map.getTileWidth() ||
					position.getY() + sprite.getSpriteHeight() < origin.getY() * map.getTileHeight() ||
					position.getY() > end.getY() * map.getTileHeight())
				{
					continue;
				}
				
				// TMP draw borders
//				map.getGraphicsContext().setStroke(Color.RED);
//				map.getGraphicsContext().strokeRect(
//					position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(),
//					position.getY() - followedPosition.getY() + followed.cameraY + offset.getY(),
//					sprite.getSpriteWidth(),
//					sprite.getSpriteHeight()
//				);
				// TMP draw collider bounds and detection bounds
				if (entity.hasComponent(ColliderComponent.class))
				{
					ColliderComponent collider = entity.getComponent(ColliderComponent.class);
					gctx.setStroke(Color.BLUE);
					gctx.strokeRect(
						collider.getBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
						collider.getBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
						collider.getBounds().getWidth(),
						collider.getBounds().getHeight()
					);
				}
				if (entity.hasComponent(DetectionComponent.class))
				{
					DetectionComponent detection = entity.getComponent(DetectionComponent.class);
					gctx.setStroke(Color.YELLOW);
					gctx.strokeRect(
						detection.getDetectionBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
						detection.getDetectionBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
						detection.getDetectionBounds().getWidth(),
						detection.getDetectionBounds().getHeight()
					);
				}
				if (entity.hasComponent(AttackComponent.class))
				{
					AttackComponent attack = entity.getComponent(AttackComponent.class );
					if (attack.getAttackHitbox() != null)
					{
						gctx.drawImage(
							attack.monsterDamageSprite,
							attack.getAttackHitbox().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
							attack.getAttackHitbox().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
							attack.getAttackHitbox().getWidth(),
							attack.getAttackHitbox().getHeight()
						);
						gctx.setStroke(Color.PURPLE);
						gctx.strokeRect(
							attack.getAttackHitbox().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
							attack.getAttackHitbox().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
							attack.getAttackHitbox().getWidth(),
							attack.getAttackHitbox().getHeight()
						);
					}
				}
				
				// Render entity
				gctx.drawImage(
					sprite.getSpritesheet(), 	// image
					sprite.getSpriteColIndex() * sprite.getSpriteWidth(), 	// src X
					sprite.getSpriteRowIndex() * sprite.getSpriteHeight(), 	// src Y
					sprite.getSpriteWidth(), 	// src W
					sprite.getSpriteHeight(),	// src H
					position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(), 	// dst X
					position.getY() - followedPosition.getY() + followed.cameraY + offset.getY(), 	// dst Y
					(sprite.getScale() == 0) ? sprite.getSpriteWidth() : sprite.getSpriteWidth() * sprite.getScale(),	// dst W
					(sprite.getScale() == 0) ? sprite.getSpriteHeight() : sprite.getSpriteHeight() * sprite.getScale()	// dst H
				);
				
				// Render monsters healthbar and name
				if (entity instanceof Monster && entity.hasComponent(HealthComponent.class))
				{
					Monster monster = (Monster) entity;
					HealthComponent health = monster.getComponent(HealthComponent.class);
					double pourcentage = ((double) health.getCurrentHealth() / (double) health.getMaxHealth());
					gctx.setFill(Color.rgb(0, 0, 0, 0.3));
					gctx.fillRect(
						position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(),
						position.getY() - followedPosition.getY() + followed.cameraY + offset.getY() - 10,
						sprite.getSpriteWidth(),
						8
					);
					gctx.setFill(Color.rgb(255, 0, 0, 0.7));
					gctx.fillRect(
						position.getX() - followedPosition.getX() + followed.cameraX + offset.getX(),
						position.getY() - followedPosition.getY() + followed.cameraY + offset.getY() - 10,
						Math.floor(sprite.getSpriteWidth() * pourcentage),
						8
					);
					gctx.setFont(new Font("Arial", 11));
					gctx.setFill(Color.WHITE);
			        String monsterName = monster.getName();
			        Text text = new Text(monsterName);
			        gctx.fillText(
			        	monsterName,
			        	position.getX() - followedPosition.getX() + followed.cameraX + offset.getX() + sprite.getSpriteWidth()/2 - text.getLayoutBounds().getCenterX(),
			        	position.getY() - followedPosition.getY() + followed.cameraY + offset.getY() - 18
			        );
				}
			}
		}
	}
	
	/**
	 * Afficher l'entite suivie par la camera.
	 */
	public void renderFollowed()
	{
		SpriteComponent sprite = followed.getComponent(SpriteComponent.class);
		// Render entity
		gctx.drawImage(
			sprite.getSpritesheet(), 	// image
			sprite.getSpriteColIndex() * sprite.getSpriteWidth(), 	// src X
			sprite.getSpriteRowIndex() * sprite.getSpriteHeight(), 	// src Y
			sprite.getSpriteWidth(), 	// src W
			sprite.getSpriteHeight(), 	// src H
			followed.cameraX + offset.getX(), 	// dst X
			followed.cameraY + offset.getY(), 	// dst Y
			sprite.getSpriteWidth(), 	// dst W
			sprite.getSpriteHeight() 	// dst H
		);
			
		// TMP draw borders
//		map.getGraphicsContext().setStroke(Color.RED);
//		map.getGraphicsContext().strokeRect(
//			followed.cameraX + offset.getX(),
//			followed.cameraY + offset.getY(),
//			sprite.getSpriteWidth(),
//			sprite.getSpriteHeight()
//		);
		// TMP draw collider bounds
		if (followed.hasComponent(ColliderComponent.class))
		{
			ColliderComponent collider = followed.getComponent(ColliderComponent.class);
			gctx.setStroke(Color.BLUE);
			gctx.strokeRect(
				collider.getBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
				collider.getBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
				collider.getBounds().getWidth(),
				collider.getBounds().getHeight()
			);
		}
		// TMP draw detection range
		if (followed.hasComponent(DetectionComponent.class))
		{
			DetectionComponent detection = followed.getComponent(DetectionComponent.class);
			gctx.setStroke(Color.YELLOW);
			gctx.strokeRect(
				detection.getDetectionBounds().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
				detection.getDetectionBounds().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
				detection.getDetectionBounds().getWidth(),
				detection.getDetectionBounds().getHeight()
			);
		}
		if (followed.hasComponent(AttackComponent.class))
		{
			AttackComponent attack = followed.getComponent(AttackComponent.class);
			gctx.setStroke(Color.PURPLE);
			gctx.strokeRect(
				attack.getAttackHitbox().getMinX() - followedPosition.getX() + followed.cameraX + offset.getX(),
				attack.getAttackHitbox().getMinY() - followedPosition.getY() + followed.cameraY + offset.getY(),
				attack.getAttackHitbox().getWidth(),
				attack.getAttackHitbox().getHeight()
			);
		}
	}
	
	/*----------------------------------------*/
	
	public GameMap getMap() { return map; }
}
