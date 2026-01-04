import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

class secondPanel {
    JPanel secondJPanel,topPanel,belloPanel,middPanel;
    JLabel playerName;
    JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    JButton[][] buttonArray;


    

    secondPanel(){


        btn1 = new JButton("");
        btn2 = new JButton("");
        btn3 = new JButton("");
        btn4 = new JButton("");
        btn5 = new JButton("");
        btn6 = new JButton("");
        btn7 = new JButton("");
        btn8 = new JButton("");
        btn9 = new JButton("");

        btn1.setBackground(new Color(95, 203, 217));
        btn2.setBackground(new Color(95, 203, 217));
        btn3.setBackground(new Color(95, 203, 217));
        btn4.setBackground(new Color(95, 203, 217));
        btn5.setBackground(new Color(95, 203, 217));
        btn6.setBackground(new Color(95, 203, 217));
        btn7.setBackground(new Color(95, 203, 217));
        btn8.setBackground(new Color(95, 203, 217));
        btn9.setBackground(new Color(95, 203, 217));

        btn1.setFont(new Font("Arial",Font.BOLD,30));
        btn2.setFont(new Font("Arial",Font.BOLD,30));
        btn3.setFont(new Font("Arial",Font.BOLD,30));
        btn4.setFont(new Font("Arial",Font.BOLD,30));
        btn5.setFont(new Font("Arial",Font.BOLD,30));
        btn6.setFont(new Font("Arial",Font.BOLD,30));
        btn7.setFont(new Font("Arial",Font.BOLD,30));
        btn8.setFont(new Font("Arial",Font.BOLD,30));
        btn9.setFont(new Font("Arial",Font.BOLD,30));

        btn1.setFocusPainted(false);
        btn2.setFocusPainted(false);
        btn3.setFocusPainted(false);
        btn4.setFocusPainted(false);
        btn5.setFocusPainted(false);
        btn6.setFocusPainted(false);
        btn7.setFocusPainted(false);
        btn8.setFocusPainted(false);
        btn9.setFocusPainted(false);
        
        buttonArray = new JButton[][]{{btn1,btn2,btn3},{btn4,btn5,btn6},{btn7,btn8,btn9}};


        secondJPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new GridBagLayout());
        belloPanel = new JPanel(new GridBagLayout());
        middPanel = new JPanel(new GridLayout(3,3));

        playerName = new JLabel("");
        playerName.setFont(new Font("Arial",Font.BOLD,25));

        secondJPanel.setLayout(new BorderLayout());
        secondJPanel.setBackground(Color.YELLOW);
        topPanel.setBackground(Color.pink);
        topPanel.setPreferredSize(new Dimension(100,70));
        belloPanel.setBackground(Color.lightGray);
        middPanel.setPreferredSize(new Dimension(300,300));

        topPanel.add(playerName);
        middPanel.add(btn1);
        middPanel.add(btn2);
        middPanel.add(btn3);
        middPanel.add(btn4);
        middPanel.add(btn5);
        middPanel.add(btn6);
        middPanel.add(btn7);
        middPanel.add(btn8);
        middPanel.add(btn9);
        belloPanel.add(middPanel);

