package tekmob.letsnote.events;

/**
 * Created by feaza on 10/2/2016.
 */
public class CreateNoteResultEvent extends BaseEvent {
    private String string;

    public CreateNoteResultEvent(boolean success, String string) {
        status = success ? OK : FAIL;
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
