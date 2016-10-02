package tekmob.letsnote.events;

/**
 * Created by feaza on 10/2/2016.
 */
public class CreateNoteEvent extends BaseEvent {
    private final String image;
    private final String name;

    public CreateNoteEvent(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
