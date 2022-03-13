import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerService;
    private int timeLimit;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerService, int timeLimit){
        this.maxNoServers = maxNoServers;
        this.maxTasksPerService = maxTasksPerService;
        this.timeLimit = timeLimit;
        servers = new ArrayList<Server>(maxNoServers);
        for(int i = 0; i < maxNoServers; i ++){
            Server server = new Server(this.timeLimit);
            servers.add(server);
            Thread t = new Thread(server);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task task, int currentTime){
        strategy.addTask(servers, task, currentTime);
    }

    public List<Server> getServers(){
        return this.servers;
    }

    public String getStateString(int currentTime){
        String stateString = "";
        int i = 0;
        for(Server server : servers){
            stateString = stateString + "Queue " + (i + 1) + ": ";
            i++;
            if(server.getTasks().isEmpty() && server.getCurrentTask() == null){
                stateString += "closed\n";
            }
            else{
                if(server.getCurrentTask() != null){
                    stateString = stateString + "(" + server.getCurrentTask().getId() + "," +
                            server.getCurrentTask().getArrivalTime() + "," + server.getCurrentTask().getServiceTime() + ") ";
                }
                for(Task task : server.getTasks()){
                    stateString = stateString + "(" + task.getId() + "," + task.getArrivalTime() + "," + task.getServiceTime()
                            + ") ";
                }
                stateString += "\n";
            }
            //stateString += server.getWaitingTime();
            //stateString += "\n";
        }
        return stateString;
    }

    public void stop(){
        for(Server server : servers){
            server.stop();
        }
    }
}
