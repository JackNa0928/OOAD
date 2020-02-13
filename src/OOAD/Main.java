package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class GameBoard extends JFrame implements ActionListener {
    // create the data
    public JButton[][] board = new JButton[20][20];
    //default constructor
    GameBoard(){
        super("Bugs and Ants");
        render();
    }

    // create render();
    public void render(){
        JPanel topBoard = new JPanel(new BorderLayout());
        JLabel label = new JLabel();
        label.setText("Round : " /* + String.valueOf(round)*/);
        JButton start = new JButton("Start");
        topBoard.add(start, BorderLayout.WEST);
        topBoard.add(label, BorderLayout.EAST);

        JPanel gameBoard = new JPanel(new GridLayout(20,20));
        for(int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                board[x][y] = new JButton();
                board[x][y].setBackground(Color.pink);
                board[x][y].putClientProperty("column",x);
                board[x][y].putClientProperty("row",y);
                board[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //pass data into game(int x, int y)
                    }
                });
                add(board[x][y]);
                gameBoard.add(board[x][y]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class AntsCreator extends GameBoard{

    //default constructor
    AntsCreator(){

    }
}

class BugsCreator extends GameBoard{
    //default constructor
    BugsCreator(){

    }
}

class Game{
    public int[][] backBoard =  new int[20][20];
    private int[][] deathBoard = new int[20][20];
    private Ants a =new Ants();
    private Bugs b = new Bugs();
    private Random random = new Random();
    private static int tempX = 0;
    private static int tempY = 0;
    private static int round = 0;
    Game(){
        for(int k = 0; k < 20; k++){
            for (int j = 0; j < 20 ; j++) {
                backBoard[j][k] = 0;
                deathBoard[j][k]= 0;
            }
        }

        for (int noOfAnts = 0; noOfAnts < 100; noOfAnts++){
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(backBoard[x][y] != 0){
                noOfAnts--;
            }
            else
                backBoard[x][y] = 1;
        }
        for (int noOfBugs = 0; noOfBugs < 5; noOfBugs++){
            int x = random.nextInt(20);
            int y = random.nextInt(20);
            if(backBoard[x][y] != 0){
                noOfBugs--;
            }
            else
                backBoard[x][y] = 2;
        }

    }
    Game(int current_x, int current_y, int next_x, int next_y) {
        //bugs movement from user
        if(backBoard[current_x][current_y] == 2) { // bugs
            boolean validMove = b.movement(current_x,current_y,next_x,next_y,backBoard);
            if (validMove){
                backBoard[current_x][current_y] = 0;
                backBoard[next_x][next_y] = 2;
            }
        }

        //ants auto movement (not done)
        for(int y = 0; y < 20; y++){
            for (int x = 0 ; x < 20; x++){
                if (backBoard[x][y] == 1) { //ants
                    int nextAnt_x = random.nextInt(2);
                    int nextAnt_y = random.nextInt(2);
                    boolean validMove = a.movement(x, y, nextAnt_x, nextAnt_y, backBoard);// need to change the next_x and next_y to random
                    if (validMove) {
                        backBoard[x][y] = 0;
                        backBoard[nextAnt_x][nextAnt_y] = 1;
                    }
                }
            }
        }

        //ants breed
        if (round % 3 == 0){
            for(int y = 0; y < 20 ;y++){
                for(int x = 0; x < 20; x++){
                    if (backBoard[x][y] == 1){
                        boolean validbreed = a.breed(round,x,y,backBoard);
                        if(validbreed){
                            backBoard[tempX][tempY] = 1;
                        }
                    }
                }
            }
        }

        // bugs breed
        if (round % 8 == 0){
            for(int y = 0; y < 20 ;y++){
                for(int x = 0; x < 20; x++){
                    if( backBoard[x][y] == 2){
                        boolean validbreed = b.breed(round,x,y,backBoard);
                        if(validbreed){
                            backBoard[tempX][tempY] = 2;
                        }
                    }
                }
            }
        }

        //bugs death counter
        for(int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (deathBoard[x][y] == 3){
                    backBoard[x][y] = 0;
                    deathBoard[x][y] = 0;
                }
            }
        }

    }

    // get the breed location after checking validity
    public static void getLocation(int x, int y){
        tempX = x;
        tempY = y;
    }

}

interface Oraganism {
    //create data

    // create functions for implement
    boolean movement(int current_x, int current_y, int next_x, int next_y, int[][] backBoard);
    boolean breed(int counter, int current_x, int current_y, int[][] backBoard);
    boolean death(int counter);
    int stepCounter();
}

class Ants implements Oraganism{
    // the is the step valid or not
    public boolean movement(int current_x, int current_y, int next_x, int next_y, int[][] backBoard) {
        if ( backBoard [current_x-1][current_y] == 0 && current_x -1 == next_x && current_y == next_y){
            return true;
        }
        else if ( backBoard [current_x+1][current_y] == 0 && current_x + 1 == next_x && current_y == next_y){
            return true;
        }
        else if ( backBoard [current_x][current_y-1] == 0 && current_x == next_x && current_y-1 == next_y){
            return true;
        }
        else if ( backBoard [current_x][current_y+1] == 0 && current_x == next_x && current_y+1 == next_y){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean breed(int counter, int current_x, int current_y, int[][] backBoard) {
        if ( backBoard [current_x-1][current_y-1] == 0){
            Game.getLocation(current_x-1,current_y-1);
            return true;
        }
        else if ( backBoard [current_x+1][current_y+1] == 0){
            Game.getLocation(current_x+1,current_y+1);
            return true;
        }
        else if ( backBoard [current_x+1][current_y-1] == 0){
            Game.getLocation(current_x+1,current_y-1);
            return true;
        }
        else if ( backBoard [current_x-1][current_y+1] == 0){
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

    @Override
    public int stepCounter() {
        return 0;
    }
}

class Bugs implements Oraganism{

    @Override
    public boolean movement(int current_x, int current_y, int next_x, int next_y, int[][] backBoard) {
        if ( backBoard [current_x-1][current_y] != 0 && current_x -1 == next_x && current_y == next_y){
            if(backBoard [current_x-1][current_y] == 1){
                //death counter reset; deathBoard [current_x][current_y] = 0; deathBoard[next_x][next_y] = 0;
            }
            //death counter continue; else deathBoard[next_x][next_y] = deathBoard [current_x][current_y] ; deathBoard [current_x][current_y] = 0;
            return true;
        }
        else if ( backBoard [current_x+1][current_y] != 0 && current_x + 1 == next_x && current_y == next_y){
            if(backBoard [current_x+1][current_y] == 1){
                //death counter reset; deathBoard [current_x][current_y] = 0; deathBoard[next_x][next_y] = 0;
            }
            //death counter continue; else  deathBoard[next_x][next_y] = deathBoard [current_x][current_y] ; deathBoard [current_x][current_y] = 0;
            return true;
        }
        else if ( backBoard [current_x][current_y-1] != 0 && current_x == next_x && current_y-1 == next_y){
            if(backBoard [current_x][current_y-1] == 1){
                //death counter reset; deathBoard [current_x][current_y] = 0; deathBoard[next_x][next_y] = 0;
            }
            //death counter continue; deathBoard[next_x][next_y] = deathBoard [current_x][current_y] ; deathBoard [current_x][current_y] = 0;
            return true;
        }
        else if ( backBoard [current_x][current_y+1] == 0 && current_x == next_x && current_y+1 == next_y){
            if(backBoard [current_x][current_y+1] == 1){
                //death counter reset; deathBoard [current_x][current_y] = 0; deathBoard[next_x][next_y] = 0;
            }
            //death counter continue; deathBoard[next_x][next_y] = deathBoard [current_x][current_y] ; deathBoard [current_x][current_y] = 0;
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean breed(int counter, int current_x, int current_y , int[][] backBoard) {
        if ( backBoard [current_x-1][current_y-1] == 0){
            Game.getLocation(current_x-1,current_y-1);
            return true;
        }
        else if ( backBoard [current_x+1][current_y+1] == 0){
            Game.getLocation(current_x+1,current_y+1);
            return true;
        }
        else if ( backBoard [current_x+1][current_y-1] == 0){
            Game.getLocation(current_x+1,current_y-1);
            return true;
        }
        else if ( backBoard [current_x-1][current_y+1] == 0){
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

    @Override
    public int stepCounter() {
        return 0;
    }
}

