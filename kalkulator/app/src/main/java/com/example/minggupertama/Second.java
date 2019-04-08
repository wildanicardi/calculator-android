package com.example.minggupertama;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    TextView tvResult;
    Button btnGet,btnPost;
    private SharedPreferences sharedPreferences;
    RequestQueue mQueue;

    public Second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View viewFrag2 = inflater.inflate(R.layout.fragment_second, container,
                    false);
        tvResult = (TextView) viewFrag2.findViewById(R.id.textView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        btnGet = (Button)viewFrag2.findViewById(R.id.btn_get);
        btnPost = (Button)viewFrag2.findViewById(R.id.btn_post);
        mQueue = Volley.newRequestQueue(getContext());
        final String post_url = "https://postman-echo.com/post";
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest(post_url);
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
            return viewFrag2;
    }
    private void jsonParse(){
        String url = "https://api.myjson.com/bins/12qdi2";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("aku");
                            for (int i=0; i<jsonArray.length();i++ ){
                                JSONObject aku = jsonArray.getJSONObject(i);
                                String fullName = aku.getString("fullname");
                                int nrp = aku.getInt("nrp");
                                String title = aku.getString("title");

                                tvResult.append(title + "\n" + String.valueOf(nrp) + "\n" + fullName+ "\n");
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
    public void postRequest(String url){

        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject headers = response.getJSONObject("headers");
                    String content_type = headers.getString("content-type");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("content-type", content_type);
                    editor.commit();

                    tvResult.setText(sharedPreferences.getString("content-type", "tidakada"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("name", "Wildan");
                params.put("password", "secret");
                return params;
            }
        };

        Volley.newRequestQueue(getContext()).add(postRequest);

    }



}
