import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable{

    private int timeLimit;
    private int minProcessingTime;
    private int maxProcessingTime;
    private int minArrivingTime;
    private int maxArrivingTime;
    private int numberOfServers;
    private int numberOfClients;
    private int maxNumberOfClients;
    private SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks;
    private List<Task> allTasks;

    public SimulationManager(int timeLimit, int minProcessingTime, int maxProcessingTime, int minArrivingTime,
                             int maxArrivingTime, int numberOfServers, int numberOfClients){
        this.timeLimit = timeLimit;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minArrivingTime  = minArrivingTime;
        this.maxArrivingTime = maxArrivingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.maxNumberOfClients = this.numberOfClients / this.numberOfServers;
        this.scheduler = new Scheduler(this.numberOfServers, this.numberOfClients, this.timeLimit);
        scheduler.changeStrategy(selectionPolicy);
        this.frame = new SimulationFrame(this.numberOfServers, this.maxNumberOfClients, this.numberOfClients);
        frame.setVisible(true);
        setGenerateNRandomTasks();
        allTasks = new ArrayList<Task>(this.numberOfClients);
        for(Task task : this.generatedTasks){
            this.allTasks.add(task);
        }
    }

    private void setGenerateNRandomTasks(){
        generatedTasks = new ArrayList<Task>(numberOfClients);
        for(int i = 0; i < this.numberOfClients; i ++){
            generatedTasks.add(new Task((int) (Math.random() * (this.maxArrivingTime - this.minArrivingTime + 1) +
                    this.minArrivingTime),
                    (int) (Math.random() * (this.maxProcessingTime - this.minProcessingTime + 1) + this.minProcessingTime)));
        }
        Collections.sort(generatedTasks);
        int i = 0;
        for(Task task : generatedTasks){
            task.setId(i++);
        }
    }

    @Override
    public void run() {
        int currentTime = 0;
        boolean quit = false;
        int peakHour = 0;
        int maxWaitingClients = 0;
        while(currentTime < this.timeLimit && !quit){
            int i = 0;
            boolean stay = true;
            while(stay && i < generatedTasks.size()){
                if(this.generatedTasks.get(i).getArrivalTime() == currentTime){
                    this.scheduler.dispatchTask(generatedTasks.get(i), currentTime);
                    i ++;
                }
                else{
                    stay = false;
                }
            }
            for(int j = 0;j < i; j++){
                this.generatedTasks.remove(0);
            }
            try{
                FileWriter writer = new FileWriter("log.txt", true);
                writer.write("Time " + currentTime + "\n");
                writer.write("Waiting clients: ");
                for(Task task : generatedTasks){
                    writer.write("(" + task.getId() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ") ");
                }
                writer.write("\n");
                writer.write(scheduler.getStateString(currentTime));
                writer.write("\n");
                writer.close();
            }
            catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            for(int j = 0; j < numberOfServers; j ++){
                if(scheduler.getServers().get(j).getCurrentTask() != null){
                    frame.setValue(j, 0, scheduler.getServers().get(j).getCurrentTask().toString());
                }
                else{
                    frame.setValue(j, 0, "");
                }
                int k = 1;
                for(Task task : scheduler.getServers().get(j).getTasks()){
                    frame.setValue(j, k++, task.toString());
                }
                for(int l = k; l < maxNumberOfClients; l++){
                    frame.setValue(j ,l, "");
                }
            }
            frame.setTask(0, String.valueOf(currentTime));
            int k = 0;
            for(Task task : this.generatedTasks){
                frame.setTask(k+1, task.toString());
                k++;
            }
            for(int l = k; l < numberOfClients; l++){
                frame.setTask(l + 1, "");
            }
            int waitingClients = 0;
            for(Server server : scheduler.getServers()){
                waitingClients += server.getWaitingPeriod();
            }
            if(waitingClients > maxWaitingClients){
                maxWaitingClients = waitingClients;
                peakHour = currentTime;
            }
            currentTime++;
            for(Server server : scheduler.getServers()){
                server.setCurrentTime(currentTime);
            }
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){

            }
            if(this.generatedTasks.size() == 0){
                quit = true;
            }
            boolean done = true;
            for(Server server : scheduler.getServers()){
                if(!server.done()){
                    done = false;
                }
            }
            if(!done){
                quit = false;
            }
        }
        scheduler.stop();
        double averageWaitingTime = 0;
        double averageServiceTime = 0;
        for(Task task : this.allTasks){
            averageWaitingTime += (double)task.getWaitingTime();
            averageServiceTime += (double)task.getServiceTime();
        }
        averageWaitingTime /= this.allTasks.size();
        averageServiceTime /= this.allTasks.size();
        try{
            FileWriter writer = new FileWriter("log.txt", true);
            writer.write("Average waiting time: " + averageWaitingTime + "\n");
            writer.write("Average service time: " + averageServiceTime + "\n");
            writer.write("Peak hour: " + peakHour + "\n");
            writer.close();
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        frame.resetAll();
    }
}
