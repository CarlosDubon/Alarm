package com.example.carlos.alarm;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public TextView hora;
    public TextView setTime;
    public EditText description;
    public ImageButton config;

    public int mHour,mMinutes;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinutes = minute;
        hora.setText(mHour + " : " + mMinutes);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = findViewById(R.id.description);
        hora = findViewById(R.id.hour);
        setTime = findViewById(R.id.setTime);
        config = findViewById(R.id.config);

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, description.getText().toString())
                        .putExtra(AlarmClock.EXTRA_HOUR,mHour)
                        .putExtra(AlarmClock.EXTRA_MINUTES,mMinutes)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI,true);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        setTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


    }
}
