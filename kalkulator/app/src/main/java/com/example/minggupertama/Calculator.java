package com.example.minggupertama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.minggupertama.Register.url;

public class Calculator extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, b10, buttonAdd, buttonSub, buttonDivision,
            buttonMul, buttonC, buttonEqual, btnNegatif, btnPersen, delete;
    TextView edit, edit2;
    RequestQueue mQueu;
    LinearLayout samadengan, hapus;
    SharedPreferences sharedPreferences;
    final static String url_calc = "https://diabsen.in/api/calculator/run";
    float hasil;

    public void setInputan(TextView btn) {
        TextView edit = findViewById(R.id.edt1);
        tampil = edit.getText().toString();
        String btnValue = btn.getText().toString();
        if (tampil.equals("0")) {
            if (btnValue.equals(".")) {
                edit.setText("0" + btnValue);
            } else {
                if (btnValue.equals("+") || btnValue.equals("x") || btnValue.equals("/") || btnValue.equals("%")) {
                } else {
                    edit.setText(btnValue);
                }
            }
        } else {
            edit.setText(tampil + btnValue);
        }
    }

    public void hapus() {
        TextView tVbawah = findViewById(R.id.edt1);
        String layar = tVbawah.getText().toString();
        if (layar.length() > 1) {
            tVbawah.setText(layar.substring(0, layar.length() - 1));
        } else {
            tVbawah.setText("0");
        }
    }

    public void hapusSemua() {
        listInput.removeAll(listInput);
        TextView tVbawah = findViewById(R.id.edt1);
        TextView tVatas = findViewById(R.id.edt2);
        tVbawah.setText("0");
        tVatas.setText("");
        listInput.clear();
    }


    public void samadengan() {

        TextView tvBawah = findViewById(R.id.edt1);
        TextView tvAtas = findViewById(R.id.edt2);

        //convert input ke array
        String layar = tvBawah.getText().toString();
        String numeric = "";

        for (int i = 0; i < layar.length(); i++) {
            if (i > 0) {
                if (layar.charAt(i) == '+' || layar.charAt(i) == '-' || layar.charAt(i) == 'x' || layar.charAt(i) == '/' || layar.charAt(i) == '%') {
                    listInput.add(numeric);
                    listInput.add(String.valueOf(layar.charAt(i)));
                    numeric = "";
                } else {
                    numeric += String.valueOf(layar.charAt(i));
                }
            } else {
                numeric += String.valueOf(layar.charAt(i));
            }

        }

        listInput.add(numeric);

        //Bodmas
        for (int i = 0; i < listInput.size(); i++) {
            if (listInput.get(i).equals("%")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                //  float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp / 100;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                // listInput.remove(i);
                i--;
            } else if (listInput.get(i).equals("x")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp * temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                listInput.remove(i);
                i--;

            } else if (listInput.get(i).equals("/")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp / temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                listInput.remove(i);
                i--;
            }
        }

        for (int i = 0; i < listInput.size(); i++) {
            if (listInput.get(i).equals("+")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp + temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                listInput.remove(i);
                i--;

            } else if (listInput.get(i).equals("-")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp - temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                listInput.remove(i);
                i--;
            }
        }

        //hasil akhir
        tvAtas.setText(tvBawah.getText().toString());
        tvBawah.setText(String.valueOf(listInput.get(0).toString()).replaceAll("\\.?0*$", ""));
        listInput.clear();

    }


    ArrayList<String> listInput = new ArrayList<>();
    String tampil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        buttonAdd = findViewById(R.id.buttonadd);
        buttonSub = findViewById(R.id.buttonsub);
        buttonMul = findViewById(R.id.buttonmul);
        buttonDivision = findViewById(R.id.buttondiv);
        buttonC = findViewById(R.id.buttonC);
        buttonEqual = findViewById(R.id.buttoneql);
        btnNegatif = findViewById(R.id.negatifplus);
        btnPersen = findViewById(R.id.buttonpersen);
        mQueu = Volley.newRequestQueue(this);
        delete = findViewById(R.id.hapus);
        edit = findViewById(R.id.edt1);
        edit2 = findViewById(R.id.edt2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b6);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b7);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b8);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b9);
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b0);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setInputan(buttonAdd);
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(buttonSub);

            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(buttonMul);
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(buttonDivision);
            }
        });
        btnPersen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(btnPersen);
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postCalculator(url_calc);

            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusSemua();
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(b10);

            }
        });
        btnNegatif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInputan(btnNegatif);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
            }
        });
    }

    public JSONObject parameter(){
        TextView papan = findViewById(R.id.edt1);
        String layar = papan.getText().toString();
        final JSONObject params = new JSONObject();
        try{
            params.put("input", layar);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return params;

    }


    private void GetCalculator() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("response")){
                        Toast.makeText(Calculator.this, "hsi", Toast.LENGTH_SHORT).show();
                        String newToken = response.getJSONObject("results").getString("access_token");
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Calculator.this);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("access_token",newToken);
                        editor.commit();
                        Toast.makeText(Calculator.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                        postCalculator(url_calc);

                    } else {
                        String message = response.getString("message");
                        Toast.makeText(Calculator.this, message, Toast.LENGTH_SHORT).show();
                        if(message.equals("Signature has expired")){
                            Intent intent = new Intent(Calculator.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    Toast.makeText(Calculator.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                error.getMessage();
                if(error.networkResponse.statusCode == 401){

                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Calculator.this);
                String token = "Bearer " + sharedPreferences.getString("refresh_token","");

                params.put("Authorization", token);
                params.put("Content-Type", "application/json");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    public void postCalculator(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, parameter(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("response")){
                        TextView tvhasil = findViewById(R.id.edt2);
                        TextView papan = findViewById(R.id.edt1);
                        String desimal = response.getJSONObject("results").getJSONObject("result").getString("decimal");
                        String hasil = response.getJSONObject("results").getJSONObject("result").getString("exact");
                        tvhasil.setText(papan.getText().toString());
                        if(!desimal.equals("")){
                            papan.setText(desimal);
                        }else{
                            papan.setText(hasil);
                        }
                    }

                    Toast.makeText(Calculator.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                error.getMessage();
                if(error.networkResponse.statusCode == 401) {
                    GetCalculator();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Calculator.this);
                String token = "Bearer " + sharedPreferences.getString("access_token","");
                params.put("Authorization", token);
                params.put("Content-Type", "application/json");

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
