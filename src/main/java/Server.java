import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private Task currentTask;
    private AtomicInteger waitingPeriod;
    private boolean stop;
    private int currentTime;

    public Server(int timeLimit){
        tasks = new ArrayBlockingQueue<Task>(1000);
        waitingPeriod = new AtomicInteger(0);
        stop = false;
    }

    public void addTask(Task task){
        this.tasks.add(task);
        waitingPeriod.getAndIncrement();
    }

    @Override
    public void run() {
        while(!stop){
            try{
                if(waitingPeriod.get() != 0){
                    Task task = tasks.take();
                    currentTask = task;
                    task.setStartTime(this.currentTime);
                    Thread.sleep(task.getServiceTime()*1000);
                    task.setEndTime(this.currentTime);
                    currentTask = null;
                    waitingPeriod.getAndDecrement();
                }
            }
            catch(InterruptedException e){

            }
        }
    }

    public void stop(){
        stop = true;
    }

    public void setCurrentTime(int currentTime){
        this.currentTime = currentTime;
    }

    public int getWaitingTime(){
        int waitingTime = 0;
        for(Task task : this.tasks){
                waitingTime += task.getServiceTime();
        }
        if(this.currentTask != null){
            waitingTime = waitingTime +this.currentTask.getServiceTime() - (currentTime - this.currentTask.getStartTime());
        }
        return waitingTime;
    }

    public int getNumberOfTasks(){
        return this.tasks.size();
    }

    public BlockingQueue<Task> getTasks(){
        return this.tasks;
    }

    public Task getCurrentTask(){
        return this.currentTask;
    }

    public boolean done(){
        if(this.waitingPeriod.get() != 0){
            return false;
        }
        else {
            return true;
        }
    }

    public int getWaitingPeriod(){
        return this.waitingPeriod.get();
    }
}
