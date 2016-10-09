package tekmob.letsnote.network.services;

import android.content.Context;
import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import tekmob.letsnote.events.GetNotesResultEvent;
import tekmob.letsnote.models.GetNotesModel;
import tekmob.letsnote.models.GetNotesResultModel;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.models.ResponseModel;
import tekmob.letsnote.network.LNClient;
import tekmob.letsnote.network.interfaces.INotes;

/**
 * Created by feaza on 10/2/2016.
 */
public class NotesService {
    public static void createNotes(NotesModel notesModel, Callback<ResponseModel> callback, Context context) {
        Log.d("on service", "createnotes");
        INotes notes = LNClient.getClient().getRestAdapter().create(INotes.class);
        notes.postNotes(notesModel.getIdUsers(), notesModel.getTitle(), notesModel.getDescription(), notesModel.getPrice(), notesModel.getImage(), callback);
    }

    public static void getNotes(GetNotesModel getNotesModel, Callback<GetNotesResultModel> callback, Context context) {
        Log.d("on service", "getnotes");
        INotes notes = LNClient.getClient().getRestAdapter().create(INotes.class);
        notes.getDetail(getNotesModel.getIdNotes(), callback);
    }
}
