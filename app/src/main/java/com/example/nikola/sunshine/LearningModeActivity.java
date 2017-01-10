package com.example.nikola.sunshine;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class LearningModeActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private boolean MEDIAPLAYER_RUNNING = true; // consider using media player running method instead


    TimerTask select_course_loop_task;
    final Handler handler = new Handler();
    Timer select_course_timer = new Timer();

    // buttons
    Button cloud_button;
    Button raindrop_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_mode);

        // initialize components
        mp = MediaPlayer.create(this, R.raw.select_course);
        cloud_button = (Button)findViewById(R.id.select_course_LEARN_NUMBERS);
        raindrop_button = (Button)findViewById(R.id.select_course_LEARN_LETTERS);

        // play cloud animation
        cloud_button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.base_cloud_intro_animation));
        raindrop_button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.base_raindrop_intro_animation));

        //mp.start();
        loopSelectCourseInBackground();

        ImageView imageSun = (ImageView)findViewById(R.id.imageSun);
        imageSun.startAnimation(AnimationUtils.loadAnimation(this, R.anim.indefinite_rotation_animation));

    }

    @Override
    protected void onPause() {
        super.onPause();
        // might need to check if any course has been selected
        mp.pause();
        mp.seekTo(0);
        MEDIAPLAYER_RUNNING = false;
        // this needs to be checked
        select_course_timer.cancel();
        select_course_timer.purge();
        select_course_loop_task.cancel();

    }


    @Override
    protected void onResume() {

        super.onResume();
        // might need to check if any course has been selected

        // play cloud animation
        cloud_button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.base_cloud_intro_animation));

        // handle sound effects
        select_course_timer = new Timer();
        MEDIAPLAYER_RUNNING = true;
        mp = MediaPlayer.create(this, R.raw.select_course);
        loopSelectCourseInBackground();

    }


    public void loopSelectCourseInBackground() {

        select_course_loop_task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        playSelectCourse();
                    }
                });
            }};

        select_course_timer.schedule(select_course_loop_task, 0, 10000);

    }

    public void playSelectCourse() {
        if (MEDIAPLAYER_RUNNING)
        mp.start();
    }

    public void startCourse_LEARN_NUMBERS(View view) {

        mp.reset();
        Intent start_course = new Intent(this, LearnNumbersActivity.class);
        startActivity(start_course);
        //overridePendingTransition(R.anim.fade_in_animation_short, R.anim.fade_out_animation_short);
    }

    public void startCourse_LEARN_LETTERS(View view) {
        mp.reset();
        Intent start_course = new Intent(this, SecondActivity.class);
        startActivity(start_course);
    }
}
