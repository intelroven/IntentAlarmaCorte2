package com.cdlc.formativa1term2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button  programar;
    EditText hora,mensaje;
    Switch vibracion,tono;
    RadioGroup grupoDias;
    RadioButton lunes,martes,miercoles,jueves,viernes,sabado,domingo;
    TimePicker time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tono = findViewById(R.id.sw_tono);
        tono.setOnClickListener(MainActivity.this);

        programar = findViewById(R.id.btn_programar);
        programar.setOnClickListener(MainActivity.this);

        time= findViewById(R.id.alarmTimePicker);
        time.setOnClickListener(this);


        mensaje= findViewById(R.id.txt_message);
        lunes= findViewById(R.id.rd_lunes);
        martes= findViewById(R.id.rd_martes);
        miercoles= findViewById(R.id.rd_miercoles);
        jueves= findViewById(R.id.rd_jueves);
        viernes= findViewById(R.id.rd_viernes);
        sabado= findViewById(R.id.rd_sabado);
        domingo= findViewById(R.id.rd_domingo);

        vibracion = findViewById(R.id.rd_vibracion);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_programar:
                int hora= time.getHour();
                int minuto = time.getMinute();
               String mens= mensaje.getText().toString();
               boolean vibrar =vibracion.isChecked();
               String tonos="";
               if(tono.isChecked()){
                    tonos= "content://settings/system/alarm_alert";
               }
                ArrayList <Integer> dias = new ArrayList<Integer>();

                    if(lunes.isChecked()){
                    dias.add(Calendar .MONDAY);}

                    if(martes.isChecked()){
                        dias.add(Calendar.TUESDAY);
                    }

                    if(miercoles.isChecked()){
                        dias.add(Calendar .WEDNESDAY);
                    }

                    if(jueves.isChecked()){
                        dias.add(Calendar .THURSDAY);
                    }

                    if(viernes.isChecked()){
                        dias.add(Calendar .FRIDAY);
                    }

                    if(sabado.isChecked()){
                        dias.add(Calendar .SATURDAY);
                    }

                    if(sabado.isChecked()){
                        dias.add(Calendar .SUNDAY);
                    }



                createAlarm(mens,hora,minuto,vibrar,dias,tonos);
                break;



        }

    }

    public void createAlarm(String message, int hour, int minutes, boolean vibra, ArrayList <Integer> dias, String tono) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                .putExtra(AlarmClock.EXTRA_VIBRATE,vibra)
                .putExtra(AlarmClock.EXTRA_DAYS, dias)
                .putExtra(AlarmClock.EXTRA_RINGTONE,tono);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
