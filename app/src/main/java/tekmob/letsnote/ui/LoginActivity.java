package tekmob.letsnote.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tekmob.letsnote.R;
import tekmob.letsnote.app.AppConfig;
import tekmob.letsnote.app.LNApplication;
import tekmob.letsnote.helper.LNSession;
import tekmob.letsnote.helper.Validator;

/**
 * Created by azhardz on 10/6/16.
 */

public class LoginActivity extends Activity {

    private static final String TAG = "LoginActivity";

    private LNSession session;
    private ProgressDialog pDialog;

    @Bind(R.id.input_email) EditText inputEmail;
    @Bind(R.id.input_password) EditText inputPassword;
    @Bind(R.id.btn_login) Button btnLogin;
    @Bind(R.id.link_signup) TextView linkLogin;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //Create session
        session = new LNSession(getApplicationContext());

        //Create pDialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.btn_login)
    public void submit(){

        String valEmail = Validator.isValidEmail(inputEmail.getText().toString());
        String valPass = Validator.isValidPassword(inputPassword.getText().toString());
        if(valEmail.equals("empty") || valPass.equals("empty")){
            toastMessage("There is empty");
        }else if(valEmail.equals("not valid")){
            toastMessage("Email is not valid");
        }else{
            if(valPass.equals("not valid")){
                toastMessage("Password must be 8 or more characters");
            }else{
                loginUser(inputEmail.getText().toString(), inputPassword.getText().toString());
            }
        }
    }

    @OnClick (R.id.link_signup)
    public void linkLogin(){
        Intent intent = new Intent(
                LoginActivity.this,
                RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_LONG)
                .show();
    }

    private void loginUser(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        pDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray userArray = jObj.getJSONArray("server_response");
                    JSONObject userObject = userArray.getJSONObject(0);

                    String uid = userObject.getString("id");
                    String name = userObject.getString("name");
                    String email = inputEmail.getText().toString();

                    session.createLoginSession(uid,name,email);

                    //Go To main
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "ERROR parse JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String errorStr = error.getMessage();
                if (error.networkResponse != null) {
                    try {
                        JSONObject jObj = new JSONObject(new String(error.networkResponse.data));
                        Log.e(TAG, "Login Error JSON: " + jObj);
                    } catch (JSONException e) {
                        Log.e(TAG, "Error parsing JSON");
                    }
                }
                Log.e(TAG, "Login Error: " + errorStr);
                Toast.makeText(getApplicationContext(),
                        errorStr, Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        LNApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
