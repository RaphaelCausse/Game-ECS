package game.ecs.system;

import game.graphics.Camera;
import game.graphics.GameMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Settings.Window;

/**
 * Classe responsable du system de rendu de la map, des entites.
 * @see AbstractSystem
 */
public class RenderSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private Camera camera;
	private GameMap map;
	private GraphicsContext gctx;
	
	/*----------------------------------------*/
	
	public RenderSystem(Camera _camera)
	{
		super();
		camera = _camera;
		map = camera.getMap();
		gctx = map.getGraphicsContext();
	}

	@Override
	public void run()
	{
		// Reset graphics context
		gctx.clearRect(0, 0, gctx.getCanvas().getWidth(), gctx.getCanvas().getHeight());
		
		camera.render();
		
		gctx.setStroke(Color.BLACK);
		gctx.strokeLine(Window.SCREEN_W/2, 0, Window.SCREEN_W/2, Window.SCREEN_H);
		gctx.strokeLine(0, Window.SCREEN_H/2, Window.SCREEN_W, Window.SCREEN_H/2);
		
//		// Render entities
//		entities = EntityManager.getEntitiesWithComponent(SpriteComponent.class);
//		for (AbstractEntity entity : entities)
//		{
//			PositionComponent position = entity.getComponent(PositionComponent.class);
//			SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
//			
//			gctx.drawImage(
//					sprite.getSpritesheet(), 
//					sprite.getSpriteColIndex() * sprite.getSpriteWidth(), // src X
//					sprite.getSpriteRowIndex() * sprite.getSpriteHeight(), // src Y
//					sprite.getSpriteWidth(), // src W
//					sprite.getSpriteHeight(), // src H
//					position.getX(), // dst X
//					position.getY(), // dst Y
//					sprite.getSpriteWidth(), // dst W
//					sprite.getSpriteHeight() // dst H
//			);
//		}
		
//		// Render map objects above
//		map.renderMapLayer(map.getLayerObjectsAbove());
	}
	
	/*----------------------------------------*/
	
}
