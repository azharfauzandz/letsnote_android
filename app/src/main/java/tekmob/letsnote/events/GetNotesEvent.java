package tekmob.letsnote.events;

import tekmob.letsnote.models.GetNotesModel;

/**
 * Created by feaza on 10/9/2016.
 */

public class GetNotesEvent extends BaseEvent {
    private final String idNotes;

    public GetNotesEvent(String idNotes) {
        this.idNotes = idNotes;
    }

    public String getIdNotes() {
        return idNotes;
    }
}
