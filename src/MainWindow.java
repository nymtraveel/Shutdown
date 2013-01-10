import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Florian
 * Date: 10.01.13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class MainWindow extends JFrame {

    private MainWindow instance;
    private JTextField hoursTextField;
    private JTextField minutesTextField;
    private JTextField secondsTextField;
    private JPanel MainPanel;
    private JComboBox actions;
    private JButton Abort;
    private JButton Go;
    private int time=0;


    public static void main( String[] args ){

        new MainWindow();
    }

    public MainWindow() {
        instance = this;

        Abort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String options = "shutdown -a";
                performShutdown(options);
            }
        });

        Go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String hourstxt = hoursTextField.getText();
                String minstxt = minutesTextField.getText();
                String secstxt = secondsTextField.getText();

                int hours=0;
                int secs=0;
                int mins=0;

                try{hours=Integer.parseInt(hourstxt);}
                catch(NumberFormatException e){}

                try{mins=Integer.parseInt(minstxt);}
                catch(NumberFormatException e){}

                try{secs=Integer.parseInt(secstxt);}
                catch(NumberFormatException e){}

                time = hours*60*60+mins*60+secs;

                String options;
                options= "shutdown";
                switch (actions.getSelectedIndex()) {
                    case 0:  options += " -s";
                        break;
                    case 1:   options += " -r";
                        break;
                    case 2:   options += " -h";
                        break;
                }
                options +=" -t " + time;

                performShutdown(options);
            }
        });



        setContentPane(MainPanel);
        setTitle("Shutdown Control");
        setSize(300,300);
        setLocation(500,250);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void performShutdown(String actions) {
        try{
            Runtime.getRuntime().exec(actions);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
