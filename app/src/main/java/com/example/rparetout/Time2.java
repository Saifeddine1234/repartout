package com.example.rparetout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
public class Time2 extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    TextView textT , textD ,t1,t2;
    SeekBar seekBar,seekBar2;
    String txt1,txt2,txt3 ,time2,time1 ;
    Button B1 , dacc;
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        textD.setText(" " +currentDateString);
        txt1 = textD.getText().toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        B1 = findViewById(R.id.button3);
        textD = findViewById(R.id.textD);
        textT = findViewById(R.id.textT);
        seekBar = findViewById(R.id.seekBar);
        seekBar2 = findViewById(R.id.seekBar2);
        t2 = findViewById(R.id.textT5);
        t1 = findViewById(R.id.textT);

        dacc = findViewById(R.id.dacc);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dataPicker = new DatePickerFragment();
                dataPicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t2.setText(String.valueOf(progress));
                txt2 = t2.getText().toString();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t1.setText(String.valueOf(progress));
                txt3 = t1.getText().toString();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        dacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textD.getText().toString().equals("")) {
                    Toast.makeText(Time2.this, "select un Date", Toast.LENGTH_SHORT).show();
                } else if (t2.getText().toString().equals("00") || t1.getText().toString().equals("00")) {
                    Toast.makeText(Time2.this, "Select l'interval de temps", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(txt3) < Integer.parseInt(txt2)) {
                    Toast.makeText(Time2.this, "l'interval est incorrect", Toast.LENGTH_SHORT).show();

                } else {
                    time2 = textD.getText().toString() + " " + t2.getText().toString() + "H" + " - " + t1.getText().toString() + "H";
                    Intent inten = getIntent();
                    String nomC = inten.getStringExtra("nom");
                    String prenomC = inten.getStringExtra("prenom");
                    String telephoneC = inten.getStringExtra("telephone");
                    String emailC = inten.getStringExtra("email");
                    String motdepasseC = inten.getStringExtra("motdepasse");
                    String T1 = inten.getStringExtra("T1");
                    String T2 = inten.getStringExtra("T2");
                    time1 = inten.getStringExtra("txt");
                   // time2 = inten.getStringExtra("txt2");
                    String T3 = inten.getStringExtra("T3");
                    String T4 = inten.getStringExtra("T4");
                    String T5 = inten.getStringExtra("T5");
                    String T6 = inten.getStringExtra("T6");
                    Intent intent = new Intent(getApplicationContext(), Demande2.class);
                    intent.putExtra("nom", nomC);
                    intent.putExtra("prenom", prenomC);
                    intent.putExtra("telephone", telephoneC);
                    intent.putExtra("email", emailC);
                    intent.putExtra("motdepasse", motdepasseC);
                    intent.putExtra("txt", time1);
                    intent.putExtra("T1", T1);
                    intent.putExtra("T2", T2);
                    intent.putExtra("T3", T3);
                    intent.putExtra("T4", T4);
                    intent.putExtra("T5", T5);
                    intent.putExtra("T6", T6);
                    intent.putExtra("txt2", time2);
                    startActivity(intent);

                }

            }
        });
    }}








