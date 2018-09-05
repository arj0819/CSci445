package hw1;

public interface Hospitable {

    public Patient receivePatient();
    public boolean servicePatient(Server server, Patient nextPatient);
    public void dischargePatient(Server server);

}