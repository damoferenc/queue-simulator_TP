public class Task implements Comparable<Task>{

    private int arrivalTime;
    private int serviceTime;
    private int startTime;
    private int endTime;
    private int id;


    public Task(int arrivalTime, int serviceTime){
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.startTime = 0;
        this. endTime = 0;
    }

    public void setStartTime(int startTime){
        this.startTime = startTime;
    }

    public void setEndTime(int endTime){
        this.endTime = endTime;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public int getServiceTime(){
        return serviceTime;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public int compareTo(Task o) {
        return this.arrivalTime - o.getArrivalTime();
    }

    public String toString(){
        return "(" + this.getId() + "," + this.getArrivalTime() + "," + this.getWaitingTime() + ")";
    }

    public int getWaitingTime(){
        return this.startTime - this.arrivalTime;
    }
}
