package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.component.AnimationComponent;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import game.graphics.GameMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Point2D;
import utils.Settings.Sprites;
import utils.Settings.Window;

/**
 * Classe responsable du system de rendu de la map, des entites.
 * @see AbstractSystem
 */
public class RenderSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	private GameMap map;
	
	/*----------------------------------------*/
	
	public RenderSystem(GraphicsContext _gctx)
	{
		super();
		gctx = _gctx;
		map = new GameMap(_gctx);
	}

	@Override
	public void run()
	{
		// Reset graphics context
		gctx.clearRect(0, 0, gctx.getCanvas().getWidth(), gctx.getCanvas().getHeight());
		
		// Render map texture
		map.renderMapLayer(map.getLayerTexture());
		// Render map objects
		map.renderMapLayer(map.getLayerObjects());
		
		// Render entities
		entities = EntityManager.getEntitiesWithComponent(SpriteComponent.class);
		for (AbstractEntity entity : entities)
		{
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			Point2D pos = movement.getPos();
			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
			AnimationComponent animation = entity.getComponent(AnimationComponent.class);
			
			Image spriteImage = sprite.getSpritesheet();
			gctx.drawImage(
					spriteImage, 
					animation.getFrameIndex()*sprite.getSpriteWidth(), // src X
					(movement.getNbDirection()*movement.getState() + movement.getDirection())*sprite.getSpriteHeight(), // src Y
					Sprites.PLAYER_SIZE, // src W
					Sprites.PLAYER_SIZE, // src H
					pos.getX()*Window.CAMERA_SCALE, // dst X
					pos.getY()*Window.CAMERA_SCALE, // dst Y
					Sprites.PLAYER_SIZE*Window.CAMERA_SCALE, // dst H
					Sprites.PLAYER_SIZE*Window.CAMERA_SCALE // dst W
			);
//			
//
//			// Reset component flag
//			sprites.setFlag(FlagECS.STABLE);
		}
		
		// Render map objects above
		map.renderMapLayer(map.getLayerObjectsAbove());
	}
}
