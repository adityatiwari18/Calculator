package com.example.aditya.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText Newnumber;
    private TextView operation;
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";
    private static final String OP = "";
    private static final String operand = "operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (EditText) findViewById(R.id.result);
        Newnumber = (EditText) findViewById(R.id.Newnumber);
        operation = (TextView) findViewById(R.id.operation);

        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button2 = (Button) findViewById(R.id.button2);
        Button buttondot = (Button) findViewById(R.id.buttonDot);
        Button equal = (Button) findViewById(R.id.buttonEquals);
        Button plus = (Button) findViewById(R.id.buttonAdd);
        Button minus = (Button) findViewById(R.id.buttonSub);
        Button multiply = (Button) findViewById(R.id.buttonMultiply);
        Button division = (Button) findViewById(R.id.buttonDivide);
        Button buttonneg = (Button) findViewById(R.id.buttonneg);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                Newnumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);

        View.OnClickListener oplistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = Newnumber.getText().toString();
                try {
                    Double doublevalue = Double.valueOf(value);
                    performoperation(doublevalue, op);

                } catch (NumberFormatException e) {
                    Newnumber.setText("");
                }
                pendingOperation = op;
                operation.setText(pendingOperation);

            }
        };
        equal.setOnClickListener(oplistener);
        plus.setOnClickListener(oplistener);
        minus.setOnClickListener(oplistener);
        multiply.setOnClickListener(oplistener);
        division.setOnClickListener(oplistener);

        View.OnClickListener neglistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performoperation(-1, "*");
            }
        };
        buttonneg.setOnClickListener(neglistener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(OP, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(operand, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString("OP");
        operand1 = savedInstanceState.getDouble(operand);
        operation.setText(pendingOperation);
    }

    public void performoperation(double value, String op) {
        if (operand1 == null) {
            operand1 = value;
        } else {
            operand2 = value;
            if (pendingOperation.equals("=")) {
                pendingOperation = op;
            }


            switch (pendingOperation) {

                case "=":
                    operand1 = operand2;
                    break;

                case "/":
                    if (operand2 == 0.0)
                        operand1 = -9999999999999.99;
                    else
                        operand1/=operand2;
                    break;

                case "*":
                    operand1 *= operand2;
                    break;

                case "-":
                    operand1 -= operand2;
                    break;

                case "+":
                    operand1 += operand2;
                    break;
            }
        }
        result.setText(operand1.toString());
        Newnumber.setText("");


    }
}

