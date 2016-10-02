package tekmob.letsnote.managers;

import android.content.Context;
import android.util.Log;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tekmob.letsnote.events.GetDummyEvent;
import tekmob.letsnote.events.GetDummyResultEvent;
import tekmob.letsnote.models.DummyModel;
import tekmob.letsnote.network.services.DummyService;

/**
 * Created by feaza on 9/27/2016.
 */
public class DummyManager {
    private final Context context;
    private final EventBus eventBus = EventBus.getDefault();

    public DummyManager(final Context context) {
        this.context = context;
    }

    public void onEvent(GetDummyEvent event) {
        Log.d("post", "event");
        Callback<DummyModel> callback = new Callback<DummyModel>() {
            @Override
            public void success(DummyModel dummyModel, Response response) {
                eventBus.post(new GetDummyResultEvent(true, dummyModel));
            }

            @Override
            public void failure(RetrofitError error) {
                eventBus.post(new GetDummyResultEvent(false, "no"));
            }
        };
        DummyService.getDummyModel(callback, context);
    }
}
