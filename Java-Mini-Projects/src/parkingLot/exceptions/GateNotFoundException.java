package parkingLot.exceptions;

public class GateNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public GateNotFoundException(String message) {
        super(message);
    }

    public GateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GateNotFoundException(Throwable cause) {
        super(cause);
    }
}
