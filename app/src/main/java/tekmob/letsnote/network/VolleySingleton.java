package tekmob.letsnote.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

//import tekmob.letsnote.LNApplication;
import tekmob.letsnote.app.LNApplication;
import tekmob.letsnote.utils.MyApplication;

/**
 * Created by dananarief on 09-10-16.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance=null;
    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;
    private VolleySingleton(){
        mRequestQueue= Volley.newRequestQueue(LNApplication.getAppContext());
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private LruCache<String,Bitmap> cache=new LruCache<>((int)(Runtime.getRuntime().maxMemory()/2048)/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });


    }

    public static VolleySingleton getsInstance(){
        if(mInstance==null){
            mInstance = new VolleySingleton();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
