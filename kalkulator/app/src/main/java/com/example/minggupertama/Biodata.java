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
public class Biodata extends Fragment {

    private Button btnAbout,btnKalkulator;
    public Biodata() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View aboutFrag = inflater.inflate(R.layout.fragment_biodata,container,false);
        btnAbout = aboutFrag.findViewById(R.id.btn_about);

        btnKalkulator = aboutFrag.findViewById(R.id.btn_kalkulator);
        btnKalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kalkulator = new Intent(getActivity(),Calculator.class);
                startActivity(kalkulator);
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               About about = new About();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_menu, about);
                fragmentTransaction.commit();

            }
        });
        return aboutFrag;
    }

}
