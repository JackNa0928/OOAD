package OOAD;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Connector {

    private GUI gui = new GUI();
    private Game game = new Game();
    private ImageIcon ant = ant();
    private ImageIcon bug = bug();
    private ImageIcon ant(){
        //resizes any images
        Image image = new ImageIcon("ant.png").getImage();
        Image scaledImage = image.getScaledInstance(132, 132,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    private ImageIcon bug(){
        //resizes any images
        Image image = new ImageIcon("bug.png").getImage();
        Image scaledImage = image.getScaledInstance(132, 132,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    //2d array
    //start btn
    //enter btn
    //function game()
    //fucntion gameflow()

    Connector(){
        gui.getListener(startBtnListener, enterBtnKeyListener);


    }

    ActionListener startBtnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("first row in start button actionlistener");
            game = new Game();
            gui.counter = 0;
            gui.counter++;
            gui.startBtn.setEnabled(false);
            getArray(game.backBoard);
            System.out.println("last row in start button actionlistener");
            for(int y=0;y<20;y++){
                for(int x=0;x<20;x++){
                    gui.btn[x][y]= new JLabel();
                    gui.btn[x][y].setBackground(Color.gray);
                    gui.frame.add(gui.btn[x][y]);
                }
            }
        }
    };
    ActionListener enterBtnKeyListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("first row in enter button actionlistener");

            //if (e.getKeyCode()==KeyEvent.VK_ENTER){
                System.out.println("first row in enter button actionlistener");
             //   gui.entBtn.doClick();
                gui.counter++;
                game.gameFlow();
                getArray(game.backBoard);
            System.out.println("last row in enter button actionlistener");
                for(int y=0;y<20;y++){
                    for(int x=0;x<20;x++){
                        gui.btn[x][y]= new JLabel();
                        gui.btn[x][y].setBackground(Color.gray);
                        gui.frame.add(gui.btn[x][y]);
                    }
                }
            //}
        }
    };  //get 2d array and set Icon
    public void getArray(Organism[][] backBoard){
        System.out.println("getArray first line");
        for (int y = 0; y < 20; y++){
            for(int x = 0; x < 20; x++){
                System.out.println("getArray inside loop");
                if(backBoard[x][y]  == null ){
                    gui.btn[x][y].setIcon(null);
                    System.out.println("null Icon Set");
                }
                else if(backBoard[x][y] instanceof Ants){
                    try {
                        Image img = ImageIO.read(getClass().getResource("ant.png"));
                        gui.btn[x][y].setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    //gui.btn[x][y].setIcon(ant);
                    System.out.println("ant Icon Set");
                }
                else{
                    try {
                        Image img = ImageIO.read(getClass().getResource("bug.png"));
                        gui.btn[x][y].setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    //gui.btn[x][y].setIcon(bug);
                    System.out.println("bug Icon Set");
                }
            }
        }
    }

    public static void main(String[] args){
        new Connector();
    }

}


