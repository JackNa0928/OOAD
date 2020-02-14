package OOAD;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;


public class testgame  {

    JButton[][] btn;

    JFrame frame = new JFrame("ANT & BUGS");

    public testgame(){

        //panel board

        TestBoard();

    }

    public void TestBoard(){
        JPanel gameBoard = new JPanel(new GridLayout(20,20));
        btn= new JButton[20][20];
        for(int y=0;y<20;y++){
            for(int x=0;x<7;x++){
                btn[x][y]=new JButton();
                btn[x][y].setBackground(Color.blue);
                frame.add(btn[x][y]);
                gameBoard.add(btn[x][y]);
                btn[x][y].addActionListener(new MyActionListener());
            }
        }
        //frame.add(topBoard,BorderLayout.NORTH);
        frame.add(gameBoard,BorderLayout.CENTER);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        new testgame();
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}