package tekmob.letsnote.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
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
    Button notesPrice;
    @Bind(R.id.notes_image)
    ImageView notesImage;
    @Bind(R.id.notes_owner)
    TextView notesOwner;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);
        ButterKnife.bind(this);
        initToolbar();
        volleySingleton=VolleySingleton.getsInstance();
        imageLoader=volleySingleton.getImageLoader();
        LNSession session = new LNSession(getApplicationContext());
        String idNotes = getIntent().getStringExtra("NOTES_ID");
        String idUser = session.getUserId();
        eventBus.post(new GetNotesEvent(idUser, idNotes));
        showDialog();
    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Deskripsi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_return_button);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(GetNotesResultEvent event) {
        Log.d("event", "get notes result");
        hideDialog();
        if (event.getStatus() == event.OK) {
            GetNotesResultModel model = event.getGetNotesResultModel();
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
                notesTitle.setText(model.getTitle());
                notesDescription.setText(model.getDescription());
                String notePrice = model.getPrice() + " Coins";
                notesPrice.setText(notePrice);
                String noteOwner = "By " + getIntent().getStringExtra("NOTES_AUTHOR");
                notesOwner.setText(noteOwner);
            } else {
                Toast.makeText(this, "get failed", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
