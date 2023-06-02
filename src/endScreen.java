import javax.swing.*;

public class endScreen extends JFrame{

    private JPanel mainPanel;
    private JLabel correct;
    private JLabel time;

    public endScreen(){
        setContentPane(mainPanel);
        setTitle("Dog Quiz");
        setSize(850, 850);
        setLocation(300, 0);
    }
    public JLabel getTime(){
        return time;
    }

    public JLabel getCorrect(){
        return correct;
    }

}
