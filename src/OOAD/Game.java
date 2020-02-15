package OOAD;

import java.util.Random;

public class Game {
    public Organism[][] backBoard= new Organism[20][20];
    private static int tempX = 0;
    private static int tempY = 0;
    private int round = 0;
    private int noOfBugs = 0;
    private int noOfAnts = 0;

    private boolean game_End = false;
    private Random random = new Random();

    //constructor
    Game(){
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if (backBoard[x][y] != null) {
                i--;
            } else
                backBoard[x][y] = new Ants();
        }
        noOfAnts =100;
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if (backBoard[x][y] != null) {
                i--;
            } else {
                backBoard[x][y] = new Bugs();
            }
        }
        noOfBugs = 5;

        tempX = 0;
        tempY = 0;
        round = 0;
        game_End = false;

        // after start clicked in GUI call the Game() //constructor

    }

    /**************************************************************************/

    //Game Progress
    public void gameFlow(){
        round++;
        System.out.println(round);
        System.out.println(noOfAnts);
        System.out.println(noOfBugs);
        //bugs move
        int noOfMovedBugs = 0;
        while(noOfMovedBugs < noOfBugs){
            for(int y = 0; y < 20; y++){
                for(int x = 0; x< 20; x++){
                    if(backBoard[x][y] instanceof Bugs) {
                        if (!((Bugs) backBoard[x][y]).moved) {
                            checkPrey(x, y);
                            System.out.println("check Prey done");
                            noOfMovedBugs++;
                        }
                    }
                }
            }
        }

        //AntsMove
        AntsMove();
        System.out.println("Ants moved");
        //reset the Moved to false
        for(int y = 0; y < 20; y++){
            for(int x = 0; x< 20; x++){
                if (backBoard[x][y] !=null) {
                    backBoard[x][y].moved = false;
                }
            }
        }
        System.out.println("Clear moved");
        //check round & breed
        if(round%3 == 0){
            antBreeding();
        }
        if(round % 8 == 0){
            bugBreeding();
        }
        System.out.println("Breed done");

        //reset Breed status
        resetBreed();

        //starve
        for(int y = 0; y < 20; y++){
            for(int x = 0; x< 20; x++) {
                starve(x,y);
            }
        }
        System.out.println("Check starve done");
    }
    /**************************************************************************/
    //CATEGORY : STARVATION
    //Starve
    public void starve(int this_x, int this_y){
        if(backBoard[this_x][this_y] instanceof Bugs)
        {
            if (((Bugs)backBoard[this_x][this_y]).isStarving()) {
                backBoard[this_x][this_y] = null;
                noOfBugs--;
                System.out.println("Bug is dead");
            }
        }

    }

    /**************************************************************************/

    //CATEGORY : MOVE

    //check for ants
    public void checkPrey(int x,int y){
        if(backBoard[x][y].checkMovement(x, y, x+1, y)){
            if(backBoard[x+1][y] instanceof Ants){
                tempX = x+1;
                tempY = y;
                ((Bugs)backBoard[x][y]).starveRound = 0;
                move(x,y,tempX,tempY);
                ((Bugs) backBoard[tempX][tempY]).moved = true;
                noOfAnts--;
            }
        }
        else if(backBoard[x][y].checkMovement(x, y, x-1, y)){
            if(backBoard[x-1][y] instanceof Ants){
                tempX = x-1;
                tempY = y;
                ((Bugs)backBoard[x][y]).starveRound = 0;
                move(x,y,tempX,tempY);
                ((Bugs) backBoard[tempX][tempY]).moved = true;
                noOfAnts--;
            }
        }
        else if(backBoard[x][y].checkMovement(x, y, x, y+1)) {
            if (backBoard[x][y + 1] instanceof Ants) {
                tempX = x;
                tempY = y + 1;
                ((Bugs) backBoard[x][y]).starveRound = 0;
                move(x,y,tempX,tempY);
                ((Bugs) backBoard[tempX][tempY]).moved = true;
                noOfAnts--;
            }
        }
        else if(backBoard[x][y].checkMovement(x, y, x, y-1)) {
             if (backBoard[x][y - 1] instanceof Ants) {
                tempX = x;
                tempY = y - 1;
                ((Bugs) backBoard[x][y]).starveRound = 0;
                 move(x,y,tempX,tempY);
                 ((Bugs) backBoard[tempX][tempY]).moved = true;
                noOfAnts--;
            }
        }
        else if(backBoard[x][y].checkMovement(x, y, x+random.nextInt(2)-1, y+random.nextInt(2)-1)){
            tempX = x+random.nextInt(2)-1;
            tempY = y+random.nextInt(2)-1;
            if(backBoard[tempY][tempY] == null) {
                ((Bugs)backBoard[x][y]).starveRound++;
                move(x,y,tempX,tempY);
                ((Bugs) backBoard[tempX][tempY]).moved = true;
            }
            else{
                ((Bugs)backBoard[x][y]).starveRound++;
            }
        }

    }


    //ants movement
    public void AntsMove() {
        int noOfMovedAnts = 0;
        while (noOfMovedAnts < noOfAnts) {
                for(int y = 0; y < 20; y++){
                    for(int x = 0; x< 20; x++) {
                        if (backBoard[x][y] instanceof Ants) {
                            if (!((Ants) backBoard[x][y]).moved) {
                                tempX = x + random.nextInt(2) - 1;
                                tempY = y + random.nextInt(2) - 1;

                                if (backBoard[x][y].checkMovement(x, y, tempX, tempY)) {
                                    if (backBoard[tempX][tempY] == null) {

                                            move(x, y, tempX, tempY);
                                            ((Ants) backBoard[tempX][tempY]).moved = true;

                                    }
                                }


                                noOfMovedAnts++;
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
        Ants temp = new Ants();
        backBoard[tempX][tempY] = temp;
        backBoard[tempX][tempY].newBreed = true;
    }
    public void bugsBreed(){
        backBoard[tempX][tempY] = new Bugs();
        backBoard[tempX][tempY].newBreed = true;
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
                    if(!backBoard[x][y].newBreed) {
                        if (backBoard[x][y].checkBreed(x, y, backBoard) != null) {
                            tempX = backBoard[x][y].checkBreed(x, y, backBoard).getCoor_x();
                            tempY = backBoard[x][y].checkBreed(x, y, backBoard).getCoor_y();
                            noOfAnts++;
                            antsBreed();
                        }
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
                    if(!backBoard[x][y].newBreed) {
                        if (backBoard[x][y].checkBreed(x, y, backBoard) != null) {
                            tempX = backBoard[x][y].checkBreed(x, y, backBoard).getCoor_x();
                            tempY = backBoard[x][y].checkBreed(x, y, backBoard).getCoor_y();
                            noOfBugs++;
                            bugsBreed();
                        }
                    }
                }
            }
        }
    }

    public void resetBreed(){
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if(backBoard[x][y] != null){
                    backBoard[x][y].newBreed = false;
                }
            }
        }
    }

    /**************************************************************************/


    //CATEGORY : CONNECTOR
    //send the 2d array GUI
    public void sendArray (){

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

