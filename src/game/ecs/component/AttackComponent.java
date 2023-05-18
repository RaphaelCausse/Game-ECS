package game.ecs.component;

import game.ecs.entity.AbstractEntity;
import javafx.scene.image.Image;
import utils.CollisionBounds;
import utils.Point2D;
import utils.Settings.ResFiles;

/**
 * Class that represents the attack component.
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
	 * Constructor of AttackComponent class.
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
	
	/**
	 * Add an attack hitbox
	 * @param x Top left corner X position
	 * @param y Top left corner Y position
	 * @param width Bounds width
	 * @param height Bounds height
	 * @param ox Offset X
	 * @param oy Offset Y
	 */
	public void addAttackHitBox(int x, int y, int w, int h, int ox, int oy)
	{
		attackHitbox = new CollisionBounds(x, y, w, h);
		offset = new Point2D(ox, oy);
	}
	
	/**
	 * Remove the attack hitbox.
	 */
	public void removeAttackHitbox()
	{
		attackHitbox = null;
		offset = null;
	}

	/**
	 * Update the attack hitbox to follow positon.
	 * @param position Position to follow
	 */
	public void updateAttackHitbox(PositionComponent position)
	{
		if (attackHitbox != null)
		{
			double x = position.getX() - attackHitbox.getMinX() + offset.getX();
			double y = position.getY() - attackHitbox.getMinY() + offset.getY();
			attackHitbox.shift(x, y);
		}
	}
	
	/**
	 * Check if attack hitbox is hitting another hitbox.
	 * @param target Entity to check with
	 * @return true, false
	 */
	public boolean isHitting(AbstractEntity target)
	{
		CollisionBounds targetBounds = target.getComponent(ColliderComponent.class).getBounds();
		return attackHitbox.intersects(targetBounds);
	}
	
	/**
	 * Deal damage to target entity.
	 * @param target Entity to deal damage to.
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
	
	/**
	 * Get attack hitbox.
	 * @return attackHitbox
	 */
	public CollisionBounds getAttackHitbox() { return attackHitbox; }
	
	/**
	 * Get damage.
	 * @return damage
	 */
	public int getDamage() { return damage; }
	
	/**
	 * Check if is attacking.
	 * @return true, false
	 */
	public boolean isAttacking() { return attacking; }
	
	/**
	 * Check if has perform an attack.
	 * @return true, false
	 */
	public boolean hasAttacked() { return attacked; }
	
	/**
	 * Set damage
	 * @param _damage New damage
	 */
	public void setDamage(int _damage) { damage = _damage; }
	
	/**
	 * Set is attacking.
	 * @param _attacking
	 */
	public void setAttacking(boolean _attacking) { attacking = _attacking; }
	
	/**
	 * Set has attacked.
	 * @param _attacked
	 */
	public void setHasAttacked(boolean _attacked) { attacked = _attacked; }
}
