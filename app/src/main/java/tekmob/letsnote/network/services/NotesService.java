package tekmob.letsnote.network.services;

import android.content.Context;

import retrofit.Callback;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.network.LNClient;
import tekmob.letsnote.network.interfaces.INotes;

/**
 * Created by feaza on 10/2/2016.
 */
public class NotesService {
    public static void createNotes(NotesModel notesModel, Callback<String> callback, Context context) {
        INotes notes = LNClient.getClient().getRestAdapter().create(INotes.class);
        notes.postNotes(notesModel, callback);
    }
}
