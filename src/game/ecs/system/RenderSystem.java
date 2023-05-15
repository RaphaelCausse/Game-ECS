package game.ecs.system;

import game.graphics.Camera;
import game.graphics.GameMap;
import game.graphics.HUD;
import javafx.scene.canvas.GraphicsContext;

/**
 * Classe responsable du systeme d'affichage du jeu.
 * @see AbstractSystem
 */
public class RenderSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private Camera camera;
	private HUD hud;
	private GameMap map;
	private GraphicsContext gctx;
	
	/*----------------------------------------*/
	
	/**
	 * Constucteur de la classe RenderSystem.
	 * @param _camera Camera du jeu
	 * @param _hud HUD du jeu
	 */
	public RenderSystem(Camera _camera, HUD _hud)
	{
		super();
		camera = _camera;
		hud = _hud;
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
		// Render HUD
		hud.render();
	}
	
	/*----------------------------------------*/
}
