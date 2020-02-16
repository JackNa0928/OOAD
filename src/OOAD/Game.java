package OOAD;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public Organism[][] backBoard= new Organism[20][20];
    private static int tempX = 0;
    private static int tempY = 0;
    private int round = 0;
    private int noOfBugs = 0;
    private int noOfAnts = 0;
    private ArrayList<Ants> antsArray = new ArrayList<Ants>();
    private ArrayList<Bugs> bugsArray = new ArrayList<Bugs>();

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
                noOfAnts++;
            	backBoard[x][y] = new Ants("Ant"+noOfAnts);
            	antsArray.add((Ants)backBoard[x][y]);
        }
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if (backBoard[x][y] != null) {
                i--;
            } else {
            	noOfBugs++;
                backBoard[x][y] = new Bugs("Bug"+noOfBugs);
                bugsArray.add((Bugs)backBoard[x][y]);
            }
        }
        

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
        System.out.println("Check bug starve status.");
        for(int i = 0 ; i < bugsArray.size() ; i++)
        {
        	System.out.println(bugsArray.get(i).name+" Kills "+bugsArray.get(i).getAntsKill());
        	System.out.println(bugsArray.get(i).name+" Starve "+bugsArray.get(i).getStarveRound());
        }
        System.out.println(round);
        System.out.println(noOfAnts);
        System.out.println(noOfBugs);
        System.out.println("Round End");
    }

    public void checkPrey(int x,int y){
        if(backBoard[x][y].checkMovement(x, y, x+1, y)){
            if(backBoard[x+1][y] instanceof Ants){
                tempX = x+1;
                tempY = y;
                ((Bugs)backBoard[x][y]).starveRound = 0;
                System.out.println("Bugs eat down");
                move(x,y,tempX,tempY);
                ((Bugs) backBoard[tempX][tempY]).moved = true;
                ((Bugs)backBoard[tempX][tempY]).plusAntsKill();
                noOfAnts--;
            }
            else if(backBoard[x][y].checkMovement(x, y, x-1, y)){
                if(backBoard[x-1][y] instanceof Ants){
                    tempX = x-1;
                    tempY = y;
                    ((Bugs)backBoard[x][y]).starveRound = 0;
                    System.out.println("Bugs eat up");
                    move(x,y,tempX,tempY);
                    ((Bugs) backBoard[tempX][tempY]).moved = true;
                    ((Bugs)backBoard[tempX][tempY]).plusAntsKill();
                    noOfAnts--;
                }
                else if(backBoard[x][y].checkMovement(x, y, x, y+1)) {
                    if (backBoard[x][y + 1] instanceof Ants) {
                        tempX = x;
                        tempY = y + 1;
                        ((Bugs) backBoard[x][y]).starveRound = 0;
                        System.out.println("Bugs eat right");
                        move(x,y,tempX,tempY);
                        ((Bugs) backBoard[tempX][tempY]).moved = true;
                        ((Bugs)backBoard[tempX][tempY]).plusAntsKill();
                        noOfAnts--;
                    }
                    else if(backBoard[x][y].checkMovement(x, y, x, y-1)) {
                        if (backBoard[x][y - 1] instanceof Ants) {
                           tempX = x;
                           tempY = y - 1;
                           ((Bugs) backBoard[x][y]).starveRound = 0;
                            System.out.println("Bugs eat left");
                            move(x,y,tempX,tempY);
                            ((Bugs) backBoard[tempX][tempY]).moved = true;
                            ((Bugs)backBoard[tempX][tempY]).plusAntsKill();
                           noOfAnts--;
                       }
                        else if(backBoard[x][y].checkMovement(x, y, x, y-1)) {
                            if (backBoard[x][y - 1] instanceof Ants) {
                               tempX = x;
                               tempY = y - 1;
                               ((Bugs) backBoard[x][y]).starveRound = 0;
                                System.out.println("Bugs eat left");
                                move(x,y,tempX,tempY);
                                ((Bugs) backBoard[tempX][tempY]).moved = true;
                                ((Bugs)backBoard[tempX][tempY]).plusAntsKill();
                               noOfAnts--;
                           }
                            else if(backBoard[x][y].checkMovement(x, y, x+random.nextInt(2)-1, y+random.nextInt(2)-1)){
                                tempX = x+random.nextInt(2)-1;
                                tempY = y+random.nextInt(2)-1;
                                if(backBoard[tempY][tempY] == null) {
                                    ((Bugs)backBoard[x][y]).starveRound++;
                                    System.out.println("bug moved to null");
                                    move(x,y,tempX,tempY);
                                    ((Bugs) backBoard[tempX][tempY]).moved = true;
                                }
                                else{
                                    System.out.println("Bugs stay same place");
                                    ((Bugs)backBoard[x][y]).starveRound++;
                                }
                            }
                       }
                   }
                }
            }
        }
        
        
        
        

    }


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

