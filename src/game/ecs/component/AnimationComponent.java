package game.ecs.component;

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
	private int direction;
	private int nbDirections;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe AnimationComponent.
	 * @param _frameAnimPerSecond Nombre de frame de l'animation par seconde
	 */
	public AnimationComponent(int _animFrames, int _state, int _direction, int _nbDirections)
	{
		animFrames = _animFrames;
		framesBeforeUpdate = Window.FPS / animFrames;
		frameCount = 0;
		state = _state;
		direction = _direction;
		nbDirections = _nbDirections;
		setFlag(FlagECS.TO_UPDATE);
	}
	
	/*----------------------------------------*/
	
	public int getFramesBeforeUpdate() { return framesBeforeUpdate; }
	
	public int getFrameCount() { return frameCount; }
	
	public int getState() { return state; }
	
	public int getDirection() { return direction; }
	
	public int getNbDirection() { return nbDirections; }
	
	public void setFrameCount(int _frameCount) { frameCount = _frameCount; }
	
	public void setState(int _state) { state = _state; }
	
	public void setDirection(int _direction) { direction = _direction; }
	
}
