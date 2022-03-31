package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton option1, option2, option3;
    String option1Text, option2Text, option3Text;
    String bckgrnd1, bckgrnd2, bckgrnd3;
    ConstraintLayout layoutMain;
    Button goBackHome, save;
    String bgSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent i = new Intent(this, HomeScreenActivity.class);
        Intent i2 = new Intent(this, MainActivity.class);

        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.bgchoice1);
        option2 = findViewById(R.id.bgchoice2);
        option3 = findViewById(R.id.bgchoice3);
        layoutMain = findViewById(R.id.layoutMain);
        goBackHome = findViewById(R.id.goBackHomeButton);
        save = findViewById(R.id.saveButton);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.bgchoice1){
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String bgSender = "1";
                            i2.putExtra("bgSender", bgSender);
                            Toast.makeText(SettingsActivity.this, "Info Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (i == R.id.bgchoice2){
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String bgSender = "2";
                            i2.putExtra("bgSender", bgSender);
                            Toast.makeText(SettingsActivity.this, "Info Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if (i == R.id.bgchoice3){
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String bgSender = "3";
                            i2.putExtra("bgSender", bgSender);
                            Toast.makeText(SettingsActivity.this, "Info Saved", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        goBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }
}
