package com.example.nikola.sunshine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    public static final String APP_ID = "com.example.nikola.testapplication";
    private static String LANGUAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent a = new Intent(this, LearningModeActivity.class);
        startActivity(a);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // setting up the fade in animation for the buttons and textboxes
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator()); //add this
        fadeIn.setDuration(2000);

        // setting up the controls
        Button button_lang_eng = (Button) findViewById(R.id.button_lang_eng);
        Button button_lang_mk = (Button) findViewById(R.id.button_lang_mk);
        TextView textView_lang_eng = (TextView) findViewById(R.id.textView_lang_eng);
        TextView textView_lang_mk = (TextView) findViewById(R.id.textView_lang_mk);

        // playing the animation
        button_lang_eng.startAnimation(fadeIn);
        button_lang_mk.startAnimation(fadeIn);
        textView_lang_eng.startAnimation(fadeIn);
        textView_lang_mk.startAnimation(fadeIn);
    }

    public void setLanguage(View view) {
        switch (view.getId()) {
            case R.id.button_lang_eng:
                LANGUAGE = "english";
                break;
            case R.id.button_lang_mk:
                LANGUAGE = "macedonian";
                break;
        }
        Intent setLanguage = new Intent(this, HomeScreenActivity.class);
        setLanguage.putExtra(APP_ID + "LANGUAGE", LANGUAGE);
        startActivity(setLanguage);
    }






}

