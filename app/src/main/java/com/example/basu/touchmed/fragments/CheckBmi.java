package com.example.basu.touchmed.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basu.touchmed.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckBmi extends Fragment {

    private EditText bmi_age;
    private ImageView bmi_gender;
    private EditText height_et_cm,height_meter;
    private ImageView bmi_height_image;
    private EditText bmi_weight_et;
    private Button checkbmibtn;
    private TextView bmi_cal_result,bmi_vlaue_tv;

    public CheckBmi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_check_bmi, container, false);
        bmi_gender = (ImageView) v.findViewById(R.id.bmi_gender_imgv);
        height_et_cm= (EditText) v.findViewById(R.id.bmi_height_cm_et);
        height_meter= (EditText) v.findViewById(R.id.bmi_height_meter);
        bmi_height_image= (ImageView) v.findViewById(R.id.bmi_height_iv);
        bmi_weight_et= (EditText) v.findViewById(R.id.bmi_weight_et);
        checkbmibtn= (Button) v.findViewById(R.id.check_bmi_id);
        bmi_cal_result= (TextView) v.findViewById(R.id.bmiresulttvid);
        bmi_vlaue_tv= (TextView) v.findViewById(R.id.bmi_value);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bmi_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGenderDialogue();
            }
        });

        bmi_height_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHeightDialog();
            }
        });

        checkbmibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBmi();
            }
        });

    }

//create a dialog for selecting in centimeter or meter
    public void showHeightDialog()
    {
        // custom dialog
        final Dialog dialog = new Dialog(getContext());

        // Hide the Title bar
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //show the Radio group or attach the layout to the dialog
        dialog.setContentView(R.layout.radiobutton_height_bmi);

        // craete a list
        List<String> stringList=new ArrayList<>();

        //add items to the list
        stringList.add("centemeter");
        stringList.add("meter");

        //find the radio group layout
        RadioGroup rgheight = (RadioGroup) dialog.findViewById(R.id.bmi_height_radio_group);

        for(int i=0;i<stringList.size();i++){

            // dynamically creating RadioButton and adding to RadioGroup.
            //pass the context of the activity
            RadioButton rbheight=new RadioButton(getContext());

            //set name for the radio button taken from the list item
            //i represent position of the item in the list
            rbheight.setText(stringList.get(i));

            //add this created radio button to the radio group
            rgheight.addView(rbheight);
        }

        // show the dialogue
        dialog.show();


        // callback to be invoked when the checked radio button changed in this group.
        rgheight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //initialize a temporary string variable
                String value="";

                //get the number of radio buttons in the radio group
                int childCount = radioGroup.getChildCount();

                //loop all the radio buttons in the radio group
                for (int x = 0; x < childCount; x++) {

                    //get each radio button from the radio group
                    RadioButton btn = (RadioButton) radioGroup.getChildAt(x);

                    //if the radio button id is equal to the pressed radio button
                    if (btn.getId() == i) {

                        //get the radio button text and store it in value
                        value= btn.getText().toString();
                    }
                }

                System.out.println(value);

                if (value == "centemeter") {
                    height_meter.setText("");
                    height_meter.setVisibility(View.GONE);
                    height_et_cm.setVisibility(View.VISIBLE);
                    bmi_height_image.setImageResource(R.drawable.cm);
                    dialog.dismiss();
                }else
                {
                    height_et_cm.setText("");
                    height_meter.setVisibility(View.VISIBLE);
                    height_et_cm.setVisibility(View.GONE);
                    bmi_height_image.setImageResource(R.drawable.meter);
                    dialog.dismiss();
                }
            }
        });
    }


    public void showGenderDialogue()
    {
        // custom dialog
        final Dialog dialog = new Dialog(getContext());

        // Hide the Title bar
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //show the Radio group or attach the layout to the dialog
        dialog.setContentView(R.layout.radiobutton_dialog);

        // craete a list
        List<String> stringList=new ArrayList<>();

        //add items to the list
        stringList.add("Male");
        stringList.add("Female");

        //find the radio group layout
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.bmi_gender_radio_group);

        for(int i=0;i<stringList.size();i++){
            // dynamically creating RadioButton and adding to RadioGroup.
            //pass the context of the activity
            RadioButton rb=new RadioButton(getContext());

            //set name for the radio button taken from the list item
            //i represent position of the item in the list
            rb.setText(stringList.get(i));

            //add this created radio button to the radio group
            rg.addView(rb);
        }

        // show the dialogue
        dialog.show();


        // callback to be invoked when the checked radio button changed in this group.
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                //initialize a temporary string variable
                String value="";

                //get the number of radio buttons in the radio group
                int childCount = radioGroup.getChildCount();

                //loop all the radio buttons in the radio group
                for (int x = 0; x < childCount; x++) {

                    //get each radio button from the radio group
                    RadioButton btn = (RadioButton) radioGroup.getChildAt(x);

                    //if the radio button id is equal to the pressed radio button
                    if (btn.getId() == i) {
                        //get the radio button text and store it in value
                        value= btn.getText().toString();
                    }
                }
                System.out.println(value);

                //if the radio button selected is male
                if (value == "Male") {
                    bmi_gender.setImageResource(R.drawable.male);
                    dialog.dismiss();
                }
                //if the radio button selected is female
                else
                {
                    bmi_gender.setImageResource(R.drawable.age_fem1);
                    dialog.dismiss();
                }
            }
        });
    }

    //validating the name field
    private boolean isValidHeight(String height)
    {
        if(height!= null && height.length()> 1) {
            return true;
        }
        return false;
    }


