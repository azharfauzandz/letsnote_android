package tekmob.letsnote.network.interfaces;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import tekmob.letsnote.models.NotesModel;
import tekmob.letsnote.models.ResponseModel;

/**
 * Created by feaza on 10/2/2016.
 */
public interface INotes {

    @Multipart
    @POST("/createNotes.php")
    void postNotes(
            @Part("id_users") String idUsers,
            @Part("title") String title,
            @Part("description") String description,
            @Part("price") String price,
            @Part("image") String image,
            Callback<ResponseModel> callback
    );
}
