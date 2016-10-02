package tekmob.letsnote.network.services;

import android.content.Context;

import retrofit.Callback;
import tekmob.letsnote.models.DummyModel;
import tekmob.letsnote.network.LNClient;
import tekmob.letsnote.network.interfaces.IDummy;

/**
 * Created by feaza on 9/27/2016.
 */
public class DummyService {
    public static void getDummyModel(Callback<DummyModel> callback, Context context) {
        IDummy dummy = LNClient.getClient().getRestAdapter().create(IDummy.class);
        dummy.getDummy(callback);
    }
}
