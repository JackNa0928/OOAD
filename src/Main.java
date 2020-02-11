import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                board[x][y].addActionListener();
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

interface Oraganism {
    //create data

    // create functions for implement
    public void movement();
    public void breed(int counter);
    public boolean death(int counter);
    public int stepCounter();
}

class Ants implements Oraganism{

    @Override
    public void movement() {

    }

    @Override
    public void breed(int counter) {

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
    public void movement() {

    }

    @Override
    public void breed(int counter) {

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