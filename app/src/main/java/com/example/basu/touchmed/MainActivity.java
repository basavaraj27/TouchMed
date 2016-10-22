package com.example.basu.touchmed;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.example.basu.touchmed.fragments.AddDependent;
import com.example.basu.touchmed.fragments.CheckBmi;
import com.example.basu.touchmed.fragments.Dad;


public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.navtoolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        Menu m = navigationView.getMenu();
        SubMenu dependents = m.addSubMenu("Dependents");
        dependents.add("Dad");
        dependents.add("Mom");
        dependents.getItem(0).setIcon(R.drawable.user_name);
        dependents.getItem(1).setIcon(R.drawable.user_name);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getTitle().toString()){

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case "Add Dependents":
                        AddDependent dependent=new AddDependent();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,dependent);
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Add Dependent");
                        return true;

                    // For rest of the options we just show a toast on click

                    case "Check BMI":
                        CheckBmi checkBmi=new CheckBmi();
                        android.support.v4.app.FragmentTransaction fragmentTransactionbmi = getSupportFragmentManager().beginTransaction();
                        fragmentTransactionbmi.replace(R.id.frame,checkBmi);
                        fragmentTransactionbmi.commit();
                        getSupportActionBar().setTitle("Check BMI");
                        return true;


                    case "Dad":
                        Dad dad=new Dad();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame,dad);
                        fragmentTransaction2.commit();
                        getSupportActionBar().setTitle("Dad");
                        return true;

                    case "Mom":
                        Toast.makeText(getApplicationContext(),"Mom",Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                        super.onDrawerOpened(drawerView);
                    }
                };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }
}