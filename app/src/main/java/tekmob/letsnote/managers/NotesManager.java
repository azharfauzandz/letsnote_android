package tekmob.letsnote.managers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tekmob.letsnote.events.CreateNoteEvent;
import tekmob.letsnote.events.CreateNoteResultEvent;
import tekmob.letsnote.events.GetDummyEvent;
import tekmob.letsnote.events.GetDummyResultEvent;
import tekmob.letsnote.models.DummyModel;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.network.services.DummyService;
import tekmob.letsnote.network.services.NotesService;

/**
 * Created by feaza on 10/2/2016.
 */
public class NotesManager {
    private final Context context;
    private final EventBus eventBus = EventBus.getDefault();

    public NotesManager(final Context context) {
        this.context = context;
    }

    public void onEvent(CreateNoteEvent event) {
        Log.d("post", "create note event");
        Callback<String> callback = new Callback<String>() {
            @Override
            public void success(String string, Response response) {
                eventBus.post(new CreateNoteResultEvent(true, response.toString()));
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(new CreateNoteResultEvent(false, "no"));
            }
        };
        NotesService.createNotes(new NotesModel(event), callback, context);
    }
}
