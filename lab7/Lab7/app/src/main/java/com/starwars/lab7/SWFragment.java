package com.starwars.lab7;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class StarWarFragment extends Fragment {

    private static final String ARG_STAR_WARS_LIST_ITEM_OBJ = "star-war-item-obj";
    private List<Result> results;


    public StarWarFragment() {
    }

    @SuppressWarnings("unused")
    public static StarWarFragment newInstance(ArrayList<Result> results) {
        StarWarFragment fragment = new StarWarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STAR_WARS_LIST_ITEM_OBJ, results);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            results = (ArrayList<Result>) getArguments().getSerializable(ARG_STAR_WARS_LIST_ITEM_OBJ);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MyStarWarRecyclerViewAdapter(results));
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            // do whatever
                            Result result = results.get(position);
                            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                            ft.replace(R.id.frame, DetailsFragment.newInstance(result));
                            ft.addToBackStack("DetailsFragment");
                            ft.commit();
                        }

                        @Override public void onLongItemClick(View view, int position) {

                        }
                    })
            );
        }
        return view;
    }
}