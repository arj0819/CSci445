package hw2;

public class Event implements Comparable<Event> {

    private double timeArrived = 0.0;
    private double timeDeparted = 0.0;
    
    private double serviceTime = 0.0;
    private double interArrivalTime = 0.0;
    private double waitTime = 0.0;

    public Event(double timeDeparted) {
        this.timeDeparted = timeDeparted;
    }

    public Event(double timeArrived, double interArrivalTime, double serviceTime, double waitTime) {
        this.timeArrived = timeArrived;
        this.interArrivalTime = interArrivalTime;
        this.waitTime = waitTime;
        this.timeDeparted = timeArrived + waitTime;
    }


    public double getTimeArrived() {
        return timeArrived;
    }

    public double getTimeDeparted() {
        return timeDeparted;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public double getInterArrivalTime() {
        return interArrivalTime;
    }

    public double getWaitTime() {
        return waitTime;
    }

    public double getTimeOccurred() {
        if (this instanceof Arrival) {
            return timeArrived;
        } else {
            return timeDeparted;
        }
    }


    @Override
    public int compareTo(Event otherEvent){
        int result = 0;
        
        if (this.getTimeOccurred() < otherEvent.getTimeOccurred()) {
            result = -1;
        } else if (this.getTimeOccurred() == otherEvent.getTimeOccurred()) {
            result = this instanceof Arrival ? -1 : 1;
        } else {
            result = 1;
        }
        return result;
    }

}