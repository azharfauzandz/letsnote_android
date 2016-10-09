package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

import tekmob.letsnote.events.GetNotesEvent;

/**
 * Created by feaza on 10/9/2016.
 */

public class GetNotesModel {
    @SerializedName("id_notes")
    String idNotes;

    public GetNotesModel(GetNotesEvent event) {
        this.idNotes = event.getIdNotes();
    }

    public String getIdNotes() {
        return idNotes;
    }

    public void setIdNotes(String idNotes) {
        this.idNotes = idNotes;
    }
}
