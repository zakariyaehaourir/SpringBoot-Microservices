package ma.microservice.patientservice.exception;

public class PatientEmailExistException extends RuntimeException {
    public PatientEmailExistException(String message) {
        super(message);
    }
}
