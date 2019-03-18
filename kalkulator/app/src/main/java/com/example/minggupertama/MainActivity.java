package com.example.minggupertama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button change;
    Boolean kondisi = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change = (Button)findViewById(R.id.change_button);
        change.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_button:
                if (kondisi) {
                    kondisi = false;
                    Home satu = new Home();
                    FragmentManager FM = getSupportFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();
                    FT.replace(R.id.main_menu, satu);
                    FT.commit();
                } else {
                    kondisi = true;
                    Second dua = new Second();
                    FragmentManager FM2 = getSupportFragmentManager();
                    FragmentTransaction FT2 = FM2.beginTransaction();
                    FT2.replace(R.id.main_menu, dua);
                    FT2.commit();
                }
                break;

        }
    }
}

