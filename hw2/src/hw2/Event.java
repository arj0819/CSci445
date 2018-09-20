package hw2;

public class Event implements Comparable<Event> {

    private double timeArrived = 0.0;
    private double timeDeparted = 0.0;
    
    private String location = "";

    private double serviceTime = 0.0;
    private double interArrivalTime = 0.0;
    private double waitTime = 0.0;

    private static int numberOfWaits = 0;
    private static double totalWaitTime = 0.0;

    public Event(double timeDeparted, String location) {
        this.timeDeparted = timeDeparted;
        this.location = location;
    }

    public Event(double timeArrived, double interArrivalTime, double serviceTime, double waitTime, String location) {
        this.timeArrived = timeArrived;
        this.interArrivalTime = interArrivalTime;
        this.waitTime = waitTime;
        this.timeDeparted = timeArrived + waitTime;
        this.location = location;
        if (waitTime > 0.0) {
            numberOfWaits++;
            totalWaitTime += waitTime;
        }
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

    public String getLocation() {
        return location;
    }

    public static int getNumOfWaits() {
        return numberOfWaits;
    }

    public static double getAverageWaitTime() {
        if (numberOfWaits != 0) {
            return totalWaitTime/numberOfWaits;
        } else {
            return numberOfWaits;
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