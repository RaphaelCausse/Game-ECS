package game.ecs.component;

import game.ecs.entity.AbstractEntity;
import javafx.scene.image.Image;
import utils.CollisionBounds;
import utils.Point2D;
import utils.Settings.ResFiles;

/**
 * Classe qui represente les points d'attaque de l'entite.
 * @see AbstractComponent
 */
public class AttackComponent extends AbstractComponent
{
	/*----------------------------------------*/
	
	public int targetUID;
	private CollisionBounds attackHitbox;
	private Point2D offset;
	private int damage;
	private boolean attacking;
	private boolean attacked;
	public int attackCooldown;
	public int cooldownCount;
	public Image monsterDamageSprite;
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur de la classe DamageComponent.
	 */
	public AttackComponent(int _damage, int _attackCooldown)
	{
		super();
		damage = _damage;
		attacking = false;
		attacked = true;
		attackCooldown = _attackCooldown;
		cooldownCount = 0;
		monsterDamageSprite = new Image(ResFiles.POISON_DAMAGE_ORBE);
	}
	
	public void addAttackHitBox(int x, int y, int w, int h, int ox, int oy)
	{
		attackHitbox = new CollisionBounds(x, y, w, h);
		offset = new Point2D(ox, oy);
	}
	
	public void removeAttackHitbox()
	{
		attackHitbox = null;
		offset = null;
	}

	public void updateAttackHitbox(PositionComponent position)
	{
		if (attackHitbox != null)
		{
			double x = position.getX() - attackHitbox.getMinX() + offset.getX();
			double y = position.getY() - attackHitbox.getMinY() + offset.getY();
			attackHitbox.shift(x, y);
		}
	}
	
	public boolean isHitting(AbstractEntity target)
	{
		CollisionBounds targetBounds = target.getComponent(ColliderComponent.class).getBounds();
		return attackHitbox.intersects(targetBounds);
	}
	
	/**
	 * Infliger des degats aux points de vie de l'entite cible.
	 * @param targetHealth Composant point de vie de l'entite ciblee.
	 */
	public void dealDamageTo(AbstractEntity target)
	{
		HealthComponent targetHealth = target.getComponent(HealthComponent.class);
		if (targetHealth != null && !targetHealth.isDead())
		{
			targetHealth.setCurrentHeath(targetHealth.getCurrentHealth() - damage);
			attacked = true;
		}
	}

	/*----------------------------------------*/
	
	public CollisionBounds getAttackHitbox() { return attackHitbox; }
	
	public int getDamage() { return damage; }
	
	public boolean isAttacking() { return attacking; }
	
	public boolean hasAttacked() { return attacked; }
	
	public void setDamage(int _damage) { damage = _damage; }
	
	public void setAttacking(boolean _attacking) { attacking = _attacking; }
	
	public void setHasAttacked(boolean _attacked) { attacked = _attacked; }
}
