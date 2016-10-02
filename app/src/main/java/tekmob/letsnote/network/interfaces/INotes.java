package tekmob.letsnote.network.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import tekmob.letsnote.models.NotesModel;

/**
 * Created by feaza on 10/2/2016.
 */
public interface INotes {
    @POST("/upload.php")
    void postNotes(
            @Body NotesModel notesModel,
            Callback<String> callback
    );
}
