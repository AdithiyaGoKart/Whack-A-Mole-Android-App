package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView hitText, scoreText, timerText;
    private long min, sec;
    private int hitcount, score, bombCounter, superChargeCounter, bombDecider;
    private int livesCounter = 5;
    Timer alienTimer, bombTimer;
    private List<ImageView> blackHoles = new ArrayList<>();
    private List<ImageView> hearts = new ArrayList<>();
    private final List<Pair<Float, Float>> alienOffsetList = new ArrayList<>();
    private final List<Pair<Float, Float>> bombOffsetList = new ArrayList<>();
    ImageView alien, bomb, bgSet1, bgSet2, bgSet3;
    int alienIndex = -1;
    int bombIndex = -1;
    private final Random random = new Random();
    private long intervalChange;
    NumberFormat nf;
    TimerTask alienTimerTask, bombTimerTask;
    ConstraintLayout layoutMain;
    Resources res;
    String bg;


    public MainActivity() {
        //hole1
        Pair pair = new Pair(.015f, .15f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole2
        pair = new Pair(.53f, .15f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole3
        pair = new Pair(1.025f, .16f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole4
        pair = new Pair(.01f, .425f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole5
        pair = new Pair(.53f, .42f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole6
        pair = new Pair(1.025f, .42f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole7
        pair = new Pair(.05f, .68f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole8
        pair = new Pair(.54f, .68f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
        //hole9
        pair = new Pair(1f, .7f);
        alienOffsetList.add(pair);
        bombOffsetList.add(pair);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getApplicationContext().getResources();

        Intent goToPostGameScreen = new Intent(this, PostGameActivity.class);

        layoutMain = findViewById(R.id.layoutMain);

        hitText = findViewById(R.id.hitCountText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timer);

        blackHoles.add(findViewById(R.id.placeHolder1));
        blackHoles.add(findViewById(R.id.placeHolder2));
        blackHoles.add(findViewById(R.id.placeHolder3));
        blackHoles.add(findViewById(R.id.placeHolder4));
        blackHoles.add(findViewById(R.id.placeHolder5));
        blackHoles.add(findViewById(R.id.placeHolder6));
        blackHoles.add(findViewById(R.id.placeHolder7));
        blackHoles.add(findViewById(R.id.placeHolder8));
        blackHoles.add(findViewById(R.id.placeHolder9));

        hearts.add(findViewById(R.id.heart1));
        hearts.add(findViewById(R.id.heart2));
        hearts.add(findViewById(R.id.heart3));
        hearts.add(findViewById(R.id.heart4));
        hearts.add(findViewById(R.id.heart5));

        // Set the black holes into the layout from the list.
        for(ImageView imageView : blackHoles) {
            imageView.setImageResource(R.drawable.blackholewhackamole);
        }

        // Set the hearts into the layout from the list.
        for (ImageView imageView : hearts){
            imageView.setImageResource(R.drawable.heartnobg);
        }

        Bundle getValues = getIntent().getExtras();
        if (getValues != null){
            String timeBetweenJump = getValues.getString("timeBetweenJump");
            bg = getValues.getString("bgSender");
            if (bg == (null)){
                layoutMain.setBackgroundResource(R.drawable.whackamolespacebackground);
            }
            else if (bg.equals("1")){
                layoutMain.setBackgroundResource(R.drawable.spacebgtwo);
            }
            else if (bg.equals("2")){
                layoutMain.setBackgroundResource(R.drawable.spacebgthree);
            }
            else if (bg.equals("3")){
                layoutMain.setBackgroundResource(R.drawable.spacebgfive);
            }
            intervalChange = Integer.parseInt(timeBetweenJump);
        }

        createAlienAndUpdateScore();
        bombAddition();

        System.out.println(intervalChange);

        new CountDownTimer(61000, 1000){
            @SuppressLint("SetTextI18n")
            public void onTick (long millisUntilFinished){
                nf = new DecimalFormat("00");
                min = (millisUntilFinished/60000) % 60;
                sec = (millisUntilFinished/1000) % 60;
                timerText.setText(nf.format(min)+":"+nf.format(sec));
                if (livesCounter == 0){
                    cancel();
                }
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                timerText.setText("00:00");
                finish();
                String scoreOfGame = score+"";
                String hitCountOfGame = hitcount+"";
                goToPostGameScreen.putExtra("scoreOfGame", scoreOfGame);
                goToPostGameScreen.putExtra("hitCountOfGame", hitCountOfGame);
                startActivity(goToPostGameScreen);
            }
        }.start();
    }

    private synchronized void createAlienAndUpdateScore() {
        alienIndex = random.nextInt(9);
        System.out.println("Calling alien with new index: '" + alienIndex + "'");

        if (bombIndex == alienIndex){
            if (bombIndex <= 7){
                bombIndex+=1;
            }
            else{
                if (bombIndex != 0){
                    bombIndex-=1;
                }
                else{
                    bombIndex+=1;
                }
            }
        }

        alien = new ImageView(this);
        alien.setId(View.generateViewId());
        alien.setImageResource(R.drawable.alien2);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*180,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*180);
        alien.setLayoutParams(params);
        Log.d("check", "check");

        runOnUiThread(() -> {
            alien.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in));
            layoutMain.addView(alien);
        });

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layoutMain);
        constraintSet.connect(alien.getId(), ConstraintSet.TOP, layoutMain.getId(), ConstraintSet.TOP);
        constraintSet.connect(alien.getId(), ConstraintSet.BOTTOM, layoutMain.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(alien.getId(), ConstraintSet.LEFT, layoutMain.getId(), ConstraintSet.LEFT);
        constraintSet.connect(alien.getId(), ConstraintSet.RIGHT, layoutMain.getId(), ConstraintSet.RIGHT);

        Pair<Float, Float> offset = alienOffsetList.get(alienIndex);

        constraintSet.setHorizontalBias(alien.getId(), offset.first);
        constraintSet.setVerticalBias(alien.getId(), offset.second);

        constraintSet.applyTo(layoutMain);

        Intent i2 = new Intent(this, PostGameActivity.class);
        // From this run() method, call the code to reset the alien while updating the score.
        alienTimerTask = new TimerTask() {
            @Override
            public void run() {
                // Removes alien from one location and adds to another after time limit per location is set
                runOnUiThread(() -> {
                    alien.setImageResource(R.drawable.pownobg);
                    vanishAlienLocation();
                    if(!alien.isPressed()){
                        livesCounter--;
                        if (livesCounter == 4){
                            hearts.get(4).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
                            hearts.get(4).setVisibility(View.INVISIBLE);
                        }
                        else if (livesCounter == 3){
                            hearts.get(3).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
                            hearts.get(3).setVisibility(View.INVISIBLE);
                        }
                        else if (livesCounter == 2){
                            hearts.get(2).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
                            hearts.get(2).setVisibility(View.INVISIBLE);
                        }
                        else if (livesCounter == 1){
                            hearts.get(1).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
                            hearts.get(1).setVisibility(View.INVISIBLE);
                        }
                        if (livesCounter == 0){
                            hearts.get(0).startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
                            hearts.get(0).setVisibility(View.INVISIBLE);
                            String scoreOfGame = score+"";
                            String hitCountOfGame = hitcount+"";
                            i2.putExtra("scoreOfGame", scoreOfGame);
                            i2.putExtra("hitCountOfGame", hitCountOfGame);
                            startActivity(i2);
                        }
                    }
                    alienIndex = random.nextInt(9);
                });
            }
        };

        alienTimer = new Timer();
        //sets how long alien is going to be at each hole
        alienTimer.schedule(alienTimerTask, intervalChange);

        alien.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                hitcount++;
                vanishAlienLocation();
                superChargeCounter++;
                if (superChargeCounter >= 8){
                    Toast toast = Toast.makeText(getApplicationContext(), "SUPER CHARGE MODE: ON", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    score+=40;
                    scoreText.setText("Score: "+score);
                    if (superChargeCounter == 17){
                        superChargeCounter = 0;
                        Toast.makeText(getApplicationContext(), "SUPER CHARGE MODE: OFF", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    score+=20;
                    hitText.setText("Hit Count: "+hitcount);
                    scoreText.setText("Score: "+score);
                }
            }
        });

//        int bombDecision = random.nextInt(20+1);
//        if (bombDecision % 5 == 0) {
//            bombAddition();
//        }
    }

    private synchronized void vanishAlienLocation() {
        System.out.println("Alien Index: '" + alienIndex + "'");
        runOnUiThread(() -> {
            alien.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
            layoutMain.removeView(alien);
            System.out.println("Reached here to remove alien of index: '" + alienIndex + "'");
        });
        alienTimer.cancel();
        alienTimer = null;

        createAlienAndUpdateScore();
    }
    private synchronized void bombAddition()
    {
        bombDecider = random.nextInt(10+1);

        bombIndex = random.nextInt(9);
        System.out.println("Calling bomb with new index: '" + bomb + "'");

        if (bombIndex == alienIndex){
            if (bombIndex <= 7){
                bombIndex+=1;
            }
            else{
                if (bombIndex != 0){
                    bombIndex-=1;
                }
                else{
                    bombIndex+=1;
                }
            }
        }

        bomb = new ImageView(this);
        bomb.setId(View.generateViewId());
        bomb.setImageResource(R.drawable.bombnobg);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*180,
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT_PERCENT*180);
        bomb.setLayoutParams(params);
        Log.d("check", "check");

        runOnUiThread(() -> {
            bomb.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in));
            layoutMain.addView(bomb);
        });

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layoutMain);
        constraintSet.connect(bomb.getId(), ConstraintSet.TOP, layoutMain.getId(), ConstraintSet.TOP);
        constraintSet.connect(bomb.getId(), ConstraintSet.BOTTOM, layoutMain.getId(), ConstraintSet.BOTTOM);
        constraintSet.connect(bomb.getId(), ConstraintSet.LEFT, layoutMain.getId(), ConstraintSet.LEFT);
        constraintSet.connect(bomb.getId(), ConstraintSet.RIGHT, layoutMain.getId(), ConstraintSet.RIGHT);

        Pair<Float, Float> offset = bombOffsetList.get(bombIndex);

        constraintSet.setHorizontalBias(bomb.getId(), offset.first);
        constraintSet.setVerticalBias(bomb.getId(), offset.second);

        constraintSet.applyTo(layoutMain);

        bombTimerTask = new TimerTask() {
            @Override
            public void run() {
                // Removes alien from one location and adds to another after time limit per location is set
                runOnUiThread(() -> {
                    vanishBombLocation();
                    bombIndex = random.nextInt(9);
                });
            }
        };

        bombTimer = new Timer();
        //sets how long alien is going to be at each hole
        bombTimer.schedule(bombTimerTask, intervalChange);

        bomb.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                bomb.setImageResource(R.drawable.pownobg);
                vanishBombLocation();
                score = score-50;
                scoreText.setText("Score: "+score);
            }
        });
    }
    private synchronized void vanishBombLocation() {
        System.out.println("Bomb Index: '" + bombIndex + "'");
        runOnUiThread(() -> {
            bomb.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out));
            layoutMain.removeView(bomb);
            System.out.println("Reached here to remove bomb of index: '" + bombIndex + "'");
        });
        bombTimer.cancel();
        bombTimer = null;

        bombAddition();
    }
    @Override
    protected void onStart() {
        Log.d("LIFECYCLE TAG", "onStart()");
        super.onStart();
    }
    @Override
    protected void onPause() {
        Log.d("LIFECYCLE TAG", "onPause()");
        super.onPause();
    }
    @Override
    protected void onResume() {
        Log.d("LIFECYCLE TAG", "onResume()");
        super.onResume();
    }
    @Override
    protected void onRestart() {
        Log.d("LIFECYCLE TAG", "onRestart()");
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE TAG", "onDestroy()");
        super.onDestroy();
    }
}
