package hw1;

import java.util.List;
import java.util.ArrayList;

public class Triage extends CareArea {

    public static final int NUM_OF_SERVERS = 1;

    public Triage(double expectedAvgInterArrivalTimePerPatient, double expectedAvgServiceTimePerPatient, int maxQueueLength) {
        super(expectedAvgInterArrivalTimePerPatient, 
              expectedAvgServiceTimePerPatient, 
              maxQueueLength, 
              NUM_OF_SERVERS);
    }

}