package tekmob.letsnote.models;

import com.google.gson.annotations.SerializedName;

import tekmob.letsnote.events.CreateNoteEvent;

/**
 * Created by feaza on 10/2/2016.
 */
public class TimelineModel {
    @SerializedName("image")
    String image;
    @SerializedName("name")
    String name;

    int id_notes;
    int id_users;
    String owner;
    String title;
    String description;
    double price;
    String photoLink;

//    public TimelineModel(CreateNoteEvent event) {
//        this.image = event.getImage();
//        this.name = event.getName();
//    }

    public TimelineModel(int id_notes,int id_users, String owner, String title, String description, int price, String photoLink){
        this.id_notes=id_notes;
        this.id_users=id_users;
        this.owner=owner;
        this.title=title;
        this.description=description;
        this.price=price;
        this.photoLink=photoLink;
    }



    public int getId_notes() {
        return id_notes;
    }

    public void setId_notes(int id_notes) {
        this.id_notes = id_notes;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
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
