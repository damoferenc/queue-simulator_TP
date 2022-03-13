import javax.swing.*;

public class SimulationFrame extends JFrame {
    private JPanel contentPane;
    private JLabel[][] servers;
    private JPanel[] panels;
    private JPanel panel;
    private JLabel[] tasks;
    private JPanel taskPanel;
    private int numberOfServers;
    private int maxNumberOfClients;
    private int totalClients;

    public SimulationFrame(int numberOfServers, int maxNumberOfClients, int totalClients){
        this.numberOfServers = numberOfServers;
        this.maxNumberOfClients =maxNumberOfClients;
        this.totalClients = totalClients;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        tasks = new JLabel[totalClients + 1];
        taskPanel = new JPanel();
        for(int i = 0; i < totalClients + 1; i ++){
            tasks[i] = new JLabel();
            taskPanel.add(tasks[i]);
        }
        servers = new JLabel[numberOfServers][maxNumberOfClients + 1];
        for(int i = 0; i < numberOfServers; i++){
            for(int j = 0; j < maxNumberOfClients + 1; j++){
                if(j == 0){
                    servers[i][j] = new JLabel("Server " + (i + 1));
                }
                else {
                    servers[i][j] = new JLabel(i + "_" + j);
                }
            }
        }
        panels = new JPanel[numberOfServers];
        panel = new JPanel();
        for(int i = 0 ; i < numberOfServers; i ++){
            panels[i] = new JPanel();
            for(int j = 0; j < maxNumberOfClients + 1; j ++){
                panels[i].add(servers[i][j]);
            }
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS));
            panel.add(panels[i]);
        }
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        contentPane = new JPanel();
        contentPane.add(taskPanel);
        contentPane.add(panel);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        this.setContentPane(contentPane);
    }

    public void setValue(int server, int task, String text){
        this.servers[server][task + 1].setText(text);
    }

    public void setTask(int i, String text){
        this.tasks[i].setText(text);
    }

    public void resetAll(){
        for(int i=0; i < numberOfServers ; i++){
            for(int j = 0 ; j < maxNumberOfClients+1; j++){
                this.servers[i][j].setText("");
            }
        }
        for(int i=0; i < this.totalClients; i++){
            this.tasks[i+1].setText("");
        }
    }
}
