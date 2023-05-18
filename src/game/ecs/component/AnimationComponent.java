package game.ecs.component;

import utils.Settings.App;

/**
 * Class that represents the animation component.
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
	 * Constructor of AnimationComponent class.
	 * @param _animation Number of frames of the animation
	 * @param _state Initial state of animation
	 * @param _nbDirections Number of directions the animation has
	 */
	public AnimationComponent(int _animationFrames, int _state, int _nbDirections)
	{
		animationFrames = _animationFrames;
		animationFrameCount = 0;
		inAnimation = false;
		frameCount = 0;
		framesBeforeUpdate = App.FPS / animationFrames;
		state = _state;
		nbDirections = _nbDirections;
	}
	
	/*----------------------------------------*/
	
	/**
	 * Get number of frames of the animation.
	 * @return animationFrames
	 */
	public int getAnimationFrames() { return animationFrames; }
	
	/**
	 * Get count of frames of the animation.
	 * @return animationFrameCount
	 */
	public int getAnimationFrameCount() { return animationFrameCount; }
	
	/**
	 * Check if is currently in animation.
	 * @return true, false
	 */
	public boolean isInAnimation() { return inAnimation; }
		
	/**
	 * Get number of frames that indicates when to change animation frame.
	 * @return framesBeforeUpdate
	 */
	public int getFramesBeforeUpdate() { return framesBeforeUpdate; }
	
	/**
	 * Get count of frames of the game.
	 * @return frameCount
	 */
	public int getFrameCount() { return frameCount; }
	
	/**
	 * Get animation state.
	 * @return state
	 */
	public int getState() { return state; }
	
	/**
	 * Get number of directions for the animation.
	 * @return
	 */
	public int getNbDirection() { return nbDirections; }
	
	/**
	 * Set number of frames of the animation.
	 * @param _animationFrames New number of frames of the animation.
	 */
	public void setAnimationFrameCount(int _animationFrameCount) { animationFrameCount = _animationFrameCount; }
	
	/**
	 * Set count of frames of the game.
	 * @return _frameCount New count of frames of the game.
	 */
	public void setFrameCount(int _frameCount) { frameCount = _frameCount; }
	
	/**
	 * Set animation state.
	 * @return _state New animation state
	 */
	public void setState(int _state) { state = _state; }
	
	/**
	 * Set in animation.
	 * @param _inAnimation
	 */
	public void setInAnimation(boolean _inAnimation) { inAnimation = _inAnimation; }
}
