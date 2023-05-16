package game.ecs.component;

/**
 * Classe qui represente les points d'attaque de l'entite.
 * @see AbstractComponent
 */
public class DamageComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	private int damage;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe DamageComponent.
	 */
	public DamageComponent(int _damage)
	{
		super();
		damage = _damage;
	}
	
	/**
	 * Infliger des degats aux points de vie de l'entite cible.
	 * @param targetHealth Composant point de vie de l'entite ciblee.
	 */
	public void dealDamage(HealthComponent targetHealth)
	{
		targetHealth.setCurrentHeath(targetHealth.getCurrentHealth() - damage); 
	}

	/*----------------------------------------*/
	
	public int getDamage() { return damage; }
}
