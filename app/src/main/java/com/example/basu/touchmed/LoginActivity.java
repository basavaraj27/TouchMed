package com.example.basu.touchmed;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mtil,ptil;
    private EditText mobilenoEditText;
    private EditText passwordEditText;
    private Button submit;
    private Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mtil= (TextInputLayout) findViewById(R.id.login_inputlayout_mobileno);
        ptil= (TextInputLayout) findViewById(R.id.login_inputlayout_pass);
        mobilenoEditText= (EditText) findViewById(R.id.login_input_mobileno);
        passwordEditText= (EditText) findViewById(R.id.input_password);
        submit= (Button) findViewById(R.id.btn_login);
        signup= (Button) findViewById(R.id.signup_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupintent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signupintent);
            }
        });
    }

    public void checkLogin() {

        final String mobileno = mobilenoEditText.getText().toString();
        if (!isValidMobile(mobileno)) {
            //Set error message for email field
            mtil.setError("Invalid Mobile No");
        }

        final String pass = passwordEditText.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            ptil.setError("Invalid Password");
        }

        if(isValidMobile(mobileno) && isValidPassword(pass))
        {
            mtil.setError(null);
            mtil.setErrorEnabled(false);
            ptil.setError(null);
            ptil.setErrorEnabled(false);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    // validating password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 4) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

