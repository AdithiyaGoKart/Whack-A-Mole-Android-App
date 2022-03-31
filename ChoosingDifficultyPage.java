package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChoosingDifficultyPage extends AppCompatActivity
{
    ConstraintLayout layout;
    Button easyMode, mediumMode, hardMode;
    long intervalChange;
    String bckgrndDataString1, bckgrndDataString2, bckgrndDataString3;
    TextView continueToGame;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_difficulty_page);

        i = new Intent(this, MainActivity.class);

        layout = findViewById(R.id.layoutMain);

        easyMode = findViewById(R.id.easyDiffButton);
        mediumMode = findViewById(R.id.mediumDiffButton);
        hardMode = findViewById(R.id.hardDiffButton);

        Bundle moveBckgrndData = getIntent().getExtras();
        if (moveBckgrndData != null){
            String bgSender = moveBckgrndData.getString("bgSender");
            i.putExtra("bgSender", bgSender);
        }

        easyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intervalChange = 2500;
                creatingContinueText();
            }
        });
        mediumMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intervalChange = 2000;
                creatingContinueText();
            }
        });
        hardMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intervalChange = 1500;
                creatingContinueText();
            }
        });
    }
    @SuppressLint("SetTextI18n")
    public void creatingContinueText()
    {
        continueToGame = new TextView(this);
        continueToGame.setId(View.generateViewId());
        continueToGame.setText("CONTINUE");
        continueToGame.setTextSize(25);
        continueToGame.setTextColor(Color.WHITE);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*180,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*180);
        continueToGame.setLayoutParams(params);
        Log.d("check", "check");

        runOnUiThread(() -> {
            continueToGame.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right));
            layout.addView(continueToGame);
        });

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(continueToGame.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP);
        constraintSet.connect(continueToGame.getId(), ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(continueToGame.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT);
        constraintSet.connect(continueToGame.getId(), ConstraintSet.RIGHT, layout.getId(), ConstraintSet.RIGHT);

        constraintSet.setHorizontalBias(continueToGame.getId(), 0.5f);
        constraintSet.setVerticalBias(continueToGame.getId(), 1f);

        constraintSet.applyTo(layout);

        continueToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeBetweenJump = intervalChange+"";
                i.putExtra("timeBetweenJump", timeBetweenJump);
                startActivity(i);
            }
        });
    }
}
