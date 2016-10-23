package tekmob.letsnote.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import de.greenrobot.event.EventBus;
import tekmob.letsnote.managers.DummyManager;
import tekmob.letsnote.managers.NotesManager;

/**
 * Created by feaza on 9/21/2016.
 * Initialize and register managers here
 */
public class LNApplication extends Application {
    private DummyManager dummyManager;
    private NotesManager notesManager;
    private EventBus eventBus = EventBus.getDefault();
    private static LNApplication sInstance;

    public static final String TAG = LNApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static LNApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        dummyManager = new DummyManager(this);
        notesManager = new NotesManager(this);

        eventBus.register(dummyManager);
        eventBus.register(notesManager);
        //sInstance=this;
        mInstance = this;
    }

    public static synchronized LNApplication getInstance() {
        return mInstance;
    }

    public static LNApplication getsInstance() {
        return sInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
