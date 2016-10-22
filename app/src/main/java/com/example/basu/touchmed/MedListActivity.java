package com.example.basu.touchmed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.basu.touchmed.Adapters.MedicineAdapter;
import com.example.basu.touchmed.model.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayoutManager linearLayoutManagerVertical;
    private RecyclerView recyclerView;
    private MedicineAdapter adapter;
    private List<Medicine> medicinelist;
    private static final int VERTICAL_ITEM_SPACE = 40;
    private Button addmedicinebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list);

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.medlist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Medicine List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        addmedicinebtn= (Button) findViewById(R.id.medlist_add_med_btn);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        linearLayoutManagerVertical =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        medicinelist = new ArrayList<>();
        adapter = new MedicineAdapter(this, medicinelist);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);
        //add ItemDecoration
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareMedicine();

        recyclerView.addOnItemTouchListener(new medItemClick(getApplicationContext(), recyclerView, new medItemClick.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getApplicationContext(),MedicineDescription.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        addmedicinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddMedicineFormActivity.class));
            }
        });

    }

    private void prepareMedicine() {

        Medicine m = new Medicine("Medicine 1");
        medicinelist.add(m);

        m = new Medicine("Medicine 2");
        medicinelist.add(m);

        m = new Medicine("Medicine 3");
        medicinelist.add(m);

        m = new Medicine("Medicine 4");
        medicinelist.add(m);

        m = new Medicine("Medicine 5");
        medicinelist.add(m);

        m = new Medicine("Medicine 6");
        medicinelist.add(m);

        adapter.notifyDataSetChanged();
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
