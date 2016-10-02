package tekmob.letsnote.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by feaza on 10/2/2016.
 */
public class DummyClient {
    private static DummyClient dummyClient;
    private RestAdapter restAdapter;

    private DummyClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://headers.jsontest.com")
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public static DummyClient getClient() {
        if(dummyClient == null)
            dummyClient = new DummyClient();
        return dummyClient;
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }
}
