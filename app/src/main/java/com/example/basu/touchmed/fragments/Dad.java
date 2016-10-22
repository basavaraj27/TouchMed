package com.example.basu.touchmed.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.basu.touchmed.Adapters.DependentAdapter;
import com.example.basu.touchmed.AddDrugs;
import com.example.basu.touchmed.AddMedicineFormActivity;
import com.example.basu.touchmed.DepndItemClick;
import com.example.basu.touchmed.MedListActivity;
import com.example.basu.touchmed.R;
import com.example.basu.touchmed.model.DepndentModel;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dad extends Fragment {

    private RecyclerView recyclerView;
    private DependentAdapter adapter;
    private List<DepndentModel> daylist;
    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton adddrugs;


    public Dad() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dad, container, false);
        adddrugs= (com.github.clans.fab.FloatingActionButton) v.findViewById(R.id.fab_add_drugs);

        recyclerView = (RecyclerView) v.findViewById(R.id.dependrecycler);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daylist=new ArrayList<>();
        adapter=new DependentAdapter(getContext(),daylist);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareDays();

        //add drugs
        adddrugs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddDrugs.class));
            }
        });


        recyclerView.addOnItemTouchListener(new DepndItemClick(getContext(), recyclerView, new DepndItemClick.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                startActivity(new Intent(getContext(),MedListActivity.class));

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

    /**
     * Adding few albums for testing
     */
    private void prepareDays() {
        String[] myStringArray = new String[]{"Morning","Afternoon","Evening","Night"};

        DepndentModel a = new DepndentModel(myStringArray[0]);
        daylist.add(a);

        a = new DepndentModel(myStringArray[1]);
        daylist.add(a);

        a = new DepndentModel(myStringArray[2]);
        daylist.add(a);

        a = new DepndentModel(myStringArray[3]);
        daylist.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
