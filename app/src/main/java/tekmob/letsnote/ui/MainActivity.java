package tekmob.letsnote.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tekmob.letsnote.R;
import tekmob.letsnote.events.GetDummyEvent;
import tekmob.letsnote.events.GetDummyResultEvent;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("activity","main activity");
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void buttonClick() {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_2)
    public void buttonClick2() {
        Intent intent = new Intent(this, NotesDetailActivity.class);
        startActivity(intent);
    }
    
    @OnClick(R.id.timeline_button)
    public void buttonClickTimeline(){
        Intent intent = new Intent(this, Timeline.class);
        startActivity(intent);
    }

    public void onEvent(GetDummyResultEvent event) {
        String test = event.getDummyModel().getHost();
        Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
    }
}
