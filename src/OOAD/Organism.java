package OOAD;

public abstract class Organism {
    private String name;
    private boolean moved;
    private boolean newBreed;

    Organism(String name)
    {
    	this.name = name;
    }
    public boolean checkMovement(int current_x, int current_y, int next_x, int next_y){
        return false;
    }
    public Location checkBreed(int current_x, int current_y, Organism[][] backBoard){
        return null;
    }
    public String getName() {
		return name;
	}
	public boolean death(int counter){
        return false;
    }
}
