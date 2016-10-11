package tekmob.letsnote.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tekmob.letsnote.R;
import tekmob.letsnote.events.GetDummyEvent;
import tekmob.letsnote.events.GetDummyResultEvent;
import tekmob.letsnote.helper.LNSession;

public class MainActivity extends BaseActivity {

    private LNSession session;

    @Bind(R.id.textme)
    TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Create session
        session = new LNSession(getApplicationContext());

        if (!session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            session.logoutUser();
        }

        helloText.setText(session.getUserName());
    }

    @OnClick(R.id.button)
    public void buttonClick() {
        session.logoutUser();
    }

    public void onEvent(GetDummyResultEvent event) {
        String test = event.getDummyModel().getHost();
        Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
    }
}
