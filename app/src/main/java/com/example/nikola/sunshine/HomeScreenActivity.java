package com.example.nikola.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Intent languageIntent = getIntent();
        String message = languageIntent.getStringExtra(MainActivity.APP_ID + "LANGUAGE");
        //TextView tv = (TextView)findViewById(R.id.textView);
        //tv.setText(message);
    }

    public void logIn(View v) {
        Intent logIn = new Intent(this, LoginActivity.class);
        startActivity(logIn);
    }

    public void about(View v) {
        Intent about = new Intent(this, AboutActivity.class);
        startActivity(about);
    }


}
