package com.example.alexv.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float bill;
    float tip;
    float total;
    int split;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        // perform seek bar change listener event used for getting the progress value
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               //Updates tipAmount view with the value of the progess bar in percent form
                TextView tipAmount = (TextView) findViewById(R.id.tipAmount);
                progressChangedValue = progress;
                String y = Integer.toString(progressChangedValue);
                tipAmount.setText(y + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                //Find and store views declared in the xml file
                EditText userInput = (EditText) findViewById(R.id.userInput);
                EditText splitAmount = (EditText) findViewById(R.id.splitAmount);
                TextView totalBill = (TextView) findViewById(R.id.totalBill);
                TextView tipAmount = (TextView) findViewById(R.id.tipAmount);


                String string1 = "";
                String string2 = userInput.getText().toString();
                if(string2.equals(string1) || string2.equals(".")) // Checks for invalid inputs
                    bill = 0;
                else
                    bill = Float.parseFloat(userInput.getText().toString());

                tip = (bill * progressChangedValue) /100;

                split = Integer.parseInt(splitAmount.getText().toString());
                if(split == 0) {
                    split = 1;
                    splitAmount.setText("1");
                }

                //Updates views
                total = ((bill + tip) / split);
                String x = String.format("%.2f", total);
                totalBill.setText(x);
                String y = Integer.toString(progressChangedValue);
                String z = String.format("%.2f", tip);
                tipAmount.setText(y + "% = " + z +  "$");
            }
        });
    }

    public void clearOnClick(View view) {
        EditText userInput = (EditText) findViewById(R.id.userInput);
        EditText splitAmount = (EditText) findViewById(R.id.splitAmount);
        TextView totalBill = (TextView) findViewById(R.id.totalBill);
        TextView tipAmount = (TextView) findViewById(R.id.tipAmount);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        //Resets views values 
        userInput.setText("");
        splitAmount.setText("1");
        tipAmount.setText("0.0");
        totalBill.setText("0.0");
        seekBar.setProgress(0);

    }


}
