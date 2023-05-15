package game.ecs.component;

/**
 * Classe qui represente les points de vie de l'entite.
 * @see AbstractComponent
 */
public class HealthComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int maxHealth;
	private int currentHealth;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe HealthComponent.
	 */
	public HealthComponent(int _maxHealth)
	{
		super();
		maxHealth = _maxHealth;
		currentHealth = maxHealth;
	}

	/*----------------------------------------*/
	
	public int getMaxHealth() { return maxHealth; }
	
	public int getCurrentHealth() { return currentHealth; }
	
	public void setMaxHealth(int _maxHealth) { maxHealth = _maxHealth; }
	
	public void setCurrentHeath(int _currentHealth) { currentHealth = _currentHealth; }
}
