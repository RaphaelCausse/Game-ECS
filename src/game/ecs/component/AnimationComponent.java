package game.ecs.component;

import utils.Settings.Window;

/**
 * Classe qui represente un composant d'animation de sprite.
 * @see AbstractComponent
 */
public class AnimationComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int frameAnimCount;
	private int frameUpdateValue;
	private int frameCount;
	private int frameIndex;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe AnimationComponent.
	 * @param _frameAnimPerSecond Nombre de frame de l'animation par seconde
	 */
	public AnimationComponent(int _frameAnimCount)
	{
		frameAnimCount = _frameAnimCount;
		frameUpdateValue = Window.FPS / frameAnimCount; // Nombre de frame avant l'update de l'animation
		frameCount = 0;
		frameIndex = 0;
	}
	
	/*----------------------------------------*/
	
	public int getFrameUpdateValue() { return frameUpdateValue; }
	
	public int getFrameCount() { return frameCount; }
	
	public int getFrameIndex() { return frameIndex; }
	
	public void setFrameCount(int _frameCount) { frameCount = _frameCount; }
	
	public void setFrameIndex(int _frameIndex) { frameIndex = _frameIndex; }
}
