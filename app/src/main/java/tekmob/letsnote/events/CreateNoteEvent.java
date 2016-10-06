package tekmob.letsnote.events;

/**
 * Created by feaza on 10/2/2016.
 */
public class CreateNoteEvent extends BaseEvent {
    private final String idUsers;
    private final String title;
    private final String description;
    private final String price;
    private final String image;

    public CreateNoteEvent(String idUsers, String title, String description, String price, String image) {
        this.idUsers = idUsers;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
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
}
