package game.ecs.component;

import utils.Settings.Window;

/**
 * Classe qui represente un composant d'animation de sprite.
 * @see AbstractComponent
 */
public class AnimationComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int animationFrames;
	private int animationFrameCount;
	private boolean inAnimation;
	private int frameCount;
	private int framesBeforeUpdate;
	private int state;
	private int nbDirections;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe AnimationComponent.
	 * @param _animation Frames Nombre de frames de l'animation
	 * @param _state Etat initial de l'animations
	 * @param _nbDirections Nombre de directions de l'animation
	 */
	public AnimationComponent(int _animationFrames, int _state, int _nbDirections)
	{
		animationFrames = _animationFrames;
		animationFrameCount = 0;
		inAnimation = false;
		frameCount = 0;
		framesBeforeUpdate = Window.FPS / animationFrames;
		state = _state;
		nbDirections = _nbDirections;
	}
	
	/*----------------------------------------*/
	
	public int getAnimationFrames() { return animationFrames; }
	
	public int getAnimationFrameCount() { return animationFrameCount; }
	
	public boolean isInAnimation() { return inAnimation; }
		
	public int getFramesBeforeUpdate() { return framesBeforeUpdate; }
	
	public int getFrameCount() { return frameCount; }
	
	public int getState() { return state; }
	
	public int getNbDirection() { return nbDirections; }
	
	public void setAnimationFrameCount(int _animationFrameCount) { animationFrameCount = _animationFrameCount; }
	
	public void setFrameCount(int _frameCount) { frameCount = _frameCount; }
	
	public void setState(int _state) { state = _state; }
	
	public void setInAnimation(boolean _inAnimation) { inAnimation = _inAnimation; }
}
