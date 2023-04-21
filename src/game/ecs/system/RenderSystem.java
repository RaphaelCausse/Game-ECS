package game.ecs.system;

import game.ecs.EntityManager;
import game.ecs.FlagECS;
import game.ecs.component.MovementComponent;
import game.ecs.component.SpritesComponent;
import game.ecs.entity.AbstractEntity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Point2D;
import utils.Settings.SpriteSize;

public class RenderSystem extends AbstractSystem
{
	/*----------------------------------------*/
	
	private GraphicsContext gctx;
	
	/*----------------------------------------*/
	
	public RenderSystem(GraphicsContext _gctx)
	{
		super();
		gctx = _gctx;
	}

	@Override
	public void run()
	{
		// Reset graphics context
		gctx.clearRect(0, 0, gctx.getCanvas().getWidth(), gctx.getCanvas().getHeight());
		gctx.fillRect(0, 0, gctx.getCanvas().getWidth(), gctx.getCanvas().getHeight());
		
		
		// Render entities
		entities = EntityManager.getEntitiesWithComponent(SpritesComponent.class);
		for (AbstractEntity entity : entities)
		{
			// Render sprite component in grphics context
			SpritesComponent sprites = entity.getComponent(SpritesComponent.class);
			MovementComponent movement = entity.getComponent(MovementComponent.class);
			Point2D pos = movement.getPos();
			
			Image spriteImage = sprites.getSprites(sprites.getState()).getSpriteImage(sprites.getSpriteDirection(), sprites.getSpriteIndex());
			gctx.drawImage(spriteImage, pos.getX(), pos.getY(), SpriteSize.PLAYER_SIZE, SpriteSize.PLAYER_SIZE);
			

			// Reset component flag
			sprites.setFlag(FlagECS.STABLE);
		}
	}
}
