package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timer,score,ques,gameOver,result;
    Button button1 , button2 , button3 , button4 , playAgain;
    int n1,n2,sum,btnnum,quesCount=0,ansCount=0,a1,a2,a3,a4,correct=0;
    Random random;
    CountDownTimer cm;

    public void playAgain(View view)
    {
        randomNumber();
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        playAgain.setVisibility(View.INVISIBLE);
        gameOver.setVisibility(View.INVISIBLE);
        quesCount=0;
        ansCount=0;
        score.setText("0 / 0");

        cm.start();

    }


    public void randomNumber()
    {
        random = new Random();
        n1 = random.nextInt(100+1);
        n2 = random.nextInt(100+1);
        sum=n1+n2;

        ques.setText(""+n1+" + "+n2);

        Button[] arr = {button1, button2, button3, button4};
        btnnum=random.nextInt(4);
        arr[btnnum].setText(""+sum);

        for(int i=0;i<4;++i)
        {
            int rr = random.nextInt(201);
            if(i!=btnnum)
                if(rr!=sum)
                    arr[i].setText(""+rr);
                else
                    i--;
        }
    }

    public void check(View view)
    {

        quesCount++;

        a1=Integer.parseInt(button1.getText().toString());
        a2=Integer.parseInt(button2.getText().toString());
        a3=Integer.parseInt(button3.getText().toString());
        a4=Integer.parseInt(button4.getText().toString());

        if(view.getId()==R.id.button1)
        {
            if(a1==sum) {
                correct = 1;
                ansCount++;
            }
        }

        else if(view.getId()==R.id.button2)
        {
            if(a2==sum) {
                correct = 1;
                ansCount++;
            }
        }

        else if(view.getId()==R.id.button3)
        {
            if(a3==sum) {
                correct = 1;
                ansCount++;
            }
        }

        else if(view.getId()==R.id.button4)
        {
            if(a4==sum) {
                correct = 1;
                ansCount++;
            }
        }


        if(correct==1) {
            result.setText("Correct!");
            correct=0;
        }
        else
            result.setText("Wrong!");

        score.setText(""+ansCount+" / "+quesCount);

        randomNumber();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        ques = (TextView) findViewById(R.id.ques);
        result = (TextView) findViewById(R.id.result);
        gameOver = (TextView) findViewById(R.id.gameOver);
        playAgain = (Button) findViewById(R.id.playAgain);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        playAgain = (Button) findViewById(R.id.playAgain);


        gameOver.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        randomNumber();

        score.setText("0 / 0");

        cm = new CountDownTimer(30000,1000)
        {
            public void onTick(long mil)
            {
                timer.setText(String.valueOf(mil/1000));
            }
            public void onFinish()
            {
                gameOver.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();





    }
}
