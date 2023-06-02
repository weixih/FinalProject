import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
public class GUIWindow extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel dogImage;
    private JPanel mainPanel;
    private JLabel timer;
    private JLabel questionNum;

    private Timer countUpTimer;

    private ActionListener timerListener;
    private int seconds;

    private String time;

    private Dog allDogs;
    private ArrayList<String> dogList;

    private int questions = 5;

    private int correctButton;

    private boolean buttonClicked;

    private int correct;

    private endScreen endScreen;

    private JLabel info;

    private JLabel info2;

    public GUIWindow(){
        endScreen = new endScreen();
        info = endScreen.getTime();
        info2 = endScreen.getCorrect();
        allDogs = new Dog();
        allDogs.importAllDogs();
        dogList = allDogs.getDogList();
        buttonClicked = false;
        countUpTimer = new Timer(1000, null);

        setContentPane(mainPanel);
        setTitle("Dog Quiz");
        setSize(850, 850);
        setLocation(300, 0);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        countUpTimer.addActionListener(this);
        countUpTimer.start();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source instanceof Timer) {
            changeTime();

        }else if (source instanceof JButton) {
            JButton button = (JButton) source;
            if(correctButton == 1){
                if (button.equals(button1)) {
                    correct++;
                    buttonClicked = true;
                }
            }
            if(correctButton == 2){
                if(button.equals(button2)) {
                    correct++;
                    buttonClicked = true;
                }
            }
            if(correctButton == 3){
                if(button.equals(button3)) {
                    correct++;
                    buttonClicked = true;
                }
            }
            if(correctButton == 4){
                if(button.equals(button4)) {
                    correct++;
                    buttonClicked = true;
                }
            }

            buttonClicked = true;

        }
    }

    public void play(){
        int count = 0;

        while(count < questions){
            try {
                String change = allDogs.changeImageURL();
                System.out.println(change);
                URL imageURL = new URL(change);
                BufferedImage image = ImageIO.read(imageURL);
                Image resizedImage = image.getScaledInstance(480, 480, Image.SCALE_DEFAULT);
                ImageIcon icon = new ImageIcon(resizedImage);
                dogImage.setIcon(icon);
                dogImage.setSize(500,500);

            } catch (IOException e) { }

            int correctButton = (int)(Math.random() * 4) + 1;
            System.out.println(correctButton);
            this.correctButton = correctButton;
            String correctDog = allDogs.getChosenDog();
            String dog1 = dogList.get((int)(Math.random() * dogList.size()));
            String dog2 = dogList.get((int)(Math.random() * dogList.size()));
            String dog3 = dogList.get((int)(Math.random() * dogList.size()));


            while(dog1.equals(correctDog)){
                dog1 = dogList.get((int)(Math.random() * dogList.size()));
            }
            while(dog2.equals(correctDog) || dog2.equals(dog1)){
                dog2 = dogList.get((int)(Math.random() * dogList.size()));
            }
            while(dog3.equals(correctDog) || dog3.equals(dog1) || dog3.equals(dog2)) {
                dog3 = dogList.get((int)(Math.random() * dogList.size()));
            }

            if(correctButton == 1){
                button1.setText(correctDog);
                button2.setText(dog1);
                button3.setText(dog2);
                button4.setText(dog3);
            }
            if(correctButton == 2){
                button1.setText(dog1);
                button2.setText(correctDog);
                button3.setText(dog2);
                button4.setText(dog3);
            }
            if(correctButton == 3){
                button1.setText(dog1);
                button2.setText(dog2);
                button3.setText(correctDog);
                button4.setText(dog3);
            }
            if(correctButton == 4){
                button1.setText(dog1);
                button2.setText(dog2);
                button3.setText(dog3);
                button4.setText(correctDog);
            }
            questionNum.setText("               " + Integer.toString(count+1) + ".");
            count++;

            buttonClicked = false;


            while(buttonClicked == false && count != 0){

            }

        }

        System.out.println(correct);
        info.setText("Time completed: " + time);
        info2.setText("Amount correct : " + correct);
        setVisible(false);
        endScreen.setVisible(true);
    }

    private void changeTime(){
        seconds++;
        int min = 0;
        int sec = 0;
        if(seconds< 60){
            min = 0;
            sec = seconds;
        }else{
            min = seconds/60;
            sec = seconds%60;
        }
        if(sec < 10){
            timer.setText(min + ":0" + sec);
            time = min + ":0" + sec;
        }else{
            timer.setText(min + ":" + sec);
            time = min + ":" + sec;
        }

    }


}
