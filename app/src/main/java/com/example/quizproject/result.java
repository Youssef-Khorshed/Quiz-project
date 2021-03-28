package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class result extends AppCompatActivity {
    TextView score;
    ImageButton home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);

        score = findViewById(R.id.score);
        home = findViewById(R.id.my_home);
        savedInstanceState = getIntent().getExtras();
        int x5 = savedInstanceState.getInt("q5");
        score.setText("score: " +x5);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(result.this,MainActivity.class));
            }
        });

    }
}