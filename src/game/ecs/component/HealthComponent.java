package game.ecs.component;

/**
 * Class that represents health points of entity.
 * @see AbstractComponent
 */
public class HealthComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int maxHealth;
	private int currentHealth;
	private boolean dead;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of HealthComponent class.
	 */
	public HealthComponent(int _maxHealth)
	{
		super();
		maxHealth = _maxHealth;
		currentHealth = maxHealth;
		dead = false;
	}
	
	/**
	 * Check is entity is dead.
	 * @return true, false
	 */
	public boolean isDead()
	{
		if (currentHealth <= 0)
		{
			currentHealth = 0;
			dead = true;
		}
		return dead;
	}

	/*----------------------------------------*/
	
	/**
	 * Get entity max health.
	 * @return maxHealth
	 */
	public int getMaxHealth() { return maxHealth; }
	
	/**
	 * Get entity current health.
	 * @return currentHealth
	 */
	public int getCurrentHealth() { return currentHealth; }
	
	/**
	 * Set entity max health.
	 * @param _maxHealth New max health
	 */
	public void setMaxHealth(int _maxHealth) { maxHealth = _maxHealth; }
	
	/**
	 * Set entity current health.
	 * @param _currentHealth New current health
	 */
	public void setCurrentHeath(int _currentHealth) { currentHealth = _currentHealth; }
}
