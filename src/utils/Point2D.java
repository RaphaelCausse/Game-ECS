package utils;

public class Point2D
{
	/*----------------------------------------*/
	
	private int x;
	private int y;
	
	/*----------------------------------------*/
	
	public Point2D(int _x, int _y)
	{
		x = _x;
		y = _y;
	}
	
	public Point2D()
	{
		this(0, 0);
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
	
	public int getX() { return x; }

    public int getY() { return y; }
    
    public void setX(int _x) { x = _x; }
    
    public void setY(int _y) { y = _y; }
    
    public void translateX(int dx) { x += dx; }
    
    public void translateY(int dy) { y += dy; }
}
