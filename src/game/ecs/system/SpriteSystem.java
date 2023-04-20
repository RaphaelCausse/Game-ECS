package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.FlagECS;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpriteComponent;
import game.ecs.entity.AbstractEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Point2D;
import utils.Settings.SpriteSize;
import utils.Settings.SpriteState;

/**
 * Classe responsable de la gestion des sprites.
 */
public class SpriteSystem extends AbstractSystem
{	
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe SpriteSystem.
	 */
	public SpriteSystem(GraphicsContext _gctx)
	{
		super();
		gctx = _gctx;
		
		// TODO Remove
		System.out.println("System created : " + this.getClass());
	}

	@Override
	public void run()
	{
		entities = EntityManager.getEntitiesWithComponent(SpriteComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Update sprite component
			SpriteComponent sprites = entity.getComponent(SpriteComponent.class);
			// Check for entities that need need to be update
			if (sprites.getFlag() == FlagECS.STABLE)
			{
				continue;
			}
			
			// TODO Update sprites data based on time for animation => ALL UNDER => TEMPORARY
			// => Move all rendering logic to RenderSystem
			//
			
			// Get position from movement component to render entity
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			Point2D pos = movement.getPos();
			
			// Reset graphics context
			gctx.clearRect(0, 0, gctx.getCanvas().getWidth(), gctx.getCanvas().getHeight());
			gctx.fillRect(0, 0, gctx.getCanvas().getWidth(), gctx.getCanvas().getHeight());
			
			// Render sprites in graphics context
			Image currentSprite = sprites.getSprite(sprites.getState()).getSpriteImage(sprites.getSpriteIndex());
			gctx.drawImage(currentSprite, pos.getX(), pos.getY(), currentSprite.getWidth()*SpriteSize.PLAYER_SCALE, currentSprite.getHeight()*SpriteSize.PLAYER_SCALE);
			
			//
			// ALL ABOVE => TEMPORARY 
			
			// Reset component flag
			sprites.setFlag(FlagECS.STABLE);
		}
	}
	
	/*----------------------------------------*/
}
