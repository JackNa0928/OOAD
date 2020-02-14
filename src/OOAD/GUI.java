package OOAD;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class GUI {

  JButton[][] btn;

  JFrame frame = new JFrame("ANT & BUGS");
    private String Counter;

    public GUI(){

    //panel board

    TestBoard();

  }

  public void TestBoard(){

    JPanel topBoard = new JPanel(new BorderLayout());
    JButton startBtn = new JButton ("START"); //start btn
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
        frame.add(btn[x][y]);
        gameBoard.add(btn[x][y]);
      //  btn[x][y].addActionListener(new MyActionListener());
      }
    }
    frame.add(topBoard,BorderLayout.NORTH);
		frame.add(gameBoard,BorderLayout.CENTER);
		frame.setSize(600,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  public static void main(String[] args){
    new GUI();
  }
}

