package tekmob.letsnote.network.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import tekmob.letsnote.models.NotesModel;

/**
 * Created by feaza on 10/2/2016.
 */
public interface INotes {

    @Multipart
    @POST("/upload.php")
    void postNotes(
            @Part("image") String image,
            @Part("name") String name,
            Callback<String> callback
    );
}
