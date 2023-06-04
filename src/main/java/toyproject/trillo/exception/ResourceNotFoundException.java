package toyproject.trillo.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final String INVALID_TRAVELLER_ID = "Check if deviceId is valid";
    public static final String INVALID_TRIP_ID = "Check if dataSpecId is valid";
    public static final String INVALID_SCHEDULE_ID = "Check if dataProtocolId is valid";
    public static final String INVALID_DAY_ID = "Make sure your request is correct";

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
