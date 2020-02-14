package OOAD;

import java.util.Random;

public class Game {
    public Organism[][] backBoard= new Organism[20][20];
    private static int tempX = 0;
    private static int tempY = 0;
    private int round = 0;
    private int noOfBugs = 0;
    private int noOfAnts = 0;

    Game(){
        Random random = new Random();
        for (int noOfAnts = 0; noOfAnts < 100; noOfAnts++){
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(backBoard[x][y] != null){
                noOfAnts--;
            }
            else
                backBoard[x][y] = new Ants();
        }
        for (int noOfBugs = 0; noOfBugs < 5; noOfBugs++){
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(backBoard[x][y] != null){
                noOfBugs--;
            }
            else {
                backBoard[x][y] = new Bugs();
            }
        }
        tempX = 0;
        tempY = 0;
        round = 0;
    }

    //for Odd Step
    public void gameFlow(int current_x, int current_y, int next_x, int next_y){
        round++;
        int touchedBugs = 0;
        bugsCounter();
        while(touchedBugs < noOfBugs){
            if(bugs_notMove(current_x,current_y)){
                if (backBoard[current_x][current_y].checkMovement(current_x,current_y,next_x,next_y)){
                    move(current_x,current_y,next_x,next_y);
                }
            }
        }
    }
    /*
        if (backBoard[current_x][current_y].checkMovement(current_x,current_y,next_x,next_y)){
                        move(current_x,current_y,next_x,next_y);
                    }
    AntsMove();
    */

    //check is it a not moved bug
    public boolean bugs_notMove(int this_x, int this_y){
        if(backBoard[this_x][this_y] instanceof Bugs)
        {
            if (((Bugs)backBoard[this_x][this_y]).moved) {
                return false;
            }
            else
                return true;
        }
        else
            return false;

    }

    //Starve
    public void starve(int this_x, int this_y){
        if(backBoard[this_x][this_y] instanceof Bugs)
        {
            if (((Bugs)backBoard[this_x][this_y]).isStarving()) {
                backBoard[this_x][this_y] = null;
            }
        }

    }

    //Ants counter
    public void antsCounter(){
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if(backBoard[x][y] instanceof Ants){
                    noOfAnts++;
                }
            }
        }
    }

    //Bugs Counter
    public void bugsCounter(){
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if(backBoard[x][y] instanceof Bugs){
                    noOfBugs++;
                }
            }
        }
    }

    //ants movement
    public void AntsMove(){
        Random random = new Random();
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20 ; x++){
                int xRan = 0;
                int yRan = 0;
                while (!(xRan != 0 && yRan == 0 || xRan == 0 && yRan != 0)) {
                     xRan = random.nextInt(2) - 1;
                     yRan = random.nextInt(2) - 1;
                    if (backBoard[x][y] instanceof Ants) {

                        move(x,y,x+xRan,y+yRan);
                    }
                }
            }
        }
    }

    //Move
    public void move(int current_x, int current_y, int next_x, int next_y){
        backBoard[next_x][next_y] = backBoard[current_x][current_y];
        backBoard[current_x][current_y]= null;
    }

    //breed
    public void antsBreed(){
        backBoard[tempX][tempY] = new Ants();
    }
    public void bugsBreed(){
        backBoard[tempX][tempY] = new Bugs();
    }

    //get location
    public static void getLocation(int x, int y){
        tempX = x;
        tempY = y;
    }
    public static void main(String[] args){
        Game game = new Game();
        for(int k = 0; k < 20; k++){
            for (int j = 0; j < 20 ; j++) {
               if(game.backBoard[j][k] != null){
                   System.out.print(game.backBoard[j][k].name);
               }
               else
                   System.out.print(0);
            }
            System.out.print("\n");
        }
    }
}

