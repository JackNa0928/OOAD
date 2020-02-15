package OOAD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {

  JLabel[][] btn;
  JButton e;


  public JFrame frame = new JFrame("ANT & BUGS");
  public int counter;
  public JButton startBtn = new JButton ("START"); //start btn
  public JButton entBtn = new JButton();


  public GUI(){
    //panel board

    TestBoard();
  }

  public void TestBoard(){

    JPanel topBoard = new JPanel(new BorderLayout());



    JLabel label =new JLabel();
    label.setText ( "    Round : "+ counter );
    topBoard.add(startBtn, BorderLayout.WEST);
    topBoard.add(label, BorderLayout.CENTER);
    topBoard.add(entBtn, BorderLayout.CENTER);


    JPanel gameBoard = new JPanel(new GridLayout(20,20));
    btn= new JLabel[20][20];
    for(int y=0;y<20;y++){
      for(int x=0;x<20;x++){
        btn[x][y]= new JLabel();
        btn[x][y].setBackground(Color.gray);
        frame.add(btn[x][y]);
        gameBoard.add(btn[x][y]);
      }
    }


    frame.add(topBoard,BorderLayout.NORTH);
		frame.add(gameBoard,BorderLayout.CENTER);
		frame.setSize(600,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }
    /*
      public static void main(String[] args){
        new GUI();
      }
    */


    public void getListener(ActionListener MyActionListener, ActionListener MyKeyListener){
      startBtn.addActionListener(MyActionListener);
      entBtn.addActionListener(MyKeyListener);
    }



}
