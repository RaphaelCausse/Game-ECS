package game.ecs.component;

import game.ecs.FlagECS;
import utils.Settings.Window;

/**
 * Classe qui represente un composant d'animation de sprite.
 * @see AbstractComponent
 */
public class AnimationComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int animFrames;
	private int framesBeforeUpdate;
	private int frameCount;
	private int state;
	private int nbDirections;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe AnimationComponent.
	 * @param _animFrames
	 * @param _state
	 * @param _nbDirections
	 */
	public AnimationComponent(int _animFrames, int _state, int _nbDirections)
	{
		animFrames = _animFrames;
		framesBeforeUpdate = Window.FPS / animFrames;
		frameCount = 0;
		state = _state;
		nbDirections = _nbDirections;
		setFlag(FlagECS.TO_UPDATE);
	}
	
	/*----------------------------------------*/
	
	public int getFramesBeforeUpdate() { return framesBeforeUpdate; }
	
	public int getFrameCount() { return frameCount; }
	
	public int getState() { return state; }
	
	public int getNbDirection() { return nbDirections; }
	
	public void setFrameCount(int _frameCount) { frameCount = _frameCount; }
	
	public void setState(int _state) { state = _state; }
	
}
