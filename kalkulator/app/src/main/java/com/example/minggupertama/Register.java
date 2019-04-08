package com.example.minggupertama;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {

    private EditText nama,alias,nrp;
    RadioGroup gender,kelas;
    private RadioButton wali,cowok,cewek,klsA,klsB;
    private Button register;
    private ProgressBar loading;
    final static String url = "https://diabsen.in/api/register/student";
    SharedPreferences sharedPreferences;
    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragRegister = inflater.inflate(R.layout.fragment_register, container,
                false);
        loading = fragRegister.findViewById(R.id.loading);
        nama = fragRegister.findViewById(R.id.name);
        alias = fragRegister.findViewById(R.id.alias1);
        nrp = fragRegister.findViewById(R.id.nrp2);
        gender = fragRegister.findViewById(R.id.gender);
        kelas = fragRegister.findViewById(R.id.kelas);
        wali = fragRegister.findViewById(R.id.wali);
        cowok = fragRegister.findViewById(R.id.cowok);
        cewek = fragRegister.findViewById(R.id.cewek);
        klsA = fragRegister.findViewById(R.id.a);
        klsB = fragRegister.findViewById(R.id.b);
        register = fragRegister.findViewById(R.id.btn_regist);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
        return fragRegister;
    }
    public JSONObject parameter(){
        String nrp1 = nrp.getText().toString();
        String username = nama.getText().toString();
        String alias1= alias.getText().toString();
        int wali = 1;
        int gender = 1;
        int group = 1;
        if(cowok.isChecked()){
            gender = 1;
        } else if(cewek.isChecked()){
            gender = 0;
        }
        if(klsA.isChecked()){
            group = 1;
        } else if(klsB.isChecked()){
            group = 2;
        }
        final JSONObject params = new JSONObject();
        try{
            params.put("nama", username);
            params.put("alias", alias1);
            params.put("gender", gender);
            params.put("nrp", nrp1);
            params.put("grupkelas_id", group);
            params.put("wali_id", wali);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return params;

    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        register.setVisibility(View.GONE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,parameter(),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Intent intent1 = new Intent(getContext(),MainActivity.class);
                    startActivity(intent1);
                    Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.GONE);
                    register.setVisibility(View.VISIBLE);
                }catch (JSONException e) {
                    e.printStackTrace();

                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        error.getMessage();
                    }
                });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

}
