package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class HomeScreenActivity extends AppCompatActivity {

    TextView titleText, playText, howToPlayButton, settingsButton;
    String bckgrndDataString1, bckgrndDataString2, bckgrndDataString3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Intent intent = new Intent(this, ChoosingDifficultyPage.class);

        titleText = findViewById(R.id.titleText);
        playText = findViewById(R.id.playText);
        howToPlayButton = findViewById(R.id.gameRulesText);
        settingsButton = findViewById(R.id.settingsButton);

        playText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDifficultyChoosingPage();
            }
        });

        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRulePage();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettingsPage();
            }
        });

    }
    public void goToDifficultyChoosingPage()
    {
        Intent intent = new Intent(this, ChoosingDifficultyPage.class);
        Bundle data = getIntent().getExtras();
        if (data != null){
            String bgSender = data.getString("bgSender");
            Toast.makeText(this, bgSender, Toast.LENGTH_SHORT).show();
            intent.putExtra("bgSender", bgSender);

        }
        startActivity(intent);
    }
    public void goToRulePage()
    {
        Intent intent2 = new Intent(this, RulePageActivity.class);
        startActivity(intent2);
    }
    public void goToSettingsPage()
    {
        Intent intent3 = new Intent(this, SettingsActivity.class);
        startActivity(intent3);
    }
}
