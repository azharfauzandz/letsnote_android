package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

import tekmob.letsnote.events.GetNotesResultEvent;

/**
 * Created by feaza on 10/9/2016.
 */

public class GetNotesResultModel {
    @SerializedName("id_notes")
    String idNotes;
    @SerializedName("id_users")
    String idUsers;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("price")
    String price;
    @SerializedName("photo_link")
    String photoLink;

    public String getIdNotes() {
        return idNotes;
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

    public String getPhotoLink() {
        return photoLink;
    }

    public void setIdNotes(String idNotes) {
        this.idNotes = idNotes;
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

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
