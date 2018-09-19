package hw2;

public class Event implements Comparable<Event> {

    private int precedence = 0;
    private double timeOccurred = 0.0;
    private double timeEndured = 0.0;
    private double timeWaited = 0.0;
    private double timeFinished = 0.0;

    public Event(double timeOccurred, double timeEndured) {
        this.timeOccurred = timeOccurred;
        this.timeEndured = timeEndured;
        this.timeFinished = timeOccurred + timeEndured;
    }

    public Event(double timeOccurred, double timeEndured, double timeWaited) {
        this.timeOccurred = timeOccurred;
        this.timeEndured = timeEndured;
        this.timeWaited = timeWaited;
        this.timeFinished = timeOccurred + timeEndured + timeWaited;
    }


    public double getTimeOccurred() {
        return timeOccurred;
    }

    public double getTimeEndured() {
        return timeEndured;
    }

    public double getTimeWaited() {
        return timeWaited;
    }

    public double getTimeFinished() {
        return timeFinished;
    }



    @Override
    public int compareTo(Event otherEvent){
        int result = 0;
        
        if (this.timeOccurred < otherEvent.getTimeOccurred()) {
            result = -1;
        } else if (this.timeOccurred == otherEvent.getTimeOccurred()) {
            result = this instanceof Arrival ? -1 : 1;
        } else {
            result = 1;
        }
        return result;
    }

}