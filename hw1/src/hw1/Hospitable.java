package hw1;

public interface Hospitable {

    public Patient recievePatient();
    public boolean servicePatient(Server server, Patient nextPatient);
    public void dischargePatient(Server server);

}