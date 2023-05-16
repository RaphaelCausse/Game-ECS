package game.ecs.entity.items;

import game.ecs.entity.AbstractEntity;

/**
 *
 */
public class FlyingRing extends AbstractItem
{
	/*----------------------------------------*/
	
	/*----------------------------------------*/

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public FlyingRing(int x, int y)
	{
		super("Flying Ring");
		initialize(x, y);
	}

	@Override
	public void initialize(int x, int y)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void useItem(AbstractEntity sender, AbstractEntity receiver)
	{
		// TODO Auto-generated method stub

	}

	/*----------------------------------------*/
}
