package com.example.basu.touchmed;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddDrugs extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView add_drug_date_TV,add_drug_expiry_date;
    private Button btnDatePicker,adddrug;
    private EditText txtDate,add_drug_name,add_drug_stock;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drugs);

        add_drug_date_TV=(TextView) findViewById(R.id.add_drug_date_pick);
        // Attaching the layout to the toolbar object
        toolbar = (Toolbar) findViewById(R.id.adddrugs_toolbar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Drugs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adddrug= (Button) findViewById(R.id.Adddrug_submit_button);

        add_drug_name= (EditText) findViewById(R.id.Adddrug_name_edittext);
        add_drug_stock= (EditText) findViewById(R.id.Adddrug_stock_EditText);


        adddrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(getApplicationContext(),"Drug Added",Toast.LENGTH_SHORT).show();
            }
        });


        add_drug_date_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

// Create a new instance of DatePickerDialog

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddDrugs.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                add_drug_date_TV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
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
}
