package tekmob.letsnote.helper;

import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by azhardz on 10/4/16.
 */

public class LNSession {
    private  static String TAG = LNSession.class.getSimpleName();

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    //Sharred prefer mode
    int PRIVATE_MODE = 0;

    //Shared prefer file name
    private static final String PREF_NAME = "LetsNote";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public LNSession(Context context){
        this._context = context;
        this.pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.editor = pref.edit();
    }

    public void setLogin (boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        //Commit changes
        editor.commit();

        Log.d(TAG, "User login session modified");
    }
    public  boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

}
