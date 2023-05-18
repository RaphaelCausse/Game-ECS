package utils;

/**
 * Class that represents collision bounds of a box.
 */
public class CollisionBounds
{
	/*----------------------------------------*/
	
	private double minX;
	private double minY;
	private double maxX;
	private double maxY;
	private double width;
	private double height;
	
	/*----------------------------------------*/

	/**
	 * Constructor of CollisionBounds class.
	 * @param _minX Top left corner X position
	 * @param _minY Top left corner Y position
	 * @param _width Bounds width
	 * @param _height Bounds height
	 */
	public CollisionBounds(double _minX, double _minY, double _width, double _height)
	{
		minX = _minX;
		minY = _minY;
		maxX = minX + _width;
		maxY = minY + _height;
		width = _width;
		height = _height;
	}
	
	/**
	 * Shift the bounds.
	 * @param dx X shift
	 * @param dy Y shift
	 */
	public void shift(double dx, double dy)
	{
		minX += dx;
		minY += dy;
		maxX += dx;
		maxY += dy;
	}
	
	/**
	 * Check if two bounds intersects.
	 * @param b Bounds to check intersection with
	 * @return true, false
	 */
	public boolean intersects(CollisionBounds b)
	{
		if (maxX < b.minX || minX > b.maxX)
		{
	        return false;
	    }
		if (maxY < b.minY || minY > b.maxY)
	    {
	        return false;
	    }
		return true;
	}
	
	@Override
	public String toString()
	{
		return "CollisionBounds [minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + "]";
	}

	/*----------------------------------------*/

	/**
	 * Get top left corner X position.
	 * @return minX
	 */
	public double getMinX() { return minX; }

	/**
	 * Get top left corner Y position.
	 * @return minY
	 */
	public double getMinY() { return minY; }

	/**
	 * Get bottom right corner X position.
	 * @return maxX
	 */
	public double getMaxX() { return maxX; }

	/**
	 * Get bottom right corner Y position
	 * @return maxY
	 */
	public double getMaxY() { return maxY; }
	
	/**
	 * Get center X position.
	 * @return centerX
	 */
	public double getCenterX() { return minX + width/2; }
	
	/**
	 * Get center Y position.
	 * @return centerY
	 */
	public double getCenterY() { return minY + height/2; }

	/**
	 * Get bounds width.
	 * @return width
	 */
	public double getWidth() {return width; }
	
	/**
	 * Get bounds height.
	 * @return height
	 */
	public double getHeight() { return height; }

	/**
	 * Set top left corner X position.
	 * @param minX New top left corner X position
	 */
	public void setMinX(double _minX) { minX = _minX; }
	
	/**
	 * Set top left corner Y position.
	 * @return _minY New top left corner Y position
	 */
	public void setMinY(double _minY) { minY = _minY; }

	/**
	 * Set bounds width;
	 * @param _width New width
	 */
	public void setWidth(double _width) { width = _width; }

	/**
	 * Set bounds height.
	 * @param _height New height
	 */
	public void setHeight(double _height) { height = _height; }
}
