package tekmob.letsnote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import tekmob.letsnote.R;
import tekmob.letsnote.events.GetDummyResultEvent;

public class NotesDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);
    }

    public void onEvent(GetDummyResultEvent event) {
        String test = event.getDummyModel().getHost();
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
    }
}
