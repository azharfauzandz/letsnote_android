package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

import tekmob.letsnote.events.CreateNoteEvent;

/**
 * Created by feaza on 10/2/2016.
 */
public class NotesModel {
    @SerializedName("image")
    String image;
    @SerializedName("name")
    String name;

    public NotesModel(CreateNoteEvent event) {
        this.image = event.getImage();
        this.name = event.getName();
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
