package OOAD;

public class Ants extends Organism {
    private boolean newBreed = false;
	private boolean moved = false;
    private Location location = new Location();
    
    Ants(String name){
        super(name);
    }

    public boolean isNewBreed() {
		return newBreed;
	}

	public void setNewBreed(boolean newBreed) {
		this.newBreed = newBreed;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

    @Override
    public boolean checkMovement(int current_x, int current_y, int next_x, int next_y) {
        if((current_x - next_x == 1 || current_x - next_x == -1) && current_y - next_y == 0 && next_x < 20 && next_x >= 0&& next_y < 20 && next_y >= 0) {
            return true;
        }
        else if(current_x - next_x == 0 && (current_y - next_y == 1 || current_y - next_y == -1) && next_x < 20 && next_x >= 0&& next_y < 20 && next_y >= 0){
            return true;
        }
        return false;
    }

    @Override
    public Location checkBreed(int current_x, int current_y, Organism[][] backBoard) { 
        if ((current_y - 1) > 0 && backBoard[current_x][current_y - 1] == null ){
        	System.out.println("Breed Up");
            return new Location(current_x,current_y-1);
        }
        else if ((current_y + 1) < 20 && backBoard[current_x][current_y + 1] == null){
        	System.out.println("Breed Down");
        	return new Location(current_x,current_y+1);
        }
        else if ((current_x + 1) < 20 && backBoard[current_x + 1][current_y] == null ){
        	System.out.println("Breed Right");
        	return new Location(current_x+1,current_y);
        }
        else if ((current_x - 1 )> 0 && backBoard [current_x-1][current_y] == null ){
        	System.out.println("Breed Left");
        	return new Location(current_x-1,current_y);
        }
        else
        	System.out.println("No breed");
        	return null;
    }

    @Override
    public boolean death(int counter) {
        return false;
    }

    /*
    public static void main(String[] args){
        Ants ants = new Ants();
        System.out.println(ants.checkMovement(0,0,1,1));
    }

     */
}
