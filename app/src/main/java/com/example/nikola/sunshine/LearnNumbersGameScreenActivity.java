package com.example.nikola.sunshine;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.percent.PercentLayoutHelper;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LearnNumbersGameScreenActivity extends AppCompatActivity {

    // timers and handlers
    TimerTask task;
    final Handler handler = new Handler();
    Timer timer = new Timer();

    // lists
    List<Button> buttons_list = new ArrayList<>();
    List<Integer> numbers_list = new ArrayList<>();

    // buttons
    Button button_top;
    Button button_top_left;
    Button button_top_middle;
    Button button_top_right;
    Button button_center_left;
    Button button_center_middle;
    Button button_center_right;
    Button button_bottom_left;
    Button button_bottom_middle;
    Button button_bottom_right;

    // media players
    private MediaPlayer mp;
    private MediaPlayer mp_intro;
    private MediaPlayer mp_buttonClicks;
    private MediaPlayer mp_beep;

    // integer counters
    int progress = 0;

    // boolean values
    boolean startedGuessing = false;
    boolean playFirstTask = true;
    boolean introSoundPlayed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_numbers_game_screen);

        // populatiog and shuffling the list of numbers
        for (int i=0; i < 10; i++)
            numbers_list.add(i);
        Collections.shuffle(numbers_list);

        // creating the media players
        mp_intro = MediaPlayer.create(this, R.raw.learn_numbers_game_screen_intro);
        mp = MediaPlayer.create(this, R.raw.learn_numbers_game_screen_intro);
        mp_buttonClicks = MediaPlayer.create(this, R.raw.learn_numbers_game_screen_intro);
        mp_beep = MediaPlayer.create(this, R.raw.learn_numbers_game_screen_intro);

        // creating the listener
        View.OnTouchListener listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PercentRelativeLayout.LayoutParams params = (PercentRelativeLayout.LayoutParams)v.getLayoutParams();
                PercentLayoutHelper.PercentLayoutInfo info = params.getPercentLayoutInfo();
                float delta_width = (float)0.03;
                float delta_height = (float)0.05;
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    info.heightPercent += delta_height;
                    info.widthPercent += delta_width;
                    v.requestLayout();
                    onClick(v);
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    info.heightPercent -= delta_height;
                    info.widthPercent -= delta_width;
                    v.requestLayout();
                    return true;
                }
                return false;
            }
        };

        // creating the buttons
        button_top = (Button)findViewById(R.id.button_top);
        button_top_left = (Button)findViewById(R.id.button_top_left);
        button_top_middle = (Button)findViewById(R.id.button_top_middle);
        button_top_right = (Button)findViewById(R.id.button_top_right);
        button_center_left = (Button)findViewById(R.id.button_center_left);
        button_center_middle = (Button)findViewById(R.id.button_center_middle);
        button_center_right= (Button)findViewById(R.id.button_center_right);
        button_bottom_left = (Button)findViewById(R.id.button_bottom_left);
        button_bottom_middle = (Button)findViewById(R.id.button_bottom_middle);
        button_bottom_right = (Button)findViewById(R.id.button_bottom_right);

        // populating the button list
        buttons_list.add(button_top);
        buttons_list.add(button_top_left);
        buttons_list.add(button_top_middle);
        buttons_list.add(button_top_right);
        buttons_list.add(button_center_left);
        buttons_list.add(button_center_middle);
        buttons_list.add(button_center_right);
        buttons_list.add(button_bottom_left);
        buttons_list.add(button_bottom_middle);
        buttons_list.add(button_bottom_right);

        // setting the listener
        for (Button button : buttons_list) {
            button.setOnTouchListener(listener);
        }

        // creating the animations
        Animation bottom_to_top = AnimationUtils.loadAnimation(this, R.anim.cloud_bottom_to_top_animation);
        Animation right_to_left = AnimationUtils.loadAnimation(this, R.anim.cloud_right_to_left_animation);
        Animation top_to_bottom = AnimationUtils.loadAnimation(this, R.anim.cloud_top_to_bottom_animation);
        Animation left_to_right = AnimationUtils.loadAnimation(this, R.anim.cloud_left_to_right_animation);
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation_short);

        // setting up the animations
        // this is done before setting the backgrounds, so we can change the buttons' tag attributes
        for (int i = 0; i < buttons_list.size(); i++) {
            Button btn = buttons_list.get(i);

            String tag = btn.getTag().toString();

            switch (tag) {
                case "top":
                case "top_left":
                case "top_middle":
                    btn.startAnimation(bottom_to_top);
                    break;
                case "center_left":
                case "bottom_left":
                    btn.startAnimation(right_to_left);
                    break;
                case "bottom_middle":
                case "bottom_right":
                    btn.startAnimation(top_to_bottom);
                    break;
                case "top_right":
                case "center_right":
                    btn.startAnimation(left_to_right);
                    break;
                default:
                    btn.startAnimation(fade_in);
                    break;
            }
        }

        ImageView iv = (ImageView)findViewById(R.id.gameScreen_imageView);
        iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.indefinite_rotation_animation));

        // set buttons background
        Collections.shuffle(buttons_list);

        for (int i=0; i<buttons_list.size(); i++) {
            Button btn = buttons_list.get(i);

            // setting the buttons' backgrounds

            if (i == 0)
               btn.setBackgroundResource(R.drawable.rsz_cloud_0);
            else if (i == 1)
                btn.setBackgroundResource(R.drawable.rsz_cloud_1);
            else if (i == 2)
                btn.setBackgroundResource(R.drawable.rsz_cloud_2);
            else if (i == 3)
                btn.setBackgroundResource(R.drawable.rsz_cloud_3);
            else if (i == 4)
                btn.setBackgroundResource(R.drawable.rsz_cloud_4);
            else if (i == 5)
                btn.setBackgroundResource(R.drawable.rsz_cloud_5);
            else if (i == 6)
                btn.setBackgroundResource(R.drawable.rsz_cloud_6);
            else if (i == 7)
                btn.setBackgroundResource(R.drawable.rsz_cloud_7);
            else if (i == 8)
                btn.setBackgroundResource(R.drawable.rsz_cloud_8);
            else if (i == 9)
                btn.setBackgroundResource(R.drawable.rsz_cloud_9);

            btn.setTag(i);
        }
    }

    //@Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // pause the media player and make sure it starts from the beginning when the activity is resumed
        mp.pause();
        mp.seekTo(0);

        mp_intro.pause();
        mp_intro.seekTo(0);

        mp_buttonClicks.pause();
        mp_buttonClicks.seekTo(0);

        mp_beep.pause();
        mp_beep.seekTo(0);
        if (mp_intro.isPlaying())
            mp_intro.pause();
            mp_intro.reset();
            mp_intro = MediaPlayer.create(this, R.raw.learn_numbers_game_screen_intro);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!introSoundPlayed) {
            mp_intro.start();
            mp_intro.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // might wanna consider releasing
                @Override
                public void onCompletion(MediaPlayer mp) {
                    int number = numbers_list.get(progress);
                    introSoundPlayed = true;
                    setMediaPlayerTask(number);
                }
            });
        }
        else {
            int number = numbers_list.get(progress);
            setMediaPlayerTask(number);
        }
    }

    public void setMediaPlayerTask(int number) {
        mp.reset();
        if (number == 0)
            mp = MediaPlayer.create(this, R.raw.click_0);
        else if (number == 1)
            mp = MediaPlayer.create(this, R.raw.click_1);
        else if (number == 2)
            mp = MediaPlayer.create(this, R.raw.click_2);
        else if (number == 3)
            mp = MediaPlayer.create(this, R.raw.click_3);
        else if (number == 4)
            mp = MediaPlayer.create(this, R.raw.click_4);
        else if (number == 5)
            mp = MediaPlayer.create(this, R.raw.click_5);
        else if (number == 6)
            mp = MediaPlayer.create(this, R.raw.click_6);
        else if (number == 7)
            mp = MediaPlayer.create(this, R.raw.click_7);
        else if (number == 8)
            mp = MediaPlayer.create(this, R.raw.click_8);
        else if (number == 9)
            mp = MediaPlayer.create(this, R.raw.click_9);
        else if (number == 10){
            mp = MediaPlayer.create(this, R.raw.game_win_sound);
        }
        loopSound();
    }

    public void loopSound() {
        timer.cancel();
        timer = new Timer();

        task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mp.start();
                    }
                });
            }};

        timer.schedule(task, 0, 8000);

    }

    public void onClick(View v) {
        if (mp.isPlaying()) {
            mp.stop();
            mp.reset();
        }

        startedGuessing = true;

        final Button btn = (Button)findViewById(v.getId());
        int tag = (int)btn.getTag();

        if (tag == numbers_list.get(progress)) {
            // update game screen
            // pause and reset audio with another value
            // delete button off grid
            playButtonClickSound(true);
            if (playFirstTask)
                playFirstTask = false;

            // play the button fade out animation
            Animation fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation_short);
            fade_out.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    // remove the button from both the list and the view
                    buttons_list.remove(btn);

                    ViewGroup layout = (ViewGroup) btn.getParent();
                    if(null!=layout) //for safety only  as you are doing onClick
                        layout.removeView(btn);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    // not needed
                }

                @Override
                public void onAnimationStart(Animation animation) {
                    // not needed
                }
            });
            btn.startAnimation(fade_out);
        }
        else {
            playButtonClickSound(false);
        }

    }

    public void playButtonClickSound(boolean correct) {
        if (correct) {
            mp_beep = MediaPlayer.create(this, R.raw.onclick_correct_beep);
            mp_beep.start();
            mp_beep.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    Random random = new Random();

                    int value = random.nextInt(4);

                    if (value == 1)
                        mp_buttonClicks = MediaPlayer.create(LearnNumbersGameScreenActivity.this, R.raw.correct_1);
                    else if (value == 2)
                        mp_buttonClicks = MediaPlayer.create(LearnNumbersGameScreenActivity.this, R.raw.correct_2);
                    else if (value == 3)
                        mp_buttonClicks = MediaPlayer.create(LearnNumbersGameScreenActivity.this, R.raw.correct_3);
                    else
                        mp_buttonClicks = MediaPlayer.create(LearnNumbersGameScreenActivity.this, R.raw.correct_4);

                    mp_buttonClicks.start();
                    mp_buttonClicks.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer player) {
                            progress++;
                            if (progress == 10) {
                                setMediaPlayerTask(10);
                            }
                            else {
                                int number = numbers_list.get(progress);
                                setMediaPlayerTask(number);
                            }
                        }
                    });
                }
            });


        }
        else {
            mp_buttonClicks = MediaPlayer.create(this, R.raw.try_again);
            mp_buttonClicks.start();
            mp_buttonClicks.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    int number = numbers_list.get(progress);
                    setMediaPlayerTask(number);
                }
            });
        }

    }
}
