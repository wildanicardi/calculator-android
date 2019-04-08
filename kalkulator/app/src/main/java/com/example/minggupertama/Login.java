package com.example.minggupertama;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    private EditText nrp,password;
    private Button btnLogin;
    SharedPreferences sharedPreferences;
    private ProgressBar loading;
    final String url = "https://diabsen.in/api/auth/login";
    final static String RESPONSE = "response";
    boolean response2;
    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLogin = inflater.inflate(R.layout.fragment_login,container,false);
        loading = fragmentLogin.findViewById(R.id.loading);
        nrp = fragmentLogin.findViewById(R.id.nrp1);
        password = fragmentLogin.findViewById(R.id.password);
        btnLogin = fragmentLogin.findViewById(R.id.btn_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        return fragmentLogin;
    }
    private void Login(){
        loading.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        JSONObject params = new JSONObject();
        try {
            params.put("username",nrp);
            params.put("password",password);
        }catch (JSONException e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getContext(), "Bisa", Toast.LENGTH_SHORT).show();
                try {

                    String acces_token = response.getJSONObject("results").getString("access_token");
                    String refresh_token = response.getJSONObject("results").getString("refresh_token");
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("acces_token",acces_token);
                    editor.putString("refresh_token",refresh_token);
                    editor.commit();
                    Intent intent = new Intent(getContext(),Calculator.class);
                    startActivity(intent);
                    loading.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                }catch (JSONException e){

                    e.printStackTrace();
                }
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        error.getMessage();
                        Log.e("error", String.valueOf(error));
                    }
                })
        {
        };
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

}
