package OOAD;

public abstract class Organism {
    public String name;
    public boolean moved;
    public boolean newBreed;

    public boolean checkMovement(int current_x, int current_y, int next_x, int next_y){
        return false;
    }
    public Location checkBreed(int current_x, int current_y, Organism[][] backBoard){
        return null;
    }
    public boolean death(int counter){
        return false;
    }
}
