package OOAD;

public class Ants extends Organism {
    public boolean newBreed = false;
    Ants(){
        super.name = "Ants";
    }

    @Override
    public boolean checkMovement(int current_x, int current_y, int next_x, int next_y) {
        if((current_x - next_x == 1 || current_x - next_x == -1) && current_y - next_y == 0) {
            return true;
        }
        else if(current_x - next_x == 0 && (current_y - next_y == 1 || current_y - next_y == -1)){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkBreed(int counter, int current_x, int current_y, Organism[][] backBoard) {
        if ( backBoard [current_x-1][current_y-1] == null){
            Game.getLocation(current_x-1,current_y-1);
            return true;
        }
        else if ( backBoard [current_x+1][current_y+1] == null){
            Game.getLocation(current_x+1,current_y+1);
            return true;
        }
        else if ( backBoard [current_x+1][current_y-1] == null){
            Game.getLocation(current_x+1,current_y-1);
            return true;
        }
        else if ( backBoard [current_x-1][current_y+1] == null){
            Game.getLocation(current_x-1,current_y+1);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean death(int counter) {
        return false;
    }

    public static void main(String[] args){
        Ants ants = new Ants();
        System.out.println(ants.checkMovement(0,0,1,1));
    }
}
