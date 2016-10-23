package tekmob.letsnote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tekmob.letsnote.R;
import tekmob.letsnote.app.LNApplication;
import tekmob.letsnote.events.GetDummyResultEvent;
import tekmob.letsnote.events.GetNotesEvent;
import tekmob.letsnote.events.GetNotesResultEvent;
import tekmob.letsnote.helper.LNSession;
import tekmob.letsnote.models.GetNotesResultModel;

public class NotesDetailActivity extends BaseActivity {
    @Bind(R.id.notes_title)
    TextView notesTitle;
    @Bind(R.id.notes_description)
    TextView notesDescription;
    @Bind(R.id.notes_price)
    TextView notesPrice;
    @Bind(R.id.notes_link)
    TextView notesLink;
    @Bind(R.id.notes_usercoin)
    TextView notesUsercoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);
        ButterKnife.bind(this);
        LNSession session = new LNSession(getApplicationContext());
        String idNotes = getIntent().getStringExtra("NOTES_ID");
        String idUser = session.getUserId();
        eventBus.post(new GetNotesEvent(idUser, idNotes));
        showDialog();
    }

    public void onEvent(GetNotesResultEvent event) {
        Log.d("event", "get notes result");
        hideDialog();
        if(event.getStatus() == event.OK) {
            GetNotesResultModel model = event.getGetNotesResultModel();
            notesTitle.setText(model.getTitle());
            notesDescription.setText(model.getDescription());
            notesPrice.setText(model.getPrice());
            notesLink.setText(model.getPhotoLink());
            notesUsercoin.setText(model.getCoinBalance());
        }
        else {
            Toast.makeText(this, "get failed", Toast.LENGTH_SHORT).show();
        }

    }
}
