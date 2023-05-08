package utils;

public class Point2D
{
	/*----------------------------------------*/
	
	private double x;
	private double y;
	
	/*----------------------------------------*/
	
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
	
	public double getX() { return x; }

    public double getY() { return y; }
    
    public void setX(double _x) { x = _x; }
    
    public void setY(double _y) { y = _y; }
    
    public void translateX(double dx) { x += dx; }
    
    public void translateY(double dy) { y += dy; }
}