        secondJPanel.add(topPanel,BorderLayout.NORTH);
        secondJPanel.add(belloPanel);
    }

    //methods

    // 1. methods for grid is fuleed filled or not
    public boolean filled_check(){
        int filled_count = 0;
        // 'X' 'O'
        for (int i = 0;i<3;i++){
            for (int j =0;j<3;j++){
                if ("X".equals(buttonArray[i][j].getText()) || "O".equals(buttonArray[i][j].getText())){
                    filled_count++;
                }
            }
        }
        if (filled_count == 9){
            return true;
        }
        else return false;
    }


    // 2. method for check winnig condition or lose condition
    public boolean check(){
        String pointer1 = "X";
        String Pointer2 = "O";
        int countX = 0;
        int countO = 0;
        int counterC1X=0,counterC1O=0,counterC2X=0,counterC2O=0,counterC3X=0,counterC3O=0;
        int counterDMX=0,counterDMO=0,counterDEX=0,counterDEO=0;

        for (int i =0;i<3;i++){
            for (int j =0;j<3;j++){
                if (buttonArray[i][j].getText() == pointer1) countX++;
                else if (buttonArray[i][j].getText() == Pointer2) countO++;

                if (j == 0){
                    if (buttonArray[i][j].getText() == pointer1) counterC1X++;
                    else if (buttonArray[i][j].getText() == Pointer2) counterC1O++;
                }
                else if (j == 1) {
                    if (buttonArray[i][j].getText() == pointer1) counterC2X++;
                    else if (buttonArray[i][j].getText() == Pointer2) counterC2O++;
                }
                else if (j == 2) {
                    if (buttonArray[i][j].getText() == pointer1) counterC3X++;
                    else if (buttonArray[i][j].getText() == Pointer2) counterC3O++;
                }

                if (i == j){
                    if (buttonArray[i][j].getText() == pointer1) counterDMX++;
                    else if (buttonArray[i][j].getText() == Pointer2) counterDMO++; 
                }

                if (i+j == 2){
                    if (buttonArray[i][j].getText() == pointer1) counterDEX++;
                    else if (buttonArray[i][j].getText() == Pointer2) counterDEO++;
                }
    
            }
            if (countX == 3 || countO == 3) return true;
            else {
                countX = 0;
                countO = 0;
            }
        }
        if (counterC1X==3 || counterC1O==3 || counterC2X==3 || counterC2O==3 || counterC3X==3 || counterC3O==3) return true;
        else if (counterDMX ==3 || counterDMO ==3 || counterDEX ==3 || counterDEO ==3) return true;
        else return false;
 
    }
}


class MyFrame extends JFrame implements ActionListener{

    JPanel maiPanel,topPanle,bellowPanel,playerXPanel,playerOPanel;
    JLabel topic,player1,player2;
    JButton start;
    JTextField plyXField,plyOField;

    String Xplay = null;
    String Oplay = null;

    secondPanel MainObject;

    File file,file2;
    Clip clip,clip2;

