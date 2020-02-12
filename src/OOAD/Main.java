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
    private Ants a =new Ants();
    private Bugs b = new Bugs();

    Game(){
        for(int k = 0; k < 20; k++){
            for (int j = 0; j < 20 ; j++) {
                backBoard[j][k] = 0;
            }
        }
        Random random = new Random();
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
    Game(int x, int y){
        boolean validMove = new a.movement(x,y);
    }

}

interface Oraganism {
    //create data

    // create functions for implement
    public boolean movement(int x, int y);
    public boolean breed(int counter);
    public boolean death(int counter);
    public int stepCounter();
}

class Ants implements Oraganism{

    public boolean movement(int x, int y) {
        if ( backBoard [x-1][y] == null){
            return true;
        }
        else if ( backBoard [x+1][y] == null){
            return true;
        }
        else if ( backBoard [x][y-1] == null){
            return true;
        }
        else if ( backBoard [x][y+1] == null){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean breed(int counter) {
        if ( backBoard [x-1][y-1] == null){
            return true;
        }
        else if ( backBoard [x+1][y+1] == null){
            return true;
        }
        else if ( backBoard [x+1][y-1] == null){
            return true;
        }
        else if ( backBoard [x-1][y+1] == null){
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
    public boolean movement(int x, int y) {
        if ( backBoard [x-1][y] == null){
            return true;
        }
        else if ( backBoard [x+1][y] == null){
            return true;
        }
        else if ( backBoard [x][y-1] == null){
            return true;
        }
        else if ( backBoard [x][y+1] == null){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean breed(int counter) {
        if ( backBoard [x-1][y-1] == null){
            return true;
        }
        else if ( backBoard [x+1][y+1] == null){
            return true;
        }
        else if ( backBoard [x+1][y-1] == null){
            return true;
        }
        else if ( backBoard [x-1][y+1] == null){
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

