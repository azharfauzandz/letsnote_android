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
 * Created by azhardz on 10/2/16.
 */

public class RegisterActivity extends Activity {
    private static final String TAG = "RegisterActivity";

    private LNSession session;
    private ProgressDialog pDialog;


    @Bind(R.id.input_name) EditText inputName;
    @Bind(R.id.input_email) EditText inputEmail;
    @Bind(R.id.input_password) EditText inputPassword;
    @Bind(R.id.input_confirm_password) EditText inputConfirmPassword;
    @Bind(R.id.btn_signup) Button btnRegister;
    @Bind(R.id.link_login) TextView linkLogin;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);

        //Create session
        session = new LNSession (getApplicationContext());

        //Create pDialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        ButterKnife.bind(this);
        if(session.isLoggedIn()){
            //to main activity
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
    @OnClick (R.id.btn_signup)
    public void submit(){
        String valName = Validator.isValidName(inputName.getText().toString());
        String valEmail = Validator.isValidEmail(inputEmail.getText().toString());
        String valPass = Validator.isValidPassword(inputPassword.getText().toString());
        String valConfPass = Validator.isValidConfPassword(inputConfirmPassword.getText().toString());
        if(valName.equals("empty") || valEmail.equals("empty") || valPass.equals("empty") || valConfPass.equals("empty")){
            toastMessage("There is empty");
        }else if(valName.equals("not valid")){
            toastMessage("Name is not valid");
        }else{
            if(valEmail.equals("not valid")){
                toastMessage("Email is not valid");
            }else{
                if(valPass.equals("not valid")){
                    toastMessage("Password must be 8 or more characters");
                }else{
                    if(valConfPass.equals("not valid") && !valConfPass.equals(valPass)){
                        toastMessage("Confirm Password is not same with password");
                    }else{
                        registerUser(inputName.getText().toString(), inputEmail.getText().toString(), inputPassword.getText().toString());
                    }
                }
            }

        }
    }

    @OnClick (R.id.link_login)
    public void linkLogin(){
        Intent intent = new Intent(
                RegisterActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerUser(final String name, final String email, final String password){
        String TAG = "Register User: ";
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Response: ", "Register Response: " + response.toString());
                toastMessage("Registration Success");
                hideDialog();
                Intent intent = new Intent(
                        RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                toastMessage(error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("username", email);
                params.put("password", password);
                return params;
            }

        };

        // Adding request to request queue
        LNApplication.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_LONG)
                .show();
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




