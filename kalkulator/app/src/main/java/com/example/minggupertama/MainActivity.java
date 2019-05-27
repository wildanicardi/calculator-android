package com.example.minggupertama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button change;
    private boolean isLoginForm = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Kalkulator");
        change = (Button)findViewById(R.id.change_button);
        formLogin();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!isLoginForm){

                        formRegist();
                        change.setVisibility(v.INVISIBLE);
                    } else {
                        formLogin();
                    }

            }
        });
    }

        public void formRegist(){
            isLoginForm = true;
            Register regis = new Register();
            FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();
            FT.replace(R.id.main_menu, regis);
            FT.commit();
        }

        public void formLogin(){
        isLoginForm = false;
        Login login = new Login();
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        FT.replace(R.id.main_menu, login);
        FT.commit();

    }

}

