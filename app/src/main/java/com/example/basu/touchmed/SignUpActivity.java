package com.example.basu.touchmed;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText signupname,signuppass,signupmail,signupmobno;
    private Button signupbutton;
    private FloatingActionButton imagepicker;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        getSupportActionBar().setTitle("sign up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        signupname= (EditText) findViewById(R.id.signup_name_edittext);
        signuppass= (EditText) findViewById(R.id.input_pass_EditText);
        signupmail= (EditText) findViewById(R.id.input_Email_EditText);
        signupmobno= (EditText) findViewById(R.id.signup_input_mobileno);

        signupbutton= (Button) findViewById(R.id.signup_sp_button);

        imagepicker= (FloatingActionButton) findViewById(R.id.signup_img_fab);


        imagepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
// Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });

        //onclick of signup  button
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSignUp();
            }
        });

    }

    public void checkSignUp()
    {
        //check for the name field
        final String name = signupname.getText().toString();
        if (!isValidName(name)) {
            //Set error message for name field
            signupname.setError("Invalid Name");
        }

        //check for the password field
        final String pass = signuppass.getText().toString();
        if (!isValidPassword(pass)) {
            //Set error message for password field
            signuppass.setError("Invalid Password");
        }

        //check for the Email field
        final String email = signupmail.getText().toString();
        if (!isValidEmail(email)) {
            //Set error message for email field
            signupmail.setError("Invalid Email");
        }

        //check for the Mobile No field
        final String mobno = signupmobno.getText().toString();
        if (!isValidMobile(mobno)) {
            //Set error message for email field
            signupmobno.setError("Invalid Mobile NO");
        }
    }

    //validating the name field
    private boolean isValidName(String name)
    {
        if(name!= null && name.length()> 6) {
        return true;
    }
        return false;
    }

    private boolean isValidMobile(String phone2)
    {
        if(phone2.length() < 6 || phone2.length() > 13)
        {
            return false;
        }
        return true;
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                //get image from gallery
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //compress bitmap
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                //select the image view to display
                ImageView imageView = (ImageView) findViewById(R.id.signup_profile_image);
                //set the compressed bitmap to image view
                imageView.setImageBitmap(scaled);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
