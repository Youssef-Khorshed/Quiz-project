package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Quiz_site4 extends AppCompatActivity {


    TextView time_no, q_text, q_no, answer1, answer2, answer3;
    RadioButton ans_1, ans_2, ans_3;
    RadioGroup res;
    Button confirm;
    CountDownTimer count_down;
    int check = 0, conf_visible = View.VISIBLE, conf_not = View.INVISIBLE, check_conf = 0;
    long count_down_in_meils = 30000, checker_result = -1;

    void calling() {
        time_no = findViewById(R.id.time_no);
        q_no = findViewById(R.id.q_no);
        q_text = findViewById(R.id.q_text);
        confirm = findViewById(R.id.confirm);
        res = findViewById(R.id.res);
        ans_1 = findViewById(R.id.ans_1);
        ans_2 = findViewById(R.id.ans_2);
        ans_3 = findViewById(R.id.ans_3);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
    }

    void time() {
        confirm.setVisibility(View.INVISIBLE);
        check_conf = conf_not;
        count_down = new CountDownTimer(count_down_in_meils, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                count_down_in_meils = millisUntilFinished;
                updateCountDownText();
            }


            @Override
            public void onFinish() {

            }
        }.start();
    }

    void updateCountDownText() {

        int minutes = (int) (count_down_in_meils / 1000) / 60;
        int seconds = (int) (count_down_in_meils / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        time_no.setText(timeFormatted);
        confirm.setVisibility(View.VISIBLE);

        if (count_down_in_meils <= 20000 && count_down_in_meils > 10000) {

            time_no.setTextColor(Color.parseColor("#FFFDD835"));
        } else if (count_down_in_meils <= 10000 && count_down_in_meils >= 1000) {
            time_no.setTextColor(Color.parseColor("#F60B0B"));
        } else if (count_down_in_meils < 1000) {
            if (ans_3.isChecked()) check = 1;
            res.setVisibility(View.INVISIBLE);
            time_no.setVisibility(View.INVISIBLE);
            answer1.setVisibility(View.VISIBLE);
            answer2.setVisibility(View.VISIBLE);
            answer3.setVisibility(View.VISIBLE);
        }
    }

    void btn() {
        res.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checker_result = checkedId;
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_conf = conf_visible;
                res.setVisibility(View.INVISIBLE);
                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                time_no.setVisibility(View.INVISIBLE);
                Bundle b= getIntent().getExtras();
                int x = b.getInt("q3");
                if (ans_3.isChecked()) check = 1;
                check+=x;
                Toast.makeText(getApplicationContext(),"correct: "+check,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Quiz_site4.this, Quiz_site5.class).putExtra("q4", check));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_site4);
        calling();
        time();
        btn();
    }

    @Override
    protected void onPause() {

        if (check_conf == conf_visible) {
            res.setVisibility(View.INVISIBLE);
            time_no.setVisibility(View.INVISIBLE);
            answer1.setVisibility(View.VISIBLE);
            answer2.setVisibility(View.VISIBLE);
            answer3.setVisibility(View.VISIBLE);
        }
        super.onPause();

    }
}