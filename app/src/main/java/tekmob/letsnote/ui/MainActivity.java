package tekmob.letsnote.ui;

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
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void buttonClick() {
        Log.d("button", "pressed");
        eventBus.post(new GetDummyEvent());
    }

    public void onEvent(GetDummyResultEvent event) {
        String test = event.getDummyModel().getHost();
        Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
    }
}
