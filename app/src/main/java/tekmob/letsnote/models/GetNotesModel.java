package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

import tekmob.letsnote.events.GetNotesEvent;

/**
 * Created by feaza on 10/9/2016.
 */

public class GetNotesModel {
    @SerializedName("id_users")
    String idUsers;
    @SerializedName("id_notes")
    String idNotes;

    public GetNotesModel(GetNotesEvent event) {
        this.idUsers = event.getIdUsers();
        this.idNotes = event.getIdNotes();
    }

    public String getIdNotes() {
        return idNotes;
    }

    public String getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public void setIdNotes(String idNotes) {
        this.idNotes = idNotes;
    }
}
