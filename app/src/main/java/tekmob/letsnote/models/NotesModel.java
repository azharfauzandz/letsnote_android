package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

import tekmob.letsnote.events.CreateNoteEvent;

/**
 * Created by feaza on 10/2/2016.
 */
public class NotesModel {
    @SerializedName("id_users")
    String idUsers;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("price")
    String price;
    @SerializedName("image")
    String image;

    public NotesModel(CreateNoteEvent event) {
        this.idUsers = event.getIdUsers();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.image = event.getImage();
    }

    public String getIdUsers() {
        return idUsers;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setIdUsers(String idUsers) {
        this.idUsers = idUsers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
