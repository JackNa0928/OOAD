package OOAD;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements ActionListener,KeyListener{

  JButton[][] btn;
  JButton e;



  private int Counter;
  boolean gameStarted = false;
  private Game game;
  private ImageIcon ant = new ImageIcon("ant.png");
  private ImageIcon bug = new ImageIcon("bug.png");

  public GUI(){
    super("ANT & BUGS");
    game = new Game();
    //panel board

    TestBoard();
  }

  public void TestBoard(){

    JPanel topBoard = new JPanel(new BorderLayout());
    JButton startBtn = new JButton ("START"); //start btn
    startBtn.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        game.sendArray();
        startBtn.setEnabled(false);
        //enterKey.enabled(true);
        gameStarted = true;
      }
    });

    JLabel label =new JLabel();
    label.setText ( "    Round : "+ Counter );
    topBoard.add(startBtn, BorderLayout.WEST);
    topBoard.add(label, BorderLayout.CENTER);


    JPanel gameBoard = new JPanel(new GridLayout(20,20));
    btn= new JButton[20][20];
    for(int y=0;y<20;y++){
      for(int x=0;x<20;x++){
        btn[x][y]=new JButton();
        btn[x][y].setBackground(Color.blue);
        add(btn[x][y]);
        gameBoard.add(btn[x][y]);
      }
    }
    add(topBoard,BorderLayout.NORTH);
		add(gameBoard,BorderLayout.CENTER);
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  public static void main(String[] args){
    new GUI();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getKeyCode()==KeyEvent.VK_ENTER){

      game.gameFlow();
      game.gameEnd();
      game.sendArray();

    }
  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  //get 2d array and set Icon
  public void getArray(Organism[][] backBoard){
    for (int y = 0; y < 20; y++){
      for(int x = 0; x < 20; x++){
        if(backBoard[x][y]  == null){
          btn[x][y].setIcon(null);
        }
        else if(backBoard[x][y] instanceof Ants){
          btn[x][y].setIcon(ant);
        }
        else{
          btn[x][y].setIcon(bug);
        }
      }
    }
  }

}