//    Paul weighs 150kgs and is 1.8m tall. He wants to know if he is overweight.
//
//    1. First we multiply Paul's height by itself: 1.8 x 1.8 = 3.24 ²
//
//    Next we divide Paul's weight by his height in meters ² just calculated: 150 / 3.24 = 46.3
//
//    Paul's BMI is 46.3

     public void calculateBmi()
    {

        //get the centimeter value
        String height_cent = height_et_cm.getText().toString();

        //get the meter value
        String height_meters=height_meter.getText().toString();

        //get the weight value
        String weight = bmi_weight_et.getText().toString();

        //initialize weight to 0
        double myweight=0;

        if(weight.isEmpty())
        {
            //if it is empty set error
            bmi_weight_et.setError("invalid weight");
        }
        else {
            //convert string weight value to double
            myweight=Double.parseDouble(weight);
        }

    //create height variable and initialize to 0
        double height=0;

        //check if the height is entered in centimeter or meter
        if ( height_cent.isEmpty() || height_meters.isEmpty())
        {
            //if it is entered in centimeter
            if(!height_cent.isEmpty())
            {
                //convert string value to double
                height = Double.parseDouble(height_cent);

                //convert centimeter to meter
                height = height/100.00;

                System.out.println(height);
            }
            //if it is entered in  meter
            else if (!height_meters.isEmpty())
            {
                //convert string value to double
                height = Double.parseDouble(height_meters);
                System.out.println(height);
            }
        }else
        {
            //if the value is not entered for centimeter or meter
            Toast.makeText(getContext(),"Enter Height",Toast.LENGTH_SHORT).show();
        }

        // calculate the bmi value
        double bmi_value= (myweight)/(height*height);

        // take only two decimal points for double so trim the other values
        String texmp = String.format(Locale.ENGLISH,"%.2f", bmi_value);

        if (bmi_value <= 18.5)
        {
            bmi_cal_result.setVisibility(View.VISIBLE);
            bmi_vlaue_tv.setVisibility(View.VISIBLE);
            bmi_cal_result.setText(texmp);
            bmi_vlaue_tv.setText("underweight : ");
        }
        else if(bmi_value > 18.5 && bmi_value <= 24.9)
        {
            bmi_cal_result.setVisibility(View.VISIBLE);
            bmi_vlaue_tv.setVisibility(View.VISIBLE);
            bmi_cal_result.setText(texmp);
            bmi_vlaue_tv.setText("Normal : ");

        }
        else if(bmi_value >= 25 && bmi_value <= 29.9)
        {
            bmi_cal_result.setVisibility(View.VISIBLE);
            bmi_vlaue_tv.setVisibility(View.VISIBLE);
            bmi_cal_result.setText(texmp);
            bmi_vlaue_tv.setText("OverWeight : ");

        }
        else if (Double.isInfinite(bmi_value))
        {
            Toast.makeText(getContext(),"enter values correctly",Toast.LENGTH_SHORT).show();
            bmi_vlaue_tv.setText("");
        }
        else if (Double.isNaN(bmi_value))
        {
            Toast.makeText(getContext(),"enter values correctly",Toast.LENGTH_SHORT).show();
            bmi_vlaue_tv.setText("");
        }
        else
        {
            bmi_cal_result.setVisibility(View.VISIBLE);
            bmi_vlaue_tv.setVisibility(View.VISIBLE);
            bmi_cal_result.setText(texmp);
            bmi_vlaue_tv.setText("Obese : ");
        }
    }
}
