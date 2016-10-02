package tekmob.letsnote.events;

/**
 * Created by feaza on 10/2/2016.
 */
public class CreateNoteResultEvent extends BaseEvent {
    private String string;

    public CreateNoteResultEvent(boolean success, String dummyModel) {
        status = success ? OK : FAIL;
        this.string = dummyModel;
    }

    public String getString() {
        return string;
    }
}
