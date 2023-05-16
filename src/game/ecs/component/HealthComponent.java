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
	private boolean dead;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe HealthComponent.
	 */
	public HealthComponent(int _maxHealth)
	{
		super();
		maxHealth = _maxHealth;
		currentHealth = maxHealth;
		dead = false;
	}
	
	/**
	 * Verifier si l'entite n'a plus de point de vie.
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
	
	public int getMaxHealth() { return maxHealth; }
	
	public int getCurrentHealth() { return currentHealth; }
	
	public void setMaxHealth(int _maxHealth) { maxHealth = _maxHealth; }
	
	public void setCurrentHeath(int _currentHealth) { currentHealth = _currentHealth; }
}
