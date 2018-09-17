
package hw1;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class EventScheduler {

    private Queue<Event> eventQueue = new PriorityQueue<Event>();

    private List<Event> arrivalList = new ArrayList<Event>();
    private List<Event> dischargeList = new ArrayList<Event>();
    private List<Double> waitTimeList = new ArrayList<Double>();

    private double expectedAvgInterArrivalTime;
    private double expectedAvgServiceTime;

    private double currentMinute = 0;
    private double waitTime = 0;

    private int maxDischarges = 0;
    private int dischargeCounter = 0;
    private int eventSequenceNumber = 0;

    private static Random rand = new Random();

    public EventScheduler(double expectedAvgInterArrivalTime, double expectedAvgServiceTime, int maxDischarges) {
        this.expectedAvgInterArrivalTime = expectedAvgInterArrivalTime;
        this.expectedAvgServiceTime = expectedAvgServiceTime;
        this.maxDischarges = maxDischarges;
    }

    public boolean generateNextEventSequence() {
        boolean reachedMaxDischarges = false;
        
        Event nextArrival = new Event("Arrival", getNextArrivalTime(), currentMinute);
        if (eventSequenceNumber == 0) {
            nextArrival.setWaitTime(0.0);
        } else {
            waitTime = dischargeList.get(eventSequenceNumber - 1).getEventTime() - nextArrival.getEventTime();
            if (waitTime <= 0) {
                waitTime = 0.0;
            }
            nextArrival.setWaitTime(waitTime);
            //System.out.println(waitTime);
        }
        waitTimeList.add(waitTime);


        arrivalList.add(nextArrival);

        //currentMinute = nextArrival.getEventTime();
        double nextDischargeTime = getNextDischargeTime(nextArrival.getEventTime()) + waitTime;

        Event nextDischarge = new Event("Discharge", nextDischargeTime, nextArrival.getEventTime());
        dischargeList.add(nextDischarge);

        eventQueue.offer(nextArrival);
        eventQueue.offer(nextDischarge);

        currentMinute = arrivalList.get(eventSequenceNumber).getEventTime();
        eventSequenceNumber++;
        //Event[] arrivalDischargePair = {nextArrival,nextDischarge};

        List<Event> dischargeCheckList = new ArrayList<Event>(eventQueue);

        for (int i = 0; i < dischargeCheckList.size(); i++){
            if (!dischargeCheckList.get(i).getEventType().equals("Discharge")) {
                dischargeCounter++;
            }
        }

        if (dischargeCounter == maxDischarges) {
            reachedMaxDischarges = true;
        } else {
            dischargeCounter = 0;
        }

        return reachedMaxDischarges;
    }

    public Event getNextEvent() {
        return eventQueue.element();
    }

    public double getNextArrivalTime() {
        return generateEventDuration(expectedAvgInterArrivalTime) + currentMinute;
    }

    public double getNextDischargeTime(double associatedArrivalTime) {
        return generateEventDuration(expectedAvgServiceTime) + associatedArrivalTime;
    }

    public double generateEventDuration(double expectedMean) {
        return Math.log(1-rand.nextDouble())/(-1/expectedMean);
    }

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }

    public void printEventLists() {
        for (int i = 0; i < arrivalList.size(); i++) {
            System.out.println("\nArrival "+(i+1)+"\nTime Occurred: "+arrivalList.get(i).getEventTime()+
                               "\nInter-arrival Time: "+arrivalList.get(i).getEventDuration()+
                               "\nWait Time: "+arrivalList.get(i).getWaitTime());
        }
        for (int i = 0; i < dischargeList.size(); i++) {
            System.out.println("\nDischarge "+(i+1)+"\nTime Occurred: "+dischargeList.get(i).getEventTime()+
                               "\nService Time: "+dischargeList.get(i).getEventDuration());
        }
    }

    public void printActualAvgInterArrivalTime() {
        double totalInterArrivalTime = 0.0;
        double avgInterArrivalTime = 0.0;

        for (int i = 0; i < arrivalList.size(); i++) {
            totalInterArrivalTime += arrivalList.get(i).getEventDuration();
        }
        avgInterArrivalTime = totalInterArrivalTime / arrivalList.size();

        System.out.printf("Average Inter-arrival Time: %.3f\n", avgInterArrivalTime);
    }

    public void printActualAvgServiceTime() {
        double totalServiceTime = 0.0;
        double avgServiceTime = 0.0;

        for (int i = 0; i < dischargeList.size(); i++) {
            totalServiceTime += dischargeList.get(i).getEventDuration();
        }
        avgServiceTime = totalServiceTime / dischargeList.size();

        System.out.printf("Average Service Time: %.3f\n", avgServiceTime);
    }

    public void printAvgWaitTime() {
        double totalWaitTime = 0.0;
        double avgWaitTime = 0.0;

        for (int i = 0; i < waitTimeList.size(); i++) {
            totalWaitTime += waitTimeList.get(i);
        }
        avgWaitTime = totalWaitTime / waitTimeList.size();

        System.out.printf("Average Wait Time: %.3f\n", avgWaitTime);
    }


}