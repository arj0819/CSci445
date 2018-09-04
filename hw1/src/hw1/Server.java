package hw1;

public class Server {

    private boolean occupied = false;
    private Patient patientBeingServed;

    public Server() {
        
    }

    public void service(Patient patient) {
        this.patientBeingServed = patient;
        occupied = true;
    }

    public Patient discharge() {
        occupied = false;
        return patientBeingServed;
    }

    public boolean isOccupied() {
        return occupied;
    }

}
        

