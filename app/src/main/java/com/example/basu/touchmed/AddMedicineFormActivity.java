package com.example.basu.touchmed;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedicineFormActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText medname,remark;
    TextView startdate,startdatepick,enddate,enddatepick,time,timepick;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Button addmedbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_form);

        medname= (EditText) findViewById(R.id.addmed_name_edittext);
        remark= (EditText) findViewById(R.id.addmedform_remark);
        startdatepick= (TextView) findViewById(R.id.startdatepick);
        enddatepick= (TextView) findViewById(R.id.enddatepick);
        timepick= (TextView) findViewById(R.id.addmedform_timepick);
        addmedbtn= (Button) findViewById(R.id.addmedicineform_addmedbtn);

        toolbar = (Toolbar) findViewById(R.id.addmedicine_form_toolbar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        getSupportActionBar().setTitle("Add Medicine");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        startdatepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepick(startdatepick);
            }
        });

        enddatepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepick(enddatepick);
            }
        });

        timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePick();
            }
        });

        //add medicine on button click
        addmedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Medicine Added",Toast.LENGTH_SHORT).show();
                resetaddMedicineForm();

            }
        });
    }

    // Reset medicine Form
    public void resetaddMedicineForm()
    {
        medname.setText("");
        startdatepick.setText("Select Start Date");
        enddatepick.setText("Select End Date");
        timepick.setText("Select Time");
        remark.setText("");
    }

    public void datepick(final TextView tv)
    {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddMedicineFormActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        tv.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void timePick()
    {
// Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String AM_PM ;
                        if(hourOfDay < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }

                        timepick.setText(hourOfDay + ":" + minute+" "+AM_PM);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //get the item id
        int id=item.getItemId();

        switch (id)
        {
         // toolbar back button on selected action
            case android.R.id.home :finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
