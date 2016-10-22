package com.example.basu.touchmed.fragments;


import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.basu.touchmed.R;
import com.thebluealliance.spectrum.SpectrumPalette;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDependent extends Fragment implements SpectrumPalette.OnColorSelectedListener {

    private EditText dependname,dependmobno,dependage,dependid;
    private Button adddependbutton;
    ImageView img;

    public AddDependent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;

        v=inflater.inflate(R.layout.fragment_add_dependent, container, false);
        dependname= (EditText) v.findViewById(R.id.dependent_name_edittext);
        dependmobno= (EditText) v.findViewById(R.id.dependent_phoneno_edittext);
        dependage= (EditText) v.findViewById(R.id.dependent_age_edittext);
        dependid= (EditText) v.findViewById(R.id.dependent_id_edittext);
        adddependbutton= (Button) v.findViewById(R.id.dependent_add_button);

        //spectrum color picker initialization
        SpectrumPalette spectrumPalette = (SpectrumPalette) v.findViewById(R.id.palette);

        //get the colors from the array of colors in initialized in value folder
        int[] colors = getResources().getIntArray(R.array.demo_colors);

        //set all the colors for pallete
        spectrumPalette.setColors(colors);

        //call on color selected listener
        spectrumPalette.setOnColorSelectedListener(this);

        //return the view
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    //on click of add dependent button
        adddependbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDependent();
            }
        });

    }

    public void checkDependent()
    {
        //check for the name field
        final String name = dependname.getText().toString();
        if (!isValidName(name)) {
            //Set error message for name field
            dependname.setError("Invalid Name");
        }

        //check for the Mobile No field
        final String mobno = dependmobno.getText().toString();
        if (!isValidMobile(mobno)) {
            //Set error message for mob no field
            dependmobno.setError("Invalid Mobile NO");
        }

        //check for the age field
        final String age = dependage.getText().toString();
        if (!isValidAge(age)) {
            //Set error message for age field
            dependage.setError("Invalid Age");
        }

        //check for the Id field
        final String id = dependid.getText().toString();
        if (!isValidId(id)) {
            //Set error message for id field
            dependid.setError("Invalid id");
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

    //validating the mobile field
    private boolean isValidMobile(String phone2)
    {
        if(phone2.length() < 6 || phone2.length() > 13)
        {
            return false;
        }
        return true;
    }

    //validating the age field
    private boolean isValidAge(String age)
    {
        if(age!= null && age.length()> 1) {
            return true;
        }
        return false;
    }

    //validating the id field
    private boolean isValidId(String id)
    {
        if(id!= null && id.length()> 1) {
            return true;
        }
        return false;
    }

    //on color selected from pallete what to happen
    @Override
    public void onColorSelected(@ColorInt int color) {
        //Toast.makeText(getContext(), "Color selected: #" + Integer.toHexString(color).toUpperCase(), Toast.LENGTH_SHORT).show();
    }
}
