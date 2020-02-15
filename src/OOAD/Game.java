package OOAD;

import java.util.Random;

public class Game {
    public Organism[][] backBoard= new Organism[20][20];
    private static int tempX = 0;
    private static int tempY = 0;
    private int round = 0;
    private int noOfBugs = 0;
    private int noOfAnts = 0;
    private GUI gui;
    private boolean game_End = false;
    private Random random = new Random();

    //constructor
    Game(){
        Random random = new Random();
        for (int noOfAnts = 0; noOfAnts < 100; noOfAnts++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if (backBoard[x][y] != null) {
                noOfAnts--;
            } else
                backBoard[x][y] = new Ants();
        }
        for (int noOfBugs = 0; noOfBugs < 5; noOfBugs++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if (backBoard[x][y] != null) {
                noOfBugs--;
            } else {
                backBoard[x][y] = new Bugs();
            }
        }
        tempX = 0;
        tempY = 0;
        round = 0;
        noOfAnts = 0;
        noOfBugs = 0;
        game_End = false;

        // after start clicked in GUI call the Game() //constructor

    }

    /**************************************************************************/

    //Game Progress
    public void gameFlow(){
        round++;
        //bugs move
        //bugsCounter(); //update noOfBugs
        int noOfMovedBugs = 0;
        while(noOfMovedBugs < noOfBugs){
            for(int y = 0; y < 20; y++){
                for(int x = 0; x< 20; x++){
                    if(!((Bugs) backBoard[x][y]).moved){
                        checkPrey(x,y);
                        if (backBoard[x][y].checkMovement(x, y, tempX, tempY)) {
                            move(x, y, tempX, tempY);
                            noOfMovedBugs++;
                        }
                        else{
                            move(x,y,x,y);
                            noOfMovedBugs++;
                        }
                    }
                }
            }
        }

        //AntsMove
        AntsMove();

        //check round & breed
        if(round%3 == 0){
            antBreeding();
        }
        if(round % 8 == 0){
            bugBreeding();
        }

        //starve
        for(int y = 0; y < 20; y++){
            for(int x = 0; x< 20; x++) {
                starve(x,y);
            }
        }

    }

    //Game End
    public boolean gameEnd(){
        if(noOfBugs == 0 || noOfAnts ==0){
            return true;
        }
        return false;
    }

    /**************************************************************************/
    //CATEGORY : STARVATION
    //Starve
    public void starve(int this_x, int this_y){
        if(backBoard[this_x][this_y] instanceof Bugs)
        {
            if (((Bugs)backBoard[this_x][this_y]).isStarving()) {
                backBoard[this_x][this_y] = null;
            }
        }

    }

    /**************************************************************************/

    //CATEGORY : MOVE

    //check is it a not moved bug
    public boolean bugs_notMove(int this_x, int this_y){
        if(backBoard[this_x][this_y] instanceof Bugs)
        {
            if (((Bugs)backBoard[this_x][this_y]).moved) {
                return false;
            }
            else{
                ((Bugs)backBoard[this_x][this_y]).moved = true;
                return true;
            }
        }
        else
            return false;

    }

    //check is it a not moved bug
    public boolean ants_notMove(int this_x, int this_y){
        if(backBoard[this_x][this_y] instanceof Ants)
        {
            if (((Ants)backBoard[this_x][this_y]).moved) {
                return false;
            }
            else{
                ((Ants)backBoard[this_x][this_y]).moved = true;
                return true;
            }
        }
        else
            return false;

    }

    //check for ants
    public void checkPrey(int x,int y){
        if(backBoard[x+1][y] instanceof Ants){
            tempX = x+1;
            tempY = y;
            ((Bugs)backBoard[x][y]).starveRound = 0;
        }
        else if(backBoard[x-1][y] instanceof Ants){
            tempX = x-1;
            tempY = y;
            ((Bugs)backBoard[x][y]).starveRound = 0;
        }
        else if(backBoard[x][y+1] instanceof Ants){
            tempX = x;
            tempY = y+1;
            ((Bugs)backBoard[x][y]).starveRound = 0;
        }
        else if(backBoard[x][y-1] instanceof Ants){
            tempX = x;
            tempY = y-1;
            ((Bugs)backBoard[x][y]).starveRound = 0;
        }
        else{
            tempX = x+random.nextInt(2)-1;
            tempY = y+random.nextInt(2)-1;
            if(backBoard[tempY][tempY] == null) {
                ((Bugs)backBoard[x][y]).starveRound++;
            }
            else{
                tempX = x;
                tempY = y;
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
    public void AntsMove() {
        antsCounter();//update noOfAnts
        int noOfMovedAnts = 0;
        while (noOfMovedAnts < noOfAnts) {

            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 20; x++) {
                    Random random = new Random();
                    int next_x = random.nextInt(2) - 1;
                    int next_y = random.nextInt(2) - 1;
                    if (ants_notMove(x, y)) {
                        if(backBoard[x+next_x][y+next_y] == null){
                            if (backBoard[x][y].checkMovement(x, y, x + next_x, y + next_y)) {
                                move(x, y, x + next_x, y + next_y);
                                noOfMovedAnts++;
                            }
                        }
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


    /**************************************************************************/
    //CATEGORY : BREED
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

    //for ant to breed
    public void antBreeding(){
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if(backBoard[x][y] instanceof Ants){
                    if(backBoard[x][y].checkBreed(x,y,backBoard)){
                        noOfAnts++;
                        antsBreed();
                    }
                }
            }
        }
    }

    //for bug to breed
    public void bugBreeding(){
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if(backBoard[x][y] instanceof Bugs){
                    if(backBoard[x][y].checkBreed(x,y,backBoard)){
                        noOfBugs++;
                        bugsBreed();
                    }
                }
            }
        }
    }

    /**************************************************************************/


    //CATEGORY : CONNECTOR
    //send the 2d array GUI
    public void sendArray (){
        gui.getArray(backBoard);
        //GUI need to have a function to get the array
    }



    /**************************************************************************/
    /*public static void main(String[] args){
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
    }*/
}

