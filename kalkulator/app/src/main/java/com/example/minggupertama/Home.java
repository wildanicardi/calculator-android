package com.example.minggupertama;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b10,buttonAdd, buttonSub, buttonDivision,
            buttonMul, buttonC, buttonEqual,btnNegatif,btnPersen,delete;
    TextView edit,edit2;
    float hasil;
    public void setInputan(TextView btn){
        TextView edit = getView().findViewById(R.id.edt1);
        tampil = edit.getText().toString();
        String btnValue = btn.getText().toString();
        if(tampil.equals("0")){
            if (btnValue.equals(".")) {
                edit.setText("0" + btnValue);
            } else {
                if (btnValue.equals("+") || btnValue.equals("x") || btnValue.equals("/") || btnValue.equals("%") ){
                }else {
                    edit.setText(btnValue);
                }
            }
        } else{
            edit.setText(tampil + btnValue);
        }
    }
    public void hapus(){
    TextView tVbawah = getView().findViewById(R.id.edt1);
    String layar = tVbawah.getText().toString();
        if (layar.length() > 1){
        tVbawah.setText(layar.substring(0,layar.length()-1));
    } else {
        tVbawah.setText("0");
    }
}

    public void hapusSemua(){
        listInput.removeAll(listInput);
        TextView tVbawah = getActivity().findViewById(R.id.edt1);
        TextView tVatas = getActivity().findViewById(R.id.edt2);
        tVbawah.setText("0");
        tVatas.setText("");
        listInput.clear();
    }


    public void hasil(){

        TextView tvBawah = getActivity().findViewById(R.id.edt1);
        TextView tvAtas = getActivity().findViewById(R.id.edt2);

        //convert input ke array
        String layar = tvBawah.getText().toString();
        String numeric = "";

        for (int i=0;i<layar.length();i++){
            if (i>0){
                if (layar.charAt(i) == '+' || layar.charAt(i) == '-' || layar.charAt(i) == 'x' || layar.charAt(i) == '/' || layar.charAt(i) == '%'){
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
        for (int i=0;i<listInput.size();i++) {
            if (listInput.get(i).equals("%")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                //  float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp / 100;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                // listInput.remove(i);
                i--;
            }

            else if (listInput.get(i).equals("x")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp * temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                listInput.remove(i);
                i--;

            } else if (listInput.get(i).equals("/")){
                float temp = Float.parseFloat(listInput.get(i-1).toString());
                float temp2 = Float.parseFloat(listInput.get(i+1).toString());
                float hsl = temp / temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i-1);
                listInput.remove(i);
                i--;
            }
        }

        for (int i=0;i<listInput.size();i++){
            if (listInput.get(i).equals("+")) {
                float temp = Float.parseFloat(listInput.get(i - 1).toString());
                float temp2 = Float.parseFloat(listInput.get(i + 1).toString());
                float hsl = temp + temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i - 1);
                listInput.remove(i);
                i--;

            } else if (listInput.get(i).equals("-")){
                float temp = Float.parseFloat(listInput.get(i-1).toString());
                float temp2 = Float.parseFloat(listInput.get(i+1).toString());
                float hsl = temp - temp2;
                listInput.set(i, String.valueOf(hsl));
                listInput.remove(i-1);
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

    boolean Addition, mSubtract, multiplication, division,persen;
    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewFrag1 = inflater.inflate(R.layout.fragment_home, container,
                false);
        b0 = (Button)viewFrag1.findViewById(R.id.button0);
        b1 = (Button)viewFrag1.findViewById(R.id.button1);
        b2 = (Button)viewFrag1.findViewById(R.id.button2);
        b3 = (Button)viewFrag1.findViewById(R.id.button3);
        b4 = (Button)viewFrag1.findViewById(R.id.button4);
        b5 = (Button)viewFrag1.findViewById(R.id.button5);
        b6 = (Button)viewFrag1.findViewById(R.id.button6);
        b7 = (Button)viewFrag1.findViewById(R.id.button7);
        b8 = (Button)viewFrag1.findViewById(R.id.button8);
        b9 = (Button)viewFrag1.findViewById(R.id.button9);
        b10 = (Button)viewFrag1.findViewById(R.id.button10);
        buttonAdd = (Button)viewFrag1.findViewById(R.id.buttonadd);
        buttonSub = (Button)viewFrag1.findViewById(R.id.buttonsub);
        buttonMul = (Button)viewFrag1.findViewById(R.id.buttonmul);
        buttonDivision = (Button)viewFrag1.findViewById(R.id.buttondiv);
        buttonC = (Button)viewFrag1.findViewById(R.id.buttonC);
        buttonEqual = (Button)viewFrag1.findViewById(R.id.buttoneql);
        btnNegatif = (Button)viewFrag1.findViewById(R.id.negatifplus);
        btnPersen = (Button)viewFrag1.findViewById(R.id.buttonpersen);
        delete = (Button)viewFrag1.findViewById(R.id.hapus);
        edit = (TextView) viewFrag1.findViewById(R.id.edt1);
        edit2 = (TextView)viewFrag1.findViewById(R.id.edt2);

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
                try {
                    hasil();
                }catch (Exception e){
                    Toast.makeText(getContext(), "Inputan Gagal!", Toast.LENGTH_SHORT).show();
                }

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
            return viewFrag1;
        }

}
