import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task task, int currentTime) {
        int minNumberOfTasks = servers.get(0).getNumberOfTasks();
        Server serverWithMinNumberOfTasks = servers.get(0);
        for( Server server : servers){
            if(server.getNumberOfTasks() < minNumberOfTasks){
                serverWithMinNumberOfTasks = server;
            }
        }
        serverWithMinNumberOfTasks.addTask(task);
    }
}
