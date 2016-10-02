package tekmob.letsnote.network.interfaces;

import retrofit.Callback;
import retrofit.http.GET;
import tekmob.letsnote.models.DummyModel;

/**
 * Created by feaza on 9/27/2016.
 */
public interface IDummy {
    @GET("/")
    void getDummy(Callback<DummyModel> callback);
}
