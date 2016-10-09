package tekmob.letsnote.managers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tekmob.letsnote.events.CreateNoteEvent;
import tekmob.letsnote.events.CreateNoteResultEvent;
import tekmob.letsnote.events.GetDummyEvent;
import tekmob.letsnote.events.GetDummyResultEvent;
import tekmob.letsnote.events.GetNotesEvent;
import tekmob.letsnote.models.DummyModel;
import tekmob.letsnote.models.GetNotesModel;
import tekmob.letsnote.models.GetNotesResultModel;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.models.ResponseModel;
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
        Log.d("on manager", "create note event");
        Callback<ResponseModel> callback = new Callback<ResponseModel>() {
            @Override
            public void success(ResponseModel message, Response response) {
                Log.d("callback", "success");
                eventBus.post(new CreateNoteResultEvent(true, response.toString()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("callback", "fail");
                Log.d("error", error.toString());
                eventBus.post(new CreateNoteResultEvent(false, "no"));
            }
        };
        NotesService.createNotes(new NotesModel(event), callback, context);
    }

    public void onEvent(GetNotesEvent event) {
        Log.d("on manager", "getnotes");
        Callback<GetNotesResultModel> callback = new Callback<GetNotesResultModel>() {
            @Override
            public void success(GetNotesResultModel getNotesResultModel, Response response) {
                Log.d("callback", "get notes success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("callback", "get notes failed");
            }
        };
        NotesService.getNotes(new GetNotesModel(event), callback, context);
    }
}
