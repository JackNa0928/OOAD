package OOAD;

public class Bugs extends Organism {
    //parameter
    public boolean newBreed = false;
    public boolean moved = false;
    public int starveRound = 0;
    Bugs(){
        super.name = "Bugs";
    }

    public void resetMoved(){
        moved = false;
    }
    @Override
    public boolean checkMovement(int current_x, int current_y, int next_x, int next_y) {
        if((current_x - next_x == 1 || current_x - next_x == -1) && current_y - next_y == 0 && next_x < 20 && next_x >= 0&& next_y < 20 && next_y >= 0) {
            return true;
        }
        else if(current_x - next_x == 0 && (current_y - next_y == 1 || current_y - next_y == -1 ) && next_x < 20 && next_x >= 0&& next_y < 20 && next_y >= 0){
            return true;
        }
        return false;
    }

    @Override
    public Location checkBreed(int current_x, int current_y, Organism[][] backBoard) { 
        if ((current_y - 1) > 0 && backBoard[current_x][current_y - 1] == null ){
            return new Location(current_x,current_y-1);
        }
        else if ((current_y + 1) < 20 && backBoard[current_x][current_y + 1] == null){
            return new Location(current_x,current_y+1);
        }
        else if ((current_x + 1) < 20 && backBoard[current_x + 1][current_y] == null ){
            return new Location(current_x+1,current_y);
        }
        else if ((current_x - 1 )> 0 && backBoard [current_x-1][current_y] == null ){
            return new Location(current_x-1,current_y);
        }
        else
            return null;
    }



    public boolean isStarving() {
        if(starveRound == 3){
            return true;
        }
        else
           return false;
    }

    /*
    public static void main(String[] args){
        Bugs bugs = new Bugs();
        System.out.println(bugs.checkMovement(0,0,1,1));
    }*/
}
