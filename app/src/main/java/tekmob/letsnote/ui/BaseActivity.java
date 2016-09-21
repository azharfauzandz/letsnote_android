package tekmob.letsnote.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;
import tekmob.letsnote.R;

/**
 * Created by feaza on 9/21/2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected EventBus eventBus = EventBus.getDefault();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        eventBus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventBus.unregister(this);
    }

    protected void showGeneralAlert(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
//        alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.tangerine));
    }

    /**
     * Create error message with one button
     */
    public void showErrorAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.alert_title)
                .setMessage(message)
                .setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.show();
//        alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(getResources().getColor(R.color.tangerine));
    }

    /**
     * Show a indeterminate, non cancelable, dialog box with message defined in R.string.loading_message
     */
    public void showDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.loading_message), true, false);
        }
    }

    /**
     * Hide loading dialog shown with showDialog()
     */
    public void hideDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
