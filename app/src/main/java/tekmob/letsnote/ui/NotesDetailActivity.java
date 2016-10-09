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
import tekmob.letsnote.events.GetDummyResultEvent;
import tekmob.letsnote.events.GetNotesEvent;
import tekmob.letsnote.events.GetNotesResultEvent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_detail)
    public void buttonDetail(){
        eventBus.post(new GetNotesEvent("4"));
    }

    public void onEvent(GetNotesResultEvent event) {
        Log.d("event", "get notes result");
        if(event.getStatus() == event.OK) {
            GetNotesResultModel model = event.getGetNotesResultModel();
            notesTitle.setText(model.getTitle());
            notesDescription.setText(model.getDescription());
            notesPrice.setText(model.getPrice());
            notesLink.setText(model.getPhotoLink());
        }
        else {
            Toast.makeText(this, "get failed", Toast.LENGTH_SHORT).show();
        }

    }
}
