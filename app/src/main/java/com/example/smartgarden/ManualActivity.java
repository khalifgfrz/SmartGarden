package com.example.smartgarden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManualActivity extends AppCompatActivity {

    ToggleButton pumpbuttons, exbuttons, lightbuttons;
    Button menu;
    SeekBar con_ex, con_pump;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);

        menu = findViewById(R.id.back3);
        pumpbuttons = findViewById(R.id.pumpbutton);
        exbuttons = findViewById(R.id.exbutton);
        lightbuttons = findViewById(R.id.lightbutton);
        con_ex = findViewById(R.id.bar_ex);
        con_pump = findViewById(R.id.bar_pump);

        //Menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ManualActivity.this, MenuActivity.class);
                ManualActivity.this.startActivity(i);
            }
        });

        //--------------------------FIREBASE------------------------------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mode");
        myRef.setValue(1); // MODE

        pumpbuttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pumpbuttons.isChecked()) {
                    DatabaseReference puon = database.getReference("control").child("pumpState");
                    puon.setValue(1);
                } else {
                    DatabaseReference puoff = database.getReference("control").child("pumpState");
                    puoff.setValue(0);
                }
            }
        });

        exbuttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(exbuttons.isChecked()) {
                    DatabaseReference fanon = database.getReference("control").child("fanState");
                    fanon.setValue(1);
                } else {
                    DatabaseReference fanoff = database.getReference("control").child("fanState");
                    fanoff.setValue(0);
                }
            }
        });

        lightbuttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lightbuttons.isChecked()) {
                    DatabaseReference lighton = database.getReference("control").child("ledState");
                    lighton.setValue(1);
                } else {
                    DatabaseReference lioff = database.getReference("control").child("ledState");
                    lioff.setValue(0);
                }
            }
        });

        //CONTROL FAN
        con_ex.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                DatabaseReference myRef3 = database.getReference("control").child("fanSpeed");
                myRef3.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //CONTROL PUMP
        con_pump.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                DatabaseReference myRef4 = database.getReference("control").child("pumpSpeed");
                myRef4.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}