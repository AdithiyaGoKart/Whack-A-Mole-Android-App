package com.example.whackamoleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RulePageActivity extends AppCompatActivity {

    TextView rulesList, rulesTitle, goHomeText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_page);

        rulesList = findViewById(R.id.rulesList);
        rulesTitle = findViewById(R.id.rulesTitle);
        goHomeText = findViewById(R.id.goHome);

        rulesList.setText("- Whack the mole to increase your score and hit count\n\n- The score increases in increments of 20 and hit count" +
                "by 1\n\n- If you don't hit an alien, you lose a life; if you lose 5 lives, it's game over!");

        goHomeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGoHomeText();
            }
        });

    }
    public void setGoHomeText()
    {
        Intent i = new Intent(this, HomeScreenActivity.class);
        startActivity(i);
    }
}