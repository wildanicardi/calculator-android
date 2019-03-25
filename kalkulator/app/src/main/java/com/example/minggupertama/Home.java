package com.example.minggupertama;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b10,buttonAdd, buttonSub, buttonDivision,
            buttonMul, buttonC, buttonEqual;
    TextView edit,edit2;
    float mValueOne, mValueTwo;

    boolean Addition, mSubtract, multiplication, division;
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
        edit = (TextView) viewFrag1.findViewById(R.id.edt1);
        edit2 = (TextView)viewFrag1.findViewById(R.id.edt2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "1");
                edit2.setText(edit2.getText() + "1");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "2");
                edit2.setText(edit2.getText() + "2");
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "3");
                edit2.setText(edit2.getText() + "3");
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "4");
                edit2.setText(edit2.getText() + "4");
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "5");
                edit2.setText(edit2.getText() + "5");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "6");
                edit2.setText(edit2.getText() + "6");
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "7");
                edit2.setText(edit2.getText() + "7");
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "8");
                edit2.setText(edit2.getText() + "8");
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "9");
                edit2.setText(edit2.getText() + "9");
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText(edit.getText() + "0");
                edit2.setText(edit2.getText() + "0");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edit == null) {
                    edit.setText("");
                } else {
                    mValueOne = Float.parseFloat(edit.getText() + "");
                    Addition = true;
                    edit.setText(null);
                    edit2.setText(edit2.getText() + "+");
                }
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit == null){
                    edit.setText("");
                }else {
                    mValueOne = Float.parseFloat(edit.getText() + "");
                    mSubtract = true;
                    edit.setText(null);
                    edit2.setText(edit2.getText() + "-");
                }

            }
        });
        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(edit.getText() + "");
                multiplication = true;
                edit.setText(null);
                edit2.setText(edit2.getText() + "*");
            }
        });
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueOne = Float.parseFloat(edit.getText() + "");
                division = true;
                edit.setText(null);
                edit2.setText(edit2.getText() + "/");
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mValueTwo = Float.parseFloat(edit.getText() + "");

                if (Addition == true) {
                    edit.setText(mValueOne + mValueTwo + "");
                    Addition = false;
                }

                if (mSubtract == true) {
                    edit.setText(mValueOne - mValueTwo + "");
                    mSubtract = false;
                }

                if (multiplication == true) {
                    edit.setText(mValueOne * mValueTwo + "");
                    multiplication = false;
                }

                if (division == true) {
                    edit.setText(mValueOne / mValueTwo + "");
                    division = false;
                }
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
                edit2.setText("");
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText( edit.getText() +".");

            }
        });
            return viewFrag1;
        }

}
