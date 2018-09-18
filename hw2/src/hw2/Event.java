package hw2;

public class Event {

    //Event Precedence constants used in the
    //event PriorityQueue during a compare
    public static final int EVENT_ARRIVE = 1;
    public static final int EVENT_DEPART = 2;

    private int precedence = 0;
    private double timeOccurred = 0.0;
    private double timeEndured = 0.0;

    public double getTimeOccurred() {
        return timeOccurred;
    }

    public double getTimeEndured() {
        return timeEndured;
    }



    @Override
    public int compareTo(Event otherEvent){
        int result = 0;
        
        if (this.timeOccurred < otherEvent.getTimeOccurred()) {
            result = -1;
        } else if (this.eventTime == otherEvent.getEventTime()) {
            result = (this.precedence == Event.EVENT_ARRIVE ? -1 : 1);
        } else {
            result = 1;
        }
        return result;
    }

}