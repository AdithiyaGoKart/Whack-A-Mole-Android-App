package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PostGameActivity extends AppCompatActivity {

    TextView statsText, playAgainText, backToHomeText;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_game);

        statsText = findViewById(R.id.stats);
        playAgainText = findViewById(R.id.playAgainText);
        backToHomeText = findViewById(R.id.backToHome);

        Bundle statsData = getIntent().getExtras();
        if (statsData == null){
            return;
        }
        String scoreOfGame = statsData.getString("scoreOfGame");
        String hitCountOfGame = statsData.getString("hitCountOfGame");
        statsText.setText("Stats:\n\n\nScore: "+scoreOfGame+"\n\nHit Count: "+hitCountOfGame);

        playAgainText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDifficulty();
            }
        });

        backToHomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGoBackHome();
            }
        });
  }
    public void setDifficulty()
    {
        Intent playAgain = new Intent(this, ChoosingDifficultyPage.class);
        startActivity(playAgain);
    }

    public void setGoBackHome() {
        Intent goBackHome = new Intent(this, HomeScreenActivity.class);
        startActivity(goBackHome);
    }
}