package com.example.minggupertama;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {

    private Button btnAlamat,btnBack;
    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View alamat = inflater.inflate(R.layout.fragment_about,container,false);
        btnAlamat = alamat.findViewById(R.id.btn_alamat);

        btnAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alamat = new Intent(getActivity(),MapsActivity.class);
                startActivity(alamat);
            }
        });
        btnBack = alamat.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Biodata biodata = new Biodata();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_menu, biodata);
                fragmentTransaction.commit();
            }
        });
        return alamat;
    }

}
