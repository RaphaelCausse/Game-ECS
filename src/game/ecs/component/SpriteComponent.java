package game.ecs.component;

import java.util.HashMap;
import java.util.Map;

import game.graphics.Sprite;
import utils.Settings.SpriteState;

/**
 * Classe qui represente un composant de sprite.
 * @see AbstractComponent
 */
public class SpriteComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int spriteIndex;
	private SpriteState state;
	private Map<SpriteState, Sprite> spriteMap;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe SpriteComponent.
	 */
	public SpriteComponent()
	{
		super();
		spriteIndex = 0;
		state = SpriteState.IDLE;
		spriteMap = new HashMap<SpriteState, Sprite>();
		
		// TODO Remove
		System.out.println("Component created : " + this.getClass());
	}
	
	/**
	 * Ajouter un ensemble de sprites associe a un etat au composant.
	 * @param state Etat associe a Sprite
	 * @param sprite Instance de Sprite, comporte un ensemble de sprites
	 */
	public void addSprite(SpriteState state, Sprite sprite)
	{
		spriteMap.put(state, sprite);
	}
	
	/**
	 * Retirer un ensemble de sprites associe a un etat au composant.
	 * @param state Etat associe a Sprite
	 */
	public void removeSprite(SpriteState state)
	{
		spriteMap.remove(state);
	}

	/**
	 * Verifier si le composant comporte un ensemble de sprites associe a un etat.
	 * @param state Etat associe a Sprite
	 * @return true, false
	 */
	public boolean hasSprite(SpriteState state)
	{
		return spriteMap.containsKey(state);
	}
	
	/*----------------------------------------*/
	
	/**
	 * Retourner l'index du sprite actuel.
	 * @return spriteIndex
	 */
	public int getSpriteIndex() { return spriteIndex; }
	
	/**
	 * Retourner l'etat de mouvement de sprite.
	 * @return state
	 */
	public SpriteState getState() { return state; }
	
	/**
	 * Definir l'etat de mouvement du sprite.
	 * @param _state
	 */
	public void setState(SpriteState _state) { state = _state; }
	
	/**
	 * Retourner le Sprite associe a l'etat donne.
	 * @param state Etat associe a Sprite
	 * @return Sprite associe a state
	 */
	public Sprite getSprite(SpriteState state) { return spriteMap.get(state); }
}
