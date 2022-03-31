package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

public class HomeScreenActivity extends AppCompatActivity {

    TextView titleText, playText, howToPlayButton, settingsButton;
    String bckgrndDataString1, bckgrndDataString2, bckgrndDataString3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        titleText = findViewById(R.id.titleText);
        playText = findViewById(R.id.playText);
        howToPlayButton = findViewById(R.id.gameRulesText);
        settingsButton = findViewById(R.id.settingsButton);

//        Bundle data = getIntent().getExtras();
//        if (!(data == null)){
//            String bgSender = data.getString("bgSender");
//            Intent intent = new Intent(this, ChoosingDifficultyPage.class);
//            intent.putExtra("bgSender", bgSender);
//        }

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
//        if(!StringUtils.isBlank(bckgrndDataString1))
//            intent.putExtra("bckgrndDataString1", bckgrndDataString1);
//        if(!StringUtils.isBlank(bckgrndDataString2))
//            intent.putExtra("bckgrndDataString2", bckgrndDataString2);
//        if(!StringUtils.isBlank(bckgrndDataString1))
//            intent.putExtra("bckgrndDataString3", bckgrndDataString3);
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