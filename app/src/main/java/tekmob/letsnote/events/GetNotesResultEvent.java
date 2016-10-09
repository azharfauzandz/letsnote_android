package tekmob.letsnote.events;

/**
 * Created by feaza on 10/9/2016.
 */

public class GetNotesResultEvent extends BaseEvent {
    private final String idNotes;
    private final String idUsers;
    private final String title;
    private final String description;
    private final String price;
    private final String photoLink;

    public GetNotesResultEvent(String idNotes, String idUsers, String title, String description, String price, String photoLink) {
        this.idNotes = idNotes;
        this.idUsers = idUsers;
        this.title = title;
        this.description = description;
        this.price = price;
        this.photoLink = photoLink;
    }

    public String getIdNotes() {
        return idNotes;
    }

    public String getIdUsers() {
        return idUsers;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getPhotoLink() {
        return photoLink;
    }
}
