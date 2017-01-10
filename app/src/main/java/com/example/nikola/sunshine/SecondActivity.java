package com.example.nikola.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


/**
 * Created by stefa_000 on 1/2/2017.
 */

public class SecondActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;
    ImageButton btn6;
    String message;
    int j = 1;
    int b = 0;
    boolean isPressed = false;

    @Override
    protected void onPause() {
        if(j == 1 || j == 2 || j == 3 || j == 4 || j == 5) j = 1;
        if(j == 6 || j == 7 || j == 8 || j == 9 || j == 10) j = 6;
        if(j == 11 || j == 12 || j == 13 || j == 14 || j == 15) j = 11;
        if(j == 16 || j == 17 || j == 18 || j == 19 || j == 20) j = 16;
        if(j == 21 || j == 22 || j == 23 || j == 24 || j == 25) j = 21;
        if(j == 31 || j == 30 || j == 29 || j ==28 || j ==27 || j ==26) j = 26;

        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        addListenerOnButtons();
    }

    @Override
    public void onBackPressed() {
        j = 1;
        if(b == 1)
            startActivity(new Intent(this, SecondActivity.class));
        else if(b == 0) startActivity(new Intent(this,  MainActivity.class));
    }

    public void addListenerOnButtons() {
        btn1 = (ImageButton) findViewById(R.id.imgBtn1);
        btn2 = (ImageButton) findViewById(R.id.imgBtn2);
        btn3 = (ImageButton) findViewById(R.id.imgBtn3);
        btn4 = (ImageButton) findViewById(R.id.imgBtn4);
        btn5 = (ImageButton) findViewById(R.id.imgBtn5);
        btn6 = (ImageButton) findViewById(R.id.imgBtn6);
        j = 1;

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    btn1.setImageResource(R.drawable.plane);
                    btn2.setImageResource(R.drawable.ballons);
                    btn3.setImageResource(R.drawable.train);
                    btn4.setImageResource(R.drawable.dove);
                    btn5.setImageResource(R.drawable.tree);
                    btn6.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)btn5.getLayoutParams();
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    btn5.setLayoutParams(params);
                    isPressed = true;
                    b = 1;
                }
                else{
                    Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, j);
                    v.getContext().startActivity(intent);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    btn1.setImageResource(R.drawable.necklace);
                    btn2.setImageResource(R.drawable.hedgehog);
                    btn3.setImageResource(R.drawable.giraffe);
                    btn4.setImageResource(R.drawable.winter);
                    btn5.setImageResource(R.drawable.bell);
                    btn6.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)btn5.getLayoutParams();
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    btn5.setLayoutParams(params);
                    j+=5;
                    isPressed = true;
                    b = 1;
                }
                else{
                    j+=1;
                    Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, j);
                    v.getContext().startActivity(intent);
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    btn1.setImageResource(R.drawable.toys);
                    btn2.setImageResource(R.drawable.strawberry);
                    btn3.setImageResource(R.drawable.truck);
                    btn4.setImageResource(R.drawable.lion);
                    btn5.setImageResource(R.drawable.love);
                    btn6.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)btn5.getLayoutParams();
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    btn5.setLayoutParams(params);
                    j+=10;
                    isPressed = true;
                    b = 1;
                }
                else{
                    j+=2;
                    Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, j);
                    v.getContext().startActivity(intent);
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    btn1.setImageResource(R.drawable.monkey);
                    btn2.setImageResource(R.drawable.rhino);
                    btn3.setImageResource(R.drawable.cheese);
                    btn4.setImageResource(R.drawable.clouds);
                    btn5.setImageResource(R.drawable.panda);
                    btn6.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)btn5.getLayoutParams();
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    btn5.setLayoutParams(params);
                    j+=15;
                    isPressed = true;
                    b =1;
                }
                else{
                    j+=3;
                    Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, j);
                    v.getContext().startActivity(intent);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    btn1.setImageResource(R.drawable.hand);
                    btn2.setImageResource(R.drawable.sun);
                    btn3.setImageResource(R.drawable.tiger);
                    btn4.setImageResource(R.drawable.blanket);
                    btn5.setImageResource(R.drawable.mouth);
                    btn6.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)btn5.getLayoutParams();
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    btn5.setLayoutParams(params);
                    j+=20;
                    isPressed = true;
                    b = 1;
                }
                else{
                    j+=4;
                    Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, j);
                    v.getContext().startActivity(intent);
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    btn1.setImageResource(R.drawable.ball);
                    btn2.setImageResource(R.drawable.helicopter);
                    btn3.setImageResource(R.drawable.flower);
                    btn4.setImageResource(R.drawable.umbrella);
                    btn5.setImageResource(R.drawable.marbles);
                    btn6.setImageResource(R.drawable.hat);
                    j+=25;
                    isPressed = true;
                    b = 1;
                }
                else{
                    j+=5;
                    Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE, j);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

}
