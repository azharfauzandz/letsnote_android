package tekmob.letsnote.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by feaza on 9/21/2016.
 */
public class LNClient {
    private static LNClient lnClient;
    private RestAdapter restAdapter;

    private LNClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://headers.jsontest.com")
                .setClient(new OkClient(okHttpClient))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public static LNClient getClient() {
        if(lnClient == null)
            lnClient = new LNClient();
        return lnClient;
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }
}
