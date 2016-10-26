package tekmob.letsnote.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.RestAdapter;
import tekmob.letsnote.R;
import tekmob.letsnote.events.CreateNoteEvent;
import tekmob.letsnote.events.CreateNoteResultEvent;
import tekmob.letsnote.events.GetDummyEvent;
import tekmob.letsnote.helper.LNSession;

public class CreateNoteActivity extends BaseActivity {

    @Bind(R.id.title)
    TextInputLayout title;
    @Bind(R.id.description)
    TextInputLayout description;
    @Bind(R.id.price)
    TextInputLayout price;
    @Bind(R.id.image_preview)
    ImageView imagePreview;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    //temporary until login is implemented
    String idUserTemp = "3";

    Bitmap uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        ButterKnife.bind(this);
        initToolbar();
    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Notes");
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

    @OnClick(R.id.image_button)
    public void image() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), 1);
    }

    @OnClick(R.id.submit_notes)
    public void submit() {
        showDialog();
        LNSession session = new LNSession(getApplicationContext());
        String idUser = session.getUserId();
        String titleString = title.getEditText().getText().toString();
        String descriptionString = description.getEditText().getText().toString();
        String priceString = price.getEditText().getText().toString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        uploadImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte b [] = baos.toByteArray();
        String base64String = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("posting", "createnoteevent");
        eventBus.post(new CreateNoteEvent(idUser, titleString, descriptionString, priceString, base64String));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            onSelectFromGalleryResult(data, requestCode);
        }
    }

    private void onSelectFromGalleryResult(Intent data, int requestCode) {
        if (data != null) {
            try {
                uploadImage = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                imagePreview.setImageBitmap(uploadImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("Image", data.getData().toString());
    }

    public void onEvent(CreateNoteResultEvent event) {
        hideDialog();
        Log.d("on res event", ""+event.getStatus());
        Log.d("on res event", event.getString());
        if(!event.getString().equals("no")) {
            Toast.makeText(CreateNoteActivity.this, "Notes Created", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            Toast.makeText(CreateNoteActivity.this, "fail", Toast.LENGTH_SHORT).show();
        }
    }
}
