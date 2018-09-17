package hw1;

public class Event implements Comparable<Event> {

    private double eventTime;
    private double eventDuration;
    private double waitTime;
    private String eventType;

    public Event(String eventType, double eventTime, double currentMinute) {
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.eventDuration = eventTime - currentMinute;
    }

    public double getEventTime() {
        return eventTime;
    }

    public double getEventDuration() {
        return eventDuration;
    }

    public String getEventType() {
        return eventType;
    }

    public void setWaitTime(double waitTime) {
        this.waitTime = waitTime;
    }
    public double getWaitTime() {
        return waitTime;
    }

    @Override
    public int compareTo(Event otherEvent){
        int result = 0;
        
        if (this.eventTime < otherEvent.getEventTime()) {
            result = -1;
        } else if (this.eventTime == otherEvent.getEventTime()) {
            if (this.eventType.equals("Arrival")) {
                result = -1;
            }
        } else {
            result = 1;
        }
        return result;
    }
}