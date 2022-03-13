import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

public class SetupFrame extends JFrame {
    private JPanel contentPane;
    private JPanel timeLimitPanel;
    private JPanel minProcessingTimePanel;
    private JPanel maxProcessingTimePanel;
    private JPanel minArrivingTimePanel;
    private JPanel maxArrivingTimePanel;
    private JPanel numberOfServersPanel;
    private JPanel numberOfClientsPanel;
    private JPanel textPanel;
    private JTextField timeLimitField;
    private JTextField minProcessingTimeField;
    private JTextField maxProcessingTimeField;
    private JTextField minArrivingTimeField;
    private JTextField maxArrivingTimeField;
    private JTextField numberOfServersField;
    private JTextField numberOfClientsField;
    private JLabel timeLimitText;
    private JLabel minProcessingTimeText;
    private JLabel maxProcessingTimeText;
    private JLabel minArrivingTimeText;
    private JLabel maxArrivingTimeText;
    private JLabel numberOfServersText;
    private JLabel numberOfClientsText;
    private JButton startButton;

    public SetupFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        timeLimitText = new JLabel("Time limit: ");
        minProcessingTimeText = new JLabel("Minimum processing time: ");
        maxProcessingTimeText = new JLabel("Maximum processing time: ");
        minArrivingTimeText = new JLabel("Minimum arriving time: ");
        maxArrivingTimeText = new JLabel("Maximum arriving time: ");
        numberOfClientsText = new JLabel("Number of clients: ");
        numberOfServersText = new JLabel("Number of servers: ");

        timeLimitField = new JTextField(5);
        minProcessingTimeField = new JTextField(5);
        maxProcessingTimeField = new JTextField(5);
        minArrivingTimeField = new JTextField(5);
        maxArrivingTimeField = new JTextField(5);
        numberOfClientsField = new JTextField(10);
        numberOfServersField = new JTextField(10);

        contentPane = new JPanel();
        timeLimitPanel = new JPanel();
        minProcessingTimePanel = new JPanel();
        maxProcessingTimePanel = new JPanel();
        minArrivingTimePanel = new JPanel();
        maxArrivingTimePanel = new JPanel();
        numberOfClientsPanel = new JPanel();
        numberOfServersPanel = new JPanel();
        textPanel = new JPanel();

        timeLimitPanel.add(timeLimitText);
        timeLimitPanel.add(timeLimitField);
        timeLimitPanel.setLayout(new FlowLayout());
        minProcessingTimePanel.add(minProcessingTimeText);
        minProcessingTimePanel.add(minProcessingTimeField);
        minProcessingTimePanel.setLayout(new FlowLayout());
        maxProcessingTimePanel.add(maxProcessingTimeText);
        maxProcessingTimePanel.add(maxProcessingTimeField);
        maxProcessingTimePanel.setLayout(new FlowLayout());
        minArrivingTimePanel.add(minArrivingTimeText);
        minArrivingTimePanel.add(minArrivingTimeField);
        minArrivingTimePanel.setLayout(new FlowLayout());
        maxArrivingTimePanel.add(maxArrivingTimeText);
        maxArrivingTimePanel.add(maxArrivingTimeField);
        maxArrivingTimePanel.setLayout(new FlowLayout());
        numberOfClientsPanel.add(numberOfClientsText);
        numberOfClientsPanel.add(numberOfClientsField);
        numberOfClientsPanel.setLayout(new FlowLayout());
        numberOfServersPanel.add(numberOfServersText);
        numberOfServersPanel.add(numberOfServersField);
        numberOfServersPanel.setLayout(new FlowLayout());
        textPanel.add(timeLimitPanel);
        textPanel.add(minProcessingTimePanel);
        textPanel.add(maxProcessingTimePanel);
        textPanel.add(minArrivingTimePanel);
        textPanel.add(maxArrivingTimePanel);
        textPanel.add(numberOfClientsPanel);
        textPanel.add(numberOfServersPanel);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        startButton = new JButton("START");

        contentPane.add(textPanel);
        contentPane.add(startButton);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        this.setContentPane(contentPane);

    }

    public void addStartButtonListener(ActionListener al) {
        this.startButton.addActionListener(al);
    }

    public int getTimeLimit(){
        return Integer.parseInt(timeLimitField.getText());
    }

    public int getMinProcessingTime(){
        return Integer.parseInt(minProcessingTimeField.getText());
    }

    public int getMaxProcessingTime(){
        return Integer.parseInt(maxProcessingTimeField.getText());
    }

    public int getMinArrivingTime(){
        return Integer.parseInt(minArrivingTimeField.getText());
    }

    public int getMaxArrivingTime(){
        return Integer.parseInt(maxArrivingTimeField.getText());
    }

    public int getNumberOfServers(){
        return Integer.parseInt(numberOfServersField.getText());
    }

    public int getNumberOfClients(){
        return Integer.parseInt(numberOfClientsField.getText());
    }

}
