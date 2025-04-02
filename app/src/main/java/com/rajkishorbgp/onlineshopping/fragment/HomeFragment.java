package com.rajkishorbgp.onlineshopping.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.rajkishorbgp.onlineshopping.DatabaseHelper;
import com.rajkishorbgp.onlineshopping.HomeAdapter;
import com.rajkishorbgp.onlineshopping.HomeDatabase;
import com.rajkishorbgp.onlineshopping.ItemClass;
import com.rajkishorbgp.onlineshopping.MainActivity;
import com.rajkishorbgp.onlineshopping.R;
import com.rajkishorbgp.onlineshopping.myclass.LoadData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View view;
    ArrayList<ItemClass> homeArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.listView);
        HomeDatabase homeDatabase = new HomeDatabase(requireActivity().getApplicationContext());
        homeArrayList = homeDatabase.getAllDataUser();

        HomeAdapter homeAdapter = new HomeAdapter(requireActivity().getApplicationContext(), homeArrayList);
        listView.setAdapter(homeAdapter);

        return view;
    }
    public void onResume() {
        super.onResume();

        MainActivity activity = (MainActivity) requireActivity();
        TextView toolbarText = activity.findViewById(R.id.toolbarText);
        toolbarText.setText("Home");
    }
}