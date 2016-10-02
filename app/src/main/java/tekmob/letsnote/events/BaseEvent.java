package tekmob.letsnote.events;

/**
 * Created by feaza on 9/27/2016.
 */
public class BaseEvent {
    public static final int OK = 1;
    public static final int FAIL = 0;

    protected int status;
    protected String message;

    protected BaseEvent() {
    }

    protected BaseEvent(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
