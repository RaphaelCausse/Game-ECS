package game.ecs.component;

import java.util.HashMap;
import java.util.Map;

import game.graphics.Sprites;
import utils.Settings.Movement;
import utils.Settings.SpriteState;

/**
 * Classe qui represente un composant de sprite.
 * @see AbstractComponent
 */
public class SpritesComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private Map<Integer, Sprites> spriteMap;
	private int state;
	private int spriteIndex;
	private int spriteDirection;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe SpriteComponent.
	 */
	public SpritesComponent()
	{
		super();
		spriteMap = new HashMap<Integer, Sprites>();
		state = SpriteState.IDLE;
		spriteIndex = 0;
		spriteDirection = 0;
	}
	
	/**
	 * Ajouter un ensemble de sprites associe a un etat au composant.
	 * @param state Etat associe a Sprite
	 * @param sprite Instance de Sprite, comporte un ensemble de sprites
	 */
	public void addSprite(int state, Sprites sprite)
	{
		spriteMap.put(state, sprite);
	}
	
	/**
	 * Retirer un ensemble de sprites associe a un etat au composant.
	 * @param state Etat associe a Sprite
	 */
	public void removeSprite(int state)
	{
		spriteMap.remove(state);
	}

	/**
	 * Verifier si le composant comporte un ensemble de sprites associe a un etat.
	 * @param state Etat associe a Sprite
	 * @return true, false
	 */
	public boolean hasSprite(int state)
	{
		return spriteMap.containsKey(state);
	}
	
	/*----------------------------------------*/
	
	
	/**
	 * Retourner l'etat de mouvement de sprite.
	 * @return state
	 */
	public int getState() { return state; }
	
	/**
	 * Definir l'etat de mouvement du sprite.
	 * @param _state
	 */
	public void setState(int _state) { state = _state; }

	/**
	 * Retourner l'index d'image du sprite actuel.
	 * @return spriteIndex
	 */
	public int getSpriteIndex() { return spriteIndex; }
	
	/**
	 * Definir l'index d'image du sprite actuel.
	 * @param _state
	 */
	public void setSpriteIndex(int index) { spriteIndex = index; }
	
	/**
	 * Retourner la direction du sprite actuel.
	 * @return spriteIndex
	 */
	public int getSpriteDirection() { return spriteDirection; }
	
	/**
	 * Definir la direction du sprite.
	 * @param _state
	 */
	public void setSpriteDirection(int _direction) { spriteDirection = _direction; }
	
	/**
	 * Retourner le Sprites associe a l'etat donne.
	 * @param state Etat associe a Sprite
	 * @return Sprite associe a state
	 */
	public Sprites getSprites(int state) { return spriteMap.get(state); }
}
