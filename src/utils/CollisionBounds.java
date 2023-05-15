package utils;

/**
 * Classe qui represente les les bordures d'une boite de collisions.
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
	 * Constructeur de la classe CollisionBounds
	 * @param _minX Position en X, coin superieur gauche
	 * @param _minY Position en Y, coin superieur gauche
	 * @param _width Largeur de la boite
	 * @param _height Hauteur de la boite
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
	 * Decaler la boite.
	 * @param dx Decalage en X
	 * @param dy Decalage en Y
	 */
	public void shift(double dx, double dy)
	{
		minX += dx;
		minY += dy;
		maxX += dx;
		maxY += dy;
	}
	
	/**
	 * Verifier si deux boites se superposent.
	 * @param b Boite de collision
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

	public double getMinX() { return minX; }

	public double getMinY() { return minY; }

	public double getMaxX() { return maxX; }

	public double getMaxY() { return maxY; }

	public double getWidth() {return width; }
	
	public double getHeight() { return height; }

	public void setMinX(double minX) { this.minX = minX; }
	
	public void setMinY(double minY) { this.minY = minY; }

	public void setWidth(double width) { this.width = width; }

	public void setHeight(double height) { this.height = height; }
}
