import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIWindow extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel dogImage;
    private JPanel mainPanel;

    private ArrayList<String> dogList;

    public GUIWindow(){
        Dog allDogs = new Dog();
        dogList = allDogs.getDogs();

        setContentPane(mainPanel);
        setTitle("Dog Quiz");
        setSize(900, 800);
        setLocation(300, 0);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;

            if (button.equals(button1)) {
                System.out.println("button1 clicked");
            } else if(button.equals(button2)){
                System.out.println("button2 clicked");
            }else if(button.equals(button3)){
                System.out.println("button3 clicked");
            }else if(button.equals(button4)){
                System.out.println("button4 clicked");
            }
        }
    }

    public void play(){
        dogImage =
    }


}
