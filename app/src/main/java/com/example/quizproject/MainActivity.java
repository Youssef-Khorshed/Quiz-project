package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button start;
    int q1, q2, q3, q4, q5;

    void calling() {
        start = findViewById(R.id.start_btn);
    }

    void hand() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog p = new ProgressDialog(MainActivity.this);
                p.setTitle("Loading");
                p.setMessage("wait...");
                p.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            p.dismiss();
                            startActivity(new Intent(MainActivity.this, Quiz_site1.class));

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calling();
        hand();

    }
}