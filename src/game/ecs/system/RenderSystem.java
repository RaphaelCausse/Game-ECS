package game.ecs.system;

import game.graphics.Camera;
import game.graphics.GameMap;
import javafx.scene.canvas.GraphicsContext;

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
		// Render camera view
		camera.render();
	}
	
	/*----------------------------------------*/
	
}
