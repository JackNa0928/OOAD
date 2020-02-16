package OOAD;

public class Location {
	private int coor_x;
	private int coor_y;
	
	Location(int x, int y)
	{
		setCoor_x(x);
		setCoor_y(y);
	}
	
	public Location() {
	}

	public int getCoor_x() {
		return coor_x;
	}
	public void setCoor_x(int coor_x) {
		this.coor_x = coor_x;
	}
	public int getCoor_y() {
		return coor_y;
	}
	public void setCoor_y(int coor_y) {
		this.coor_y = coor_y;
	}
}
