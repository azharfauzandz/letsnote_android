package tekmob.letsnote.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
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

public class CreateNoteActivity extends BaseActivity {

    @Bind(R.id.title)
    EditText title;
    @Bind(R.id.description)
    EditText description;
    @Bind(R.id.price)
    EditText price;

    //temporary until login is implemented
    String idUserTemp = "3";

    Bitmap uploadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        ButterKnife.bind(this);
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
        String titleString = title.getText().toString();
        String descriptionString = description.getText().toString();
        String priceString = price.getText().toString();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        uploadImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte b [] = baos.toByteArray();
        String base64String = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("posting", "createnoteevent");
        eventBus.post(new CreateNoteEvent(idUserTemp, titleString, descriptionString, priceString, base64String));
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
            Toast.makeText(CreateNoteActivity.this, "success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(CreateNoteActivity.this, "fail", Toast.LENGTH_SHORT).show();
        }
    }
}
