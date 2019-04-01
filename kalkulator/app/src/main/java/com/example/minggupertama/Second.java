package com.example.minggupertama;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    TextView tvResult;
    Button btnGet,btnPost;
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
        btnGet = (Button)viewFrag2.findViewById(R.id.btn_get);
        mQueue = Volley.newRequestQueue(getContext());

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

}
