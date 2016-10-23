package tekmob.letsnote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

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
import tekmob.letsnote.network.VolleySingleton;

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
    @Bind(R.id.notes_image)
    ImageView notesImage;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);
        ButterKnife.bind(this);
        volleySingleton=VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
        LNSession session = new LNSession(getApplicationContext());
        String idNotes = getIntent().getStringExtra("NOTES_ID");
        String idUser = session.getUserId();
        eventBus.post(new GetNotesEvent(idUser, idNotes));
        showDialog();
    }

    public void onEvent(GetNotesResultEvent event) {
        Log.d("event", "get notes result");
        hideDialog();
        if (event.getStatus() == event.OK) {
            GetNotesResultModel model = event.getGetNotesResultModel();
            notesTitle.setText(model.getTitle());
            notesDescription.setText(model.getDescription());
            notesPrice.setText(model.getPrice());
            notesLink.setText(model.getPhotoLink());
            notesUsercoin.setText(model.getCoinBalance());
            if (model.getPhotoLink() != null) {
                imageLoader.get(model.getPhotoLink(), new ImageLoader.ImageListener() {

                    @Override
                    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                        Log.d("masuk on response", "a");
                        notesImage.setImageBitmap(response.getBitmap());
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("masuk error response", "a");
                        //Log.d("eror gambar",error.getMessage());
                        notesImage.setImageResource(R.mipmap.ic_launcher);
                    }


                });
            } else {
                Toast.makeText(this, "get failed", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
