package OOAD;

public abstract class Organism {
    public String name;
    public boolean moved;

    public boolean checkMovement(int current_x, int current_y, int next_x, int next_y){
        return false;
    }
    public boolean checkBreed(int current_x, int current_y, Organism[][] backBoard){
        return false;
    }
    public boolean death(int counter){
        return false;
    }
}
