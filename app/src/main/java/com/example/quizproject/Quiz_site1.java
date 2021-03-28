package com.example.quizproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static com.example.quizproject.R.color.black;
import static com.example.quizproject.R.color.red;
import static com.example.quizproject.R.color.white;

public class Quiz_site1 extends AppCompatActivity {

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

    @SuppressLint("Range")
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
            check_conf = conf_visible;
            res.setVisibility(View.INVISIBLE);
            answer1.setVisibility(View.VISIBLE);
            answer2.setVisibility(View.VISIBLE);
            answer3.setVisibility(View.VISIBLE);
            time_no.setVisibility(View.INVISIBLE);

        }

    }

    void btn() {


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_conf = conf_visible;
                res.setVisibility(View.INVISIBLE);
                answer1.setVisibility(View.VISIBLE);
                answer2.setVisibility(View.VISIBLE);
                answer3.setVisibility(View.VISIBLE);
                time_no.setVisibility(View.INVISIBLE);

                if (ans_1.isChecked()) check = 1;
                Toast.makeText(getApplicationContext(),"correct: "+check,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Quiz_site1.this, Quiz_site2.class).putExtra("q1", check));

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_site1);
        calling();
        time();
        btn();


    }


    @SuppressLint("Range")
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