    MyFrame(){

        file = new File("ting.wav");
        file2 = new File("gameover.wav");
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip2 = AudioSystem.getClip();
            clip2.open(audioStream2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
        //clip.start();

        MainObject = new secondPanel();
        MainObject.btn1.addActionListener(this);
        MainObject.btn2.addActionListener(this);
        MainObject.btn3.addActionListener(this);
        MainObject.btn4.addActionListener(this);
        MainObject.btn5.addActionListener(this);
        MainObject.btn6.addActionListener(this);
        MainObject.btn7.addActionListener(this);
        MainObject.btn8.addActionListener(this);
        MainObject.btn9.addActionListener(this);

        plyXField = new JTextField();
        plyOField = new JTextField();
        start = new JButton("START");
        start.setForeground(Color.YELLOW);
        start.setBackground(new Color(38,30,30));
        start.setFocusPainted(false);
        start.setFont(new Font("Arial",Font.BOLD,30));

        plyXField.setForeground(Color.YELLOW);
        plyXField.setBackground(Color.BLACK);
        plyOField.setForeground(Color.YELLOW);
        plyOField.setBackground(Color.BLACK);
        plyXField.setCaretColor(Color.YELLOW);
        plyOField.setCaretColor(Color.YELLOW);
        plyXField.setFont(new Font("Arial",Font.BOLD,25));
        plyOField.setFont(new Font("Arial",Font.BOLD,25));
        plyXField.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
        plyOField.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        maiPanel = new JPanel(new GridLayout(2,1));
        topic = new JLabel();
        topic.setText("TIC-TAK-TOY");
        topic.setForeground(Color.WHITE);
        topic.setFont(new Font("Arial",Font.BOLD,50));

        player1 = new JLabel();
        player1.setText("Enter Player(x) name :");
        player1.setForeground(Color.WHITE);
        player1.setFont(new Font("Arial",Font.BOLD,20));
        player2 = new JLabel();
        player2.setText("Enter Player(O) name :");
        player2.setForeground(Color.WHITE);
        player2.setFont(new Font("Arial",Font.BOLD,20));

        playerXPanel = new JPanel(new GridLayout(1,2));
        playerOPanel = new JPanel(new GridLayout(1,2));
        topPanle = new JPanel(new GridBagLayout());//then add something it is add very middle of the panel
        bellowPanel = new JPanel(new GridLayout(3,1));
        topPanle.setBackground(new Color(38, 30, 30));
        bellowPanel.setBackground(new Color(38, 30, 30));
        playerXPanel.setBackground(new Color(38, 30, 30));
        playerOPanel.setBackground(new Color(38, 30, 30));
        topPanle.add(topic);

        playerXPanel.add(player1);
        playerXPanel.add(plyXField);
        playerOPanel.add(player2);
        playerOPanel.add(plyOField);

        bellowPanel.add(playerXPanel);
        bellowPanel.add(playerOPanel);
        bellowPanel.add(start);





        maiPanel.add(topPanle);
        maiPanel.add(bellowPanel);

        start.addActionListener(this);

        this.add(maiPanel);
    }

    public void actionPerformed(ActionEvent e) {

        //secondPanel objectCur= new secondPanel();

        if (e.getSource() == start) {
            //this.maiPanel.setLayout(new BorderLayout());

            if (plyXField.getText().isBlank() || plyOField.getText().isBlank()){
                JOptionPane.showMessageDialog(null,"Please Enter Names!" , "Names Not Entered." , JOptionPane.OK_OPTION);
            }
            else {
                //System.out.println(plyXField.getText()+"k");
                //System.out.println(plyOField.getText()+"k");
                MainObject.playerName.setText(plyXField.getText() + "  (X)");
                Xplay = "X";
                this.getContentPane().removeAll();
                this.add(MainObject.secondJPanel);
                this.revalidate();//again set layouts
                this.repaint();// again draw frame
            } 
        }
        else {

            int curi = 0;
            int curj = 0;

            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (MainObject.buttonArray[i][j] == e.getSource()) {
                        curi = i;
                        curj = j;
                        break; 
                    }
                    else continue;
                }
            }            

            if ("X".equals(Xplay)){

                if ("X".equals(MainObject.buttonArray[curi][curj].getText()) || "O".equals(MainObject.buttonArray[curi][curj].getText())){
                    JOptionPane.showMessageDialog(null,"Already Filled ! \n\nTry Again Correctly !" , "Already Filled Grid" , JOptionPane.OK_OPTION);
                }
                else {
                    playSound();
                    MainObject.buttonArray[curi][curj].setText("X");
                    Xplay = null;
                    if (MainObject.check()){
                        playSound2();
                        int num = JOptionPane.showConfirmDialog(null,plyXField.getText() + "  You Won !\n\n Try Again ?" , "Congratulation!" , JOptionPane.OK_CANCEL_OPTION);
                        //System.out.println(num); //ok == 0 cansel == 2
                        if (num == 0 || num == -1) {
                            for (int k=0;k<3;k++){
                                for (int l=0;l<3;l++){
                                    MainObject.buttonArray[k][l].setText("");
                                }
                            }
                            plyXField.setText("");
                            plyOField.setText("");
                            this.getContentPane().removeAll();
                            this.add(maiPanel);
                            this.revalidate();
                            this.repaint();
                            


                        }
                        else if (num == 2){
                            plyXField.setText("");
                            plyOField.setText("");
                            System.exit(0);
                        }
                    }
                    else if (MainObject.filled_check()){
                        //System.out.println("GridFilled");
                        playSound2();
                        int num = JOptionPane.showConfirmDialog(null,"Grid is Filled!\n\nTry Again ?" , "Match Drwan!" , JOptionPane.OK_CANCEL_OPTION);
                        if (num == 0 || num == -1) {
                            for (int k=0;k<3;k++){
                                for (int l=0;l<3;l++){
                                    MainObject.buttonArray[k][l].setText("");
                                }
                            }
                            plyXField.setText("");
                            plyOField.setText("");
                            this.getContentPane().removeAll();
                            this.add(maiPanel);
                            this.revalidate();
                            this.repaint();
                            


                        }
                        else if (num == 2){
                            plyXField.setText("");
                            plyOField.setText("");
                            System.exit(0);
                        }
                    }
                    else {
                        Oplay = "O";
                        MainObject.playerName.setText(plyOField.getText() + "  (O)");
                    }
                }
            }

            else if ("O".equals(Oplay)){

                if ( "X".equals(MainObject.buttonArray[curi][curj].getText()) || "O".equals(MainObject.buttonArray[curi][curj].getText())){
                    JOptionPane.showMessageDialog(null,"Already Filled ! \n\nTry Again Correctly !" , "Already Filled Grid" , JOptionPane.OK_OPTION);
                }
                else {
                    playSound();
                    MainObject.buttonArray[curi][curj].setText("O");
                    Oplay = null;
                    if (MainObject.check()){
                        //System.out.println("win O");
                        playSound2();
                        int num = JOptionPane.showConfirmDialog(null,plyOField.getText() + "  You Won !\n\n Try Again ?" , "Congratulation!" , JOptionPane.OK_CANCEL_OPTION);
                        //System.out.println(num); //ok == 0 cansel == 2
                        if (num == 0 || num == -1) {
                            for (int k=0;k<3;k++){
                                for (int l=0;l<3;l++){
                                    MainObject.buttonArray[k][l].setText("");
                                }
                            }
                            plyXField.setText("");
                            plyOField.setText("");
                            this.getContentPane().removeAll();
                            this.add(maiPanel);
                            this.revalidate();
                            this.repaint();
                            


                        }
                        else if (num == 2){
                            plyXField.setText("");
                            plyOField.setText("");
                            System.exit(0);
                        }
                    }
                    else if (MainObject.filled_check()){
                        //System.out.println("GridISFilled");
                        playSound2();
                        int num = JOptionPane.showConfirmDialog(null,"Grid is Filled!\n\nTry Again ?" , "Match Drwan!" , JOptionPane.OK_CANCEL_OPTION);
                        if (num == 0 || num == -1) {
                            for (int k=0;k<3;k++){
                                for (int l=0;l<3;l++){
                                    MainObject.buttonArray[k][l].setText("");
                                }
                            }
                            plyXField.setText("");
                            plyOField.setText("");
                            this.getContentPane().removeAll();
                            this.add(maiPanel);
                            this.revalidate();
                            this.repaint();
                            


                        }
                        else if (num == 2){
                            plyXField.setText("");
                            plyOField.setText("");
                            System.exit(0);
                        }
                    }
                    else {
                        Xplay = "X";
                        MainObject.playerName.setText(plyXField.getText() + "  (X)");
                    }
                }
            }

        }


    }


    private void playSound() {
        if (clip != null) {
            clip.stop();              
            clip.setFramePosition(0); 
            clip.start();             
        }
    }

    private void playSound2() {
        if (clip2 != null) {
            clip2.stop();              
            clip2.setFramePosition(0); 
            clip2.start();             
        }
    }

}



public class Main {
    public static void main(String[] args) {


        MyFrame frame = new MyFrame();

        frame.setVisible(true);

        frame.setTitle("TIK-TAK-TOY");

        frame.setSize(600,600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);




    }
}


