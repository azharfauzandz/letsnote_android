package tekmob.letsnote.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import tekmob.letsnote.R;
import tekmob.letsnote.helper.LNSession;
import tekmob.letsnote.helper.Validator;

/**
 * Created by azhardz on 10/2/16.
 */

public class RegisterActivity extends BaseActivity {
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

    }

    private void registerUser(final String name, final String email, final String password){
        String TAG = "Register User: ";

    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(),
                message, Toast.LENGTH_LONG)
                .show();
    }
}




