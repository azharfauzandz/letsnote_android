package tekmob.letsnote;

import android.app.Application;

import de.greenrobot.event.EventBus;
import tekmob.letsnote.managers.DummyManager;
import tekmob.letsnote.managers.NotesManager;

/**
 * Created by feaza on 9/21/2016.
 * Initialize and register managers here
 */
public class LNApplication extends Application {
    private DummyManager dummyManager;
    private NotesManager notesManager;
    private EventBus eventBus = EventBus.getDefault();

    @Override
    public void onCreate() {
        super.onCreate();

        dummyManager = new DummyManager(this);
        notesManager = new NotesManager(this);

        eventBus.register(dummyManager);
        eventBus.register(notesManager);
    }
}
