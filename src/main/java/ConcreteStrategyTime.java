import java.util.List;

public class ConcreteStrategyTime implements Strategy {


    @Override
    public void addTask(List<Server> servers, Task task, int currentTime) {
        int minWaitingTime = servers.get(0).getWaitingTime();
        Server serverWithMinWaitingTime = servers.get(0);
        for( Server server : servers){
            if(server.getWaitingTime() < minWaitingTime){
                serverWithMinWaitingTime = server;
                minWaitingTime = server.getWaitingTime();
            }
        }
        serverWithMinWaitingTime.addTask(task);
    }
}
