package utils;

/**
 * Class that represents a point in 2D.
 */
public class Point2D
{
	/*----------------------------------------*/
	
	private double x;
	private double y;
	
	/*----------------------------------------*/
	
	/**
	 * Constructor of Point2D class.
	 * @param _x X position
	 * @param _y Y position
	 */
	public Point2D(double _x, double _y)
	{
		x = _x;
		y = _y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		Point2D other = (Point2D) obj;
		return x == other.x && y == other.y;
	}
	
	@Override
    public String toString() {
        return "(" + x + " , " + y + ")";
    }
	
	/*----------------------------------------*/
	
	/**
	 * Get X position.
	 * @return x
	 */
	public double getX() { return x; }

	/**
	 * Get Y position.
	 * @return y
	 */
    public double getY() { return y; }
    
    /**
     * Set x position.
     * @param _x New X position
     */
    public void setX(double _x) { x = _x; }
    
    /**
     * Set Y position.
     * @param _y new Y position
     */
    public void setY(double _y) { y = _y; }
    
    /**
     * Translate X position.
     * @param dx Translate x value
     */
    public void translateX(double dx) { x += dx; }
    
    /**
     * Translate Y position.
     * @param dy Translate y value
     */
    public void translateY(double dy) { y += dy; }
}
